/*
 * @author wwy
 * ����XML�ĵ������ӿ�
 */

/*  ��������
 * <?xml version="1.0" encoding="UTF-8"?>
<users>
  <user id="0">
    <name>Alexia</name>
    <age>23</age>
    <sex>Female</sex>
  </user>
  <user id="1">
    <name>Edward</name>
    <age>24</age>
    <sex>Male</sex>
  </user>
  <user id="2">
    <name>wjm</name>
    <age>23</age>
    <sex>Female</sex>
  </user>
  <user id="3">
    <name>wh</name>
    <age>24</age>
    <sex>Male</sex>
  </user>
</users>
 */
package Test_XML;

public interface XmlDocument {
	/*
	 * ����XML�ĵ�
	 * @param fileName �ļ�ȫ·������
	 */
	public void parserXml(String fileName);

}
