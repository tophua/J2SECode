package Test_Dom4j;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

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
public class MyJdomReaderXML {

	public Document parse(String string) throws DocumentException {  
		SAXReader reader = new SAXReader();  
		Document document = reader.read(string);  
		return document;  
	}  

	public void OutXMLInfo(Document document) throws DocumentException {  
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
	 * 可以根据节点名字读取节点，也可以读取节点里的key和value 
	*/  
	public void readNodes(Document document) {  
	   List list = document.selectNodes( "//users/user" );   //foo为根节点，获得根节点下的bar节点  
	   Node node = document.selectSingleNode( "//users/user/sex" );  //获得名为author的第一 节点  
	   String name = node.valueOf( "@name" ); //获得节点名属性名为name的value  
	}  
	/* 
	* 如果xml文件很大的情况下，用上面的方法很费时，这样 可以用递归遍历整个xml文件 
	*/  
	public void treeWalk(Document document) {  
	   treeWalk(document.getRootElement());  
	}  
	/* 
	* 递归调用，传递每一个父节点做为参数 
	*/  
	public void treeWalk(Element element) {  
	   for (int i = 0, size = element.nodeCount(); i < size; i++) {  
	    Node node = element.node(i);  
	    if (node instanceof Element) {//如果node实现了Element接口，那么就表示node是一个节点。再递归  
	       treeWalk((Element) node);  
	       System.out.println(((Element) node).getName()+":"+node.valueOf("@name"));  
	     } else {//如果没有实现Element接口，那么就表示这个node不是节点了，输出节点等操作；  
	     }  
	   }  
	 }  

	//获得节点属性名key为name的value  
	public void findLinks(Document document) throws DocumentException {  
	   List list = document.selectNodes( "//ehcache/cache/@name" );  
	   for (Iterator iter = list.iterator(); iter.hasNext(); ) {  
	      Attribute attribute = (Attribute) iter.next();  
	      String url = attribute.getValue();  
	      System.out.println(url);  
	   }  
	}  
  
	/* 
	* 创建一个documnet文档 
	*/  
	public Document createDocument() {  
	   Document document = DocumentHelper.createDocument();  
	   Element root = document.addElement( "root" );  
	   Element author1 = root.addElement( "author" )  
	         .addAttribute( "name", "James" )  
	         .addAttribute( "location", "UK" )  
	         .addText( "James Strachan" );  
	   Element author2 = root.addElement( "author" )  
	         .addAttribute( "name", "Bob" )  
	         .addAttribute( "location", "US" )  
	         .addText( "Bob McWhirter" );  
	   return document;  
	}  

    //写入xml文件  
	public void write(Document document) throws IOException {  
	  // lets write to a file  
	  XMLWriter writer = new XMLWriter(new FileWriter( "output.xml" ));  
	  writer.write( document );  
	  writer.close();  
	  // Pretty print the document to System.out  
	  OutputFormat format = OutputFormat.createPrettyPrint();  
	  writer = new XMLWriter( System.out, format );  
	  writer.write( document );  
	  // Compact format to System.out  
	  format = OutputFormat.createCompactFormat();  
	  writer = new XMLWriter( System.out, format );  
	  writer.write( document );  
	}  

	
	public static void main(String[] args) throws DocumentException,IOException{
		// TODO Auto-generated method stub
	     MyJdomReaderXML mjrx = new MyJdomReaderXML();  
	     Document d = mjrx.parse("D://Java_wwy//workspace//TestJAVAInfo//src//Test_Dom4j//xmlContext.xml");  
	     mjrx.OutXMLInfo(d);  ///遍历一个xml的内容
	//     System.out.println("------one----------");  
	 //    mjrx.treeWalk(d);  
	 //    System.out.println("------two----------");  
	 //    mjrx.findLinks(d);  

	}

}
