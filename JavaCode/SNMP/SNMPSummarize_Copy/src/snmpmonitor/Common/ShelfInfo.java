package snmpmonitor.Common;

public class ShelfInfo {
	private String ChasId;
	private String ShelfId;	
	private String level;
	private String shelfName;//�������ơ�λ��
	private String shelfpsuA;//��ԴA״̬
	private String shelfpsuB;//��ԴB״̬
	private String shelfvolA;//��ԴA��ѹ״̬
	private String shelfvolB; //��ԴB��ѹ״̬
	private String shelffan;//����״̬
	private String shelftemperature;//�¶�
	private String shelfcoCardNum;//��ǰ�������ӵı��ؿ���Ŀ,���ؿ������뿨�����ӵ�
	private String shelfrmtCardNum; //��ǰ�������ӵ�Զ�˿���Ŀ
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
