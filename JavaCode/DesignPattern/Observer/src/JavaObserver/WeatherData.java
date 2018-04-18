package JavaObserver;

import java.util.ArrayList;

public class WeatherData implements Subject{

	private ArrayList observers;
	private float temperature;
	private float humidity;
	private float preesure;
	
	public WeatherData(){
		observers=new ArrayList();
	}
	@Override
	public void registerObserver(Observer o) {
		// TODO Auto-generated method stub
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		// TODO Auto-generated method stub
		int i=observers.indexOf(o);
		if(i>=0){
			observers.remove(i);
		}
	}

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub
		for(int i=0;i<observers.size();i++){
			Observer observer=(Observer)observers.get(i);
			observer.update(temperature, humidity, preesure);
		}
	}
	
	public void measurementsChanged(){//当对象获得了新数据就通知所有观察者
		notifyObserver();
	}
  
	//这是相当于气象站数据变化，先更新WeatherData对象数据，然后通话观察者
	public void setMeasurements(float temp,float humidity,float pressure){
		this.temperature=temp;
		this.humidity=humidity;
		this.preesure=pressure;
		measurementsChanged();
	}
}
