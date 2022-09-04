-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author` (
  `author_id` int NOT NULL AUTO_INCREMENT COMMENT '作者id',
  `author_name` varchar(64) NOT NULL COMMENT '作者姓名',
  `author_sex` int DEFAULT '2' COMMENT '作者性别，0为女，1为男，2为保密，默认为2',
  `author_age` int DEFAULT '42' COMMENT '作者年龄，若作者已经去世则为其去世时年龄，无确切年龄者默认为42',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除标志，0为未删除、1为已删除，默认为0',
  PRIMARY KEY (`author_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1030 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='作者信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'卡尔·马克思',1,64,'2022-09-01 08:00:00',NULL,_binary '\0'),(2,'弗里德里希·恩格斯',1,74,'2022-09-01 08:00:00',NULL,_binary '\0'),(3,'列宁/弗拉基米尔·伊里奇·乌里扬诺夫',1,53,'2022-09-01 08:00:00',NULL,_binary '\0'),(4,'约瑟夫·维萨里奥诺维奇·斯大林',1,74,'2022-09-01 08:00:00',NULL,_binary '\0'),(5,'毛泽东',1,82,'2022-09-01 08:00:00',NULL,_binary '\0'),(6,'托马斯·皮凯蒂',1,51,'2022-09-01 08:00:00',NULL,_binary '\0'),(7,'柏拉图',1,73,'2022-09-01 08:00:00',NULL,_binary '\0'),(8,'希波的奥古斯丁',1,75,'2022-09-01 08:00:00',NULL,_binary '\0'),(9,'伊曼努尔·康德',1,79,'2022-09-01 08:00:00',NULL,_binary '\0'),(10,'弗里德里希·尼采',1,55,'2022-09-01 08:00:00',NULL,_binary '\0'),(11,'让-保罗·萨特',1,74,'2022-09-01 08:00:00',NULL,_binary '\0'),(12,'温铁军',1,70,'2022-09-01 08:00:00',NULL,_binary '\0'),(13,'孟德斯鸠/夏尔·路易·德·塞孔达',1,66,'2022-09-01 08:00:00',NULL,_binary '\0'),(14,'尼科洛·迪·贝尔纳多·代·马基雅维利',1,58,'2022-09-01 08:00:00',NULL,_binary '\0'),(15,'卡尔·菲利普·戈特弗里德·冯·克劳塞维茨',1,51,'2022-09-01 08:00:00',NULL,_binary '\0'),(16,'亚当·斯密',1,67,'2022-09-01 08:00:00',NULL,_binary '\0'),(17,'约翰·梅纳德·凯恩斯',1,62,'2022-09-01 08:00:00',NULL,_binary '\0'),(18,'让-雅克·卢梭',1,66,'2022-09-01 08:00:00',NULL,_binary '\0'),(19,'米歇尔·福柯',1,57,'2022-09-01 08:00:00',NULL,_binary '\0'),(20,'费奥多尔·米哈伊洛维奇·陀思妥耶夫斯基',1,59,'2022-09-01 08:00:00',NULL,_binary '\0'),(21,'若丁·萨达',1,70,'2022-09-01 08:00:00',NULL,_binary '\0'),(22,'克林顿·理查德·道金斯',1,81,'2022-09-01 08:00:00',NULL,_binary '\0'),(23,'居伊·德波',1,62,'2022-09-01 08:00:00',NULL,_binary '\0'),(24,'克劳狄乌斯·托勒密',1,70,'2022-09-01 08:00:00',NULL,_binary '\0'),(25,'维克多·马里·雨果',1,83,'2022-09-01 08:00:00',NULL,_binary '\0'),(26,'弗迪南·德·索绪尔',1,55,'2022-09-01 08:00:00',NULL,_binary '\0'),(27,'许慎',1,94,'2022-09-01 08:00:00',NULL,_binary '\0'),(28,'尼古拉·阿列克谢耶维奇·奥斯特洛夫斯基',1,32,'2022-09-01 08:00:00',NULL,_binary '\0'),(29,'鲁迅',1,55,'2022-09-01 08:00:00',NULL,_binary '\0'),(30,'列夫·尼古拉耶维奇·托尔斯泰',1,82,'2022-09-01 08:00:00',NULL,_binary '\0'),(31,'曹雪芹',1,47,'2022-09-01 08:00:00',NULL,_binary '\0'),(32,'查尔斯·约翰·赫芬姆·狄更斯',1,58,'2022-09-01 08:00:00',NULL,_binary '\0'),(33,'米格尔·德·塞万提斯·萨韦德拉',1,68,'2022-09-01 08:00:00',NULL,_binary '\0'),(34,'夏洛特·勃朗特',0,38,'2022-09-01 08:00:00',NULL,_binary '\0'),(35,'简·奥斯汀',0,41,'2022-09-01 08:00:00',NULL,_binary '\0'),(36,'紫式部',0,41,'2022-09-01 08:00:00',NULL,_binary '\0'),(37,'阿加莎·克里斯蒂',0,85,'2022-09-01 08:00:00',NULL,_binary '\0'),(38,'傅雷',1,58,'2022-09-01 08:00:00',NULL,_binary '\0'),(39,'朱光潜',1,88,'2022-09-01 08:00:00',NULL,_binary '\0'),(40,'托克维尔/亚历克西-夏尔-亨利·克莱雷尔',1,53,'2022-09-01 08:00:00',NULL,_binary '\0'),(41,'宋应星',1,78,'2022-09-01 08:00:00',NULL,_binary '\0'),(42,'斯蒂芬·威廉·霍金',1,76,'2022-09-01 08:00:00',NULL,_binary '\0'),(43,'埃尔温·鲁道夫·约瑟夫·亚历山大·薛定谔',1,73,'2022-09-01 08:00:00',NULL,_binary '\0'),(44,'威廉·布莱恩·阿瑟',1,76,'2022-09-01 08:00:00',NULL,_binary '\0'),(45,'罗伊·波特',1,55,'2022-09-01 08:00:00',NULL,_binary '\0'),(46,'蕾切尔·路易丝·卡森',0,56,'2022-09-01 08:00:00',NULL,_binary '\0'),(47,'威廉·乌克斯',1,71,'2022-09-01 08:00:00',NULL,_binary '\0'),(48,'霍华德·菲利普斯·洛夫克拉夫特',1,46,'2022-09-01 08:00:00',NULL,_binary '\0'),(49,'刘慈欣',1,58,'2022-09-01 08:00:00',NULL,_binary '\0'),(50,'叶永烈',1,79,'2022-09-01 08:00:00',NULL,_binary '\0'),(52,'佚名',2,42,'1970-01-01 00:00:00',NULL,_binary '\0'),(1024,'null',2,42,'2006-01-02 15:04:05',NULL,_binary '\1');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `book_id` int NOT NULL AUTO_INCREMENT COMMENT '该书id',
  `book_name` varchar(64) NOT NULL COMMENT '该书书名',
  `author_id` int NOT NULL COMMENT '该书作者id',
  `IBSN` varchar(32) DEFAULT 'IBSN_is_not_exist' COMMENT '该书IBSN号，默认为[IBSN_is_not_exist]',
  `publishing_company_id` int DEFAULT '12' COMMENT '出版社id为12时代表无出版社，默认为12',
  `tag_id` int DEFAULT NULL COMMENT '该书类型标签id',
  `quantity` int DEFAULT '1' COMMENT '该书库存数量，默认为1',
  `price` int DEFAULT '0' COMMENT '单价为0时代表该书为公益书籍，默认为0',
  `is_being_borrowed` int NOT NULL DEFAULT '0' COMMENT '书籍是否外借，0为否、>=1为是，默认为0，当值大于库存数量时无法借出',
  `publication_date` date DEFAULT '1900-01-01' COMMENT '书籍出版日期，默认为''01-1月-1900''',
  `joint_author_table_id` int DEFAULT '0' COMMENT '该书是否存在共同作者，0为否、>=1则为共同作者表id号，默认为0',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除标志，0为未删除、1为已删除，默认为0',
  PRIMARY KEY (`book_id`),
  UNIQUE KEY `single_same_authorId&bookName` (`book_name`,`author_id`),
  KEY `books_FK_author_id` (`author_id`),
  KEY `books_FK_publishing_company_id` (`publishing_company_id`),
  KEY `books_FK_tag_id` (`tag_id`),
  KEY `books_FK_joint_author_table_id` (`joint_author_table_id`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `author` (`author_id`),
  CONSTRAINT `book_ibfk_2` FOREIGN KEY (`publishing_company_id`) REFERENCES `publishing_company` (`publishing_company_id`),
  CONSTRAINT `book_ibfk_3` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`tag_id`),
  CONSTRAINT `book_ibfk_4` FOREIGN KEY (`joint_author_table_id`) REFERENCES `joint_author_table` (`joint_author_table_id`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='书籍信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'资本论',1,'978-7-0101-4048-3',1,1,1,0,0,'1867-01-01',1,'2022-09-01 08:00:00',NULL,_binary '\0'),(2,'政治经济学批判',1,'978-7-5117-4152-3',6,1,1,0,0,'1859-06-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(3,'剩余价值理论',1,'978-7-5115-0135-6',12,6,1,0,0,'1862-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(4,'反杜林论',2,'978-7-5117-4157-8',6,1,1,0,0,'1885-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(5,'家庭、私有制和国家的起源',2,'978-7-01-018876-8',1,1,1,0,0,'1884-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(6,'进一步，退两步',3,'978-7-01-018215-5',1,1,1,0,0,'1904-05-19',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(7,'帝国主义是资本主义的最高阶段',3,'978-7-01-015651-4',1,1,1,0,0,'1917-09-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(8,'共产主义运动中的“左派”幼稚病',3,'978-7-01-017001-5',1,1,1,0,0,'1920-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(9,'论辩证唯物主义和历史唯物主义',4,'IBSN_is_not_exist',1,1,1,0,0,'1938-10-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(10,'毛泽东选集',5,'978-7-01-018112-7',1,1,1,0,0,'1951-10-12',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(11,'建国以来毛泽东文稿',5,'7-5073-0061-7',12,1,1,0,0,'1987-11-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(12,'21世纪资本论',6,'978-7-5086-4725-8',5,6,1,0,0,'2013-08-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(13,'理想国',7,'978-7-100-18643-8',11,2,1,0,0,'1900-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(14,'忏悔录',8,'978-7-100-07860-3',11,2,1,0,0,'1900-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(15,'纯粹理性批判',9,'978-7-01-016735-0',1,2,1,0,0,'1781-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(16,'实践理性批判',9,'978-7-100-02699-4',11,2,1,0,0,'1788-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(17,'判断力批判',9,'978-7-100-01669-8',11,2,1,0,0,'1790-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(18,'查拉图斯特拉如是说',10,'978-7-108-05097-7',10,2,1,0,0,'1883-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(19,'存在与虚无',11,'978-7-108-05098-4',10,2,1,0,0,'1943-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(20,'八次危机：中国的真实经验1949—2009',12,'978-7-5060-5557-4',12,6,1,0,0,'2013-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(21,'中国农业的生态化转型',12,'978-7-109-22484-1',12,17,1,0,0,'2017-02-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(22,'论法的精神',13,'978-7-100-09013-1',11,4,1,0,0,'1748-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(23,'君主论',14,'978-7-5117-3253-8',6,4,1,0,0,'1532-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(24,'战争论',15,'978-7-100-14558-9',11,5,1,0,0,'1832-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(25,'国富论',16,'978-7-308-16167-1',12,6,1,0,0,'1776-03-09',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(26,'就业、利息和货币通论',17,'978-7-100-06164-3',11,6,1,0,0,'1936-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(27,'货币论',17,'978-7-212-05576-9',11,6,1,0,0,'1930-10-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(28,'植物学通信',18,'978-7-301-18191-1',12,15,1,0,0,'1785-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(29,'论人类不平等的起源与基础',18,'978-7-5327-8600-8',12,3,1,0,0,'1755-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(30,'社会契约论',18,'978-7-100-12208-5',11,4,1,0,0,'1762-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(31,'爱弥儿：论教育',18,'978-7-100-08030-9',11,7,1,0,0,'1762-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(32,'论语言的起源',18,'978-7-100-18805-0',11,8,1,0,0,'1781-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(33,'性史',19,'7-225-01633-4',12,3,1,0,0,'1976-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(34,'临床医学的诞生',19,'978-7-5447-1578-2',12,16,1,0,0,'1963-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(35,'规训与惩罚',19,'978-7-108-04148-7',10,2,1,0,0,'1975-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(36,'罪与罚',20,'978-7-02-012995-9',3,9,1,0,0,'1866-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(37,'卡拉马佐夫兄弟',20,'978-7-02-007158-6',3,9,1,0,0,'1878-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(38,'文化研究介绍',21,'978-1-4058-5843-4',12,7,1,0,0,'2008-03-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(39,'自私的基因',22,'978-7-5086-9327-9',5,15,1,0,0,'1976-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(40,'景观社会',23,'978-7-305-17529-9',12,3,1,0,0,'1967-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(41,'至大论',24,'IBSN_is_not_exist',12,14,1,0,0,'1900-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(42,'地理学指南',24,'IBSN_is_not_exist',12,14,1,0,0,'1900-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(43,'悲惨世界',25,'978-7-02-010434-5',3,9,1,0,0,'1862-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(44,'九三年',25,'978-7-02-016611-4',3,9,1,0,0,'1874-02-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(45,'巴黎圣母院',25,'978-7-02-007073-2',3,9,1,0,0,'1831-01-14',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(46,'普通语言学教程',26,'978-7-100-14907-5',3,8,1,0,0,'1916-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(47,'说文解字',27,'978-7-101-00260-7',2,8,1,0,0,'1900-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(48,'钢铁是怎样炼成的',28,'978-7-5455-2142-9',12,9,1,0,0,'1933-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(49,'狂人日记',29,'978-7-5208-0224-6',12,9,1,0,0,'1918-05-15',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(50,'故事新编',29,'978-7-02-011026-1',3,9,1,0,0,'1933-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(51,'华盖集',29,'978-7-02-015266-7',3,9,1,0,0,'1926-06-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(52,'战争与和平',30,'978-7-02-010274-7',3,9,1,0,0,'1865-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(53,'安娜·卡列尼娜',30,'978-7-5001-2435-1',3,9,1,0,0,'1877-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(54,'复活',30,'978-7-5449-1746-9',3,9,1,0,0,'1899-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(55,'红楼梦',31,'978-7-101-06730-9',2,9,1,0,0,'1792-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(56,'雾都孤儿',32,'978-7-302-19426-2',9,9,1,0,0,'1838-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(57,'双城记',32,'978-7-5063-7980-9',4,9,1,0,0,'1859-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(58,'堂吉诃德',33,'978-7-5063-8315-8',4,9,1,0,0,'1605-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(59,'简·爱',34,'978-7-5063-8054-6',4,9,1,0,0,'1847-10-16',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(60,'傲慢与偏见',35,'978-7-302-19001-1',9,9,1,0,0,'1813-01-28',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(61,'源氏物语',36,'978-7-5063-7243-5',4,9,1,0,0,'1010-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(62,'东方快车谋杀案',37,'978-7-02-016870-5',3,9,1,0,0,'1934-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(63,'ABC谋杀案',37,'978-7-02-007361-0',3,9,1,0,0,'1936-01-06',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(64,'无人生还',37,'978-7-02-006539-4',3,9,1,0,0,'1939-11-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(65,'傅雷家书',38,'978-7-108-06265-9',10,7,1,0,0,'1981-08-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(66,'谈美',39,'978-7-101-07524-3',2,10,1,0,0,'1932-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(67,'论美国的民主',40,'978-7-5117-1889-1',6,4,1,0,0,'1835-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(68,'旧制度与大革命',40,'978-7-5117-1886-0',6,11,1,0,0,'1856-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(69,'天工开物',41,'978-7-101-15182-4',2,12,1,0,0,'1637-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(70,'时间简史',42,'978-7-5357-3230-9',12,14,1,0,0,'1988-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(71,'果壳中的宇宙',42,'978-7-5357-3359-7',12,14,1,0,0,'2001-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(72,'大设计',42,'978-7-5357-6544-4',12,22,1,0,0,'2010-09-07',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(73,'生命是什么',43,'978-7-100-16430-6',11,15,1,0,0,'1944-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(74,'技术的本质',44,'978-7-213-05998-8',12,12,1,0,0,'2018-06-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(75,'剑桥医学史',45,'978-7-5447-8592-1',12,16,1,0,0,'2000-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(76,'寂静的春天',46,'978-7-03-019654-5',8,21,1,0,0,'1962-09-27',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(77,'茶叶全书',47,'978-7-5060-4017-4',12,17,1,0,0,'1935-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(78,'克苏鲁的呼唤',48,'978-7-02-011969-1',3,9,1,0,0,'1928-02-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(79,'疯狂山脉',48,'978-7-5699-3172-3',12,9,1,0,0,'1936-02-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(80,'超越时间之影',48,'978-7-5699-3175-4',12,9,1,0,0,'1936-06-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(81,'超新星纪元',49,'978-7-229-00476-7',5,9,1,0,0,'1991-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(82,'球状闪电',49,'978-7-5364-9047-5',12,9,1,0,0,'2005-06-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(83,'三体',49,'978-7-229-16692-2',12,9,1,0,0,'2008-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(84,'小灵通漫游未来',50,'978-7-5212-0044-7',4,9,1,0,0,'1978-08-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(85,'毛泽东重返人间',50,'978-957-56-9842-3',12,9,1,0,0,'2002-11-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(86,'优势火力学说',52,'IBSN_is_not_exist',12,5,1,0,0,'2022-05-20',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(87,'现代计算机艺术',52,'IBSN_is_not_exist',12,10,1,0,0,'2022-05-20',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(88,'科学出版60年',52,'978-7-03-042147-0',8,7,1,0,0,'2014-10-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(89,'中国科学技术史',52,'978-7-03-049360-6',8,12,1,0,0,'2016-07-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(90,'革命的艺术与艺术的革命',52,'IBSN_is_not_exist',12,10,1,0,0,'2022-05-20',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(91,'从地理看历史',52,'IBSN_is_not_exist',9,11,1,0,0,'2022-05-20',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(92,'从历史看地理',52,'IBSN_is_not_exist',9,11,1,0,0,'2022-05-20',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(93,'理论力学',52,'7-111-08145-5',7,13,1,0,0,'2000-08-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(94,'化学',52,'7-111-06199-3',7,13,1,0,0,'2000-04-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(95,'大学物理学第一册',52,'7-111-06268-X',7,13,1,0,0,'1998-07-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(96,'方法与技术',52,'978-7-03-028085-5',8,16,1,0,0,'2010-07-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(97,'节水农业',52,'978-7-302-21824-1',9,17,1,0,0,'2009-12-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(98,'人工智能',52,'978-7-111-38455-7',8,18,1,0,0,'2012-07-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(99,'自动控制理论',52,'7-111-02043-X',8,18,1,0,0,'2000-05-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(100,'Oracle Designer信息系统开发',52,'7-111-07298-7',8,18,1,0,0,'1999-05-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(101,'统计学导论',52,'978-7-03-035886-8',8,3,1,0,0,'2012-11-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(102,'大学生军事理论课教程',52,'978-7-03-035473-0',8,5,1,0,0,'2012-08-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(103,'国防经济学前沿专题',52,'978-7-5058-9547-8',12,5,1,0,0,'2010-08-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(104,'汽车概论',52,'978-7-5640-3299-9',12,19,1,0,0,'2010-06-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(105,'公路工程定额编制与管理',52,'978-7-114-08510-9',12,19,1,0,0,'2010-06-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(106,'自动驾驶汽车平台技术基础',52,'978-7-302-54973-4',9,19,1,0,0,'2020-03-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(107,'深空探测无线电地基导航的统计信号处理方法',52,'978-7-302-56642-7',8,20,1,0,0,'2020-10-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(108,'航天器推进系统及其应用',52,'978-7-5612-2672-8',12,20,1,0,0,'2009-10-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(109,'大型客机计算流体力学应用与发展',52,'978-7-313-06115-7',12,20,1,0,0,'2009-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(110,'环境工程概论',52,'978-7-03-031242-6',8,21,1,0,0,'2011-06-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(111,'环境多样性原理',52,'978-7-03-026604-0',8,21,1,0,0,'2010-02-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(112,'国学人文导论',52,'978-7-100-09186-2',11,22,1,0,0,'2012-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0'),(113,'十万个为什么',52,'IBSN_is_not_exist',12,22,1,0,0,'1963-01-01',0,'2022-09-01 08:00:00',NULL,_binary '\0');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_borrow_table`
--

DROP TABLE IF EXISTS `book_borrow_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_borrow_table` (
  `books_borrow_table_id` int NOT NULL AUTO_INCREMENT COMMENT '借书表id',
  `reader_id` int NOT NULL COMMENT '借书者id',
  `book_id` int NOT NULL COMMENT '所借书id',
  `is_borrowing` int DEFAULT '1' COMMENT '书籍是否仍处于借用中，0为否、1为是，默认为1',
  `borrow_time` datetime DEFAULT '1900-01-01 08:00:00' COMMENT '书籍外借时间，默认为1900-01-01 08:00:00',
  `back_time` datetime DEFAULT '2022-05-11 04:00:00' COMMENT '书籍归还时间，默认为2022-05-11 04:00:00',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除标志，0为未删除、1为已删除，默认为0',
  PRIMARY KEY (`books_borrow_table_id`),
  KEY `books_borrow_tables_FK_reader_id` (`reader_id`),
  KEY `books_borrow_tables_FK_book_id` (`book_id`),
  CONSTRAINT `book_borrow_table_ibfk_1` FOREIGN KEY (`reader_id`) REFERENCES `reader` (`reader_id`),
  CONSTRAINT `book_borrow_table_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='借书表信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_borrow_table`
--

LOCK TABLES `book_borrow_table` WRITE;
/*!40000 ALTER TABLE `book_borrow_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_borrow_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `joint_author_table`
--

DROP TABLE IF EXISTS `joint_author_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `joint_author_table` (
  `joint_author_table_id` int NOT NULL COMMENT '该书是否存在共同作者，0为否、>=1则为共同作者表id号',
  `table_id` int NOT NULL COMMENT '存在共同作者的书的共同作者表id',
  `author_id` int NOT NULL COMMENT '相同table_id的作者为该joint_author_table_id对应的书的共同作者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除标志，0为未删除、1为已删除，默认为0',
  PRIMARY KEY (`joint_author_table_id`),
  KEY `joint_author_tables_FK` (`author_id`),
  CONSTRAINT `joint_author_tables_FK` FOREIGN KEY (`author_id`) REFERENCES `author` (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='共同作者表信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `joint_author_table`
--

LOCK TABLES `joint_author_table` WRITE;
/*!40000 ALTER TABLE `joint_author_table` DISABLE KEYS */;
INSERT INTO `joint_author_table` VALUES (0,0,1024,'2022-09-01 08:00:00',NULL,_binary '\0'),(1,1,1,'2022-09-01 08:00:00',NULL,_binary '\0'),(2,1,2,'2022-09-01 08:00:00',NULL,_binary '\0');
/*!40000 ALTER TABLE `joint_author_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publishing_company`
--

DROP TABLE IF EXISTS `publishing_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publishing_company` (
  `publishing_company_id` int NOT NULL AUTO_INCREMENT COMMENT '出版社id',
  `publishing_company_name` varchar(64) NOT NULL COMMENT '出版社名称',
  `publishing_company_telephone_number` varchar(128) DEFAULT 'publishing_company_telephone_number_is_not_exist' COMMENT '出版社电话号',
  `publishing_company_address` varchar(128) DEFAULT 'publishing_company_address_is_not_exist' COMMENT '出版社地址',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除标志，0为未删除、1为已删除，默认为0',
  PRIMARY KEY (`publishing_company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='出版社信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publishing_company`
--

LOCK TABLES `publishing_company` WRITE;
/*!40000 ALTER TABLE `publishing_company` DISABLE KEYS */;
INSERT INTO `publishing_company` VALUES (1,'人民出版社','19210901','北京市东城区朝阳门内大街166号','2022-09-01 08:00:00',NULL,_binary '\0'),(2,'中华书局','19120101','北京市丰台区太平桥西里38号','2022-09-01 08:00:00',NULL,_binary '\0'),(3,'人民文学出版社','19510300','北京市东城区朝阳门内大街166号','2022-09-01 08:00:00',NULL,_binary '\0'),(4,'作家出版社','19510000','北京市朝阳区农展馆南里10号办公楼11-13层','2022-09-01 08:00:00',NULL,_binary '\0'),(5,'中信出版社','19880000','北京市朝阳区惠新东街甲4号8-10层','2022-09-01 08:00:00',NULL,_binary '\0'),(6,'中央编译出版社','19930900','北京市西单西斜街36号','2022-09-01 08:00:00',NULL,_binary '\0'),(7,'机械工业出版社','19520000','北京市西城区百万庄大街22号院3号楼1-9层','2022-09-01 08:00:00',NULL,_binary '\0'),(8,'科学出版社','19540800','北京市西城区西直门南大街16号','2022-09-01 08:00:00',NULL,_binary '\0'),(9,'清华大学出版社','19800600','北京市海淀区清华大学学研大厦A座6层','2022-09-01 08:00:00',NULL,_binary '\0'),(10,'三联书店','19481000','北京市美术馆东街22号','2022-09-01 08:00:00',NULL,_binary '\0'),(11,'商务印书馆','18970211','北京市王府井大街36号','2022-09-01 08:00:00',NULL,_binary '\0'),(12,'702出版社','20220511','羊城帝江区北荒学府岱宗区8栋702','2022-09-01 08:00:00',NULL,_binary '\0');
/*!40000 ALTER TABLE `publishing_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reader`
--

DROP TABLE IF EXISTS `reader`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reader` (
  `reader_id` int NOT NULL AUTO_INCREMENT COMMENT '读者id',
  `reader_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '读者姓名',
  `reader_sex` int DEFAULT '2' COMMENT '读者性别，0为女，1为男，2为保密，默认为2',
  `reader_age` int DEFAULT '18' COMMENT '读者年龄，默认为18',
  `saving` int DEFAULT '0' COMMENT '读者存款，默认为0',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除标志，0为未删除、1为已删除，默认为0',
  PRIMARY KEY (`reader_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='读者信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reader`
--

LOCK TABLES `reader` WRITE;
/*!40000 ALTER TABLE `reader` DISABLE KEYS */;
INSERT INTO `reader` VALUES (1,'xuuuyeee',1,20,627,'2022-09-01 08:00:00',NULL,_binary '\0'),(2,'HasturKiki',1,20,628,'2022-09-01 08:00:00',NULL,_binary '\0'),(3,'HolmesZᯤ⁶ᴳ',1,20,629,'2022-09-01 08:00:00',NULL,_binary '\0'),(4,'いほう',1,20,630,'2022-09-01 08:00:00',NULL,_binary '\0'),(5,'reader5',0,18,500,'2022-09-01 08:00:00',NULL,_binary '\0'),(6,'reader6',2,18,0,'2022-09-01 08:00:00',NULL,_binary '\0');
/*!40000 ALTER TABLE `reader` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag` (
  `tag_id` int NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `tag_name` varchar(64) NOT NULL COMMENT '标签名称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除标志，0为未删除、1为已删除，默认为0',
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='书籍标签';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'马恩列斯毛理论研究','2022-09-01 08:00:00',NULL,_binary '\0'),(2,'哲学、宗教','2022-09-01 08:00:00',NULL,_binary '\0'),(3,'社会科学总论','2022-09-01 08:00:00',NULL,_binary '\0'),(4,'政治、法律','2022-09-01 08:00:00',NULL,_binary '\0'),(5,'军事','2022-09-01 08:00:00',NULL,_binary '\0'),(6,'经济','2022-09-01 08:00:00',NULL,_binary '\0'),(7,'文化、科学、教育、体育','2022-09-01 08:00:00',NULL,_binary '\0'),(8,'语言、文字','2022-09-01 08:00:00',NULL,_binary '\0'),(9,'文学','2022-09-01 08:00:00',NULL,_binary '\0'),(10,'艺术','2022-09-01 08:00:00',NULL,_binary '\0'),(11,'历史、地理','2022-09-01 08:00:00',NULL,_binary '\0'),(12,'自然科学总论','2022-09-01 08:00:00',NULL,_binary '\0'),(13,'数理科学和化学','2022-09-01 08:00:00',NULL,_binary '\0'),(14,'天文学、地球科学','2022-09-01 08:00:00',NULL,_binary '\0'),(15,'生物科学','2022-09-01 08:00:00',NULL,_binary '\0'),(16,'医药、卫生','2022-09-01 08:00:00',NULL,_binary '\0'),(17,'农业科学','2022-09-01 08:00:00',NULL,_binary '\0'),(18,'工业技术','2022-09-01 08:00:00',NULL,_binary '\0'),(19,'交通运输','2022-09-01 08:00:00',NULL,_binary '\0'),(20,'航空、航天','2022-09-01 08:00:00',NULL,_binary '\0'),(21,'环境科学、劳动保护科学（安全科学）','2022-09-01 08:00:00',NULL,_binary '\0'),(22,'综合性图书','2022-09-01 08:00:00',NULL,_binary '\0');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'library'
--

--
-- Final view structure for view `newview`
--

/*!50001 DROP VIEW IF EXISTS `newview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `newview` AS select `tag`.`tag_id` AS `tag_id`,`tag`.`tag_name` AS `tag_name` from `tag` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `newview_1`
--

/*!50001 DROP VIEW IF EXISTS `newview_1`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `newview_1` AS select `r`.`reader_id` AS `reader_id`,`r`.`reader_name` AS `reader_name`,`r`.`reader_sex` AS `reader_sex`,`r`.`reader_age` AS `reader_age`,`r`.`saving` AS `saving`,`pc`.`publishing_company_id` AS `publishing_company_id`,`pc`.`publishing_company_name` AS `publishing_company_name`,`pc`.`publishing_company_telephone_number` AS `publishing_company_telephone_number`,`pc`.`publishing_company_address` AS `publishing_company_address` from (`reader` `r` join `publishing_company` `pc`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `newview_2`
--

/*!50001 DROP VIEW IF EXISTS `newview_2`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `newview_2` AS select `r`.`reader_id` AS `reader_id`,`r`.`reader_name` AS `reader_name`,`r`.`reader_sex` AS `reader_sex`,`r`.`reader_age` AS `reader_age`,`r`.`saving` AS `saving`,`pc`.`publishing_company_id` AS `publishing_company_id`,`pc`.`publishing_company_name` AS `publishing_company_name`,`pc`.`publishing_company_telephone_number` AS `publishing_company_telephone_number`,`pc`.`publishing_company_address` AS `publishing_company_address` from (`reader` `r` join `publishing_company` `pc`) where (`r`.`reader_id` = `pc`.`publishing_company_id`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `newview_3`
--

/*!50001 DROP VIEW IF EXISTS `newview_3`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `newview_3` AS select `b`.`book_id` AS `book_id`,`b`.`book_name` AS `book_name`,`a`.`author_name` AS `author_name`,`pc`.`publishing_company_name` AS `publishing_company_name` from ((`book` `b` join `author` `a`) join `publishing_company` `pc`) where ((`b`.`author_id` = `a`.`author_id`) and (`b`.`publishing_company_id` = `pc`.`publishing_company_id`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `newview_4`
--

/*!50001 DROP VIEW IF EXISTS `newview_4`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `newview_4` AS select `t`.`tag_id` AS `tag_id`,`t`.`tag_name` AS `tag_name`,`pc`.`publishing_company_id` AS `publishing_company_id`,`pc`.`publishing_company_name` AS `publishing_company_name`,`pc`.`publishing_company_telephone_number` AS `publishing_company_telephone_number`,`pc`.`publishing_company_address` AS `publishing_company_address` from (`tag` `t` join `publishing_company` `pc`) where (`t`.`tag_id` = `pc`.`publishing_company_id`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `newview_5`
--

/*!50001 DROP VIEW IF EXISTS `newview_5`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `newview_5` AS select `t`.`tag_id` AS `tag_id`,`t`.`tag_name` AS `tag_name`,`pc`.`publishing_company_id` AS `publishing_company_id`,`pc`.`publishing_company_name` AS `publishing_company_name`,`pc`.`publishing_company_telephone_number` AS `publishing_company_telephone_number`,`pc`.`publishing_company_address` AS `publishing_company_address` from (`tag` `t` left join `publishing_company` `pc` on((`t`.`tag_id` = `pc`.`publishing_company_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `newview_6`
--

/*!50001 DROP VIEW IF EXISTS `newview_6`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `newview_6` AS select `t`.`tag_id` AS `tag_id`,`t`.`tag_name` AS `tag_name`,`pc`.`publishing_company_id` AS `publishing_company_id`,`pc`.`publishing_company_name` AS `publishing_company_name`,`pc`.`publishing_company_telephone_number` AS `publishing_company_telephone_number`,`pc`.`publishing_company_address` AS `publishing_company_address` from (`tag` `t` left join `publishing_company` `pc` on((`t`.`tag_id` = `pc`.`publishing_company_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `newview_7`
--

/*!50001 DROP VIEW IF EXISTS `newview_7`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `newview_7` AS select `t`.`tag_id` AS `tag_id`,`t`.`tag_name` AS `tag_name`,`pc`.`publishing_company_id` AS `publishing_company_id`,`pc`.`publishing_company_name` AS `publishing_company_name`,`pc`.`publishing_company_telephone_number` AS `publishing_company_telephone_number`,`pc`.`publishing_company_address` AS `publishing_company_address` from (`publishing_company` `pc` left join `tag` `t` on((`t`.`tag_id` = `pc`.`publishing_company_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `newview_8`
--

/*!50001 DROP VIEW IF EXISTS `newview_8`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `newview_8` AS select `t`.`tag_id` AS `tag_id`,`t`.`tag_name` AS `tag_name`,`pc`.`publishing_company_id` AS `publishing_company_id`,`pc`.`publishing_company_name` AS `publishing_company_name`,`pc`.`publishing_company_telephone_number` AS `publishing_company_telephone_number`,`pc`.`publishing_company_address` AS `publishing_company_address` from (`publishing_company` `pc` left join `tag` `t` on((`t`.`tag_id` = `pc`.`publishing_company_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `newview_9`
--

/*!50001 DROP VIEW IF EXISTS `newview_9`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `newview_9` AS select `t`.`tag_id` AS `tag_id`,`t`.`tag_name` AS `tag_name`,`pc`.`publishing_company_id` AS `publishing_company_id`,`pc`.`publishing_company_name` AS `publishing_company_name`,`pc`.`publishing_company_telephone_number` AS `publishing_company_telephone_number`,`pc`.`publishing_company_address` AS `publishing_company_address` from (`tag` `t` join `publishing_company` `pc` on((`t`.`tag_id` = `pc`.`publishing_company_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-31 17:38:20
