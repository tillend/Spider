/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50704
Source Host           : localhost:3306
Source Database       : spider

Target Server Type    : MYSQL
Target Server Version : 50704
File Encoding         : 65001

Date: 2018-02-12 16:26:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for anime
-- ----------------------------
DROP TABLE IF EXISTS `anime`;
CREATE TABLE `anime` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `video_id` varchar(15) DEFAULT NULL,
  `play` bigint(11) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `pubdate` datetime DEFAULT NULL,
  `review` bigint(11) DEFAULT NULL,
  `pic` varchar(100) DEFAULT NULL,
  `mid` varchar(15) DEFAULT NULL,
  `arcurl` varchar(200) DEFAULT NULL,
  `tag` varchar(200) DEFAULT NULL,
  `video_review` bigint(11) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `favorites` bigint(11) DEFAULT NULL,
  `duration` bigint(11) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `arcrank` varchar(10) DEFAULT NULL,
  `senddate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3384 DEFAULT CHARSET=utf8;
