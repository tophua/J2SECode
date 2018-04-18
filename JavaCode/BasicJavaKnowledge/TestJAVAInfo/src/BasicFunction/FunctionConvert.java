package BasicFunction;

public class FunctionConvert {
	
	//byte[]转换为16进制字符串
	public static String bytes2Hex(byte[] src) {
		if (src == null || src.length <= 0) {
			return null;
		}
		char[] res = new char[src.length * 2]; // 每个byte对应两个字符
		final char hexDigits[] = { '0', '1', '2', '3', '4', '5',
				'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		for (int i = 0, j = 0; i < src.length; i++) {
			res[j++] = hexDigits[src[i] >> 4 & 0x0f]; // 先存byte的高4位
			res[j++] = hexDigits[src[i] & 0x0f]; // 再存byte的低4位
		}
		return new String(res);
	  }
	
	//网络字节byte转换十六进制，且还需要进行大端--小端的转换
	public static String Netbytes2Hex(byte[] src) {
	
		//网络字节byte转换十六进制，且还需要进行大端--小端的转换
		String strResult=bytes2Hex(src);
		String strNew="";
		int ResultSize=strResult.length();
		if(strResult.length()>0){
			for(int i=0;i<ResultSize;i+=2){
				strNew+=strResult.substring(strResult.length()-2);
				strResult=strResult.substring(0,strResult.length()-2);
			}
		}
		return strNew;	
  }
	
	
}
