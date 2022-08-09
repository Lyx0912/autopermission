/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : autoperm

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 09/08/2022 08:43:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for l_permission
-- ----------------------------
DROP TABLE IF EXISTS `l_permission`;
CREATE TABLE `l_permission`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '权限菜单编号',
  `PARENT_ID` int(0) NULL DEFAULT NULL COMMENT '父权限编号',
  `PERM_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路径',
  `SORT` int(0) NULL DEFAULT NULL COMMENT '排序权重',
  `ICON` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图表',
  `TYPE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单类型 0目录 1菜单 2按钮',
  `COMPONENT` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件地址',
  `PERMS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of l_permission
-- ----------------------------
INSERT INTO `l_permission` VALUES (1, 0, '个人中心a', '/personal/center', 1, 'dashboard', '1', '', 'personal:center');
INSERT INTO `l_permission` VALUES (2, 0, '用户管理', '/user/manager', 2, 'peoples', '1', '/user/manager/index', 'user:manager');
INSERT INTO `l_permission` VALUES (3, 0, '菜单管理', '/menu/manager', 3, 'tree-table', '1', '/menu/manager/index', 'menu:manager');
INSERT INTO `l_permission` VALUES (5, 1, '个人中心1', '/personal/center', 2, 'dashboard', '1', '/personal/center/index', 'personal:center');
INSERT INTO `l_permission` VALUES (13, 0, '角色管理', '/role/manager', 2, 'edit', '1', '/role/manager/index', 'role:manager');

-- ----------------------------
-- Table structure for l_role
-- ----------------------------
DROP TABLE IF EXISTS `l_role`;
CREATE TABLE `l_role`  (
  `ID` int(0) NOT NULL COMMENT '角色编号',
  `ROLE_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `STATUS` tinyint(0) NULL DEFAULT NULL COMMENT '角色状态 0禁用 1启用',
  `CREATE_TIME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of l_role
-- ----------------------------
INSERT INTO `l_role` VALUES (1, 'admin', 1, '2022-6-28 15:22:21');
INSERT INTO `l_role` VALUES (2, 'manager', 1, '2022-6-28 15:22:21');

-- ----------------------------
-- Table structure for l_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `l_role_permission`;
CREATE TABLE `l_role_permission`  (
  `ID` int(0) NOT NULL,
  `ROLE_ID` int(0) NULL DEFAULT NULL COMMENT '角色编号',
  `PERMISSION_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限编号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of l_role_permission
-- ----------------------------
INSERT INTO `l_role_permission` VALUES (1, 1, '1');
INSERT INTO `l_role_permission` VALUES (2, 1, '2');
INSERT INTO `l_role_permission` VALUES (3, 2, '2');
INSERT INTO `l_role_permission` VALUES (4, 1, '3');
INSERT INTO `l_role_permission` VALUES (5, 2, '1');
INSERT INTO `l_role_permission` VALUES (6, 2, '3');
INSERT INTO `l_role_permission` VALUES (7, 2, '3');
INSERT INTO `l_role_permission` VALUES (8, 1, '4');
INSERT INTO `l_role_permission` VALUES (9, 1, '5');
INSERT INTO `l_role_permission` VALUES (10, 1, '6');
INSERT INTO `l_role_permission` VALUES (11, 1, '13');

-- ----------------------------
-- Table structure for l_user
-- ----------------------------
DROP TABLE IF EXISTS `l_user`;
CREATE TABLE `l_user`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户编号',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '年龄',
  `enable` tinyint(0) NULL DEFAULT NULL COMMENT '启用状态0禁用，1启用',
  `sex` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别 0女 1男',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `create_Time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of l_user
-- ----------------------------
INSERT INTO `l_user` VALUES ('1', 'admin', '$2a$10$56cZG1/1kdNAJuTi9Va3v.No5QXxHSNI8.BsquuocH9QhWqmc4sl2', 'admin', '0', 2, '1', '湖南省湘潭市九华区', '15673225896', '2022-6-22 15:22:22');
INSERT INTO `l_user` VALUES ('2', 'lyx', '$2a$10$EPF9DjBafQ9ctrASa3P9Ped6tYjEQN67IU4hx45nbdHigQkeStN3C', 'lyyxx', '12', 1, '1', '湖南省湘潭市岳塘区', '15673225896', '2022-6-22 15:22:22');
INSERT INTO `l_user` VALUES ('3', 'xxx', '$2a$10$56cZG1/1kdNAJuTi9Va3v.No5QXxHSNI8.BsquuocH9QhWqmc4sl2', NULL, '12', 2, '1', '湖南省湘潭市岳塘区', '15673225896', '2022-6-22 15:22:22');

-- ----------------------------
-- Table structure for l_user_role
-- ----------------------------
DROP TABLE IF EXISTS `l_user_role`;
CREATE TABLE `l_user_role`  (
  `ID` int(0) NOT NULL COMMENT '关联编号',
  `USER_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户编号',
  `ROLE_ID` int(0) NULL DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of l_user_role
-- ----------------------------
INSERT INTO `l_user_role` VALUES (1, '1', 1);
INSERT INTO `l_user_role` VALUES (2, '2', 1);
INSERT INTO `l_user_role` VALUES (1052819457, '3', 2);

SET FOREIGN_KEY_CHECKS = 1;
