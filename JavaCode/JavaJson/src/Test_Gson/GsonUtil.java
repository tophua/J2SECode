package Test_Gson;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;

public class GsonUtil {
/////////------重点是parse()和format()两个接口函数
	/*
     * 将json字符串转换成对象,该对象可以是list、map、Bean
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
    ** 将json转成数组 
	* @param json 
	* @param type 
	* @return 
	*/  
	public static <T> T[] parseArr(String json, Class<T[]> type) {  
	    return parse(json, type);  
	}  
	
	/*
	 * * 将json转成集合 
	 * @param json 
	 * @param type 
	 * @return 
	*/  
	public static <T> ArrayList<T> parseList(String json, Class<T[]> type) {  
	   return new ArrayList<T>(Arrays.asList(parse(json, type)));  
	}  

    /*
      * * 将对象转成json字符串,该对象可以是list、map、Bean
	  * @param o 
	  * @return 
	*/  
	public static String format(Object o) {  
	    Gson gson = new Gson();  
	    return gson.toJson(o);  
	}  

	/* -------目前不知道该接口函数的功能
     * 将对象转成json字符串 并使用url编码 
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
