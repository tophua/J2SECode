
public class CloudHead {
	byte[] cStartCode;	
	byte    dwCmdType;	
	short   sPacketSN;	
	short	 sPacketNum;
	long	nDataSize;
	byte[]	 cReserved;
	
	public byte[] getcStartCode() {
		return cStartCode;
	}
	public void setcStartCode(byte[] cStartCode) {
		this.cStartCode = cStartCode;
	}
	public byte getDwCmdType() {
		return dwCmdType;
	}
	public void setDwCmdType(byte dwCmdType) {
		this.dwCmdType = dwCmdType;
	}
	public short getsPacketSN() {
		return sPacketSN;
	}
	public void setsPacketSN(short sPacketSN) {
		this.sPacketSN = sPacketSN;
	}
	public short getsPacketNum() {
		return sPacketNum;
	}
	public void setsPacketNum(short sPacketNum) {
		this.sPacketNum = sPacketNum;
	}
	public long getnDataSize() {
		return nDataSize;
	}
	public void setnDataSize(long nDataSize) {
		this.nDataSize = nDataSize;
	}
	public byte[] getcReserved() {
		return cReserved;
	}
	public void setcReserved(byte[] cReserved) {
		this.cReserved = cReserved;
	}
}
