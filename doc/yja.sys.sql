/*
* yja 权限系统数据库(MySQL8.x)
* @author haoxr
*/

-- ----------------------------
-- 1. 创建数据库
-- ----------------------------
CREATE DATABASE IF NOT EXISTS yja DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;


-- ----------------------------
-- 2. 创建表 && 数据初始化
-- ----------------------------
use yja;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for yja_dept
-- ----------------------------
DROP TABLE IF EXISTS `yja_dept`;
CREATE TABLE `yja_dept`  (
                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '部门名称',
                             `parent_id` bigint NOT NULL DEFAULT 0 COMMENT '父节点id',
                             `tree_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '父节点id路径',
                             `sort` int NULL DEFAULT 0 COMMENT '显示顺序',
                             `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态(1:正常;0:禁用)',
                             `deleted` tinyint NULL DEFAULT 0 COMMENT '逻辑删除标识(1:已删除;0:未删除)',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                             `create_by` bigint NULL DEFAULT NULL COMMENT '创建人ID',
                             `update_by` bigint NULL DEFAULT NULL COMMENT '修改人ID',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 171 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of yja_dept
-- ----------------------------
INSERT INTO `yja_dept` VALUES (1, '有来技术', 0, '0', 1, 1, 0, NULL, NULL, 1, 1);
INSERT INTO `yja_dept` VALUES (2, '研发部门', 1, '0,1', 1, 1, 0, NULL, '2022-04-19 12:46:37', 2, 2);
INSERT INTO `yja_dept` VALUES (3, '测试部门', 1, '0,1', 1, 1, 0, NULL, '2022-04-19 12:46:37', 2, 2);

-- ----------------------------
-- Table structure for yja_dict
-- ----------------------------
DROP TABLE IF EXISTS `yja_dict`;
CREATE TABLE `yja_dict`  (
                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `type_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典类型编码',
                             `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典项名称',
                             `value` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典项值',
                             `sort` int NULL DEFAULT 0 COMMENT '排序',
                             `status` tinyint NULL DEFAULT 0 COMMENT '状态(1:正常;0:禁用)',
                             `defaulted` tinyint NULL DEFAULT 0 COMMENT '是否默认(1:是;0:否)',
                             `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of yja_dict
-- ----------------------------
INSERT INTO `yja_dict` VALUES (1, 'gender', '男', '1', 1, 1, 0, NULL, '2019-05-05 13:07:52', '2022-06-12 23:20:39');
INSERT INTO `yja_dict` VALUES (2, 'gender', '女', '2', 2, 1, 0, NULL, '2019-04-19 11:33:00', '2019-07-02 14:23:05');
INSERT INTO `yja_dict` VALUES (3, 'gender', '未知', '0', 1, 1, 0, NULL, '2020-10-17 08:09:31', '2020-10-17 08:09:31');

-- ----------------------------
-- Table structure for yja_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `yja_dict_type`;
CREATE TABLE `yja_dict_type`  (
                                  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ',
                                  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '类型名称',
                                  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '类型编码',
                                  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态(0:正常;1:禁用)',
                                  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
                                  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                  PRIMARY KEY (`id`) USING BTREE,
                                  UNIQUE INDEX `type_code`(`code` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 89 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of yja_dict_type
-- ----------------------------
INSERT INTO `yja_dict_type` VALUES (1, '性别', 'gender', 1, NULL, '2019-12-06 19:03:32', '2022-06-12 16:21:28');

-- ----------------------------
-- Table structure for yja_menu
-- ----------------------------
DROP TABLE IF EXISTS `yja_menu`;
CREATE TABLE `yja_menu` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `parent_id` bigint NOT NULL COMMENT '父菜单ID',
                            `tree_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父节点ID路径',
                            `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '菜单名称',
                            `type` tinyint NOT NULL COMMENT '菜单类型(1:菜单 2:目录 3:外链 4:按钮)',
                            `path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '路由路径(浏览器地址栏路径)',
                            `component` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组件路径(vue页面完整路径，省略.vue后缀)',
                            `perm` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限标识',
                            `visible` tinyint(1) NOT NULL DEFAULT '1' COMMENT '显示状态(1-显示;0-隐藏)',
                            `sort` int DEFAULT '0' COMMENT '排序',
                            `icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '菜单图标',
                            `redirect` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '跳转路径',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            `always_show` tinyint DEFAULT NULL COMMENT '【目录】只有一个子路由是否始终显示(1:是 0:否)',
                            `keep_alive` tinyint DEFAULT NULL COMMENT '【菜单】是否开启页面缓存(1:是 0:否)',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='菜单管理';

-- ----------------------------
-- Records of yja_menu
-- ----------------------------
INSERT INTO `yja_menu` VALUES (1, 0, '0', '系统管理', 2, '/system', 'Layout', NULL, 1, 1, 'system', '/system/user', '2021-08-28 09:12:21', '2021-08-28 09:12:21', NULL, NULL);
INSERT INTO `yja_menu` VALUES (2, 1, '0,1', '用户管理', 1, 'user', 'system/user/index', NULL, 1, 1, 'user', NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', NULL, 1);
INSERT INTO `yja_menu` VALUES (3, 1, '0,1', '角色管理', 1, 'role', 'system/role/index', NULL, 1, 2, 'role', NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', NULL, 1);
INSERT INTO `yja_menu` VALUES (4, 1, '0,1', '菜单管理', 1, 'menu', 'system/menu/index', NULL, 1, 3, 'menu', NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', NULL, 1);
INSERT INTO `yja_menu` VALUES (5, 1, '0,1', '部门管理', 1, 'dept', 'system/dept/index', NULL, 1, 4, 'tree', NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', NULL, 1);
INSERT INTO `yja_menu` VALUES (6, 1, '0,1', '字典管理', 1, 'dict', 'system/dict/index', NULL, 1, 5, 'dict', NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', NULL, 1);
INSERT INTO `yja_menu` VALUES (20, 0, '0', '多级菜单', 2, '/multi-level', 'Layout', NULL, 1, 9, 'cascader', '/multi-level/multi-level1', '2022-02-16 23:11:00', '2022-02-16 23:11:00', NULL, NULL);
INSERT INTO `yja_menu` VALUES (21, 20, '0,20', '菜单一级', 1, 'multi-level1', 'demo/multi-level/level1', NULL, 1, 1, '', '/multi-level/multi-level2', '2022-02-16 23:13:38', '2022-02-16 23:13:38', NULL, 1);
INSERT INTO `yja_menu` VALUES (22, 21, '0,20,21', '菜单二级', 1, 'multi-level2', 'demo/multi-level/children/level2', NULL, 1, 1, '', '/multi-level/multi-level2/multi-level3-1', '2022-02-16 23:14:23', '2022-02-16 23:14:23', NULL, 1);
INSERT INTO `yja_menu` VALUES (23, 22, '0,20,21,22', '菜单三级-1', 1, 'multi-level3-1', 'demo/multi-level/children/children/level3-1', NULL, 1, 1, '', '', '2022-02-16 23:14:51', '2022-02-16 23:14:51', NULL, 1);
INSERT INTO `yja_menu` VALUES (24, 22, '0,20,21,22', '菜单三级-2', 1, 'multi-level3-2', 'demo/multi-level/children/children/level3-2', NULL, 1, 2, '', '', '2022-02-16 23:15:08', '2022-02-16 23:15:08', NULL, 1);
INSERT INTO `yja_menu` VALUES (26, 0, '0', '平台文档', 2, '/doc', 'Layout', NULL, 1, 8, 'document', NULL, '2022-02-17 22:51:20', '2022-02-17 22:51:20', NULL, NULL);
INSERT INTO `yja_menu` VALUES (30, 26, '0,26', '平台文档(外链)', 3, 'https://juejin.cn/post/7228990409909108793', '', NULL, 1, 2, 'link', '', '2022-02-18 00:01:40', '2022-02-18 00:01:40', NULL, NULL);
INSERT INTO `yja_menu` VALUES (31, 2, '0,1,2', '用户新增', 4, '', NULL, 'sys:user:add', 1, 1, '', '', '2022-10-23 11:04:08', '2022-10-23 11:04:11', NULL, NULL);
INSERT INTO `yja_menu` VALUES (32, 2, '0,1,2', '用户编辑', 4, '', NULL, 'sys:user:edit', 1, 2, '', '', '2022-10-23 11:04:08', '2022-10-23 11:04:11', NULL, NULL);
INSERT INTO `yja_menu` VALUES (33, 2, '0,1,2', '用户删除', 4, '', NULL, 'sys:user:delete', 1, 3, '', '', '2022-10-23 11:04:08', '2022-10-23 11:04:11', NULL, NULL);
INSERT INTO `yja_menu` VALUES (36, 0, '0', '组件封装', 2, '/component', 'Layout', NULL, 1, 10, 'menu', '', '2022-10-31 09:18:44', '2022-10-31 09:18:47', NULL, NULL);
INSERT INTO `yja_menu` VALUES (37, 36, '0,36', '富文本编辑器', 1, 'wang-editor', 'demo/wang-editor', NULL, 1, 1, '', '', NULL, NULL, NULL, 1);
INSERT INTO `yja_menu` VALUES (38, 36, '0,36', '图片上传', 1, 'upload', 'demo/upload', NULL, 1, 2, '', '', '2022-11-20 23:16:30', '2022-11-20 23:16:32', NULL, 1);
INSERT INTO `yja_menu` VALUES (39, 36, '0,36', '图标选择器', 1, 'icon-selector', 'demo/icon-selector', NULL, 1, 3, '', '', '2022-11-20 23:16:30', '2022-11-20 23:16:32', NULL, 1);
INSERT INTO `yja_menu` VALUES (40, 0, '0', '接口', 2, '/api', 'Layout', NULL, 1, 7, 'api', '', '2022-02-17 22:51:20', '2022-02-17 22:51:20', 1, NULL);
INSERT INTO `yja_menu` VALUES (41, 40, '0,40', '接口文档', 1, 'api-doc', 'demo/api-doc', NULL, 1, 1, 'api', '', '2022-02-17 22:51:20', '2022-02-17 22:51:20', NULL, 1);
INSERT INTO `yja_menu` VALUES (70, 3, '0,1,3', '角色新增', 4, '', NULL, 'sys:role:add', 1, 1, '', NULL, '2023-05-20 23:39:09', '2023-05-20 23:39:09', NULL, NULL);
INSERT INTO `yja_menu` VALUES (71, 3, '0,1,3', '角色编辑', 4, '', NULL, 'sys:role:edit', 1, 2, '', NULL, '2023-05-20 23:40:31', '2023-05-20 23:40:31', NULL, NULL);
INSERT INTO `yja_menu` VALUES (72, 3, '0,1,3', '角色删除', 4, '', NULL, 'sys:role:delete', 1, 3, '', NULL, '2023-05-20 23:41:08', '2023-05-20 23:41:08', NULL, NULL);
INSERT INTO `yja_menu` VALUES (73, 4, '0,1,4', '菜单新增', 4, '', NULL, 'sys:menu:add', 1, 1, '', NULL, '2023-05-20 23:41:35', '2023-05-20 23:41:35', NULL, NULL);
INSERT INTO `yja_menu` VALUES (74, 4, '0,1,4', '菜单编辑', 4, '', NULL, 'sys:menu:edit', 1, 3, '', NULL, '2023-05-20 23:41:58', '2023-05-20 23:41:58', NULL, NULL);
INSERT INTO `yja_menu` VALUES (75, 4, '0,1,4', '菜单删除', 4, '', NULL, 'sys:menu:delete', 1, 3, '', NULL, '2023-05-20 23:44:18', '2023-05-20 23:44:18', NULL, NULL);
INSERT INTO `yja_menu` VALUES (76, 5, '0,1,5', '部门新增', 4, '', NULL, 'sys:dept:add', 1, 1, '', NULL, '2023-05-20 23:45:00', '2023-05-20 23:45:00', NULL, NULL);
INSERT INTO `yja_menu` VALUES (77, 5, '0,1,5', '部门编辑', 4, '', NULL, 'sys:dept:edit', 1, 2, '', NULL, '2023-05-20 23:46:16', '2023-05-20 23:46:16', NULL, NULL);
INSERT INTO `yja_menu` VALUES (78, 5, '0,1,5', '部门删除', 4, '', NULL, 'sys:dept:delete', 1, 3, '', NULL, '2023-05-20 23:46:36', '2023-05-20 23:46:36', NULL, NULL);
INSERT INTO `yja_menu` VALUES (79, 6, '0,1,6', '字典类型新增', 4, '', NULL, 'sys:dict_type:add', 1, 1, '', NULL, '2023-05-21 00:16:06', '2023-05-21 00:16:06', NULL, NULL);
INSERT INTO `yja_menu` VALUES (81, 6, '0,1,6', '字典类型编辑', 4, '', NULL, 'sys:dict_type:edit', 1, 2, '', NULL, '2023-05-21 00:27:37', '2023-05-21 00:27:37', NULL, NULL);
INSERT INTO `yja_menu` VALUES (84, 6, '0,1,6', '字典类型删除', 4, '', NULL, 'sys:dict_type:delete', 1, 3, '', NULL, '2023-05-21 00:29:39', '2023-05-21 00:29:39', NULL, NULL);
INSERT INTO `yja_menu` VALUES (85, 6, '0,1,6', '字典数据新增', 4, '', NULL, 'sys:dict:add', 1, 4, '', NULL, '2023-05-21 00:46:56', '2023-05-21 00:47:06', NULL, NULL);
INSERT INTO `yja_menu` VALUES (86, 6, '0,1,6', '字典数据编辑', 4, '', NULL, 'sys:dict:edit', 1, 5, '', NULL, '2023-05-21 00:47:36', '2023-05-21 00:47:36', NULL, NULL);
INSERT INTO `yja_menu` VALUES (87, 6, '0,1,6', '字典数据删除', 4, '', NULL, 'sys:dict:delete', 1, 6, '', NULL, '2023-05-21 00:48:10', '2023-05-21 00:48:20', NULL, NULL);
INSERT INTO `yja_menu` VALUES (88, 2, '0,1,2', '重置密码', 4, '', NULL, 'sys:user:reset_pwd', 1, 4, '', NULL, '2023-05-21 00:49:18', '2023-05-21 00:49:18', NULL, NULL);
INSERT INTO `yja_menu` VALUES (89, 0, '0', '功能演示', 2, '/function', 'Layout', NULL, 1, 11, 'menu', '', '2022-10-31 09:18:44', '2022-10-31 09:18:47', NULL, NULL);
INSERT INTO `yja_menu` VALUES (90, 89, '0,89', 'Websocket', 1, 'websocket', 'demo/websocket', NULL, 1, 3, '', '', '2022-11-20 23:16:30', '2022-11-20 23:16:32', NULL, 1);
INSERT INTO `yja_menu` VALUES (91, 89, '0,89', '敬请期待...', 2, 'other', 'demo/other', NULL, 1, 4, '', '', '2022-11-20 23:16:30', '2022-11-20 23:16:32', NULL, NULL);
INSERT INTO `yja_menu` VALUES (93, 36, '0,36', '签名', 1, 'signature', 'demo/signature', NULL, 1, 6, '', '', '2022-11-20 23:16:30', '2022-11-20 23:16:32', NULL, 1);
INSERT INTO `yja_menu` VALUES (94, 36, '0,36', '表格', 1, 'table', 'demo/table', NULL, 1, 7, '', '', '2022-11-20 23:16:30', '2022-11-20 23:16:32', NULL, 1);
INSERT INTO `yja_menu` VALUES (95, 36, '0,36', '字典组件', 1, 'dict-demo', 'demo/dict', NULL, 1, 4, '', '', '2022-11-20 23:16:30', '2022-11-20 23:16:32', NULL, 1);
INSERT INTO `yja_menu` VALUES (96, 89, '0,89', 'Permission', 1, 'permission', 'demo/permission/page', NULL, 1, 1, '', '', '2022-11-20 23:16:30', '2022-11-20 23:16:32', NULL, 1);
INSERT INTO `yja_menu` VALUES (97, 89, '0,89', 'Icons', 1, 'icon-demo', 'demo/icons', NULL, 1, 2, '', '', '2022-11-20 23:16:30', '2022-11-20 23:16:32', NULL, 1);
INSERT INTO `yja_menu` VALUES (98, 0, '0', 'Table', 2, '/table', 'Layout', NULL, 0, 10, 'table', '', '2023-08-08 20:49:50', '2023-08-08 20:49:50', NULL, NULL);
INSERT INTO `yja_menu` VALUES (99, 98, '0,98', '动态Table', 1, 'dynamic-table', 'table/dynamic-table/index', NULL, 0, 1, '', '', '2023-08-08 20:54:42', '2023-08-08 20:54:42', NULL, 1);
INSERT INTO `yja_menu` VALUES (100, 98, '0,98', '拖拽Table', 1, 'drag-table', 'table/drag-table', NULL, 0, 2, '', '', '2023-08-08 20:54:42', '2023-08-08 20:54:42', NULL, 1);
INSERT INTO `yja_menu` VALUES (101, 98, '0,98', '综合Table', 1, 'complex-table', 'table/complex-table', NULL, 0, 3, '', '', '2023-08-08 20:54:42', '2023-08-08 20:54:42', NULL, 1);
INSERT INTO `yja_menu` VALUES (102, 26, '0,26', '平台文档(内嵌)', 3, 'internal-doc', 'demo/internal-doc', NULL, 1, 1, 'document', '', '2022-02-18 00:01:40', '2022-02-18 00:01:40', NULL, NULL);

-- --------------------
-- Table structure for yja_role
-- ----------------------------
DROP TABLE IF EXISTS `yja_role`;
CREATE TABLE `yja_role`  (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
                             `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色编码',
                             `sort` int NULL DEFAULT NULL COMMENT '显示顺序',
                             `status` tinyint(1) NULL DEFAULT 1 COMMENT '角色状态(1-正常；0-停用)',
                             `data_scope` tinyint NULL DEFAULT NULL COMMENT '数据权限(0-所有数据；1-部门及子部门数据；2-本部门数据；3-本人数据)',
                             `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除标识(0-未删除；1-已删除)',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                             `update_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             PRIMARY KEY (`id`) USING BTREE,
                             UNIQUE INDEX `name`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 128 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of yja_role
-- ----------------------------
INSERT INTO `yja_role` VALUES (1, '超级管理员', 'ROOT', 1, 1, 0, 0, '2021-05-21 14:56:51', '2018-12-23 16:00:00');
INSERT INTO `yja_role` VALUES (2, '系统管理员', 'ADMIN', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO `yja_role` VALUES (3, '访问游客', 'GUEST', 3, 1, 2, 0, '2021-05-26 15:49:05', '2019-05-05 16:00:00');
INSERT INTO `yja_role` VALUES (4, '系统管理员1', 'ADMIN1', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO `yja_role` VALUES (5, '系统管理员2', 'ADMIN1', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO `yja_role` VALUES (6, '系统管理员3', 'ADMIN1', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO `yja_role` VALUES (7, '系统管理员4', 'ADMIN1', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO `yja_role` VALUES (8, '系统管理员5', 'ADMIN1', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO `yja_role` VALUES (9, '系统管理员6', 'ADMIN1', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO `yja_role` VALUES (10, '系统管理员7', 'ADMIN1', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO `yja_role` VALUES (11, '系统管理员8', 'ADMIN1', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO `yja_role` VALUES (12, '系统管理员9', 'ADMIN1', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);

-- ----------------------------
-- Table structure for yja_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `yja_role_menu`;
CREATE TABLE `yja_role_menu`  (
                                  `role_id` bigint NOT NULL COMMENT '角色ID',
                                  `menu_id` bigint NOT NULL COMMENT '菜单ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of yja_role_menu
-- ----------------------------
INSERT INTO `yja_role_menu` VALUES (2, 1);
INSERT INTO `yja_role_menu` VALUES (2, 2);
INSERT INTO `yja_role_menu` VALUES (2, 3);
INSERT INTO `yja_role_menu` VALUES (2, 4);
INSERT INTO `yja_role_menu` VALUES (2, 5);
INSERT INTO `yja_role_menu` VALUES (2, 6);
INSERT INTO `yja_role_menu` VALUES (2, 11);
INSERT INTO `yja_role_menu` VALUES (2, 12);
INSERT INTO `yja_role_menu` VALUES (2, 19);
INSERT INTO `yja_role_menu` VALUES (2, 18);
INSERT INTO `yja_role_menu` VALUES (2, 17);
INSERT INTO `yja_role_menu` VALUES (2, 13);
INSERT INTO `yja_role_menu` VALUES (2, 14);
INSERT INTO `yja_role_menu` VALUES (2, 15);
INSERT INTO `yja_role_menu` VALUES (2, 16);
INSERT INTO `yja_role_menu` VALUES (2, 9);
INSERT INTO `yja_role_menu` VALUES (2, 10);
INSERT INTO `yja_role_menu` VALUES (2, 37);
INSERT INTO `yja_role_menu` VALUES (2, 20);
INSERT INTO `yja_role_menu` VALUES (2, 21);
INSERT INTO `yja_role_menu` VALUES (2, 22);
INSERT INTO `yja_role_menu` VALUES (2, 23);
INSERT INTO `yja_role_menu` VALUES (2, 24);
INSERT INTO `yja_role_menu` VALUES (2, 32);
INSERT INTO `yja_role_menu` VALUES (2, 33);
INSERT INTO `yja_role_menu` VALUES (2, 39);
INSERT INTO `yja_role_menu` VALUES (2, 34);
INSERT INTO `yja_role_menu` VALUES (2, 26);
INSERT INTO `yja_role_menu` VALUES (2, 30);
INSERT INTO `yja_role_menu` VALUES (2, 31);
INSERT INTO `yja_role_menu` VALUES (2, 36);
INSERT INTO `yja_role_menu` VALUES (2, 38);
INSERT INTO `yja_role_menu` VALUES (2, 39);
INSERT INTO `yja_role_menu` VALUES (2, 40);
INSERT INTO `yja_role_menu` VALUES (2, 41);
INSERT INTO `yja_role_menu` VALUES (2, 1);
INSERT INTO `yja_role_menu` VALUES (2, 2);
INSERT INTO `yja_role_menu` VALUES (2, 3);
INSERT INTO `yja_role_menu` VALUES (2, 4);
INSERT INTO `yja_role_menu` VALUES (2, 5);
INSERT INTO `yja_role_menu` VALUES (2, 6);
INSERT INTO `yja_role_menu` VALUES (2, 20);
INSERT INTO `yja_role_menu` VALUES (2, 21);
INSERT INTO `yja_role_menu` VALUES (2, 22);
INSERT INTO `yja_role_menu` VALUES (2, 23);
INSERT INTO `yja_role_menu` VALUES (2, 24);
INSERT INTO `yja_role_menu` VALUES (2, 26);
INSERT INTO `yja_role_menu` VALUES (2, 30);
INSERT INTO `yja_role_menu` VALUES (2, 31);
INSERT INTO `yja_role_menu` VALUES (2, 32);
INSERT INTO `yja_role_menu` VALUES (2, 33);
INSERT INTO `yja_role_menu` VALUES (2, 36);
INSERT INTO `yja_role_menu` VALUES (2, 37);
INSERT INTO `yja_role_menu` VALUES (2, 38);
INSERT INTO `yja_role_menu` VALUES (2, 39);
INSERT INTO `yja_role_menu` VALUES (2, 40);
INSERT INTO `yja_role_menu` VALUES (2, 41);
INSERT INTO `yja_role_menu` VALUES (2, 70);
INSERT INTO `yja_role_menu` VALUES (2, 71);
INSERT INTO `yja_role_menu` VALUES (2, 72);
INSERT INTO `yja_role_menu` VALUES (2, 73);
INSERT INTO `yja_role_menu` VALUES (2, 74);
INSERT INTO `yja_role_menu` VALUES (2, 75);
INSERT INTO `yja_role_menu` VALUES (2, 76);
INSERT INTO `yja_role_menu` VALUES (2, 77);
INSERT INTO `yja_role_menu` VALUES (2, 78);
INSERT INTO `yja_role_menu` VALUES (2, 79);
INSERT INTO `yja_role_menu` VALUES (2, 81);
INSERT INTO `yja_role_menu` VALUES (2, 84);
INSERT INTO `yja_role_menu` VALUES (2, 85);
INSERT INTO `yja_role_menu` VALUES (2, 86);
INSERT INTO `yja_role_menu` VALUES (2, 87);
INSERT INTO `yja_role_menu` VALUES (2, 88);
INSERT INTO `yja`.`yja_role_menu` (`role_id`, `menu_id`) VALUES (2, 89);
INSERT INTO `yja`.`yja_role_menu` (`role_id`, `menu_id`) VALUES (2, 90);
INSERT INTO `yja`.`yja_role_menu` (`role_id`, `menu_id`) VALUES (2, 91);
INSERT INTO `yja`.`yja_role_menu` (`role_id`, `menu_id`) VALUES (2, 92);
INSERT INTO `yja`.`yja_role_menu` (`role_id`, `menu_id`) VALUES (2, 93);
INSERT INTO `yja`.`yja_role_menu` (`role_id`, `menu_id`) VALUES (2, 94);
INSERT INTO `yja`.`yja_role_menu` (`role_id`, `menu_id`) VALUES (2, 95);
INSERT INTO `yja`.`yja_role_menu` (`role_id`, `menu_id`) VALUES (2, 96);
INSERT INTO `yja`.`yja_role_menu` (`role_id`, `menu_id`) VALUES (2, 97);
INSERT INTO `yja`.`yja_role_menu` (`role_id`, `menu_id`) VALUES (2, 102);



-- ----------------------------
-- Table structure for yja_user
-- ----------------------------
DROP TABLE IF EXISTS `yja_user`;
CREATE TABLE `yja_user`  (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
                             `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
                             `gender` tinyint(1) NULL DEFAULT 1 COMMENT '性别((1:男;2:女))',
                             `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
                             `dept_id` int NULL DEFAULT NULL COMMENT '部门ID',
                             `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户头像',
                             `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系方式',
                             `status` tinyint(1) NULL DEFAULT 1 COMMENT '用户状态((1:正常;0:禁用))',
                             `email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
                             `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除标识(0:未删除;1:已删除)',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                             PRIMARY KEY (`id`) USING BTREE,
                             UNIQUE INDEX `login_name`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 288 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of yja_user
-- ----------------------------
INSERT INTO `yja_user` VALUES (1, 'root', '有来技术', 0, '$2a$10$xVWsNOhHrCxh5UbpCE7/HuJ.PAOKcYAqRxD2CO2nVnJS.IAXkr5aq', NULL, 'https://oss.youlai.tech/youlai-boot/2023/05/16/811270ef31f548af9cffc026dfc3777b.gif', '17621590365', 1, 'youlaitech@163.com', 0, NULL, NULL);
INSERT INTO `yja_user` VALUES (2, 'admin', '系统管理员', 1, '$2a$10$xVWsNOhHrCxh5UbpCE7/HuJ.PAOKcYAqRxD2CO2nVnJS.IAXkr5aq', 1, 'https://oss.youlai.tech/youlai-boot/2023/05/16/811270ef31f548af9cffc026dfc3777b.gif', '17621210366', 1, '', 0, '2019-10-10 13:41:22', '2022-07-31 12:39:30');
INSERT INTO `yja_user` VALUES (3, 'test', '测试小用户', 1, '$2a$10$xVWsNOhHrCxh5UbpCE7/HuJ.PAOKcYAqRxD2CO2nVnJS.IAXkr5aq', 3, 'https://oss.youlai.tech/youlai-boot/2023/05/16/811270ef31f548af9cffc026dfc3777b.gif', '17621210366', 1, 'youlaitech@163.com', 0, '2021-06-05 01:31:29', '2021-06-05 01:31:29');
INSERT INTO `yja_user` VALUES (287, '123', '123', 1, '$2a$10$mVoBVqm1837huf7kcN0wS.GVYKEFv0arb7GvzfFXoTyqDlcRzT.6i', 1, '', NULL, 1, NULL, 1, '2023-05-21 14:11:19', '2023-05-21 14:11:25');

-- ----------------------------
-- Table structure for yja_user_role
-- ----------------------------
DROP TABLE IF EXISTS `yja_user_role`;
CREATE TABLE `yja_user_role`  (
                                  `user_id` bigint NOT NULL COMMENT '用户ID',
                                  `role_id` bigint NOT NULL COMMENT '角色ID',
                                  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of yja_user_role
-- ----------------------------
INSERT INTO `yja_user_role` VALUES (1, 1);
INSERT INTO `yja_user_role` VALUES (2, 2);
INSERT INTO `yja_user_role` VALUES (3, 3);
INSERT INTO `yja_user_role` VALUES (287, 2);

SET FOREIGN_KEY_CHECKS = 1;

