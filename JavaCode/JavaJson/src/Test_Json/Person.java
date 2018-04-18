package Test_Json;

public class Person {
   String Num;
   String Name;
   
    Person(String num,String name){
    	Num=num;
    	Name=name;
    }

	public String getNum() {
		return Num;
	}

	public void setNum(String num) {
		Num = num;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

}
