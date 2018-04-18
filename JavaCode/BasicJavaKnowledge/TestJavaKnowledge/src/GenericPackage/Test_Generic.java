package GenericPackage;

class Pair<T>{
	public Pair(){first=null;second=null;}
	public Pair(T first,T second){this.first=first;this.second=second;}
	
	public T getFirst(){return first;}
	public T getSecond(){return second;}
	
	public void setFirst(T newValue){ first=newValue;}
	public void setSecond(T newValue){second=newValue;}
	
	private T first;
	private T second;
	
}
public class Test_Generic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      String[] words={"Mary","had","a","little","lamb"};
      Pair<String> mm=Arraylg.minmax(words);
      System.out.println("min="+mm.getFirst());
      System.out.println("max="+mm.getSecond());
	}

}

class Arraylg{
	
	public static Pair<String> minmax(String[] a){
		if(a==null || a.length==0) return null;
		String min=a[0];
		String max=a[0];
		for(int i=1;i<a.length;i++){
			if(min.compareTo(a[i])>0) min=a[0];
			if(max.compareTo(a[i])<0) max=a[0];
		}
		return new Pair<String>(min,max);
	}
}