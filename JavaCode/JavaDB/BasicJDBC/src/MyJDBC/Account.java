package MyJDBC;

import java.io.Serializable;

public class Account implements Serializable{
	private static final long serialVersionUID = -4313782718477229465L;
	
	private String id;
	private String name;
	//
	private String money;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
