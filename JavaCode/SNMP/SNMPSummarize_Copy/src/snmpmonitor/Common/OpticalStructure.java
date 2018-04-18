package snmpmonitor.Common;

import java.util.List;

public class OpticalStructure {
	private String PId;
	private String ChassisId;
	private String Level;
	private String Civilcode;
	private String Brand;
	private String Community;
	private String ChassisIP;
	private String ChassisMask;
	private String ChassisGateway;
	private String ShelfNum;
	private String OnlineState;
	private String UpdateTime;
	private List<OpticalShelfStructure> listShelfinfo;
	public String getPId() {
		return PId;
	}
	public void setPId(String pId) {
		PId = pId;
	}
	public String getChassisId() {
		return ChassisId;
	}
	public void setChassisId(String chassisId) {
		ChassisId = chassisId;
	}
	public String getLevel() {
		return Level;
	}
	public void setLevel(String level) {
		Level = level;
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
	public String getChassisIP() {
		return ChassisIP;
	}
	public void setChassisIP(String chassisIP) {
		ChassisIP = chassisIP;
	}
	public String getChassisMask() {
		return ChassisMask;
	}
	public void setChassisMask(String chassisMask) {
		ChassisMask = chassisMask;
	}
	public String getChassisGateway() {
		return ChassisGateway;
	}
	public void setChassisGateway(String chassisGateway) {
		ChassisGateway = chassisGateway;
	}
	public String getShelfNum() {
		return ShelfNum;
	}
	public void setShelfNum(String shelfNum) {
		ShelfNum = shelfNum;
	}
	public String getOnlineState() {
		return OnlineState;
	}
	public void setOnlineState(String onlineState) {
		OnlineState = onlineState;
	}
	public String getUpdateTime() {
		return UpdateTime;
	}
	public void setUpdateTime(String updateTime) {
		UpdateTime = updateTime;
	}
	public List<OpticalShelfStructure> getListShelfinfo() {
		return listShelfinfo;
	}
	public void setListShelfinfo(List<OpticalShelfStructure> listShelfinfo) {
		this.listShelfinfo = listShelfinfo;
	}
}
