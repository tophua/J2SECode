package me.gacl.domain;

public class User {
	 //实体类的属性和表的字段名称一一对应
    private int id;
    private String name;
    private int age;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	//这个会表明User输出是对象的格式
	 @Override
	    public String toString() {
	        return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
	    }

}
