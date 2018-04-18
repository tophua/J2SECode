package GenericPackage;

public class BasicGenPa<T> {

	private T ob; //定义泛型成员变量
	public BasicGenPa(T ob1){
		this.ob=ob1;
	}
	public T getOb(){
		return ob;
	}
	
	public void setOb(T ob1){
		this.ob=ob1;
	}
	
	public void showType(){
		System.out.println("T实际类型："+ob.getClass().getName());
	}
	
}
