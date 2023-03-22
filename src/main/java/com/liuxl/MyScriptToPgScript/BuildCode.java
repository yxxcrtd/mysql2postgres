package com.liuxl.MyScriptToPgScript;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class BuildCode
{

	/**
	 * 生成 脚本 类
	 *
	 * @param zt
	 * @throws IOException
	 */
	public static void BuildScript(mytable zt, File trfile) throws IOException
	{
		StringBuilder prostrb;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		prostrb = new StringBuilder("\r\n");
		prostrb.append("-- ----------------------------\r\n");
		prostrb.append("-- Table structure for ");
		prostrb.append(zt.getTableName());
		prostrb.append("\r\n-- -- ");
		prostrb.append(df.format(new Date()));
		prostrb.append("\r\n-- -- ");
		prostrb.append(zt.getTableComment());
		prostrb.append("\r\n-- ----------------------------\r\n\r\n");
		for (myfield mf : zt.getFields())
		{
			if (mf.getFieldAutoInc()) {
				prostrb.append("CREATE SEQUENCE \"");
				prostrb.append(zt.getTableName());
				prostrb.append("_");
				prostrb.append(mf.getFieldName());
				prostrb.append("_seq\"\r\n");
				prostrb.append("    INCREMENT 1\r\n    START 1\r\n    MINVALUE 1\r\n    MAXVALUE 9223372036854775807\r\n    CACHE 1;\r\n\r\n");
			}
		}
		prostrb.append("-- DROP TABLE IF EXISTS \"");
		prostrb.append(zt.getTableName());
		prostrb.append("\";\r\n\r\nCREATE TABLE \"");
		prostrb.append(zt.getTableName());
		prostrb.append("\"(");
		for (myfield mf : zt.getFields())
		{
			prostrb.append("\r\n	\"");
			prostrb.append(mf.getFieldName());
			prostrb.append("\" ");
			prostrb.append(mf.getPgFDBType());
			if (mf.getFieldLength() != null) {
				prostrb.append("(");
				prostrb.append(mf.getFieldLength());
				prostrb.append(")");
			}
			if (StringUtils.isNotBlank(mf.getFieldCollate())) {
				prostrb.append(" COLLATE \"");
				prostrb.append(mf.getFieldCollate());
				prostrb.append("\"");
			}
			if (mf.getFieldNotNull()) {
				prostrb.append(" NOT NULL");
			}
			if (StringUtils.isNotBlank(mf.getFieldDefVal())) {
				prostrb.append(" DEFAULT ");
				prostrb.append(mf.getFieldDefVal());
			} else if (mf.getFieldAutoInc()) {
				prostrb.append(" DEFAULT nextval('\"");
				prostrb.append(zt.getTableName());
				prostrb.append("_");
				prostrb.append(mf.getFieldName());
				prostrb.append("_seq\"'::regclass)");
			}
			prostrb.append(",");
		}
		if (zt.getPriFields().size() > 0) {
			prostrb.append("\r\n	CONSTRAINT ");
			prostrb.append(zt.getTableName());
			prostrb.append("_pkey PRIMARY KEY (");
			for (myfield mm : zt.getPriFields()) {
				prostrb.append("\"");
				prostrb.append(mm.getFieldName());
				prostrb.append("\",");
			}
			prostrb.deleteCharAt(prostrb.length() - 1);
			prostrb.append(")");
		} else {
			prostrb.deleteCharAt(prostrb.length() - 1);
		}
		prostrb.append("\r\n)\r\nWITH (\r\n    OIDS = FALSE\r\n)\r\n;\r\n\r\n");
		prostrb.append("COMMENT ON TABLE \"");
		prostrb.append(zt.getTableName());
		prostrb.append("\" IS '");
		prostrb.append(zt.getTableComment());
		prostrb.append("';\r\n\r\n");
		for (myfield mf : zt.getFields())
		{
			prostrb.append("COMMENT ON COLUMN \"");
			prostrb.append(zt.getTableName());
			prostrb.append("\".\"");
			prostrb.append(mf.getFieldName());
			prostrb.append("\" IS '");
			prostrb.append(mf.getFieldComment());
			prostrb.append("';\r\n");
		}
		try
		{
			OutputStreamWriter fw = new OutputStreamWriter(
					new FileOutputStream(trfile, true), "UTF-8");
			fw.write(prostrb.toString());
			fw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			throw e;
		}
	}

}
