package MyJDBC;

import java.sql.SQLException;
import java.util.List;
/*
 * AccountDao是处理数据库的业务逻辑
 */
public class AccountDao {

	   public void add(Account account) throws SQLException{
	        String sql = "insert into account(name,money) values(?,?)";
	        Object params[] = {account.getName(),account.getMoney()};
	        JdbcUtils.update(sql, params);
	    }
	    
	    
	    public void delete(int id) throws SQLException{
	        String sql = "delete from account where id=?";
	        Object params[] = {id};
	        JdbcUtils.update(sql, params);
	    }
	    
	    public void update(Account account) throws SQLException{
	        
	        String sql = "update account set name=?,money=? where id=?";
	        Object params[] = {account.getName(),account.getMoney(),account.getId()};
	        JdbcUtils.update(sql, params);
	        
	    }
	 // query()支持不同的子类对象的返回值，所以query()的输入参数是接口，这样可以不同子类去实现这个接口就可以。  
	    public Account find(int id) throws SQLException{
	        String sql = "select * from account where id=?";
	        Object params[] = {id};
	        return (Account) JdbcUtils.query(sql, params, new BeanHandler(Account.class));
	    }
	    
	    public List<Account> getAll() throws SQLException{
	        String sql = "select * from account";
	        Object params[] = {};
	        return (List<Account>) JdbcUtils.query(sql, params,new BeanListHandler(Account.class));
	    }
}
