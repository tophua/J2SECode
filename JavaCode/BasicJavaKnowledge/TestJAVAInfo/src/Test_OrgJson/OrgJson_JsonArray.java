package Test_OrgJson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.*;   //这就是json.org.jar
import java.lang.*;

public class OrgJson_JsonArray {

	 public static void constructorTest() {  
		String jsonStr = "[{'name':'JTZen9','age':21}]";  
		JSONArray strJson;
		try {
			strJson = new JSONArray(jsonStr);// 传入字符串  
			System.out.println("构造参数为String类：" + strJson);  
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
		

        List<Object> list = new ArrayList<Object>();  
		for (int i = 1; i < 3; i++) {  
		     Map<String,Object> map = new HashMap<String,Object>();  
		     map.put("title", "java程序设计 第" + i + "版");  
		     map.put("price", i*20);  
		     list.add(map);  
		}  
		JSONArray mapJson = new JSONArray(list);// 传入Collection类型  
		System.out.println("构造参数为Collection类：" + mapJson);  
		int[] numlist = new int[10];  
		for (int i = 0; i < numlist.length; i++) {  
		    numlist[i] = i;  
		}  
		///好像不行
//		JSONArray arrayJson = new JSONArray(numlist);   // 传入Array类型，实例1  
//		System.out.println(arrayJson);  
//		StudentOrg[] student = {new StudentOrg(),new StudentOrg()};  
//		student[0].setAge(21);  
//		student[0].setName("JTZen9");  
//		student[0].setSex("male");  
//		student[1].setAge(21);  
//		student[1].setName("heiheihei");  
//		student[1].setSex("female");  
//		JSONArray beanJson = new JSONArray(student);    // 传入Array类型，实例2  
//		System.out.println("构造参数为Array类：" + beanJson);  
	}  

	public static void putMethodTest() {  
		JSONArray jsonArray1 = new JSONArray();  
		jsonArray1.put("JTZen9");  
		jsonArray1.put(21);  
		jsonArray1.put("male");  
		System.out.println(jsonArray1);  
		
		JSONArray jsonArray2 = new JSONArray();  
		Map<String,Object> map = new HashMap<String,Object>();  
		map.put("title", "java程序设计 第2版");  
		map.put("price", 20);  
		jsonArray2.put(map);        //传入一个map  
		System.out.println("传入一个Map：" + jsonArray2);  
		
		map.clear();  
		map.put("title", "java程序设计 第3版");  
		map.put("price", 30);  
		jsonArray2.put(map);        //没有下标的直接在结果后面添加  
		System.out.println("没有指定下标：" + jsonArray2);  
		
		map.clear();  
		map.put("title", "java程序设计 第1版");  
		map.put("price", 10);  
		try {
			jsonArray2.put(0,map);
			System.out.println("添加到第一个位置：" + jsonArray2);  
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      //使用下标可以添加到自定义的位置  
		
		StudentOrg[] student = { new StudentOrg(), new StudentOrg() };  
		student[0].setAge(21);  
		student[0].setName("JTZen9");  
		student[0].setSex("male");  
		student[1].setAge(21);  
		student[1].setName("heiheihei");  
		student[1].setSex("female");  
		JSONArray jsonArray3 = new JSONArray();   
		jsonArray3.put(student);  
		System.out.println("注意输出结果：" + jsonArray3);  
	}  

	   
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		constructorTest();
	}

}
