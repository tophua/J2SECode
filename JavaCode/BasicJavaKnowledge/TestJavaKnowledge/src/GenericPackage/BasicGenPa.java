package GenericPackage;

public class BasicGenPa<T> {

	private T ob; //���巺�ͳ�Ա����
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
		System.out.println("Tʵ�����ͣ�"+ob.getClass().getName());
	}
	
}
