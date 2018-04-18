package JavaAbstractFactory;

//宝马型号--抽象产品
public abstract class BenzCar {
	private String name;

	public abstract void drive();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
