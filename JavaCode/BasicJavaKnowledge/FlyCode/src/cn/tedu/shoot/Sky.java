package cn.tedu.shoot;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
/** 天空: 是飞行物 */
public class Sky extends FlyingObject {
	private static BufferedImage image; //图片
	static{
		image = loadImage("background.png");
	}
	
	private int speed; //移动速度
	private int y1; //y坐标(用于两个天空切换)
	/** 构造方法 */
	public Sky(){
		super(World.WIDTH,World.HEIGHT,0,0);
		speed = 1;
		y1 = -height;
	}
	
	/** 重写step()移动 */
	public void step(){
		y+=speed;  //y+(向下)
		y1+=speed; //y1+(向下)
		if(y>=this.height){ //若y>=天空的高，意味着对应图片移出窗口了
			y=-this.height; //则修改y的值为负的高(移到上面去了)
		}
		if(y1>=this.height){ //若y1>=天空的高，意味着对应的图片移出窗口了
			y1=-this.height; //则修改y1的值为负的高(移到上面去了)
		}
	}
	
	/** 重写getImage()获取图片 */
	public BufferedImage getImage(){
		return image; 
	}
	
	/** 重写paintObject()画对象 */
	public void paintObject(Graphics g){
		g.drawImage(getImage(),x,y,null); //画对象
		g.drawImage(getImage(),x,y1,null); //画对象
	}
	
	/** 重写outOfBounds()越界检查 */
	public boolean outOfBounds(){
		return false; //永不越界
	}
	
}













