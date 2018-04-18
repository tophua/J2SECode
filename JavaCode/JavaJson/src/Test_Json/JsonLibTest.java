package Test_Json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JsonLibTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
 ////将 Array 解析成 Json 串。使用 JSONArray 可以解析 Array 类型
  /* 将 Array 解析成 Json 串*/
	   String[] str = { "Jack", "Tom", "90", "true" };
	   JSONArray json = JSONArray.fromObject(str);
	   System.err.println(json);

  /*对象数组，注意数字和布而值*/
	   Object[] o = { "北京", "上海", 89, true, 90.87 };
	   json = JSONArray.fromObject(o);
	   System.err.println(json);

  /* 使用集合类*/
       List<String> list = new ArrayList<String>();
       list.add("Jack");
       list.add("Rose");
       json = JSONArray.fromObject(list);
       System.err.println(json);

  /* 使用 set 集*/
       Set<Object> set = new HashSet<Object>();
       set.add("Hello");
       set.add(true);
       set.add(99);
       json = JSONArray.fromObject(set);
       System.err.println(json);

///将 JavaBean/Map 解析成 JSON 串
  /* 解析 HashMap*/
       Map<String, Object> map = new HashMap<String, Object>();
       map.put("name", "Tom");
       map.put("age", 33);
       JSONObject jsonObject = JSONObject.fromObject(map);
       System.out.println(jsonObject);

  /* 解析 JavaBean*/
       Person person = new Person("A001", "Jack");
       jsonObject = jsonObject.fromObject(person);
       System.out.println(jsonObject);

  /* 解析嵌套的对象*/
       map.put("person", person);
       jsonObject = jsonObject.fromObject(map);
       System.out.println(jsonObject);

///使用 JsonConfig 过虑属性：适用于 JavaBean/Map
       JsonConfig config = new JsonConfig();
       config.setExcludes(new String[] { "name" });                      // 指定在转换时不包含哪些属性
       Person person1 = new Person("A001", "Jack");
       JSONObject jsonObject1 = JSONObject.fromObject(person1, config);    // 在转换时传入之前的配置对象
       System.out.println(jsonObject1);

 ///将 Json 串转换成 Array
       JSONArray jsonArray = JSONArray.fromObject("[89,90,99]");
       Object array = JSONArray.toArray(jsonArray);
       System.out.println(array);
       System.out.println(Arrays.asList((Object[]) array));

 ///将 Json 串转成 JavaBean/Map ,json转换为java对象
       /*将 Json 形式的字符串转换为 Map*/
       String str1 = "{\"name\":\"Tom\",\"age\":\"90\"}";
       JSONObject jsonObject2 = JSONObject.fromObject(str1);
       Map<String, Object> map2 = (Map<String, Object>) JSONObject.toBean(jsonObject2, Map.class);
       System.out.println(map2);

       /* 将 Json 形式的字符串转换为 JavaBean*/
       str1 = "{\"id\":\"A001\",\"name\":\"Jack\"}";
       jsonObject2 = JSONObject.fromObject(str1);
       System.out.println(jsonObject2);
   //    Person person2 = (Person) JSONObject.toBean(jsonObject2, Person.class);
   //    System.out.println(person2);
       
       /*Json字符串转换为list，对象数组*/
       String arrayStr="[{\"name\":\"Tom\",\"age\":\"90\"},{\"name\":\"Jack\",\"age\":\"100\"}]";
          //转化为list
//       List<Person> list2=(List<Person>)JSONArray.toList(JSONArray.fromObject(arrayStr), Person.class);     
//       for (Person stu : list2) {
//           System.out.println(stu);
//       }
          //转化为数组
//       Person[] ss =(Person[])JSONArray.toArray(JSONArray.fromObject(arrayStr),Person.class);
//       for (Person pe : ss) {
//           System.out.println(pe);
//       }


	}

}
