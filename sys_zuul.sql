create table t_sys_access_info(
	id bigint(20) UNSIGNED auto_increment PRIMARY key comment '自增主键',
	access_key varchar(50) comment 'access_key',
	access_secret varchar(50) comment 'access_secret',
	 `if_del` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0-正常 1-删除',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `version` bigint(20) DEFAULT '1' COMMENT '版本号',
  `rec_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `rec_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间'
);