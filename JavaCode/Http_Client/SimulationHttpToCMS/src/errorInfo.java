
public class errorInfo {
  //各种返回码/错误码返回值
	public static int NORMAL=10001;          //登录一切正常
	public static int NO_LOGIN=1;               //没调用login接口，调用其他接口报-401
	public static int Login_NO_SESSIONID=2;              //有调用login接口，用户名或密码错误或2200有问题
	public static int AsynSearch_SUCCESS=10002;   //调用AsynSearch操作成功
	public static int AsynSearch_LOGIN_ERROR=3; //调用AsynSearch之前没登录
	public static int AsynSearch_DESC_ERROR=4;   //调用AsynSearch解析的值错误
	
	public static int AsyncSearchResult_SUCCESS=10003; //
}
