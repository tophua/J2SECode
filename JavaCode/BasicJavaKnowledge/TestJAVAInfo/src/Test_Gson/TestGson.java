package Test_Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import Test_Gson.Student2;

public class TestGson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

  //int[]、String[]互转
	  int[] ints={1,2,3,4,6};
	  String strInt=GsonUtil.format(ints); 
	  int[] intTemp1=GsonUtil.parse(strInt,int[].class);
	  
	  String[] strings={"abc","def","ghi"};
	  String strStr=GsonUtil.format(strings); 
	  String[] StrTemp1=GsonUtil.parse(strStr,String[].class);
  //list<String>互转	
	  List<String> list = Arrays.asList("1", "a", "3", "rt", "5");  
	  String strlist=GsonUtil.format(list); 
	//  String strlist2=GsonUtil.formatURLString(list);
	  ArrayList<String> listTemp2=GsonUtil.parse(strlist,ArrayList.class);
	
 //Map<String,String>互转	
	  Map<String,String> mapTest1=new HashMap<String,String>();
	  mapTest1.put("1", "Tom");
	  mapTest1.put("2", "jack");
	  mapTest1.put("3", "Li");
	  String strmap=GsonUtil.format(mapTest1);
	//  String strmap2=GsonUtil.formatURLString(mapTest1);
	  HashMap<String,String> mapTemp2=GsonUtil.parse(strmap,HashMap.class);

 //Student2互转	
	  Student2 stu1=new Student2("Jack","Boy",21);
	  String strstu1=GsonUtil.format(stu1);
	//  String strstu2=GsonUtil.formatURLString(stu1);
	  Student2 stuTemp2=GsonUtil.parse(strstu1,Student2.class);
	  
 //List<Student2>互转		  
	  List<Student2> listStudent1 = new ArrayList<Student2>();  
		for (int i = 0; i < 3; i++) {  
		  Student2 student = new Student2("test1","G",i+20);  
		  listStudent1.add(student);  
	  } 	
	 String strlistStu1=GsonUtil.format(listStudent1);
	// String strlistStu2=GsonUtil.formatURLString(listStudent1);
	 List<Student2> liststuTemp2=GsonUtil.parse(strlistStu1,List.class);
	  
	}

}
