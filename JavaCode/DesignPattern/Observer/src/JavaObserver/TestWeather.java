package JavaObserver;

public class TestWeather {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        WeatherData weatherData=new WeatherData();
        CurrentConditionDisplay currentCon=new CurrentConditionDisplay(weatherData);
        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.65f);
        currentCon.removeDisplay();
        weatherData.setMeasurements(82, 70, 29.65f);
	}

}
