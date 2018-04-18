package snmpmonitor.Common;

public class SlotInfo {
	private String ChasId;
	private String ShelfId;	
	private String SlotId;	
	private String Level;
	private String LocalCardType;  //°üÀ¨MCardType 34»ò100
	private String RemoteCardType; //35
	private String OnlineState;
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
	public String getLevel() {
		return Level;
	}
	public void setLevel(String level) {
		Level = level;
	}
	public String getLocalCardType() {
		return LocalCardType;
	}
	public void setLocalCardType(String localCardType) {
		LocalCardType = localCardType;
	}
	public String getRemoteCardType() {
		return RemoteCardType;
	}
	public void setRemoteCardType(String remoteCardType) {
		RemoteCardType = remoteCardType;
	}
	public String getOnlineState() {
		return OnlineState;
	}
	public void setOnlineState(String onlineState) {
		OnlineState = onlineState;
	}
	
}
