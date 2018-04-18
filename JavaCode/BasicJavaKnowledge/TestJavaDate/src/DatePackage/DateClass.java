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
      //////����ǣ�  Mon Jan 29 13:45:53 CST 2018
      
      System.out.println("Year��:"+date1.getYear()+"  Month��:"+date1.getMonth()
      +"  Date��:"+date1.getDate()+"  hourСʱ:"+date1.getHours()+"  minute����:"+
    		  date1.getMinutes()+"  Second��:"+date1.getSeconds()+"  day����:"+date1.getDay());
     //////����ǣ�   Year��:118  Month��:0  Date��:29  hourСʱ:13  minute����:45  Second��:53  day����:1
      
//long���ʱ�䡢Date����String����ת��
   //Date--long
	  Date date2=new Date(2018-1900,12-3,23);
      long t=date2.getTime();
      System.out.println("Date:"+date2+"--long:"+t);     
      //////����ǣ�  Date:Tue Oct 23 00:00:00 CST 2018--long:1540224000000
      
   //long--Date
      long mseconds=date2.getTime()/1000;     
      Date date3 = new Date(mseconds * 1000);  
   //Date--һ��ʱ��String
      SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String str = sdf.format(date3);  
      System.out.println("long:"+mseconds+"--Date:"+date3+"--String:"+str);     
      //////����ǣ� long:1540224000--Date:Tue Oct 23 00:00:00 CST 2018--String:2018-10-23 00:00:00
      
   //CSTʱ��String--Date
      String str1="Wed Sep 16 11:26:23 CST 2009";
      SimpleDateFormat sdf1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
      try {
		Date date4 = (Date) sdf1.parse(str1);
		System.out.println("CSTʱ��String:"+str1+"--Date:"+date4);   
		///////����ǣ�   CSTʱ��String:Wed Sep 16 11:26:23 CST 2009--Date:Wed Sep 16 11:26:23 CST 2009
	  } catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
   //һ��ʱ��String--Date
      String str2="2015-08-31 21:08:06";
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      try {
		Date date5 = (Date) formatter.parse(str2);
		System.out.println("һ��ʱ��String:"+str2+"--Date:"+date5);   
		///////����ǣ�     һ��ʱ��String:2015-08-31 21:08:06--Date:Mon Aug 31 21:08:06 CST 2015
	  } catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	 }
      
      
      Date date = new Date();          
      SimpleDateFormat df = new SimpleDateFormat("yyyy��MM��dd�� HH:mm");  
      String dateStr = df.format(date);  
      System.out.println(dateStr);  
      ///����ǣ�    2018��01��29�� 17:07
      
	}

}
