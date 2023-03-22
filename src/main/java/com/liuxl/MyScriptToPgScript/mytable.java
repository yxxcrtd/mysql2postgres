package com.liuxl.MyScriptToPgScript;

import java.util.List;

public class mytable{

	/**
	 * 包名
	 */
	private String PackageName;

	/**
	 * 表名称前缀
	 */
	private String TableNamePrefixion;

	/**
	 * 数据库脚本目录
	 */
	private String TableScriptDir;

	/**
	 * Model目录
	 */
	private String ModelDir;

	/**
	 * Dao目录
	 */
	private String DaoDir;

	/**
	 * Service目录
	 */
	private String ServiceDir;

	/**
	 * ServiceImpl目录
	 */
	private String ServiceImplDir;

	/**
	 * 表名
	 */
	private String TableName;

	/**
	 * 真表名
	 */
	private String TableTBName;

	/**
	 * 表注释
	 */
	private String TableComment;

	/**
	 * 主键字段集合
	 */
	private List<myfield> PriFields;

	/**
	 * 非主键字段集合
	 */
	private List<myfield> CommFields;

	/**
	 * 所有字段集合
	 */
	private List<myfield> Fields;

	public String getPackageName()
	{
		return PackageName;
	}

	public void setPackageName(String packageName)
	{
		PackageName = packageName;
	}

	public String getTableNamePrefixion()
	{
		return TableNamePrefixion;
	}

	public void setTableNamePrefixion(String tableNamePrefixion)
	{
		TableNamePrefixion = tableNamePrefixion;
	}

	public String getTableScriptDir()
	{
		return TableScriptDir;
	}

	public void setTableScriptDir(String tableScriptDir)
	{
		TableScriptDir = tableScriptDir;
	}

	public String getModelDir()
	{
		return ModelDir;
	}

	public void setModelDir(String modelDir)
	{
		ModelDir = modelDir;
	}

	public String getDaoDir()
	{
		return DaoDir;
	}

	public void setDaoDir(String daoDir)
	{
		DaoDir = daoDir;
	}

	public String getServiceDir()
	{
		return ServiceDir;
	}

	public void setServiceDir(String serviceDir)
	{
		ServiceDir = serviceDir;
	}

	public String getServiceImplDir()
	{
		return ServiceImplDir;
	}

	public void setServiceImplDir(String serviceImplDir)
	{
		ServiceImplDir = serviceImplDir;
	}

	public String getTableName()
	{
		return TableName;
	}

	public void setTableName(String tableName)
	{
		TableName = tableName;
		TableTBName = TableNamePrefixion + tableName;
	}

	public String getTableTBName()
	{
		return TableTBName;
	}

	public void setTableTBName(String tableTBName)
	{
		TableTBName = tableTBName;
	}

	public String getTableComment()
	{
		return TableComment;
	}

	public void setTableComment(String tableComment)
	{
		TableComment = tableComment;
	}

	public List<myfield> getPriFields()
	{
		return PriFields;
	}

	public void setPriFields(List<myfield> priFields)
	{
		PriFields = priFields;
	}

	public List<myfield> getCommFields()
	{
		return CommFields;
	}

	public void setCommFields(List<myfield> commFields)
	{
		CommFields = commFields;
	}

	public List<myfield> getFields()
	{
		return Fields;
	}

	public void setFields(List<myfield> fields)
	{
		Fields = fields;
	}

}