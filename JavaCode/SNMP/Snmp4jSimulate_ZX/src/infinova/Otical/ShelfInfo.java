package infinova.Otical;

public class ShelfInfo {

	private String id;
	private String pid;	
	private String level;
	private String shelfName;//机架名称、位置
	private String shelfIP; //机架IP
	private String shelfMask; //机架子网掩码
	private String shelfGateway;  //机架网关
	private String shelfpsuA;//电源A状态
	private String shelfpsuB;//电源B状态
	private String shelfvolA;//电源A电压状态
	private String shelfvolB; //电源B电压状态
	private String shelffan;//风扇状态
	private String shelftemperature;//温度
	private String shelfcoCardNum;//当前机架连接的本地卡数目,本地卡就是与卡槽连接的
	private String shelfrmtCardNum; //当前机架连接的远端卡数目
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getshelfName() {
		return shelfName;
	}
	public void setshelfName(String shelfName) {
		this.shelfName = shelfName;
	}
	public String getshelfIP() {
		return shelfIP;
	}
	public void setshelfIP(String strIP) {
		this.shelfIP = strIP;
	}
	public String getshelfMask() {
		return shelfMask;
	}
	public void setshelfMask(String chaMask) {
		this.shelfMask = chaMask;
	}
	public String getshelfGateway() {
		return shelfGateway;
	}
	public void setshelfGateway(String chaGateway) {
		this.shelfGateway = chaGateway;
	}
	public String getshelfpsuA() {
		return shelfpsuA;
	}
	public void setshelfpsuA(String shelfpsuA) {
		this.shelfpsuA = shelfpsuA;
	}
	public String getshelfpsuB() {
		return shelfpsuB;
	}
	public void setshelfpsuB(String shelfpsuB) {
		this.shelfpsuB = shelfpsuB;
	}
	public String getshelfvolA() {
		return shelfvolA;
	}
	public void setshelfvolA(String shelfvolA) {
		this.shelfvolA = shelfvolA;
	}
	public String getshelfvolB() {
		return shelfvolB;
	}
	public void setshelfvolB(String shelfvolB) {
		this.shelfvolB = shelfvolB;
	}
	public String getshelffan() {
		return shelffan;
	}
	public void setshelffan(String shelffan) {
		this.shelffan = shelffan;
	}
	public String getshelftemperature() {
		return shelftemperature;
	}
	public void setshelftemperature(String shelftemperature) {
		this.shelftemperature = shelftemperature;
	}
	public String getshelfcoCardNum() {
		return shelfcoCardNum;
	}
	public void setshelfcoCardNum(String shelfcoCardNum) {
		this.shelfcoCardNum = shelfcoCardNum;
	}
	public String getshelfrmtCardNum() {
		return shelfrmtCardNum;
	}
	public void setshelfrmtCardNum(String shelfrmtCardNum) {
		this.shelfrmtCardNum = shelfrmtCardNum;
	}
}
