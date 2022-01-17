SET GLOBAL time_zone = '+8:00';

DROP DATABASE IF EXISTS `devtest`;
CREATE DATABASE `devtest` CHARACTER SET utf8mb4;
USE `devtest`;

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
    `id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `username`  varchar(50) NOT NULL COMMENT '用户名',
    `password`  varchar(50) NOT NULL COMMENT '用户密码',
    `email`  varchar(80) NOT NULL COMMENT 'email地址',
    `status`  tinyint NOT NULL DEFAULT 0 COMMENT '用户状态(0:正常,1:已锁定,其他:扩展)',
    `login_times`  int(8) NOT NULL DEFAULT 0 COMMENT '总的登录次数',
    `last_login`  datetime NULL COMMENT '最后一次登录时间',
    `is_deleted`  tinyint NOT NULL COMMENT '删除标志,0:正常,1:已删除',
    `create_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建/注册时间',
    `update_time`  datetime NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
)   ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 ;


ALTER TABLE `sys_user`
    ADD UNIQUE INDEX `idx_username` (`username`) ;

INSERT INTO sys_user( username, password, email, status, login_times, is_deleted, create_time)
    VALUES( 'admin', '123123', 'f.ky@qq.com', 0, 0, 0, now()) ;
ALTER TABLE `sys_user` AUTO_INCREMENT=10000 ;


DROP TABLE IF EXISTS `sys_mail_notify`;
CREATE TABLE `sys_mail_notify` (
   `id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
   `user_id`  int(11) NOT NULL COMMENT '用户id',
   `email`  varchar(80) NOT NULL COMMENT 'email地址',
   `subject`  varchar(200) NOT NULL COMMENT '邮件主题',
   `content`  varchar(2000) NOT NULL COMMENT '邮件内容',
   `status`  tinyint NOT NULL COMMENT '邮件发送状态',
   `send_times`  smallint NULL COMMENT '邮件发送次数',
   `last_send`  datetime NULL COMMENT '最后一次发送时间',
   `create_time`  datetime NULL COMMENT '邮件通知创建时间',
   `update_time`  datetime NULL COMMENT '修改时间',
   PRIMARY KEY (`id`)
)   ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 ;


