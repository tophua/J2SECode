package infinova.Otical;
//������Ϣ
public class ChassisInfo {
	private String id;
	private String pid;	
	private String level;
	private String shelfNum;//�������������Ŀ
	private String chassisIP; //����IP
	private String chassisMask; //������������
	private String chassisGateway;  //��������
	private String chassisOnline;  //����״̬
	private String chassisUpTime;  //����ʱ��
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
	public String getshelfNum() {
		return shelfNum;
	}
	public void setshelfNum(String Num) {
		this.shelfNum = Num;
	}
	public String getchassisIP() {
		return chassisIP;
	}
	public void setchassisIP(String strIP) {
		this.chassisIP = strIP;
	}
	public String getchassisMask() {
		return chassisMask;
	}
	public void setchassisMask(String chaMask) {
		this.chassisMask = chaMask;
	}
	public String getchassisGateway() {
		return chassisGateway;
	}
	public void setchassisGateway(String chaGateway) {
		this.chassisGateway = chaGateway;
	}
	public String getchassisOnline() {
		return chassisOnline;
	}
	public void setchassisOnline(String chassisOnline) {
		this.chassisOnline = chassisOnline;
	}
	public String getchassisUpTime() {
		return chassisUpTime;
	}
	public void setchassisUpTime(String chassisUpTime) {
		this.chassisUpTime = chassisUpTime;
	}
}
