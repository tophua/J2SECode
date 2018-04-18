package Test_Map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;

import TreeMultimap.CloudChannelClass;

public class MultimapClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Multimap<String, String> mapTest= ArrayListMultimap.create();
		
	// ��ʼ�����
		mapTest.put("Fruits", "Bannana");
		mapTest.put("Fruits", "Apple");
		mapTest.put("Fruits", "Pear");
		mapTest.put("Fruits", "Pear");
		mapTest.put("Vegetables", "Carrot");

	// Getting the size
		int size = mapTest.size();
		System.out.println(size); // 5

	// Getting values
		Collection<String> fruits = mapTest.get("Fruits");
		System.out.println(fruits); //  [Bannana, Apple, Pear, Pear]
		for(String str:fruits){  //�ڶ�keyֵһһ����
			System.out.println(str);
		}
		
		//��ò��ظ���ֵ
		System.out.println(ImmutableSet.copyOf(fruits));// [Bannana, Apple, Pear],
		// Set<Foo> set = Sets.newHashSet(list);
		// Set<Foo> foo = new HashSet<Foo>(myList);

		Collection<String> vegetables = mapTest.get("Vegetables");
		System.out.println(vegetables); // [Carrot]

	// ѭ��������ȡ���е�
		for (String value : mapTest.values()) {
			System.out.println(value);
		}

	// Removing a single value
		mapTest.remove("Fruits", "Pear"); //ֻɾ��һ��
		System.out.println(mapTest.get("Fruits")); // [Bannana, Apple, Pear]

	// Remove all values for a key  //ɾ������
		mapTest.removeAll("Fruits");
		System.out.println(mapTest.get("Fruits")); // [] (Empty Collection!)
 
  //��������		
		Multimap<String, CloudChannelClass> mapChannel= ArrayListMultimap.create();
	//	TreeMultimap<Comparable, Comparable> mapChannel = TreeMultimap.create();
		CloudChannelClass ChannelInfo = new CloudChannelClass();
		ChannelInfo.setStrChannelID("321");
		ChannelInfo.setStrChannelIP("10.82.17.15");
		ChannelInfo.setStrChannelState("1");
		ChannelInfo.setStrParentMAC("fa-23-45");		
		mapChannel.put("fa-23-45",ChannelInfo);
		mapChannel.put("fa-23-45",ChannelInfo);
		mapChannel.put("ga-23-46",ChannelInfo);
		mapChannel.put("ga-ba-c5",ChannelInfo);
		
		//����������,�����������
		Iterator iter1=mapChannel.entries().iterator();
		while(iter1.hasNext()){
		    Map.Entry<String,CloudChannelClass> entry=(Map.Entry<String,CloudChannelClass>) iter1.next();
		    System.out.println(String.format("Key: %s", entry.getKey()));
		//   System.out.println(String.format("%s:%s", entry.getKey(),entry.getValue()));
		    Collection<CloudChannelClass> ChanValue = mapChannel.get(entry.getKey());
		    
			for(CloudChannelClass strChan:ChanValue){  //�ڶ�keyֵһһ����
				System.out.println(strChan.getStrChannelID());
				System.out.println(strChan.getStrChannelIP());
				System.out.println(strChan.getStrChannelState());
				System.out.println(strChan.getStrParentMAC());
			}					
		}
		
	 //�����������map
		Map<String,CloudClass> mapCloud=new HashMap<String,CloudClass>();
		CloudClass cloud1=new CloudClass();
		cloud1.setStrDeviceIP("10.82.17.14");
		cloud1.setStrDeviceName("yun1");
		mapCloud.put("fa-23-45",cloud1);
		
		Iterator iter2 = mapCloud.entrySet().iterator(); //Ч�ʸߵĵ���
		while(iter2.hasNext()){
			Map.Entry entry = (Map.Entry) iter2.next();
			/*
		     Object key = entry.getKey();
	         Object val = entry.getValue(); //���ַ�����Object�Ϳ��Ի�ö�Ӧ��
	         */
			String keyParentMac = (String)entry.getKey();
			
			List<CloudChannelClass> listChan=new ArrayList<CloudChannelClass>();
			Collection<CloudChannelClass> ChanValues=mapChannel.get(keyParentMac);
		    for(CloudChannelClass OneChan:ChanValues){
		    	listChan.add(OneChan);
		    }
		    ((CloudClass) entry.getValue()).setListCloudChannel(listChan);
		}
		
  //��α���ɾ�����޸ĵ�
		Iterator iter3 = mapCloud.entrySet().iterator(); //Ч�ʸߵĵ���
		while(iter3.hasNext()){
			Map.Entry entry = (Map.Entry) iter3.next();
			CloudClass CloudTemp=(CloudClass)entry.getValue();
			List<CloudChannelClass> listCloudChan=CloudTemp.getListCloudChannel();
			for(CloudChannelClass Chan:listCloudChan){
				System.out.println(Chan.getStrChannelIP());
			}		
		}
		
	//�����������ͽ��бȽ�
		Map<String,CloudClass> mapTest1=new HashMap<String,CloudClass>();
		CloudClass cloudTest1=new CloudClass();
		cloudTest1.setStrDeviceIP("10.82.17.1");
		cloudTest1.setStrDeviceName("yun1");
		List<CloudChannelClass> listChan1=new ArrayList<CloudChannelClass>();
		CloudChannelClass ChanTest1 = new CloudChannelClass();
		ChanTest1.setStrChannelID("321");
		ChanTest1.setStrChannelIP("10.82.17.15");
		ChanTest1.setStrChannelState("1");
		ChanTest1.setStrParentMAC("fa-23-45");	
		listChan1.add(ChanTest1);
		cloudTest1.setListCloudChannel(listChan1);
		mapTest1.put("1", cloudTest1);
		
		Map<String,CloudClass> mapTest2=new HashMap<String,CloudClass>();
		CloudClass cloudTest2=new CloudClass();
		cloudTest2.setStrDeviceIP("10.82.17.1");
		cloudTest2.setStrDeviceName("yun1");
		List<CloudChannelClass> listChan2=new ArrayList<CloudChannelClass>();
		CloudChannelClass ChanTest2 = new CloudChannelClass();
		ChanTest2.setStrChannelID("321");
		ChanTest2.setStrChannelIP("10.82.17.15");
		ChanTest2.setStrChannelState("1");
		ChanTest2.setStrParentMAC("fa-23-45");	
		listChan2.add(ChanTest2);
		cloudTest2.setListCloudChannel(listChan2);
		mapTest2.put("1", cloudTest2);
		
		if(cloudTest1.equals(cloudTest2)){
			System.out.println("ֵ��ͬ");
		}
	}
	
	

}
