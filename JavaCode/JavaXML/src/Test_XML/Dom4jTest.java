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
		/*  ��������
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
		   Element users=document.getRootElement();  //��ȡ���ڵ�
		   //���е�������ȡ���ڵ��µ����нڵ���ӽڵ��µ����нڵ�
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
	/* ��Ӧ������������
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
	 * <?xml version="1.0" encoding="utf-8"?>
<!--������ע��-->
<Students>
  <student id="101">
    <name>����</name>
    <sax>Boy</sax>
    <test>
      <Id>131021</Id>
    </test>
  </student>
  <student id="102">
    <name>����</name>
    <sax>girl</sax>
    <test>
      <Id>131023</Id>
    </test>
  </student>
</Students>
	 */
	void CreateXML(){
		//����һ��xml�ĵ�
		Document doc=DocumentHelper.createDocument();
		//��xml�����ע��
		doc.addComment("������ע��");
		//����һ����Ϊstudents�Ľڵ㣬�Ǹ��ڵ�
		Element root=doc.addElement("Students");
		//��root�ڵ��ϴ���һ����Ϊstudent�Ľڵ�
		Element stuEle=root.addElement("student");
		//��student�������
		stuEle.addAttribute("id", "101");
		//��student�ڵ����һ���ӽڵ�
		Element nameEle=stuEle.addElement("name");
		Element saxEle=stuEle.addElement("sax");
		//�����ӽڵ���ı�
		nameEle.setText("����");
		saxEle.setText("Boy");
		   //��student�ڵ������test,��������ӽڵ�Id
		Element test=stuEle.addElement("test");
		Element IdEle=test.addElement("Id");
		  //�����ӽڵ�Id���ı�
		IdEle.setText("131021");
		
///�ٴμ�һ��student, id=102��
		//��root�ڵ��ϴ���һ����Ϊstudent�Ľڵ�
		Element stuEle2=root.addElement("student");
		//��student�������
		stuEle2.addAttribute("id", "102");
		//��student�ڵ����һ���ӽڵ�
		Element nameEle2=stuEle2.addElement("name");
		Element saxEle2=stuEle2.addElement("sax");
		//�����ӽڵ���ı�
		nameEle2.setText("����");
		saxEle2.setText("girl");
		   //��student�ڵ������test,��������ӽڵ�Id
		Element test2=stuEle2.addElement("test");
		Element IdEle2=test2.addElement("Id");
		  //�����ӽڵ�Id���ı�
		IdEle2.setText("131023");
		
		//���ڸ�ʽ��xml���ݺ�����ͷ����ǩ
		OutputFormat format=OutputFormat.createPrettyPrint();
		//����xml�ĵ��ı���Ϊutf-8
		format.setEncoding("utf-8");
		Writer out;
		try{
			//����һ�����������
			out=new FileWriter("D://Java_wwy//workspace//TestJAVAInfo//src//Test_XML//new.xml");
			//����һ��dom4j����xml�Ķ���
			XMLWriter writer1=new XMLWriter(out, format);
			//����write������doc�ĵ�д��ָ��·��
			writer1.write(doc);
			writer1.close();
			System.out.print("����xml�ɹ�");
		}catch(IOException e){
			System.out.print("����xmlʧ��");
			e.printStackTrace();
		}
	}
	
	/*
	 * ����id��ȡ��Ϣ
	 */
	public void readXmlInfoFromID(String filename,String id) throws DocumentException{
		SAXReader sax1=new SAXReader();
		Document doc1=sax1.read(new File(filename));
		Element e=(Element)doc1.selectSingleNode("/users/user[@id='"+id+"']");
		Element name=e.element("name");
		System.out.println("Name:"+name.getText());			
	}
	
	/*
	 * ��ȡ����name����Ϣ
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
	 * �����Ϣ
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
	 * ɾ��ָ��id����Ϣ
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
	///�޸�xml�ļ������ݣ������Ϊһ�����ļ�(Ҳ�������Լ���xml��д��)
	/*
	 * @param filename
	 *       �޸Ķ����ļ�
	 * @param newfilename
	 *       �޸ĺ����Ϊ���ļ�
	 */
	public  int  ModifyXMLFile(String filename,String newfilename) throws DocumentException{
		int returnVlaue=0;
		try{
			SAXReader reader1 = new SAXReader();  
			Document doc1 = reader1.read(new File(filename));
			
			//��:1�����student�ڵ������Ϊ101,��Ϊ201
			List list1= doc1.selectNodes("Students/student/@id"); ///�������jaxen-1.1-beta-6.jar�ſ��Ե���
			Iterator iter1=list1.iterator();
			while(iter1.hasNext()){
				Attribute attribute=(Attribute) iter1.next();
				if(attribute.getValue().equals("101")){
					attribute.setValue("201");
				}
			}
			//�޸�2���޸�student�ڵ���name�ڵ�����ݣ���
			//��student�ڵ��¼���date�ڵ㣬�����һ������
			List list2=doc1.selectNodes("Students/student/name");
			iter1=list2.iterator();
			if(iter1.hasNext()){
				Element ownerElement = (Element) iter1.next();  
				ownerElement.setText("����Copy");  
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
			//�޸�3�����saxΪboy����ɾ��
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
			//�޸�4���޸�101��saxֵ
			Element e = (Element)doc1.selectSingleNode("/Students/student[@id='"+"102"+"']");  
			Element n = e.element("sax");  
			n.setText("Test_Boy");  

			//		Element n=Ele.element("sax");
			//		n.setText("Test_boy");
		try{
			//��document������д���ļ���
			XMLWriter writer1=new XMLWriter(new FileWriter(new File(newfilename)));
			writer1.write(doc1);
			writer1.close();
			//ִ�гɹ�����Ҫ����1
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
     * ��ʽ���ĵ��������������,------------�÷����д�����
     */
	public int  formatXmlFile(String filename){
		int nVlaue=0;
		try{
			//����һ��xml�ĵ�
			Document doc=DocumentHelper.createDocument();
			XMLWriter writer1=new XMLWriter(new FileWriter(new File(filename)));
//			writer1.write(doc);
//			writer1.close(); //��ִ���꣬�����һ��xml�ļ�����������<?xml version="1.0" encoding="UTF-8"?>
			
		//	SAXReader saxReader=new SAXReader();
		//	Document doc1=saxReader.read(filename);
			XMLWriter writer = null;
			/** ��ʽ�����,����IE���һ�� */
			OutputFormat format = OutputFormat.createPrettyPrint();
			/** ָ��XML���� */
			format.setEncoding("GBK");
			writer = new XMLWriter(new FileWriter(new File(filename)), format);
			writer.write(doc);
			writer.close(); 
			/** ִ�гɹ�,�践��1 */
			nVlaue = 1;
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return nVlaue;
	}
   }
