package Test_String;

import java.util.*;

import org.apache.commons.lang.ArrayUtils;

public class CTestString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
  //String和int、double等转换	
	int i1=10;
	String stri1=Integer.toString(i1);
	double d1=10.0;
	String strd1=Double.toString(d1);
	
	int i2=Integer.valueOf(stri1).intValue();
	int i3=Integer.parseInt("11");
	double d2=Double.valueOf(strd1).doubleValue(); 
	double d3 = Double.parseDouble("123.2"); 

//字符数组char[]、字符串数组String[]、String相互转换   
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
	char[] data={'a', 'b', '你'};
	String strC = new String(data);
	//String--char[]
	String strD = "123abc";
	char[] arD = strD.toCharArray();//char数组
	for(int i =0;i<arD.length;i++)
	{
      System.out.println(arD[i]);
	}
	//String--byte[]
	String strb="Hello23";
	byte[] by1=strb.getBytes();
	//byte[]--String
	String strb2=new String(by1);
	
 ///构造String
		byte[] b = {'a','b','c','d','e','f','g','h','i','j'};
		char[] c = {'0','1','2','3','4','5','6','7','8','9'};
		String strb3=new String(b);
		String strSub=new String(b,3,2); //从b中第3位开始取两个值
		String strc=new String(c);
		String strSuc=new String(c,3,2); //从c中第3位开始取两个值
		System.out.println("strb:"+strb3); //strb:abcdefghij
		
//charAt(idex):idex取值是[0,length())范围，取一个字符
		String s = new String("abcdefghijklmnopqrstuvwxyz");
        System.out.println("s.charAt(5): " + s.charAt(5) );
        
//int compareTo(String anotherString) ：当前String对象与anotherString比较。相等关系返回０；
//不相等时，从两个字符串第0个字符开始比较，返回第一个不相等的字符差，
//另一种情况，较长字符串的前面部分恰巧是较短的字符串，返回它们的长度差。    
        String s1 = new String("abcdefghijklmn");
        String s2 = new String("abcdefghij");
       String s3 = new String("abcdefghijalmn");
       System.out.println("s1.compareTo(s2): " + s1.compareTo(s2) ); //返回长度差  ---4
       System.out.println("s1.compareTo(s3): " + s1.compareTo(s3) ); //返回'k'-'a'的差 ---10
        
 //String concat(String str): 将两个对象连接起来
       String s4=s1.concat(s2);
       s1=s1.concat(s2);
       
 //static String copyValueOf(char[] data, int offset, int count) ：
    //这两个方法将char数组转换成String，与其中一个构造函数类似。
       char[] array1={'a','b','你'};
       String str5=String.copyValueOf(array1);
       String str6=String.copyValueOf(array1, 2, 1); //从array1上第2位置往后取1个值
  
//boolean endsWith(String suffix) ：该String对象是否以suffix结尾。
       String s5 = new String("abcdefghij");
       String s6 = new String("ghij");
       System.out.println("s1.endsWith(s2): " + s5.endsWith(s6) );
       System.out.println("s5: " + s5);
 
 ///boolean equals(Object anObject) ：当anObject不为空并且与当前String对象一样
       //，返回true；否则，返回false。
       if(s5.equals(s6)==false)
       {
    	   System.out.println("equal: true"); 
       }
       
  //byte[] getBytes() ：将该String对象转换成byte数组。------可解决utf-8乱码问题     
  //     String str7 = "234年后";
   //    byte[] b_gbk=str7.getBytes("GBK");
       
  // void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) ：
       //该方法将字符串拷贝到字符数组中。
  //其中，srcBegin为拷贝的起始位置、srcEnd为拷贝的结束位置、字符串数值dst为目标字符数组、
  //dstBegin为目标字符数组的拷贝起始位置。
       char[] s7 = {'I',' ','l','o','v','e',' ','h','e','r','!'};//s1=I love her!
       String s8 = new String("544you!");
       s8.getChars(0,3,s7,7); 
    //s8=I love you! ,取s8的[0,3)的值存入s7的第7位置开始，当超过s7现有的大小就报错
   
  //int indexOf(int ch) ：只找第一个匹配字符位置。     
  //int indexOf(int ch, int fromIndex) ：从fromIndex开始找第一个匹配字符位置。     
  // int indexOf(String str) ：只找第一个匹配字符串位置。
  //int indexOf(String str, int fromIndex) ：从fromIndex开始找第一个匹配字符串位置。     
       String st = new String("write once, run anywhere!");
       String ss = new String("run");
       System.out.println("s.indexOf('r'): " + st.indexOf('r') ); //---1
       System.out.println("s.indexOf('r',2): " + st.indexOf('r',2) );  //----12
       System.out.println("s.indexOf(ss): " + st.indexOf(ss) );  //----12
       
//int lastIndexOf(int ch)   int lastIndexOf(int ch, int fromIndex)
//int lastIndexOf(String str)
//int lastIndexOf(String str, int fromIndex) 以上四个方法与13、14、15、16类似，不同的是：找最后一个匹配的内容。
       String s9 = new String("acbdebfg");     
       System.out.println(s9.lastIndexOf((int)'b',7));
 //---其中fromIndex的参数为 7，是从字符串acbdebfg的最后一个字符g开始往前数的位数。既是从字符c开始匹配，
 //寻找最后一个匹配b的位置。所以结果为 5
       
 //String replace(char oldChar, char newChar) ：将字符号串中所有的oldChar替换成newChar。
       String str10=s9.replace('b', '1');
   
  ///boolean startsWith(String prefix) ：该String对象是否以prefix开始。
  //boolean startsWith(String prefix, int toffset) ：该String对象从toffset位置算起，是否以prefix开始。
       String st1 = new String("  write once, run anywhere!");
       String sst1 = new String("write");
       String ssst1 = new String("once");
       System.out.println("s.startsWith(ss): " + st1.startsWith(sst1) );  //---true
       System.out.println("s.startsWith(sss,6): " + st1.startsWith(ssst1,6) );  //---true
       
  //String substring(int beginIndex) ：取从beginIndex位置开始到结束的子字符串。[beginIndex,..)
  //String substring(int beginIndex, int endIndex) ：取从beginIndex位置开始到endIndex位置的子字符串。
   //[beginIndex,endIndex)
       String sub1=st1.substring(1);
       String sub2=st1.substring(2, 5);
 
 //char[ ] toCharArray() ：将该String对象转换成char数组。
      char [] ch1=st1.toCharArray(); 
      
//public String trim()  返回该字符串去掉开头和结尾空格后的字符串
     String st2=st1.trim(); 
      
 //构建字符串StringBuilder
     StringBuilder builder = new StringBuilder();	
     builder.append("123");
     builder.append("455");
     builder.append(4); //这样也被当成一个字符
     String Comstr=builder.toString();
     
     StringBuilder bu1=new StringBuilder("Hello");
     bu1.insert(3, "word");
 
      
//String toLowerCase() ：将字符串转换成小写。
//String toUpperCase() ：将字符串转换成大写。      
      String str11 = new String("java.lang.Class String");
      System.out.println("s.toUpperCase(): " + str11.toUpperCase() );
      System.out.println("s.toLowerCase(): " + str11.toLowerCase() );
  
 //将不同类型转换为Java字符型
  // static String valueOf(boolean b)    static String valueOf(char c)
  //static String valueOf(char[] data)   static String valueOf(char[] data, int offset, int count)
 //static String valueOf(double d)       static String valueOf(float f)
  //static String valueOf(int i)     static String valueOf(long l)    static String valueOf(Object obj)    
 
 //split()：public String[] split(String regex) 
          //将一个字符串按照指定的分隔符分隔，返回分隔后的字符串数组
 ///String内容转换为字符串数组String[]    
	     String date = "2008/09/10";
         String[ ] dateAfterSplit= new String[3];
         dateAfterSplit=date.split("/"); 
        //以“/”作为分隔符来分割date字符串，并把结果放入3个字符串中。
         for(int i=0;i<dateAfterSplit.length;i++) 
        	 System.out.print(dateAfterSplit[i]+" ");
      // date=Arrays.toString(dateAfterSplit);
         
 //Scanner类介绍，控制台输入内容
        Scanner in=new Scanner(System.in);
        System.out.println("your");
 //       String name=in.nextLine();  //在Console中输入，可包括空行
 //       String name1=in.next();   //读取一个单词
        int age=in.nextInt();   //读取整数
  		
	//声明一个String[]数组 
        String[] aArray = new String[5];
        String[] bArray = {"a","b","c", "d", "e"};
        String[] cArray = new String[]{"a","b","c","d","e"};	
   //从一个String[]数组变成List列表
      //List<String> listA = Arrays.asList(bArray);
      //相当于先转换为listA，然后 List<String> arrayList = new ArrayList<String>(listA);
        List<String> arrayList = new ArrayList<String>(Arrays.asList(bArray));
        System.out.println(arrayList);
  //检查一个数组是否包含某个值  
        boolean bl = Arrays.asList(bArray).contains("a");
        System.out.println(bl);
        
  //将List转换为String[]
        String[] stringArray1 = { "a", "b", "c", "d", "e" };
        List<String> arrayList1 = new ArrayList<String>(Arrays.asList(stringArray1));
        
        String[] stringArr = new String[arrayList1.size()];
        arrayList1.toArray(stringArr);
        for (String str1 : stringArr)
        	System.out.println(str1);
        
   //将String[]转换为Set
        Set<String> set1 = new HashSet<String>(Arrays.asList(stringArray1));
        System.out.println(set1);

           
	}

}
