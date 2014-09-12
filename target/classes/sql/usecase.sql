--connection: username:root. password: tools2013---
----database name: solr----
--item--
--schema.xml�б��������INT�͵�ID��String���ͣ����ݿ��п���ֱ����String���룬����
--��data-config.xml�е����ÿɣ�ֻ������POPULRITY)�ƶϳ���Solr��������Դ�������Ǵ�Сд����ν��Ĭ�ϻ�ƥ�䵽ID - > id

--��window��������Ϊ����Ĭ�ϱ�����gbk������������趨client�ı���ȥ����MySQL�����ݿ⣬�����������ʹ��utf-8�ı���洢��һ�������ǣ�����
--�򵥵Ľ���취�ǣ�
--mysql -h ip -u username -p passwd --default-character-set=gbk
--notes: �������ڽ���console��ͨ��set������ָ���� set charset gbk ͬ���ܿ��������ַ�


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

INSERT INTO solr_item VALUES("1","MX3","����ʹ��", "1.2","2399.0","10", "��������Ĥ����ǿ��ӡ�");
INSERT INTO solr_item VALUES("2","Iphone5s","����ʹ��,һ������", "1.1","5399.0","10", "Ipad�齱����");
INSERT INTO solr_item VALUES("3","Note3","�е���е���", "1.5","5199.0","7", "��ޢ��ױƷ");
INSERT INTO solr_item VALUES("4","LG-One","�൱Nice", "1.3","3399.0","10", "LG��Ϸ���ݸ�");
INSERT INTO solr_item VALUES("5","Lumia1520","�ι̡��߶ˡ�����", "1.2","3399.0","10", "���߳����");
INSERT INTO solr_item VALUES("6","С��3","û����ƣ������ͨ", "1.4","1999.0","9", "�Լ۱ȸߣ�����࣬��ͷ��");


INSERT INTO solr_item VALUES("100","����","û����ƣ��к�ɫ", "1.4","999.0","9", "ͨ�죬�ѿ�");
INSERT INTO solr_item VALUES("101","����","��ͳ�����", "1.0","2999.0","10", "�ֻ��е�ս����");



--feature item(1):feature(n)--
CREATE TABLE IF NOT EXISTS solr_feature(
	ID INT NOT NULL AUTO_INCREMENT,
	ITEM_ID INT NOT NULL,
	LAST_UPDATE_TIME TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	DESCRIPTION VARCHAR(1000),
	PRIMARY KEY(ID),
	FOREIGN KEY (ITEM_ID) REFERENCES solr_item(ID)
)
INSERT INTO solr_feature VALUES(1,"1", CURRENT_TIMESTAMP(),"��������Exynos5 Octa˫�ĺ˴�������������Flyme 3.0ϵͳ������MX3��Ϊ��ȫ���׿��䱸128G�汾�Ļ��͡�����Ƽ��ܲð�������ȫ��ý�塢������顢����������MX3�ǵ�������õĴ����ֻ�");
INSERT INTO solr_feature VALUES(2,"2", CURRENT_TIMESTAMP(),"iPhone 5s������ƻ����˾��2013��9���Ƴ���һ���ֻ�����9��20����12�����Լ������׷�iPhone 5s���״ΰ����й���½����������ͻ��900�򲿡�2013��ף�����֪���Ƽ�ý�塶��ҵ��Ļ��������ˡ��������ߴ�������ʮ���豸����iPhone 5s��ָ��ʶ���ܶ�����ѡ����");
INSERT INTO solr_feature VALUES(3,"3", CURRENT_TIMESTAMP(),"����GALAXY Note III������Noteϵ�еĵ�������Ʒ���䱸5.7Ӣ��ȫ����������(Super AMOLED)���ֱ���Ϊ1080P(1920*1080����)���������� Exynos 5 Octa 5420�˺��Ĵ�������2.3GHz��ͨ����800�ĺ�о�������������ڴ�ߴ�3GB�������ڴ�Ϊ16GB/32GB/64GB,Ӳ�������Ѵﵽ���µĶ���ˮƽ.");
INSERT INTO solr_feature VALUES(4,"4", CURRENT_TIMESTAMP(),"LG G Flex������8.7mm����������ر�Ӳ��Ҳ�ǳ���ɫ��������һ��6��720p��������Ļ��ʹ�õ���LG�Լҵ�����OLED��塣�û�������2.3GHz�ĸ�ͨ����800�ĺ˴��������䱸2GB�ڴ��Լ�32GB�洢���ṩһ��1300����������ͷ������Android 4.2.2ϵͳ");
INSERT INTO solr_feature VALUES(5,"5", CURRENT_TIMESTAMP(),"2013��10��22�գ�ŵ����Lumia 1520��ʽ�������ò�Ʒ֧��PureView����������ʶ���ܣ�����ͬʱ����һ��1600��������Ƭ�ͱ���һ��500��������Ƭ���ڷ��������������°汾��WP8 GDR3ϵͳ��]ŵ����Lumia 1520�����Ƴ���ɫ����ɫ����ɫ������ɫ������ɫ��Ԥ����2013����ļ������У�Ԥ���ۼ�Ϊ749��Ԫ������˰�Ͳ�����");
INSERT INTO solr_feature VALUES(6,"1", CURRENT_TIMESTAMP(),"MX3����Ƽ�ֱ����Iphone6����ƣ������ӱ߿����ƣ�������������ۡ�");

--used to test delta import



--category item(m):catory(n)--
CREATE TABLE IF NOT EXISTS solr_category(
	ID INT NOT NULL AUTO_INCREMENT,
	DESCRIPTION VARCHAR(1000),
	PRIMARY KEY(ID)
)AUTO_INCREMENT=0;
INSERT INTO solr_category VALUES("1","Android�ֻ�,��׿һ�ʵı�����ָ�����ˣ�ͬʱҲ��Google��2007��11��5�������Ļ���Linuxƽ̨�Ŀ�Դ�ֻ�����ϵͳ�����ƣ���ƽ̨�ɲ���ϵͳ���м�����û������Ӧ�������ɣ����׸�Ϊ�ƶ��ն˴�����������ź��������ƶ���������°汾ΪAndroid4.4.2 KitKat��");
INSERT INTO solr_category VALUES("2","Windows Phone����ƣ�WP����΢������һ���ֻ�����ϵͳ������΢�����µ�Xbox Live��Ϸ��Xbox Music��������ص���Ƶ���鼯�����ֻ��С�΢��˾��2010��10��11������9��30����ʽ�����������ֻ�����ϵͳWindows Phone��������ʹ�ýӿڳ�Ϊ��Modern���ӿڡ�2011��2�£���ŵ���ǡ���΢����ȫ��ս��ͬ�˲���Ⱥ�����ͬ�з���2011��9��27�գ�΢����Windows Phone 7.5��2012��6��21�գ�΢����ʽ����Windows Phone 8�����ú�Windows 8��ͬ��Windows NT�ںˣ�ͬʱҲ����г���Windows Phone 7.5����Windows Phone 7.8������Windows Phone 7�ֻ������޷�������Windows Phone 8��");
INSERT INTO solr_category VALUES("3","ƻ��iOS����ƻ����˾�������ƶ�����ϵͳ��ƻ����˾������2007��1��9�յ�Macworld����Ϲ������ϵͳ���������Ƹ�iPhoneʹ�õģ�����½�����õ�iPod touch��iPad�Լ�Apple TV�Ȳ�Ʒ�ϡ�iOS��ƻ����Mac OS X����ϵͳһ������Ҳ����DarwinΪ�����ģ����ͬ��������Unix����ҵ����ϵͳ��ԭ�����ϵͳ��ΪiPhone OS����ΪiPad��iPhone��iPod Touch��ʹ��iPhone OS������2010WWDC�������������ΪiOS");


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
