package SingletonPackage;

import java.util.ArrayList;
import java.util.List;

public class StaticInitTest {

	private static List<Integer> dataList = null;
	//static������ֻ֤��ʼ��һ��
	static{
		dataList = Singleton.INSTANCE.init();
	}
	
	//����ö�ٷ����ĵ���ģʽ�������
	private static enum Singleton {
		INSTANCE;
		private List<Integer> list;
		private Singleton(){
			fillData();
		}
		
		//��ʼ������
		private void fillData(){
			list = new ArrayList<Integer>(5);
			for(int i=1;i<6;i++){
				list.add(i);
			}
		}
		//��ʼ�������
		public List<Integer> init(){
			return list;
		}
	}
	
}
