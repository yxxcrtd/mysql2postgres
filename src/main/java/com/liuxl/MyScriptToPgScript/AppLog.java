package com.liuxl.MyScriptToPgScript;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppLog
{

	/**
	 * 打印并写入日志
	 */
	public static void WriteLog(String logstr)
	{
		WriteLog(logstr, false);
	}

	/**
	 * 打印并写入日志
	 */
	public static void WriteLog(String logstr, boolean iserr)
	{
		String LogFileName = System.getProperty("user.dir") + "/autocode.log";
		try
		{
			//FileWriter fw = new FileWriter(LogFileName, true);
			OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(LogFileName, true), "UTF-8");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");// 设置日期格式
			String wlog = "* " + df.format(new Date()) + "\r\n";
			if (iserr)
			{
				wlog += "× " + logstr;
			}
			else
			{
				wlog += "√ " + logstr;
			}
			fw.write(wlog + "\r\n");
			fw.close();
			System.out.println(wlog);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

}
