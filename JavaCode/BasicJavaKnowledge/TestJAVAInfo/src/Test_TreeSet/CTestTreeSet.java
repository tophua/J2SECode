package Test_TreeSet;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

class CTeacher implements Comparable{
	int num;
	String name;
	CTeacher(String name,int num){
		this.num=num;
		this.name=name;
	}
	@Override 
	//java.lang ,add()������������toString()����
	public String toString(){
		return "ѧ��:"+num+"����:"+name;
	}
	
	@Override
	public int compareTo(Object o){
		CTeacher ss =(CTeacher) o;
		int result =num > ss.num ? 1: (num==ss.num) ? 0:-1 ;
		if(result ==0){
			result =name.compareTo(ss.name);
		}
		return result;
	}
}
public class CTestTreeSet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	//TreeSet����Ӻͱ���
		Set<String> StreeSet=(Set<String>) new TreeSet<String>();
		StreeSet.add("abc");
		StreeSet.add("xyz");
		StreeSet.add(new String("rst"));
		
		System.out.println(StreeSet);
		
		Iterator ite= StreeSet.iterator();
		while(ite.hasNext()){
			System.out.println(ite.next());
		}
		//�淶�ı���Iterator
		Iterator<String> ite1= StreeSet.iterator();
		while(ite1.hasNext()){
			System.out.println(ite1.next());
		}

    //�Զ�����������,��ʵ�ֱȽϷ���
		 Set<CTeacher> treeSet = new TreeSet<CTeacher>();    
		 treeSet.add(new CTeacher("zhangsan", 2));    
		 treeSet.add(new CTeacher("lisi", 1));    
		 treeSet.add(new CTeacher("wangwu", 3));    
		 treeSet.add(new CTeacher("mazi", 3));   
		 System.out.println(treeSet);//ֱ�����  (�����toString())
		 Iterator<CTeacher> itTSet = treeSet.iterator();//�������  
		 while(itTSet.hasNext())    
		 System.out.print(itTSet.next() + "\t");    
		 System.out.println();   	
	}

}
