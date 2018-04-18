package snmpmonitor.Common;

public class SlotState {
	private String SlotId;
	private String Level;
	private String SlotState; //Slot在线状态
	private String LocalCardState; //每个卡槽下近端卡和远端卡的,-2表示不存在,1在线，0离线，-1不管理
	private String RemoteCardState;
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
	public String getSlotState() {
		return SlotState;
	}
	public void setSlotState(String slotState) {
		SlotState = slotState;
	}
	public String getLocalCardState() {
		return LocalCardState;
	}
	public void setLocalCardState(String localCardState) {
		LocalCardState = localCardState;
	}
	public String getRemoteCardState() {
		return RemoteCardState;
	}
	public void setRemoteCardState(String remoteCardState) {
		RemoteCardState = remoteCardState;
	}
}
