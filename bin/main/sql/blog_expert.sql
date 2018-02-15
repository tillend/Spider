/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50704
Source Host           : localhost:3306
Source Database       : spider

Target Server Type    : MYSQL
Target Server Version : 50704
File Encoding         : 65001

Date: 2018-02-15 17:07:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blog_expert
-- ----------------------------
DROP TABLE IF EXISTS `blog_expert`;
CREATE TABLE `blog_expert` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bloger_id` varchar(50) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `article_num` int(10) unsigned DEFAULT NULL,
  `read_num` bigint(20) unsigned DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `job` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1572 DEFAULT CHARSET=utf8;
