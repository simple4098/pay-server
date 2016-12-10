/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.35
Source Server Version : 50505
Source Host           : 192.168.0.35:3306
Source Database       : pay_server

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2016-12-10 15:06:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bank_info
-- ----------------------------
DROP TABLE IF EXISTS `bank_info`;
CREATE TABLE `bank_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `version` int(11) NOT NULL,
  `bank_code` varchar(255) DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bank_code` (`bank_code`,`bank_name`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fy_bank_city_code
-- ----------------------------
DROP TABLE IF EXISTS `fy_bank_city_code`;
CREATE TABLE `fy_bank_city_code` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `city_id` varchar(5) DEFAULT NULL,
  `city_name` varchar(20) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `city_id` (`city_id`,`city_name`)
) ENGINE=InnoDB AUTO_INCREMENT=685 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pay_account
-- ----------------------------
DROP TABLE IF EXISTS `pay_account`;
CREATE TABLE `pay_account` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(16) NOT NULL DEFAULT '' COMMENT '用户code',
  `small_pay` tinyint(4) NOT NULL DEFAULT '0' COMMENT '小额支付 0 未开通 1 开通',
  `pay_password` varchar(64) DEFAULT NULL COMMENT '支付密码',
  `random_code` varchar(6) DEFAULT NULL COMMENT '随机数',
  `system_pay_seq` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否默认系统支付银行卡顺序 1 默认 0 不默认',
  `small_pay_money` varchar(20) NOT NULL DEFAULT 'MONEY_200' COMMENT '小额支付金额 枚举 MONEY_200  MONEY_500 MONEY_800 MONEY_1000 MONEY_2000',
  `real_name_auth` tinyint(4) DEFAULT '0' COMMENT '实名真正 0未认证  1 认证',
  `deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `version` int(11) DEFAULT NULL,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `identification_number` varchar(255) DEFAULT NULL,
  `identification_type` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_code` (`user_code`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pay_bank
-- ----------------------------
DROP TABLE IF EXISTS `pay_bank`;
CREATE TABLE `pay_bank` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(16) NOT NULL DEFAULT '' COMMENT '用户code',
  `pay_account_id` varchar(64) NOT NULL DEFAULT '' COMMENT 'pay_account id',
  `cardholder` varchar(48) NOT NULL DEFAULT '' COMMENT '持卡人',
  `bank_card` varchar(32) NOT NULL DEFAULT '' COMMENT '银卡号',
  `bank_name` varchar(10) NOT NULL DEFAULT '' COMMENT '银行卡名称',
  `quick_payment_amount` decimal(8,2) DEFAULT NULL COMMENT '快捷支付金额',
  `bank_id` varchar(10) DEFAULT '' COMMENT '第三方提供总行代码',
  `tx_code` varchar(32) DEFAULT '' COMMENT '交易号',
  `tx_SN_binding` varchar(32) DEFAULT '' COMMENT '绑定流水号（后续绑定支付和绑定代收需要使用）',
  `tx_SN_un_binding` varchar(32) DEFAULT '' COMMENT '绑定银行卡编码',
  `phone_number` varchar(16) DEFAULT '' COMMENT '手机号码',
  `card_type` varchar(10) DEFAULT '' COMMENT '卡类型：10=个人借记 20=个人贷记',
  `valid_date` int(4) DEFAULT NULL COMMENT '信用卡有效期，格式 YYMM',
  `cvn2` int(3) DEFAULT NULL COMMENT '信用卡背面的末 3 位数字',
  `bang_status` tinyint(4) DEFAULT NULL COMMENT '绑定状态 10=绑定处理中 20=绑定失败 30=绑定成功',
  `bank_tx_time` datetime DEFAULT NULL COMMENT '银行处理时间',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `version` int(11) DEFAULT NULL,
  `sort` int(11) unsigned zerofill DEFAULT NULL COMMENT '顺序从小到大',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `settlement_flag` varchar(255) DEFAULT NULL COMMENT '结算标识',
  `city_no` varchar(255) DEFAULT NULL COMMENT '银行卡所在城市code',
  `ins_cd` varchar(255) DEFAULT NULL COMMENT '银行机构号 ',
  PRIMARY KEY (`id`),
  KEY `pay_account_id` (`pay_account_id`,`bank_card`,`tx_SN_binding`,`user_code`,`tx_code`,`settlement_flag`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pay_order_account
-- ----------------------------
DROP TABLE IF EXISTS `pay_order_account`;
CREATE TABLE `pay_order_account` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `pay_account_id` varchar(64) DEFAULT NULL COMMENT '支付账户id',
  `order_no` varchar(32) NOT NULL COMMENT '支付订单号',
  `pay_no` varchar(32) DEFAULT NULL COMMENT '支付号（支付系统生成）',
  `pay_order` varchar(64) DEFAULT NULL COMMENT '第三方支付返回的订单号',
  `other_user_code` varchar(16) DEFAULT NULL COMMENT '对方user_code',
  `pay_type` varchar(20) DEFAULT NULL COMMENT '支付类型 ACCOUNT(余额支付) QUICK_PAYMENT（快捷支付） DIAMOND(钻石支付) ',
  `tx_code` varchar(32) DEFAULT '' COMMENT '支付通道标识 微信openid  快捷支付银行卡编码 ',
  `cardholder` varchar(15) DEFAULT '' COMMENT '持卡人',
  `bank_card` varchar(20) DEFAULT NULL COMMENT '银行卡号',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '支付金额',
  `pay_status` tinyint(4) DEFAULT NULL COMMENT '10=处理中 20=支付成功 30=支付失败',
  `deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `version` int(11) DEFAULT NULL,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `bank_tx_time` datetime DEFAULT NULL,
  `msg` varchar(255) DEFAULT NULL,
  `pay_bank_id` int(11) DEFAULT NULL COMMENT 'pay_bank pk，绑定的银行卡id(快捷支付才有值）',
  `bank_code` varchar(50) DEFAULT NULL COMMENT '支付银行卡编码（暂时微信支付有值）',
  PRIMARY KEY (`id`),
  KEY `pay_account_id` (`pay_account_id`,`order_no`,`pay_order`,`pay_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pay_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `pay_order_detail`;
CREATE TABLE `pay_order_detail` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `pay_order_account_id` bigint(16) NOT NULL COMMENT 'pay_order_account id',
  `pay_account_id` bigint(16) NOT NULL COMMENT 'accountId',
  `order_no` varchar(32) NOT NULL COMMENT '支付订单号',
  `pay_order` varchar(64) DEFAULT NULL COMMENT '第三方支付返回的订单号',
  `other_user_code` varchar(16) DEFAULT NULL COMMENT '对方user_code',
  `pay_type` varchar(20) DEFAULT NULL COMMENT '支付类型 ACCOUNT(余额支付) QUICK_PAYMENT（快捷支付） DIAMOND(钻石支付) ',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '支付金额',
  `pay_status` tinyint(4) DEFAULT NULL COMMENT '10=处理中 20=支付成功 30=支付失败',
  `msg` varchar(225) DEFAULT NULL COMMENT '支付信息',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `version` int(11) DEFAULT NULL,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `pay_no` varchar(32) DEFAULT NULL,
  `cardholder` varchar(255) DEFAULT NULL,
  `pay_bank_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pay_order_account_id` (`pay_order_account_id`,`pay_account_id`,`order_no`,`pay_order`,`pay_no`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pay_problem
-- ----------------------------
DROP TABLE IF EXISTS `pay_problem`;
CREATE TABLE `pay_problem` (
  `id` bigint(16) NOT NULL,
  `problem_name` varchar(30) NOT NULL COMMENT '问题名称',
  `deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `version` int(11) DEFAULT NULL,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `deleted` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for security_problem
-- ----------------------------
DROP TABLE IF EXISTS `security_problem`;
CREATE TABLE `security_problem` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `pay_account_id` bigint(64) NOT NULL,
  `problem_id` bigint(16) DEFAULT NULL,
  `answer` varchar(50) DEFAULT NULL COMMENT '答案',
  `deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `version` int(11) DEFAULT NULL,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `pay_account_id` (`pay_account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
