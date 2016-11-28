/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.12-log : Database - db_student
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_student` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_student`;

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `Cno` int(11) NOT NULL AUTO_INCREMENT,
  `Cname` varchar(30) DEFAULT NULL,
  `Ccredit` int(11) DEFAULT NULL,
  PRIMARY KEY (`Cno`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`Cno`,`Cname`,`Ccredit`) values (1,'语文',5),(2,'数学',5),(3,'英语',5),(4,'计算机基础教程',4);

/*Table structure for table `score` */

DROP TABLE IF EXISTS `score`;

CREATE TABLE `score` (
  `id` int(11) DEFAULT NULL,
  `Cno` int(11) DEFAULT NULL,
  `Score` int(11) DEFAULT NULL,
  `Credit` int(11) DEFAULT NULL,
  KEY `id` (`id`),
  KEY `Cno` (`Cno`),
  CONSTRAINT `score_ibfk_1` FOREIGN KEY (`id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `score_ibfk_2` FOREIGN KEY (`Cno`) REFERENCES `course` (`Cno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `score` */

insert  into `score`(`id`,`Cno`,`Score`,`Credit`) values (1,1,120,5),(2,1,130,5),(3,1,135,5),(5,1,NULL,0),(6,1,35,0),(7,1,NULL,0),(8,1,NULL,0),(9,1,NULL,0),(10,1,NULL,0),(11,1,NULL,0),(13,1,NULL,0),(14,1,NULL,0),(15,1,NULL,0),(16,1,125,5),(18,1,125,5),(20,1,120,5),(21,1,95,5),(22,1,NULL,0),(45,1,NULL,0),(46,1,NULL,0),(47,1,NULL,0),(48,1,NULL,0),(49,1,NULL,0),(51,1,NULL,0),(54,1,NULL,0),(55,1,NULL,0),(56,1,NULL,0),(63,1,NULL,0),(66,1,NULL,0),(67,1,NULL,0),(69,1,NULL,0),(71,1,NULL,0),(72,1,NULL,0),(74,1,90,0),(77,1,NULL,0),(82,1,NULL,0),(1,2,132,5),(2,2,NULL,0),(3,2,132,5),(5,2,NULL,0),(6,2,140,5),(7,2,95,5),(8,2,NULL,0),(9,2,NULL,0),(10,2,130,5),(11,2,NULL,0),(13,2,NULL,0),(14,2,NULL,0),(15,2,NULL,0),(16,2,130,5),(18,2,120,5),(20,2,NULL,0),(21,2,95,5),(22,2,130,5),(45,2,NULL,0),(46,2,126,5),(47,2,NULL,0),(48,2,NULL,0),(49,2,NULL,0),(51,2,NULL,0),(54,2,NULL,0),(55,2,NULL,0),(56,2,NULL,0),(63,2,NULL,0),(66,2,NULL,0),(67,2,NULL,0),(69,2,NULL,0),(71,2,NULL,0),(72,2,150,5),(74,2,NULL,0),(77,2,NULL,0),(82,2,NULL,0),(1,3,NULL,2),(2,3,NULL,0),(3,3,NULL,0),(5,3,NULL,0),(6,3,130,5),(7,3,115,5),(8,3,NULL,0),(9,3,NULL,0),(10,3,65,0),(11,3,NULL,0),(13,3,NULL,0),(14,3,NULL,0),(15,3,NULL,0),(16,3,110,5),(18,3,NULL,0),(20,3,NULL,0),(21,3,135,5),(22,3,12,0),(45,3,NULL,0),(46,3,NULL,0),(47,3,NULL,0),(48,3,NULL,0),(49,3,NULL,0),(51,3,NULL,0),(54,3,NULL,0),(55,3,NULL,0),(56,3,NULL,0),(63,3,NULL,0),(66,3,NULL,0),(67,3,NULL,0),(69,3,NULL,0),(71,3,NULL,0),(72,3,NULL,0),(74,3,89,0),(77,3,NULL,0),(82,3,NULL,0),(1,4,NULL,2),(2,4,130,4),(3,4,80,0),(5,4,NULL,0),(6,4,NULL,0),(7,4,98,4),(8,4,NULL,0),(9,4,NULL,0),(10,4,NULL,0),(11,4,NULL,0),(13,4,NULL,0),(14,4,NULL,0),(15,4,NULL,0),(16,4,80,0),(18,4,NULL,0),(20,4,NULL,0),(21,4,70,0),(22,4,NULL,0),(45,4,NULL,0),(46,4,NULL,0),(47,4,NULL,0),(48,4,NULL,0),(49,4,NULL,0),(51,4,NULL,0),(54,4,NULL,0),(55,4,NULL,0),(56,4,NULL,0),(63,4,NULL,0),(66,4,NULL,0),(67,4,NULL,0),(69,4,NULL,0),(71,4,NULL,0),(72,4,95,4),(74,4,9,0),(77,4,NULL,0),(82,4,NULL,0),(78,1,NULL,0),(78,2,NULL,0),(78,3,NULL,0),(78,4,NULL,0),(79,1,NULL,0),(79,2,NULL,0),(79,3,NULL,0),(79,4,NULL,0),(80,1,NULL,0),(80,2,NULL,0),(80,3,NULL,0),(80,4,NULL,0),(81,1,NULL,0),(81,2,NULL,0),(81,3,NULL,0),(81,4,NULL,0),(110,1,90,0),(110,2,NULL,0),(110,3,9,0),(110,4,120,4);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stuName` varchar(60) DEFAULT NULL,
  `stuGrade` varchar(60) DEFAULT NULL,
  `stuClass` int(11) DEFAULT NULL,
  `mobile` varchar(60) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`id`,`stuName`,`stuGrade`,`stuClass`,`mobile`,`email`) values (1,'LGB','高三',17,'15625178801','Eyetide@live.com'),(2,'林清玄','高三',29,'15020516458','test@qq.com'),(3,'陈颖凡','高三',12,'13080008005','chenyf@qq.com'),(5,'黄狗','初三',12,'13245784954','1531@jj.com'),(6,'是朕','高三',40,'',''),(7,'林景新','高三',17,'','12416@qq.com'),(8,'克里斯汀斯图尔特','高三',20,'',''),(9,'雅各布','高三',12,'',''),(10,'奇葩一只','初一',12,'',''),(11,'杀马特','六年级',2,'13245687894',''),(13,'艾斯比','高一',12,'',''),(14,'老子','高三',13,'',''),(15,'艾斯比','高二',35,'13024659845',''),(16,'颖颖佳','高三',16,'15645687865',''),(18,'颖颖颖佳','高三',13,'',''),(20,'呵呵哒','高二',12,'',''),(21,'刘可怕','高二',20,'15625178846',''),(22,'张课堂','高一',12,'',''),(45,'张一二十','高一',56,'',''),(46,'罗宇峰','高二',17,'',''),(47,'橙汁','高二',18,'',''),(48,'呵呵','高二',19,'',''),(49,'范增','高二',18,'',''),(51,'呵你妹','高二',18,'18999999966',''),(54,'呵你大爷','高三',5,'','henidaye@henidaye.dddd'),(55,'何里活','高二',25,'13254687952','helihuo@qq.com'),(56,'麒麟臂','高二',1,'13025151345','qilingbi@qq.com'),(63,'张全蛋','高二',18,'','zhangquandan@163.com'),(66,'呵呵','高二',18,'',''),(67,'大鱼海棠','六年级',18,'',''),(69,'呵呵','高二',18,'',''),(71,'呵呵哒','高二',18,'',''),(72,'呵呵','高二',18,'',''),(74,'狗蛋','高二',18,'',''),(77,'阿尼玛','高一',17,NULL,NULL),(78,'王二小','高一',20,'',''),(79,'狗剩','初一',12,'13054879878',''),(80,'王尼玛','高一',30,'',''),(81,'黑迪','高三',25,'13054879454',''),(82,'何仙姑','高二',45,'',''),(110,'GB','一年级',3,'','');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `identity` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`user`,`password`,`identity`) values (21,'1241616558','1241616558',0),(22,'124161','124161',1),(29,'hahaha','hahaha',1),(30,'gbgb','gb1234',1);

/* Procedure structure for procedure `addCourse` */

/*!50003 DROP PROCEDURE IF EXISTS  `addCourse` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `addCourse`(in con int,in Cname varchar(30),in credit int)
BEGIN
	
	declare sid int default 0;
	declare done int default 0;  
	declare cur cursor for select id from student;
	declare continue handler for not found set done=1;
	
	INSERT INTO course VALUES(con,Cname,credit);
	open cur;
	
	my : loop
		fetch cur into sid;
		
		if done = 1 then   
			leave my;  
		else INSERT INTO score VALUES(sid,con,NULL,0);
		end if;
		
	end loop my;
	close cur;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `addStu` */

/*!50003 DROP PROCEDURE IF EXISTS  `addStu` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `addStu`(in id int,in stuName varchar(60),in stuGrade varchar(60),in stuClass int,in mobile varchar(60),in email varchar(60))
begin
	    
	    declare icno int default 0;
	    DECLARE done INT DEFAULT 0;  
	    DECLARE cur CURSOR FOR SELECT Cno FROM course;
	    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done=1;
	    	    
	    INSERT INTO student VALUES(id,stuName,stuGrade,stuClass,mobile,email);
            
	    OPEN cur;
		my : LOOP
			FETCH cur INTO icno;
			
			IF done = 1 THEN   
				LEAVE my;  
			ELSE INSERT INTO score VALUES(id,icno,NULL,0);
			END IF;
		END LOOP my;
	    CLOSE cur;
	    
	end */$$
DELIMITER ;

/* Procedure structure for procedure `updateCourse` */

/*!50003 DROP PROCEDURE IF EXISTS  `updateCourse` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `updateCourse`(in cno int,in cname varchar(30),in cre int)
BEGIN
	DECLARE sc INT DEFAULT 0;
	declare i int default 0;
	DECLARE done INT DEFAULT 0;  
	DECLARE cur CURSOR FOR SELECT score,id FROM score WHERE score.`Cno` = cno;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done=1;
		
	UPDATE course SET Cname = cname , Ccredit = cre WHERE course.Cno = cno;
	
	OPEN cur;
		m : LOOP
			FETCH cur INTO sc,i;
			
			IF done = 1 THEN   
				LEAVE m;  
			ELSE if sc >= 90 then
				UPDATE Score SET Score.`Credit` = cre where Score.`id` = i and Score.`Cno` = cno;
			end IF;
			end if;
		end loop m;
	CLOSE cur;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `updateScore` */

/*!50003 DROP PROCEDURE IF EXISTS  `updateScore` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `updateScore`(in s int , in i int , in c int)
BEGIN
	UPDATE Score SET score = s WHERE id = i AND Cno = c;
	if s > 90 then 
		update Score set Credit = (SELECT Ccredit FROM course WHERE course.`Cno` = c GROUP BY course.`Cno`) where id = i AND Cno = c;
	else 
		UPDATE Score SET Credit = 0 WHERE id = i AND Cno = c;
	end if;
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
