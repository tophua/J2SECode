package infinova.Otical;

public class SlotInfo {
	private String id;
	private String ppid;	
	private String pid;	
	private String level;
//	private String shelfNum;//卡槽总数(包括M的)
	private String LocalCardType;  //包括MCardType
	private String RemoteCardType;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPpid() {
		return ppid;
	}
	public void setPpid(String ppid) {
		this.ppid = ppid;
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
	
	
}
