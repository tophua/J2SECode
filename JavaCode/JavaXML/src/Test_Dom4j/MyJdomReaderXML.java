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
		Element root = document.getRootElement();//��ø��ڵ㣻  
		 System.out.println(root.getName()); //�������users
		//���е�������ȡ���ڵ��µ����нڵ���ӽڵ��µ����нڵ�  
		for (Iterator i = root.elementIterator(); i.hasNext();) {  
		   Element element = (Element) i.next();  
		   System.out.println(element.getName());  //�������user
		   System.out.println(((Element)element.elementIterator().next()).getName());  
		   for (Iterator j = element.elementIterator(); j.hasNext();) {  
			   Element el2 = (Element) j.next();  			
			   for(Iterator k=el2.elementIterator();k.hasNext();){
				   System.out.println(((Element) k.next()).getName());  
			   }
			   System.out.println(((Element) j.next()).getName());  
		   }  
		}  
		//��ȡ�ڵ���Ϊuser�������ӽڵ�  
		for (Iterator i = root.elementIterator("user"); i.hasNext();) {  
		  Element u1 = (Element) i.next();  
		  System.out.println(u1.getName());  
		}  
		//��ȡ���ڵ����������  
		for (Iterator i = root.attributeIterator(); i.hasNext();) {  
		  Attribute attribute = (Attribute) i.next();  
		  System.out.println(attribute.getName());   //û����Ϣ
		  }  
		
		for (Iterator i = root.elementIterator("name"); i.hasNext();) {  
			  Element name1 = (Element) i.next();  
			  System.out.println(name1.getName()+":"+name1.getText());  //����Usersֱ���ӽڵ㲻��name������û��Ϣ
			} 
		
		}  

	
	 /* 
	 * ���Ը��ݽڵ����ֶ�ȡ�ڵ㣬Ҳ���Զ�ȡ�ڵ����key��value 
	*/  
	public void readNodes(Document document) {  
	   List list = document.selectNodes( "//users/user" );   //fooΪ���ڵ㣬��ø��ڵ��µ�bar�ڵ�  
	   Node node = document.selectSingleNode( "//users/user/sex" );  //�����Ϊauthor�ĵ�һ �ڵ�  
	   String name = node.valueOf( "@name" ); //��ýڵ���������Ϊname��value  
	}  
	/* 
	* ���xml�ļ��ܴ������£�������ķ����ܷ�ʱ������ �����õݹ��������xml�ļ� 
	*/  
	public void treeWalk(Document document) {  
	   treeWalk(document.getRootElement());  
	}  
	/* 
	* �ݹ���ã�����ÿһ�����ڵ���Ϊ���� 
	*/  
	public void treeWalk(Element element) {  
	   for (int i = 0, size = element.nodeCount(); i < size; i++) {  
	    Node node = element.node(i);  
	    if (node instanceof Element) {//���nodeʵ����Element�ӿڣ���ô�ͱ�ʾnode��һ���ڵ㡣�ٵݹ�  
	       treeWalk((Element) node);  
	       System.out.println(((Element) node).getName()+":"+node.valueOf("@name"));  
	     } else {//���û��ʵ��Element�ӿڣ���ô�ͱ�ʾ���node���ǽڵ��ˣ�����ڵ�Ȳ�����  
	     }  
	   }  
	 }  

	//��ýڵ�������keyΪname��value  
	public void findLinks(Document document) throws DocumentException {  
	   List list = document.selectNodes( "//ehcache/cache/@name" );  
	   for (Iterator iter = list.iterator(); iter.hasNext(); ) {  
	      Attribute attribute = (Attribute) iter.next();  
	      String url = attribute.getValue();  
	      System.out.println(url);  
	   }  
	}  
  
	/* 
	* ����һ��documnet�ĵ� 
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

    //д��xml�ļ�  
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
	     mjrx.OutXMLInfo(d);  ///����һ��xml������
	//     System.out.println("------one----------");  
	 //    mjrx.treeWalk(d);  
	 //    System.out.println("------two----------");  
	 //    mjrx.findLinks(d);  

	}

}
