package com.liuxl.MyScriptToPgScript;

public class myfield {

	/**
	 * 字段名
	 */
	private String FieldName;

	/**
	 * 字段名在Model中的全小写名称
	 */
	// private String FieldModelName;

	/**
	 * 字段名在Model中Get\Set时的首字母大写名称
	 */
	// private String FieldGetSetName;

	/**
	 * 字段注释
	 */
	private String FieldComment;

	/*
	 * 字段不为空
	 */
	private Boolean FieldNotNull;

	/*
	 * 字段自增长
	 */
	private Boolean FieldAutoInc;

	/**
	 * 字段长度
	 */
	private Integer FieldLength;

	/**
	 * 字段排序
	 */
	private String FieldCollate;

	/**
	 * 字段默认值
	 */
	private String FieldDefVal;

	/**
	 * MySql中的类型名(必须全小写)
	 */
	private String FDBType;

	/**
	 * PgSql中的类型名(必须全小写)
	 */
	private String PgFDBType;

	/**
	 * jdbc中的类型名
	 */
	private String FJDBCType;

	/*
	 * Model中的类型名(全)
	 */
	private String FModleTypeFull;

	/*
	 * Model中的类型名(简)
	 */
	private String FModleType;

	public String getFieldName() {
		return FieldName;
	}

	public void setFieldName(String fieldName) {
		FieldName = fieldName;
		// SetFieldName();
	}

	// public String getFieldModelName()
	// {
	// return FieldModelName;
	// }

	// public void setFieldModelName(String fieldModelName)
	// {
	// FieldModelName = fieldModelName;
	// }

	// public String getFieldGetSetName()
	// {
	// return FieldGetSetName;
	// }

	// public void setFieldGetSetName(String fieldGetSetName)
	// {
	// FieldGetSetName = fieldGetSetName;
	// }

	public String getFieldComment() {
		return FieldComment;
	}

	public void setFieldComment(String fieldComment) {
		FieldComment = fieldComment;
	}

	public String getFieldCollate() {
		return FieldCollate;
	}

	public void setFieldCollate(String fieldCollate) {
		FieldCollate = fieldCollate;
	}

	public String getFieldDefVal() {
		return FieldDefVal;
	}

	public void setFieldDefVal(String fieldDefVal) {
		FieldDefVal = fieldDefVal;
	}

	public Boolean getFieldNotNull() {
		return FieldNotNull;
	}

	public void setFieldNotNull(Boolean fieldNotNull) {
		FieldNotNull = fieldNotNull;
	}

	public Boolean getFieldAutoInc() {
		return FieldAutoInc;
	}

	public void setFieldAutoInc(Boolean fieldAutoInc) {
		FieldAutoInc = fieldAutoInc;
	}

	public Integer getFieldLength() {
		return FieldLength;
	}

	public void setFieldLength(Integer fieldLength) {
		FieldLength = fieldLength;
	}

	public String getFDBType() {
		return FDBType;
	}

	public String getPgFDBType() {
		return PgFDBType;
	}

	public void setFDBType(String fDBType) throws Exception {
		FDBType = fDBType;
		SetTypeByDBType();
	}

	public String getFJDBCType() {
		return FJDBCType;
	}

	// public void setFJDBCType(String fJDBCType)
	// {
	// FJDBCType = fJDBCType;
	// }

	public String getFModleTypeFull() {
		return FModleTypeFull;
	}

	// public void setFModleTypeFull(String fModleTypeFull)
	// {
	// FModleTypeFull = fModleTypeFull;
	// }

	public String getFModleType() {
		return FModleType;
	}

	// public void setFModleType(String fModleType)
	// {
	// FModleType = fModleType;
	// }

	/**
	 * 设置字段名
	 */
	// private void SetFieldName()
	// {
	// this.FieldModelName = this.FieldName.toLowerCase();
	// String zstr0 = this.FieldModelName.substring(0, 1).toUpperCase();
	// String zstr = this.FieldModelName.substring(1);
	// this.FieldGetSetName = zstr0 + zstr;
	// }

	/**
	 * 通过数据库类型名，设置其他类型名
	 */
	private void SetTypeByDBType() throws Exception {
		switch (this.FDBType) {
		case "tinyint":
			this.PgFDBType = "smallint";
			this.FJDBCType = "TINYINT";
			this.FModleType = "Byte";
			this.FModleTypeFull = "java.lang.Byte";
			this.FieldCollate = null;
			this.FieldLength = null;
			if (this.FieldDefVal != null) {
				this.FieldDefVal = this.FieldDefVal.replace("'", "");
			}
			break;
		case "smallint":
			this.PgFDBType = "smallint";
			this.FJDBCType = "SMALLINT";
			this.FModleType = "Short";
			this.FModleTypeFull = "java.lang.Short";
			this.FieldCollate = null;
			this.FieldLength = null;
			if (this.FieldDefVal != null) {
				this.FieldDefVal = this.FieldDefVal.replace("'", "");
			}
			break;
		case "mediumint":
			this.PgFDBType = "integer";
			this.FJDBCType = "INTEGER";
			this.FModleType = "Integer";
			this.FModleTypeFull = "java.lang.Integer";
			this.FieldCollate = null;
			this.FieldLength = null;
			if (this.FieldDefVal != null) {
				this.FieldDefVal = this.FieldDefVal.replace("'", "");
			}
			break;
		case "int":
			this.PgFDBType = "integer";
			this.FJDBCType = "INTEGER";
			this.FModleType = "Integer";
			this.FModleTypeFull = "java.lang.Integer";
			this.FieldCollate = null;
			this.FieldLength = null;
			if (this.FieldDefVal != null) {
				this.FieldDefVal = this.FieldDefVal.replace("'", "");
			}
			break;
		case "integer":
			this.PgFDBType = "integer";
			this.FJDBCType = "INTEGER";
			this.FModleType = "Integer";
			this.FModleTypeFull = "java.lang.Integer";
			this.FieldCollate = null;
			this.FieldLength = null;
			if (this.FieldDefVal != null) {
				this.FieldDefVal = this.FieldDefVal.replace("'", "");
			}
			break;
		case "bigint":
			this.PgFDBType = "bigint";
			this.FJDBCType = "BIGINT";
			this.FModleType = "Long";
			this.FModleTypeFull = "java.lang.Long";
			this.FieldCollate = null;
			this.FieldLength = null;
			if (this.FieldDefVal != null) {
				this.FieldDefVal = this.FieldDefVal.replace("'", "");
			}
			break;
		case "bit":
			this.PgFDBType = "boolean";
			this.FJDBCType = "BIT";
			this.FModleType = "Boolean";
			this.FModleTypeFull = "java.lang.Boolean";
			this.FieldCollate = null;
			this.FieldLength = null;
			if (this.FieldDefVal != null) {
				this.FieldDefVal = this.FieldDefVal.replace("'", "");
			}
			break;
		case "real":
			this.PgFDBType = "real";
			this.FJDBCType = "DOUBLE";
			this.FModleType = "Double";
			this.FModleTypeFull = "java.lang.Double";
			this.FieldCollate = null;
			this.FieldLength = null;
			if (this.FieldDefVal != null) {
				this.FieldDefVal = this.FieldDefVal.replace("'", "");
			}
			break;
		case "double":
			this.PgFDBType = "double precision";
			this.FJDBCType = "DOUBLE";
			this.FModleType = "Double";
			this.FModleTypeFull = "java.lang.Double";
			this.FieldCollate = null;
			this.FieldLength = null;
			if (this.FieldDefVal != null) {
				this.FieldDefVal = this.FieldDefVal.replace("'", "");
			}
			break;
		case "float":
			this.PgFDBType = "real";
			this.FJDBCType = "REAL";
			this.FModleType = "Float";
			this.FModleTypeFull = "java.lang.Float";
			this.FieldCollate = null;
			this.FieldLength = null;
			if (this.FieldDefVal != null) {
				this.FieldDefVal = this.FieldDefVal.replace("'", "");
			}
			break;
		case "decimal":
			this.PgFDBType = "numeric";
			this.FJDBCType = "DECIMAL";
			this.FModleType = "BigDecimal";
			this.FModleTypeFull = "	java.math.BigDecimal";
			this.FieldCollate = null;
			this.FieldLength = null;
			if (this.FieldDefVal != null) {
				this.FieldDefVal = this.FieldDefVal.replace("'", "");
			}
			break;
		case "numeric":
			this.PgFDBType = "numeric";
			this.FJDBCType = "DECIMAL";
			this.FModleType = "BigDecimal";
			this.FModleTypeFull = "	java.math.BigDecimal";
			this.FieldCollate = null;
			this.FieldLength = null;
			if (this.FieldDefVal != null) {
				this.FieldDefVal = this.FieldDefVal.replace("'", "");
			}
			break;
		case "char":
			this.PgFDBType = "character";
			this.FJDBCType = "CHAR";
			this.FModleType = "String";
			this.FModleTypeFull = "java.lang.String";
			this.FieldCollate = "default";
			if (this.FieldDefVal != null) {
				this.FieldDefVal = this.FieldDefVal + "::bpchar";
			}
			break;
		case "varchar":
			this.PgFDBType = "character varying";
			this.FJDBCType = "VARCHAR";
			this.FModleType = "String";
			this.FModleTypeFull = "java.lang.String";
			this.FieldCollate = "default";
			if (this.FieldDefVal != null) {
				this.FieldDefVal = this.FieldDefVal + "::character varying";
			}
			break;
		case "date":
			this.PgFDBType = "date";
			this.FJDBCType = "DATE";
			this.FModleType = "Date";
			this.FModleTypeFull = "java.util.Date";
			this.FieldCollate = null;
			this.FieldLength = null;
			if (this.FieldDefVal != null) {
				this.FieldDefVal = this.FieldDefVal + "::" + this.PgFDBType;
			}
			break;
		case "time":
			this.PgFDBType = "time with time zone";
			this.FJDBCType = "TIME";
			this.FModleType = "Date";
			this.FModleTypeFull = "java.util.Date";
			this.FieldCollate = null;
			this.FieldLength = null;
			if (this.FieldDefVal != null) {
				this.FieldDefVal = this.FieldDefVal + "::" + this.PgFDBType;
			}
			break;
		case "timestamp":
			this.PgFDBType = "timestamp with time zone";
			this.FJDBCType = "TIMESTAMP";
			this.FModleType = "Date";
			this.FModleTypeFull = "java.util.Date";
			this.FieldCollate = null;
			this.FieldLength = null;
			if (this.FieldDefVal != null) {
				this.FieldDefVal = this.FieldDefVal + "::" + this.PgFDBType;
			}
			break;
		case "datetime":
			this.PgFDBType = "timestamp with time zone";
			this.FJDBCType = "TIMESTAMP";
			this.FModleType = "Date";
			this.FModleTypeFull = "java.util.Date";
			this.FieldCollate = null;
			this.FieldLength = null;
			if (this.FieldDefVal != null) {
				this.FieldDefVal = this.FieldDefVal + "::" + this.PgFDBType;
			}
			break;
		case "tinytext":
			this.PgFDBType = "text";
			this.FJDBCType = "VARCHAR";
			this.FModleType = "String";
			this.FModleTypeFull = "java.lang.String";
			this.FieldCollate = "default";
			this.FieldLength = null;
			if (this.FieldDefVal != null) {
				this.FieldDefVal = this.FieldDefVal + "::character varying";
			}
			break;
		case "tinyblob":
			this.PgFDBType = "bytea";
			this.FJDBCType = "BINARY";
			this.FModleType = "byte[]";
			this.FModleTypeFull = "";
			this.FieldCollate = null;
			this.FieldLength = null;
			if (this.FieldDefVal != null) {
				this.FieldDefVal = this.FieldDefVal + "::" + this.PgFDBType;
			}
			break;
		case "blob":
			this.PgFDBType = "bytea";
			this.FJDBCType = "LONGVARBINARY";
			this.FModleType = "byte[]";
			this.FModleTypeFull = "";
			this.FieldCollate = null;
			this.FieldLength = null;
			if (this.FieldDefVal != null) {
				this.FieldDefVal = this.FieldDefVal + "::" + this.PgFDBType;
			}
			break;
		case "mediumblob":
			this.PgFDBType = "bytea";
			this.FJDBCType = "LONGVARBINARY";
			this.FModleType = "byte[]";
			this.FModleTypeFull = "";
			this.FieldCollate = null;
			this.FieldLength = null;
			if (this.FieldDefVal != null) {
				this.FieldDefVal = this.FieldDefVal + "::" + this.PgFDBType;
			}
			break;
		case "longblob":
			this.PgFDBType = "bytea";
			this.FJDBCType = "LONGVARBINARY";
			this.FModleType = "byte[]";
			this.FModleTypeFull = "";
			this.FieldCollate = null;
			this.FieldLength = null;
			if (this.FieldDefVal != null) {
				this.FieldDefVal = this.FieldDefVal + "::" + this.PgFDBType;
			}
			break;
		case "text":
			this.PgFDBType = "text";
			this.FJDBCType = "LONGVARCHAR";
			this.FModleType = "String";
			this.FModleTypeFull = "java.lang.String";
			this.FieldCollate = "default";
			this.FieldLength = null;
			if (this.FieldDefVal != null) {
				this.FieldDefVal = this.FieldDefVal + "::character varying";
			}
			break;
		case "mediumtext":
			this.PgFDBType = "text";
			this.FJDBCType = "LONGVARCHAR";
			this.FModleType = "String";
			this.FModleTypeFull = "java.lang.String";
			this.FieldCollate = "default";
			this.FieldLength = null;
			if (this.FieldDefVal != null) {
				this.FieldDefVal = this.FieldDefVal + "::character varying";
			}
			break;
		case "longtext":
			this.PgFDBType = "text";
			this.FJDBCType = "LONGVARCHAR";
			this.FModleType = "String";
			this.FModleTypeFull = "java.lang.String";
			this.FieldCollate = "default";
			this.FieldLength = null;
			if (this.FieldDefVal != null) {
				this.FieldDefVal = this.FieldDefVal + "::character varying";
			}
			break;
		default:
			throw new Exception("没有找到 [" + this.FDBType + "]对应的类型名！");
		}
	}
}
