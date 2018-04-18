package Test_Gson;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;

public class GsonUtil {
/////////------�ص���parse()��format()�����ӿں���
	/*
     * ��json�ַ���ת���ɶ���,�ö��������list��map��Bean
	 * @param json 
	 * @param type 
	 * @return 
	*/  
   public static <T> T parse(String json, Class<T> type) {  
	    Gson gson = new Gson();  
	    T t = null;  
	    try {  
	         t = gson.fromJson(json, type);  
	    } catch (Exception e) {  
	         e.printStackTrace();  
	         return null;  
	    }  
	  return t;  
	}  
	
   /*
    ** ��jsonת������ 
	* @param json 
	* @param type 
	* @return 
	*/  
	public static <T> T[] parseArr(String json, Class<T[]> type) {  
	    return parse(json, type);  
	}  
	
	/*
	 * * ��jsonת�ɼ��� 
	 * @param json 
	 * @param type 
	 * @return 
	*/  
	public static <T> ArrayList<T> parseList(String json, Class<T[]> type) {  
	   return new ArrayList<T>(Arrays.asList(parse(json, type)));  
	}  

    /*
      * * ������ת��json�ַ���,�ö��������list��map��Bean
	  * @param o 
	  * @return 
	*/  
	public static String format(Object o) {  
	    Gson gson = new Gson();  
	    return gson.toJson(o);  
	}  

	/* -------Ŀǰ��֪���ýӿں����Ĺ���
     * ������ת��json�ַ��� ��ʹ��url���� 
	 * @param o 
	 * @return 
	 */ 
	public static String formatURLString(Object o) {  
	   try {  
	      return URLEncoder.encode(format(o), "utf-8");  
	   } catch (Exception e) {  
	      return null;  
	   }  
	}  

	
}
