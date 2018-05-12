package com.Task;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

public class ScheduledExecutorSample03 extends TimerTask{

	private String jobName = "";
	public ScheduledExecutorSample03(String jobName){
		super();
		this.jobName = jobName;
	}	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		 System.out.println("Date = "+new Date()+", execute " + jobName);
	}

	 /**
     * ����ӵ�ǰʱ��currentDate��ʼ����������dayOfWeek, hourOfDay, 
     * minuteOfHour, secondOfMinite�����ʱ��
     * @return
     */
    public Calendar getEarliestDate(Calendar currentDate, int dayOfWeek,
            int hourOfDay, int minuteOfHour, int secondOfMinite) {
        //���㵱ǰʱ���WEEK_OF_YEAR,DAY_OF_WEEK, HOUR_OF_DAY, MINUTE,SECOND�ȸ����ֶ�ֵ
        int currentWeekOfYear = currentDate.get(Calendar.WEEK_OF_YEAR);
        int currentDayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK);
        int currentHour = currentDate.get(Calendar.HOUR_OF_DAY);
        int currentMinute = currentDate.get(Calendar.MINUTE);
        int currentSecond = currentDate.get(Calendar.SECOND);
 
        //������������е�dayOfWeekС�ڵ�ǰ���ڵ�dayOfWeek,��WEEK_OF_YEAR��Ҫ�Ƴ�һ��
        boolean weekLater = false;
        if (dayOfWeek < currentDayOfWeek) {
            weekLater = true;
        } else if (dayOfWeek == currentDayOfWeek) {
            //�����������뵱ǰ���ڵ�dayOfWeek���ʱ��������������е�
            //hourOfDayС�ڵ�ǰ���ڵ�
            //currentHour����WEEK_OF_YEAR��Ҫ�Ƴ�һ��   
            if (hourOfDay < currentHour) {
                weekLater = true;
            } else if (hourOfDay == currentHour) {
                 //�����������뵱ǰ���ڵ�dayOfWeek, hourOfDay���ʱ��
                 //������������е�minuteOfHourС�ڵ�ǰ���ڵ�
                //currentMinute����WEEK_OF_YEAR��Ҫ�Ƴ�һ��
                if (minuteOfHour < currentMinute) {
                    weekLater = true;
                } else if (minuteOfHour == currentSecond) {
                     //�����������뵱ǰ���ڵ�dayOfWeek, hourOfDay�� 
                     //minuteOfHour���ʱ��������������е�
                    //secondOfMiniteС�ڵ�ǰ���ڵ�currentSecond��
                    //��WEEK_OF_YEAR��Ҫ�Ƴ�һ��
                    if (secondOfMinite < currentSecond) {
                        weekLater = true;
                    }
                }
            }
        }
        if (weekLater) {
            //���õ�ǰ�����е�WEEK_OF_YEARΪ��ǰ���Ƴ�һ��
            currentDate.set(Calendar.WEEK_OF_YEAR, currentWeekOfYear + 1);
        }
        // ���õ�ǰ�����е�DAY_OF_WEEK,HOUR_OF_DAY,MINUTE,SECONDΪ���������е�ֵ��
        currentDate.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        currentDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
        currentDate.set(Calendar.MINUTE, minuteOfHour);
        currentDate.set(Calendar.SECOND, secondOfMinite);
        return currentDate;
    }
}
