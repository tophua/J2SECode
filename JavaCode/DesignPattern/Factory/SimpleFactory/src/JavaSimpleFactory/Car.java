package JavaSimpleFactory;

//�����Ʒ
public abstract class Car {
     private String name; //�����Ʒ��
     public abstract void drive(); //��Ϊ
     
     public String getName(){
    	 return name;
     }
     public void setName(String name){
    	 this.name=name;
     }
}
