package Test_HashCode;

import java.util.HashMap;

class People{
	private String name;
	private int age;
	
	public People(String name,int age){
		this.name=name;
		this.age=age;
	}
	public void setAge(int age){
		this.age=age;
	}
	
	@Override
	//重写了父类hashCode()方法
	public int hashCode(){
		return name.hashCode()*37+age;
	}
	
	@Override
	//重写了父类hashCode()方法
	public boolean equals(Object obj){
		return this.equals(((People)obj).name) && this.age==((People)obj).age;
	}
}



public class CTestHashCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		People p1=new People("jack", 12);
		System.out.println(p1.hashCode());
		
		HashMap<People,Integer> hash1=new HashMap<People,Integer>();
		hash1.put(p1, 1);
		
		System.out.println(hash1.get(new People("jack",12)));
	}

}
