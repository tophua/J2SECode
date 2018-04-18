package DatePackage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateJudgeUtil {
	/**
	 * �ж��Ƿ���ͬһ��
	 * @param date1
	 * @param date2
	 * @return
	 */
	private static boolean isSameDate(Date date1, Date date2) {
	       Calendar cal1 = Calendar.getInstance();
	       cal1.setTime(date1);

	       Calendar cal2 = Calendar.getInstance();
	       cal2.setTime(date2);

	       boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
	               .get(Calendar.YEAR);
	       boolean isSameMonth = isSameYear
	               && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
	       boolean isSameDate = isSameMonth
	               && cal1.get(Calendar.DAY_OF_MONTH) == cal2
	                       .get(Calendar.DAY_OF_MONTH);

	       return isSameDate;
	   }

	/**
	 * �ж�date1��date2�Ƿ���ͬһ��
	 * 
	 * @param date1
	 * @param date2
	 * @return true��ͬһ��false����ͬһ��
	 */
	public static boolean isSameWeek(String date1, String date2) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(date1);
			d2 = format.parse(date2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSameWeek(d1, d2);
	}

	/**
	 * �ж�date1��date2�Ƿ���ͬһ��
	 * 
	 * @param date1
	 * @param date2
	 * @return true��ͬһ��false����ͬһ��
	 */
	public static boolean isSameWeek(Date date1, Date date2) {
		return isSameDate(getFirstDateOfWeek(date1), getFirstDateOfWeek(date2));
	}
	
	/**
	 * �ж�date1��date2�Ƿ���ͬһ��
	 * 
	 * @param date1
	 * @param date2
	 * @return true��ͬһ��false����ͬһ��
	 */
	public static boolean isSameMonth(Date date1, Date date2) {
		return isSameDate(getFirstDateOfMonth(date1), getFirstDateOfMonth(date2));
	}

	
	
	/**
	 * �ж�date1��date2�Ƿ���ͬһ����
	 * 
	 * @param date1
	 * @param date2
	 * @return true��ͬһ����false����ͬһ����
	 */
	public static boolean isSameQuarter(Date date1, Date date2) {
		return isSameDate(getFirstDateOfSeason(date1), getFirstDateOfSeason(date2));
	}
	/**
	 * �ж�date1��date2�Ƿ���ͬһ��
	 * 
	 * @param date1
	 * @param date2
	 * @return true��ͬһ��false����ͬһ��
	 */
	public static boolean isSameYear(Date date1, Date date2) {
		return isSameDate(getFirstDateOfYear(date1), getFirstDateOfYear(date2));
	}
	
	/**
	 * ��ȡ���ܵĵ�һ�죨��һ��
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// ��õ�ǰ������һ�����ڵĵڼ���
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// ����һ�����ڵĵ�һ�죬���й���ϰ��һ�����ڵĵ�һ��������һ
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// ��õ�ǰ������һ�����ڵĵڼ���
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// ���������Ĺ��򣬸���ǰ���ڼ�ȥ���ڼ���һ�����ڵ�һ��Ĳ�ֵ
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		return cal.getTime();
	}
	/**
	 * ��ȡ���µĵ�һ��
	 * @param date
	 * @return ���µĵ�һ��
	 */
	public static Date getFirstDateOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
	    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}
	
    /** 
     * ȡ�ü��ȵĵ�һ�� 
     *  
     * @param date 
     * @return ���ȵĵ�һ��
     */  
    public static Date getFirstDateOfSeason(Date date) {  
        return getFirstDateOfMonth(getSeasonDate(date)[0]);  
    }
    
    /** 
     * ȡ����ĵ�һ�� 
     *  
     * @param date 
     * @return ����ĵ�һ��
     */  
    public static Date getFirstDateOfYear(Date date) {  
    	 Calendar currCal=Calendar.getInstance(); 
    	 currCal.setTime(date);
         int currentYear = currCal.get(Calendar.YEAR); 
         currCal.clear();  
         currCal.set(currCal.YEAR, currentYear);  
         return currCal.getTime();  
    } 
    
    
    
    
    /** 
     * ȡ�ü����� 
     *  
     * @param date 
     * @return Date[] �����µ�����
     */  
    private static Date[] getSeasonDate(Date date) {  
        Date[] season = new Date[3];  
  
        Calendar c = Calendar.getInstance();  
        c.setTime(date);  
  
        int nSeason = getSeason(date);  
        if (nSeason == 1) {// ��һ����  
            c.set(Calendar.MONTH, Calendar.JANUARY);  
            season[0] = c.getTime();  
            c.set(Calendar.MONTH, Calendar.FEBRUARY);  
            season[1] = c.getTime();  
            c.set(Calendar.MONTH, Calendar.MARCH);  
            season[2] = c.getTime();  
        } else if (nSeason == 2) {// �ڶ�����  
            c.set(Calendar.MONTH, Calendar.APRIL);  
            season[0] = c.getTime();  
            c.set(Calendar.MONTH, Calendar.MAY);  
            season[1] = c.getTime();  
            c.set(Calendar.MONTH, Calendar.JUNE);  
            season[2] = c.getTime();  
        } else if (nSeason == 3) {// ��������  
            c.set(Calendar.MONTH, Calendar.JULY);  
            season[0] = c.getTime();  
            c.set(Calendar.MONTH, Calendar.AUGUST);  
            season[1] = c.getTime();  
            c.set(Calendar.MONTH, Calendar.SEPTEMBER);  
            season[2] = c.getTime();  
        } else if (nSeason == 4) {// ���ļ���  
            c.set(Calendar.MONTH, Calendar.OCTOBER);  
            season[0] = c.getTime();  
            c.set(Calendar.MONTH, Calendar.NOVEMBER);  
            season[1] = c.getTime();  
            c.set(Calendar.MONTH, Calendar.DECEMBER);  
            season[2] = c.getTime();  
        }  
        return season;  
    }  
  
    /** 
     *  
     * 1 ��һ���� 2 �ڶ����� 3 �������� 4 ���ļ��� 
     *  
     * @param date 
     * @return 
     */  
    private static int getSeason(Date date) {  
  
        int season = 0;  
  
        Calendar c = Calendar.getInstance();  
        c.setTime(date);  
        int month = c.get(Calendar.MONTH);  
        switch (month) {  
        case Calendar.JANUARY:  
        case Calendar.FEBRUARY:  
        case Calendar.MARCH:  
            season = 1;  
            break;  
        case Calendar.APRIL:  
        case Calendar.MAY:  
        case Calendar.JUNE:  
            season = 2;  
            break;  
        case Calendar.JULY:  
        case Calendar.AUGUST:  
        case Calendar.SEPTEMBER:  
            season = 3;  
            break;  
        case Calendar.OCTOBER:  
        case Calendar.NOVEMBER:  
        case Calendar.DECEMBER:  
            season = 4;  
            break;  
        default:  
            break;  
        }  
        return season;  
    }  
}