package Test_JSON_lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.*;

public class JsonLib_JsonObjectAndArray {

	public static void CreateJson(){
		//String����JSONObject  
		String name = "{'name':'JTZen9'}";  
		JSONObject jsonObject3 = JSONObject.fromObject(name);  
		System.out.println(jsonObject3);  
		//Java Array����JSONArray  
		String[] strings = {"1","2","3"};  
		JSONArray jsonArray1 = JSONArray.fromObject(strings);  
		System.out.println(jsonArray1);  
		//Beans����JSONObject  
		Student2 student = new Student2();  
		student.setAge(21);  
		student.setName("JTZen9");  
		student.setSex("male");  
		JSONObject jsonObject1 = JSONObject.fromObject(student);  
		System.out.println(jsonObject1);  
		//Map����JSONObject  
		Map<String,Object> map = new HashMap<String,Object>();  
		map.put("name", "JTZen9");  
		map.put("num", strings);  
		JSONObject jsonObject2 = JSONObject.fromObject(map);  
		System.out.println(jsonObject2);  
		//List����JSONObject  
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
	    jsonObject.put("student", student);  //����Bean��
	    System.out.println(jsonObject);  

        JSONArray jsonArray = new JSONArray();  
	    jsonArray.add("JTZen9");  
	    jsonArray.add(map);  
	    jsonArray.add(0,student); //��ָ��λ�ü��� ������Bean�� 
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
		
		//key�����ڣ�sexStr1Ϊ""
		String sexStr1 = jsonObject.optString("sex"); 
		if(sexStr1==null){
			sexStr1="";
		}else if(sexStr1.equals("")){
			
		}
		System.out.println("optString('sex')����ģ�" + sexStr1); //Ĭ��ֵΪ��  
		
	//	String sexStr2 = jsonObject.getString("sex");  
	//	System.out.println(sexStr2);      //����  
	}
	
	public static void ConvertToBean(){
	// ����һ��bean  
		Student2 student = new Student2();   
		student.setName("JTZen9");  
		student.setAge(21);  
		student.setSex("male");  
	// bean��תΪjson����  
		JSONObject jsonObject = JSONObject.fromObject(student);   
		System.out.println(jsonObject);  
		
	// ����һ��json����תΪbean��  
		Student2 student2 = (Student2) JSONObject.toBean(jsonObject, Student2.class);  
		System.out.println(student2.getName() + " " + student2.getAge() 
		                    + " " + student2.getSex());  
	// ������  
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
		
		// JSONArrayת��ΪList<student>������һ  
		List<Student2> sList1 = (List<Student2>) JSONArray.toList(sJsonArray, Student2.class);  
		System.out.println(sList1.get(1).getAge());  
		// ������  
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
	    System.out.println(jsonObject1);   //������������ֶ�  
	    JsonConfig jsonConfig = new JsonConfig();  
	    jsonConfig.setExcludes(new String[]{ "sex" });  //����Ҫ������ֶ�  
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
