package BeanPackage;

public class PersonBean {
	 //------------------Person类封装的私有属性---------------------------------------
    // 姓名 String类型
    private String name;
    // 性别 String类型
    private String sex;
    // 年龄 int类型
    private int age;
    //是否已婚 boolean类型
    private boolean married;
    //---------------------------------------------------------
    
    //------------------Person类的无参数构造方法---------------------------------------
    /**
     * 无参数构造方法
     */
    public PersonBean() {
        
    }
//---------------------------------------------------------
    //------------------Person类对外提供的用于访问私有属性的public方法---------------------------------------
    
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
