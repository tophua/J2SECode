package Test_XML;

import java.io.IOException;
import java.util.List;

import javax.lang.model.element.Element;
import javax.swing.text.Document;

import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class JDOMTest implements XmlDocument{
	
	@Override
	public void parserXml(String fileName){
		SAXBuilder builder=new SAXBuilder();
//		try{
//			Document document=(Document) builder.build(fileName);
////			Element users=((org.jdom.Document) document).getRootElement();
////			List<E> userList=((org.jdom.Element) users).getChildren("user");
////			for(int i=0;i<userList.size();i++){
////				Element user=(Element) userList.get(i);
////				List userInfo=((org.jdom.Element) user).getChildren();
////				for(int j=0;j<userInfo.size();j++){
////					System.out.println(((Element)userInfo.get(j)).getName()
////							+":"+((Element)userInfo.get(j)).getValue());
////				}
//			}
//			
//		}catch(JDOMException e){
//			e.printStackTrace();
//			
//		}catch(IOException e){
//			e.printStackTrace();
//		}
	}

}
