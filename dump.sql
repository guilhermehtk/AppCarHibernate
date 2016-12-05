-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: appcarhibernate
-- ------------------------------------------------------
-- Server version	5.7.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `carro`
--

DROP TABLE IF EXISTS `carro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carro` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `ano` varchar(255) DEFAULT NULL,
  `chassi` varchar(255) DEFAULT NULL,
  `cor` varchar(255) DEFAULT NULL,
  `km` varchar(255) DEFAULT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `modelo` varchar(255) DEFAULT NULL,
  `obs` varchar(255) DEFAULT NULL,
  `placa` varchar(255) DEFAULT NULL,
  `dono_codigo` int(11) DEFAULT NULL,
  PRIMARY KEY (`cod`),
  KEY `FK_esspgov7dajikrc5ycuqe64od` (`dono_codigo`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carro`
--

LOCK TABLES `carro` WRITE;
/*!40000 ALTER TABLE `carro` DISABLE KEYS */;
INSERT INTO `carro` VALUES (1,'1221','1221','Branco ','21212','FIAT','Uno','Não há','SAS-2132',2),(2,'2016','120000','Preto  ','120000','Ford','KA','Não há','PXW-2165',3),(3,'1990','2500999','Vermelho ','2500999','JEEP','Outros','Rebaixado','MKV-7842',4),(4,'2001','12547800','Marrom/Bege ','12547800','FIAT','Uno','Não há','JUT-3524',5),(5,'2006','177000','Prata ','177000','Ford','KA','Não há','PPJ-6566',6),(6,'2013','2013','Prata ','200000','FIAT','Palio','Não há','OMH-0974',7),(7,'1995','450000','Vermelho ','450000','Ford','Escort Hobby','Não há','GPP-2733',8),(8,'2013','50000','Branco ','50000','GM - Chevrolet','Onix','Este é o carro do Pedro','PHM-9999',9),(9,'2012','12587000','Prata ','12587000','FIAT','Mobi','Carro do Gorazil\n','KJU-2520',10),(10,'1990','152000','Azul ','152000','Honda','Outros','Não há','JUJ-6523',11);
/*!40000 ALTER TABLE `carro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `codigo` int(11) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (2),(3),(4),(5),(6),(7),(8),(9),(10),(11);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endereco` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `bairro` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `rua` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cod`)
) ENGINE=MyISAM AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES (21,'Centro','35580-000','Formiga','Apart. A','365','Rua Sete de Setembro'),(36,'Nra. Lourdes','35570-000','Formiga','E','19','Prof. Francis'),(37,'Sao Lourenço','35570-000','Formiga','A','84','Antonio Jorge Rezende'),(38,'Centro','35570-000','Formiga','A','41','do Ifmg'),(39,'SAAE','35570-000','Formiga','A','656','do Saae'),(40,'Ouro Negro','35570-000','Formiga','Casa','255','Uruguai '),(41,'Lagoa','35570-000','Formiga','Casa','97','José do Couto'),(42,'Cidade Nova','35570-000','Formiga','Casa','24','Perdigão'),(43,'Bela Vista','35570-000','Formiga','Casa','55','Deoliinda Nogueira da Costa'),(44,'Rosário','35570-000','Formiga','A','55','Anildo gontijo '),(45,'Centro','35567-000','Formiga','A','387','Sete de Setembro'),(46,'Centro','35570-000','Formiga','A','2332','1Sete de Setembro');
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionario` (
  `codigo` int(11) NOT NULL,
  `login_cod` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_c0ngjlhef59t383jww3xb8207` (`login_cod`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (13,9);
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `data` datetime DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `funcionario_codigo` int(11) DEFAULT NULL,
  PRIMARY KEY (`cod`),
  KEY `FK_e2fxadc5hj7gsqqspjc3qxosx` (`funcionario_codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `senha` varchar(255) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cod`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (9,'$2a$10$VBSbs5D2TjILcOsQ/pE9../PG5Ah.c5vzDCTt9.Rhf1uDUL5wavFq','admin');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordemservico`
--

DROP TABLE IF EXISTS `ordemservico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordemservico` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `data` datetime DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `situacao` int(11) NOT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `carro_cod` int(11) DEFAULT NULL,
  `cliente_codigo` int(11) DEFAULT NULL,
  PRIMARY KEY (`cod`),
  KEY `FK_ig9a3018nn0bx5shc4iwxkic0` (`carro_cod`),
  KEY `FK_r2r02xc0tnp4v4kbyflxo46wt` (`cliente_codigo`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordemservico`
--

LOCK TABLES `ordemservico` WRITE;
/*!40000 ALTER TABLE `ordemservico` DISABLE KEYS */;
INSERT INTO `ordemservico` VALUES (1,'2016-07-28 23:59:51','Carro',1,'Orçamento',1,2),(2,'2016-11-18 09:24:53','Ordem de serviço',3,'Revisão',2,3),(3,'2016-11-18 09:28:19','',0,'Concerto',3,4),(4,'2016-11-18 09:29:51','',0,'Trocas',4,5),(5,'2016-11-18 09:32:17','Lanternagem no carro',0,'Lanternagem',5,6),(6,'2016-11-18 09:33:50','Pintura',0,'Pintura',6,7),(7,'2016-11-18 09:35:18','Manutenção do carro',0,'Manutenção',7,8),(8,'2016-11-18 09:36:11','Troca de óleo',0,'Lanternagem',8,9),(9,'2016-11-18 09:36:54','Revisão ',0,'Revisão',9,10),(10,'2016-11-18 09:39:05','23412',0,'Manutenção',10,11);
/*!40000 ALTER TABLE `ordemservico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pessoa` (
  `tipo` varchar(31) NOT NULL,
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `rg` varchar(255) DEFAULT NULL,
  `sexo` varchar(255) DEFAULT NULL,
  `telefoneF` varchar(255) DEFAULT NULL,
  `telefoneM` varchar(255) DEFAULT NULL,
  `endereco_cod` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_hcqfns5v9p63lqsngkrg6vsk7` (`endereco_cod`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES ('C',2,'094.139.344-46','guilhermehtk@hotmail.com','Guilherme Henrique','MG19383888','Masculino','(38)8883-3333','(37)99988-1818',21),('C',3,'125.987.988-32','lelepinheiro@hotmail.com','Leandro Souza Pinheiro','MG12366998','Masculino','(37)6565-6121','(37)98545-4544',36),('C',4,'032.216.565-65','danillo@hotmail.com','Danillo Remaclo dos Santos','MG654232454','Masculino','(37)6545-6432','(37)56565-3621',37),('C',5,'036.546.546-54','thomas@thomas.com.br','Thomas do Vale','MG36232323256','Masculino','(37)6564-1362','(37)65652-1545',38),('C',6,'312.241.545-45','leitao@arape.com.br','Marcelo Geovane Silva Sá','MG65621212','Masculino','(37)6526-5332','(37)65652-1545',39),('C',7,'121.975.456-07','igorribeiro@gmail.com','Igor Ribeiro da Silva','MG1974321758','Feminino','(37)3321-2505','(37)99925-7421',40),('C',8,'194.723.465-02','tiaocarreiro99@hotmail.com','Hernane Marcos de Faria Junior','MG98414355','Masculino','(37)3322-5588','(37)99825-4509',41),('C',9,'242.424.240-24','pedrohenrique@hotmail.com','Pedro Henrique Meneses','MG242424242424','Masculino','(37)3321-2578','(37)99925-5252',42),('C',10,'136.152.981-54','gorazilgarcia@hotmail.com','Gorazil Emerson Garcia Junior','MG24936551','Masculino','(37)3321-5588','(67)99925-5252',43),('C',11,'152.357.951-25','viniciuslima96@gmail.com','Vinmicius Pedro Damasceno Lima','MG245866522423','Masculino','(37)3325-2521','(37)99958-9536',44),('F',13,'094.202.329-82','guilhermehtk@hotmail.com','Guilherme Henrique Pinto','MG9328239','Masculino','(27)8012-9821','(37)92198-2189',46);
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servico`
--

DROP TABLE IF EXISTS `servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servico` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `valor` double NOT NULL,
  PRIMARY KEY (`cod`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servico`
--

LOCK TABLES `servico` WRITE;
/*!40000 ALTER TABLE `servico` DISABLE KEYS */;
INSERT INTO `servico` VALUES (5,'Troca de amortecedores e molas ',200),(6,'Troca da bandeja ',312),(12,'Troca do braço axial ',155),(14,'Luzes acessas no painel',123),(18,'Controle de tração',155),(20,'Reprogramação de Centrais Eletrônicas ',124),(21,'Troca da barra de direção ',341),(22,'Reparos em módulos de: injeção',55),(23,'Conversor de torque',950),(24,'Troca de óleo de câmbio',85),(25,'Pintura',200);
/*!40000 ALTER TABLE `servico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servico_os`
--

DROP TABLE IF EXISTS `servico_os`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servico_os` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `funcionario_codigo` int(11) DEFAULT NULL,
  `ordemservico_cod` int(11) DEFAULT NULL,
  `servico_cod` int(11) DEFAULT NULL,
  PRIMARY KEY (`cod`),
  KEY `FK_ntdvx375m25i5jm14ns46nlcr` (`funcionario_codigo`),
  KEY `FK_isbu3lkkgoq557mvv9adqe90d` (`ordemservico_cod`),
  KEY `FK_llo3a2hsa2co5djm4ta8og13h` (`servico_cod`)
) ENGINE=MyISAM AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servico_os`
--

LOCK TABLES `servico_os` WRITE;
/*!40000 ALTER TABLE `servico_os` DISABLE KEYS */;
INSERT INTO `servico_os` VALUES (44,13,10,25),(43,13,10,23),(42,13,10,21),(41,13,10,20),(40,13,10,18),(39,13,10,14),(38,13,9,23),(37,13,9,20),(36,13,9,14),(35,13,9,12),(34,13,8,24),(33,13,8,22),(32,13,8,21),(31,13,7,14),(30,13,7,12),(29,13,7,6),(28,13,6,25),(27,13,5,24),(26,13,5,23),(25,13,5,21),(24,13,5,18),(23,13,4,22),(22,13,4,21),(21,13,4,12),(20,13,4,5),(19,13,3,24),(18,13,3,23),(17,13,3,20),(16,13,3,18),(15,13,3,5),(14,13,2,23),(13,13,2,22),(12,13,2,20),(11,13,2,14),(10,13,2,5),(9,13,1,23),(8,13,1,22),(7,13,1,21),(6,13,1,20),(5,13,1,18),(4,13,1,14),(3,13,1,12),(2,13,1,6),(1,13,1,5);
/*!40000 ALTER TABLE `servico_os` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-05 13:09:39
