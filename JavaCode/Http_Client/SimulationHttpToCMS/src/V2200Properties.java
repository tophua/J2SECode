import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class V2200Properties {

	protected static final String AUTHSTR = "Authorization";
	protected static final String SET_COOKIE = "Set-Cookie";
	protected static final String JSESSION_ID = "JSESSIONID=";
	protected static final String SEMICOLON = ";";
	protected static final int JSESSION_ID_LEN = JSESSION_ID.length();
	
	
	
	public static String loginUrl;
	// 访问V2200平台的权限校验码
	public static String authorization;
	
	static{
		loginUrl="http://10.82.27.171/CMS/main/login.do?";
//		authorization= "Basic " + new String(
//				Base64.getEncoder().encode((userName + ":" + getMd5Value(password).toUpperCase()).getBytes()));
		authorization= "Basic " + new String(
		Base64.getEncoder().encode(("admin" + ":" + getMd5Value("admin").toUpperCase()).getBytes()));

	
	}
	
	public static String getMd5Value(String sSecret) {
		try {
			MessageDigest bmd5 = MessageDigest.getInstance("MD5");
			bmd5.update(sSecret.getBytes());
			int i;
			StringBuffer buf = new StringBuffer();
			byte[] b = bmd5.digest();// 加密
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

}
