/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50704
Source Host           : localhost:3306
Source Database       : spider

Target Server Type    : MYSQL
Target Server Version : 50704
File Encoding         : 65001

Date: 2018-02-13 00:57:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bangumi
-- ----------------------------
DROP TABLE IF EXISTS `bangumi`;
CREATE TABLE `bangumi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `favorites` bigint(10) unsigned DEFAULT NULL,
  `play` bigint(10) unsigned DEFAULT NULL,
  `coins` bigint(20) DEFAULT NULL,
  `video_review` bigint(11) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `update_time` varchar(20) DEFAULT NULL,
  `evaluate` varchar(500) DEFAULT NULL,
  `season_id` varchar(10) DEFAULT NULL,
  `pub_time` datetime DEFAULT NULL,
  `total_count` int(11) DEFAULT NULL,
  `pub_string` varchar(100) DEFAULT NULL,
  `update_pattern` varchar(100) DEFAULT NULL,
  `is_finish` varchar(10) DEFAULT NULL,
  `newest_ep_index` varchar(10) DEFAULT NULL,
  `cover` varchar(200) DEFAULT NULL,
  `season_status` varchar(10) DEFAULT NULL,
  `week` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3002 DEFAULT CHARSET=utf8;
