import java.io.UnsupportedEncodingException;

public class CTestString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
 ///����String
		byte[] b = {'a','b','c','d','e','f','g','h','i','j'};
		char[] c = {'0','1','2','3','4','5','6','7','8','9'};
		String strb=new String(b);
		String strSub=new String(b,3,2); //��b�е�3λ��ʼȡ����ֵ
		String strc=new String(c);
		String strSuc=new String(c,3,2); //��c�е�3λ��ʼȡ����ֵ
		System.out.println("strb:"+strb); //strb:abcdefghij
		
//charAt(idex):idexȡֵ��[0,length())��Χ��ȡһ���ַ�
		String s = new String("abcdefghijklmnopqrstuvwxyz");
        System.out.println("s.charAt(5): " + s.charAt(5) );
        
//int compareTo(String anotherString) ����ǰString������anotherString�Ƚϡ���ȹ�ϵ���أ���
//�����ʱ���������ַ�����0���ַ���ʼ�Ƚϣ����ص�һ������ȵ��ַ����һ��������ϳ��ַ�����ǰ�沿��ǡ���ǽ϶̵��ַ������������ǵĳ��Ȳ    
        String s1 = new String("abcdefghijklmn");
        String s2 = new String("abcdefghij");
       String s3 = new String("abcdefghijalmn");
       System.out.println("s1.compareTo(s2): " + s1.compareTo(s2) ); //���س��Ȳ�  ---4
       System.out.println("s1.compareTo(s3): " + s1.compareTo(s3) ); //����'k'-'a'�Ĳ� ---10
        
 //String concat(String str): ������������������
       String s4=s1.concat(s2);
       s1=s1.concat(s2);
       
 //static String copyValueOf(char[] data, int offset, int count) ��������������char����ת����String��������һ�����캯�����ơ�
       char[] array1={'a','b','��'};
       String str5=String.copyValueOf(array1);
       String str6=String.copyValueOf(array1, 2, 1); //��array1�ϵ�2λ������ȡ1��ֵ
  
//boolean endsWith(String suffix) ����String�����Ƿ���suffix��β��
       String s5 = new String("abcdefghij");
       String s6 = new String("ghij");
       System.out.println("s1.endsWith(s2): " + s5.endsWith(s6) );
       System.out.println("s5: " + s5);
 
 ///boolean equals(Object anObject) ����anObject��Ϊ�ղ����뵱ǰString����һ��������true�����򣬷���false��
       if(s5.equals(s6)==false)
       {
    	   System.out.println("equal: true"); 
       }
       
  //byte[] getBytes() ������String����ת����byte���顣------�ɽ��utf-8��������     
  //     String str7 = "234���";
   //    byte[] b_gbk=str7.getBytes("GBK");
       
  // void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) ���÷������ַ����������ַ������С�
  //���У�srcBeginΪ��������ʼλ�á�srcEndΪ�����Ľ���λ�á��ַ�����ֵdstΪĿ���ַ����顢
  //dstBeginΪĿ���ַ�����Ŀ�����ʼλ�á�
       char[] s7 = {'I',' ','l','o','v','e',' ','h','e','r','!'};//s1=I love her!
       String s8 = new String("544you!");
       s8.getChars(0,3,s7,7); //s8=I love you! ,ȡs8��[0,3)��ֵ����s7�ĵ�7λ�ÿ�ʼ��������s7���еĴ�С�ͱ���
   
  //int indexOf(int ch) ��ֻ�ҵ�һ��ƥ���ַ�λ�á�     
  //int indexOf(int ch, int fromIndex) ����fromIndex��ʼ�ҵ�һ��ƥ���ַ�λ�á�     
  // int indexOf(String str) ��ֻ�ҵ�һ��ƥ���ַ���λ�á�
  //int indexOf(String str, int fromIndex) ����fromIndex��ʼ�ҵ�һ��ƥ���ַ���λ�á�     
       String st = new String("write once, run anywhere!");
       String ss = new String("run");
       System.out.println("s.indexOf('r'): " + st.indexOf('r') ); //---1
       System.out.println("s.indexOf('r',2): " + st.indexOf('r',2) );  //----12
       System.out.println("s.indexOf(ss): " + st.indexOf(ss) );  //----12
       
//int lastIndexOf(int ch)   int lastIndexOf(int ch, int fromIndex)
//int lastIndexOf(String str)
//int lastIndexOf(String str, int fromIndex) �����ĸ�������13��14��15��16���ƣ���ͬ���ǣ������һ��ƥ������ݡ�
       String s9 = new String("acbdebfg");     
       System.out.println(s9.lastIndexOf((int)'b',7));//---����fromIndex�Ĳ���Ϊ 7���Ǵ��ַ���acbdebfg�����һ���ַ�g��ʼ��ǰ����λ�������Ǵ��ַ�c��ʼƥ�䣬Ѱ�����һ��ƥ��b��λ�á����Խ��Ϊ 5
       
 //String replace(char oldChar, char newChar) �����ַ��Ŵ������е�oldChar�滻��newChar��
       String str10=s9.replace('b', '1');
   
  ///boolean startsWith(String prefix) ����String�����Ƿ���prefix��ʼ��
  //boolean startsWith(String prefix, int toffset) ����String�����toffsetλ�������Ƿ���prefix��ʼ��
       String st1 = new String("  write once, run anywhere!");
       String sst1 = new String("write");
       String ssst1 = new String("once");
       System.out.println("s.startsWith(ss): " + st1.startsWith(sst1) );  //---true
       System.out.println("s.startsWith(sss,6): " + st1.startsWith(ssst1,6) );  //---true
       
  //String substring(int beginIndex) ��ȡ��beginIndexλ�ÿ�ʼ�����������ַ�����[beginIndex,..)
  //String substring(int beginIndex, int endIndex) ��ȡ��beginIndexλ�ÿ�ʼ��endIndexλ�õ����ַ�����[beginIndex,endIndex)
       String sub1=st1.substring(1);
       String sub2=st1.substring(2, 5);
 
 //char[ ] toCharArray() ������String����ת����char���顣
      char [] ch1=st1.toCharArray(); 
      
//public String trim()  ���ظ��ַ���ȥ����ͷ�ͽ�β�ո����ַ���
     String st2=st1.trim(); 
      
      
      
//String toLowerCase() �����ַ���ת����Сд��
//String toUpperCase() �����ַ���ת���ɴ�д��      
      String str11 = new String("java.lang.Class String");
      System.out.println("s.toUpperCase(): " + str11.toUpperCase() );
      System.out.println("s.toLowerCase(): " + str11.toLowerCase() );
  
 //����ͬ����ת��ΪJava�ַ���
  // static String valueOf(boolean b)    static String valueOf(char c)
  //static String valueOf(char[] data)   static String valueOf(char[] data, int offset, int count)
 //static String valueOf(double d)       static String valueOf(float f)
  //static String valueOf(int i)     static String valueOf(long l)    static String valueOf(Object obj)    
 
 //split()��public String[] split(String regex)    ��һ���ַ�������ָ���ķָ����ָ������طָ�����ַ�������
 ///String����ת��Ϊ�ַ�������String[]    
	     String date = "2008/09/10";
         String[ ] dateAfterSplit= new String[3];
         dateAfterSplit=date.split("/");         //�ԡ�/����Ϊ�ָ������ָ�date�ַ��������ѽ������3���ַ����С�

         for(int i=0;i<dateAfterSplit.length;i++)
                    System.out.print(dateAfterSplit[i]+" ");

         
  ///String��byte[]�໥ת��
         String str1="fs123daed33d";   
         byte by[]=str1.getBytes();  //String ת��Ϊbyte[] ��[102, 115, 49, 50, 51, 100, 97, 101, 100, 51, 51, 100]��ӦASCI��
         String str2=new String(by); //byte[] ת��ΪString fs123daed33d
	}

}
