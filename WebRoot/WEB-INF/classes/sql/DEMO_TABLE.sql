/*
Navicat Oracle Data Transfer
Oracle Client Version : 10.2.0.5.0

Source Server         : 10.1.111.135SPRINGMVC
Source Server Version : 110100
Source Host           : 10.1.111.135:1521
Source Schema         : SPRINGMVC

Target Server Type    : ORACLE
Target Server Version : 110100
File Encoding         : 65001

Date: 2018-02-11 13:40:19
*/


-- ----------------------------
-- Table structure for DEMO_TABLE
-- ----------------------------
DROP TABLE "DEMO_TABLE";
CREATE TABLE "DEMO_TABLE" (
"ID" VARCHAR2(100 BYTE) NOT NULL ,
"NAME" VARCHAR2(255 BYTE) NULL ,
"COUNT" NUMBER NULL ,
"CREATE_TIME" DATE NULL 
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

-- ----------------------------
-- Primary Key structure for table DEMO_TABLE
-- ----------------------------
ALTER TABLE "DEMO_TABLE" ADD PRIMARY KEY ("ID");
