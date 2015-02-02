CREATE TABLE `class_students` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `SID` int(11) NOT NULL,
  `CID` int(11) NOT NULL,
  `FNAME` varchar(100) NOT NULL DEFAULT '',
  `LNAME` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

CREATE TABLE `classes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classid` int(11) NOT NULL,
  `classname` varchar(100) DEFAULT NULL,
  `instructor` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

CREATE TABLE `features` (
  `fid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `featureName` varchar(100) DEFAULT NULL,
  `Level` int(11) DEFAULT NULL,
  `misc` int(11) DEFAULT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

CREATE TABLE `learning_data` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sid` int(11) NOT NULL,
  `compileTotal` int(11) DEFAULT '0',
  `compileFails` int(11) DEFAULT '0',
  `compileTotalErrors` int(11) DEFAULT '0',
  `compilePass` int(11) DEFAULT '0',
  `EPC` float DEFAULT '0',
  `bracketRate` float DEFAULT '0',
  `parenRate` float DEFAULT '0',
  `level` int(11) DEFAULT '1',
  `LV` float DEFAULT '0',
  `CID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=latin1;

CREATE TABLE `profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `CID` int(11) NOT NULL,
  `SID` int(11) NOT NULL,
  `dir` varchar(100) DEFAULT NULL,
  `dockinst` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

CREATE TABLE `program_stats` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `student_features` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `SID` int(11) DEFAULT NULL,
  `FID` int(11) DEFAULT NULL,
  `misc` int(11) DEFAULT NULL,
  `CID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(100) DEFAULT NULL,
  `lname` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
