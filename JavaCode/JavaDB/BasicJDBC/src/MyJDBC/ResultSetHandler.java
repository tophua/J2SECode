package MyJDBC;

import java.sql.ResultSet;

/**
* @ClassName: ResultSetHandler
* @Description:������������ӿ�
* @date: 2014-10-5 ����12:01:27
* ����ӿڣ�������ͬ��ȥʵ�֣���������new��ͬ���������
*/ 

public interface ResultSetHandler {
	  public Object handler(ResultSet rs);
}