package Test_String;

import java.util.*;

import org.apache.commons.lang.ArrayUtils;

public class CTestString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
  //String��int��double��ת��	
	int i1=10;
	String stri1=Integer.toString(i1);
	double d1=10.0;
	String strd1=Double.toString(d1);
	
	int i2=Integer.valueOf(stri1).intValue();
	int i3=Integer.parseInt("11");
	double d2=Double.valueOf(strd1).doubleValue(); 
	double d3 = Double.parseDouble("123.2"); 

//�ַ�����char[]���ַ�������String[]��String�໥ת��   
	//String[]--String
	String[] str = {"abc", "bcd", "def"};
	StringBuffer sB = new StringBuffer();
	for(int i = 0; i < str.length;i++)
	{
       sB.append(str[i]);
	}
	String strB = sB.toString();
	//String--String[]
	String[] arr = strB.split("");
	for(int i =0;i<arr.length;i++)
	{
       System.out.println(arr[i]);
	} 
	//char[]--String
	char[] data={'a', 'b', '��'};
	String strC = new String(data);
	//String--char[]
	String strD = "123abc";
	char[] arD = strD.toCharArray();//char����
	for(int i =0;i<arD.length;i++)
	{
      System.out.println(arD[i]);
	}
	//String--byte[]
	String strb="Hello23";
	byte[] by1=strb.getBytes();
	//byte[]--String
	String strb2=new String(by1);
	
 ///����String
		byte[] b = {'a','b','c','d','e','f','g','h','i','j'};
		char[] c = {'0','1','2','3','4','5','6','7','8','9'};
		String strb3=new String(b);
		String strSub=new String(b,3,2); //��b�е�3λ��ʼȡ����ֵ
		String strc=new String(c);
		String strSuc=new String(c,3,2); //��c�е�3λ��ʼȡ����ֵ
		System.out.println("strb:"+strb3); //strb:abcdefghij
		
//charAt(idex):idexȡֵ��[0,length())��Χ��ȡһ���ַ�
		String s = new String("abcdefghijklmnopqrstuvwxyz");
        System.out.println("s.charAt(5): " + s.charAt(5) );
        
//int compareTo(String anotherString) ����ǰString������anotherString�Ƚϡ���ȹ�ϵ���أ���
//�����ʱ���������ַ�����0���ַ���ʼ�Ƚϣ����ص�һ������ȵ��ַ��
//��һ��������ϳ��ַ�����ǰ�沿��ǡ���ǽ϶̵��ַ������������ǵĳ��Ȳ    
        String s1 = new String("abcdefghijklmn");
        String s2 = new String("abcdefghij");
       String s3 = new String("abcdefghijalmn");
       System.out.println("s1.compareTo(s2): " + s1.compareTo(s2) ); //���س��Ȳ�  ---4
       System.out.println("s1.compareTo(s3): " + s1.compareTo(s3) ); //����'k'-'a'�Ĳ� ---10
        
 //String concat(String str): ������������������
       String s4=s1.concat(s2);
       s1=s1.concat(s2);
       
 //static String copyValueOf(char[] data, int offset, int count) ��
    //������������char����ת����String��������һ�����캯�����ơ�
       char[] array1={'a','b','��'};
       String str5=String.copyValueOf(array1);
       String str6=String.copyValueOf(array1, 2, 1); //��array1�ϵ�2λ������ȡ1��ֵ
  
//boolean endsWith(String suffix) ����String�����Ƿ���suffix��β��
       String s5 = new String("abcdefghij");
       String s6 = new String("ghij");
       System.out.println("s1.endsWith(s2): " + s5.endsWith(s6) );
       System.out.println("s5: " + s5);
 
 ///boolean equals(Object anObject) ����anObject��Ϊ�ղ����뵱ǰString����һ��
       //������true�����򣬷���false��
       if(s5.equals(s6)==false)
       {
    	   System.out.println("equal: true"); 
       }
       
  //byte[] getBytes() ������String����ת����byte���顣------�ɽ��utf-8��������     
  //     String str7 = "234���";
   //    byte[] b_gbk=str7.getBytes("GBK");
       
  // void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) ��
       //�÷������ַ����������ַ������С�
  //���У�srcBeginΪ��������ʼλ�á�srcEndΪ�����Ľ���λ�á��ַ�����ֵdstΪĿ���ַ����顢
  //dstBeginΪĿ���ַ�����Ŀ�����ʼλ�á�
       char[] s7 = {'I',' ','l','o','v','e',' ','h','e','r','!'};//s1=I love her!
       String s8 = new String("544you!");
       s8.getChars(0,3,s7,7); 
    //s8=I love you! ,ȡs8��[0,3)��ֵ����s7�ĵ�7λ�ÿ�ʼ��������s7���еĴ�С�ͱ���
   
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
       System.out.println(s9.lastIndexOf((int)'b',7));
 //---����fromIndex�Ĳ���Ϊ 7���Ǵ��ַ���acbdebfg�����һ���ַ�g��ʼ��ǰ����λ�������Ǵ��ַ�c��ʼƥ�䣬
 //Ѱ�����һ��ƥ��b��λ�á����Խ��Ϊ 5
       
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
  //String substring(int beginIndex, int endIndex) ��ȡ��beginIndexλ�ÿ�ʼ��endIndexλ�õ����ַ�����
   //[beginIndex,endIndex)
       String sub1=st1.substring(1);
       String sub2=st1.substring(2, 5);
 
 //char[ ] toCharArray() ������String����ת����char���顣
      char [] ch1=st1.toCharArray(); 
      
//public String trim()  ���ظ��ַ���ȥ����ͷ�ͽ�β�ո����ַ���
     String st2=st1.trim(); 
      
 //�����ַ���StringBuilder
     StringBuilder builder = new StringBuilder();	
     builder.append("123");
     builder.append("455");
     builder.append(4); //����Ҳ������һ���ַ�
     String Comstr=builder.toString();
     
     StringBuilder bu1=new StringBuilder("Hello");
     bu1.insert(3, "word");
 
      
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
 
 //split()��public String[] split(String regex) 
          //��һ���ַ�������ָ���ķָ����ָ������طָ�����ַ�������
 ///String����ת��Ϊ�ַ�������String[]    
	     String date = "2008/09/10";
         String[ ] dateAfterSplit= new String[3];
         dateAfterSplit=date.split("/"); 
        //�ԡ�/����Ϊ�ָ������ָ�date�ַ��������ѽ������3���ַ����С�
         for(int i=0;i<dateAfterSplit.length;i++) 
        	 System.out.print(dateAfterSplit[i]+" ");
      // date=Arrays.toString(dateAfterSplit);
         
 //Scanner����ܣ�����̨��������
        Scanner in=new Scanner(System.in);
        System.out.println("your");
 //       String name=in.nextLine();  //��Console�����룬�ɰ�������
 //       String name1=in.next();   //��ȡһ������
        int age=in.nextInt();   //��ȡ����
  		
	//����һ��String[]���� 
        String[] aArray = new String[5];
        String[] bArray = {"a","b","c", "d", "e"};
        String[] cArray = new String[]{"a","b","c","d","e"};	
   //��һ��String[]������List�б�
      //List<String> listA = Arrays.asList(bArray);
      //�൱����ת��ΪlistA��Ȼ�� List<String> arrayList = new ArrayList<String>(listA);
        List<String> arrayList = new ArrayList<String>(Arrays.asList(bArray));
        System.out.println(arrayList);
  //���һ�������Ƿ����ĳ��ֵ  
        boolean bl = Arrays.asList(bArray).contains("a");
        System.out.println(bl);
        
  //��Listת��ΪString[]
        String[] stringArray1 = { "a", "b", "c", "d", "e" };
        List<String> arrayList1 = new ArrayList<String>(Arrays.asList(stringArray1));
        
        String[] stringArr = new String[arrayList1.size()];
        arrayList1.toArray(stringArr);
        for (String str1 : stringArr)
        	System.out.println(str1);
        
   //��String[]ת��ΪSet
        Set<String> set1 = new HashSet<String>(Arrays.asList(stringArray1));
        System.out.println(set1);

           
	}

}
