package ClonePackage;

public class EasyClone implements Cloneable{

	public BasicClass      data1=null;
	public double          data2=0;
	public String          data3=null;
	public StringBuffer    data4=null;
	
	public EasyClone(BasicClass da1,double da2,String da3,StringBuffer da4){
		this.data1=da1;
		this.data2=da2;
		this.data3=da3;
		this.data4=da4;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	
	public void show(){
		System.out.println("da1: "+data1.data);
		System.out.println("da2: "+data2);
		System.out.println("da3: "+data3);
		System.out.println("da4: "+data4);
	}
	
}
