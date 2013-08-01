package org.adaikiss.xun.demo.sprite;

import org.adaikiss.xun.demo.BattleView;
import org.adaikiss.xun.demo.BulletPool;
import org.adaikiss.xun.demo.Main;
import org.adaikiss.xun.demo.R;
import org.adaikiss.xun.demo.enumeration.BulletType;
import org.adaikiss.xun.demo.enumeration.Direction;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

/**  
 * <pre>
 * Copyright:	Copyright (c)2009  
 * Company:		杭州龙驹信息科技
 * Author:		HuLingwei
 * Create at:	2013-7-30 上午9:38:36  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */

public class Ship extends Sprite {
    private int mShipIndex = 0;
    private Bitmap[] mShipFlying = new Bitmap[4];
    private int mShipXMin = 10;
    private int mShipXMax = 40;
    private int mX;
    private int mY;
    private BattleView mBattleView;

    private boolean exploded;

    private int shootInterval = 5;
    private int shootCountDown = 0;

    private Thread motionThread;

 // move
 	private volatile boolean moving;
 	private int direction;

    public Ship(Resources res, BattleView battleView){
    	mBattleView = battleView;
    	mShipFlying[0] = BitmapFactory.decodeResource(res, R.drawable.ship);
		mShipFlying[0] = Bitmap.createScaledBitmap(mShipFlying[0], 64, 64, false);
        mShipFlying[1] = BitmapFactory.decodeResource(res, R.drawable.ship);
        mShipFlying[1] = Bitmap.createScaledBitmap(mShipFlying[1], 64, 64, false);
        mShipFlying[2] = BitmapFactory.decodeResource(res, R.drawable.ship);
        mShipFlying[2] = Bitmap.createScaledBitmap(mShipFlying[2], 64, 64, false);
        mShipFlying[3] = BitmapFactory.decodeResource(res, R.drawable.ship);
        mShipFlying[3] = Bitmap.createScaledBitmap(mShipFlying[3], 64, 64, false);
    }

    public void start(){
    	exploded = false;
    	moving = false;
    	mX = (mBattleView.getBattleThread().getCanvasWidth() - mShipFlying[0].getWidth()) / 2;
    	motionThread = new Thread() {

//			private int i = 0;
			@Override
			public void run() {
				Log.d("MOTION",Main.running + ", " +  mBattleView.isRunning());
				while (Main.running && mBattleView.isRunning()) {
					Log.d("MOTION", "direction:" + direction + ", moving:" + moving);
					try {
//						if(i++ % 50 == 0){
//							executor.execute(new Runnable(){
//								@Override
//								public void run(){
//									mBattleThread.getShip().shoot();
//								}
//							});
//						}
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (moving) {
						motion();
					}
				}
			}

		};
    	motionThread.start();
    }

    public void motion(){
    	switch (direction) {
		case Main.MOVE_LEFT:
			moveLeft();
			break;
		case Main.MOVE_RIGHT:
			moveRight();
			break;
		case Main.MOVE_UP:
			speedUp();
			break;
		case Main.MOVE_DOWN:
			speedDown();
			break;
		}
    }

	@Override
	public void tick(Canvas canvas) {
		if(exploded){
			return;
		}
		if(shootCountDown-- == 0){
			shoot();
			shootCountDown = shootInterval;
		}
		mShipIndex++;
        if (mShipIndex == 4)
            mShipIndex = 0;
        // draw the space ship in the same lane as the next asteroid
        canvas.drawBitmap(mShipFlying[mShipIndex], mX, mY, null);

	}

	public void shoot(){
		Bullet b = BulletPool.getInstance().require(BulletType.PlayerNormal);
		b.setVisibility(true);
		b.setDirection(Direction.Up);
		b.setPosition(mX + mShipFlying[0].getWidth()/2, mY);
		mBattleView.getBattleThread().addBullet(b);
	}

	public void moveLeft(){
		mX = Math.max(mX - 2, mShipXMin);
	}

	public void moveRight(){
		mX = Math.min(mX + 2, mShipXMax);
	}
	public void speedUp(){
		
	}
	public void speedDown(){
		
	}

	@Override
	public void resize(int width, int height){
		mShipXMax = width - mShipFlying[0].getWidth() - 10;
		mY = height - mShipFlying[0].getHeight();
		mX = (width - mShipFlying[0].getWidth()) / 2;
	}

	@Override
	public int[] getRange() {
		return new int[]{mX, mY, mShipFlying[0].getWidth(), mShipFlying[0].getHeight()};
	}

	public void explode(){
		exploded = true;
		motionThread = null;
		Main.instance.over();
	}

	public void setDirection(int direction){
		this.direction = direction;
	}

	public void setMoving(boolean moving){
		this.moving = moving;
	}

	public Thread getMotionThread(){
		return motionThread;
	}
}
