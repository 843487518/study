create schema `utilsdemo` default character set utf8 collate utf8_bin ;

use utilsdemo;

CREATE TABLE `menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint NOT NULL default 0 COMMENT '父菜单ID，一级菜单为0',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `url` varchar(200) NOT NULL COMMENT '菜单URL',
  `icon` varchar(50) COMMENT '菜单图标',
  `create_user_id` bigint(20) NOT NULL COMMENT '创建者ID',
  `create_time` timestamp  not null default CURRENT_TIMESTAMP comment '创建时间',
  `update_time` timestamp  not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '最后修改时间',
  `delete_flag` tinyint(4)  not null default 0 comment '是否删除，0-未删除；1-已删除',
  PRIMARY KEY (`id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='菜单管理';

CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) COMMENT '角色名称',
  `remark` varchar(100) COMMENT '备注',
  `create_user_id` bigint(20) NOT NULL COMMENT '创建者ID',
  `create_time` timestamp  not null default CURRENT_TIMESTAMP comment '创建时间',
  `update_time` timestamp  not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '最后修改时间',
  `delete_flag` tinyint(4)  not null default 0 comment '是否删除，0-未删除；1-已删除',
  PRIMARY KEY (`id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='角色';

CREATE TABLE `user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint COMMENT '用户ID',
  `role_id` bigint COMMENT '角色ID',
  `create_user_id` bigint(20) NOT NULL COMMENT '创建者ID',
  `create_time` timestamp  not null default CURRENT_TIMESTAMP comment '创建时间',
  `update_time` timestamp  not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '最后修改时间',
  `delete_flag` tinyint(4)  not null default 0 comment '是否删除，0-未删除；1-已删除',
  PRIMARY KEY (`id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='用户与角色对应关系';

CREATE TABLE `role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id`  bigint NOT NULL COMMENT '角色ID',
  `menu_id`  bigint NOT NULL COMMENT '菜单ID',
  `query_authority` tinyint NOT NULL default 0 COMMENT '查询权限',
  `create_authority` tinyint NOT NULL default 0 COMMENT '创建权限',
  `update_authority` tinyint NOT NULL default 0 COMMENT '修改权限',
  `delete_authority` tinyint NOT NULL default 0 COMMENT '删除权限',
  `create_user_id` bigint(20) NOT NULL COMMENT '创建者ID',
  `create_time` timestamp  not null default CURRENT_TIMESTAMP comment '创建时间',
  `update_time` timestamp  not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '最后修改时间',
  `delete_flag` tinyint(4)  not null default 0 comment '是否删除，0-未删除；1-已删除',
  PRIMARY KEY (`id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='角色与菜单对应关系';

-- CREATE TABLE `log` (
--   `id` bigint(20) NOT NULL AUTO_INCREMENT,
--   `username` varchar(50) COMMENT '用户名',
--   `operation` varchar(50) COMMENT '用户操作',
--   `method` varchar(200) COMMENT '请求方法',
--   `params` varchar(5000) COMMENT '请求参数',
--   `time` bigint NOT NULL COMMENT '执行时长(毫秒)',
--   `ip` varchar(64) COMMENT 'IP地址',
--   `create_date` datetime COMMENT '创建时间',
--   PRIMARY KEY (`id`)
-- ) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='系统日志';

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `create_time` timestamp  not null default CURRENT_TIMESTAMP comment '创建时间',
  `update_time` timestamp  not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '最后修改时间',
  `delete_flag` tinyint(4)  not null default 0 comment '是否删除，0-未删除；1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE INDEX (`username`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='用户';

