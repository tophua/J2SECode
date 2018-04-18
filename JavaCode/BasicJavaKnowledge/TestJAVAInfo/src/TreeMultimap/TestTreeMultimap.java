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
		mapTemp.put(12, "32");  //�洢�����{1=[32, we], 2=[23], 12=[32]}
		
	//����������,�����������
		Iterator iter=mapTemp.entries().iterator();
		while(iter.hasNext()){
		   Map.Entry<Integer,String> entry=(Map.Entry<Integer,String>) iter.next();
		   System.out.println(String.format("%d:%s", entry.getKey(),entry.getValue()));
		}
		
    //ʹ��keyֵ����,//key�ɵõ�ȫ����ֵ��multiSet��û���ظ���ֵ��keyset
	  //	
	Set<Integer> keys=	mapTemp.keySet();  //��ò��ظ���keyֵ
	for(int key:keys){
		String result = String.format("%d:", key);
		Set<String> values = mapTemp.get(key); //���key����Ӧ������valueֵ
		for(String value:values)
		{
		   result = result+" "+value;
		}
		System.out.println(result);
	}
	
	//���ڸ�������-----������
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
//	//����������,�����������
//	  Iterator iter1=mapChannel.entries().iterator();
//	  while(iter1.hasNext()){
//		 Map.Entry<String,CloudChannelClass> entry=(Map.Entry<String,CloudChannelClass>) iter1.next();
//		 System.out.println(String.format("%s:%s", entry.getKey(),entry.getValue()));
//	  }
	}

}
