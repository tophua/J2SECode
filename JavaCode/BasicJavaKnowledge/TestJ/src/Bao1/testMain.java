package Bao1;

public class testMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person person1 =new Person();    
		person1.setName("��ά�� ");
		person1.setEmail("��ð���������");
		
		Person person2=person1.clone();  //������person2��ú�person1һ��������
		person2.setName("wang1");   //Ϊperson2�����޸�����
		
		person1.setEmail("����");
		
//�������
		CPerson Cperson1 =new CPerson(); 
		Cperson1.setName("w1");
		Cperson1.setEmail("dah��������");
		CPerson Cperson2=CloneUtils.clone(Cperson1);
		Cperson2.setName("w2");
		
        System.out.println(person1.getName() + "���ʼ������ǣ�" + person1.getEmail());
        System.out.println(person2.getName() + "���ʼ������ǣ�" + person2.getEmail());
 
 ///Java����
        int[] array=new int[10];
        System.out.println("array����:"+array.getClass().getSuperclass());
        System.out.println("array����:"+array.getClass().getName());
	}

}
