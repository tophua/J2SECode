package MyJDBC;

import java.sql.ResultSet;

/**
* @ClassName: ResultSetHandler
* @Description:结果集处理器接口
* @date: 2014-10-5 下午12:01:27
* 这个接口，其他不同类去实现，这样可以new不同的子类进行
*/ 

public interface ResultSetHandler {
	  public Object handler(ResultSet rs);
}
