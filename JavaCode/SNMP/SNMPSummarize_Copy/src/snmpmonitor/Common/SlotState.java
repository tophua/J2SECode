package snmpmonitor.Common;

public class SlotState {
	private String SlotId;
	private String Level;
	private String SlotState; //Slot����״̬
	private String LocalCardState; //ÿ�������½��˿���Զ�˿���,-2��ʾ������,1���ߣ�0���ߣ�-1������
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
