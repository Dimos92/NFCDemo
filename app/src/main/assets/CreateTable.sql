drop table cs_table;
drop table fw_check_table;
drop table hzp_table;
drop table jxs_table;
CREATE TABLE IF NOT EXISTS `cs_table`(`csId` TEXT  PRIMARY KEY,`csName` TEXT,`adress` TEXT,`telephone` TEXT,`chDate` DATETIME,`hzpId` TEXT);
CREATE TABLE IF NOT EXISTS `fw_check_table`(`No` INTEGER PRIMARY KEY AUTOINCREMENT,`hzpId` TEXT,`bqId` TEXT,`check` INTEGER);
CREATE TABLE IF NOT EXISTS `hzp_table`(`hzpId` TEXT PRIMARY KEY,`hzpName` TEXT,`scDate` DATETIME,`bzq` TEXT);
CREATE TABLE IF NOT EXISTS `jxs_table`(`jxsId` TEXT PRIMARY KEY,`jxsName` TEXT,`jhDate` DATETIME,`chDate` DATETIME,`hzpId` TEXT);