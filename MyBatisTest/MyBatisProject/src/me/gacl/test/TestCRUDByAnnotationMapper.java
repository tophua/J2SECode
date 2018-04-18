package me.gacl.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import me.gacl.domain.User;
import me.gacl.mapping.UserMapperI;
import me.gacl.util.MyBatisUtil;

public class TestCRUDByAnnotationMapper {

	  public void testAdd(){
	        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
	        //�õ�UserMapperI�ӿڵ�ʵ�������UserMapperI�ӿڵ�ʵ���������sqlSession.getMapper(UserMapperI.class)��̬��������
	        UserMapperI mapper = sqlSession.getMapper(UserMapperI.class);
	        User user = new User();
	        user.setName("�û�xdp");
	        user.setAge(20);
	        int add = mapper.add(user);
	        //ʹ��SqlSessionִ����SQL֮����Ҫ�ر�SqlSession
	        sqlSession.close();
	        System.out.println(add);
	    }
	    
	    public void testUpdate(){
	        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
	        //�õ�UserMapperI�ӿڵ�ʵ�������UserMapperI�ӿڵ�ʵ���������sqlSession.getMapper(UserMapperI.class)��̬��������
	        UserMapperI mapper = sqlSession.getMapper(UserMapperI.class);
	        User user = new User();
	        user.setId(3);
	        user.setName("wwy_xdp");
	        user.setAge(26);
	        //ִ���޸Ĳ���
	        int retResult = mapper.update(user);
	        //ʹ��SqlSessionִ����SQL֮����Ҫ�ر�SqlSession
	        sqlSession.close();
	        System.out.println(retResult);
	    }
	    
	    public void testDelete(){
	        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
	        //�õ�UserMapperI�ӿڵ�ʵ�������UserMapperI�ӿڵ�ʵ���������sqlSession.getMapper(UserMapperI.class)��̬��������
	        UserMapperI mapper = sqlSession.getMapper(UserMapperI.class);
	        //ִ��ɾ������
	        int retResult = mapper.deleteById(7);
	        //ʹ��SqlSessionִ����SQL֮����Ҫ�ر�SqlSession
	        sqlSession.close();
	        System.out.println(retResult);
	    }
	    
	    public void testGetUser(){
	        SqlSession sqlSession = MyBatisUtil.getSqlSession();
	        //�õ�UserMapperI�ӿڵ�ʵ�������UserMapperI�ӿڵ�ʵ���������sqlSession.getMapper(UserMapperI.class)��̬��������
	        UserMapperI mapper = sqlSession.getMapper(UserMapperI.class);
	        //ִ�в�ѯ����������ѯ����Զ���װ��User����
	        User user = mapper.getById(8);
	        //ʹ��SqlSessionִ����SQL֮����Ҫ�ر�SqlSession
	        sqlSession.close();
	        System.out.println(user);
	    }
	    
	    public void testGetAll(){
	        SqlSession sqlSession = MyBatisUtil.getSqlSession();
	        //�õ�UserMapperI�ӿڵ�ʵ�������UserMapperI�ӿڵ�ʵ���������sqlSession.getMapper(UserMapperI.class)��̬��������
	        UserMapperI mapper = sqlSession.getMapper(UserMapperI.class);
	        //ִ�в�ѯ����������ѯ����Զ���װ��List<User>����
	        List<User> lstUsers = mapper.getAll();
	        //ʹ��SqlSessionִ����SQL֮����Ҫ�ر�SqlSession
	        sqlSession.close();
	        System.out.println(lstUsers);
	    }

}
