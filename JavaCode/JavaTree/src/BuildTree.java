import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author wangwy
 *
 */
public class BuildTree {
	
	public static <T> Tree<T> build(List<Tree<T>> nodes) {
		 
	    if(nodes == null){
	      return null;
	    }
	    List<Tree<T>> topNodes = new ArrayList<Tree<T>>();
	 
	    for (Tree<T> children : nodes) {
	 
	      String pid = children.getParentId();
	      if (pid == null || "".equals(pid)) {
	        topNodes.add(children);
	 
	        continue;
	      }
	 
	      for (Tree<T> parent : nodes) {
	        String id = parent.getId();
	        if (id != null && id.equals(pid)) {
	          parent.getChildren().add(children);
	          children.setParent(true);
	          parent.setChildren(true);
	           
	          continue;
	        }
	      }
	 
	    }
	 
	    Tree<T> root = new Tree<T>();
	    if (topNodes.size() == 0) {
	      root = topNodes.get(0);
	    } else {
	      root.setId("-1");
	      root.setParentId("");
	      root.setParent(false);
	      root.setChildren(true);
	      root.setChecked(true);
	      root.setChildren(topNodes);
	      root.setText("¶¥¼¶½Úµã");	 
	    }	 
	    return root;
	  }
	
	
	public static <T> List<Tree<T>> build1(List<Tree<T>> nodes) {
		 
	    if(nodes == null){
	      return null;
	    }
	    List<Tree<T>> topNodes = new ArrayList<Tree<T>>();
	 
	    for (Tree<T> children : nodes) {
	 
	      String pid = children.getParentId();
	      if (pid == null || "".equals(pid)) {
	        topNodes.add(children);
	 
	        continue;
	      }
	 
	      for (Tree<T> parent : nodes) {
	        String id = parent.getId();
	        if (id != null && id.equals(pid)) {
	          parent.getChildren().add(children);
	          children.setParent(true);
	          parent.setChildren(true);
	           
	          continue;
	        }
	      }
	 
	    }	 
	    return topNodes;
	  }
}
