package cn.tedu.shoot;
import java.awt.image.BufferedImage;
/** 英雄机: 是飞行物 */
public class Hero extends FlyingObject {
	private static BufferedImage[] images; //图片数组
	static{
		images = new BufferedImage[6]; //6张图
		for(int i=0;i<images.length;i++){
			images[i] = loadImage("hero"+i+".png");
		}
	}
	
	private int life; //命
	private int doubleFire; //火力值
	/** 构造方法 */
	public Hero(){
		super(97,124,140,400);
		life = 3;
		doubleFire = 0;
	}
	
	/** 英雄机随着鼠标移动  x,y:鼠标的x坐标和y坐标 */
	public void moveTo(int x,int y){
		this.x = x-this.width/2;  //英雄机的x=鼠标的x-1/2英雄机的宽
		this.y = y-this.height/2; //英雄机的y=鼠标的y-1/2英雄机的高
	}
	
	/** 重写step() */
	public void step(){
		
	}
	
	int index = 0; //活着时的起始下标
	int deadIndex = 2; //死了时的起始下标
	/** 重写getImage()获取图片 */
	public BufferedImage getImage(){ //10毫秒走一次
		if(isLife()){ //若活着呢则images[0]和images[1]切换
			return images[index++%2];
			/*
			 * 10M images[0] index=1
			 * 20M images[1] index=2
			 * 30M images[0] index=3
			 * 40M images[1] index=4
			 * ...
			 */
		}else if(isDead()){ //若死了的
			BufferedImage img = images[deadIndex++];
			if(deadIndex==images.length){
				state = REMOVE;
			}
			return img;
			/*
			 * 10M img=images[2] deadIndex=3
			 * 20M img=images[3] deadIndex=4
			 * 30M img=images[4] deadIndex=5
			 * 40M img=images[5] deadIndex=6 REMOVE
			 */
		}
		return null; //若死了或删除了则返回null
	}
	
	/** 英雄机发射子弹(创建子弹对象) */
	public Bullet[] shoot(){
		int xStep = this.width/4; //1/4英雄机的宽
		int yStep = 20; //固定的20
		if(doubleFire>0){ //双倍
			Bullet[] bs = new Bullet[2]; //2发子弹
			bs[0] = new Bullet(this.x+1*xStep,this.y-yStep); //x:英雄机的x+1/4英雄机的宽 y:英雄机的y-固定的20
			bs[1] = new Bullet(this.x+3*xStep,this.y-yStep); //x:英雄机的x+3/4英雄机的宽 y:英雄机的y-固定的20
			doubleFire-=2; //发射一次双倍火力，则火力值减2
			return bs;
		}else{ //单倍
			Bullet[] bs = new Bullet[1]; //1发子弹
			bs[0] = new Bullet(this.x+2*xStep,this.y-yStep); //x:英雄机的x+2/4英雄机的宽 y:英雄机的y-固定的20
			return bs;
		}
	}
	
	/** 重写outOfBounds()越界检查 */
	public boolean outOfBounds(){
		return false; //永不越界
	}
	
	/** 英雄机得命 */
	public void addLife(){
		life++; //命数增1
	}
	
	/** 英雄机减命 */
	public void subtractLife(){
		life--; //命数减1
	}
	
	/** 获取英雄机的命 */
	public int getLife(){
		return life; //返回命数
	}
	
	/** 英雄机得火力 */
	public void addDoubleFire(){
		doubleFire+=40; //火力值增40
	}
	
	/** 清空火力值 */
	public void clearDoubleFire(){
		doubleFire=0; //火力值归0
	}
	
}















