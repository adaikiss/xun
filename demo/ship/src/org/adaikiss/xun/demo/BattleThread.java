package org.adaikiss.xun.demo;

import java.util.LinkedList;

import org.adaikiss.xun.demo.sprite.Bullet;
import org.adaikiss.xun.demo.sprite.Demon;
import org.adaikiss.xun.demo.sprite.Ship;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.view.SurfaceHolder;

/**  
 * <pre>
 * Copyright:	Copyright (c)2009  
 * Company:		杭州龙驹信息科技
 * Author:		HuLingwei
 * Create at:	2013-7-31 上午9:47:20  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */

public class BattleThread extends Thread {
	private SurfaceHolder mSurfaceHolder;
	private Bitmap mBackgroundImageFar;
	private int mBGFarMoveY = 0;
	private int mCanvasHeight = 1;
    private int mCanvasWidth = 1;
    private int speed = 2;
    private LinkedList<Bullet> bullets;

    private Ship ship;
    private Demon demon;

	Resources mRes;

	private BattleView mBattleView;

	public int getCanvasWidth(){
		return mCanvasWidth;
	}

	public BattleThread(BattleView battleView, SurfaceHolder surfaceHolder, Context context, Handler handler) {
		mBattleView = battleView;
		mSurfaceHolder = surfaceHolder;
		mRes = context.getResources();
		mBackgroundImageFar = BitmapFactory.decodeResource(mRes, R.drawable.beehive);
		ship = new Ship(mRes, battleView);
		demon = new Demon(mRes, battleView);
		demon.setPosition(50, 10);
		bullets = new LinkedList<Bullet>();
	}

	public void startStage(){
		mBattleView.setRunning(true);
		ship.start();
	}

	public void addBullet(Bullet bullet){
		synchronized(bullets){
			this.bullets.add(bullet);
		}
	}

	public Ship getShip(){
		return ship;
	}

	public void setSurfaceSize(int width, int height) {
		// synchronized to make sure these all change atomically
		synchronized (mSurfaceHolder) {
			mCanvasWidth = width;
			mCanvasHeight = height;
			// don't forget to resize the background image
//			mBackgroundImageFar = Bitmap.createScaledBitmap(mBackgroundImageFar, width, height, true);
			ship.resize(width, height);
		}
	}

	public void run() {
		while (Main.running) {
			Canvas c = null;
			try {
				c = mSurfaceHolder.lockCanvas(null);
				// synchronized (mSurfaceHolder) {
				doDraw(c);
				// }
			} finally {
				// do this in a finally so that if an exception is thrown
				// during the above, we don't leave the Surface in an
				// inconsistent state
				if (c != null) {
					mSurfaceHolder.unlockCanvasAndPost(c);
				}
			}
		}
	}

	public void doDraw(Canvas c) {
		doDrawBg(c);
		if(mBattleView.isRunning()){
			doDrawRunning(c);
		}
	}

	public void doDrawBg(Canvas canvas){
//		mBGFarMoveY = mBGFarMoveY - 1;
		mBGFarMoveY = mBGFarMoveY + speed;
//		int newFarY = mBackgroundImageFar.getHeight() - (-mBGFarMoveY);
		int newFarY = -mBackgroundImageFar.getHeight() - (-mBGFarMoveY);
		// if we have scrolled all the way, reset to start
		if (newFarY >= 0) {
//			mBGFarMoveY = 0;
			mBGFarMoveY = mCanvasHeight - mBackgroundImageFar.getHeight();
			// only need one draw
			canvas.drawBitmap(mBackgroundImageFar, 0, mBGFarMoveY, null);

		} else {
			// need to draw original and wrap
			canvas.drawBitmap(mBackgroundImageFar, 0, mBGFarMoveY, null);
			canvas.drawBitmap(mBackgroundImageFar, 0, newFarY, null);
		}
	}

	public void doDrawRunning(Canvas canvas) {
		//draw ship
		ship.tick(canvas);
		synchronized(bullets){
			for(int i = bullets.size() - 1;i>= 0;i--){
				Bullet b = bullets.get(i);
				if(!b.isVisible()){
					bullets.remove(i);
					continue;
				}
				if(b.isEnemy()){
					if(ship.isCollided(b)){
						ship.explode();
						b.setVisibility(false);
						bullets.remove(i);
					}
				}else{
					if(demon.isCollided(b)){
						demon.hurt(b.getDamage());
						b.setVisibility(false);
						bullets.remove(i);
					}
				}
				b.tick(canvas);
			}
		}
		demon.tick(canvas);
	}
}
