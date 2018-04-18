package JavaSimpleFactory;

//抽象产品
public abstract class Car {
     private String name; //具体产品名
     public abstract void drive(); //行为
     
     public String getName(){
    	 return name;
     }
     public void setName(String name){
    	 this.name=name;
     }
}
