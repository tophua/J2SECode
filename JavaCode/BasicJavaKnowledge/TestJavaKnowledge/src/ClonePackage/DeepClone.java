package ClonePackage;

public class DeepClone implements Cloneable{
	public BasicClass      data1=null;
	public double          data2=0;
	public String          data3=null;
	public StringBuffer    data4=null;
	
	public DeepClone(BasicClass da1,double da2,String da3,StringBuffer da4){
		this.data1=da1;
		this.data2=da2;
		this.data3=da3;
		this.data4=da4;
	}
	
	//深拷贝重写clone()方法
	@Override
	public Object clone() throws CloneNotSupportedException{
 
		BasicClass dat1=new BasicClass(this.data1.data); //重新new一个空间
		double     dat2=this.data2;
	//	String     dat3=new String(this.data3);
		String     dat3=this.data3;
		StringBuffer  dat4=new StringBuffer(this.data4.toString());
		
		DeepClone copy=new DeepClone(data1,data2,data3,data4); //重新New一块内存
		return copy;
	}
	
	public void show(){
		System.out.println("da1: "+data1.data);
		System.out.println("da2: "+data2);
		System.out.println("da3: "+data3);
		System.out.println("da4: "+data4);
	}
      
}
