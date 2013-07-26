package org.adaikiss.xun.demo;

import org.adaikiss.xun.demo.ShipView.ShipThread;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
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

public class Ship extends Activity {

	/**
     * Constants for desired direction of moving the snake
     */
    public static int MOVE_LEFT = 0;
    public static int MOVE_UP = 1;
    public static int MOVE_DOWN = 2;
    public static int MOVE_RIGHT = 3;

	private ShipView mShipView;
	private ShipThread mShipThread;
	private View mArrayContainer;
	private View mImageUp;
	private View mImageLeft;
	private View mImageRight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mArrayContainer = findViewById(R.id.arrowContainer);
		mShipView = (ShipView)findViewById(R.id.ship_view);
		mImageUp = findViewById(R.id.imageUp);
		mImageLeft = findViewById(R.id.imageLeft);
		mImageRight = findViewById(R.id.imageRight);
		mArrayContainer.setVisibility(View.VISIBLE);
		mShipThread = mShipView.getShipThread();

		mShipView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mShipView.isRunning()) {
                    // Normalize x,y between 0 and 1
                    float x = event.getX() / v.getWidth();
                    float y = event.getY() / v.getHeight();

                    // Direction will be [0,1,2,3] depending on quadrant
                    int direction = 0;
                    direction = (x > y) ? 1 : 0;
                    direction |= (x > 1 - y) ? 2 : 0;

                    if(direction == MOVE_LEFT){
                    	mShipThread.moveLeft();
                    	return true;
                    }
                    if(direction == MOVE_RIGHT){
                    	mShipThread.moveRight();
                    	return true;
                    }
                    if(direction == MOVE_UP){
                    	mShipThread.speedUp();
                    	return true;
                    }
                    if(direction == MOVE_DOWN){
                    	mShipThread.speedDown();
                    	return true;
                    }
                }
                return false;
            }
        });
	}

}
