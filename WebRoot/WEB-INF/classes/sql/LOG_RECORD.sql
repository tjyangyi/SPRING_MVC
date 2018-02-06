/*
Navicat Oracle Data Transfer
Oracle Client Version : 11.2.0.1.0

Source Server         : Y500_PSOPUSER
Source Server Version : 110200
Source Host           : 127.0.0.1:1521
Source Schema         : PSOPUSER

Target Server Type    : ORACLE
Target Server Version : 110200
File Encoding         : 65001

Date: 2018-02-06 17:20:29
*/


-- ----------------------------
-- Table structure for LOG_RECORD
-- ----------------------------
DROP TABLE "PSOPUSER"."LOG_RECORD";
CREATE TABLE "PSOPUSER"."LOG_RECORD" (
"LOG_ID" VARCHAR2(100 BYTE) NOT NULL ,
"OPERATION_USER_ID" VARCHAR2(100 BYTE) NULL ,
"OPERATION_UERANAME" VARCHAR2(100 BYTE) NULL ,
"OPERATION_TYPE" VARCHAR2(255 BYTE) NULL ,
"OPERATION_DESC" VARCHAR2(255 BYTE) NULL ,
"TARGET_CLASS" VARCHAR2(255 BYTE) NULL ,
"TARGET_METHOD" VARCHAR2(255 BYTE) NULL ,
"TARGET_METHOD_PARAMS" VARCHAR2(2000 BYTE) NULL ,
"TARGET_METHOD_RESULT" VARCHAR2(2000 BYTE) NULL ,
"OPERATION_START_TIME" DATE NULL ,
"OPERATION_END_TIME" DATE NULL ,
"OPERATION_ELAPSED_TIME_MILLIS" NUMBER(16) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of LOG_RECORD
-- ----------------------------

-- ----------------------------
-- Indexes structure for table LOG_RECORD
-- ----------------------------

-- ----------------------------
-- Checks structure for table LOG_RECORD
-- ----------------------------
ALTER TABLE "PSOPUSER"."LOG_RECORD" ADD CHECK ("LOG_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table LOG_RECORD
-- ----------------------------
ALTER TABLE "PSOPUSER"."LOG_RECORD" ADD PRIMARY KEY ("LOG_ID");
