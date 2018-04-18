package GenericPackage;

public class GenFunction {
//·ºÐÍ·½·¨
	public <T> void f(T x){
		System.out.println(x.getClass().getName());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenFunction ea=new GenFunction();
		ea.f(" ");
		ea.f(10);
		ea.f('a');
		ea.f(ea);
	}

}
