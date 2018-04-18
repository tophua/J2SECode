package DatePackage;

import java.util.Calendar;

public class CalendarClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       Calendar ca1=Calendar.getInstance();
       System.out.println("年:"+ca1.get(ca1.YEAR)+"  月:"+ca1.get(ca1.MONTH)+1
                          +"  日:"+ca1.get(ca1.DAY_OF_MONTH)+"  时:"+ca1.get(ca1.HOUR_OF_DAY)
                          +"  分钟:"+ca1.get(ca1.MINUTE)+"  秒:"+ca1.get(ca1.SECOND)
                          +"  星期:"+ca1.get(ca1.DAY_OF_WEEK)+"  当前月最大日期数:"+ca1.getActualMaximum(Calendar.DATE)
                          +"  1号是星期:"+ca1.get(Calendar.DAY_OF_WEEK));  
       //////结果是：    年:2018  月:01  日:29  时:16  分钟:40  秒:39  星期:2  当前月最大日期数:31  1号是星期:2
       Calendar ca2=Calendar.getInstance();
       ca2.set(2019, 7-2, 25);//修改当前时间的年月日内容，其余不变       
       System.out.println("年:"+ca2.get(ca2.YEAR)+"  月:"+ca2.get(ca2.MONTH)
                         +"   日:"+ca2.get(ca2.DAY_OF_MONTH)+"  时:"+ca2.get(ca2.HOUR_OF_DAY)
                         +"   分钟:"+ca2.get(ca2.MINUTE)+"  秒:"+ca2.get(ca2.SECOND)
                         +"  星期:"+ca2.get(ca2.DAY_OF_WEEK));  
       //////结果是：   年:2019  月:5   日:25  时:14   分钟:28  秒:16  星期:3
       Calendar ca3=Calendar.getInstance();
       ca3.set(Calendar.YEAR, 1990);    
       ca3.set(Calendar.MONTH, 8);   
       ca3.set(Calendar.DATE, 16);   //相同ca3.set(Calendar.DAY_OF_MONTH, 17);   
       ca3.set(Calendar.HOUR, 11);   //12小时制的小时数
       ca3.set(Calendar.HOUR_OF_DAY, 19);  //24小时制的小时数
       ca3.set(Calendar.MINUTE, 52); 
       ca3.set(Calendar.SECOND, 53);   
       ca3.set(Calendar.DAY_OF_WEEK, 4);   
       System.out.println("年:"+ca3.get(ca3.YEAR)+"  月:"+ca3.get(ca3.MONTH)
                          +"   日:"+ca3.get(ca3.DATE)+"  时:"+ca3.get(ca3.HOUR_OF_DAY)
                          +"   分钟:"+ca3.get(ca3.MINUTE)+"  秒:"+ca3.get(ca3.SECOND)
                          +"  星期:"+ca3.get(ca3.DAY_OF_WEEK)); 
       //////结果是：   年:1990  月:8   日:26  时:19   分钟:52  秒:53  星期:4 
  ////add、after方法
       Calendar ca4=Calendar.getInstance();
   //  ca4.add(Calendar.DATE,100); //当前时间往前进100天
       ca4.add(Calendar.YEAR,-2); //当前时间往后退2年
       System.out.println("年:"+ca4.get(ca4.YEAR)+"  月:"+ca4.get(ca4.MONTH)+1
                          +"   日:"+ca4.get(ca4.DATE)+"  时:"+ca4.get(ca4.HOUR_OF_DAY)
                          +"   分钟:"+ca4.get(ca4.MINUTE)+"  秒:"+ca4.get(ca4.SECOND)
                          +"  星期:"+ca4.get(ca4.DAY_OF_WEEK)); 
       ///////结果是：      年:2016  月:01   日:29  时:16   分钟:34  秒:33  星期:6
       Calendar ca5=Calendar.getInstance();
       Calendar ca6=Calendar.getInstance();
       ca6.add(Calendar.DATE, 2);
       boolean b=ca6.after(ca5);
       System.out.println("b:"+b); 
       //////结果是：     b:true
  ////Calendar对象和相对时间     
     //将Calendar对象转换为相对时间
       Calendar c7 = Calendar.getInstance();
       long t1 = c7.getTimeInMillis();
       System.out.println("t1: "+t1); 
         ////结果是：    t1: 1517214873213
     //将相对时间转换成Calendar对象
      Calendar c8 = Calendar.getInstance();
      c8.setTimeInMillis(t1+1);
      System.out.println("c8: "+c8); 
      //计算相差天数
      Calendar c9 = Calendar.getInstance();
      c9.set(2016, 8-2, 20);
      Calendar c10 = Calendar.getInstance();
      c10.set(2016, 8-1, 22);
      long t2=c9.getTimeInMillis();
      long t3=c10.getTimeInMillis();
      long day1=(t3-t2)/(24*60*60*1000);
      System.out.println("t2: "+t2+" t3:"+t3+" day1:"+day1); 
        ////结果是：t2: 1469003673214 t3:1471854873214 day1:33     
     
	}

}
