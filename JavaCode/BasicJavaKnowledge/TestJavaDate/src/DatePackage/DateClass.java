package DatePackage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      Date date1=new Date();
      System.out.println(date1);
      //////结果是：  Mon Jan 29 13:45:53 CST 2018
      
      System.out.println("Year年:"+date1.getYear()+"  Month月:"+date1.getMonth()
      +"  Date日:"+date1.getDate()+"  hour小时:"+date1.getHours()+"  minute分钟:"+
    		  date1.getMinutes()+"  Second秒:"+date1.getSeconds()+"  day星期:"+date1.getDay());
     //////结果是：   Year年:118  Month月:0  Date日:29  hour小时:13  minute分钟:45  Second秒:53  day星期:1
      
//long相对时间、Date对象、String对象转换
   //Date--long
	  Date date2=new Date(2018-1900,12-3,23);
      long t=date2.getTime();
      System.out.println("Date:"+date2+"--long:"+t);     
      //////结果是：  Date:Tue Oct 23 00:00:00 CST 2018--long:1540224000000
      
   //long--Date
      long mseconds=date2.getTime()/1000;     
      Date date3 = new Date(mseconds * 1000);  
   //Date--一般时间String
      SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String str = sdf.format(date3);  
      System.out.println("long:"+mseconds+"--Date:"+date3+"--String:"+str);     
      //////结果是： long:1540224000--Date:Tue Oct 23 00:00:00 CST 2018--String:2018-10-23 00:00:00
      
   //CST时间String--Date
      String str1="Wed Sep 16 11:26:23 CST 2009";
      SimpleDateFormat sdf1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
      try {
		Date date4 = (Date) sdf1.parse(str1);
		System.out.println("CST时间String:"+str1+"--Date:"+date4);   
		///////结果是：   CST时间String:Wed Sep 16 11:26:23 CST 2009--Date:Wed Sep 16 11:26:23 CST 2009
	  } catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
   //一般时间String--Date
      String str2="2015-08-31 21:08:06";
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      try {
		Date date5 = (Date) formatter.parse(str2);
		System.out.println("一般时间String:"+str2+"--Date:"+date5);   
		///////结果是：     一般时间String:2015-08-31 21:08:06--Date:Mon Aug 31 21:08:06 CST 2015
	  } catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	 }
      
      
      Date date = new Date();          
      SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");  
      String dateStr = df.format(date);  
      System.out.println(dateStr);  
      ///结果是：    2018年01月29日 17:07
      
	}

}
