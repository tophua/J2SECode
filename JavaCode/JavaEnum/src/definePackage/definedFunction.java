/**
 * 
 */
package definePackage;

/**
 * @author wangwy
 *
 */
public enum definedFunction {

	START("START","����",1),
	STOP("STOP","ֹͣ",1);
	
	private String type;
	private String des;
	private int level;
	
	private definedFunction(String type,String des,int level){
		this.type = type;
		this.des = des;
		this.level = level;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
}
