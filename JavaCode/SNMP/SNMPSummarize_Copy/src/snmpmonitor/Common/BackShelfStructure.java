package snmpmonitor.Common;

import java.util.List;

public class BackShelfStructure {
	public ShelfInfo shelf;
	public List<SlotState> lstSlotState;
	public ShelfInfo getShelf() {
		return shelf;
	}
	public void setShelf(ShelfInfo shelf) {
		this.shelf = shelf;
	}
	public List<SlotState> getLstSlotState() {
		return lstSlotState;
	}
	public void setLstSlotState(List<SlotState> lstSlotState) {
		this.lstSlotState = lstSlotState;
	}
}
