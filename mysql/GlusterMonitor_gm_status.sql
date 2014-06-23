
DROP TABLE IF EXISTS `gm_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gm_status` (
  `id` datetime NOT NULL,
  `hostname` varchar(20) NOT NULL DEFAULT '',
  `ipofib` varchar(16) NOT NULL,
  `cpuuseratio` double NOT NULL,
  `memtotal` int(11) NOT NULL,
  `usedmem` int(11) NOT NULL,
  `netsendflow` int(11) NOT NULL,
  `netreceiveflow` int(11) NOT NULL,
  `ibsendflow` int(11) NOT NULL,
  `ibreceiveflow` int(11) NOT NULL,
  PRIMARY KEY (`id`,`hostname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

