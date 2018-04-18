package ES.createIndex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/*
 * ��������ӣ���̫��
 */

public class ESCreateIndex1 {
     private Client client;
     private IndicesAdminClient adminClient;
   
     private String clusterName="itoss_es_10_82_17_14";
     private String hosts="10.82.17.13:9300";
     /** 
      * ���췽�� 
      */  
     public ESCreateIndex1() {  
         try {  
             init();  
         } catch (Exception e) {  
             System.out.println("init() exception!");  
             e.printStackTrace();  
         }  
         adminClient = client.admin().indices();  
     }  
     
     /*
      * ��Ⱥ���ó�ʼ������
      */
     private void init() throws Exception{
   // 	 Properties properties = readElasticsearchConfig();
   // 	 String clusterName = properties.getProperty("clusterName");  
  //     String[] inetAddresses = properties.getProperty("hosts").split(",");  
    	 String[] inetAddresses = "10.82.17.13:9300".split(",");  
         Settings settings = Settings.settingsBuilder().put("cluster.name", clusterName)  
                 .build();  
         client = TransportClient.builder().settings(settings)  
                 .build();  
         for (int i = 0; i < inetAddresses.length; i++) {  
             String[] tmpArray = inetAddresses[i].split(":");  
             String ip = tmpArray[0];  
             int port = Integer.valueOf(tmpArray[1]);  
             client = ((TransportClient)client).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ip), port));  
         }    	 
     }
     
     /* 
      * ��ȡes������Ϣ 
      * @return 
      */  
     private Properties readElasticsearchConfig() {  
         Properties properties = new Properties();  
         try {  
             InputStream is = this.getClass().getClassLoader().getResourceAsStream("configelasticsearch.properties");  
             properties.load(new InputStreamReader(is,"UTF-8"));  
         } catch (IOException e) {  
             System.out.println("readEsConfig exception!");  
             e.printStackTrace();  
         }  
         return properties;  
     }  
     
     /*
      * ��ȡjson�����ļ� 
      * @return JSONArray 
      * @throws IOException 
      */  
     public JSONArray readJosnFile() throws IOException{  
         InputStream is = this.getClass().getClassLoader().getResourceAsStream("index.json");  
         BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));  
         StringBuffer sb = new StringBuffer();  
         String tmp = null;  
         while((tmp=br.readLine()) != null){  
             sb.append(tmp);  
         }  
         JSONArray result = JSONArray.parseArray(sb.toString());  
         return result;  
     }  
    
     
     /* 
      * �жϼ�Ⱥ��{index}�Ƿ���� 
      * @param index 
      * @return ���ڣ�true���������ڣ�false�� 
      */  
     public boolean indexExists(String index){  
         IndicesExistsRequest request = new IndicesExistsRequest(index);  
         IndicesExistsResponse response = adminClient.exists(request).actionGet();  
         if (response.isExists()) {  
             return true;  
         }  
         return false;  
     }  
    
     /** 
      * ��ȡҪ������index�б� 
      * @param client 
      * @return List<Index> 
      */  
     public List<Index1> getIndexList(){  
           
         List<Index1> result = new ArrayList<Index1>();  
//         JSONArray jsonArray = null;  
//         try {  
//             jsonArray = readJosnFile();  
//         } catch (IOException e) {  
//             System.out.println("readJsonFile exception!");  
//             e.printStackTrace();  
//         }  
//         if (jsonArray == null || jsonArray.size() == 0) {  
//             return null;  
//         }  
//         for (int i = 0; i < jsonArray.size(); i++) {  
//             JSONObject jsonObject = jsonArray.getJSONObject(i);  
//             Index1 indexObject = new Index1();  
//             String index = jsonObject.getString("index");  
//             String type = jsonObject.getString("type");  
//             Integer number_of_shards = jsonObject.getInteger("number_of_shards");  
//             Integer number_of_replicas = jsonObject.getInteger("number_of_replicas");  
//             String fieldRType = jsonObject.get("fieldType").toString();  
//             indexObject.setIndex(index);  
//             indexObject.setType(type);  
//             indexObject.setFieldJson(fieldRType);  
//             indexObject.setNumber_of_shards(number_of_shards);  
//             indexObject.setNumber_of_replicas(number_of_replicas);  
//             result.add(indexObject);  
//         }  
         Index1 indexObject = new Index1();  
         indexObject.setIndex("indexTest1");
         indexObject.setNumber_of_replicas(2);  //����Ƭ
         indexObject.setNumber_of_shards(3);    //����Ƭ
         indexObject.setType("initType");
      //   String fieldRType="{"initType":{"properties":{"date":{"format":"yyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis","type":"date"},"ip":{"type":"ip"}}}}";
         String fieldRType="{initType:{properties:{date:{format:yyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis,type:date},ip:{type:ip}}}}".toString();
         indexObject.setFieldJson(fieldRType);
         result.add(indexObject);
         return result;  
     }  
     /** 
      * ����Index 
      * @param client 
      */  
     public void CreateIndex(){  
         int i = 0;  
         List<Index1> list = getIndexList();  
         IndicesAdminClient adminClient = client.admin().indices();  
         for(Index1 index : list){  
             if (indexExists(index.getIndex())) {  
                 continue;  
             }  
             adminClient.prepareCreate(index.getIndex())  
             .setSettings(Settings.builder().put("index.number_of_shards", index.getNumber_of_shards()).put("index.number_of_replicas", index.getNumber_of_replicas()))  
             .addMapping(index.getType(), index.getFieldJson())  
             .get();  
             i++;  
         }  
         System.out.println("index creation success! create "+i+" index");  
           
     }   
}
