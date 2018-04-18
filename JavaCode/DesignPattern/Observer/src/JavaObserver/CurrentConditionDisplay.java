package JavaObserver;

//其中显示温湿度布告
public class CurrentConditionDisplay implements Observer,DisplayElement{

	public float temperature;
	public float humidity;
	public Subject weatherData;
	
	public CurrentConditionDisplay(Subject weatherData){
		this.weatherData=weatherData;
	//下面的this其实是自身，这样在weatherData对象就有了该观察者的句柄一样
		//向weatherData对象注册
		weatherData.registerObserver(this);
	}
	
	public void removeDisplay(){
		//向weatherData对象注销
		weatherData.removeObserver(this);
	}
	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("Current Cndition:"+temperature+" and "+ humidity);
	}

	@Override
	public void update(float temp, float humidity, float pressure) {
		// TODO Auto-generated method stub
		this.temperature=temp;
		this.humidity=humidity;
		display();
	}

}
