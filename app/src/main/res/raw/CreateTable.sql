CREATE TABLE cs_table(
   `csId` TEXT  PRIMARY KEY,
   `csName` TEXT,
   `adress` TEXT,
   `telephone` TEXT,
   `chDate` DATETIME,
   `hzpId` TEXT
);

CREATE TABLE `fw_check_table`(
   `No` INTEGER PRIMARY KEY AUTOINCREMENT,
   `hzpId` TEXT,
   `bqId` TEXT,
   `check` INTEGER
);

CREATE TABLE `hzp_table`(
   `hzpId` TEXT PRIMARY,
   `hzpName` TEXT,
   `scDate` DATETIME,
   `bzq` TEXT
);

CREATE TABLE `jxs_table`(
   `jxsId` TEXT PRIMARY,
   `jxsName` TEXT,
   `jhDate` DATETIME,
   `chDate` DATETIME,
   `hzpId` TEXT
);

INSERT INTO `cs_table` VALUES ('1', '南京化妆品生产厂', '南京江宁区', '15195857959', '2019-04-25 20:11:42', '1');
INSERT INTO `fw_check_table` VALUES (1, '1', '1', 0);
INSERT INTO `hzp_table` VALUES ('1', '防晒霜', '2019-04-24 20:25:10', '365');
INSERT INTO `jxs_table` VALUES ('1', '江宁1街道商店', '2019-04-25 20:26:41', '2019-04-26 20:26:52', '1');