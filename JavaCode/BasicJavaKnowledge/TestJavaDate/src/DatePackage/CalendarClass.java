package DatePackage;

import java.util.Calendar;

public class CalendarClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       Calendar ca1=Calendar.getInstance();
       System.out.println("��:"+ca1.get(ca1.YEAR)+"  ��:"+ca1.get(ca1.MONTH)+1
                          +"  ��:"+ca1.get(ca1.DAY_OF_MONTH)+"  ʱ:"+ca1.get(ca1.HOUR_OF_DAY)
                          +"  ����:"+ca1.get(ca1.MINUTE)+"  ��:"+ca1.get(ca1.SECOND)
                          +"  ����:"+ca1.get(ca1.DAY_OF_WEEK)+"  ��ǰ�����������:"+ca1.getActualMaximum(Calendar.DATE)
                          +"  1��������:"+ca1.get(Calendar.DAY_OF_WEEK));  
       //////����ǣ�    ��:2018  ��:01  ��:29  ʱ:16  ����:40  ��:39  ����:2  ��ǰ�����������:31  1��������:2
       Calendar ca2=Calendar.getInstance();
       ca2.set(2019, 7-2, 25);//�޸ĵ�ǰʱ������������ݣ����಻��       
       System.out.println("��:"+ca2.get(ca2.YEAR)+"  ��:"+ca2.get(ca2.MONTH)
                         +"   ��:"+ca2.get(ca2.DAY_OF_MONTH)+"  ʱ:"+ca2.get(ca2.HOUR_OF_DAY)
                         +"   ����:"+ca2.get(ca2.MINUTE)+"  ��:"+ca2.get(ca2.SECOND)
                         +"  ����:"+ca2.get(ca2.DAY_OF_WEEK));  
       //////����ǣ�   ��:2019  ��:5   ��:25  ʱ:14   ����:28  ��:16  ����:3
       Calendar ca3=Calendar.getInstance();
       ca3.set(Calendar.YEAR, 1990);    
       ca3.set(Calendar.MONTH, 8);   
       ca3.set(Calendar.DATE, 16);   //��ͬca3.set(Calendar.DAY_OF_MONTH, 17);   
       ca3.set(Calendar.HOUR, 11);   //12Сʱ�Ƶ�Сʱ��
       ca3.set(Calendar.HOUR_OF_DAY, 19);  //24Сʱ�Ƶ�Сʱ��
       ca3.set(Calendar.MINUTE, 52); 
       ca3.set(Calendar.SECOND, 53);   
       ca3.set(Calendar.DAY_OF_WEEK, 4);   
       System.out.println("��:"+ca3.get(ca3.YEAR)+"  ��:"+ca3.get(ca3.MONTH)
                          +"   ��:"+ca3.get(ca3.DATE)+"  ʱ:"+ca3.get(ca3.HOUR_OF_DAY)
                          +"   ����:"+ca3.get(ca3.MINUTE)+"  ��:"+ca3.get(ca3.SECOND)
                          +"  ����:"+ca3.get(ca3.DAY_OF_WEEK)); 
       //////����ǣ�   ��:1990  ��:8   ��:26  ʱ:19   ����:52  ��:53  ����:4 
  ////add��after����
       Calendar ca4=Calendar.getInstance();
   //  ca4.add(Calendar.DATE,100); //��ǰʱ����ǰ��100��
       ca4.add(Calendar.YEAR,-2); //��ǰʱ��������2��
       System.out.println("��:"+ca4.get(ca4.YEAR)+"  ��:"+ca4.get(ca4.MONTH)+1
                          +"   ��:"+ca4.get(ca4.DATE)+"  ʱ:"+ca4.get(ca4.HOUR_OF_DAY)
                          +"   ����:"+ca4.get(ca4.MINUTE)+"  ��:"+ca4.get(ca4.SECOND)
                          +"  ����:"+ca4.get(ca4.DAY_OF_WEEK)); 
       ///////����ǣ�      ��:2016  ��:01   ��:29  ʱ:16   ����:34  ��:33  ����:6
       Calendar ca5=Calendar.getInstance();
       Calendar ca6=Calendar.getInstance();
       ca6.add(Calendar.DATE, 2);
       boolean b=ca6.after(ca5);
       System.out.println("b:"+b); 
       //////����ǣ�     b:true
  ////Calendar��������ʱ��     
     //��Calendar����ת��Ϊ���ʱ��
       Calendar c7 = Calendar.getInstance();
       long t1 = c7.getTimeInMillis();
       System.out.println("t1: "+t1); 
         ////����ǣ�    t1: 1517214873213
     //�����ʱ��ת����Calendar����
      Calendar c8 = Calendar.getInstance();
      c8.setTimeInMillis(t1+1);
      System.out.println("c8: "+c8); 
      //�����������
      Calendar c9 = Calendar.getInstance();
      c9.set(2016, 8-2, 20);
      Calendar c10 = Calendar.getInstance();
      c10.set(2016, 8-1, 22);
      long t2=c9.getTimeInMillis();
      long t3=c10.getTimeInMillis();
      long day1=(t3-t2)/(24*60*60*1000);
      System.out.println("t2: "+t2+" t3:"+t3+" day1:"+day1); 
        ////����ǣ�t2: 1469003673214 t3:1471854873214 day1:33     
     
	}

}