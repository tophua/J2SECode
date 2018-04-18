package Bao1;

public class Person implements Cloneable {
	 /** ���� **/
    private String name;
    
    /** �����ʼ� **/
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
	            person = (Person) super.clone();  //��Ϊ�̳нӿڣ�����Ҫsuper���ظ���ĺ���
	        } catch (CloneNotSupportedException e) {
	            e.printStackTrace();
	        }
	        
	        return person;
	    }
    
}
