package cn.tedu.shoot;
import java.awt.image.BufferedImage;
/** 小敌机: 是飞行物，也是敌人能得分 */
public class Airplane extends FlyingObject implements Enemy {
	private static BufferedImage[] images; //图片数组
	static{
		images = new BufferedImage[5]; //5张图
		for(int i=0;i<images.length;i++){
			images[i] = loadImage("airplane"+i+".png");
		}
	}
	
	private int speed; //移动速度
	/** 构造方法 */
	public Airplane(){
		super(49,36);
		speed = 2;
	}
	
	/** 重写step() */
	public void step(){
		y+=speed; //y+(向下)
	}
	
	int deadIndex = 1; //死了的起始下标
	/** 重写getImage()获取图片 */
	public BufferedImage getImage(){
		if(isLife()){ //若活着呢，则返回images[0]
			return images[0];
		}else if(isDead()){ //若死了呢，
			BufferedImage img = images[deadIndex++];
			if(deadIndex==images.length){
				state = REMOVE;
			}
			return img;
		}
		return null;
	}
	
	/** 重写outOfBounds()越界检查 */
	public boolean outOfBounds(){
		return this.y>=World.HEIGHT; //小敌机的y>=窗口的高，即为越界了
	}
	
	/** 重写getScore()得分 */
	public int getScore(){
		return 1; //打掉一个小敌机，得1分
	}
	
}










