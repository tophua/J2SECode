package me.gacl.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import me.gacl.domain.User;
import me.gacl.util.MyBatisUtil;

public class TableUser {
	public void testAdd() {
		// SqlSession sqlSession = MyBatisUtil.getSqlSession(false);
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		/**
		 * 映射sql的标识字符串，
		 * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
		 * addUser是insert标签的id属性值，通过insert标签的id属性值就可以找到要执行的SQL
		 */
		String statement = "me.gacl.mapping.userMapper.addUser";// 映射sql的标识字符串
		User user = new User();
		user.setName("用户wwy"); ///// 有中文乱码
		user.setAge(20);
		// 执行插入操作
		int retResult = sqlSession.insert(statement, user);
		// 手动提交事务
		// sqlSession.commit();
		// 使用SqlSession执行完SQL之后需要关闭SqlSession
		sqlSession.close();
		System.out.println(retResult);
	}

	public void testUpdate() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		/**
		 * 映射sql的标识字符串，
		 * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
		 * updateUser是update标签的id属性值，通过update标签的id属性值就可以找到要执行的SQL
		 */
		String statement = "me.gacl.mapping.userMapper.updateUser";// 映射sql的标识字符串
		User user = new User();
		user.setId(3);
		user.setName("www");
		user.setAge(25);
		// 执行修改操作
		int retResult = sqlSession.update(statement, user);
		// 使用SqlSession执行完SQL之后需要关闭SqlSession
		sqlSession.close();
		System.out.println(retResult);
	}

	public void testDelete() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		/**
		 * 映射sql的标识字符串，
		 * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
		 * deleteUser是delete标签的id属性值，通过delete标签的id属性值就可以找到要执行的SQL
		 */
		String statement = "me.gacl.mapping.userMapper.deleteUser";// 映射sql的标识字符串
		// 执行删除操作
		int retResult = sqlSession.delete(statement, 5);
		// 使用SqlSession执行完SQL之后需要关闭SqlSession
		sqlSession.close();
		System.out.println(retResult);
	}

	public void testGetAll() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		/**
		 * 映射sql的标识字符串，
		 * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
		 * getAllUsers是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
		 */
		String statement = "me.gacl.mapping.userMapper.getAllUsers";// 映射sql的标识字符串
		// 执行查询操作，将查询结果自动封装成List<User>返回
		List<User> lstUsers = sqlSession.selectList(statement);
		// 使用SqlSession执行完SQL之后需要关闭SqlSession
		sqlSession.close();
		System.out.println(lstUsers);
	}
}
