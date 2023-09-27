CREATE DATABASE /*!32312 IF NOT EXISTS*/`sfams` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sfams`;

/*Table structure for table `buy_record` */
DROP TABLE USER;



CREATE TABLE `user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_number` VARCHAR(20) DEFAULT NULL COMMENT '学号/教工号',
  `name` VARCHAR(30) DEFAULT NULL COMMENT '姓名',
  `phone` VARCHAR(30) DEFAULT NULL COMMENT '电话号码',
  `password` VARCHAR(30) DEFAULT NULL COMMENT '密码',
  `mail` VARCHAR(40) DEFAULT NULL COMMENT '邮箱',
  `avatar` TEXT DEFAULT NULL COMMENT '头像',
  `department_id` BIGINT(20) DEFAULT NULL  COMMENT '学院部门ID',
  `position_id` BIGINT(20) DEFAULT NULL COMMENT '职位ID',
  `authority` INT(10) DEFAULT '0' COMMENT '权限 ：0 无权限 1 有权限',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `user` VALUES (NULL,'2220203546','丰宇凡','15157165006','1234567890','feng_yufan@163.com','https://hospital-test1.oss-cn-hangzhou.aliyuncs.com/2023/09/26/fb5580d32bc8660001ecb79b23fb68d315319615.jpg','1','1','0'),
 (NULL,'2222222222','逍遥小枫','1234567890','1234567890','xiao_yaoxiao@123.com','https://hospital-test1.oss-cn-hangzhou.aliyuncs.com/2023/09/26/5d3696e224ad7d4609ce2953a5e34726.jpeg','2','1','1')

;
SELECT * FROM USER;


DROP TABLE `asset`;


CREATE TABLE `asset`(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`asset_number` VARCHAR(30) DEFAULT NULL COMMENT '资产编号',
	`asset_name`  VARCHAR(30) DEFAULT NULL COMMENT '资产名称',
	`asset_category_id` BIGINT(20) DEFAULT NULL COMMENT '资产类型Id',
	`origin_value` DECIMAL(10,2) NOT NULL DEFAULT '0.00',
	`specification` VARCHAR(50) DEFAULT NULL COMMENT '规格',
	`brand_model` VARCHAR(50) DEFAULT NULL COMMENT '品牌/型号',
	`asset_photo` TEXT DEFAULT NULL COMMENT '固定资产照片',
	`quantity` BIGINT(10) DEFAULT '0' COMMENT '资产数量',
	`expected_life` BIGINT(10) DEFAULT '12' COMMENT '预计使用时间,单位：月',
	`acquisition_date` DATETIME DEFAULT NULL COMMENT '购置日期',
	`notes` VARCHAR(1000) DEFAULT NULL COMMENT '备注',
	`user` BIGINT(20) DEFAULT NULL COMMENT '使用人ID',
	`department_id` BIGINT(20) DEFAULT NULL COMMENT '使用的部门单位',
	`status` TINYINT(4) DEFAULT '1' COMMENT '使用状态,0在用 1闲置 2报废',
	`net_value` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '净值，单位：元',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;





INSERT INTO `ASSET` VALUES (NULL,'1234567890','CPU','1','100.11','规格1 xxxx','英特尔/I7-19210','xxx.xxxx.com','12','12','2023-02-01 12:29:03','xxxxxxxxxxxxxxxxxx','1','1','0','100'),
(NULL,'23232323232','CPU2','1','1023.11','规格2 xxxx','英特尔/I9-19210','xxx.xxxx.com','24','24','2023-02-05 8:29:03','xxxxxxxxxxxxxxxxxx','1','2','1','100')
;


SELECT * FROM ASSET;



DROP TABLE `asset_category`;


CREATE TABLE `asset_category` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `category_number` BIGINT(20) DEFAULT NULL COMMENT '分类编号',
  `category_name` VARCHAR(50) DEFAULT NULL COMMENT '类型名称',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


INSERT INTO `asset_category` VALUES( NULL,'1','设备'),(NULL,'2','材料');

SELECT * FROM asset_category;


DROP TABLE `department`;

CREATE TABLE `department` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `department_number` BIGINT(20) DEFAULT NULL COMMENT '部门编号',
  `department_name` VARCHAR(50) DEFAULT NULL COMMENT '部门名称',

  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


INSERT INTO `department` VALUES (NULL,'1','信息科学技术学院'),(NULL,'2','法学院'),(NULL,'3','软件学院'),(NULL,'4','幼儿园');

SELECT * FROM department;


DROP TABLE `position`;

CREATE TABLE `position`(
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `position_name` VARCHAR(50) DEFAULT NULL COMMENT '职位名称',
  
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


INSERT INTO `position` VALUES (NULL,'学生'),(NULL,'教师'),(NULL,'职工');

SELECT * FROM `position`;


DROP TABLE asset_lease_record;


CREATE TABLE `asset_lease_record`(

  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `asset_id` BIGINT(20) DEFAULT NULL COMMENT '资产Id',
  `user_id` BIGINT(20) DEFAULT NULL COMMENT '用户Id',
  `start_date` DATETIME DEFAULT NULL COMMENT '租借日期',
  `due_date` DATETIME DEFAULT NULL COMMENT '预计归还日期',
  `return_date` DATETIME DEFAULT NULL COMMENT '实际归还日期',
  `status` TINYINT(8) DEFAULT NULL COMMENT '租借状态, 0借用中 1已归还',
  `notes` VARCHAR(1000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;









DROP TABLE verification;

CREATE TABLE `verification` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `asset_id` BIGINT(20) DEFAULT NULL COMMENT '资产Id',
  `verification_date` DATETIME DEFAULT NULL COMMENT '核查时间',
  `location` VARCHAR(100) DEFAULT NULL COMMENT '资产所在位置',
  `verification_staff` BIGINT(20) DEFAULT NULL COMMENT '核查人员',
  `status` TINYINT(8) DEFAULT '0' COMMENT '使用状态,0在用 1闲置 2报废',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;



INSERT INTO `verification` VALUES (NULL,'1','2023-02-01','xxxx:xxxx','1','0');

SELECT * FROM verification;


DROP TABLE asset_maintenance_record;



CREATE TABLE asset_maintenance_record(
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `asset_id` BIGINT DEFAULT NULL COMMENT '资产ID',
  `maintenance_date` DATETIME DEFAULT NULL COMMENT '执行维护的日期',
  `maintenance_cost` DECIMAL(10,2) DEFAULT NULL COMMENT '维护费用',
  `maintenance_description` VARCHAR(1000) DEFAULT NULL COMMENT '对维护内容的详情描述',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


/*

DROP TABLE user_asset;

CREATE TABLE `user_asset`(
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) DEFAULT NULL COMMENT '用户ID',
  `asset_id` BIGINT(20) DEFAULT NULL COMMENT '资产ID',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;




*/






