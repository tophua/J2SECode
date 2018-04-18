package JavaAbstractFactory;

public class SportDriver extends Driver3{
    public BenzCar createBenzCar(String car) throws Exception {  
        return new BenzSportCar();  
    }  
    public BmwCar createBmwCar(String car) throws Exception {  
        return new BmwSportCar();  
    } 
}
