package snmpmonitor.Common;

public class FrontSearchStructure {

	private String ChassisIP;
	private String Civilcode;
	private String Brand;
	private String Community;
	private String OnlineState; //1和0表示已添加，2表示未添加，3表示不存在
	public String getChassisIP() {
		return ChassisIP;
	}
	public void setChassisIP(String chassisIP) {
		ChassisIP = chassisIP;
	}
	public String getCivilcode() {
		return Civilcode;
	}
	public void setCivilcode(String civilcode) {
		Civilcode = civilcode;
	}
	public String getBrand() {
		return Brand;
	}
	public void setBrand(String brand) {
		Brand = brand;
	}
	public String getCommunity() {
		return Community;
	}
	public void setCommunity(String community) {
		Community = community;
	}
	public String getOnlineState() {
		return OnlineState;
	}
	public void setOnlineState(String onlineState) {
		OnlineState = onlineState;
	}

	
}
