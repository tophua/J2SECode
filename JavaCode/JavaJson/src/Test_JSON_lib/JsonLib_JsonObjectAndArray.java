package Test_JSON_lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.*;

public class JsonLib_JsonObjectAndArray {

	public static void CreateJson(){
		//String构造JSONObject  
		String name = "{'name':'JTZen9','age':21}";  
		JSONObject jsonObject3 = JSONObject.fromObject(name);  
		System.out.println(jsonObject3);  
		  //去遍历
		String strname=jsonObject3.getString("name");
		int iage=jsonObject3.getInt("age");
		
		//Java Array构造JSONArray  
		String[] strings = {"1s","2","3"};  //就是[1s, 2, 3]
		JSONArray jsonArray1 = JSONArray.fromObject(strings);  
		System.out.println(jsonArray1);  //["1s","2","3"]
		  //去遍历
		String[] strArr=new String[3]; //初始化值[null, null, null]
		for(int i=0;i<jsonArray1.size();i++){
		//	strArr[i]=jsonArray1.getString(i);  //结果是[1s, 2, 3]
			strArr[i]=jsonArray1.getString(i).toString();//结果也是[1s, 2, 3]
		}
		
		//Beans构造JSONObject  
		Student2 student = new Student2();  
		student.setAge(21);  
		student.setName("JTZen9");  
		student.setSex("male");  
		JSONObject jsonObject1 = JSONObject.fromObject(student);  
		System.out.println(jsonObject1);  //{"age":21,"name":"JTZen9","sex":"male"}
		   //去遍历
		String strname1=jsonObject1.getString("name");
		String strsex1=jsonObject1.get("sex").toString();
		int iage1=jsonObject1.getInt("age");	
		
		//Map<String,Object>，其中Object为String[]，构造JSONObject  
		Map<String,Object> map = new HashMap<String,Object>();  
		map.put("name", "JTZen9");  
		map.put("num", strings);  
		JSONObject jsonObject2 = JSONObject.fromObject(map);  
		System.out.println(jsonObject2);  //{"num":["1s","2","3"],"name":"JTZen9"}
		  //根据key去遍历
		Object ObName=jsonObject2.get("name");
		String ObName1=jsonObject2.get("name").toString();
	//	Object ObNum=jsonObject2.get("num"); //["1s","2","3"]
		JSONArray NumArray=jsonObject2.getJSONArray("num"); //["1s","2","3"]
		String[] strArr1=new String[3]; //初始化值[null, null, null]
		for(int i=0;i<NumArray.size();i++){
			strArr1[i]=jsonArray1.getString(i).toString();//结果也是[1s, 2, 3]
		}	
		
		//List构造JSONObject  
		List<Object> list = new ArrayList<Object>();  
		list.add("JTZen9");  
		list.add(strings);  
		list.add(map);  
		list.add(student);  
		JSONArray jsonArray2 = JSONArray.fromObject(list);  
		System.out.println(jsonArray2); 
		//["JTZen9",["1s","2","3"],{"num":["1s","2","3"],"name":"JTZen9"},
		//{"age":21,"name":"JTZen9","sex":"male"}]		
	}
	
	public static void AddJsonInfo(){
	    Map<String,Object> map = new HashMap<String,Object>();  
	    map.put("name", "JTZen9");  
	    map.put("age", 21);  
	    Student2 student = new Student2();  
	    student.setAge(21);  
	    student.setName("JTZen9");  
	    student.setSex("male");  
	    ///构建JSONObject
	    JSONObject jsonObject = new JSONObject();  
	    jsonObject.put("name", "JTZen9");  
	    jsonObject.put("student", student);  //加入Bean类	    
	    System.out.println(jsonObject);  
        //去遍历解析
	    Object ObName=jsonObject.get("name");
	    String strName=jsonObject.get("name").toString();
	    String strName1=jsonObject.getString("name");
	    
	    JSONObject stuArray=jsonObject.getJSONObject("student");
	    //{"age":21,"name":"JTZen9","sex":"male"}
	    Object Obage=stuArray.get("name");
	    
	    
        JSONArray jsonArray = new JSONArray();  
	    jsonArray.add("JTZen9");  
	    jsonArray.add(map);  
	    jsonArray.add(0,student); //在指定位置加入 ，加入Bean类 
	    System.out.println(jsonArray);  
	}
	
	public static void GetJsonInfo(){
	 //String转换为Json，然后对这个Json进行解析
		String jsonData = "{'name':'JTZen9','age':21}";  
		JSONObject jsonObject = JSONObject.fromObject(jsonData);  
		System.out.println(jsonObject);  //{"name":"JTZen9","age":21}
		
		String nameStr = jsonObject.getString("name");  	
		int ageInt = jsonObject.optInt("age");  
		System.out.println(nameStr+" "+ageInt);  //JTZen9 21
		
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
	// bean类转为json对象(JSONObject)  
		JSONObject jsonObject = JSONObject.fromObject(student);   
		System.out.println(jsonObject);  
		
	// 方法一，json对象(JSONObject)转为bean类  
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
		//List<String>转换为JSONArray
		JSONArray jsonArray = JSONArray.fromObject(list);//[JTZen9, heiheihei, DSMGYH]  
		System.out.println(jsonArray); // ["JTZen9","heiheihei","DSMGYH"]
		
		//方法一, JSONArray转换为List<String>
		List<String> list2 = (List<String>) JSONArray.toList(jsonArray);  
		System.out.println(list2.get(0) + " " + list2.get(1) + " " + list2.get(2));  
		
		//方法二, JSONArray转换为List<String>
		//for循环解析
		List<String> listStr=new ArrayList<String>();
		for(int i=0;i<jsonArray.size();i++){
			String strV=(String)jsonArray.get(i).toString();
			listStr.add(strV);
		}
		
		
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
		
		//JSONArray转换为List<student>方法
		// 方法一,当list数量小的时候，这种转换是可以的，但当数量大的时候，不推荐
		List<Student2> sList1 = (List<Student2>) JSONArray.toList(sJsonArray, Student2.class);  
		System.out.println(sList1.get(1).getAge());  
		
		// 方法二  
		JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.setRootClass(Student2.class);  
		List<Student2> sList2 = (List<Student2>) JSONSerializer.toJava(sJsonArray, jsonConfig);  
		System.out.println(sList2.get(1).getAge());  
		
		//方法三，采用for循环遍历
		List<Student2> listStu=new ArrayList<Student2>();
		for(int i=0;i<sJsonArray.size();i++){
			JSONObject jsonOb=sJsonArray.getJSONObject(i);
			Student2 st2=new Student2();
			st2.setName(jsonOb.get("name").toString());
			st2.setAge(jsonOb.getInt("age"));
			st2.setSex(jsonOb.getString("sex"));
			listStu.add(st2);
		}
		
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
