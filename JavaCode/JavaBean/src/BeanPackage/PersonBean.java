package BeanPackage;

public class PersonBean {
	 //------------------Person���װ��˽������---------------------------------------
    // ���� String����
    private String name;
    // �Ա� String����
    private String sex;
    // ���� int����
    private int age;
    //�Ƿ��ѻ� boolean����
    private boolean married;
    //---------------------------------------------------------
    
    //------------------Person����޲������췽��---------------------------------------
    /**
     * �޲������췽��
     */
    public PersonBean() {
        
    }
//---------------------------------------------------------
    //------------------Person������ṩ�����ڷ���˽�����Ե�public����---------------------------------------
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}
    
  
    
    
    
    
}
