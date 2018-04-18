package Test_Stack;

import java.util.Enumeration;
import java.util.Stack;

public class CTestStack {
	
	 private static void printStack(Stack<Integer> stack ){
	        if (stack.empty())
	            System.out.println("��ջ�ǿյģ�û��Ԫ��");
	            else {
	                System.out.print("��ջ�е�Ԫ�أ�");
	                Enumeration items = stack.elements(); // �õ� stack �е�ö�ٶ��� 
	                while (items.hasMoreElements()) //��ʾö�٣�stack �� �е�����Ԫ��
	                    System.out.print(items.nextElement()+" ");
	            }
	        System.out.println(); //����
	    }
	 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Stack stack = new Stack(); // ������ջ���� 
	     System.out.println("11111, absdder, 29999.3 ����Ԫ����ջ"); 
	     stack.push(new Integer(11111)); //�� ջ�� ѹ������ 11111
	     printStack(stack);  //��ʾջ�е�����Ԫ��
	     stack.push("absdder"); //�� ջ�� ѹ��
	     printStack(stack);  //��ʾջ�е�����Ԫ��
	     stack.push(new Double(29999.3)); //�� ջ�� ѹ��
	     printStack(stack);  //��ʾջ�е�����Ԫ��

	     String s = new String("absdder");
	     System.out.println("Ԫ��absdder�ڶ�ջ��λ��"+stack.search(s));      
	     System.out.println("Ԫ��11111�ڶ�ջ��λ��"+stack.search(11111));

	     System.out.println("11111, absdder, 29999.3 ����Ԫ�س�ջ"); //���� ջ��Ԫ�� 
	     System.out.println("Ԫ��"+stack.pop()+"��ջ");
	     printStack(stack);  //��ʾջ�е�����Ԫ��
	     System.out.println("Ԫ��"+stack.pop()+"��ջ");
	     printStack(stack);  //��ʾջ�е�����Ԫ��
	     System.out.println("Ԫ��"+stack.pop()+"��ջ");
	     printStack(stack);  //��ʾջ�е�����Ԫ��
	     
	////��ջ������Vector����5�ֲ���
	     Stack<String> stack2=new Stack<String>(); ///������Stringѹ��
	     stack2.push("a");
	     stack2.push("b");
	     stack2.push("c");
	     System.out.println(stack2.peek());//c
	     System.out.println(stack2.search("a"));//3
	     while(!stack2.isEmpty()){
	         String strv=stack2.pop();
	         System.out.print(strv+" ");
	       }//c b a
	       System.out.println();
	       System.out.println(stack2.search("a"));//-1,��û�и�ֵʱ������-1
	     
	     
	}

}
