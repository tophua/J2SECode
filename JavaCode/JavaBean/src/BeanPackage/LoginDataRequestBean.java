package BeanPackage;

public class LoginDataRequestBean {
	 /**
     * Éú³ÉµÄserialVersionUID
     */ 
    private static final long serialVersionUID = 5231134212346077681L; 
   
    private String uname; 
    private String upwd; 
    private String extrData;
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public String getExtrData() {
		return extrData;
	}
	public void setExtrData(String extrData) {
		this.extrData = extrData;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	} 
    
    
}
