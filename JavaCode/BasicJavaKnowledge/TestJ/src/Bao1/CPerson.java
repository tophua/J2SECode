package Bao1;

import java.io.Serializable;

public class CPerson implements Serializable{
	private static final long serialVersionUID = 2631590509760908280L;
	 /** ���� **/
    private String name;
    
    /** �����ʼ� **/
    private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;   //���ַ�����ʵ���Ǹ�����ı�����ֵ
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
