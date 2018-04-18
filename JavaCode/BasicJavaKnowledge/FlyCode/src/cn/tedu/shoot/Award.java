package cn.tedu.shoot;
/** 奖励接口，能得奖励 */
public interface Award {
	public int LIFE = 0;        //命
	public int DOUBLE_FIRE = 1; //火力值
	/** 获取奖励类型(0或1) */
	public int getType();
}












