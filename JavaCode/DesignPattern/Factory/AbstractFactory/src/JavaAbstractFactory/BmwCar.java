package JavaAbstractFactory;

//奔驰型号--抽象产品
public abstract class BmwCar {
    private String name;  
    
    public abstract void drive();  
      
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
}
