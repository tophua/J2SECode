package DatePackage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateJudgeUtil {
	/**
	 * 判断是否是同一天
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
	 * 判断date1与date2是否在同一周
	 * 
	 * @param date1
	 * @param date2
	 * @return true是同一周false不是同一周
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
	 * 判断date1与date2是否在同一周
	 * 
	 * @param date1
	 * @param date2
	 * @return true是同一周false不是同一周
	 */
	public static boolean isSameWeek(Date date1, Date date2) {
		return isSameDate(getFirstDateOfWeek(date1), getFirstDateOfWeek(date2));
	}
	
	/**
	 * 判断date1与date2是否在同一月
	 * 
	 * @param date1
	 * @param date2
	 * @return true是同一月false不是同一月
	 */
	public static boolean isSameMonth(Date date1, Date date2) {
		return isSameDate(getFirstDateOfMonth(date1), getFirstDateOfMonth(date2));
	}

	
	
	/**
	 * 判断date1与date2是否在同一季度
	 * 
	 * @param date1
	 * @param date2
	 * @return true是同一季度false不是同一季度
	 */
	public static boolean isSameQuarter(Date date1, Date date2) {
		return isSameDate(getFirstDateOfSeason(date1), getFirstDateOfSeason(date2));
	}
	/**
	 * 判断date1与date2是否在同一年
	 * 
	 * @param date1
	 * @param date2
	 * @return true是同一年false不是同一年
	 */
	public static boolean isSameYear(Date date1, Date date2) {
		return isSameDate(getFirstDateOfYear(date1), getFirstDateOfYear(date2));
	}
	
	/**
	 * 获取当周的第一天（周一）
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		return cal.getTime();
	}
	/**
	 * 获取该月的第一天
	 * @param date
	 * @return 该月的第一天
	 */
	public static Date getFirstDateOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
	    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}
	
    /** 
     * 取得季度的第一天 
     *  
     * @param date 
     * @return 季度的第一天
     */  
    public static Date getFirstDateOfSeason(Date date) {  
        return getFirstDateOfMonth(getSeasonDate(date)[0]);  
    }
    
    /** 
     * 取得年的第一天 
     *  
     * @param date 
     * @return 当年的第一天
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
     * 取得季度月 
     *  
     * @param date 
     * @return Date[] 季度月的数组
     */  
    private static Date[] getSeasonDate(Date date) {  
        Date[] season = new Date[3];  
  
        Calendar c = Calendar.getInstance();  
        c.setTime(date);  
  
        int nSeason = getSeason(date);  
        if (nSeason == 1) {// 第一季度  
            c.set(Calendar.MONTH, Calendar.JANUARY);  
            season[0] = c.getTime();  
            c.set(Calendar.MONTH, Calendar.FEBRUARY);  
            season[1] = c.getTime();  
            c.set(Calendar.MONTH, Calendar.MARCH);  
            season[2] = c.getTime();  
        } else if (nSeason == 2) {// 第二季度  
            c.set(Calendar.MONTH, Calendar.APRIL);  
            season[0] = c.getTime();  
            c.set(Calendar.MONTH, Calendar.MAY);  
            season[1] = c.getTime();  
            c.set(Calendar.MONTH, Calendar.JUNE);  
            season[2] = c.getTime();  
        } else if (nSeason == 3) {// 第三季度  
            c.set(Calendar.MONTH, Calendar.JULY);  
            season[0] = c.getTime();  
            c.set(Calendar.MONTH, Calendar.AUGUST);  
            season[1] = c.getTime();  
            c.set(Calendar.MONTH, Calendar.SEPTEMBER);  
            season[2] = c.getTime();  
        } else if (nSeason == 4) {// 第四季度  
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
     * 1 第一季度 2 第二季度 3 第三季度 4 第四季度 
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
