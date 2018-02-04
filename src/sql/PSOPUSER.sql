/*
Navicat Oracle Data Transfer
Oracle Client Version : 11.2.0.1.0

Source Server         : 主卧台式机PSOPUSER
Source Server Version : 110200
Source Host           : 127.0.0.1:1521
Source Schema         : PSOPUSER

Target Server Type    : ORACLE
Target Server Version : 110200
File Encoding         : 65001

Date: 2018-02-05 00:20:42
*/


-- ----------------------------
-- Table structure for PERSISTENT_LOGINS
-- ----------------------------
DROP TABLE "PSOPUSER"."PERSISTENT_LOGINS";
CREATE TABLE "PSOPUSER"."PERSISTENT_LOGINS" (
"USERNAME" VARCHAR2(64 BYTE) NULL ,
"SERIES" VARCHAR2(64 BYTE) NOT NULL ,
"TOKEN" VARCHAR2(64 BYTE) NULL ,
"LAST_USED" TIMESTAMP(6)  NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON TABLE "PSOPUSER"."PERSISTENT_LOGINS" IS 'Spring Remember me 持久化';

-- ----------------------------
-- Records of PERSISTENT_LOGINS
-- ----------------------------

-- ----------------------------
-- Table structure for SYS_AUTHORITIES
-- ----------------------------
DROP TABLE "PSOPUSER"."SYS_AUTHORITIES";
CREATE TABLE "PSOPUSER"."SYS_AUTHORITIES" (
"AUTHORITY_ID" VARCHAR2(100 BYTE) NOT NULL ,
"AUTHORITY_MARK" VARCHAR2(100 BYTE) NULL ,
"AUTHORITY_NAME" VARCHAR2(100 BYTE) NOT NULL ,
"AUTHORITY_DESC" VARCHAR2(200 BYTE) NULL ,
"MESSAGE" VARCHAR2(100 BYTE) NULL ,
"ENABLE" NUMBER NULL ,
"ISSYS" NUMBER NULL ,
"MODULE_ID" VARCHAR2(100 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of SYS_AUTHORITIES
-- ----------------------------
INSERT INTO "PSOPUSER"."SYS_AUTHORITIES" VALUES ('579a1c5c-ece6-4a2d-8675-0d53b091112b', 'ROLE_TO_INDEX', 'ROLE_TO_INDEX', '进入首页', 'sda', '1', '0', '1');

-- ----------------------------
-- Table structure for SYS_AUTHORITIES_RESOURCES
-- ----------------------------
DROP TABLE "PSOPUSER"."SYS_AUTHORITIES_RESOURCES";
CREATE TABLE "PSOPUSER"."SYS_AUTHORITIES_RESOURCES" (
"ID" VARCHAR2(100 BYTE) NOT NULL ,
"RESOURCE_ID" VARCHAR2(100 BYTE) NOT NULL ,
"AUTHORITY_ID" VARCHAR2(100 BYTE) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of SYS_AUTHORITIES_RESOURCES
-- ----------------------------

-- ----------------------------
-- Table structure for SYS_MODULES
-- ----------------------------
DROP TABLE "PSOPUSER"."SYS_MODULES";
CREATE TABLE "PSOPUSER"."SYS_MODULES" (
"MODULE_ID" VARCHAR2(100 BYTE) NOT NULL ,
"MODULE_NAME" VARCHAR2(100 BYTE) NOT NULL ,
"MODULE_DESC" VARCHAR2(200 BYTE) NULL ,
"MODULE_TYPE" VARCHAR2(100 BYTE) NULL ,
"PARENT" VARCHAR2(100 BYTE) NULL ,
"MODULE_URL" VARCHAR2(100 BYTE) NULL ,
"I_LEVEL" NUMBER NULL ,
"LEAF" NUMBER NULL ,
"APPLICATION" VARCHAR2(100 BYTE) NULL ,
"CONTROLLER" VARCHAR2(100 BYTE) NULL ,
"ENABLE" NUMBER(1) NULL ,
"PRIORITY" NUMBER NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "PSOPUSER"."SYS_MODULES"."I_LEVEL" IS '1';

-- ----------------------------
-- Records of SYS_MODULES
-- ----------------------------

-- ----------------------------
-- Table structure for SYS_RESOURCES
-- ----------------------------
DROP TABLE "PSOPUSER"."SYS_RESOURCES";
CREATE TABLE "PSOPUSER"."SYS_RESOURCES" (
"RESOURCE_ID" VARCHAR2(100 BYTE) NOT NULL ,
"RESOURCE_TYPE" VARCHAR2(100 BYTE) NULL ,
"RESOURCE_NAME" VARCHAR2(100 BYTE) NULL ,
"RESOURCE_DESC" VARCHAR2(200 BYTE) NULL ,
"RESOURCE_PATH" VARCHAR2(200 BYTE) NULL ,
"PRIORITY" VARCHAR2(100 BYTE) NULL ,
"ENABLE" NUMBER NULL ,
"ISSYS" NUMBER NULL ,
"MODULE_ID" VARCHAR2(100 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "PSOPUSER"."SYS_RESOURCES"."RESOURCE_TYPE" IS 'URL,METHOD';

-- ----------------------------
-- Records of SYS_RESOURCES
-- ----------------------------

-- ----------------------------
-- Table structure for SYS_ROLES
-- ----------------------------
DROP TABLE "PSOPUSER"."SYS_ROLES";
CREATE TABLE "PSOPUSER"."SYS_ROLES" (
"ROLE_ID" VARCHAR2(100 BYTE) NOT NULL ,
"ROLE_NAME" VARCHAR2(100 BYTE) NULL ,
"ROLE_DESC" VARCHAR2(200 BYTE) NULL ,
"ENABLE" NUMBER NULL ,
"ISSYS" NUMBER NULL ,
"MODULE_ID" VARCHAR2(100 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of SYS_ROLES
-- ----------------------------
INSERT INTO "PSOPUSER"."SYS_ROLES" VALUES ('32dedf57-5051-4667-806a-e87dbbd3cea2', 'ADMIN', '管理员', '1', '1', null);

-- ----------------------------
-- Table structure for SYS_ROLES_AUTHORITIES
-- ----------------------------
DROP TABLE "PSOPUSER"."SYS_ROLES_AUTHORITIES";
CREATE TABLE "PSOPUSER"."SYS_ROLES_AUTHORITIES" (
"ID" VARCHAR2(100 BYTE) NOT NULL ,
"AUTHORITY_ID" VARCHAR2(100 BYTE) NOT NULL ,
"ROLE_ID" VARCHAR2(100 BYTE) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of SYS_ROLES_AUTHORITIES
-- ----------------------------
INSERT INTO "PSOPUSER"."SYS_ROLES_AUTHORITIES" VALUES ('af84fabb-1b67-4b4b-95b9-380b8ed15b29', '579a1c5c-ece6-4a2d-8675-0d53b091112b', '32dedf57-5051-4667-806a-e87dbbd3cea2');

-- ----------------------------
-- Table structure for SYS_ROLES_MODULES
-- ----------------------------
DROP TABLE "PSOPUSER"."SYS_ROLES_MODULES";
CREATE TABLE "PSOPUSER"."SYS_ROLES_MODULES" (
"ID" VARCHAR2(100 BYTE) NOT NULL ,
"MODULE_ID" VARCHAR2(100 BYTE) NOT NULL ,
"ROLE_ID" VARCHAR2(100 BYTE) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON TABLE "PSOPUSER"."SYS_ROLES_MODULES" IS '控制角色对模块的访问权，主要用于生成菜单';

-- ----------------------------
-- Records of SYS_ROLES_MODULES
-- ----------------------------

-- ----------------------------
-- Table structure for SYS_USERS
-- ----------------------------
DROP TABLE "PSOPUSER"."SYS_USERS";
CREATE TABLE "PSOPUSER"."SYS_USERS" (
"USER_ID" VARCHAR2(100 BYTE) NOT NULL ,
"USERNAME" VARCHAR2(100 BYTE) NOT NULL ,
"NAME" VARCHAR2(100 BYTE) NULL ,
"PASSWORD" VARCHAR2(100 BYTE) NOT NULL ,
"DT_CREATE" DATE DEFAULT SYSDATE  NULL ,
"LAST_LOGIN" DATE NULL ,
"DEADLINE" DATE NULL ,
"LOGIN_IP" VARCHAR2(100 BYTE) NULL ,
"V_QZJGID" VARCHAR2(100 BYTE) NULL ,
"V_QZJGMC" VARCHAR2(100 BYTE) NULL ,
"DEP_ID" VARCHAR2(100 BYTE) NULL ,
"DEP_NAME" VARCHAR2(100 BYTE) NULL ,
"ENABLED" NUMBER NULL ,
"ACCOUNT_NON_EXPIRED" NUMBER NULL ,
"ACCOUNT_NON_LOCKED" NUMBER NULL ,
"CREDENTIALS_NON_EXPIRED" NUMBER NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of SYS_USERS
-- ----------------------------
INSERT INTO "PSOPUSER"."SYS_USERS" VALUES ('1e7ddd97-d9db-4b4f-9f79-9b8b38d0f9e4', 'yangyi', '杨毅', '123456', TO_DATE('2018-02-04 18:53:50', 'YYYY-MM-DD HH24:MI:SS'), null, null, null, null, null, null, null, '1', '1', '1', '1');

-- ----------------------------
-- Table structure for SYS_USERS_ROLES
-- ----------------------------
DROP TABLE "PSOPUSER"."SYS_USERS_ROLES";
CREATE TABLE "PSOPUSER"."SYS_USERS_ROLES" (
"ID" VARCHAR2(100 BYTE) NOT NULL ,
"ROLE_ID" VARCHAR2(100 BYTE) NOT NULL ,
"USER_ID" VARCHAR2(100 BYTE) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of SYS_USERS_ROLES
-- ----------------------------
INSERT INTO "PSOPUSER"."SYS_USERS_ROLES" VALUES ('6072b1c0-1616-4c22-9ba0-d169ec58fd9c', '32dedf57-5051-4667-806a-e87dbbd3cea2', '1e7ddd97-d9db-4b4f-9f79-9b8b38d0f9e4');

-- ----------------------------
-- Table structure for WELCOME
-- ----------------------------
DROP TABLE "PSOPUSER"."WELCOME";
CREATE TABLE "PSOPUSER"."WELCOME" (
"ID" VARCHAR2(64 BYTE) NOT NULL ,
"USER_ID" VARCHAR2(64 BYTE) NULL ,
"USER_NAME" VARCHAR2(64 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of WELCOME
-- ----------------------------
INSERT INTO "PSOPUSER"."WELCOME" VALUES ('1', '001', 'yy');
INSERT INTO "PSOPUSER"."WELCOME" VALUES ('2', '002', 'yy');

-- ----------------------------
-- Indexes structure for table PERSISTENT_LOGINS
-- ----------------------------

-- ----------------------------
-- Checks structure for table PERSISTENT_LOGINS
-- ----------------------------
ALTER TABLE "PSOPUSER"."PERSISTENT_LOGINS" ADD CHECK ("SERIES" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table PERSISTENT_LOGINS
-- ----------------------------
ALTER TABLE "PSOPUSER"."PERSISTENT_LOGINS" ADD PRIMARY KEY ("SERIES");

-- ----------------------------
-- Indexes structure for table SYS_AUTHORITIES
-- ----------------------------

-- ----------------------------
-- Checks structure for table SYS_AUTHORITIES
-- ----------------------------
ALTER TABLE "PSOPUSER"."SYS_AUTHORITIES" ADD CHECK ("AUTHORITY_ID" IS NOT NULL);
ALTER TABLE "PSOPUSER"."SYS_AUTHORITIES" ADD CHECK ("AUTHORITY_NAME" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table SYS_AUTHORITIES
-- ----------------------------
ALTER TABLE "PSOPUSER"."SYS_AUTHORITIES" ADD PRIMARY KEY ("AUTHORITY_ID");

-- ----------------------------
-- Indexes structure for table SYS_AUTHORITIES_RESOURCES
-- ----------------------------

-- ----------------------------
-- Checks structure for table SYS_AUTHORITIES_RESOURCES
-- ----------------------------
ALTER TABLE "PSOPUSER"."SYS_AUTHORITIES_RESOURCES" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "PSOPUSER"."SYS_AUTHORITIES_RESOURCES" ADD CHECK ("RESOURCE_ID" IS NOT NULL);
ALTER TABLE "PSOPUSER"."SYS_AUTHORITIES_RESOURCES" ADD CHECK ("AUTHORITY_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table SYS_AUTHORITIES_RESOURCES
-- ----------------------------
ALTER TABLE "PSOPUSER"."SYS_AUTHORITIES_RESOURCES" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table SYS_MODULES
-- ----------------------------

-- ----------------------------
-- Checks structure for table SYS_MODULES
-- ----------------------------
ALTER TABLE "PSOPUSER"."SYS_MODULES" ADD CHECK ("MODULE_ID" IS NOT NULL);
ALTER TABLE "PSOPUSER"."SYS_MODULES" ADD CHECK ("MODULE_NAME" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table SYS_MODULES
-- ----------------------------
ALTER TABLE "PSOPUSER"."SYS_MODULES" ADD PRIMARY KEY ("MODULE_ID");

-- ----------------------------
-- Indexes structure for table SYS_RESOURCES
-- ----------------------------

-- ----------------------------
-- Checks structure for table SYS_RESOURCES
-- ----------------------------
ALTER TABLE "PSOPUSER"."SYS_RESOURCES" ADD CHECK ("RESOURCE_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table SYS_RESOURCES
-- ----------------------------
ALTER TABLE "PSOPUSER"."SYS_RESOURCES" ADD PRIMARY KEY ("RESOURCE_ID");

-- ----------------------------
-- Indexes structure for table SYS_ROLES
-- ----------------------------

-- ----------------------------
-- Checks structure for table SYS_ROLES
-- ----------------------------
ALTER TABLE "PSOPUSER"."SYS_ROLES" ADD CHECK ("ROLE_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table SYS_ROLES
-- ----------------------------
ALTER TABLE "PSOPUSER"."SYS_ROLES" ADD PRIMARY KEY ("ROLE_ID");

-- ----------------------------
-- Indexes structure for table SYS_ROLES_AUTHORITIES
-- ----------------------------

-- ----------------------------
-- Checks structure for table SYS_ROLES_AUTHORITIES
-- ----------------------------
ALTER TABLE "PSOPUSER"."SYS_ROLES_AUTHORITIES" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "PSOPUSER"."SYS_ROLES_AUTHORITIES" ADD CHECK ("AUTHORITY_ID" IS NOT NULL);
ALTER TABLE "PSOPUSER"."SYS_ROLES_AUTHORITIES" ADD CHECK ("ROLE_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table SYS_ROLES_AUTHORITIES
-- ----------------------------
ALTER TABLE "PSOPUSER"."SYS_ROLES_AUTHORITIES" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table SYS_ROLES_MODULES
-- ----------------------------

-- ----------------------------
-- Checks structure for table SYS_ROLES_MODULES
-- ----------------------------
ALTER TABLE "PSOPUSER"."SYS_ROLES_MODULES" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "PSOPUSER"."SYS_ROLES_MODULES" ADD CHECK ("MODULE_ID" IS NOT NULL);
ALTER TABLE "PSOPUSER"."SYS_ROLES_MODULES" ADD CHECK ("ROLE_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table SYS_ROLES_MODULES
-- ----------------------------
ALTER TABLE "PSOPUSER"."SYS_ROLES_MODULES" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table SYS_USERS
-- ----------------------------

-- ----------------------------
-- Checks structure for table SYS_USERS
-- ----------------------------
ALTER TABLE "PSOPUSER"."SYS_USERS" ADD CHECK ("USER_ID" IS NOT NULL);
ALTER TABLE "PSOPUSER"."SYS_USERS" ADD CHECK ("USERNAME" IS NOT NULL);
ALTER TABLE "PSOPUSER"."SYS_USERS" ADD CHECK ("PASSWORD" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table SYS_USERS
-- ----------------------------
ALTER TABLE "PSOPUSER"."SYS_USERS" ADD PRIMARY KEY ("USER_ID");

-- ----------------------------
-- Indexes structure for table SYS_USERS_ROLES
-- ----------------------------

-- ----------------------------
-- Checks structure for table SYS_USERS_ROLES
-- ----------------------------
ALTER TABLE "PSOPUSER"."SYS_USERS_ROLES" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "PSOPUSER"."SYS_USERS_ROLES" ADD CHECK ("ROLE_ID" IS NOT NULL);
ALTER TABLE "PSOPUSER"."SYS_USERS_ROLES" ADD CHECK ("USER_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table SYS_USERS_ROLES
-- ----------------------------
ALTER TABLE "PSOPUSER"."SYS_USERS_ROLES" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table WELCOME
-- ----------------------------

-- ----------------------------
-- Checks structure for table WELCOME
-- ----------------------------
ALTER TABLE "PSOPUSER"."WELCOME" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "PSOPUSER"."WELCOME" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table WELCOME
-- ----------------------------
ALTER TABLE "PSOPUSER"."WELCOME" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Foreign Key structure for table "PSOPUSER"."SYS_AUTHORITIES_RESOURCES"
-- ----------------------------
ALTER TABLE "PSOPUSER"."SYS_AUTHORITIES_RESOURCES" ADD FOREIGN KEY ("AUTHORITY_ID") REFERENCES "PSOPUSER"."SYS_AUTHORITIES" ("AUTHORITY_ID");
ALTER TABLE "PSOPUSER"."SYS_AUTHORITIES_RESOURCES" ADD FOREIGN KEY ("RESOURCE_ID") REFERENCES "PSOPUSER"."SYS_RESOURCES" ("RESOURCE_ID");

-- ----------------------------
-- Foreign Key structure for table "PSOPUSER"."SYS_RESOURCES"
-- ----------------------------
ALTER TABLE "PSOPUSER"."SYS_RESOURCES" ADD FOREIGN KEY ("MODULE_ID") REFERENCES "PSOPUSER"."SYS_MODULES" ("MODULE_ID");

-- ----------------------------
-- Foreign Key structure for table "PSOPUSER"."SYS_ROLES_AUTHORITIES"
-- ----------------------------
ALTER TABLE "PSOPUSER"."SYS_ROLES_AUTHORITIES" ADD FOREIGN KEY ("AUTHORITY_ID") REFERENCES "PSOPUSER"."SYS_AUTHORITIES" ("AUTHORITY_ID");
ALTER TABLE "PSOPUSER"."SYS_ROLES_AUTHORITIES" ADD FOREIGN KEY ("ROLE_ID") REFERENCES "PSOPUSER"."SYS_ROLES" ("ROLE_ID");

-- ----------------------------
-- Foreign Key structure for table "PSOPUSER"."SYS_ROLES_MODULES"
-- ----------------------------
ALTER TABLE "PSOPUSER"."SYS_ROLES_MODULES" ADD FOREIGN KEY ("MODULE_ID") REFERENCES "PSOPUSER"."SYS_MODULES" ("MODULE_ID");
ALTER TABLE "PSOPUSER"."SYS_ROLES_MODULES" ADD FOREIGN KEY ("ROLE_ID") REFERENCES "PSOPUSER"."SYS_ROLES" ("ROLE_ID");

-- ----------------------------
-- Foreign Key structure for table "PSOPUSER"."SYS_USERS_ROLES"
-- ----------------------------
ALTER TABLE "PSOPUSER"."SYS_USERS_ROLES" ADD FOREIGN KEY ("ROLE_ID") REFERENCES "PSOPUSER"."SYS_ROLES" ("ROLE_ID");
ALTER TABLE "PSOPUSER"."SYS_USERS_ROLES" ADD FOREIGN KEY ("USER_ID") REFERENCES "PSOPUSER"."SYS_USERS" ("USER_ID");
