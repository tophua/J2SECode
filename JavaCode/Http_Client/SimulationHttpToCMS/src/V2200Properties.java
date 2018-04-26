import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import net.sf.json.JSONObject;

public class V2200Properties {

	//������
	public static int errorValue=0; 
			
			
	//���login��SessionId
	public static String sessionId="";
	//Cookie
	public static String cookie="";
	//���Org����
	public static List<JSONObject> reOrgJsons = new ArrayList<JSONObject>();
	public static int iOrgNum=0;
	
	protected static final String AUTHSTR = "Authorization";
	protected static final String SET_COOKIE = "Set-Cookie";
	protected static final String JSESSION_ID = "JSESSIONID=";
	protected static final String SEMICOLON = ";";
	protected static final int JSESSION_ID_LEN = JSESSION_ID.length();
		
	public static String loginUrl;
	// ����V2200ƽ̨��Ȩ��У����
	public static String authorization;
	public static String getOrgByPageUrl;
	public static String getAsyncSearch;
	public static String getAsyncSearchResult;
	
	static{
		errorValue=errorInfo.NORMAL;
		loginUrl="http://10.82.25.251/CMS/main/login.do?";
//		authorization= "Basic " + new String(
//				Base64.getEncoder().encode((userName + ":" + getMd5Value(password).toUpperCase()).getBytes()));
		authorization= "Basic " + new String(
		Base64.getEncoder().encode(("admin" + ":" + getMd5Value("infinova9981").toUpperCase()).getBytes()));

		getOrgByPageUrl="http://10.82.27.171/CMS/action/topology/org/getAllByPage.do?";
		getAsyncSearch="http://10.82.25.251/CMS/action/record/record/asyncSearch.do";
		getAsyncSearchResult="http://10.82.25.251/CMS/action/record/record/getAsyncSearchResult.do";
	}
	
	public static String getMd5Value(String sSecret) {
		try {
			MessageDigest bmd5 = MessageDigest.getInstance("MD5");
			bmd5.update(sSecret.getBytes());
			int i;
			StringBuffer buf = new StringBuffer();
			byte[] b = bmd5.digest();// ����
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getAsyncSearchCookie(){
	   return cookie="JSESSIONID="+sessionId+"; userInfo.userName=admin; userInfo.loginName=admin;"
				+ "userInfo.originalLoginPassword=infinova9981; userInfo.loginFlag=1; userInfo.password=";
	}
	
}
