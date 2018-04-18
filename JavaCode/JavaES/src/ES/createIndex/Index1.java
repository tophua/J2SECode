package ES.createIndex;

public class Index1 {
  public String index; //索引名
  public String type;  //类型名
  private Integer number_of_shards;   //分片数
  private Integer number_of_replicas;  //备份数
  private String fieldJson;   //字段类型 
public String getIndex() {
	return index;
}
public void setIndex(String index) {
	this.index = index;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public Integer getNumber_of_shards() {
	return number_of_shards;
}
public void setNumber_of_shards(Integer number_of_shards) {
	this.number_of_shards = number_of_shards;
}
public Integer getNumber_of_replicas() {
	return number_of_replicas;
}
public void setNumber_of_replicas(Integer number_of_replicas) {
	this.number_of_replicas = number_of_replicas;
}
public String getFieldJson() {
	return fieldJson;
}
public void setFieldJson(String fieldJson) {
	this.fieldJson = fieldJson;
}
  
}
