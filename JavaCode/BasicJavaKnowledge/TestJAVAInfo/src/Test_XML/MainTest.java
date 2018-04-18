package Test_XML;


import org.dom4j.DocumentException;

public class MainTest {
/*
 * xmlContext.xml的内容
 * <?xml version="1.0" encoding="UTF-8"?>
<users>
  <user id="0">
    <name>Adist</name>
    <age>23</age>
    <sex>Female</sex>
  </user>
  <user id="1">
    <name>Edward</name>
    <age>24</age>
    <sex>Male</sex>
  </user>
  <user id="2">
    <name>wjm</name>
    <age>23</age>
    <sex>Female</sex>
  </user>
  <user id="3">
    <name>wh</name>
    <age>24</age>
    <sex>Male</sex>
  </user>
</users>
 */
	/*
	 * xmlContext1.xml的内容
	 * <?xml version="1.0" encoding="UTF-8"?>
<users>
  <user id="0">
    <Test>
	   <name>Alexia</name>
	   <age>23</age>
	</Test>
    <sex>Female</sex>
  </user>
  <user id="1">
   <Test>
	   <name>Edward</name>
	   <age>24</age>
	</Test>
    <sex>Male</sex>
  </user>
  <user id="2">
     <Test>
	   <name>wjm</name>
	   <age>25</age>
	</Test>
    <sex>Female</sex>
  </user>
  <user id="3">
     <Test>
	    <name>wh</name>
        <age>24</age>
	</Test>
    <sex>Male</sex>
  </user>
</users>
	 */
	
	/*
	 * ModifyTestFile.xml
	 * <?xml version="1.0" encoding="UTF-8"?>
<Students>
  <student id="101">
    <name>张三</name>
    <sax>Boy</sax>
    <test>
      <Id>131021</Id>
    </test>
  </student>
  <student id="102">
    <name>李四</name>
    <sax>girl</sax>
    <test>
      <Id>131023</Id>
    </test>
  </student>
</Students>
	 */
	public static void main(String[] args) throws DocumentException,Exception {
		// TODO Auto-generated method stub
        String strFile="D://Java_wwy//workspace//TestJAVAInfo//src//Test_XML//xmlContext.xml";
        String strFile1="D://Java_wwy//workspace//TestJAVAInfo//src//Test_XML//xmlContext1.xml";
        String strNew="D://Java_wwy//workspace//TestJAVAInfo//src//Test_XML//ModifyTestFile.xml";
        String strNewFile="D://Java_wwy//workspace//TestJAVAInfo//src//Test_XML//new_Modify.xml";
	//	DOMTest dt=new DOMTest();
	//	dt.parserXml(strFile);
        Dom4jTest d4j=new Dom4jTest();
        d4j.parserXml(strFile);
        d4j.parserXml1(strFile1); //如果调用parserXml1()，则必须需要 throws DocumentException 
        d4j.CreateXML();
        d4j.ModifyXMLFile(strNew,strNewFile);
        d4j.readXmlInfoFromID(strFile,"2");
        d4j.readXmlInfoToAll(strFile);
      //  d4j.addInfoToXML(strFile, "4", "test", "30", "boy");
        d4j.deleteXmlInfoFromID(strFile, "3"); //这些重新写入一个xml文件，会有编码格式问题
	}

}
