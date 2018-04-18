package Test_XML;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jTest implements XmlDocument{

	@Override
	public void parserXml(String fileName){
		/*  例子如下
		 * <?xml version="1.0" encoding="UTF-8"?>
		<users>
		  <user id="0">
		    <name>Alexia</name>
		    <age>23</age>
		    <sex>Female</sex>
		  </user>
		  <user id="1">
		    <name>Edward</name>
		    <age>24</age>
		    <sex>Male</sex>
		  </user>
		</users>
		 */		
		File inputXml=new File(fileName);
		SAXReader saxReader=new SAXReader();
		
		try{
		   Document document=saxReader.read(inputXml);
		   Element users=document.getRootElement();  //获取根节点
		   //进行迭代，读取根节点下的所有节点和子节点下的所有节点
		   for(Iterator i=users.elementIterator();i.hasNext();){
			   Element user= (Element)i.next();
			   for(Iterator j=user.elementIterator();j.hasNext();){
				   Element node=(Element) j.next();
				   System.out.println(node.getName()+":"+node.getText());
			   }
			   System.out.println();
		   }							
		}catch(DocumentException e){
			System.out.println(e.getMessage());
		}
	}
	/* 相应的输出结果如下
	 * name:Alexia
       age:23
       sex:Female

       name:Edward
       age:24
       sex:Male
	 * 
	 */
	
	/*
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
	</users>
	 */
	public void parserXml1(String strFileName) throws DocumentException{
		SAXReader reader = new SAXReader();  
		Document document = reader.read(strFileName);
		
		Element root = document.getRootElement();//获得根节点；  
		 System.out.println(root.getName()); //输出的是users
		//进行迭代；读取根节点下的所有节点和子节点下的所有节点  
		for (Iterator i = root.elementIterator(); i.hasNext();) {  
		   Element element = (Element) i.next();  
		   System.out.println(element.getName());  //输出的是user
		   System.out.println(((Element)element.elementIterator().next()).getName());  
		   for (Iterator j = element.elementIterator(); j.hasNext();) {  
			   Element el2 = (Element) j.next();  			
			   for(Iterator k=el2.elementIterator();k.hasNext();){
				   System.out.println(((Element) k.next()).getName());  
			   }
			   System.out.println(((Element) j.next()).getName());  
		   }  
		}  
		//读取节点名为user的所有子节点  
		for (Iterator i = root.elementIterator("user"); i.hasNext();) {  
		  Element u1 = (Element) i.next();  
		  System.out.println(u1.getName());  
		}  
		//读取根节点的所有属性  
		for (Iterator i = root.attributeIterator(); i.hasNext();) {  
		  Attribute attribute = (Attribute) i.next();  
		  System.out.println(attribute.getName());   //没有信息
		  }  
		
		for (Iterator i = root.elementIterator("name"); i.hasNext();) {  
			  Element name1 = (Element) i.next();  
			  System.out.println(name1.getName()+":"+name1.getText());  //由于Users直接子节点不是name，所有没信息
			} 		
	}
	
	/*
	 * <?xml version="1.0" encoding="utf-8"?>
<!--这里是注释-->
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
	void CreateXML(){
		//创建一个xml文档
		Document doc=DocumentHelper.createDocument();
		//向xml中添加注释
		doc.addComment("这里是注释");
		//创建一个名为students的节点，是根节点
		Element root=doc.addElement("Students");
		//在root节点上创建一个名为student的节点
		Element stuEle=root.addElement("student");
		//给student添加属性
		stuEle.addAttribute("id", "101");
		//给student节点添加一个子节点
		Element nameEle=stuEle.addElement("name");
		Element saxEle=stuEle.addElement("sax");
		//设置子节点的文本
		nameEle.setText("张三");
		saxEle.setText("Boy");
		   //在student节点下添加test,且再添加子节点Id
		Element test=stuEle.addElement("test");
		Element IdEle=test.addElement("Id");
		  //设置子节点Id的文本
		IdEle.setText("131021");
		
///再次加一个student, id=102的
		//在root节点上创建一个名为student的节点
		Element stuEle2=root.addElement("student");
		//给student添加属性
		stuEle2.addAttribute("id", "102");
		//给student节点添加一个子节点
		Element nameEle2=stuEle2.addElement("name");
		Element saxEle2=stuEle2.addElement("sax");
		//设置子节点的文本
		nameEle2.setText("李四");
		saxEle2.setText("girl");
		   //在student节点下添加test,且再添加子节点Id
		Element test2=stuEle2.addElement("test");
		Element IdEle2=test2.addElement("Id");
		  //设置子节点Id的文本
		IdEle2.setText("131023");
		
		//用于格式化xml内容和设置头部标签
		OutputFormat format=OutputFormat.createPrettyPrint();
		//设置xml文档的编码为utf-8
		format.setEncoding("utf-8");
		Writer out;
		try{
			//创建一个输出流对象
			out=new FileWriter("D://Java_wwy//workspace//TestJAVAInfo//src//Test_XML//new.xml");
			//创建一个dom4j创建xml的对象
			XMLWriter writer1=new XMLWriter(out, format);
			//调用write方法将doc文档写到指定路径
			writer1.write(doc);
			writer1.close();
			System.out.print("生成xml成功");
		}catch(IOException e){
			System.out.print("生成xml失败");
			e.printStackTrace();
		}
	}
	
	/*
	 * 根据id读取信息
	 */
	public void readXmlInfoFromID(String filename,String id) throws DocumentException{
		SAXReader sax1=new SAXReader();
		Document doc1=sax1.read(new File(filename));
		Element e=(Element)doc1.selectSingleNode("/users/user[@id='"+id+"']");
		Element name=e.element("name");
		System.out.println("Name:"+name.getText());			
	}
	
	/*
	 * 读取所有name的信息
	 */
	public void readXmlInfoToAll(String filename) throws DocumentException{
		SAXReader sax1=new SAXReader();
		Document doc1=sax1.read(new File(filename));
		List list1=doc1.selectNodes("//name");
		Iterator it=list1.iterator();
		while(it.hasNext()){
			Element name=(Element)it.next();
			System.out.println("Name:"+name.getText());			
		}	
	}
	
	/*
	 * 添加信息
	 */
	public void addInfoToXML(String filename,String id,String name,String age,
			String sex)throws DocumentException, Exception {
		SAXReader sax1=new SAXReader();
		Document doc1=sax1.read(new File(filename));
		Element e=(Element)doc1.selectSingleNode("users");
		Element user=e.addElement("user");
		user.setAttributeValue("id", id);
		user.addElement("name").setText(name);
		user.addElement("age").setText(age);
		user.addElement("sex").setText(sex);
		XMLWriter output=new XMLWriter(new FileWriter(new File(filename)));
		output.write(doc1);
        output.close();
	}
	/*
	 * 删除指定id的信息
	 */
	public void deleteXmlInfoFromID(String filename,String id)throws DocumentException, Exception{
		SAXReader sax1=new SAXReader();
		Document doc1=sax1.read(new File(filename));
		Element e=(Element)doc1.selectSingleNode("/users/user[@id='"+id+"']");
		if(e !=null){
			Element parent=e.getParent();
			parent.remove(e);  
			 XMLWriter output = new XMLWriter(  
				new FileWriter( new File(filename) ));  
			output.write(doc1);  
			output.close();  
		}		
	}
	///修改xml文件的内容，并另存为一个新文件(也可以向自己的xml中写入)
	/*
	 * @param filename
	 *       修改对象文件
	 * @param newfilename
	 *       修改后另存为该文件
	 */
	public  int  ModifyXMLFile(String filename,String newfilename) throws DocumentException{
		int returnVlaue=0;
		try{
			SAXReader reader1 = new SAXReader();  
			Document doc1 = reader1.read(new File(filename));
			
			//修:1：如果student节点的属性为101,改为201
			List list1= doc1.selectNodes("Students/student/@id"); ///必须加入jaxen-1.1-beta-6.jar才可以调用
			Iterator iter1=list1.iterator();
			while(iter1.hasNext()){
				Attribute attribute=(Attribute) iter1.next();
				if(attribute.getValue().equals("101")){
					attribute.setValue("201");
				}
			}
			//修改2：修改student节点下name节点的内容，并
			//在student节点下加入date节点，还添加一个属性
			List list2=doc1.selectNodes("Students/student/name");
			iter1=list2.iterator();
			if(iter1.hasNext()){
				Element ownerElement = (Element) iter1.next();  
				ownerElement.setText("张三Copy");  
//				Element dateElement = ownerElement.addElement("date");  
//				dateElement.setText("2004-09-11");  
//				dateElement.addAttribute("type", "Gregorian calendar");  
			}
			List list3=doc1.selectNodes("Students/student");
			iter1=list3.iterator();
			if(iter1.hasNext()){
				Element ownerElement = (Element) iter1.next();  
				Element dateElement = ownerElement.addElement("date");  
				dateElement.setText("2004-09-11");  
				dateElement.addAttribute("type", "China");
			}
			//修改3：如果sax为boy，则删除
			List list4=doc1.selectNodes("Students/student");
			iter1=list4.iterator();
			while(iter1.hasNext()){
				Element StuElement=(Element)iter1.next();
				Iterator iter2=StuElement.elementIterator("sax");
				while (iter2.hasNext()) {
					Element saxElement=(Element)iter2.next();
					if(saxElement.getText().equals("Boy")){
						StuElement.remove(saxElement);
					}
				}							
			}
			//修改4：修改101的sax值
			Element e = (Element)doc1.selectSingleNode("/Students/student[@id='"+"102"+"']");  
			Element n = e.element("sax");  
			n.setText("Test_Boy");  

			//		Element n=Ele.element("sax");
			//		n.setText("Test_boy");
		try{
			//将document中内容写入文件中
			XMLWriter writer1=new XMLWriter(new FileWriter(new File(newfilename)));
			writer1.write(doc1);
			writer1.close();
			//执行成功，需要返回1
			returnVlaue=1;
		   }catch(Exception ex1){
			 ex1.printStackTrace();
		   }
					
		}catch(Exception ex2){
			ex2.printStackTrace();
		}
		return returnVlaue;	
	}

    /*
     * 格式化文档，解决中文问题,------------该方法有待商量
     */
	public int  formatXmlFile(String filename){
		int nVlaue=0;
		try{
			//创建一个xml文档
			Document doc=DocumentHelper.createDocument();
			XMLWriter writer1=new XMLWriter(new FileWriter(new File(filename)));
//			writer1.write(doc);
//			writer1.close(); //这执行完，会产生一个xml文件，且内容是<?xml version="1.0" encoding="UTF-8"?>
			
		//	SAXReader saxReader=new SAXReader();
		//	Document doc1=saxReader.read(filename);
			XMLWriter writer = null;
			/** 格式化输出,类型IE浏览一样 */
			OutputFormat format = OutputFormat.createPrettyPrint();
			/** 指定XML编码 */
			format.setEncoding("GBK");
			writer = new XMLWriter(new FileWriter(new File(filename)), format);
			writer.write(doc);
			writer.close(); 
			/** 执行成功,需返回1 */
			nVlaue = 1;
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return nVlaue;
	}
   }
