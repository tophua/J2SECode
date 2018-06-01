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
public class Tree<T> {
	
	  private String id; //节点ID
	  private String text; //显示节点文本
	  private String state = "open"; //节点状态，open closed
	  private boolean checked = false; //节点是否被选中 true false
	  private List<Map<String, Object>> attributes; //节点属性
	  private List<Tree<T>> children = new ArrayList<Tree<T>>(); //节点的子节点
	  private String parentId; //父ID
	  private boolean isParent = false;  //是否有父节点
	  private boolean isChildren = false;  //是否有子节点
	  
	  public Tree(){
		  super();
	  }
	  
	public Tree(String id, String text, String state, boolean checked, List<Map<String, Object>> attributes,
			List<Tree<T>> children, boolean isParent, boolean isChildren, String parentID) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
		this.checked = checked;
		this.attributes = attributes;
		this.children = children;
		this.isParent = isParent;
		this.isChildren = isChildren;
		this.parentId = parentID;
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public List<Map<String, Object>> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<Map<String, Object>> attributes) {
		this.attributes = attributes;
	}
	public List<Tree<T>> getChildren() {
		return children;
	}
	public void setChildren(List<Tree<T>> children) {
		this.children = children;
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
}
