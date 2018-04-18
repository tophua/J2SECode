package ES.TestPackage;

import java.net.InetSocketAddress;

import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.settings.Settings.Builder;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.sort.SortParseElement;
import org.junit.Before;
import org.junit.Test;

public class JavaESTest1 {

	  private TransportClient client;

	    /**
	     * ��ȡclient����
	     */
	   @Before
	    public void testBefore() {
	        Builder builder = Settings.settingsBuilder();
	        builder.put("cluster.name", "itoss_es_10_82_17_14");
//	                .put("client.transport.ignore_cluster_name", true);
	        Settings settings = builder.build();
	        
	        org.elasticsearch.client.transport.TransportClient.Builder transportBuild = TransportClient.builder();
	        TransportClient client1 = transportBuild.settings(settings).build();
	        client = client1.addTransportAddress((new InetSocketTransportAddress(new InetSocketAddress("10.82.17.13", 9300))));
	        System.out.println("success connect to escluster");
	        
	    }
	    
	    /**
	     * ���Բ�ѯ
	     */
	    @Test
	    public void testSearch() {
//	        SearchRequestBuilder searchRequestBuilder = client.prepareSearch("twitter", "tweet", "1");
//	        SearchResponse response = searchRequestBuilder.setTypes("type1", "type2")
//	                            .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//	                            .setQuery(QueryBuilders.termQuery("user", "test"))
//	                            .setPostFilter(QueryBuilders.rangeQuery("age").from(0).to(1))
//	                            .setFrom(0).setSize(2).setExplain(true)
//	                            .execute().actionGet();
	        SearchResponse response = client.prepareSearch()
	                .execute().actionGet();
//	        SearchHits hits = response.getHits();
//	        for (SearchHit searchHit : hits) {
//	            for(Iterator<SearchHitField> iterator = searchHit.iterator(); iterator.hasNext(); ) {
//	                SearchHitField next = iterator.next();
//	                System.out.println(next.getValues());
//	            }
//	        }
	        System.out.println(response);
	    }
	    
	    /**
	     * ����scroll api
	     * �Դ������ݵĴ������Ч
	     */
	    @Test
	    public void testScrolls() {
	        QueryBuilder queryBuilder = QueryBuilders.termQuery("twitter", "tweet");
	        
	        SearchResponse response = client.prepareSearch("twitter")
	        .addSort(SortParseElement.DOC_FIELD_NAME, SortOrder.ASC)
	        .setScroll(new TimeValue(60000))
	        .setQuery(queryBuilder)
	        .setSize(100).execute().actionGet();
	        
	        while(true) {
	            for (SearchHit hit : response.getHits().getHits()) {
	                System.out.println("i am coming");
	            }
	            SearchResponse response2 = client.prepareSearchScroll(response.getScrollId())
	                .setScroll(new TimeValue(60000)).execute().actionGet();
	            if (response2.getHits().getHits().length == 0) {
	                System.out.println("oh no=====");
	                break;
	            }
	        }
	        
	    }
	    
	    /**
	     * ����multiSearch
	     */
	    @Test
	    public void testMultiSearch() {
	        QueryBuilder qb1 = QueryBuilders.queryStringQuery("elasticsearch");
	        SearchRequestBuilder requestBuilder1 = client.prepareSearch().setQuery(qb1).setSize(1);
	        
	        QueryBuilder qb2 = QueryBuilders.matchQuery("user", "kimchy");
	        SearchRequestBuilder requestBuilder2 = client.prepareSearch().setQuery(qb2).setSize(1);
	        
	        MultiSearchResponse multiResponse = client.prepareMultiSearch().add(requestBuilder1).add(requestBuilder2)
	                .execute().actionGet();
	        long nbHits = 0;
	        for (MultiSearchResponse.Item item : multiResponse.getResponses()) {
	            SearchResponse response = item.getResponse();
	            nbHits = response.getHits().getTotalHits();
	            SearchHit[] hits = response.getHits().getHits();
	            System.out.println(nbHits);
	        }
	        
	    }
	    
	    /**
	     * ���Ծۺϲ�ѯ
	     */
	    @Test
	    public void testAggregation() {
	        SearchResponse response = client.prepareSearch()
	                .setQuery(QueryBuilders.matchAllQuery()) // ��ʹ��query���˵�һ����
	                .addAggregation(AggregationBuilders.terms("term").field("user"))
	                .addAggregation(AggregationBuilders.dateHistogram("agg2").field("birth")
	                    .interval(DateHistogramInterval.YEAR))
	                .execute().actionGet();
	        Aggregation aggregation2 = response.getAggregations().get("term");
	        Aggregation aggregation = response.getAggregations().get("agg2");
//	        SearchResponse response2 = client.search(new SearchRequest().searchType(SearchType.QUERY_AND_FETCH)).actionGet();
	    }
	    
	    /**
	     * ����terminate
	     */
	    @Test
	    public void testTerminateAfter() {
	        SearchResponse response = client.prepareSearch("twitter").setTerminateAfter(1000).get();
	        if (response.isTerminatedEarly()) {
	            System.out.println("ternimate");
	        }
	    }
	    
	    /**
	     * ���˲�ѯ: ����gt, С��lt, С�ڵ���lte, ���ڵ���gte
	     */
	    @Test
	    public void testFilter() {
	        SearchResponse response = client.prepareSearch("twitter")  
	                .setTypes("")  
	                .setQuery(QueryBuilders.matchAllQuery()) //��ѯ����  
	                .setSearchType(SearchType.QUERY_THEN_FETCH)  
//	              .setPostFilter(FilterBuilders.rangeFilter("age").from(0).to(19)  
//	                      .includeLower(true).includeUpper(true))  
//	                .setPostFilter(FilterBuilderFactory .rangeFilter("age").gte(18).lte(22))  
	                .setExplain(true) //explainΪtrue��ʾ����������ض����򣬺͹ؼ���ƥ����ߵ�����ǰ��  
	                .get();  
	    }
	    
	    /**
	     * �����ѯ
	     */
	    @Test
	    public void testGroupBy() {
	        client.prepareSearch("twitter").setTypes("tweet")
	        .setQuery(QueryBuilders.matchAllQuery())
	        .setSearchType(SearchType.QUERY_THEN_FETCH)
	        .addAggregation(AggregationBuilders.terms("user")
	                .field("user").size(0)        // ����user���з���
	                                            // size(0) Ҳ��10
	        ).get();
	    }
}
