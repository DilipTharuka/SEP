-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 21, 2015 at 03:30 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `nms`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`ID`) VALUES
(3);

-- --------------------------------------------------------

--
-- Table structure for table `approver`
--

CREATE TABLE IF NOT EXISTS `approver` (
  `ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `approver`
--

INSERT INTO `approver` (`ID`) VALUES
(5);

-- --------------------------------------------------------

--
-- Table structure for table `editor`
--

CREATE TABLE IF NOT EXISTS `editor` (
  `ID` int(11) NOT NULL,
  `last_assign` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `editor`
--

INSERT INTO `editor` (`ID`, `last_assign`) VALUES
(6, '2015-08-20 07:27:49'),
(8, '2015-08-19 01:53:08');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `street` varchar(30) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `country` varchar(20) DEFAULT NULL,
  `type` int(3) NOT NULL,
  `state` int(11) NOT NULL,
  `join_date` date NOT NULL,
  `left_date` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `username` (`username`),
  KEY `employeeType` (`type`),
  KEY `state` (`state`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`ID`, `username`, `password`, `first_name`, `last_name`, `street`, `city`, `country`, `type`, `state`, `join_date`, `left_date`) VALUES
(1, 'dilip', '*EE2BD8C582A8407A834B33FA120F6960D9FBBA4B', 'Dilip', 'Tharuka', 'Malligodella', 'Mulleriyawa', 'Sri Lanka', 2, 2, '2015-06-28', NULL),
(3, 'pegasus', '*33DCE53C1AB18386877EE319653BFC5F3988D998', 'pegasus', '1010101', 'CBK RD', 'Malabe', 'Sri Lanka', 1, 1, '2015-08-02', NULL),
(4, 'nipuna', '*3E671ECFEADF2FA4EFC23490FA54261CB4709D47', 'Journalist', 'Journalist', 'Journalist', 'Journalist', 'Journalist', 2, 2, '2015-08-03', NULL),
(5, 'dilini', '*C1C4E532285006BE55ACD762747DB5D871D4C0A9', 'Dilini', 'Arachchi', 'DFE RD', 'Panadura', 'Sri Lanka', 5, 1, '2015-08-03', NULL),
(6, 'yasanka', '*92DA154145AEDE9D59985F49FE4C09E95CB95FBF', 'Yasanka', 'Pappa', 'Editor', 'Editor', 'Editor', 4, 1, '2015-08-03', NULL),
(7, 'pubudu', '*D20555F5B59A6BC249518819CF6F371E3545C744', 'Pubudu', 'Fernando', 'KUG RD', 'Horana', 'Sri Lanka', 3, 1, '2013-08-03', NULL),
(8, 'nisal', '*88D1BE4D8A3635D7D4A79B25D3CB855D1B9A4932', 'nisal', 'chamara', 'Malabe', 'Colombo', 'Sri Lanka', 4, 1, '2015-08-19', NULL),
(10, 'nandula', '*0BC04F8B2999CAB85AA2A9ECF962ADDD4F10DC95', 'Nandula', 'Jayahama', 'TB Jaya', 'Wellawaththa', 'Sri Lanka', 2, 1, '2015-08-20', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `employee_mobile`
--

CREATE TABLE IF NOT EXISTS `employee_mobile` (
  `ID` int(11) NOT NULL,
  `mobile_number` varchar(15) NOT NULL,
  PRIMARY KEY (`ID`,`mobile_number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee_mobile`
--

INSERT INTO `employee_mobile` (`ID`, `mobile_number`) VALUES
(1, '0719957440'),
(1, '0719995064'),
(1, '0774532627'),
(4, '071564245'),
(4, '077564234'),
(5, '071456342'),
(6, '076545272'),
(7, '077876829'),
(8, '071536472'),
(8, '077546325'),
(10, '071345263');

-- --------------------------------------------------------

--
-- Table structure for table `employee_state`
--

CREATE TABLE IF NOT EXISTS `employee_state` (
  `ID` int(11) NOT NULL,
  `description` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee_state`
--

INSERT INTO `employee_state` (`ID`, `description`) VALUES
(1, 'offline'),
(2, 'online'),
(3, 'suspend'),
(4, 'retire');

-- --------------------------------------------------------

--
-- Table structure for table `employee_type`
--

CREATE TABLE IF NOT EXISTS `employee_type` (
  `ID` int(11) NOT NULL,
  `description` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee_type`
--

INSERT INTO `employee_type` (`ID`, `description`) VALUES
(1, 'Admin'),
(2, 'Journalist'),
(3, 'Receptionist'),
(4, 'Editor'),
(5, 'Approver');

-- --------------------------------------------------------

--
-- Table structure for table `journalist`
--

CREATE TABLE IF NOT EXISTS `journalist` (
  `ID` int(11) NOT NULL,
  `work_province` varchar(30) DEFAULT NULL,
  `work_city` varchar(30) DEFAULT NULL,
  `work_division` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `journalist`
--

INSERT INTO `journalist` (`ID`, `work_province`, `work_city`, `work_division`) VALUES
(1, NULL, NULL, NULL),
(4, 'Journalist', 'Journalist', 'Journalist'),
(10, 'Comombo 2', 'Colombo', 'Western');

-- --------------------------------------------------------

--
-- Table structure for table `news`
--

CREATE TABLE IF NOT EXISTS `news` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `description` longtext NOT NULL,
  `state` int(11) NOT NULL,
  `journalist_ID` int(11) NOT NULL,
  `receptionist_ID` int(11) DEFAULT NULL,
  `editor_ID` int(11) DEFAULT NULL,
  `approver_ID` int(11) DEFAULT NULL,
  `published_year` int(11) DEFAULT NULL,
  `published_month` int(11) DEFAULT NULL,
  `published_date` int(11) DEFAULT NULL,
  `published_time` time DEFAULT NULL,
  `published_link` varchar(100) DEFAULT NULL,
  `temp_link` varchar(100) NOT NULL,
  `comment` longtext NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `title` (`title`),
  KEY `state` (`state`),
  KEY `journalistID` (`journalist_ID`,`receptionist_ID`,`editor_ID`,`approver_ID`),
  KEY `receptionistID` (`receptionist_ID`),
  KEY `receptionistID_2` (`receptionist_ID`),
  KEY `editorID` (`editor_ID`),
  KEY `approverID` (`approver_ID`),
  KEY `state_2` (`state`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Dumping data for table `news`
--

INSERT INTO `news` (`ID`, `title`, `description`, `state`, `journalist_ID`, `receptionist_ID`, `editor_ID`, `approver_ID`, `published_year`, `published_month`, `published_date`, `published_time`, `published_link`, `temp_link`, `comment`) VALUES
(3, 'Russia vetoes MH17 tribunal at UN', 'Russia uses its UN veto to block plans for an international tribunal to invetigate last years MH17 crash in Ukraine drawing condemnation.', 6, 1, 7, 6, NULL, NULL, NULL, NULL, NULL, NULL, 'C:UsersDilipDesktopSet.zip', 'find more - http://www.bbc.com/news/world-europe-33710088'),
(4, 'bbdx snuja', 'bsjzan hushas huhus huhus hus hsygajijs', 7, 1, 7, 6, NULL, 2015, NULL, NULL, NULL, NULL, 'C:UsersDilipDesktopClab6.zip', 'bsan ushaujus huhusa huhus'),
(6, 'India executes Mumbai bomb plotter', 'India has carried out the execution of Yakub Memon, the man convicted of financing the deadly 1993 Mumbai bombings.', 4, 1, 7, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'C:UsersDilipDesktopClab9.zip', 'nothing'),
(9, 'Russia vetoes MH17', 'Russia uses its UN veto to block plans for an international tribunal to investigate last years MH17 crash in Ukraine drawing condemnation', 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'C:\\Users\\Dilip\\Desktop\\Set.zip', 'find more - http://www.bbc.com/news/world-europe-33710088'),
(10, 'dsvhcb gdshc', 'ghfwey  fewgdsh gygcus', 4, 1, 7, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'C:\\Users\\Dilip\\Desktop\\Clab6.zip', 'sdvhb ghdsc hdc'),
(11, 'Final Test', 'harge towards the truck, clambering onto the top with pipes, which a', 5, 1, 7, 6, NULL, NULL, NULL, NULL, NULL, NULL, 'C:\\Users\\Dilip\\Desktop\\Clab6.zip', 'nothing'),
(14, '', '', 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', ''),
(15, 'Why Delhi is buying water on the black market', 'Young men charge towards the truck, clambering onto the top with pipes, which are then lowered into the tank. Others push the drums in place - there is a mad scramble to fill them up with clean water.', 5, 1, 7, 6, NULL, NULL, NULL, NULL, NULL, NULL, 'C:UsersDilipDesktopClab7.zip', 'nothing'),
(16, 'sh\\hshu/huhs\\jnsj', 'nsjn njns', 5, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ns isk js', 'jnsjn isjjis ji'),
(17, 'Why Delhi is buying water', 'Young men charge towards the truck\nclambering onto the top with pipes', 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'C:\\Users\\Dilip\\Desktop\\120300N.zip', 'no'),
(18, 'ABCD', 'bah hauqhua uhuah1 huhu1', 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'C:\\Users\\Dilip\\Desktop\\Clab9.zip', 'nope');

-- --------------------------------------------------------

--
-- Table structure for table `news_image`
--

CREATE TABLE IF NOT EXISTS `news_image` (
  `ID` int(11) NOT NULL,
  `local_link` varchar(100) NOT NULL,
  `remote_link` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`,`local_link`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `news_image`
--

INSERT INTO `news_image` (`ID`, `local_link`, `remote_link`) VALUES
(11, 'C:\\Users\\Dilip\\Desktop\\moraSepia.png', 'image_11_1.png'),
(11, 'C:\\Users\\Dilip\\Documents\\120300N.png', 'image_11_2.png'),
(15, 'C:\\Users\\Dilip\\Desktop\\mora.jpg', 'image_15_3.jpg'),
(15, 'C:\\Users\\Dilip\\Desktop\\moraSepia.gif', 'image_15_2.gif'),
(15, 'C:\\Users\\Dilip\\Documents\\120300N.png', 'image_15_1.png');

-- --------------------------------------------------------

--
-- Table structure for table `news_state`
--

CREATE TABLE IF NOT EXISTS `news_state` (
  `ID` int(11) NOT NULL,
  `description` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `news_state`
--

INSERT INTO `news_state` (`ID`, `description`) VALUES
(1, 'saved'),
(2, 'submitted'),
(3, 'accepted'),
(4, 'throwaway'),
(5, 'edited'),
(6, 'reedit'),
(7, 'approved'),
(8, 'published');

-- --------------------------------------------------------

--
-- Table structure for table `news_video`
--

CREATE TABLE IF NOT EXISTS `news_video` (
  `ID` int(11) NOT NULL,
  `local_link` varchar(100) NOT NULL,
  `remote_link` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`,`local_link`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `news_video`
--

INSERT INTO `news_video` (`ID`, `local_link`, `remote_link`) VALUES
(11, 'C:\\Users\\Dilip\\Desktop\\0102 Understanding prerequisites for Python.mov', 'video_11_1.mov'),
(11, 'C:\\Users\\Dilip\\Desktop\\0103 Using the exercise files.mov', 'video_11_2.mov');

-- --------------------------------------------------------

--
-- Table structure for table `receptionist`
--

CREATE TABLE IF NOT EXISTS `receptionist` (
  `ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `receptionist`
--

INSERT INTO `receptionist` (`ID`) VALUES
(7);

-- --------------------------------------------------------

--
-- Table structure for table `shift`
--

CREATE TABLE IF NOT EXISTS `shift` (
  `employee_ID` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  `month` int(11) NOT NULL,
  `day` int(11) NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  PRIMARY KEY (`employee_ID`,`year`,`month`,`day`,`start_time`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `subscriber`
--

CREATE TABLE IF NOT EXISTS `subscriber` (
  `mobile_number` varchar(15) NOT NULL,
  PRIMARY KEY (`mobile_number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subscriber`
--

INSERT INTO `subscriber` (`mobile_number`) VALUES
('0719957440'),
('0768427704');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `employee` (`ID`);

--
-- Constraints for table `approver`
--
ALTER TABLE `approver`
  ADD CONSTRAINT `approver_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `employee` (`ID`);

--
-- Constraints for table `editor`
--
ALTER TABLE `editor`
  ADD CONSTRAINT `editor_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `employee` (`ID`);

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`type`) REFERENCES `employee_type` (`ID`),
  ADD CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`state`) REFERENCES `employee_state` (`ID`);

--
-- Constraints for table `employee_mobile`
--
ALTER TABLE `employee_mobile`
  ADD CONSTRAINT `employee_mobile_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `employee` (`ID`);

--
-- Constraints for table `journalist`
--
ALTER TABLE `journalist`
  ADD CONSTRAINT `journalist_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `employee` (`ID`);

--
-- Constraints for table `news`
--
ALTER TABLE `news`
  ADD CONSTRAINT `news_ibfk_1` FOREIGN KEY (`state`) REFERENCES `news_state` (`ID`),
  ADD CONSTRAINT `news_ibfk_2` FOREIGN KEY (`journalist_ID`) REFERENCES `journalist` (`ID`),
  ADD CONSTRAINT `news_ibfk_3` FOREIGN KEY (`receptionist_ID`) REFERENCES `receptionist` (`ID`),
  ADD CONSTRAINT `news_ibfk_4` FOREIGN KEY (`editor_ID`) REFERENCES `editor` (`ID`),
  ADD CONSTRAINT `news_ibfk_5` FOREIGN KEY (`approver_ID`) REFERENCES `approver` (`ID`);

--
-- Constraints for table `receptionist`
--
ALTER TABLE `receptionist`
  ADD CONSTRAINT `receptionist_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `employee` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
