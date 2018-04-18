package Bao1;

public class testMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person person1 =new Person();    
		person1.setName("汪维友 ");
		person1.setEmail("你好啊。。。。");
		
		Person person2=person1.clone();  //这里是person2获得和person1一样的内容
		person2.setName("wang1");   //为person2进行修改内容
		
		person1.setEmail("您好");
		
//测试深拷贝
		CPerson Cperson1 =new CPerson(); 
		Cperson1.setName("w1");
		Cperson1.setEmail("dah。。。。");
		CPerson Cperson2=CloneUtils.clone(Cperson1);
		Cperson2.setName("w2");
		
        System.out.println(person1.getName() + "的邮件内容是：" + person1.getEmail());
        System.out.println(person2.getName() + "的邮件内容是：" + person2.getEmail());
 
 ///Java数组
        int[] array=new int[10];
        System.out.println("array父类:"+array.getClass().getSuperclass());
        System.out.println("array类名:"+array.getClass().getName());
	}

}
