package com.liuxl.MyScriptToPgScript;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Hello mysql-to-pgsql!
 *
 */
public class App {
	public static void main(String[] args) {
		String LogFileName = System.getProperty("user.dir") + "/autocode.log";
		File logfile = new File(LogFileName);
		if (!logfile.exists()) {
			try {
				logfile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				return;
			}
		}
		logfile = null;
		System.out.println("日志文件：" + LogFileName);

		if (StartPro(args)) {
			AppLog.WriteLog("结束！全部成功!\r\n");
		} else {
			AppLog.WriteLog("结束！有错误发生!\r\n", true);
		}
	}

	/**
	 * 开始自动生成
	 *
	 * @param args
	 *            命令参数
	 * @return 是否全部成功
	 */
	private static boolean StartPro(String[] args) {
		String CusTableName = null; // 只生成针对一个文件转换
		AppLog.WriteLog("检查参数1：XML配置文件");
		if (args.length < 1 || args.length > 2) {
			AppLog.WriteLog("参数为空！", true);
			//return false;
			//若直接使用主函数执行，则赋值以下默认路径。
			args = new String[1];
			args[0] = System.getProperty("user.dir") + "/config.xml";//"./config.xml";
			AppLog.WriteLog("已经赋值默认参数路径："+args[0] , true);
		}
		if (args.length == 2) {
			CusTableName = args[1];
		}
		AppLog.WriteLog("解析文件：" + args[0]);
		String Script_MySQL;
		String Script_PostgreSQL;
		try {
			SAXReader saxReader = new SAXReader();
			Document doc = saxReader.read(new File(args[0]));
			Element zroot = doc.getRootElement();
			Script_MySQL = VNode(zroot, "Script_MySQL");
			Script_PostgreSQL = VNode(zroot, "Script_PostgreSQL");
		} catch (Exception e) {
			AppLog.WriteLog("发生错误：" + e.getMessage(), true);
			return false;
		}
		File[] fs = new File(Script_MySQL).listFiles();
		for (int i = 0; i < fs.length; i++) {
			String trfile = Script_PostgreSQL + "/" + fs[i].getName();
			File ftfile = new File(trfile);
			if (ftfile.exists()) {
				AppLog.WriteLog("目标文件已经存在，不在分析：" + Script_PostgreSQL + "/" + fs[i].getName());
				continue;
			}
			String rs = null;
			if (StringUtils.isBlank(CusTableName)) {
				rs = ".+(?i)\\.sql$";
			} else {
				rs = "^(?i)" + CusTableName + "\\.sql$";
			}
			if (fs[i].getName().matches(rs)) {
				AppLog.WriteLog("分析数据库脚本：" + fs[i].getName());
				String tablestr = "";
				try {
					// FileReader fr = new FileReader(fs[i]);
					InputStreamReader fr = new InputStreamReader(new FileInputStream(fs[i]), "UTF-8");
					BufferedReader br = new BufferedReader(fr);
					try {
						String rline = br.readLine();
						while (rline != null) {
							boolean canread = false;
							StringBuilder sbf = new StringBuilder();
							while (rline != null) {
								if (rline.matches("(?i)\\s*CREATE\\s*TABLE\\s*`[^`]+`\\s*\\(.*")) {
									canread = true;
								}
								if (canread) {
									sbf.append(rline);
								}
								if (rline.matches(".*?\\).*?(?i)COMMENT\\s*=\\s*'[^']+'.*?;.*?$")) {
									rline = br.readLine();
									break;
								}
								rline = br.readLine();
							}
							tablestr = sbf.toString();
							if (tablestr.matches(
									"(?i)\\s*CREATE\\s*TABLE\\s*`[^`]+`\\s*\\(.*\\).*COMMENT\\s*=\\s*'[^']+'.*;.*$")) {
								mytable zt = GetTable(tablestr);
								if (zt != null) {
									if (zt.getFields().isEmpty() && zt.getPriFields().isEmpty()) {
										AppLog.WriteLog("发生错误：[" + zt.getTableName() + "] 表中没有字段！", true);
									} else {
										BuildCode.BuildScript(zt, ftfile);
										AppLog.WriteLog("成功生成了 " + trfile + " 文件。");
									}
								}
							}
						}
						br.close();
						fr.close();
					} catch (IOException e) {
						e.printStackTrace();
						AppLog.WriteLog("发生错误：" + e.getMessage(), true);
					} catch (Exception e) {
						e.printStackTrace();
						AppLog.WriteLog("发生错误：" + e.getMessage(), true);
					}
				} catch (Exception e) {
					e.printStackTrace();
					AppLog.WriteLog("发生错误：" + e.getMessage(), true);
				}

			}
		}
		return true;

	}

	/**
	 * 将脚本字符串转为table对象
	 */
	private static mytable GetTable(String TabStr)
			throws Exception {
		Matcher zm = Pattern
				.compile("\\s*CREATE\\s*TABLE\\s*`([^`]+)`\\s*\\((.*)\\).*COMMENT\\s*=\\s*'([^']+)'.*;.*", Pattern.CASE_INSENSITIVE)
				.matcher(TabStr);
		if (zm.find()) {
			String TableName = zm.group(1).trim();
			String TableComment = zm.group(3).trim();
			AppLog.WriteLog("找到数据表：" + TableName);
			String TableCon = zm.group(2).trim();
			List<String> prilist = new ArrayList<String>();
			zm = Pattern.compile(".*PRIMARY\\s+KEY\\s*\\(([^\\(\\)]+)\\).*").matcher(TableCon);
			if (zm.find()) {
				zm = Pattern.compile("\\s*`\\s*([^`]+?)\\s*`\\s*").matcher(zm.group(1));
				while (zm.find()) {
					prilist.add(zm.group(1).trim());
				}
			}
			mytable zt = new mytable();
			zt.setTableName(TableName);
			zt.setTableComment(TableComment);
			zt.setFields(new ArrayList<myfield>());
			zt.setPriFields(new ArrayList<myfield>());
			zt.setCommFields(new ArrayList<myfield>());
			zm = Pattern
					.compile(
							"\\s*`([^`]+)`\\s*([a-zA-Z]+?)(\\s+|\\s*\\([\\d\\s,]+\\))([^`]*?)COMMENT\\s*'([^`]+)'\\s*(?:,|\\)|$)")
					.matcher(TableCon);
			while (zm.find()) {
				TableCon = zm.group(0).trim();
				myfield zf = new myfield();
				zf.setFieldName(zm.group(1).trim());
				Integer len = null;
				try {
					String strlen = zm.group(3).trim().replace("(", "").replace(")", "");
					len = Integer.valueOf(strlen);
				} catch (Exception e) {
				}
				zf.setFieldLength(len);
				zf.setFieldComment(zm.group(5).trim());
				String gr4 = zm.group(4);
				zf.setFieldNotNull(gr4.matches(".*NOT\\s*NULL.*"));
				zf.setFieldAutoInc(gr4.matches(".*AUTO_INCREMENT.*"));
				Matcher z4 = Pattern
						.compile("DEFAULT\\s+([^\\s]+)\\s+", Pattern.CASE_INSENSITIVE)
						.matcher(gr4);
				if (z4.find()) {
					zf.setFieldDefVal(z4.group(1));
				}
				zf.setFDBType(zm.group(2).trim().toLowerCase());
				boolean ispri = false;
				for (String pstr : prilist) {
					if (pstr.equals(zf.getFieldName())) {
						ispri = true;
						break;
					}
				}
				if (ispri) {
					zt.getPriFields().add(zf);
				} else {
					zt.getCommFields().add(zf);
				}
				zt.getFields().add(zf);
			}
			return zt;
		} else {
			AppLog.WriteLog("发生错误：没有找到表数据！", true);
			return null;
		}
	}

	/**
	 * 检查配置节点是否为空，配置节点为目录的检查目录是否存在
	 */
	private static String VNode(Element zroot, String NodeName) throws Exception {
		return VNode(zroot, NodeName, true);
	}

	/**
	 * 检查配置节点是否为空，配置节点为目录的检查目录是否存在
	 */
	private static String VNode(Element zroot, String NodeName, Boolean NotBlank) throws Exception {
		Element nownode = zroot.element(NodeName);
		String StPr = nownode.getText().trim();
		if (NotBlank && StringUtils.isBlank(StPr)) {
			throw new Exception(NodeName + " 为空！");
		} else {
			if (nownode.attribute("ConType") != null) {
				if ("dir".equals(nownode.attribute("ConType").getValue())) {
					File prodir = new File(StPr);
					if (!prodir.exists()) {
						throw new Exception(NodeName + "：" + StPr + " 目录不存在！");
					}
				}
			}
			AppLog.WriteLog(NodeName + "： " + StPr);
			return StPr;
		}
	}

}
