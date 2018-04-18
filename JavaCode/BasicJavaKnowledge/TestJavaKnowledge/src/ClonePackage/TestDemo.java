package ClonePackage;

public class TestDemo {

	public static void main(String[] args) throws CloneNotSupportedException {
		// TODO Auto-generated method stub

		BasicClass ba1=new BasicClass("original data");
		StringBuffer strBuf=new StringBuffer("origin string buf");
		
		EasyClone org=new EasyClone(ba1,1.0,"old",strBuf);
		EasyClone copy=null;
		Object obj1=org.clone(); //ǳ�������������������ֶ�(BasicClass��StringBuffer)ֻ�ǽ����ø����¶��󣬵�ֵ���䣬����ָ��ͬһ��
		if(obj1 instanceof EasyClone){ //ȷ��obj1�Ƿ���EasyClone���һ��ʵ��
			copy =(EasyClone) obj1;
		}
		
		System.out.println("copy == org? "+(copy==org));//����ֵ��false,��Ȼcopy��org�ǲ�ͬ����ʵ�������ֶε�ֵ����ͬ
		                                                //BasicClass��StringBuffer�����������ֶΣ�double��String�ǻ������������ֶ�
		System.out.println("data of org: ");
		org.show();
		System.out.println();
		System.out.println("data of copy: ");
		copy.show();
		System.out.println();
		System.out.println("org.data1==copy.data1? "+(org.data1==copy.data1)); //�����ĸ���ͬ��ǳ����
		System.out.println("org.data2==copy.data2? "+(org.data2==copy.data2));
		System.out.println("org.data2==copy.data2? "+(org.data3==copy.data3));
		System.out.println("org.data2==copy.data2? "+(org.data4==copy.data4));
		
		///ǳ���������ĺ��,�޸Ŀ�����copy��ֵ
		  //�ӽ�����֣����������ֶε�ֵ�ı�ʱ����Ӧ��ԭ�����ֵҲ��Ӧ�仯
		copy.data1.data="copy New data";
		copy.data2=2.0;
		copy.data3="New str";
		copy.data4.replace(0, copy.data4.length(), "New strBuf");
		
		System.out.println("New data of org: ");
		org.show();
		System.out.println();
		System.out.println("New data of copy: ");
		copy.show();
		
///////�������
		System.out.println("-------------���--------------");
		BasicClass ba2=new BasicClass("original data2");
		StringBuffer strBuf2=new StringBuffer("origin string buf2");
		
		DeepClone org2=new DeepClone(ba2,4.0,"old2",strBuf2);
		DeepClone copy2=null;
		Object obj2=org2.clone(); 
		if(obj2 instanceof DeepClone){ //ȷ��obj1�Ƿ���EasyClone���һ��ʵ��
			copy2 =(DeepClone) obj2;
		}
		System.out.println("data of org2: ");
		org2.show();
		System.out.println();
		System.out.println("data of copy2: ");
		copy2.show();
		
		System.out.println();
		System.out.println("org2.data1==copy2.data1? "+(org2.data1==copy2.data1)); 
		System.out.println("org2.data2==copy2.data2? "+(org2.data2==copy2.data2));
		System.out.println("org2.data2==copy2.data2? "+(org2.data3==copy2.data3));
		System.out.println("org2.data2==copy2.data2? "+(org2.data4==copy2.data4));
		//�޸Ŀ�����copy2��ֵ
		copy2.data1.data="copy2 New data";
		copy2.data2=3.0;
		copy2.data3="New str2";
		copy2.data4.replace(0, copy.data4.length(), "New strBuf2");
		
		System.out.println("New data of org2: ");
		org2.show();
		System.out.println();
		System.out.println("New data of copy2: ");
		copy2.show();
	}

}
