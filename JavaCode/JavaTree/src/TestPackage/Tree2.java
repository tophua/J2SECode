package TestPackage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 
 */

/**
 * @author wangwy
 *
 */
public class Tree2<T> {
	
	  private String id; //节点ID
	  private String parentId; //父ID
	  private T node;
	  private List<Tree2<T>> childrens = new ArrayList<Tree2<T>>(); //节点的子节点
	  private boolean isParent = false;  //是否有父节点
	  private boolean isChildren = false;  //是否有子节点
	  
	  public Tree2(){
		  super();
	  }
	  
	public Tree2(String id,String parentID,T node,List<Tree2<T>> childrens, boolean isParent, boolean isChildren) {
		super();
		this.id = id;
		this.parentId = parentID;
		this.node =node;
		this.childrens = childrens;
		this.isParent = isParent;
		this.isChildren = isChildren;
		
	}
	
	 @Override
	  public String toString() {
	     
	    return JSON.toJSONString(this);
	  }
	 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public List<Tree2<T>> getChildrens() {
		return childrens;
	}
	public void setChildrens(List<Tree2<T>> childrens) {
		this.childrens = childrens;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public boolean isParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	public boolean isChildren() {
		return isChildren;
	}
	public void setChildren(boolean isChildren) {
		this.isChildren = isChildren;
	}

	public T getNode() {
		return node;
	}

	public void setNode(T node) {
		this.node = node;
	}
}
