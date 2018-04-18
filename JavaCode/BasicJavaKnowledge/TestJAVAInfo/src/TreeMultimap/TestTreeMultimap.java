package TreeMultimap;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.TreeMultimap;

public class TestTreeMultimap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TreeMultimap<Integer,String> mapTemp=TreeMultimap.create();
		mapTemp.put(2, "23");
		mapTemp.put(1,"32");
		mapTemp.put(1, "we");
		mapTemp.put(2,"23");
		mapTemp.put(12, "32");  //存储结果是{1=[32, we], 2=[23], 12=[32]}
		
	//遍历器遍历,依次整体遍历
		Iterator iter=mapTemp.entries().iterator();
		while(iter.hasNext()){
		   Map.Entry<Integer,String> entry=(Map.Entry<Integer,String>) iter.next();
		   System.out.println(String.format("%d:%s", entry.getKey(),entry.getValue()));
		}
		
    //使用key值遍历,//key可得到全部键值的multiSet或没有重复键值的keyset
	  //	
	Set<Integer> keys=	mapTemp.keySet();  //获得不重复的key值
	for(int key:keys){
		String result = String.format("%d:", key);
		Set<String> values = mapTemp.get(key); //获得key所对应的所有value值
		for(String value:values)
		{
		   result = result+" "+value;
		}
		System.out.println(result);
	}
	
	//对于复合类型-----不可行
//	  TreeMultimap<Comparable, Comparable> mapChannel=TreeMultimap.create();
//	  CloudChannelClass ChannelInfo=new CloudChannelClass();
//	  ChannelInfo.setStrChannelID("321");
//	  ChannelInfo.setStrChannelIP("10.82.17.15");
//	  ChannelInfo.setStrChannelState("1");
//	  ChannelInfo.setStrParentMAC("fa-23-45");	
//	  
//	  mapChannel.put((Comparable)"fa-23-45", (Comparable) ChannelInfo);
//	  mapChannel.put((Comparable)"fa-23-45", (Comparable) ChannelInfo);
//	  mapChannel.put((Comparable)"ga-23-46", (Comparable) ChannelInfo);
//	  mapChannel.put((Comparable)"ga-ba-c5", (Comparable) ChannelInfo);
//	//遍历器遍历,依次整体遍历
//	  Iterator iter1=mapChannel.entries().iterator();
//	  while(iter1.hasNext()){
//		 Map.Entry<String,CloudChannelClass> entry=(Map.Entry<String,CloudChannelClass>) iter1.next();
//		 System.out.println(String.format("%s:%s", entry.getKey(),entry.getValue()));
//	  }
	}

}
