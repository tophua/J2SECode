package me.gacl.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import me.gacl.domain.User;
import me.gacl.mapping.UserMapperI;
import me.gacl.util.MyBatisUtil;

public class TestCRUDByAnnotationMapper {

	  public void testAdd(){
	        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
	        //得到UserMapperI接口的实现类对象，UserMapperI接口的实现类对象由sqlSession.getMapper(UserMapperI.class)动态构建出来
	        UserMapperI mapper = sqlSession.getMapper(UserMapperI.class);
	        User user = new User();
	        user.setName("用户xdp");
	        user.setAge(20);
	        int add = mapper.add(user);
	        //使用SqlSession执行完SQL之后需要关闭SqlSession
	        sqlSession.close();
	        System.out.println(add);
	    }
	    
	    public void testUpdate(){
	        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
	        //得到UserMapperI接口的实现类对象，UserMapperI接口的实现类对象由sqlSession.getMapper(UserMapperI.class)动态构建出来
	        UserMapperI mapper = sqlSession.getMapper(UserMapperI.class);
	        User user = new User();
	        user.setId(3);
	        user.setName("wwy_xdp");
	        user.setAge(26);
	        //执行修改操作
	        int retResult = mapper.update(user);
	        //使用SqlSession执行完SQL之后需要关闭SqlSession
	        sqlSession.close();
	        System.out.println(retResult);
	    }
	    
	    public void testDelete(){
	        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
	        //得到UserMapperI接口的实现类对象，UserMapperI接口的实现类对象由sqlSession.getMapper(UserMapperI.class)动态构建出来
	        UserMapperI mapper = sqlSession.getMapper(UserMapperI.class);
	        //执行删除操作
	        int retResult = mapper.deleteById(7);
	        //使用SqlSession执行完SQL之后需要关闭SqlSession
	        sqlSession.close();
	        System.out.println(retResult);
	    }
	    
	    public void testGetUser(){
	        SqlSession sqlSession = MyBatisUtil.getSqlSession();
	        //得到UserMapperI接口的实现类对象，UserMapperI接口的实现类对象由sqlSession.getMapper(UserMapperI.class)动态构建出来
	        UserMapperI mapper = sqlSession.getMapper(UserMapperI.class);
	        //执行查询操作，将查询结果自动封装成User返回
	        User user = mapper.getById(8);
	        //使用SqlSession执行完SQL之后需要关闭SqlSession
	        sqlSession.close();
	        System.out.println(user);
	    }
	    
	    public void testGetAll(){
	        SqlSession sqlSession = MyBatisUtil.getSqlSession();
	        //得到UserMapperI接口的实现类对象，UserMapperI接口的实现类对象由sqlSession.getMapper(UserMapperI.class)动态构建出来
	        UserMapperI mapper = sqlSession.getMapper(UserMapperI.class);
	        //执行查询操作，将查询结果自动封装成List<User>返回
	        List<User> lstUsers = mapper.getAll();
	        //使用SqlSession执行完SQL之后需要关闭SqlSession
	        sqlSession.close();
	        System.out.println(lstUsers);
	    }

}
