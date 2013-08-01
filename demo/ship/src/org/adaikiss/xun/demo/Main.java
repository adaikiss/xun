package org.adaikiss.xun.demo;

import org.adaikiss.xun.demo.sprite.Ship;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

/**
 * <pre>
 * Copyright:	Copyright (c)2009  
 * Company:		杭州龙驹信息科技
 * Author:		HuLingwei
 * Create at:	2013-6-28 上午10:22:07  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------
 * 
 * </pre>
 */

public class Main extends Activity implements OnClickListener{

	public static Main instance;

	/**
	 * Constants for desired direction of moving the snake
	 */
	public final static int MOVE_LEFT = 0;
	public final static int MOVE_UP = 1;
	public final static int MOVE_DOWN = 2;
	public final static int MOVE_RIGHT = 3;

	private BattleView mBattleView;
	private BattleThread mBattleThread;
	private Ship mShip;
	private View mArrayContainer;
//	private View mImageUp;
//	private View mImageLeft;
//	private View mImageRight;

	private View mOverView;
	private View mStartView;

	public static volatile boolean running = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;
		setContentView(R.layout.main);
		BulletPool.init(this);
		mArrayContainer = findViewById(R.id.arrowContainer);
		mBattleView = (BattleView) findViewById(R.id.ship_view);
//		mImageUp = findViewById(R.id.imageUp);
//		mImageLeft = findViewById(R.id.imageLeft);
//		mImageRight = findViewById(R.id.imageRight);
		mOverView = findViewById(R.id.over);
		mStartView = findViewById(R.id.start);
		mStartView.setOnClickListener(this);
		mArrayContainer.setVisibility(View.VISIBLE);
		mBattleThread = mBattleView.getBattleThread();
		mShip = mBattleThread.getShip();

		mBattleView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.d("", "onTouch!!" + event.getAction());
				if (mBattleView.isRunning()) {
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						// finger press
						Log.d("Touch", "DOWN");
						// Normalize x,y between 0 and 1
						float x = event.getX() / v.getWidth();
						float y = event.getY() / v.getHeight();

						// Direction will be [0,1,2,3] depending on quadrant
						int direction = 0;
						direction = (x > y) ? 1 : 0;
						direction |= (x > 1 - y) ? 2 : 0;
						mShip.setDirection(direction);
						mShip.setMoving(true);
						break;
					case MotionEvent.ACTION_UP:
						// finger release
						Log.d("Touch", "UP");
						mShip.setMoving(false);
						break;
					}
				}
				return true;
			}
		});
		start();
	}

	public void start(){
		running = true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		boolean retry = true;
		running = false;
		while (retry) {
			try {
				if(mShip.getMotionThread() != null){
					mShip.getMotionThread().join();
				}
				retry = false;
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.start:
			mBattleThread.startStage();
			mOverView.setVisibility(View.GONE);
			mStartView.setVisibility(View.GONE);
			break;
		}
	}

	public void over(){
		mBattleView.setRunning(false);
		runOnUiThread(new Runnable(){
			@Override
			public void run(){
				mOverView.setVisibility(View.VISIBLE);
				mStartView.setVisibility(View.VISIBLE);
			}
		});
	}
}
