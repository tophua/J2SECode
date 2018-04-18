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
 ////�� Array ������ Json ����ʹ�� JSONArray ���Խ��� Array ����
  /* �� Array ������ Json ��*/
	   String[] str = { "Jack", "Tom", "90", "true" };
	   JSONArray json = JSONArray.fromObject(str);
	   System.err.println(json);

  /*�������飬ע�����ֺͲ���ֵ*/
	   Object[] o = { "����", "�Ϻ�", 89, true, 90.87 };
	   json = JSONArray.fromObject(o);
	   System.err.println(json);

  /* ʹ�ü�����*/
       List<String> list = new ArrayList<String>();
       list.add("Jack");
       list.add("Rose");
       json = JSONArray.fromObject(list);
       System.err.println(json);

  /* ʹ�� set ��*/
       Set<Object> set = new HashSet<Object>();
       set.add("Hello");
       set.add(true);
       set.add(99);
       json = JSONArray.fromObject(set);
       System.err.println(json);

///�� JavaBean/Map ������ JSON ��
  /* ���� HashMap*/
       Map<String, Object> map = new HashMap<String, Object>();
       map.put("name", "Tom");
       map.put("age", 33);
       JSONObject jsonObject = JSONObject.fromObject(map);
       System.out.println(jsonObject);

  /* ���� JavaBean*/
       Person person = new Person("A001", "Jack");
       jsonObject = jsonObject.fromObject(person);
       System.out.println(jsonObject);

  /* ����Ƕ�׵Ķ���*/
       map.put("person", person);
       jsonObject = jsonObject.fromObject(map);
       System.out.println(jsonObject);

///ʹ�� JsonConfig �������ԣ������� JavaBean/Map
       JsonConfig config = new JsonConfig();
       config.setExcludes(new String[] { "name" });                      // ָ����ת��ʱ��������Щ����
       Person person1 = new Person("A001", "Jack");
       JSONObject jsonObject1 = JSONObject.fromObject(person1, config);    // ��ת��ʱ����֮ǰ�����ö���
       System.out.println(jsonObject1);

 ///�� Json ��ת���� Array
       JSONArray jsonArray = JSONArray.fromObject("[89,90,99]");
       Object array = JSONArray.toArray(jsonArray);
       System.out.println(array);
       System.out.println(Arrays.asList((Object[]) array));

 ///�� Json ��ת�� JavaBean/Map ,jsonת��Ϊjava����
       /*�� Json ��ʽ���ַ���ת��Ϊ Map*/
       String str1 = "{\"name\":\"Tom\",\"age\":\"90\"}";
       JSONObject jsonObject2 = JSONObject.fromObject(str1);
       Map<String, Object> map2 = (Map<String, Object>) JSONObject.toBean(jsonObject2, Map.class);
       System.out.println(map2);

       /* �� Json ��ʽ���ַ���ת��Ϊ JavaBean*/
       str1 = "{\"id\":\"A001\",\"name\":\"Jack\"}";
       jsonObject2 = JSONObject.fromObject(str1);
       System.out.println(jsonObject2);
   //    Person person2 = (Person) JSONObject.toBean(jsonObject2, Person.class);
   //    System.out.println(person2);
       
       /*Json�ַ���ת��Ϊlist����������*/
       String arrayStr="[{\"name\":\"Tom\",\"age\":\"90\"},{\"name\":\"Jack\",\"age\":\"100\"}]";
          //ת��Ϊlist
//       List<Person> list2=(List<Person>)JSONArray.toList(JSONArray.fromObject(arrayStr), Person.class);     
//       for (Person stu : list2) {
//           System.out.println(stu);
//       }
          //ת��Ϊ����
//       Person[] ss =(Person[])JSONArray.toArray(JSONArray.fromObject(arrayStr),Person.class);
//       for (Person pe : ss) {
//           System.out.println(pe);
//       }


	}

}
