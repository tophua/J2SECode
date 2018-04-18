package Test_JSON_lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.*;

public class JsonLib_JsonObjectAndArray {

	public static void CreateJson(){
		//String构造JSONObject  
		String name = "{'name':'JTZen9'}";  
		JSONObject jsonObject3 = JSONObject.fromObject(name);  
		System.out.println(jsonObject3);  
		//Java Array构造JSONArray  
		String[] strings = {"1","2","3"};  
		JSONArray jsonArray1 = JSONArray.fromObject(strings);  
		System.out.println(jsonArray1);  
		//Beans构造JSONObject  
		Student2 student = new Student2();  
		student.setAge(21);  
		student.setName("JTZen9");  
		student.setSex("male");  
		JSONObject jsonObject1 = JSONObject.fromObject(student);  
		System.out.println(jsonObject1);  
		//Map构造JSONObject  
		Map<String,Object> map = new HashMap<String,Object>();  
		map.put("name", "JTZen9");  
		map.put("num", strings);  
		JSONObject jsonObject2 = JSONObject.fromObject(map);  
		System.out.println(jsonObject2);  
		//List构造JSONObject  
		List<Object> list = new ArrayList<Object>();  
		list.add("JTZen9");  
		list.add(strings);  
		list.add(map);  
		list.add(student);  
		JSONArray jsonArray2 = JSONArray.fromObject(list);  
		System.out.println(jsonArray2);  

	}
	
	public static void AddJsonInfo(){
	    Map<String,Object> map = new HashMap<String,Object>();  
	    map.put("name", "JTZen9");  
	    map.put("age", 21);  
	    Student2 student = new Student2();  
	    student.setAge(21);  
	    student.setName("JTZen9");  
	    student.setSex("male");  
	    JSONObject jsonObject = new JSONObject();  
	    jsonObject.put("name", "JTZen9");  
	    System.out.println(jsonObject);  
	    jsonObject.put("student", student);  //加入Bean类
	    System.out.println(jsonObject);  

        JSONArray jsonArray = new JSONArray();  
	    jsonArray.add("JTZen9");  
	    jsonArray.add(map);  
	    jsonArray.add(0,student); //在指定位置加入 ，加入Bean类 
	    System.out.println(jsonArray);  
	}
	
	public static void GetJsonInfo(){
		String jsonData = "{'name':'JTZen9','age':21}";  
		JSONObject jsonObject = JSONObject.fromObject(jsonData);  
		System.out.println(jsonObject);  
		
		String nameStr = jsonObject.getString("name");  
		System.out.println(nameStr);  
		
		int ageInt = jsonObject.optInt("age");  
		System.out.println(ageInt);  
		
		//key不存在，sexStr1为""
		String sexStr1 = jsonObject.optString("sex"); 
		if(sexStr1==null){
			sexStr1="";
		}else if(sexStr1.equals("")){
			
		}
		System.out.println("optString('sex')输出的：" + sexStr1); //默认值为空  
		
	//	String sexStr2 = jsonObject.getString("sex");  
	//	System.out.println(sexStr2);      //报错  
	}
	
	public static void ConvertToBean(){
	// 创建一个bean  
		Student2 student = new Student2();   
		student.setName("JTZen9");  
		student.setAge(21);  
		student.setSex("male");  
	// bean类转为json对象  
		JSONObject jsonObject = JSONObject.fromObject(student);   
		System.out.println(jsonObject);  
		
	// 方法一，json对象转为bean类  
		Student2 student2 = (Student2) JSONObject.toBean(jsonObject, Student2.class);  
		System.out.println(student2.getName() + " " + student2.getAge() 
		                    + " " + student2.getSex());  
	// 方法二  
		JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.setRootClass(Student2.class);  
		Student2 student3 = (Student2) JSONObject.toBean(jsonObject, jsonConfig);  
		System.out.println(student3.getName() + " " + student3.getAge()
		                  + " " + student3.getSex());  
	}
	
	public static void convertToList(){
		List<String> list = new ArrayList<String>();  
		list.add("JTZen9");  
		list.add("heiheihei");  
		list.add("DSMGYH");  
		JSONArray jsonArray = JSONArray.fromObject(list);  
		System.out.println(jsonArray);  
		
		List<String> list2 = (List<String>) JSONArray.toList(jsonArray);  
		System.out.println(list2.get(0) + " " + list2.get(1) + " " + list2.get(2));  
		
		List<Student2> listStudent = new ArrayList<Student2>();  
		for (int i = 0; i < 3; i++) {  
		  Student2 student = new Student2();  
		  student.setName("JTZen9" + i);  
		  student.setAge(i);  
		  student.setSex("male");  
		  listStudent.add(student);  
		} 	
		JSONArray sJsonArray = JSONArray.fromObject(listStudent);  
		System.out.println(sJsonArray);  
		
		// JSONArray转换为List<student>，方法一  
		List<Student2> sList1 = (List<Student2>) JSONArray.toList(sJsonArray, Student2.class);  
		System.out.println(sList1.get(1).getAge());  
		// 方法二  
		JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.setRootClass(Student2.class);  
		List<Student2> sList2 = (List<Student2>) JSONSerializer.toJava(sJsonArray, jsonConfig);  
		System.out.println(sList2.get(1).getAge());  
	}
	
	public static void FilterJsonInfo(){
		Student2 student = new Student2();  
		student.setName("JTZen9");  
	    student.setSex("male");  
	    student.setAge(21);  
	    JSONObject jsonObject1 = JSONObject.fromObject(student);  
	    System.out.println(jsonObject1);   //正常输出三个字段  
	    JsonConfig jsonConfig = new JsonConfig();  
	    jsonConfig.setExcludes(new String[]{ "sex" });  //过滤要输出的字段  
	    JSONObject jsonObject2 = JSONObject.fromObject(student, jsonConfig);  
	    System.out.println(jsonObject2.toString());  	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CreateJson();
		AddJsonInfo();
		GetJsonInfo();
		ConvertToBean();
		convertToList();
		FilterJsonInfo();
	}

}
