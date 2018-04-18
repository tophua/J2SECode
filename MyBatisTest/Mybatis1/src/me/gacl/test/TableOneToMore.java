package me.gacl.test;

import org.apache.ibatis.session.SqlSession;

import me.gacl.domain.ClassesNew;
import me.gacl.util.MyBatisUtil;

public class TableOneToMore {

	public void testGetClass3() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		/**
		 * ӳ��sql�ı�ʶ�ַ�����
		 * me.gacl.mapping.classMapper��classMapper.xml�ļ���mapper��ǩ��namespace���Ե�ֵ��
		 * getClass3��select��ǩ��id����ֵ��ͨ��select��ǩ��id����ֵ�Ϳ����ҵ�Ҫִ�е�SQL
		 */
		String statement = "me.gacl.mapping.classNewMapper.getClass3";// ӳ��sql�ı�ʶ�ַ���
		// ִ�в�ѯ����������ѯ����Զ���װ��Classes���󷵻�
		ClassesNew clazz = sqlSession.selectOne(statement, 1);// ��ѯclass����idΪ1�ļ�¼
		// ʹ��SqlSessionִ����SQL֮����Ҫ�ر�SqlSession
		sqlSession.close();
		// ��ӡ�����Classes [id=1, name=class_a, teacher=Teacher [id=1,
		// name=teacher1], students=[Student [id=1, name=student_A], Student
		// [id=2, name=student_B], Student [id=3, name=student_C]]]
		System.out.println(clazz);
	}

	public void testGetClass4() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		/**
		 * ӳ��sql�ı�ʶ�ַ�����
		 * me.gacl.mapping.classMapper��classMapper.xml�ļ���mapper��ǩ��namespace���Ե�ֵ��
		 * getClass4��select��ǩ��id����ֵ��ͨ��select��ǩ��id����ֵ�Ϳ����ҵ�Ҫִ�е�SQL
		 */
		String statement = "me.gacl.mapping.classNewMapper.getClass4";// ӳ��sql�ı�ʶ�ַ���
		// ִ�в�ѯ����������ѯ����Զ���װ��Classes���󷵻�
		ClassesNew clazz = sqlSession.selectOne(statement, 2);// ��ѯclass����idΪ1�ļ�¼
		// ʹ��SqlSessionִ����SQL֮����Ҫ�ر�SqlSession
		sqlSession.close();
		// ��ӡ�����Classes [id=1, name=class_a, teacher=Teacher [id=1,
		// name=teacher1], students=[Student [id=1, name=student_A], Student
		// [id=2, name=student_B], Student [id=3, name=student_C]]]
		System.out.println(clazz);
	}
}
