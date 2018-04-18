package Test_Dom4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class OperatorDom4j {

	public void creat(String filename){//创建xml文件  
		Document document = DocumentHelper.createDocument();  
		Element usersElement = document.addElement("Users");  
		Element userElement =  usersElement.addElement("User");  
		userElement.addAttribute("id", "0");  
		Element nameElement=userElement.addElement("Name");  
		nameElement.setText("youchuancong");  
		Element  ageElement=userElement.addElement("Age");  
		ageElement.setText("23");  
		Element userElement1 =  usersElement.addElement("User");  
		userElement1.addAttribute("id", "1");  
		Element nameElement1=userElement1.addElement("Name");  
		nameElement1.setText("cindy");  
		Element  ageElement1=userElement1.addElement("Age");  
		ageElement1.setText("23");  
		try{  
		   XMLWriter output = new XMLWriter(  
		   new FileWriter( new File(filename) ));  
		   output.write( document );  
		   output.close();  
		}catch(IOException e){
			System.out.println(e.getMessage());
		}  
	}
	public void read(String filename,String id) throws Exception{//根据id读出学生姓名  
		SAXReader saxReader = new SAXReader();  
		Document document = saxReader.read(new File(filename));  
		Element e = (Element)document.selectSingleNode("/Users/User[@id='"+id+"']");  
	    Element name = e.element("Name");  
		System.out.println("Name:"+name.getText());  
	}  
	public void readName(String filename) throws Exception{//读出所有学生的学生姓名  
		SAXReader saxReader = new SAXReader();  
		Document document = saxReader.read(new File(filename));  
		List users = document.selectNodes("//Name");  
		Iterator it = users.iterator();  
		while(it.hasNext()){  
		   Element e = (Element)it.next();  
		   System.out.println("Name:"+e.getText());  
		}  
	}  
	public void addUser(String filename,String id,String name,String age) throws Exception{  
		SAXReader saxReader = new SAXReader();//添加信息  
		Document document = saxReader.read(new File(filename));  
		Element e = (Element)document.selectSingleNode("/Users");  
		// Element e =  document.getRootElement();  
		Element user = e.addElement("User");  
		user.setAttributeValue("id", id);  
		user.addElement("Name").setText(name);  
		user.addElement("Age").setText(age);  
		XMLWriter output = new XMLWriter(  
		   new FileWriter( new File(filename) ));  
		   output.write( document );  
		   output.close();  
		}  
	public void readUser(String filename) throws Exception{//读出所有学生的基本信息  
		SAXReader saxReader = new SAXReader();  
		Document document = saxReader.read(new File(filename));  
		List l = document.selectNodes("/Users/User");  
		Iterator it = l.iterator();  
		while(it.hasNext()){  
		Element e = (Element)it.next();  
		System.out.println("id:"+e.attributeValue("id"));  
		Element name = e.element("Name");  
		System.out.println("name:"+name.getText());  
		Element age = e.element("Age");  
		System.out.println("age:"+age.getText());  
		System.out.println("#############################");  
	    }  
    }  
	public void delete(String filename,String id) throws Exception{//删除指定id的学生  
		SAXReader saxReader = new SAXReader();  
		Document document = saxReader.read(new File(filename));  
		Element e = (Element)document.selectSingleNode("/Users/User[@id='"+id+"']");  
		if(e!=null){  
		Element parent = e.getParent();  
		parent.remove(e);  
		XMLWriter output = new XMLWriter(new FileWriter( new File(filename) ));  
		output.write( document );  
		output.close();  
		}  
	}  
	public void readAll(String filename) throws Exception{//选取所有节点  
		SAXReader saxReader = new SAXReader();  
		Document document = saxReader.read(new File(filename));  
		List users = document.selectNodes("//*");  
		Iterator it = users.iterator();  
		while(it.hasNext()){  
		   Element e = (Element)it.next();  
		   System.out.println("ElementName:"+e.getName()+"||value:"+e.getText());  
		}  
	}  
	public void modify(String filename,String id,String name) throws Exception{//修改指定学号的学生姓名  
		SAXReader saxReader = new SAXReader();  
		Document document = saxReader.read(new File(filename));  
		Element e = (Element)document.selectSingleNode("/Users/User[@id='"+id+"']");  
		Element n = e.element("Name");  
		n.setText(name);  
		XMLWriter output = new XMLWriter( new FileWriter( new File(filename) ));  
		output.write( document );  
		output.close();       
	}  
}
