/*
Navicat MySQL Data Transfer

Source Server         : 106.14.1.101
Source Server Version : 50717
Source Host           : 106.14.1.101:3306
Source Database       : vvote

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-03-09 09:53:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `vote_list`
-- ----------------------------
DROP TABLE IF EXISTS `vote_list`;
CREATE TABLE `vote_list` (
  `serial` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `openid` varchar(255) DEFAULT NULL,
  `noNum` varchar(255) DEFAULT NULL COMMENT 'video的编号',
  `record_time` varchar(255) DEFAULT NULL,
  `is_valid` varchar(2) DEFAULT '1' COMMENT '0无效   1有效',
  PRIMARY KEY (`serial`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of vote_list
-- ----------------------------
INSERT INTO `vote_list` VALUES ('4', 'none', '000002', '2020-03-02 17:29:35', '1');
INSERT INTO `vote_list` VALUES ('5', 'none', '000002', '2020-03-02 17:29:38', '1');
INSERT INTO `vote_list` VALUES ('6', 'none', '000002', '2020-03-02 17:29:40', '1');
INSERT INTO `vote_list` VALUES ('7', 'orU3k0wqLbir9PKy0NXYJze60CGs', '000002', '2020-03-02 19:41:30', '1');
INSERT INTO `vote_list` VALUES ('8', 'orU3k0wqLbir9PKy0NXYJze60CGs', '000001', '2020-03-02 22:28:53', '1');
INSERT INTO `vote_list` VALUES ('9', 'orU3k0wqLbir9PKy0NXYJze60CGs', '000001', '2020-03-02 22:28:57', '1');
INSERT INTO `vote_list` VALUES ('10', 'orU3k044-S6122QPAYzFKlXgSRHg', '000001', '2020-03-03 09:13:10', '1');
INSERT INTO `vote_list` VALUES ('11', 'orU3k044-S6122QPAYzFKlXgSRHg', '000002', '2020-03-03 09:14:11', '1');
INSERT INTO `vote_list` VALUES ('12', 'orU3k044-S6122QPAYzFKlXgSRHg', '000002', '2020-03-03 09:14:11', '1');
INSERT INTO `vote_list` VALUES ('13', 'orU3k0zmJ8RTgdRY48d0fGc0AEOY', '000001', '2020-03-04 09:23:04', '1');
INSERT INTO `vote_list` VALUES ('14', 'orU3k06uwBx0uP2Y9zvc_W-Y2fzY', '000002', '2020-03-04 09:24:19', '1');
INSERT INTO `vote_list` VALUES ('15', 'orU3k0wqUHaXL0JHW0fd_C17UbQk', '000002', '2020-03-04 09:41:39', '1');

-- ----------------------------
-- Table structure for `vote_video`
-- ----------------------------
DROP TABLE IF EXISTS `vote_video`;
CREATE TABLE `vote_video` (
  `noNum` varchar(255) NOT NULL,
  `imgurl` text,
  `initiator` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `videourl` text,
  PRIMARY KEY (`noNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of vote_video
-- ----------------------------
INSERT INTO `vote_video` VALUES ('000001', 'https://video.acg123.cn/cover.jpg', '苏州分局', '这是测试的视频1', 'https://video.acg123.cn/19546962-1.mp4');
INSERT INTO `vote_video` VALUES ('000002', 'https://video.acg123.cn/cover2.jpg', '浙江分局', '这是测试2', 'https://video.acg123.cn/God%20of%20War_20180603213423.mp4');

-- ----------------------------
-- View structure for `video_likes_view`
-- ----------------------------
DROP VIEW IF EXISTS `video_likes_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `video_likes_view` AS select `a`.`noNum` AS `noNum`,`a`.`imgurl` AS `imgurl`,`a`.`initiator` AS `initiator`,`a`.`title` AS `title`,`a`.`videourl` AS `videourl`,ifnull(`b`.`counting`,'0') AS `likes` from (`vvote`.`vote_video` `a` left join (select `vvote`.`vote_list`.`noNum` AS `noNum`,count(1) AS `counting` from `vvote`.`vote_list` where (`vvote`.`vote_list`.`is_valid` = '1') group by `vvote`.`vote_list`.`noNum`) `b` on((`b`.`noNum` = `a`.`noNum`))) ;
