-- ----------------------------
-- 1. 创建数据库
-- ----------------------------
CREATE DATABASE IF NOT EXISTS yja DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;


-- ----------------------------
-- 2. 创建表 && 数据初始化
-- ----------------------------
use yja;

SET NAMES utf8mb4;
SET
    FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for yja_dept
-- ----------------------------
DROP TABLE IF EXISTS yja_dept;
-- auto-generated definition
create table yja_dept
(
    REVISION     int                                                           null comment '乐观锁',
    CREATED_BY   varchar(32)                                                   null comment '创建人',
    CREATED_TIME datetime                                                      null comment '创建时间',
    UPDATED_BY   varchar(32)                                                   null comment '更新人',
    UPDATED_TIME datetime                                                      null comment '更新时间',
    ID           varchar(32)                                                   not null comment 'ID'
        primary key,
    NAME         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL comment '部门名称',
    PARENT_ID    varchar(255)                                                  null comment '父节点ID',
    TREE_PATH    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL comment '父节点ID路径',
    SORT         int        default 0                                          null comment '显示顺序',
    STATUS       tinyint(1) default 1                                          null comment '状态;(1:正常;0:禁用)',
    DELETED      tinyint(1) default 0                                          null comment '逻辑删除标识;(1:已删除;0:未删除)'
) comment '部门表';


-- ----------------------------
-- Table structure for yja_menu
-- ----------------------------
DROP TABLE IF EXISTS yja_menu;
CREATE TABLE yja_menu
(
    REVISION      int                                                          null comment '乐观锁',
    CREATED_BY    varchar(32)                                                  null comment '创建人',
    CREATED_TIME  datetime                                                     null comment '创建时间',
    UPDATED_BY    varchar(32)                                                  null comment '更新人',
    UPDATED_TIME  datetime                                                     null comment '更新时间',
    ID          varchar(32)                                                  NOT NULL COMMENT 'ID',
    PARENT_ID   bigint                                                       NOT NULL COMMENT '父菜单ID',
    TREE_PATH   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         DEFAULT NULL COMMENT '父节点ID路径',
    NAME        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '菜单名称',
    TYPE        tinyint                                                      NOT NULL COMMENT '菜单类型(1:菜单 2:目录 3:外链 4:按钮)',
    PATH        varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         DEFAULT '' COMMENT '路由路径(浏览器地址栏路径)',
    COMPONENT   varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         DEFAULT NULL COMMENT '组件路径(vue页面完整路径，省略.vue后缀)',
    PERM        varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         DEFAULT NULL COMMENT '权限标识',
    VISIBLE     tinyint(1)                                                   NOT NULL DEFAULT '1' COMMENT '显示状态(1-显示;0-隐藏)',
    SORT        int                                                                   DEFAULT '0' COMMENT '排序',
    ICON        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT '' COMMENT '菜单图标',
    REDIRECT    varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         DEFAULT NULL COMMENT '跳转路径',
    ALWAYS_SHOW tinyint                                                               DEFAULT NULL COMMENT '【目录】只有一个子路由是否始终显示(1:是 0:否)',
    KEEP_ALIVE  tinyint                                                               DEFAULT NULL COMMENT '【菜单】是否开启页面缓存(1:是 0:否)',
    PRIMARY KEY (ID)
) COMMENT ='菜单管理';

INSERT INTO yja_menu(REVISION, CREATED_BY, CREATED_TIME, UPDATED_BY, UPDATED_TIME,id,parent_id,tree_path,name ,type ,path,component,perm,visible,sort ,icon,redirect,always_show ,keep_alive )
VALUES (null,null,'2021-08-28 09:12:21',null, '2021-08-28 09:12:21',1, 0, '0', '系统管理', 2, '/system', 'Layout', NULL, 1, 1, 'system', '/system/user',  1, NULL);
INSERT INTO yja_menu(REVISION, CREATED_BY, CREATED_TIME, UPDATED_BY, UPDATED_TIME,id,parent_id,tree_path,name ,type ,path,component,perm,visible,sort ,icon,redirect,always_show ,keep_alive )
VALUES (null,null,'2021-08-28 09:12:21',null, '2021-08-28 09:12:21',2, 1, '0,1', '用户管理', 1, 'user', 'system/user/index', NULL, 1, 1, 'user', NULL, 1, 1);
INSERT INTO yja_menu(REVISION, CREATED_BY, CREATED_TIME, UPDATED_BY, UPDATED_TIME,id,parent_id,tree_path,name ,type ,path,component,perm,visible,sort ,icon,redirect,always_show ,keep_alive )
VALUES (null,null,'2021-08-28 09:12:21',null, '2021-08-28 09:12:21',3, 1, '0,1', '角色管理', 1, 'role', 'system/role/index', NULL, 1, 2, 'role', NULL, 1, 1);
INSERT INTO yja_menu(REVISION, CREATED_BY, CREATED_TIME, UPDATED_BY, UPDATED_TIME,id,parent_id,tree_path,name ,type ,path,component,perm,visible,sort ,icon,redirect,always_show ,keep_alive )
VALUES (null,null,'2021-08-28 09:12:21',null, '2021-08-28 09:12:21',4, 1, '0,1', '菜单管理', 1, 'menu', 'system/menu/index', NULL, 1, 3, 'menu', NULL, 1, 1);
INSERT INTO yja_menu(REVISION, CREATED_BY, CREATED_TIME, UPDATED_BY, UPDATED_TIME,id,parent_id,tree_path,name ,type ,path,component,perm,visible,sort ,icon,redirect,always_show ,keep_alive )
VALUES (null,null,'2021-08-28 09:12:21',null, '2021-08-28 09:12:21',5, 1, '0,1', '部门管理', 1, 'dept', 'system/dept/index', NULL, 1, 4, 'tree', NULL, 1, 1);
INSERT INTO yja_menu(REVISION, CREATED_BY, CREATED_TIME, UPDATED_BY, UPDATED_TIME,id,parent_id,tree_path,name ,type ,path,component,perm,visible,sort ,icon,redirect,always_show ,keep_alive )
VALUES (null,null,'2021-08-28 09:12:21',null, '2021-08-28 09:12:21',6, 1, '0,1', '字典管理', 1, 'dict', 'system/dict/index', NULL, 1, 5, 'dict', NULL, 1, 1);


DROP TABLE IF EXISTS yja_role;
CREATE TABLE yja_role
(
    REVISION     INT COMMENT '乐观锁',
    CREATED_BY   VARCHAR(32) COMMENT '创建人',
    CREATED_TIME DATETIME COMMENT '创建时间',
    UPDATED_BY   VARCHAR(32) COMMENT '更新人',
    UPDATED_TIME DATETIME COMMENT '更新时间',
    ID           VARCHAR(32)                                                   NOT NULL COMMENT 'ID',
    ROLENO       VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
    ROLENAME     VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
    SORT         int COMMENT '显示顺序' DEFAULT '0' COMMENT '排序',
    STATUS       TINYINT(1)         DEFAULT 0 COMMENT '角色状态(1-正常；0-停用)',
    SUMMARY      VARCHAR(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '角色介绍',
    DATA_SCOPE tinyint NULL DEFAULT NULL COMMENT '数据权限(0-所有数据；1-部门及子部门数据；2-本部门数据；3-本人数据)',
    DELETED      TINYINT(1)         DEFAULT 0 COMMENT '逻辑删除标识(0-未删除；1-已删除)',
    PRIMARY KEY (ID)
) COMMENT = '角色表';

INSERT INTO `yja_role` VALUES (0,'admin','2024-04-09 00:00:00','admin','2024-04-09 00:00:00',1, '管理员', 'admin', 1, 1, 0, 0, 0);

-- ----------------------------
-- Table structure for yja_user
-- ----------------------------
DROP TABLE IF EXISTS yja_user;
create table yja_user
(
    REVISION              INT COMMENT '乐观锁',
    CREATED_BY           VARCHAR(32) COMMENT '创建人',
    CREATED_TIME           DATETIME COMMENT '创建时间',
    UPDATED_BY             VARCHAR(32) COMMENT '更新人',
    UPDATED_TIME           DATETIME COMMENT '更新时间',
    ID                       varchar(32)                             not null comment 'ID'
        primary key,
    USERID                   varchar(255)                            not null comment '用户账号',
    USERNAME                 varchar(255) collate utf8mb4_general_ci not null comment '用户名',
    NICK_NAME                varchar(255) collate utf8mb4_general_ci null comment '昵称',
    DEPT_ID                  varchar(32)                             null comment '部门ID',
    PASSWORD                 varchar(200) collate utf8mb4_general_ci  not null comment '密码',
    GENDER                   tinyint(1)                              null comment '性别;(1:男;2:女)',
    AVATAR                   varchar(100)                            null comment '头像',
    EMAIL                    varchar(60)                             null comment '邮箱',
    BIRTHDAY                 datetime                                null comment '出生年月日',
    MOBILE                   varchar(50)                             null comment '手机',
    VALID_CODE               varchar(255)                            null comment '邮箱验证码',
    SUMMARY                  varchar(200) collate utf8mb4_general_ci null comment '自我简介最多150字',
    LOGIN_COUNT              tinyint                                 null comment '登录次数',
    LAST_LOGIN_TIME          datetime                                null comment '最后登录时间',
    LAST_LOGIN_IP            varchar(50)                             null comment '最后登录IP',
    STATUS                   int        default 0                    null comment '状态;(0:正常;1:锁定;2:停用;3:注销)',
    SOURCE                   varchar(255) collate utf8mb4_general_ci null comment '资料来源',
    QQ_NUMBER                varchar(20)                             null comment 'QQ号',
    WE_CHAT                  varchar(255)                            null comment '微信号',
    OCCUPATION               varchar(255)                            null comment '职业',
    COMMENT_STATUS           tinyint(1) default 1                    null comment '评论状态;(1:正常;0:禁言)',
    IP_SOURCE                varchar(255)                            null comment 'ip来源',
    BROWSER                  varchar(255)                            null comment '浏览器',
    OS                       varchar(255)                            null comment '操作系统',
    START_EMAIL_NOTIFICATION tinyint(1) default 1                    null comment '是否开启邮件通知;(0:关闭;1:开启)',
    USER_TAG                 tinyint(1) default 0                    null comment '用户标签;(0:普通用户;1:管理员;2:博主 等)',
    LOADING_VALID            tinyint(1) default 0                    null comment '是否通过加载校验;(0:未通过;1:已通过)',
    DELETED                  tinyint(1) default 0                    null comment '是否删除;(0:否;1:是)'
)
    comment '用户信息表';
INSERT INTO `yja_user` VALUES (0,'admin','2024-04-09 00:00:00','admin','2024-04-09 00:00:00','1', 'admin', '系统管理员','系统管理员', '1', '$2a$10$xVWsNOhHrCxh5UbpCE7/HuJ.PAOKcYAqRxD2CO2nVnJS.IAXkr5aq', 1, 'https://oss.youlai.tech/youlai-boot/2023/05/16/811270ef31f548af9cffc026dfc3777b.gif', '123@qq.com', '2024-04-09', '15269173558', '', '',0,'2024-04-09','',0,'','','','',1,'','','',0,1,0,0);


-- ----------------------------
-- Table structure for yja_role_menu
-- ----------------------------

DROP TABLE IF EXISTS yja_role_menu;
CREATE TABLE yja_role_menu
(
    CREATED_BY   VARCHAR(32) COMMENT '创建人',
    CREATED_TIME DATETIME COMMENT '创建时间',
    UPDATED_BY   VARCHAR(32) COMMENT '更新人',
    UPDATED_TIME DATETIME COMMENT '更新时间',
    ROLE_ID      VARCHAR(32) NOT NULL COMMENT '角色ID',
    MENU_ID      VARCHAR(32) NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (MENU_ID, ROLE_ID)
) COMMENT = '角色和菜单关联表';
INSERT INTO `yja_role_menu`(ROLE_ID,MENU_ID) VALUES ('1', '1');
INSERT INTO `yja_role_menu`(ROLE_ID,MENU_ID) VALUES ('1', '2');
INSERT INTO `yja_role_menu`(ROLE_ID,MENU_ID) VALUES ('1', '3');
INSERT INTO `yja_role_menu`(ROLE_ID,MENU_ID) VALUES ('1', '4');
INSERT INTO `yja_role_menu`(ROLE_ID,MENU_ID) VALUES ('1', '5');


-- ----------------------------
-- Table structure for yja_user_role
-- ----------------------------
DROP TABLE IF EXISTS yja_user_role;
CREATE TABLE yja_user_role
(
    CREATED_BY   VARCHAR(32) COMMENT '创建人',
    CREATED_TIME DATETIME COMMENT '创建时间',
    UPDATED_BY   VARCHAR(32) COMMENT '更新人',
    UPDATED_TIME DATETIME COMMENT '更新时间',
    USER_ID      VARCHAR(32) NOT NULL COMMENT '用户ID',
    ROLE_ID      VARCHAR(32) NOT NULL COMMENT '角色ID',
    PRIMARY KEY (USER_ID, ROLE_ID)
) COMMENT = '用户和角色关联表';
INSERT INTO `yja_user_role`(USER_ID,ROLE_ID) VALUES ('1', '1');

SET FOREIGN_KEY_CHECKS = 1;
