package Test_OrgJson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.*;   //这就是json.org.jar

public class OrgJsonTest {

	public static void constructorTest() {  
		String jsonStr = "{'name':'JTZen9','age':21}";  
		JSONObject strJson;
		try {
			strJson = new JSONObject(jsonStr); // 传入字符串  
			System.out.println("构造参数为String类：" + strJson);  
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
				
		Map<String, Object> map = new HashMap<String, Object>();  
		map.put("age", 21);  
		map.put("sex", "male");  
		map.put("name", "JTZen9");  
		JSONObject mapJson = new JSONObject(map); // 传入Map类型  
		System.out.println("构造参数为Map类：" + mapJson);  
		
		StudentOrg student = new StudentOrg();  
		student.setAge(21);  
		student.setName("JTZen9");  
		student.setSex("male");  

	//	JSONObject beanJson = new JSONObject(student); // 传入Bean类型  
	//	System.out.println("构造参数为Bean类：" + beanJson);  
		}  
		
	public static void putMethodTest() {  
		JSONObject jsonObject1 = new JSONObject();  
		try {
			jsonObject1.put("bookName", "JTZen9");
			jsonObject1.put("age", 21);  
			System.out.println(jsonObject1);  
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	

        JSONObject jsonObject2 = new JSONObject();  
		List<Object> list = new ArrayList<Object>();  
		for (int i = 1; i < 3; i++) {  
		    Map<String,Object> map = new HashMap<String, Object>();  
		    map.put("title", "java程序设计 第" + i + "版");  
		    map.put("price", i*20);  
		    list.add(map);  
		}  
		try {
			jsonObject2.put("book", list);
			System.out.println(jsonObject2);  
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	
		StudentOrg student = new StudentOrg();  
        student.setAge(21);  
		student.setName("JTZen9");  
		student.setSex("male");  
      //jsonObject2 = new JSONObject(student);  
		JSONObject jsonObject3 = new JSONObject();  
		 //不可以直接传bean类对象put("people",student)  
      //jsonObject3.put("people", jsonObject2);  
		System.out.println(jsonObject3);  
	}  

		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		constructorTest();
		putMethodTest();
	}

}
