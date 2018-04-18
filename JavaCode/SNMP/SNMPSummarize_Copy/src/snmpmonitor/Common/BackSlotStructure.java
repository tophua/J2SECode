package snmpmonitor.Common;

import java.util.List;

public class BackSlotStructure {
	public SlotInfo slot;
	public List<CardInfo> lstCardinfo;
	public SlotInfo getSlot() {
		return slot;
	}
	public void setSlot(SlotInfo slot) {
		this.slot = slot;
	}
	public List<CardInfo> getLstCardinfo() {
		return lstCardinfo;
	}
	public void setLstCardinfo(List<CardInfo> lstCardinfo) {
		this.lstCardinfo = lstCardinfo;
	}
}
