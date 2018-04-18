package infinova.Otical;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonFunction {

	public static String GetCurrentTime(){
		  
		   String strTimeNow=null;
		   Date dateNow = new Date();
		   SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   strTimeNow=format1.format(dateNow);
		   return strTimeNow;
	}
}
