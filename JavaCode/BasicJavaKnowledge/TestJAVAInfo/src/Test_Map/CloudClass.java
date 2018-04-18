package Test_Map;

import java.util.List;

import TreeMultimap.CloudChannelClass;

public class CloudClass {
	    String strDeviceName;  //物联网云节点名称
	    String strDeviceSIMNumber;   //物联网云节点SIM卡号码
	    String strDeviceIP;   //物联网云节点IP
	    String strHardwareVersions;//物联网云节点硬件版本
	    String strSoftwareVersions;//物联网云节点软件版本
	    String strDeviceState;//设备状态
	    public List<CloudChannelClass> listCloudChannel;
		public String getStrDeviceName() {
			return strDeviceName;
		}
		public void setStrDeviceName(String strDeviceName) {
			this.strDeviceName = strDeviceName;
		}
		public String getStrDeviceSIMNumber() {
			return strDeviceSIMNumber;
		}
		public void setStrDeviceSIMNumber(String strDeviceSIMNumber) {
			this.strDeviceSIMNumber = strDeviceSIMNumber;
		}
		public String getStrDeviceIP() {
			return strDeviceIP;
		}
		public void setStrDeviceIP(String strDeviceIP) {
			this.strDeviceIP = strDeviceIP;
		}
		public String getStrHardwareVersions() {
			return strHardwareVersions;
		}
		public void setStrHardwareVersions(String strHardwareVersions) {
			this.strHardwareVersions = strHardwareVersions;
		}
		public String getStrSoftwareVersions() {
			return strSoftwareVersions;
		}
		public void setStrSoftwareVersions(String strSoftwareVersions) {
			this.strSoftwareVersions = strSoftwareVersions;
		}
		public String getStrDeviceState() {
			return strDeviceState;
		}
		public void setStrDeviceState(String strDeviceState) {
			this.strDeviceState = strDeviceState;
		}
		public List<CloudChannelClass> getListCloudChannel() {
			return listCloudChannel;
		}
		public void setListCloudChannel(List<CloudChannelClass> listCloudChannel) {
			this.listCloudChannel = listCloudChannel;
		}
	    
}
