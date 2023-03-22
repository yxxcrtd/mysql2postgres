# MysqlScriptToPgScript 将Mysql的建表脚本转换为PostgreSQL的建表脚本。


## 使用方法
### 1、准备MySQL数据库脚本
* MySQL数据库脚本文件以 .sql 结尾；
* 所有数据库脚本文件请放在同一个文件夹下，不要放在子目录中；
* 程序自动遍历所有脚本文件，在另一文件夹中生成PgSQL脚本；

MySQL脚本示例：
```
CREATE TABLE `t_sys_role`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;
```

注意：必须有字段注释和表注释！

### 2、编辑config.xml配置文件
配置文件格式如下：

```
<?xml version="1.0" encoding="UTF-8" ?>
<Config>
    <!-- MySQL数据表脚本文件所在目录 -->
    <Script_MySQL ConType="dir">E:\mytable</Script_MySQL>
    <!-- 生成的PgSQL数据表脚本文件所在目录 -->
    <Script_PostgreSQL ConType="dir">E:\pgtable</Script_PostgreSQL>
</Config>
```
以上默认已经在E盘中创建好了，mytable和pgtable的文件夹。
其中mytable里存放需要转换的mysql脚本。

注意：目录必须存在！


### 3、执行，两种方式
#### 1）直接修改config文件内容
打开App.java主类，执行main函数即可。



#### 2）生成jar包
1.执行mvn install

2.使用方法1：行执行下列命令，生成所有代码：

```
java -jar .\MyScriptToPgScript.jar config.xml
```

其中：MyScriptToPgScript.jar 为生成的 jar 包， config.xml 为配置文件路径。

## 注意
* 表脚本必须要有字段注释和表注释。
* 程序会生成 autocode.log 日志文件。
* 仅支持 UTF-8 。


## 生成的PgSQL数据表脚本示例：

```

-- ----------------------------
-- Table structure for t_sys_role
-- -- 角色表
-- ----------------------------

-- DROP TABLE IF EXISTS "t_sys_role";

CREATE TABLE "t_sys_role"(
	"id" character varying(255) COLLATE "default" NOT NULL,
	"name" character varying(255) COLLATE "default" DEFAULT NULL::character varying,
	CONSTRAINT t_sys_role_pkey PRIMARY KEY ("id")
)
WITH (
    OIDS = FALSE
)
;

COMMENT ON TABLE "t_sys_role" IS '角色表';

COMMENT ON COLUMN "t_sys_role"."id" IS 'id';
COMMENT ON COLUMN "t_sys_role"."name" IS '角色名称';

```


## 结语

* 推荐使用第一种方法，也方便错误调试。

* 第二种方法适合给不会使用java的人，打成现有文件，自己去执行即可。# mysql2postgres
