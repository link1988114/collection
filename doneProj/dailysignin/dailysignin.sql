/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : dailysignin

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-03-07 19:03:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sign_exchange`
-- ----------------------------
DROP TABLE IF EXISTS `sign_exchange`;
CREATE TABLE `sign_exchange` (
  `openid` varchar(255) NOT NULL,
  `proID` varchar(255) DEFAULT NULL,
  `record_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sign_exchange
-- ----------------------------

-- ----------------------------
-- Table structure for `sign_pro`
-- ----------------------------
DROP TABLE IF EXISTS `sign_pro`;
CREATE TABLE `sign_pro` (
  `proID` varchar(255) NOT NULL,
  `proName` varchar(255) DEFAULT NULL,
  `proUnlock` varchar(255) DEFAULT '0' COMMENT '已解锁人数',
  `proRemains` varchar(255) DEFAULT '0' COMMENT '商品初始剩余数量',
  `proImg` varchar(255) DEFAULT NULL,
  `unlockDays` varchar(255) DEFAULT '0' COMMENT '需要解锁天数',
  `isvalid` varchar(255) DEFAULT '1' COMMENT '1有效   0无效',
  PRIMARY KEY (`proID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sign_pro
-- ----------------------------
INSERT INTO `sign_pro` VALUES ('1', '立白除螨洗衣液', '0', '0', 'img/pro03.png', '20', '1');
INSERT INTO `sign_pro` VALUES ('2', '福临门食用油', '0', '0', 'img/pro02.png', '10', '1');
INSERT INTO `sign_pro` VALUES ('3', '尚岛宜家垃圾袋', '0', '0', 'img/pro01.png', '5', '1');

-- ----------------------------
-- Table structure for `sign_unlock`
-- ----------------------------
DROP TABLE IF EXISTS `sign_unlock`;
CREATE TABLE `sign_unlock` (
  `openid` varchar(255) NOT NULL,
  `unlock_proid` varchar(255) NOT NULL,
  `record_time` varchar(255) DEFAULT NULL,
  `isvalid` varchar(255) DEFAULT '1',
  PRIMARY KEY (`openid`,`unlock_proid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sign_unlock
-- ----------------------------

-- ----------------------------
-- Table structure for `sign_user`
-- ----------------------------
DROP TABLE IF EXISTS `sign_user`;
CREATE TABLE `sign_user` (
  `openid` varchar(255) NOT NULL,
  `sign_date` varchar(255) NOT NULL COMMENT '签到日期 YYYY-MM-DD',
  `record_time` varchar(255) DEFAULT NULL,
  `combo_flag` varchar(255) DEFAULT '1' COMMENT '是否在连续签到队列 0     1',
  PRIMARY KEY (`openid`,`sign_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sign_user
-- ----------------------------

-- ----------------------------
-- View structure for `pro_view`
-- ----------------------------
DROP VIEW IF EXISTS `pro_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `pro_view` AS select `a`.`proID` AS `proID`,`a`.`proName` AS `proName`,ifnull(`c`.`counting`,0) AS `proUnlock`,(`a`.`proRemains` - ifnull(`b`.`counting`,0)) AS `proRemains`,`a`.`proImg` AS `proImg`,`a`.`unlockDays` AS `unlockDays` from ((`dailysignin`.`sign_pro` `a` left join (select count(1) AS `counting`,`dailysignin`.`sign_exchange`.`proID` AS `proID` from `dailysignin`.`sign_exchange` group by `dailysignin`.`sign_exchange`.`proID`) `b` on((`b`.`proID` = `a`.`proID`))) left join (select count(1) AS `counting`,`dailysignin`.`sign_unlock`.`unlock_proid` AS `unlock_proid` from `dailysignin`.`sign_unlock` where (`dailysignin`.`sign_unlock`.`isvalid` = '1') group by `dailysignin`.`sign_unlock`.`unlock_proid`) `c` on((`c`.`unlock_proid` = `a`.`proID`))) where (`a`.`isvalid` = '1') ;
