package snmpmonitor.Common;

public class CardInfo {
	private String ChasId;
	private String ShelfId;	
	private String SlotId;	
	private String CardId;	//���Ŀ���1��ʾ��Զ�˿���2��ʾ
	private String Level;
	private String cardType; //34��ʾ���Ŀ���35��ʾԶ�˿�
	private String Fxlink;  //��˿���·״̬
	private String LFPState;  //LFP״̬
	private String Txlink1;  //�����·ָʾ��TP1
	private String CurMode1;  //TP1˫��ģʽ(��ǰ˫��)
	private String Mode1;  //TP1����˫��ģʽ
	private String Txlink2;  //�����·ָʾ��TP2
	private String CurMode2;  //TP2˫��ģʽ
	private String Mode2;  //TP2����˫��ģʽ
	private String Txlink3;  //�����·ָʾ��TP3
	private String CurMode3;  //TP3˫��ģʽ
	private String Mode3;  //TP3����˫��ģʽ
	private String Txlink4;  //�����·ָʾ��TP4
	private String CurMode4;  //TP4˫��ģʽ
	private String Mode4;  //TP4����˫��ģʽ
	private String OnlineState;  //����״̬
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
	public String getSlotId() {
		return SlotId;
	}
	public void setSlotId(String slotId) {
		SlotId = slotId;
	}
	public String getCardId() {
		return CardId;
	}
	public void setCardId(String cardId) {
		CardId = cardId;
	}
	public String getLevel() {
		return Level;
	}
	public void setLevel(String level) {
		Level = level;
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
	public String getTxlink1() {
		return Txlink1;
	}
	public void setTxlink1(String txlink1) {
		Txlink1 = txlink1;
	}
	public String getCurMode1() {
		return CurMode1;
	}
	public void setCurMode1(String curMode1) {
		CurMode1 = curMode1;
	}
	public String getMode1() {
		return Mode1;
	}
	public void setMode1(String mode1) {
		Mode1 = mode1;
	}
	public String getTxlink2() {
		return Txlink2;
	}
	public void setTxlink2(String txlink2) {
		Txlink2 = txlink2;
	}
	public String getCurMode2() {
		return CurMode2;
	}
	public void setCurMode2(String curMode2) {
		CurMode2 = curMode2;
	}
	public String getMode2() {
		return Mode2;
	}
	public void setMode2(String mode2) {
		Mode2 = mode2;
	}
	public String getTxlink3() {
		return Txlink3;
	}
	public void setTxlink3(String txlink3) {
		Txlink3 = txlink3;
	}
	public String getCurMode3() {
		return CurMode3;
	}
	public void setCurMode3(String curMode3) {
		CurMode3 = curMode3;
	}
	public String getMode3() {
		return Mode3;
	}
	public void setMode3(String mode3) {
		Mode3 = mode3;
	}
	public String getTxlink4() {
		return Txlink4;
	}
	public void setTxlink4(String txlink4) {
		Txlink4 = txlink4;
	}
	public String getCurMode4() {
		return CurMode4;
	}
	public void setCurMode4(String curMode4) {
		CurMode4 = curMode4;
	}
	public String getMode4() {
		return Mode4;
	}
	public void setMode4(String mode4) {
		Mode4 = mode4;
	}
	public String getOnlineState() {
		return OnlineState;
	}
	public void setOnlineState(String onlineState) {
		OnlineState = onlineState;
	}
}