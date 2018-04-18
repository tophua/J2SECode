package Test_OrgJson;

import java.io.File;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;

public class OrgJson_JsonTokener {

	public static void readJsonFile() throws Exception{  
		FileReader str=new FileReader(new File("D:\\Java_wwy\\workspace"
				+ "\\TestJAVAInfo\\src\\Test_OrgJson\\test.txt")); //好像不行
//		JSONTokener jsonTokener = new JSONTokener(str);  
//		JSONObject jsonObject = new JSONObject(jsonTokener);  
//		System.out.println(jsonObject);   
//		System.out.println("姓名：" + jsonObject.getString("name"));  
//		System.out.println("年龄：" + jsonObject.getInt("age"));  
	} 

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			readJsonFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
