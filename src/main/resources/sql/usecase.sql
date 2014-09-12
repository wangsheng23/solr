--connection: username:root. password: tools2013---
----database name: solr----
--item--
--schema.xml中本处定义的INT型的ID是String类型，数据库中可以直接用String插入，见下
--在data-config.xml中的配置可（只配置了POPULRITY)推断出，Solr对于数据源，列名是大小写无所谓，默认会匹配到ID - > id

--在window环境下因为本地默认编码是gbk。所以如果不设定client的编码去链接MySQL的数据库，如果表内数据使用utf-8的编码存储，一般结果就是？？？
--简单的解决办法是：
--mysql -h ip -u username -p passwd --default-character-set=gbk
--notes: 还可以在进入console后，通过set命令来指定， set charset gbk 同样能看到中文字符


CREATE TABLE IF NOT EXISTS solr_item(
	ID INT NOT NULL AUTO_INCREMENT, 
	NAME VARCHAR(10),
	MANU VARCHAR(10),
	WEIGHT FLOAT,
	PRICE FLOAT,
	POPULRITY INT,
	INCLUDES VARCHAR(50),
	PRIMARY KEY(ID)
)AUTO_INCREMENT=0;

INSERT INTO solr_item VALUES("1","MX3","放心使用", "1.2","2399.0","10", "耳机、贴膜、后盖壳子。");
INSERT INTO solr_item VALUES("2","Iphone5s","放心使用,一流体验", "1.1","5399.0","10", "Ipad抽奖机会");
INSERT INTO solr_item VALUES("3","Note3","有点贵，有点重", "1.5","5199.0","7", "蓝蔻化妆品");
INSERT INTO solr_item VALUES("4","LG-One","相当Nice", "1.3","3399.0","10", "LG游戏操纵杆");
INSERT INTO solr_item VALUES("5","Lumia1520","牢固、高端、大气", "1.2","3399.0","10", "无线充电器");
INSERT INTO solr_item VALUES("6","小米3","没有设计，外表普通", "1.4","1999.0","9", "性价比高，服务多，噱头大");


INSERT INTO solr_item VALUES("100","红米","没有设计，有红色", "1.4","999.0","9", "通红，难看");
INSERT INTO solr_item VALUES("101","波导","传统，大观", "1.0","2999.0","10", "手机中的战斗机");



--feature item(1):feature(n)--
CREATE TABLE IF NOT EXISTS solr_feature(
	ID INT NOT NULL AUTO_INCREMENT,
	ITEM_ID INT NOT NULL,
	LAST_UPDATE_TIME TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	DESCRIPTION VARCHAR(1000),
	PRIMARY KEY(ID),
	FOREIGN KEY (ITEM_ID) REFERENCES solr_item(ID)
)
INSERT INTO solr_feature VALUES(1,"1", CURRENT_TIMESTAMP(),"搭载三星Exynos5 Octa双四核处理器，并运行Flyme 3.0系统，并且MX3成为了全球首款配备128G版本的机型。魅族科技总裁白永祥向全球媒体、合作伙伴、魅友宣布，MX3是当今更好用的大屏手机");
INSERT INTO solr_feature VALUES(2,"2", CURRENT_TIMESTAMP(),"iPhone 5s是美国苹果公司在2013年9月推出的一款手机。在9月20日于12国家以及地区首发iPhone 5s，首次包括中国大陆，首周销量突破900万部。2013年底，美国知名科技媒体《商业内幕》整理出了“本年度最具创新力的十大设备”，iPhone 5s因指纹识别功能而被入选其中");
INSERT INTO solr_feature VALUES(3,"3", CURRENT_TIMESTAMP(),"三星GALAXY Note III是三星Note系列的第三代产品，配备5.7英寸全高清炫丽屏(Super AMOLED)，分辨率为1080P(1920*1080像素)，搭载三星 Exynos 5 Octa 5420八核心处理器或2.3GHz高通骁龙800四核芯处理器，运行内存高达3GB，机身内存为16GB/32GB/64GB,硬件配置已达到当下的顶级水平.");
INSERT INTO solr_feature VALUES(4,"4", CURRENT_TIMESTAMP(),"LG G Flex机身厚度8.7mm，不仅外观特别，硬件也非常出色。它采用一块6寸720p的柔性屏幕，使用的是LG自家的柔性OLED面板。该机还搭载2.3GHz的高通骁龙800四核处理器，配备2GB内存以及32GB存储，提供一颗1300万像素摄像头，运行Android 4.2.2系统");
INSERT INTO solr_feature VALUES(5,"5", CURRENT_TIMESTAMP(),"2013年10月22日，诺基亚Lumia 1520正式发布，该产品支持PureView技术和人脸识别功能，可以同时拍摄一张1600万像素照片和保存一张500万像素照片用于分享，还将搭载最新版本的WP8 GDR3系统。]诺基亚Lumia 1520将会推出黄色、白色、黑色、亮红色四种颜色，预计于2013年第四季度上市，预计售价为749美元（不含税和补贴）");
INSERT INTO solr_feature VALUES(6,"1", CURRENT_TIMESTAMP(),"MX3的设计简直就是Iphone6的设计，无外延边框的设计，极大地提高了外观。");

--used to test delta import



--category item(m):catory(n)--
CREATE TABLE IF NOT EXISTS solr_category(
	ID INT NOT NULL AUTO_INCREMENT,
	DESCRIPTION VARCHAR(1000),
	PRIMARY KEY(ID)
)AUTO_INCREMENT=0;
INSERT INTO solr_category VALUES("1","Android手机,安卓一词的本来是指机器人，同时也是Google于2007年11月5日宣布的基于Linux平台的开源手机操作系统的名称，该平台由操作系统、中间件、用户界面和应用软件组成，是首个为移动终端打造的真正开放和完整的移动软件。最新版本为Android4.4.2 KitKat。");
INSERT INTO solr_category VALUES("2","Windows Phone（简称：WP）是微软发布的一款手机操作系统，它将微软旗下的Xbox Live游戏、Xbox Music音乐与独特的视频体验集成至手机中。微软公司于2010年10月11日晚上9点30分正式发布了智能手机操作系统Windows Phone，并将其使用接口称为“Modern”接口。2011年2月，“诺基亚”与微软达成全球战略同盟并深度合作共同研发。2011年9月27日，微软发布Windows Phone 7.5。2012年6月21日，微软正式发布Windows Phone 8，采用和Windows 8相同的Windows NT内核，同时也针对市场的Windows Phone 7.5发布Windows Phone 7.8。现有Windows Phone 7手机都将无法升级至Windows Phone 8。");
INSERT INTO solr_category VALUES("3","苹果iOS是由苹果公司开发的移动操作系统。苹果公司最早于2007年1月9日的Macworld大会上公布这个系统，最初是设计给iPhone使用的，后来陆续套用到iPod touch、iPad以及Apple TV等产品上。iOS与苹果的Mac OS X操作系统一样，它也是以Darwin为基础的，因此同样属于类Unix的商业操作系统。原本这个系统名为iPhone OS，因为iPad，iPhone，iPod Touch都使用iPhone OS，所以2010WWDC大会上宣布改名为iOS");


CREATE TABLE IF NOT EXISTS solr_item_category(
	ITEM_ID INT NOT NULL,
	CATEGORY_ID INT NOT NULL,
	PRIMARY KEY(ITEM_ID, CATEGORY_ID),
	FOREIGN KEY(ITEM_ID) REFERENCES solr_item(ID),
	FOREIGN KEY(CATEGORY_ID) REFERENCES solr_category(ID)
)
INSERT INTO solr_item_category VALUES("1", "1");
INSERT INTO solr_item_category VALUES("2", "3");
INSERT INTO solr_item_category VALUES("3", "1");
INSERT INTO solr_item_category VALUES("4", "1");
INSERT INTO solr_item_category VALUES("5", "2");
