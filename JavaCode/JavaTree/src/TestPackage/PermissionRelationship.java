
package TestPackage;
  

public class PermissionRelationship
{
    private String id;
    private String pid;
    private String permission_name;
    private String type;
    private String url;
    private String seq;
    private String operation_key;
    
    public PermissionRelationship() {
        super();
    }
    
    public PermissionRelationship(String id,String pid,String permission_name,
                                  String type,String url,String seq,String operation_key) {
        super();
        this.id = id;
        this.pid = pid;
        this.permission_name = permission_name;
        this.type = type;
        this.url = url;
        this.seq = seq;
        this.operation_key = operation_key;
    }
    
//    @Override
//    public String toString(){
//        return "[id=" + id + ", pid=" + pid + ", permission_name=" + permission_name + "]";
//    }
    
    public String getId()
    {
    
        return id;
    }
    public void setId(String id)
    {
    
        this.id = id;
    }
    public String getPid()
    {
    
        return pid;
    }
    public void setPid(String pid)
    {
    
        this.pid = pid;
    }
    public String getPermission_name()
    {
    
        return permission_name;
    }
    public void setPermission_name(String permission_name)
    {
    
        this.permission_name = permission_name;
    }
    public String getType()
    {
    
        return type;
    }

    public void setType(String type)
    {
    
        this.type = type;
    }

    public String getUrl()
    {
    
        return url;
    }

    public void setUrl(String url)
    {
    
        this.url = url;
    }

    public String getSeq()
    {
    
        return seq;
    }

    public void setSeq(String seq)
    {
    
        this.seq = seq;
    }

    public String getOperation_key()
    {
    
        return operation_key;
    }

    public void setOperation_key(String operation_key)
    {
    
        this.operation_key = operation_key;
    }
    
}

