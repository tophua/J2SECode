package snmpmonitor.Common;

public class ShelfInfo {
	private String ChasId;
	private String ShelfId;	
	private String level;
	private String shelfName;//机架名称、位置
	private String shelfpsuA;//电源A状态
	private String shelfpsuB;//电源B状态
	private String shelfvolA;//电源A电压状态
	private String shelfvolB; //电源B电压状态
	private String shelffan;//风扇状态
	private String shelftemperature;//温度
	private String shelfcoCardNum;//当前机架连接的本地卡数目,本地卡就是与卡槽连接的
	private String shelfrmtCardNum; //当前机架连接的远端卡数目
	private String shelfOnlineState;
	public String getChasId() {
		return ChasId;
	}
	public void setChasId(String chasId) {
		ChasId = chasId;
	}
	public String getShelfId() {
		return ShelfId;
	}
	public void setShelfId(String shelfId) {
		ShelfId = shelfId;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getShelfName() {
		return shelfName;
	}
	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}
	public String getShelfpsuA() {
		return shelfpsuA;
	}
	public void setShelfpsuA(String shelfpsuA) {
		this.shelfpsuA = shelfpsuA;
	}
	public String getShelfpsuB() {
		return shelfpsuB;
	}
	public void setShelfpsuB(String shelfpsuB) {
		this.shelfpsuB = shelfpsuB;
	}
	public String getShelfvolA() {
		return shelfvolA;
	}
	public void setShelfvolA(String shelfvolA) {
		this.shelfvolA = shelfvolA;
	}
	public String getShelfvolB() {
		return shelfvolB;
	}
	public void setShelfvolB(String shelfvolB) {
		this.shelfvolB = shelfvolB;
	}
	public String getShelffan() {
		return shelffan;
	}
	public void setShelffan(String shelffan) {
		this.shelffan = shelffan;
	}
	public String getShelftemperature() {
		return shelftemperature;
	}
	public void setShelftemperature(String shelftemperature) {
		this.shelftemperature = shelftemperature;
	}
	public String getShelfcoCardNum() {
		return shelfcoCardNum;
	}
	public void setShelfcoCardNum(String shelfcoCardNum) {
		this.shelfcoCardNum = shelfcoCardNum;
	}
	public String getShelfrmtCardNum() {
		return shelfrmtCardNum;
	}
	public void setShelfrmtCardNum(String shelfrmtCardNum) {
		this.shelfrmtCardNum = shelfrmtCardNum;
	}
	public String getShelfOnlineState() {
		return shelfOnlineState;
	}
	public void setShelfOnlineState(String shelfOnlineState) {
		this.shelfOnlineState = shelfOnlineState;
	}
}
