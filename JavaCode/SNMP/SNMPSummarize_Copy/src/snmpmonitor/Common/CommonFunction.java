package snmpmonitor.Common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonFunction {
	/** 
	* ip��ַת��long������ 
	* ��IP��ַת���������ķ������£� 
	* 1��ͨ��String��split������.�ָ��õ�4�����ȵ����� 
	* 2��ͨ������λ������<<����ÿһ�ε����ּ�Ȩ����һ�ε�ȨΪ2��24�η����ڶ��ε�ȨΪ2��16�η��������ε�ȨΪ2��8�η������һ�ε�ȨΪ1 
	* @param strIp 
	* @return 
	*/ 

	public static long StringIPToLong(String IPValue) {
		String[] strip=IPValue.split("\\.");
		return (Long.parseLong(strip[0]) << 24) + 
				(Long.parseLong(strip[1]) << 16) + (Long.parseLong(strip[2]) << 8)
				+ Long.parseLong(strip[3]);  
	}
	

   /** 
    * ��ʮ����������ʽת����127.0.0.1��ʽ��ip��ַ 
    * ��������ʽ��IP��ַת�����ַ����ķ������£� 
    * 1��������ֵ��������λ������>>>��������24λ������ʱ��λ��0���õ������ּ�Ϊ��һ��IP�� 
    * 2��ͨ�����������&��������ֵ�ĸ�8λ��Ϊ0��������16λ���õ������ּ�Ϊ�ڶ���IP�� 
    * 3��ͨ���������������ֵ�ĸ�16λ��Ϊ0��������8λ���õ������ּ�Ϊ������IP�� 
    * 4��ͨ���������������ֵ�ĸ�24λ��Ϊ0���õ������ּ�Ϊ���Ķ�IP�� 
    * @param longIp 
    * @return 
    */  

	 public static String LongToStringIP(long longIp) {  
		StringBuffer sb = new StringBuffer("");  
		// ֱ������24λ  
		sb.append(String.valueOf((longIp >>> 24)));  
		sb.append(".");  
		// ����8λ��0��Ȼ������16λ  
		sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));  
		sb.append(".");  
		// ����16λ��0��Ȼ������8λ  
		sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));  
		sb.append(".");  
		// ����24λ��0  
		sb.append(String.valueOf((longIp & 0x000000FF)));  
		return sb.toString();  
	}  
	 
	public static String GetCurrentTime(){
			  
			   String strTimeNow=null;
			   Date dateNow = new Date();
			   SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			   strTimeNow=format1.format(dateNow);
			   return strTimeNow;
	}
}
