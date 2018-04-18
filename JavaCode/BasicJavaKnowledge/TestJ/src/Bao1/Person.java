package Bao1;

public class Person implements Cloneable {
	 /** 姓名 **/
    private String name;
    
    /** 电子邮件 **/
    private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	 protected Person clone() {
	        Person person = null;
	        try {
	            person = (Person) super.clone();  //因为继承接口，则需要super加载父类的函数
	        } catch (CloneNotSupportedException e) {
	            e.printStackTrace();
	        }
	        
	        return person;
	    }
    
}
