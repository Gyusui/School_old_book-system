/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50131
Source Host           : localhost:3306
Source Database       : tingtao

Target Server Type    : MYSQL
Target Server Version : 50131
File Encoding         : 65001

Date: 2014-11-04 19:59:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user_infor_tab`
-- ----------------------------
DROP TABLE IF EXISTS `user_infor_tab`;
CREATE TABLE `user_infor_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` varchar(255) DEFAULT NULL,
  `login_pwd` varchar(50) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_sex` varchar(10) DEFAULT NULL,
  `user_location` varchar(255) DEFAULT NULL,
  `user_phone` varchar(50) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_right` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_infor_tab
-- ----------------------------
INSERT INTO `user_infor_tab` VALUES ('1', 'hbdl', '123', '海滨', 'male', '浙江', '13940812345', 'yanghaibineddy@gmail.com', '1');
INSERT INTO `user_infor_tab` VALUES ('2', 'tom', '123', 'Tom', 'male', '浙江', '13940812345', 'yanghaibineddy@gmail.com', '1');
INSERT INTO `user_infor_tab` VALUES ('3', 'eddyyang', '123', 'Eddy Yang', 'male', '浙江', '13940812345', 'yanghaibineddy@gmail.com', '0');
