package infinova.Otical;

public class LocalAndRemoteCardInfo {
	private String id; //中心卡用1表示，远端卡用2表示
	private String Chaid;	
	private String Sheid;	
	private String pid;	
	private String level;
	private String cardType; //34表示中心卡，35表示远端卡
	private String Fxlink;  //光端口链路状态
	private String LFPState;  //LFP状态	
	private String Txlink1;  //电口链路指示灯TP1
	private String CurMode1;  //TP1双工模式(当前双工)
	private String Mode1;  //TP1速率双工模式
	
	private String Txlink2;  //电口链路指示灯TP2
	private String CurMode2;  //TP2双工模式
	private String Mode2;  //TP2速率双工模式
	
	private String Txlink3;  //电口链路指示灯TP3
	private String CurMode3;  //TP3双工模式
	private String Mode3;  //TP3速率双工模式
	
	private String Txlink4;  //电口链路指示灯TP4
	private String CurMode4;  //TP4双工模式
	private String Mode4;  //TP4速率双工模式
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChaid() {
		return Chaid;
	}
	public void setChaid(String chaid) {
		Chaid = chaid;
	}
	public String getSheid() {
		return Sheid;
	}
	public void setSheid(String sheid) {
		Sheid = sheid;
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
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getFxlink() {
		return Fxlink;
	}
	public void setFxlink(String fxlink) {
		Fxlink = fxlink;
	}
	public String getLFPState() {
		return LFPState;
	}
	public void setLFPState(String lFPState) {
		LFPState = lFPState;
	}
	public String getCurMode1() {
		return CurMode1;
	}
	public void setCurMode1(String curMode1) {
		CurMode1 = curMode1;
	}
	public String getTxlink1() {
		return Txlink1;
	}
	public void setTxlink1(String txlink1) {
		Txlink1 = txlink1;
	}
	public String getMode1() {
		return Mode1;
	}
	public void setMode1(String mode1) {
		Mode1 = mode1;
	}
	public String getCurMode2() {
		return CurMode2;
	}
	public void setCurMode2(String curMode2) {
		CurMode2 = curMode2;
	}
	public String getTxlink2() {
		return Txlink2;
	}
	public void setTxlink2(String txlink2) {
		Txlink2 = txlink2;
	}
	public String getMode2() {
		return Mode2;
	}
	public void setMode2(String mode2) {
		Mode2 = mode2;
	}
	public String getCurMode3() {
		return CurMode3;
	}
	public void setCurMode3(String curMode3) {
		CurMode3 = curMode3;
	}
	public String getTxlink3() {
		return Txlink3;
	}
	public void setTxlink3(String txlink3) {
		Txlink3 = txlink3;
	}
	public String getMode3() {
		return Mode3;
	}
	public void setMode3(String mode3) {
		Mode3 = mode3;
	}
	public String getCurMode4() {
		return CurMode4;
	}
	public void setCurMode4(String curMode4) {
		CurMode4 = curMode4;
	}
	public String getTxlink4() {
		return Txlink4;
	}
	public void setTxlink4(String txlink4) {
		Txlink4 = txlink4;
	}
	public String getMode4() {
		return Mode4;
	}
	public void setMode4(String mode4) {
		Mode4 = mode4;
	}
		
}
