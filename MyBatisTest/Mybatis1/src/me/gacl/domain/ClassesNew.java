package me.gacl.domain;

import java.util.List;

public class ClassesNew {
	 //����ʵ��������ԣ���class���е��ֶζ�Ӧ
    private int id;            //id===>c_id
    private String name;    //name===>c_name
    
    /**
     * class������һ��teacher_id�ֶΣ�������Classes���ж���һ��teacher���ԣ�
     * ����ά��teacher��class֮���һ��һ��ϵ��ͨ�����teacher���ԾͿ���֪������༶�����ĸ���ʦ�����
     */
    private Teacher teacher;
    //ʹ��һ��List<Student>�������Ա�ʾ�༶ӵ�е�ѧ��
    private List<Student> students;
    

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


	public Teacher getTeacher() {
		return teacher;
	}


	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}


	public List<Student> getStudents() {
		return students;
	}


	public void setStudents(List<Student> students) {
		this.students = students;
	}


	@Override
	public String toString() {
	   return "Classes [id=" + id + ", name=" + name + ", teacher=" + teacher+ "]";
	} 
}
