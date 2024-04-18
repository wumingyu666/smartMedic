CREATE TABLE `t_sm_news`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `title`       varchar(255)        NOT NULL DEFAULT '' COMMENT '标题',
    `content`     longtext                     Default NULL COMMENT '内容',
    `user_id`     bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '作者id',
    `user_name`   varchar(255)        NOT NULL DEFAULT '' COMMENT '作者名称',
    `create_time` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted`  tinyint(4)          NOT NULL DEFAULT '0' COMMENT '删除标志',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 38
  DEFAULT CHARSET = utf8mb4 COMMENT ='新闻信息表';