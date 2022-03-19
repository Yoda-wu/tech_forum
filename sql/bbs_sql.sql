
-----------------
-- 创建用户表 -- 
-----------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
	`id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
	`open_id` VARCHAR(32) COMMENT '微信id',
	`avatar` VARCHAR(128) COMMENT '头像',
	`name` VARCHAR(12) COMMENT '昵称',
	`phone` VARCHAR(11) COMMENT '手机号码',
	`gender` VARCHAR(10) COMMENT '性别',
	`desc` VARCHAR(120) COMMENT '简介',
	`school` VARCHAR(10) COMMENT '学院',
	`version` INT UNSIGNED  DEFAULT '0' NOT NULL COMMENT '版本号',
	`create_time` DATETIME DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
	`update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`delete` BOOLEAN DEFAULT FALSE COMMENT '是否删除',
	PRIMARY KEY(`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

------------------
-- 插入用户数据 -- 
------------------
INSERT INTO `user` (`id`,`open_id`,`avatar`,`name`,`phone`,`gender`,`desc`,`school`)VALUES(
	'1354971607569526785','oodIh5OfOCuNoBJs84DoyzUEKQQY','127.0.0.1:8089/project/img/avatar.jpg',
	'KennyS','19924685310','MALE',
	'这是测试用户1','软件学院'
);
INSERT INTO `user` (`open_id`,`avatar`,`name`,`phone`,`gender`,`desc`,`school` )VALUES(
	'oocIh5OfOCuNoBJs84DoyzUEKXQY','127.0.0.1:8089/project/img/avatar2.jpg',
	'Niko','19924685310','1',
	'这是测试用户2','软件学院'
);
INSERT INTO `user` (`open_id`,`avatar`,`name`,`phone`,`gender`,`desc`,`school` )VALUES(
	'ooeIh2OfODuNoBJs84DoyzUEKQQZ','127.0.0.1:8089/project/img/avatar3.jpg',
	'Simple','19924685310','1',
	'这是测试用户3','软件学院'
);
INSERT INTO `user` (`open_id`,`avatar`,`name`,`phone`,`gender`,`desc`,`school` )VALUES(
	'oadIh5OfOCuNoBJe84DoysUETQSY','127.0.0.1:8089/project/img/avatar4.jpg',
	'ntfwdgq','19924685310','1',
	'这是测试用户4','软件学院'
);
INSERT INTO `user` (`open_id`,`avatar`,`name`,`phone`,`gender`,`desc`,`school` )VALUES(
	'ootIh5WfECuNoBJs84DoyzUEKXQI','127.0.0.1:8089/project/img/avatar5.jpg',
	'不懂csgo','19924685310','1',
	'这是测试用户5','软件学院'
);


-----------------
-- 创建文章表 -- 
-----------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`(
	`id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '文章id',
	`uid`  BIGINT(20) NOT NULL COMMENT '用户id',
	`type` VARCHAR(20) COMMENT '类型',
	`title` VARCHAR(20) NOT NULL COMMENT '文章标题',
	`content` LONGTEXT COMMENT '内容',
	`first_picture` VARCHAR(128) COMMENT '首图',
	`likes` INT DEFAULT '0' COMMENT '点赞数',
	`views` INT DEFAULT '0' COMMENT '浏览次数',
	`state` VARCHAR(16) COMMENT '帖子状态',
	`version` INT UNSIGNED DEFAULT '0' NOT NULL COMMENT '版本号',
	`create_time` DATETIME DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
	`update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`delete` BOOLEAN DEFAULT FALSE COMMENT '是否删除',
	PRIMARY KEY(`id`,`uid`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

------------------
-- 插入文章数据 -- 
------------------
INSERT INTO `article` (`id` , `uid` , `type`, `title`, `content`, `first_picture`, `state`  )
VALUES(
	'235451360349526785', '1354971607569526785', NULL, '测试标题1', '欢迎使用这个小程序来发布你的技术文章！开始书写你的博客吧','127.0.0.1:8089/img/fp/fp1.jpg','1'
)

INSERT INTO `article` (`uid` , `type`, `title`, `content`, `first_picture`, `state`  )
VALUES(
	'1354971607569526786', '技术', '测试标题2', '欢迎使用这个小程序来发布你的技术文章！开始书写你的博客吧','127.0.0.1:8089/img/fp/fp2.jpg','1'
)

--------------------
-- 创建帖子标签表 -- 
--------------------
DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag`(
	`id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '标签id',
	`article_id`  BIGINT(20) NOT NULL COMMENT '文章id',
	`tag_name` varchar(10) not null comment '标签名',
	`version` INT UNSIGNED DEFAULT '0' NOT NULL COMMENT '版本号',
	`create_time` DATETIME DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
	`update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`delete` BOOLEAN DEFAULT FALSE COMMENT '是否删除',
	index(tag_name),
	PRIMARY KEY(`id`,`article_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

-----------------
-- 创建评论表  -- 
-----------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`(
	`id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '评论id',
	`uid`  BIGINT(20) NOT NULL not null COMMENT '用户id',
	`article_id`  BIGINT(20) NOT NULL COMMENT '文章id',
	`parent_id`  BIGINT(20) NOT NULL COMMENT '父评论id',
	`content` varchar(255) comment '评论内容',
	`likes` int default '0' comment '点赞数',
	`version` INT UNSIGNED DEFAULT '0' NOT NULL COMMENT '版本号',
	`create_time` DATETIME DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
	`update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`delete` BOOLEAN DEFAULT FALSE COMMENT '是否删除',
	PRIMARY KEY(`id`,`uid`,`article_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

-----------------
-- 创建关注表  -- 
-----------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow`(
	`id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '评论id',
	`follow_id` bigint(20) not null comment '关注用户的id',
	`uid` bigint(20) not null comment '用户id',
	`version` INT UNSIGNED DEFAULT '0' NOT NULL COMMENT '版本号',
	`create_time` DATETIME DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
	`update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`delete` BOOLEAN DEFAULT FALSE COMMENT '是否删除',
	PRIMARY KEY(`id`,`uid`,`follow_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

-----------------
-- 创建点赞表  -- 
-----------------
DROP TABLE IF EXISTS `like`;
CREATE TABLE `like`(
	`id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '点赞id',
	`uid` BIGINT(20) NOT NULL COMMENT '用户id',
	`version` INT UNSIGNED DEFAULT '0' NOT NULL COMMENT '版本号',
	`create_time` DATETIME DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
	`update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`delete` BOOLEAN DEFAULT FALSE COMMENT '是否删除',
	PRIMARY KEY(`id`,`uid`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

-----------------
-- 创建消息表  -- 
-----------------
drop table if exists `chat`;
create table `chat`(
	`id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '消息id',
	`sender_id` bigint(20) not null comment '发送者id',
	`receiver_id` bigint(20) not null comment '接受者id',
	`content` varchar(255) comment '消息内容',
	`version` INT UNSIGNED DEFAULT '0' NOT NULL COMMENT '版本号',
	`create_time` DATETIME DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
	`delete` BOOLEAN DEFAULT FALSE COMMENT '是否删除',
	PRIMARY KEY(`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4;




