package snmpmonitor.Common;

import java.util.List;

public class AddDeviceBackStructure {
	private String Civilcode;
	private String ChassisId;
	private String ChassisIP;
	private String ChassisName; //����ChassisId(ChassisIP)
	private String Brand;
	private String ShelfNum;	
	private String ChassisState; //Chassis����״̬��1���ߣ�0���ߣ�-1������-2������
	private List<ShelfState> lst;
	public String getCivilcode() {
		return Civilcode;
	}
	public void setCivilcode(String civilcode) {
		Civilcode = civilcode;
	}
	public String getChassisId() {
		return ChassisId;
	}
	public void setChassisId(String chassisId) {
		ChassisId = chassisId;
	}
	public String getChassisIP() {
		return ChassisIP;
	}
	public void setChassisIP(String chassisIP) {
		ChassisIP = chassisIP;
	}
	public String getChassisName() {
		return ChassisName;
	}
	public void setChassisName(String chassisName) {
		ChassisName = chassisName;
	}
	public String getBrand() {
		return Brand;
	}
	public void setBrand(String brand) {
		Brand = brand;
	}
	public String getShelfNum() {
		return ShelfNum;
	}
	public void setShelfNum(String shelfNum) {
		ShelfNum = shelfNum;
	}
	public String getChassisState() {
		return ChassisState;
	}
	public void setChassisState(String chassisState) {
		ChassisState = chassisState;
	}
	public List<ShelfState> getLst() {
		return lst;
	}
	public void setLst(List<ShelfState> lst) {
		this.lst = lst;
	}
	
}
