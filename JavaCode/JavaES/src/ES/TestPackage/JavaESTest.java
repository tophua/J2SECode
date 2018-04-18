package ES.TestPackage;

import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkProcessor.Listener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.script.Script;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class JavaESTest {
	
	private TransportClient client;
	private IndexRequest source;
	
	 private String clusterName="itoss_es_10_82_17_14";
	 private String port="9300";
	 
	/*
     * ��ȡ����, ��һ�ַ�ʽ
     * @throws Exception
     */
    @Before
    public void before() throws Exception {
        Map<String, String> map = new HashMap<String, String>();  
        map.put("cluster.name", "elasticsearch_wenbronk");  
        Settings.Builder settings = Settings.builder().put(map);  
        client = TransportClient.builder().settings(settings).build()  
                        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(clusterName), Integer.parseInt(port))); 
    }    
	    
	
    /*
     * �鿴��Ⱥ��Ϣ
     */
   @Test
    public void testInfo() {
        List<DiscoveryNode> nodes = client.connectedNodes();
        for (DiscoveryNode node : nodes) {
            System.out.println(node.getHostAddress());
        }
    }
    
    /**
     * ��֯json��, ��ʽ1,ֱ��ƴ��
     */
    public String createJson1() {
        String json = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
            "}";
        return json;
    }
    
    /**
     * ʹ��map����json
     */
    public Map<String, Object> createJson2() {
        Map<String,Object> json = new HashMap<String, Object>();
        json.put("user", "kimchy");
        json.put("postDate", new Date());
        json.put("message", "trying out elasticsearch");
        return json;
    }
    
    /**
     * ʹ��fastjson����
     */
    public JSONObject createJson3() {
        JSONObject json = new JSONObject();
        json.put("user", "kimchy");
        json.put("postDate", new Date());
        json.put("message", "trying out elasticsearch");
        return json;
    }
    
    /**
     * ʹ��es�İ�����
     */
    public XContentBuilder createJson4() throws Exception {
        // ����json����, ����һ������json�ķ�ʽ
        XContentBuilder source = XContentFactory.jsonBuilder().startObject().field("user", "kimchy").field("postDate", new Date())
                .field("message", "trying to out ElasticSearch")
            .endObject();
        return source;
    }
    
    /**
     * ����������
     * @throws Exception
     */
//   @Test
    public void test1() throws Exception {
        XContentBuilder source = createJson4();
        // ��json��������
        IndexResponse response = client.prepareIndex("twitter", "tweet", "1").setSource(source).get();
//        // �����ȡ
        String index = response.getIndex();
        String type = response.getType();
        String id = response.getId();
        long version = response.getVersion();
        boolean created = response.isCreated();
        System.out.println(index + " : " + type + ": " + id + ": " + version + ": " + created);
    }

    /**
     * get API ��ȡָ���ĵ���Ϣ
     */
 //   @Test
    public void testGet() {
//        GetResponse response = client.prepareGet("twitter", "tweet", "1")
//                                .get();
        GetResponse response = client.prepareGet("twitter", "tweet", "1")
                .setOperationThreaded(false)    // �̰߳�ȫ
                .get();
        System.out.println(response.getSourceAsString());
    }
    
    /**
     * ���� delete api
     */
 //   @Test
    public void testDelete() {
        DeleteResponse response = client.prepareDelete("twitter", "tweet", "1")
                .get();
        String index = response.getIndex();
        String type = response.getType();
        String id = response.getId();
        long version = response.getVersion();
        System.out.println(index + " : " + type + ": " + id + ": " + version);
    }
    
    /**
     * ���Ը��� update API
     * ʹ�� updateRequest ����
     * @throws Exception 
     */
 //   @Test
    public void testUpdate() throws Exception {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("twitter");
        updateRequest.type("tweet");
        updateRequest.id("1");
        updateRequest.doc(XContentFactory.jsonBuilder()
                .startObject()
                // ��û�е��ֶ����, �����е��ֶ��滻
                    .field("gender", "male")
                    .field("message", "hello")
                .endObject());
        UpdateResponse response = client.update(updateRequest).get();
        
        // ��ӡ
        String index = response.getIndex();
        String type = response.getType();
        String id = response.getId();
        long version = response.getVersion();
        System.out.println(index + " : " + type + ": " + id + ": " + version);
    }   
	
    /**
     * ����update api, ʹ��client
     * @throws Exception 
     */
 //   @Test
    public void testUpdate2() throws Exception {
        // ʹ��Script������и���
//        UpdateResponse response = client.prepareUpdate("twitter", "tweet", "1")
//                .setScript(new Script("hits._source.gender = \"male\""))
//                .get();
        
        // ʹ��XContFactory.jsonBuilder() ���и���
//        UpdateResponse response = client.prepareUpdate("twitter", "tweet", "1")
//                .setDoc(XContentFactory.jsonBuilder()
//                        .startObject()
//                            .field("gender", "malelelele")
//                        .endObject()).get();
        
        // ʹ��updateRequest����script
//        UpdateRequest updateRequest = new UpdateRequest("twitter", "tweet", "1")
//                .script(new Script("ctx._source.gender=\"male\""));
//        UpdateResponse response = client.update(updateRequest).get();
        
        // ʹ��updateRequest����documents���и���
        UpdateResponse response = client.update(new UpdateRequest("twitter", "tweet", "1")
                .doc(XContentFactory.jsonBuilder()
                        .startObject()
                            .field("gender", "male")
                        .endObject()
                    )).get();
        System.out.println(response.getIndex());
    }
    
    /**
     * ����update
     * ʹ��updateRequest
     * @throws Exception 
     * @throws InterruptedException 
     */
 //   @Test
    public void testUpdate3() throws InterruptedException, Exception {
        UpdateRequest updateRequest = new UpdateRequest("twitter", "tweet", "1")
            .script(new Script("ctx._source.gender=\"male\""));
        UpdateResponse response = client.update(updateRequest).get();
    }
    
    /**
     * ����upsert����
     * @throws Exception 
     * 
     */
 //   @Test
    public void testUpsert() throws Exception {
        // ���ò�ѯ����, ���Ҳ����������Ч
        IndexRequest indexRequest = new IndexRequest("twitter", "tweet", "2")
            .source(XContentFactory.jsonBuilder()
                .startObject()
                    .field("name", "214")
                    .field("gender", "gfrerq")
                .endObject());
        // ���ø���, ���ҵ��������������
        UpdateRequest upsert = new UpdateRequest("twitter", "tweet", "2")
            .doc(XContentFactory.jsonBuilder()
                    .startObject()
                        .field("user", "wenbronk")
                    .endObject())
            .upsert(indexRequest);
        
        client.update(upsert).get();
    }
    
    /**
     * ����multi get api
     * �Ӳ�ͬ��index, type, ��id�л�ȡ
     */
 //   @Test
    public void testMultiGet() {
        MultiGetResponse multiGetResponse = client.prepareMultiGet()
        .add("twitter", "tweet", "1")
        .add("twitter", "tweet", "2", "3", "4")
        .add("anothoer", "type", "foo")
        .get();
        
        for (MultiGetItemResponse itemResponse : multiGetResponse) {
            GetResponse response = itemResponse.getResponse();
            if (response.isExists()) {
                String sourceAsString = response.getSourceAsString();
                System.out.println(sourceAsString);
            }
        }
    }
    
    /**
     * bulk ����ִ��
     * һ�β�ѯ����update �� delete���document
     */
//    @Test
    public void testBulk() throws Exception {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        bulkRequest.add(client.prepareIndex("twitter", "tweet", "1")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                            .field("user", "kimchy")
                            .field("postDate", new Date())
                            .field("message", "trying out Elasticsearch")
                        .endObject()));
        bulkRequest.add(client.prepareIndex("twitter", "tweet", "2")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                            .field("user", "kimchy")
                            .field("postDate", new Date())
                            .field("message", "another post")
                        .endObject()));
        BulkResponse response = bulkRequest.get();
        System.out.println(response.getHeaders());
    }
    
    /**
     * ʹ��bulk processor
     * @throws Exception 
     */
 //   @Test
    public void testBulkProcessor() throws Exception {
        // ����BulkPorcessor����
        BulkProcessor bulkProcessor = BulkProcessor.builder(client, new Listener() {
            public void beforeBulk(long paramLong, BulkRequest paramBulkRequest) {
                // TODO Auto-generated method stub
            }
            
            // ִ�г���ʱִ��
            public void afterBulk(long paramLong, BulkRequest paramBulkRequest, Throwable paramThrowable) {
                // TODO Auto-generated method stub
            }
            
            public void afterBulk(long paramLong, BulkRequest paramBulkRequest, BulkResponse paramBulkResponse) {
                // TODO Auto-generated method stub
            }
        })
        // 1w������ִ��һ��bulk
        .setBulkActions(10000)
        // 1gb������ˢ��һ��bulk
        .setBulkSize(new ByteSizeValue(1, ByteSizeUnit.GB))
        // �̶�5s����ˢ��һ��
        .setFlushInterval(TimeValue.timeValueSeconds(5))
        // ������������, 0������, 1��������ִ��
        .setConcurrentRequests(1)
        // �����˱�, 100ms��ִ��, �������3��
        .setBackoffPolicy(
                BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
        .build();
        
        // ��ӵ�������
        bulkProcessor.add(new IndexRequest("twitter", "tweet", "1"));
        bulkProcessor.add(new DeleteRequest("twitter", "tweet", "2"));
        
        // �ر�
        bulkProcessor.awaitClose(10, TimeUnit.MINUTES);
        // ����
        bulkProcessor.close();
    }
    
    
}
