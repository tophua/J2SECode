package Test_XML;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMTest implements XmlDocument{
	private Document document;
	@Override
	public void parserXml(String fileName){
		try{
			DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			document=(Document) db.parse(fileName);
			NodeList users=((org.w3c.dom.Node) document).getChildNodes();
			
			for(int i=0;i<users.getLength();i++){
				Node user=(Node) users.item(i);
				NodeList userInfo=user.getChildNodes();
				for(int j=0;j<userInfo.getLength();j++){
					Node node=(Node) userInfo.item(j);
					NodeList userMeta=node.getChildNodes();
					for(int k=0;k<userMeta.getLength();k++){
						if(userMeta.item(k).getNodeName() != "#text"){
							System.out.println(userMeta.item(k).getNodeName()
									+":"+userMeta.item(k).getTextContent());
						}
					}
				}
			}
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
			System.out.println("1");
		}catch(ParserConfigurationException e){
			e.printStackTrace();
			System.out.println("2");
		}catch(SAXException e){
			e.printStackTrace();
			System.out.println("3");
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("4");
		}	
	}
}
