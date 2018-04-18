package Bao1;

import java.io.Serializable;

public class CPerson implements Serializable{
	private static final long serialVersionUID = 2631590509760908280L;
	 /** 姓名 **/
    private String name;
    
    /** 电子邮件 **/
    private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;   //这种方法其实就是给该类的变量赋值
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
