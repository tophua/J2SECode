package Test_JSON_lib;

public class FrontCloudInfo {
	public String state;
	public CloudConfigInfo[] cloud;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public CloudConfigInfo[] getCloud() {
		return cloud;
	}
	public void setCloud(CloudConfigInfo[] cloud) {
		this.cloud = cloud;
	}
}
