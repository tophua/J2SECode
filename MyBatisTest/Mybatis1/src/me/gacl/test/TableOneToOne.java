package me.gacl.test;

import org.apache.ibatis.session.SqlSession;

import me.gacl.domain.Classes;
import me.gacl.util.MyBatisUtil;

public class TableOneToOne {

	    public void testGetClass(){
	        SqlSession sqlSession = MyBatisUtil.getSqlSession();
	        /**
	         * ӳ��sql�ı�ʶ�ַ�����
	         * me.gacl.mapping.classMapper��classMapper.xml�ļ���mapper��ǩ��namespace���Ե�ֵ��
	         * getClass��select��ǩ��id����ֵ��ͨ��select��ǩ��id����ֵ�Ϳ����ҵ�Ҫִ�е�SQL
	         */
	        String statement = "me.gacl.mapping.classMapper.getClass";//ӳ��sql�ı�ʶ�ַ���
	        //ִ�в�ѯ����������ѯ����Զ���װ��Classes���󷵻�
	        Classes clazz = sqlSession.selectOne(statement,1);//��ѯclass����idΪ1�ļ�¼
	        //ʹ��SqlSessionִ����SQL֮����Ҫ�ر�SqlSession
	        sqlSession.close();
	        System.out.println(clazz);//��ӡ�����Classes [id=1, name=class_a, teacher=Teacher [id=1, name=teacher1]]
	    }
	    
	    public void testGetClass2(){
	        SqlSession sqlSession = MyBatisUtil.getSqlSession();
	        /**
	         * ӳ��sql�ı�ʶ�ַ�����
	         * me.gacl.mapping.classMapper��classMapper.xml�ļ���mapper��ǩ��namespace���Ե�ֵ��
	         * getClass2��select��ǩ��id����ֵ��ͨ��select��ǩ��id����ֵ�Ϳ����ҵ�Ҫִ�е�SQL
	         */
	        String statement = "me.gacl.mapping.classMapper.getClass2";//ӳ��sql�ı�ʶ�ַ���
	        //ִ�в�ѯ����������ѯ����Զ���װ��Classes���󷵻�
	        Classes clazz = sqlSession.selectOne(statement,2);//��ѯclass����idΪ1�ļ�¼
	        //ʹ��SqlSessionִ����SQL֮����Ҫ�ر�SqlSession
	        sqlSession.close();
	        System.out.println(clazz);//��ӡ�����Classes [id=1, name=class_a, teacher=Teacher [id=1, name=teacher1]]
	    }
}
