package socketclient;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ByteConvert {
	/**
	 * intתΪ���ֽ���ǰ�����ֽ��ں��byte���� VC
	 * @param n
	 * @return byte[]
	 */
	public static byte[] toLH(int n){
	    byte[] b = new byte[4];
	    b[0] = (byte) (n & 0xff);
	    b[1] = (byte) (n >> 8 & 0xff);
	    b[2] = (byte) (n >> 16 & 0xff);
	    b[3] = (byte) (n >> 24 & 0xff);
	    return b;
	}

	/**
	 * ��floatתΪ���ֽ���ǰ�����ֽ��ں��byte����
	 * @param f
	 * @return byte[]
	 */
	public static byte[] toLH(float f) {
  	return toLH(Float.floatToRawIntBits(f));
	}

	public static byte[] intToByteArray(int i) throws Exception { 
	    ByteArrayOutputStream buf = new ByteArrayOutputStream(); 
	    DataOutputStream dos= new DataOutputStream(buf); 
	    dos.writeInt(i); 
	    byte[] b = buf.toByteArray(); 
	    dos.close(); 
	    buf.close(); 
	    return b; 
	} 

	//�ú���������
	public static int ByteArrayToInt(byte b[]) throws Exception { 
	    int temp = 0, a=0; 
	    ByteArrayInputStream buf = new ByteArrayInputStream(b);
	    DataInputStream dis= new DataInputStream (buf); 
	    return dis.readInt();
	}
	
	//byte[]ת��Ϊ16�����ַ���
	public static String bytes2Hex(byte[] src) {
		if (src == null || src.length <= 0) {
			return null;
		}
		char[] res = new char[src.length * 2]; // ÿ��byte��Ӧ�����ַ�
		final char hexDigits[] = { '0', '1', '2', '3', '4', '5',
				'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		for (int i = 0, j = 0; i < src.length; i++) {
			res[j++] = hexDigits[src[i] >> 4 & 0x0f]; // �ȴ�byte�ĸ�4λ
			res[j++] = hexDigits[src[i] & 0x0f]; // �ٴ�byte�ĵ�4λ
		}
		return new String(res);
	  }
	
	public static String Netbytes2Hex(byte[] src) {
	
		//�����ֽ�byteת��ʮ�����ƣ��һ���Ҫ���д��--С�˵�ת��
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
