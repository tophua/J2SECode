package snmpmonitor.Common;

public class ShelfState {
	private String ShelfId;
	private String ShelfName; //»ú¼ÜShelfId
	private String Level;
	private String ShelfState; //ShelfÔÚÏß×´Ì¬
	public String getShelfId() {
		return ShelfId;
	}
	public void setShelfId(String shelfId) {
		ShelfId = shelfId;
	}
	public String getShelfName() {
		return ShelfName;
	}
	public void setShelfName(String shelfName) {
		ShelfName = shelfName;
	}
	public String getLevel() {
		return Level;
	}
	public void setLevel(String level) {
		Level = level;
	}
	public String getShelfState() {
		return ShelfState;
	}
	public void setShelfState(String shelfState) {
		ShelfState = shelfState;
	}
}
