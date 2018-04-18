package Test_OrgJson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.*;   //�����json.org.jar
import java.lang.*;

public class OrgJson_JsonArray {

	 public static void constructorTest() {  
		String jsonStr = "[{'name':'JTZen9','age':21}]";  
		JSONArray strJson;
		try {
			strJson = new JSONArray(jsonStr);// �����ַ���  
			System.out.println("�������ΪString�ࣺ" + strJson);  
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
		

        List<Object> list = new ArrayList<Object>();  
		for (int i = 1; i < 3; i++) {  
		     Map<String,Object> map = new HashMap<String,Object>();  
		     map.put("title", "java������� ��" + i + "��");  
		     map.put("price", i*20);  
		     list.add(map);  
		}  
		JSONArray mapJson = new JSONArray(list);// ����Collection����  
		System.out.println("�������ΪCollection�ࣺ" + mapJson);  
		int[] numlist = new int[10];  
		for (int i = 0; i < numlist.length; i++) {  
		    numlist[i] = i;  
		}  
		///������
//		JSONArray arrayJson = new JSONArray(numlist);   // ����Array���ͣ�ʵ��1  
//		System.out.println(arrayJson);  
//		StudentOrg[] student = {new StudentOrg(),new StudentOrg()};  
//		student[0].setAge(21);  
//		student[0].setName("JTZen9");  
//		student[0].setSex("male");  
//		student[1].setAge(21);  
//		student[1].setName("heiheihei");  
//		student[1].setSex("female");  
//		JSONArray beanJson = new JSONArray(student);    // ����Array���ͣ�ʵ��2  
//		System.out.println("�������ΪArray�ࣺ" + beanJson);  
	}  

	public static void putMethodTest() {  
		JSONArray jsonArray1 = new JSONArray();  
		jsonArray1.put("JTZen9");  
		jsonArray1.put(21);  
		jsonArray1.put("male");  
		System.out.println(jsonArray1);  
		
		JSONArray jsonArray2 = new JSONArray();  
		Map<String,Object> map = new HashMap<String,Object>();  
		map.put("title", "java������� ��2��");  
		map.put("price", 20);  
		jsonArray2.put(map);        //����һ��map  
		System.out.println("����һ��Map��" + jsonArray2);  
		
		map.clear();  
		map.put("title", "java������� ��3��");  
		map.put("price", 30);  
		jsonArray2.put(map);        //û���±��ֱ���ڽ���������  
		System.out.println("û��ָ���±꣺" + jsonArray2);  
		
		map.clear();  
		map.put("title", "java������� ��1��");  
		map.put("price", 10);  
		try {
			jsonArray2.put(0,map);
			System.out.println("��ӵ���һ��λ�ã�" + jsonArray2);  
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      //ʹ���±������ӵ��Զ����λ��  
		
		StudentOrg[] student = { new StudentOrg(), new StudentOrg() };  
		student[0].setAge(21);  
		student[0].setName("JTZen9");  
		student[0].setSex("male");  
		student[1].setAge(21);  
		student[1].setName("heiheihei");  
		student[1].setSex("female");  
		JSONArray jsonArray3 = new JSONArray();   
		jsonArray3.put(student);  
		System.out.println("ע����������" + jsonArray3);  
	}  

	   
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		constructorTest();
	}

}
