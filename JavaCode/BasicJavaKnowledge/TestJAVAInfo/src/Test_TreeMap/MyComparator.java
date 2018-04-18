package Test_TreeMap;

import java.util.Comparator;

class MyComparator implements Comparator<TestTemp>{
	
	 @Override 
	 public int compare(TestTemp o1, TestTemp o2) {  
	        // TODO Auto-generated method stub  
	        return o1.salary - o2.salary;  
	    }  
}
