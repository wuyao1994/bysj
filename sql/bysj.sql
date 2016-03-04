/*
Navicat MySQL Data Transfer

Source Server         : wuyao
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : bysj

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2016-03-04 09:50:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_menu_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu_info`;
CREATE TABLE `admin_menu_info` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `url` varchar(300) DEFAULT NULL,
  `active` int(1) DEFAULT NULL,
  `parent_no` int(1) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `level` int(2) DEFAULT NULL,
  `sequence` int(2) DEFAULT NULL,
  `modelid` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_menu_info
-- ----------------------------
INSERT INTO `admin_menu_info` VALUES ('1', '系统管理', null, '1', null, '2016-02-26 14:00:03', '2016-02-26 14:00:07', '1', '1', 'system');
INSERT INTO `admin_menu_info` VALUES ('2', '密码修改', '/admin/system/updatePassword', '1', '1', '2016-02-26 14:01:07', null, '2', '1', 'system');
INSERT INTO `admin_menu_info` VALUES ('3', '系统用户', '/admin/system/viewAdminUsers', '1', '1', '2016-02-26 14:02:17', null, '2', '2', 'system');
INSERT INTO `admin_menu_info` VALUES ('4', '监测服务', null, '1', null, '2016-02-26 14:03:00', null, '1', '1', 'service');
INSERT INTO `admin_menu_info` VALUES ('5', '系统菜单', '/admin/system/viewAdminMenus', '1', '1', '2016-02-26 14:03:32', null, '2', '3', 'system');
INSERT INTO `admin_menu_info` VALUES ('6', '系统角色', '/admin/system/viewAdminRoles', '1', '1', '2016-02-26 14:04:40', null, '2', '4', 'system');
INSERT INTO `admin_menu_info` VALUES ('7', '节点监测', null, '1', '4', '2016-03-03 10:42:16', null, '2', '1', 'service');

-- ----------------------------
-- Table structure for admin_role_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_info`;
CREATE TABLE `admin_role_info` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `active` int(1) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `description` varchar(180) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_role_info
-- ----------------------------
INSERT INTO `admin_role_info` VALUES ('1', 'ROLE_ADMIN', '1', '2016-02-26 13:46:53', '2016-02-26 13:46:31', '系统后台管理员');
INSERT INTO `admin_role_info` VALUES ('2', 'ROLE_PUBLIC', '1', '2016-02-26 13:47:29', '2016-02-26 13:47:31', '公共权限');

-- ----------------------------
-- Table structure for admin_role_menu_grant
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_menu_grant`;
CREATE TABLE `admin_role_menu_grant` (
  `role_no` int(11) NOT NULL,
  `menu_no` int(11) NOT NULL,
  `create` int(1) DEFAULT NULL,
  `delete` int(1) DEFAULT NULL,
  `update` int(1) DEFAULT NULL,
  `view` int(1) DEFAULT NULL,
  UNIQUE KEY `role_menu_grant_uk` (`role_no`,`menu_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_role_menu_grant
-- ----------------------------
INSERT INTO `admin_role_menu_grant` VALUES ('1', '1', '1', null, null, null);
INSERT INTO `admin_role_menu_grant` VALUES ('1', '2', '1', null, null, null);
INSERT INTO `admin_role_menu_grant` VALUES ('1', '3', '1', null, null, null);
INSERT INTO `admin_role_menu_grant` VALUES ('1', '4', '1', null, null, null);
INSERT INTO `admin_role_menu_grant` VALUES ('1', '5', '1', null, null, null);
INSERT INTO `admin_role_menu_grant` VALUES ('1', '6', '1', null, null, null);
INSERT INTO `admin_role_menu_grant` VALUES ('1', '7', '1', null, null, null);
INSERT INTO `admin_role_menu_grant` VALUES ('2', '2', '1', null, null, null);
INSERT INTO `admin_role_menu_grant` VALUES ('2', '4', '1', null, null, null);
INSERT INTO `admin_role_menu_grant` VALUES ('2', '7', '1', null, null, null);

-- ----------------------------
-- Table structure for admin_user_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_info`;
CREATE TABLE `admin_user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `password` varchar(90) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `update_date` date DEFAULT NULL,
  `email` varchar(45) DEFAULT 'ROLE_ADMIN',
  `active` int(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_user_info
-- ----------------------------
INSERT INTO `admin_user_info` VALUES ('1', 'admin', 'admin', '2016-02-26', '2016-02-16', 'yao.wu@sipingsoft.com', '1');

-- ----------------------------
-- Table structure for admin_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_role_relation`;
CREATE TABLE `admin_user_role_relation` (
  `user_no` int(11) NOT NULL,
  `role_no` int(11) NOT NULL,
  PRIMARY KEY (`user_no`,`role_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_user_role_relation
-- ----------------------------
INSERT INTO `admin_user_role_relation` VALUES ('1', '1');

-- ----------------------------
-- View structure for admin_role_menu_grant_view
-- ----------------------------
DROP VIEW IF EXISTS `admin_role_menu_grant_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `admin_role_menu_grant_view` AS select `admin_role_menu_grant`.`role_no` AS `role_no`,`admin_role_menu_grant`.`menu_no` AS `menu_no`,`admin_role_menu_grant`.`create` AS `create`,`admin_role_menu_grant`.`delete` AS `delete`,`admin_role_menu_grant`.`update` AS `update`,`admin_role_menu_grant`.`view` AS `view`,1 AS `active` from `admin_role_menu_grant` ;
DROP TRIGGER IF EXISTS `admin_menu_info_BEFORE_DELETE`;
DELIMITER ;;
CREATE TRIGGER `admin_menu_info_BEFORE_DELETE` BEFORE DELETE ON `admin_menu_info` FOR EACH ROW delete from admin_role_menu_grant where menu_no = old.no
;;
DELIMITER ;
