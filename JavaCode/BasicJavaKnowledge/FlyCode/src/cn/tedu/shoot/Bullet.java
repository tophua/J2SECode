package cn.tedu.shoot;
import java.awt.image.BufferedImage;
/** 子弹: 是飞行物 */
public class Bullet extends FlyingObject {
	private static BufferedImage image; //图片
	static{
		image = loadImage("bullet.png");
	}
	
	private int speed; //移动速度
	/** 构造方法 */
	public Bullet(int x,int y){
		super(8,14,x,y);
		speed = 3;
	}
	
	/** 重写step() */
	public void step(){
		y-=speed; //y-(向上)
	}
	
	/** 重写getImage()获取图片 */
	public BufferedImage getImage(){
		if(isLife()){ //若活着呢就返回image
			return image;
		}else if(isDead()){ //若死了就删除
			state=REMOVE;
		}
		return null; //若死了或删除了则返回null
	}
	
	/** 重写outOfBounds()越界检查 */
	public boolean outOfBounds(){
		return this.y<=-this.height; //子弹的y<=负的子弹的高，即为越界了
	}
	
}













