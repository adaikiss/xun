package org.adaikiss.xun.demo.sprite;

import org.adaikiss.xun.demo.BulletPool;
import org.adaikiss.xun.demo.enumeration.Direction;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**  
 * <pre>
 * Copyright:	Copyright (c)2009  
 * Company:		杭州龙驹信息科技
 * Author:		HuLingwei
 * Create at:	2013-7-30 上午8:40:12  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */

public class Bullet extends Sprite{

	private boolean enemy;

	private boolean visibility;

	private Direction direction;

	private int mSpeed = 10;

//	private int mYMin;
//	private int mYMax;

	private int mX;
	private int mY;

	private Bitmap mBitmap;
//	private Matrix matrix;

	private int mDamage = 4;

	public Bullet(Bitmap bitmap){
		mBitmap = bitmap;
//		matrix = new Matrix();
//		matrix.setRotate(-90f, 0, 0);
	}

	public void init(){
		
	}

	@Override
	public void tick(Canvas canvas) {
//		Log.d("BULLET", mX + ", " + mY);
		if(!this.visibility || Direction.None.equals(this.direction)){
			destroy();
			return;
		}
		//move the bullet
		move();
//		matrix.postTranslate(mX, mY);
		canvas.drawBitmap(mBitmap, mX, mY, null);
	}

	private void move(){
		switch(direction){
		case Up:
			mY -= mSpeed;
			break;
		case Down:
			mY += mSpeed;
			break;
		}
		if(mY <= 0){
			setVisibility(false);
		}
		
	}

	public void destroy(){
		BulletPool.getInstance().recycle(this);
	}

	public void setVisibility(boolean visibility){
		this.visibility = visibility;
	}

	public boolean isVisible(){
		return this.visibility;
	}

	public void setDirection(Direction direction){
		this.direction = direction;
	}

	public void setPosition(int x, int y){
		mX = x - mBitmap.getWidth()/2;
		mY = y - mBitmap.getHeight();
	}

	public int getDamage(){
		return mDamage;
	}

	@Override
	public int[] getRange() {
		return new int[]{mX, mY, mBitmap.getWidth(), mBitmap.getHeight()};
	}

	public boolean isEnemy() {
		return enemy;
	}

	public void setEnemy(boolean enemy) {
		this.enemy = enemy;
	}

	public void setBitmap(Bitmap bitmap){
		mBitmap = bitmap;
	}

	public void setSpeed(int speed){
		mSpeed = speed;
	}
}
