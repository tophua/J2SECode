package Test_OrgJson;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONStringer;
import org.json.JSONWriter;

public class OrgJson_JsonStringer {

	 public static void JSONStringerTest() throws Exception {  
		PrintWriter writer = new PrintWriter("D:\\Java_wwy\\workspace"
				+ "\\TestJAVAInfo\\src\\Test_OrgJson\\test.txt");  
		JSONWriter jsonWriter = new JSONWriter(writer);  
		jsonWriter.object().key("name").value("JTZen9").key("age").
		          value(21).key("sex").value("male").endObject();  
		writer.flush();  
		writer.close();  //这是向txt中写入数据

        Map<String,Object> map1 = new HashMap<String,Object>();  
		map1.put("age", 21);  
		map1.put("sex", "male");  
		map1.put("name", "jtzen9");  
		Map<String,Object> map2 = new HashMap<String,Object>();  
		map2.put("age", 21);  
		map2.put("sex", "female");  
		map2.put("name", "heiheihei");  
		JSONStringer jsonStringer = new JSONStringer();  
		jsonStringer.array().value(map1).value(map2).endArray();  
		System.out.println(jsonStringer);  
	}  

	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			JSONStringerTest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
