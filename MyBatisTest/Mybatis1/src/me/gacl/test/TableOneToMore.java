package me.gacl.test;

import org.apache.ibatis.session.SqlSession;

import me.gacl.domain.ClassesNew;
import me.gacl.util.MyBatisUtil;

public class TableOneToMore {

	public void testGetClass3() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		/**
		 * 映射sql的标识字符串，
		 * me.gacl.mapping.classMapper是classMapper.xml文件中mapper标签的namespace属性的值，
		 * getClass3是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
		 */
		String statement = "me.gacl.mapping.classNewMapper.getClass3";// 映射sql的标识字符串
		// 执行查询操作，将查询结果自动封装成Classes对象返回
		ClassesNew clazz = sqlSession.selectOne(statement, 1);// 查询class表中id为1的记录
		// 使用SqlSession执行完SQL之后需要关闭SqlSession
		sqlSession.close();
		// 打印结果：Classes [id=1, name=class_a, teacher=Teacher [id=1,
		// name=teacher1], students=[Student [id=1, name=student_A], Student
		// [id=2, name=student_B], Student [id=3, name=student_C]]]
		System.out.println(clazz);
	}

	public void testGetClass4() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		/**
		 * 映射sql的标识字符串，
		 * me.gacl.mapping.classMapper是classMapper.xml文件中mapper标签的namespace属性的值，
		 * getClass4是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
		 */
		String statement = "me.gacl.mapping.classNewMapper.getClass4";// 映射sql的标识字符串
		// 执行查询操作，将查询结果自动封装成Classes对象返回
		ClassesNew clazz = sqlSession.selectOne(statement, 2);// 查询class表中id为1的记录
		// 使用SqlSession执行完SQL之后需要关闭SqlSession
		sqlSession.close();
		// 打印结果：Classes [id=1, name=class_a, teacher=Teacher [id=1,
		// name=teacher1], students=[Student [id=1, name=student_A], Student
		// [id=2, name=student_B], Student [id=3, name=student_C]]]
		System.out.println(clazz);
	}
}
