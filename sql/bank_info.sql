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



INSERT INTO `bank_info` VALUES ('2', '2016-12-08 19:24:21', '2016-12-08 19:24:21', '0', '0102', '中国工商银行');
INSERT INTO `bank_info` VALUES ('3', '2016-12-08 19:24:08', '2016-12-08 19:24:08', '0', '0103', '中国农业银行');
INSERT INTO `bank_info` VALUES ('4', '2016-12-08 19:24:06', '2016-12-08 19:24:06', '0', '0104', '中国银行');
INSERT INTO `bank_info` VALUES ('5', '2016-12-08 19:24:23', '2016-12-08 19:24:23', '0', '0105', '中国建设银行');
INSERT INTO `bank_info` VALUES ('6', '2016-12-08 19:24:26', '2016-12-08 19:24:26', '0', '0301', '交通银行');
INSERT INTO `bank_info` VALUES ('7', '2016-12-08 19:24:27', '2016-12-08 19:24:27', '0', '0308', '招商银行');
INSERT INTO `bank_info` VALUES ('8', '2016-12-08 19:24:29', '2016-12-08 19:24:29', '0', '0309', '兴业银行');
INSERT INTO `bank_info` VALUES ('9', '2016-12-08 19:25:02', '2016-12-08 19:25:02', '0', '0305', '中国民生银行');
INSERT INTO `bank_info` VALUES ('10', '2016-12-08 19:25:04', '2016-12-08 19:25:04', '0', '0306', '广东发展银行');
INSERT INTO `bank_info` VALUES ('11', '2016-12-08 19:25:05', '2016-12-08 19:25:05', '0', '0307', '平安银行股份有限公司');
INSERT INTO `bank_info` VALUES ('12', '2016-12-08 19:25:07', '2016-12-08 19:25:07', '0', '0310', '上海浦东发展银行');
INSERT INTO `bank_info` VALUES ('13', '2016-12-08 19:25:08', '2016-12-08 19:25:08', '0', '0304', '华夏银行');
INSERT INTO `bank_info` VALUES ('14', '2016-12-08 19:25:10', '2016-12-08 19:25:10', '0', '0313', '其他城市商业银行');
INSERT INTO `bank_info` VALUES ('15', '2016-12-08 19:25:12', '2016-12-08 19:25:12', '0', '1401', '邯郸市城市信用社');
INSERT INTO `bank_info` VALUES ('16', '2016-12-08 19:25:13', '2016-12-08 19:25:13', '0', '0314', '其他农村商业银行');
INSERT INTO `bank_info` VALUES ('17', '2016-12-08 19:25:15', '2016-12-08 19:25:15', '0', '0315', '恒丰银行');
INSERT INTO `bank_info` VALUES ('18', '2016-12-08 19:25:16', '2016-12-08 19:25:16', '0', '0403', '中国邮政储蓄银行股份有限公司');
INSERT INTO `bank_info` VALUES ('19', '2016-12-08 19:25:17', '2016-12-08 19:25:17', '0', '0303', '中国光大银行');
INSERT INTO `bank_info` VALUES ('20', '2016-12-08 19:25:19', '2016-12-08 19:25:19', '0', '0302', '中信银行');
INSERT INTO `bank_info` VALUES ('21', '2016-12-08 19:24:34', '2016-12-08 19:24:34', '0', '0316', '浙商银行股份有限公司');
INSERT INTO `bank_info` VALUES ('22', '2016-12-08 19:24:35', '2016-12-08 19:24:35', '0', '0318', '渤海银行股份有限公司');
INSERT INTO `bank_info` VALUES ('23', '2016-12-08 19:24:37', '2016-12-08 19:24:37', '0', '0423', '杭州市商业银行');
INSERT INTO `bank_info` VALUES ('24', '2016-12-08 19:24:38', '2016-12-08 19:24:38', '0', '0412', '温州市商业银行');
INSERT INTO `bank_info` VALUES ('25', '2016-12-08 19:24:41', '2016-12-08 19:24:41', '0', '0424', '南京市商业银行');
INSERT INTO `bank_info` VALUES ('26', '2016-12-08 19:24:43', '2016-12-08 19:24:43', '0', '0461', '长沙市商业银行');
INSERT INTO `bank_info` VALUES ('27', '2016-12-08 19:24:44', '2016-12-08 19:24:44', '0', '0409', '济南市商业银行');
INSERT INTO `bank_info` VALUES ('28', '2016-12-08 19:24:46', '2016-12-08 19:24:46', '0', '0422', '石家庄市商业银行');
INSERT INTO `bank_info` VALUES ('29', '2016-12-08 19:24:47', '2016-12-08 19:24:47', '0', '0458', '西宁市商业银行');
INSERT INTO `bank_info` VALUES ('30', '2016-12-08 19:24:48', '2016-12-08 19:24:48', '0', '0404', '烟台市商业银行');
INSERT INTO `bank_info` VALUES ('31', '2016-12-08 19:24:49', '2016-12-08 19:24:49', '0', '0462', '潍坊市商业银行');
INSERT INTO `bank_info` VALUES ('32', '2016-12-08 19:24:51', '2016-12-08 19:24:51', '0', '0515', '德州市商业银行');
INSERT INTO `bank_info` VALUES ('33', '2016-12-08 19:24:52', '2016-12-08 19:24:52', '0', '0431', '临沂市商业银行');
INSERT INTO `bank_info` VALUES ('34', '2016-12-08 19:24:53', '2016-12-08 19:24:53', '0', '0481', '威海商业银行');
INSERT INTO `bank_info` VALUES ('35', '2016-12-08 19:24:54', '2016-12-08 19:24:54', '0', '0497', '莱芜市商业银行');
INSERT INTO `bank_info` VALUES ('36', '2016-12-08 19:24:55', '2016-12-08 19:24:55', '0', '0450', '青岛市商业银行');
INSERT INTO `bank_info` VALUES ('37', '2016-12-08 19:25:00', '2016-12-08 19:25:00', '0', '0319', '徽商银行');
INSERT INTO `bank_info` VALUES ('38', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0', '0322', ' 上海农村商业银行');
INSERT INTO `bank_info` VALUES ('39', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0', '0402', ' 其他农村信用合作社');


INSERT INTO `fy_bank_city_code` VALUES ('343', '1000', '北京市', '110000', '北京', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('344', '1100', '天津市', '120000', '天津', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('345', '1340', '保定市', '130000', '河北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('346', '1430', '沧州市', '130000', '河北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('347', '1410', '承德市', '130000', '河北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('348', '1270', '邯郸市', '130000', '河北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('349', '1480', '衡水市', '130000', '河北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('350', '1460', '廊坊市', '130000', '河北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('351', '1260', '秦皇岛市', '130000', '河北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('352', '1210', '石家庄市', '130000', '河北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('353', '1240', '唐山市', '130000', '河北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('354', '1310', '邢台市', '130000', '河北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('355', '1380', '张家口市', '130000', '河北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('356', '1660', '长治市', '140000', '山西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('357', '1620', '大同市', '140000', '山西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('358', '1680', '晋城市', '140000', '山西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('359', '1750', '晋中市', '140000', '山西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('360', '1770', '临汾市', '140000', '山西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('361', '1730', '吕梁市', '140000', '山西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('362', '1690', '朔州市', '140000', '山西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('363', '1610', '太原市', '140000', '山西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('364', '1710', '忻州市', '140000', '山西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('365', '1630', '阳泉市', '140000', '山西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('366', '1810', '运城市', '140000', '山西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('367', '2080', '阿拉善盟', '150000', '内蒙古自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('368', '2070', '巴彦淖尔市', '150000', '内蒙古自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('369', '1920', '包头市', '150000', '内蒙古自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('370', '1940', '赤峰市', '150000', '内蒙古自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('371', '2050', '鄂尔多斯市', '150000', '内蒙古自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('372', '1910', '呼和浩特市', '150000', '内蒙古自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('373', '1960', '呼伦贝尔市', '150000', '内蒙古自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('374', '1990', '通辽市', '150000', '内蒙古自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('375', '1930', '乌海市', '150000', '内蒙古自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('376', '2030', '乌兰察布市', '150000', '内蒙古自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('377', '2010', '锡林郭勒盟', '150000', '内蒙古自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('378', '1980', '兴安盟', '150000', '内蒙古自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('379', '2230', '鞍山市', '210000', '辽宁省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('380', '2250', '本溪市', '210000', '辽宁省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('381', '2340', '朝阳市', '210000', '辽宁省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('382', '2220', '大连市', '210000', '辽宁省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('383', '2260', '丹东市', '210000', '辽宁省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('384', '2240', '抚顺市', '210000', '辽宁省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('385', '2290', '阜新市', '210000', '辽宁省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('386', '2276', '葫芦岛市', '210000', '辽宁省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('387', '2270', '锦州市', '210000', '辽宁省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('388', '2310', '辽阳市', '210000', '辽宁省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('389', '2320', '盘锦市', '210000', '辽宁省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('390', '2210', '沈阳市', '210000', '辽宁省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('391', '2330', '铁岭市', '210000', '辽宁省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('392', '2280', '营口市', '210000', '辽宁省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('393', '2470', '白城市', '220000', '吉林省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('394', '2460', '白山市', '220000', '吉林省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('395', '2410', '长春市', '220000', '吉林省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('396', '2420', '吉林市', '220000', '吉林省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('397', '2440', '辽源市', '220000', '吉林省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('398', '2430', '四平市', '220000', '吉林省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('399', '2520', '松原市', '220000', '吉林省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('400', '2450', '通化市', '220000', '吉林省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('401', '2490', '延边朝鲜族自治州', '220000', '吉林省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('402', '2650', '大庆市', '230000', '黑龙江省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('403', '2790', '大兴安岭地区', '230000', '黑龙江省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('404', '2610', '哈尔滨市', '230000', '黑龙江省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('405', '2670', '鹤岗市', '230000', '黑龙江省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('406', '2780', '黑河市', '230000', '黑龙江省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('407', '2660', '鸡西市', '230000', '黑龙江省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('408', '2690', '佳木斯市', '230000', '黑龙江省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('409', '2720', '牡丹江市', '230000', '黑龙江省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('410', '2740', '七台河市', '230000', '黑龙江省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('411', '2640', '齐齐哈尔市', '230000', '黑龙江省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('412', '2680', '双鸭山市', '230000', '黑龙江省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('413', '2760', '绥化市', '230000', '黑龙江省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('414', '2710', '伊春市', '230000', '黑龙江省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('415', '2900', '上海市', '310000', '上海', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('416', '3040', '常州市', '320000', '江苏省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('417', '3080', '淮安市', '320000', '江苏省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('418', '3070', '连云港市', '320000', '江苏省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('419', '3010', '南京市', '320000', '江苏省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('420', '3060', '南通市', '320000', '江苏省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('421', '3050', '苏州市', '320000', '江苏省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('422', '3090', '宿迁市', '320000', '江苏省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('423', '3128', '泰州市', '320000', '江苏省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('424', '3020', '无锡市', '320000', '江苏省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('425', '3030', '徐州市', '320000', '江苏省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('426', '3110', '盐城市', '320000', '江苏省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('427', '3120', '扬州市', '320000', '江苏省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('428', '3140', '镇江市', '320000', '江苏省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('429', '3310', '杭州市', '330000', '浙江省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('430', '3350', '嘉兴市', '330000', '浙江省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('431', '3360', '湖州市', '330000', '浙江省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('432', '3380', '金华市', '330000', '浙江省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('433', '3430', '丽水市', '330000', '浙江省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('434', '3320', '宁波市', '330000', '浙江省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('435', '3370', '绍兴市', '330000', '浙江省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('436', '3450', '台州市', '330000', '浙江省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('437', '3330', '温州市', '330000', '浙江省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('438', '3420', '舟山市', '330000', '浙江省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('439', '3410', '衢州市', '330000', '浙江省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('440', '3680', '安庆市', '340000', '安徽省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('441', '3630', '蚌埠市', '340000', '安徽省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('442', '3781', '巢湖市', '340000', '安徽省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('443', '3790', '池州市', '340000', '安徽省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('444', '3750', '滁州市', '340000', '安徽省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('445', '3720', '阜阳市', '340000', '安徽省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('446', '3610', '合肥市', '340000', '安徽省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('447', '3660', '淮北市', '340000', '安徽省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('448', '3640', '淮南市', '340000', '安徽省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('449', '3710', '黄山市', '340000', '安徽省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('450', '3760', '六安市', '340000', '安徽省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('451', '3650', '马鞍山市', '340000', '安徽省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('452', '3740', '宿州市', '340000', '安徽省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('453', '3670', '铜陵市', '340000', '安徽省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('454', '3620', '芜湖市', '340000', '安徽省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('455', '3771', '宣城市', '340000', '安徽省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('456', '3722', '亳州市', '340000', '安徽省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('457', '3910', '福州市', '350000', '福建省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('458', '4050', '龙岩市', '350000', '福建省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('459', '4010', '南平市', '350000', '福建省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('460', '4030', '宁德市', '350000', '福建省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('461', '3940', '莆田市', '350000', '福建省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('462', '3970', '泉州市', '350000', '福建省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('463', '3950', '三明市', '350000', '福建省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('464', '3930', '厦门市', '350000', '福建省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('465', '3990', '漳州市', '350000', '福建省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('466', '4370', '抚州市', '360000', '江西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('467', '4280', '赣州市', '360000', '江西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('468', '4350', '吉安市', '360000', '江西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('469', '4220', '景德镇市', '360000', '江西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('470', '4240', '九江市', '360000', '江西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('471', '4210', '南昌市', '360000', '江西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('472', '4230', '萍乡市', '360000', '江西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('473', '4330', '上饶市', '360000', '江西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('474', '4260', '新余市', '360000', '江西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('475', '4310', '宜春市', '360000', '江西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('476', '4270', '鹰潭市', '360000', '江西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('477', '4660', '滨州市', '370000', '山东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('478', '4680', '德州市', '370000', '山东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('479', '4550', '东营市', '370000', '山东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('480', '4750', '菏泽市', '370000', '山东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('481', '4510', '济南市', '370000', '山东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('482', '4610', '济宁市', '370000', '山东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('483', '4634', '莱芜市', '370000', '山东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('484', '4710', '聊城市', '370000', '山东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('485', '4730', '临沂市', '370000', '山东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('486', '4520', '青岛市', '370000', '山东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('487', '4732', '日照市', '370000', '山东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('488', '4630', '泰安市', '370000', '山东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('489', '4650', '威海市', '370000', '山东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('490', '4580', '潍坊市', '370000', '山东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('491', '4560', '烟台市', '370000', '山东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('492', '4540', '枣庄市', '370000', '山东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('493', '4530', '淄博市', '370000', '山东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('494', '4960', '安阳市', '410000', '河南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('495', '4970', '鹤壁市', '410000', '河南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('496', '5010', '焦作市', '410000', '河南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('497', '4920', '开封市', '410000', '河南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('498', '4930', '洛阳市', '410000', '河南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('499', '5130', '南阳市', '410000', '河南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('500', '4950', '平顶山市', '410000', '河南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('501', '5050', '三门峡市', '410000', '河南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('502', '5060', '商丘市', '410000', '河南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('503', '4980', '新乡市', '410000', '河南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('504', '5150', '信阳市', '410000', '河南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('505', '5030', '许昌市', '410000', '河南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('506', '4910', '郑州市', '410000', '河南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('507', '5080', '周口市', '410000', '河南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('508', '5110', '驻马店市', '410000', '河南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('509', '5040', '漯河市', '410000', '河南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('510', '5020', '濮阳市', '410000', '河南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('511', '5310', '鄂州市', '420000', '湖北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('512', '5410', '恩施土家族苗族自治州', '420000', '湖北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('513', '5330', '黄冈市', '420000', '湖北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('514', '5220', '黄石市', '420000', '湖北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('515', '5320', '荆门市', '420000', '湖北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('516', '5370', '荆州市', '420000', '湖北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('517', '5375', '潜江市', '420000', '湖北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('518', '5311', '神农架林区', '420000', '湖北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('519', '5230', '十堰市', '420000', '湖北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('520', '5286', '随州市', '420000', '湖北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('521', '5374', '天门市', '420000', '湖北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('522', '5210', '武汉市', '420000', '湖北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('523', '5371', '仙桃市', '420000', '湖北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('524', '5360', '咸宁市', '420000', '湖北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('525', '5280', '襄阳市', '420000', '湖北省', '2016-12-10 16:11:00', '2016-12-10 16:11:00', '0');
INSERT INTO `fy_bank_city_code` VALUES ('526', '5350', '孝感市', '420000', '湖北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('527', '5260', '宜昌市', '420000', '湖北省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('528', '5580', '常德市', '430000', '湖南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('529', '5510', '长沙市', '430000', '湖南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('530', '5630', '郴州市', '430000', '湖南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('531', '5540', '衡阳市', '430000', '湖南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('532', '5670', '怀化市', '430000', '湖南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('533', '5620', '娄底市', '430000', '湖南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('534', '5550', '邵阳市', '430000', '湖南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('535', '5530', '湘潭市', '430000', '湖南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('536', '5690', '湘西土家族苗族自治州', '430000', '湖南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('537', '5610', '益阳市', '430000', '湖南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('538', '5650', '永州市', '430000', '湖南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('539', '5570', '岳阳市', '430000', '湖南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('540', '5590', '张家界市', '430000', '湖南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('541', '5520', '株洲市', '430000', '湖南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('542', '5869', '潮州市', '440000', '广东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('543', '6020', '东莞市', '440000', '广东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('544', '5880', '佛山市', '440000', '广东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('545', '5810', '广州市', '440000', '广东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('546', '5980', '河源市', '440000', '广东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('547', '5950', '惠州市', '440000', '广东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('548', '5890', '江门市', '440000', '广东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('549', '5865', '揭阳市', '440000', '广东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('550', '5920', '茂名市', '440000', '广东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('551', '5960', '梅州市', '440000', '广东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('552', '6010', '清远市', '440000', '广东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('553', '5860', '汕头市', '440000', '广东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('554', '5970', '汕尾市', '440000', '广东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('555', '5820', '韶关市', '440000', '广东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('556', '5840', '深圳市', '440000', '广东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('557', '5990', '阳江市', '440000', '广东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('558', '5937', '云浮市', '440000', '广东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('559', '5910', '湛江市', '440000', '广东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('560', '5930', '肇庆市', '440000', '广东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('561', '6030', '中山市', '440000', '广东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('562', '5850', '珠海市', '440000', '广东省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('563', '6261', '百色市', '450000', '广西壮族自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('564', '6230', '北海市', '450000', '广西壮族自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('565', '6128', '崇左市', '450000', '广西壮族自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('566', '6330', '防城港市', '450000', '广西壮族自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('567', '6170', '桂林市', '450000', '广西壮族自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('568', '6242', '贵港市', '450000', '广西壮族自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('569', '6281', '河池市', '450000', '广西壮族自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('570', '6225', '贺州市', '450000', '广西壮族自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('571', '6155', '来宾市', '450000', '广西壮族自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('572', '6140', '柳州市', '450000', '广西壮族自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('573', '6110', '南宁市', '450000', '广西壮族自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('574', '6311', '钦州市', '450000', '广西壮族自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('575', '6210', '梧州市', '450000', '广西壮族自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('576', '6240', '玉林市', '450000', '广西壮族自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('577', '6410', '海口市', '460000', '海南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('578', '6420', '三亚市', '460000', '海南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('579', '6530', '重庆市', '500000', '重庆', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('580', '6790', '阿坝藏族羌族自治州', '510000', '四川省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('581', '6758', '巴中市', '510000', '四川省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('582', '6510', '成都市', '510000', '四川省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('583', '6750', '达州市', '510000', '四川省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('584', '6580', '德阳市', '510000', '四川省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('585', '6810', '甘孜藏族自治州', '510000', '四川省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('586', '6737', '广安市', '510000', '四川省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('587', '6610', '广元市', '510000', '四川省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('588', '6650', '乐山市', '510000', '四川省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('589', '6840', '凉山彝族自治州', '510000', '四川省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('590', '6652', '眉山市', '510000', '四川省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('591', '6590', '绵阳市', '510000', '四川省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('592', '6730', '南充市', '510000', '四川省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('593', '6630', '内江市', '510000', '四川省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('594', '6560', '攀枝花市', '510000', '四川省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('595', '6620', '遂宁市', '510000', '四川省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('596', '6770', '雅安市', '510000', '四川省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('597', '6710', '宜宾市', '510000', '四川省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('598', '6550', '自贡市', '510000', '四川省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('599', '6570', '泸州市', '510000', '四川省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('600', '7110', '安顺市', '520000', '贵州省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('601', '7090', '毕节地区', '520000', '贵州省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('602', '7010', '贵阳市', '520000', '贵州省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('603', '7020', '六盘水市', '520000', '贵州省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('604', '7130', '黔东南苗族侗族自治州', '520000', '贵州省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('605', '7150', '黔南布依族苗族自治州', '520000', '贵州省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('606', '7070', '黔西南布依族苗族自治州', '520000', '贵州省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('607', '7050', '铜仁地区', '520000', '贵州省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('608', '7030', '遵义市', '520000', '贵州省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('609', '7530', '保山市', '530000', '云南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('610', '7380', '楚雄彝族自治州', '530000', '云南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('611', '7510', '大理白族自治州', '530000', '云南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('612', '7540', '德宏傣族景颇族自治州', '530000', '云南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('613', '7570', '迪庆藏族自治州', '530000', '云南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('614', '7430', '红河哈尼族彝族自治州', '530000', '云南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('615', '7310', '昆明市', '530000', '云南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('616', '7550', '丽江市', '530000', '云南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('617', '7580', '临沧市', '530000', '云南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('618', '7560', '怒江傈僳族自治州', '530000', '云南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('619', '7470', '普洱市', '530000', '云南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('620', '7360', '曲靖市', '530000', '云南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('621', '7450', '文山壮族苗族自治州', '530000', '云南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('622', '7490', '西双版纳傣族自治州', '530000', '云南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('623', '7410', '玉溪市', '530000', '云南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('624', '7340', '昭通市', '530000', '云南省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('625', '7810', '阿里地区', '540000', '西藏自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('626', '7720', '昌都地区', '540000', '西藏自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('627', '7700', '拉萨市', '540000', '西藏自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('628', '7830', '林芝地区', '540000', '西藏自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('629', '7790', '那曲地区', '540000', '西藏自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('630', '7760', '日喀则地区', '540000', '西藏自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('631', '7740', '山南地区', '540000', '西藏自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('632', '8010', '安康市', '610000', '陕西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('633', '7930', '宝鸡市', '610000', '陕西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('634', '7990', '汉中市', '610000', '陕西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('635', '8030', '商洛市', '610000', '陕西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('636', '7920', '铜川市', '610000', '陕西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('637', '7970', '渭南市', '610000', '陕西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('638', '7910', '西安市', '610000', '陕西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('639', '7950', '咸阳市', '610000', '陕西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('640', '8040', '延安市', '610000', '陕西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('641', '8060', '榆林市', '610000', '陕西省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('642', '8240', '白银市', '620000', '甘肃省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('643', '8290', '定西市', '620000', '甘肃省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('644', '8380', '甘南藏族自治州', '620000', '甘肃省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('645', '8220', '嘉峪关市', '620000', '甘肃省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('646', '8230', '金昌市', '620000', '甘肃省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('647', '8260', '酒泉市', '620000', '甘肃省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('648', '8210', '兰州市', '620000', '甘肃省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('649', '8360', '临夏回族自治州', '620000', '甘肃省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('650', '8310', '陇南市', '620000', '甘肃省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('651', '8330', '平凉市', '620000', '甘肃省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('652', '8340', '庆阳市', '620000', '甘肃省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('653', '8250', '天水市', '620000', '甘肃省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('654', '8280', '武威市', '620000', '甘肃省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('655', '8270', '张掖市', '620000', '甘肃省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('656', '8570', '果洛藏族自治州', '630000', '青海省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('657', '8540', '海北藏族自治州', '630000', '青海省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('658', '8520', '海东地区', '630000', '青海省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('659', '8560', '海南藏族自治州', '630000', '青海省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('660', '8590', '海西蒙古族藏族自治州', '630000', '青海省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('661', '8550', '黄南藏族自治州', '630000', '青海省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('662', '8510', '西宁市', '630000', '青海省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('663', '8580', '玉树藏族自治州', '630000', '青海省', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('664', '8741', '固原市', '640000', '宁夏回族自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('665', '8720', '石嘴山市', '640000', '宁夏回族自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('666', '8731', '吴忠市', '640000', '宁夏回族自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('667', '8710', '银川市', '640000', '宁夏回族自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('668', '8910', '阿克苏地区', '650000', '新疆维吾尔自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('669', '9031', '阿拉尔市', '650000', '新疆维吾尔自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('670', '9020', '阿勒泰地区', '650000', '新疆维吾尔自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('671', '8880', '巴音郭楞蒙古自治州', '650000', '新疆维吾尔自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('672', '8870', '博尔塔拉蒙古自治州', '650000', '新疆维吾尔自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('673', '8850', '昌吉回族自治州', '650000', '新疆维吾尔自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('674', '8840', '哈密地区', '650000', '新疆维吾尔自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('675', '8960', '和田地区', '650000', '新疆维吾尔自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('676', '8940', '喀什地区', '650000', '新疆维吾尔自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('677', '8820', '克拉玛依市', '650000', '新疆维吾尔自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('678', '8930', '克孜勒苏柯尔克孜自治州', '650000', '新疆维吾尔自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('679', '9028', '石河子市', '650000', '新疆维吾尔自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('680', '9010', '塔城地区', '650000', '新疆维吾尔自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('681', '9032', '图木舒克市', '650000', '新疆维吾尔自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('682', '8830', '吐鲁番地区', '650000', '新疆维吾尔自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('683', '8810', '乌鲁木齐市', '650000', '新疆维吾尔自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
INSERT INTO `fy_bank_city_code` VALUES ('684', '8980', '伊犁哈萨克自治州', '650000', '新疆维吾尔自治区', '2016-12-10 16:09:13', '2016-12-10 16:09:13', '0');
