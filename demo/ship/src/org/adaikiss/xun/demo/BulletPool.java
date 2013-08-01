package org.adaikiss.xun.demo;

import java.util.LinkedList;

import org.adaikiss.xun.demo.enumeration.BulletType;
import org.adaikiss.xun.demo.enumeration.Direction;
import org.adaikiss.xun.demo.sprite.Bullet;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**  
 * <pre>
 * Copyright:	Copyright (c)2009  
 * Company:		杭州龙驹信息科技
 * Author:		HuLingwei
 * Create at:	2013-7-30 上午9:20:41  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */

public class BulletPool {
	private LinkedList<Bullet> pool;
	private static BulletPool instance;
	private Context context;
	private Bitmap normalPlayerBulletBitmap;
	private Bitmap normalEnemyBulletBitmap;

	public static void init(Context context){
		instance = new BulletPool(context, 20);
		instance.initNormalPlayerBulletBitmap(6, 6*20/11, R.drawable.bullet);
		instance.initNormalEnemyBulletBitmap(6, 6*20/11, R.drawable.bullet2);
	}

	private void initNormalPlayerBulletBitmap(int width, int height, int resId){
		normalPlayerBulletBitmap = BitmapFactory.decodeResource(context.getResources(), resId);
		normalPlayerBulletBitmap = Bitmap.createScaledBitmap(normalPlayerBulletBitmap, width, height, false);
	}

	private void initNormalEnemyBulletBitmap(int width, int height, int resId){
		normalEnemyBulletBitmap = BitmapFactory.decodeResource(context.getResources(), resId);
		normalEnemyBulletBitmap = Bitmap.createScaledBitmap(normalEnemyBulletBitmap, width, height, false);
	}

	private BulletPool(Context context, int init){
		this.context = context;
		pool = new LinkedList<Bullet>();
		for(int i = 0;i<init;i++){
			pool.add(create(normalPlayerBulletBitmap));
		}
	}

	public static BulletPool getInstance(){
		return instance;
	}

	public Bullet require(BulletType type){
		Bullet b = pool.poll();
		if(null == b){
			b = create(normalPlayerBulletBitmap);
		}
		switch(type){
		case PlayerNormal:
			b.setBitmap(normalPlayerBulletBitmap);
			break;
		case EmenyNormal:
			b.setBitmap(normalEnemyBulletBitmap);
			break;
		}
		return b;
	}

	private Bullet create(Bitmap bitmap){
		return new Bullet(bitmap);
	}

	public void recycle(Bullet bullet){
		bullet.setVisibility(false);
		bullet.setDirection(Direction.None);
		pool.add(bullet);
	}
}
