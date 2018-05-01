package SingletonPackage;

import java.util.ArrayList;
import java.util.List;

public class StaticInitTest {

	private static List<Integer> dataList = null;
	//static方法保证只初始化一次
	static{
		dataList = Singleton.INSTANCE.init();
	}
	
	//利用枚举方法的单例模式填充数据
	private static enum Singleton {
		INSTANCE;
		private List<Integer> list;
		private Singleton(){
			fillData();
		}
		
		//初始化数据
		private void fillData(){
			list = new ArrayList<Integer>(5);
			for(int i=1;i<6;i++){
				list.add(i);
			}
		}
		//初始化的入口
		public List<Integer> init(){
			return list;
		}
	}
	
}
