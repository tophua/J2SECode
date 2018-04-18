package JavaObserver;

//������ʾ��ʪ�Ȳ���
public class CurrentConditionDisplay implements Observer,DisplayElement{

	public float temperature;
	public float humidity;
	public Subject weatherData;
	
	public CurrentConditionDisplay(Subject weatherData){
		this.weatherData=weatherData;
	//�����this��ʵ������������weatherData��������˸ù۲��ߵľ��һ��
		//��weatherData����ע��
		weatherData.registerObserver(this);
	}
	
	public void removeDisplay(){
		//��weatherData����ע��
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
