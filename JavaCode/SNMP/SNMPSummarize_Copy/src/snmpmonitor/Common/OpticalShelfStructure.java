package snmpmonitor.Common;

import java.util.List;

public class OpticalShelfStructure {
	  public ShelfInfo shelfinfo;
	   public List<BackSlotStructure> listSlotinfo;
	public ShelfInfo getShelfinfo() {
		return shelfinfo;
	}
	public void setShelfinfo(ShelfInfo shelfinfo) {
		this.shelfinfo = shelfinfo;
	}
	public List<BackSlotStructure> getListSlotinfo() {
		return listSlotinfo;
	}
	public void setListSlotinfo(List<BackSlotStructure> listSlotinfo) {
		this.listSlotinfo = listSlotinfo;
	}
}
