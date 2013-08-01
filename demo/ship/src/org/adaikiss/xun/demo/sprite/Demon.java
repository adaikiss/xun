package org.adaikiss.xun.demo.sprite;

import org.adaikiss.xun.demo.BattleView;
import org.adaikiss.xun.demo.BulletPool;
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
 * Create at:	2013-7-31 上午11:13:15  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */

public class Demon extends Sprite {
	private Bitmap mImage;
	private int mX;
	private int mY;

	private int shootInterval = 10;
    private int shootCountDown = 0;

    private BattleView mBattleView;

	public Demon(Resources res, BattleView battleView){
		mBattleView = battleView;
		mImage = BitmapFactory.decodeResource(res, R.drawable.demon);
	}

	public void shoot(){
		Bullet b = BulletPool.getInstance().require(BulletType.EmenyNormal);
		b.setEnemy(true);
		b.setVisibility(true);
		b.setDirection(Direction.Down);
		b.setSpeed(15);
		b.setPosition(mX + mImage.getWidth()/2, mY + mImage.getHeight());
		mBattleView.getBattleThread().addBullet(b);
	}

	public void setPosition(int x, int y){
		mX = x;
		mY = y;
	}

	@Override
	public void tick(Canvas canvas) {
		if(shootCountDown-- == 0){
			shoot();
			shootCountDown = shootInterval;
		}
		canvas.drawBitmap(mImage, mX, mY, null);
	}

	public void hurt(int damage){
		Log.d("DEMON", "hurt:" + damage);
	}

	@Override
	public int[] getRange() {
		return new int[]{mX, mY, mImage.getWidth(), mImage.getHeight()};
	}
}
