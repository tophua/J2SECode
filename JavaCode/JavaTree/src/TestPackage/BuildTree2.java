package TestPackage;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author wangwy
 *
 */
public class BuildTree2 {
	
	public static <T> Tree2<T> build(List<Tree2<T>> nodes) {
		 
	    if(nodes == null){
	      return null;
	    }
	    List<Tree2<T>> topNodes = new ArrayList<Tree2<T>>();
	 
	    for (Tree2<T> children : nodes) {
	 
	      String pid = children.getParentId();
	      if (pid == null || "".equals(pid)) {
	        topNodes.add(children);
	 
	        continue;
	      }
	 
	      for (Tree2<T> parent : nodes) {
	        String id = parent.getId();
	        if (id != null && id.equals(pid)) {
	          parent.getChildrens().add(children);
	          children.setParent(true);
	          parent.setChildren(true);
	           
	          continue;
	        }
	      }
	 
	    }
	 
	    Tree2<T> root = new Tree2<T>();
	    if (topNodes.size() == 0) {
	      root = topNodes.get(0);
	    } else {
//	      root.setId("-1");
//	      root.setParentId("");
//	      root.setParent(false);
//	      root.setChildren(true);
//	      root.setChecked(true);
//	      root.setChildren(topNodes);
//	      root.setText("¶¥¼¶½Úµã");	 
	    }	 
	    return root;
	  }
	
	
	public static <T> List<Tree2<T>> build1(List<Tree2<T>> nodes) {
		 
	    if(nodes == null){
	      return null;
	    }
	    List<Tree2<T>> topNodes = new ArrayList<Tree2<T>>();
	 
	    for (Tree2<T> children : nodes) {
	 
	      String pid = children.getParentId();
	      if (pid == null || "".equals(pid)) {
	        topNodes.add(children);
	 
	        continue;
	      }
	 
	      for (Tree2<T> parent : nodes) {
	        String id = parent.getId();
	        if (id != null && id.equals(pid)) {
	          children.setParent(true);
		      parent.setChildren(true);
	          parent.getChildrens().add(children);
	        
	           
	          continue;
	        }
	      }
	 
	    }	 
	    return topNodes;
	  }
}
