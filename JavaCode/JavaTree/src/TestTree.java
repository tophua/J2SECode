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
		List<Tree<TreeRelationship>> trees = new ArrayList<Tree<TreeRelationship>>();
	    List<TreeRelationship> tests = new ArrayList<TreeRelationship>();
	    
	    tests.add(new TreeRelationship("-1","","����ed"));
	    tests.add(new TreeRelationship("0","","���ڱ���"));
	    tests.add(new TreeRelationship("1", "0", "����ѧϰ"));
	    tests.add(new TreeRelationship("2", "0", "��Ȥ"));
	    tests.add(new TreeRelationship("3", "1", "JAVA"));
	    tests.add(new TreeRelationship("4", "1", "oracle"));
	    tests.add(new TreeRelationship("5", "1", "spring"));
	    tests.add(new TreeRelationship("6", "1", "springmvc"));
	    tests.add(new TreeRelationship("7", "1", "fastdfs"));
	    tests.add(new TreeRelationship("8", "1", "linux"));
	    tests.add(new TreeRelationship("9", "2", "����"));
	    tests.add(new TreeRelationship("10", "2", "�Ժ�����"));
	    tests.add(new TreeRelationship("11", "2", "ѧϰ"));
	    tests.add(new TreeRelationship("12", "3", "String"));
	    tests.add(new TreeRelationship("13", "4", "sql"));
	    tests.add(new TreeRelationship("14", "5", "ioc"));
	    tests.add(new TreeRelationship("15", "5", "aop"));
	    tests.add(new TreeRelationship("16", "1", "�ȵ�"));
	    tests.add(new TreeRelationship("17", "2", "�ȵ�"));
	    tests.add(new TreeRelationship("18", "3", "�ȵ�"));
	    tests.add(new TreeRelationship("19", "4", "�ȵ�"));
	    tests.add(new TreeRelationship("20", "5", "�ȵ�"));
	    
	    //������������ϵ
	    for (TreeRelationship test : tests) {
	        Tree<TreeRelationship> tree = new Tree<TreeRelationship>();
	        tree.setId(test.getId());
	        tree.setParentId(test.getPid());
	        tree.setText(test.getText());         
	        trees.add(tree);
	      }
	   
	 //     Tree<TreeRelationship> t = BuildTree.build(trees);
	    List<Tree<TreeRelationship>> t = BuildTree.build1(trees);
	    System.out.println(JSON.toJSONString(t));
	
	}

}
