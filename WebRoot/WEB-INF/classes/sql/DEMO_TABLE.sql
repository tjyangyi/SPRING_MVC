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

Date: 2018-02-19 11:40:30
*/


-- ----------------------------
-- Table structure for DEMO_TABLE
-- ----------------------------
DROP TABLE "DEMO_TABLE";
CREATE TABLE "DEMO_TABLE" (
"ID" VARCHAR2(100 BYTE) NOT NULL ,
"NAME" VARCHAR2(255 BYTE) NOT NULL ,
"COUNT_NUM" NUMBER NOT NULL ,
"CREATE_TIME" DATE NOT NULL ,
"UPDATE_TIME" DATE NOT NULL ,
"CUSTOM_TIME" DATE NOT NULL
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Indexes structure for table DEMO_TABLE
-- ----------------------------

-- ----------------------------
-- Checks structure for table DEMO_TABLE
-- ----------------------------
ALTER TABLE "DEMO_TABLE" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "DEMO_TABLE" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "DEMO_TABLE" ADD CHECK ("NAME" IS NOT NULL);
ALTER TABLE "DEMO_TABLE" ADD CHECK ("COUNT_NUM" IS NOT NULL);
ALTER TABLE "DEMO_TABLE" ADD CHECK ("CREATE_TIME" IS NOT NULL);
ALTER TABLE "DEMO_TABLE" ADD CHECK ("UPDATE_TIME" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table DEMO_TABLE
-- ----------------------------
ALTER TABLE "DEMO_TABLE" ADD PRIMARY KEY ("ID");
