package TestPackage;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * 
 */

/**
 * @author wangwy
 *
 */
public class TestTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		List<Tree<TreeRelationship>> trees = new ArrayList<Tree<TreeRelationship>>();
//	    List<TreeRelationship> tests = new ArrayList<TreeRelationship>();
//	    
//	    tests.add(new TreeRelationship("-1","","关于ed"));
//	    tests.add(new TreeRelationship("0","","关于本人"));
//	    tests.add(new TreeRelationship("1", "0", "技术学习"));
//	    tests.add(new TreeRelationship("2", "0", "兴趣"));
//	    tests.add(new TreeRelationship("3", "1", "JAVA"));
//	    tests.add(new TreeRelationship("4", "1", "oracle"));
//	    tests.add(new TreeRelationship("5", "1", "spring"));
//	    tests.add(new TreeRelationship("6", "1", "springmvc"));
//	    tests.add(new TreeRelationship("7", "1", "fastdfs"));
//	    tests.add(new TreeRelationship("8", "1", "linux"));
//	    tests.add(new TreeRelationship("9", "2", "骑行"));
//	    tests.add(new TreeRelationship("10", "2", "吃喝玩乐"));
//	    tests.add(new TreeRelationship("11", "2", "学习"));
//	    tests.add(new TreeRelationship("12", "3", "String"));
//	    tests.add(new TreeRelationship("13", "4", "sql"));
//	    tests.add(new TreeRelationship("14", "5", "ioc"));
//	    tests.add(new TreeRelationship("15", "5", "aop"));
//	    tests.add(new TreeRelationship("16", "1", "等等"));
//	    tests.add(new TreeRelationship("17", "2", "等等"));
//	    tests.add(new TreeRelationship("18", "3", "等等"));
//	    tests.add(new TreeRelationship("19", "4", "等等"));
//	    tests.add(new TreeRelationship("20", "5", "等等"));
	    
	    //输出上面的树关系
//	    for (TreeRelationship test : tests) {
//	        Tree<TreeRelationship> tree = new Tree<TreeRelationship>();
//	        tree.setId(test.getId());
//	        tree.setParentId(test.getPid());
//	        trees.add(tree);
//	      }
//	   
//	 //     Tree<TreeRelationship> t = BuildTree.build(trees);
//	    List<Tree<TreeRelationship>> t = BuildTree.build1(trees);
		
		 List<PermissionRelationship> tests = new ArrayList<PermissionRelationship>();
		 tests.add(new PermissionRelationship("1","","资源监测","nav","","1", ""));
		 tests.add(new PermissionRelationship("2","","统计报表","nav","","2", ""));
		 tests.add(new PermissionRelationship("3","","配置管理","nav","","3", ""));
		 tests.add(new PermissionRelationship("100","3","用户管理","nav","/user/getUserList","1", ""));
		 tests.add(new PermissionRelationship("101","100","查看详情","opt","/user/getUserDetail","1", "detail"));
		 tests.add(new PermissionRelationship("102","100","添加","opt","/user/addUser","2", "add"));
		 tests.add(new PermissionRelationship("103","100","修改","nav","","1", ""));
		 tests.add(new PermissionRelationship("200","3","角色/权限管理","nav","","1", "")); 
		 tests.add(new PermissionRelationship("201","200","添加","nav","","1", ""));
		 tests.add(new PermissionRelationship("500","3","数据字典配置","nav","","1", ""));
		 tests.add(new PermissionRelationship("501","500","添加","nav","","1", ""));
		 
	    System.out.println(JSONTree1(tests));
	
	}
	
//	static String JSONTree(List<TreeRelationship> tests){
//		List<Tree2<TreeRelationship>> trees = new ArrayList<Tree2<TreeRelationship>>();
//	    for (TreeRelationship test : tests) {
//	        Tree2<TreeRelationship> tree = new Tree2<TreeRelationship>();
//	        tree.setId(test.getId());
//	        tree.setParentId(test.getPid());
//	        tree.setNode(test);
//	        trees.add(tree);
//	      }
//	    List<Tree2<TreeRelationship>> t = BuildTree2.build1(trees);
//	    return JSON.toJSONString(t);
//	}
	
	static String JSONTree1(List<PermissionRelationship> tests){
		List<Tree2<PermissionRelationship>> trees = new ArrayList<Tree2<PermissionRelationship>>();
	    for (PermissionRelationship test : tests) {
	        Tree2<PermissionRelationship> tree = new Tree2<PermissionRelationship>();
	        tree.setId(test.getId());
	        tree.setParentId(test.getPid());
	        tree.setNode(test);
	        trees.add(tree);
	      }
	    List<Tree2<PermissionRelationship>> t = BuildTree2.build1(trees);
	    return JSON.toJSONString(t);
	}

}
