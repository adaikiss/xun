package org.adaikiss.xun.demo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**  
 * <pre>
 * Copyright:	Copyright (c)2009  
 * Company:		杭州龙驹信息科技
 * Author:		HuLingwei
 * Create at:	2013-7-30 上午9:36:27  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */

public class BattleView extends SurfaceView implements SurfaceHolder.Callback {
	private boolean mRun;

	private BattleThread thread;

	public BattleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setFocusable(true);
		// register our interest in hearing about changes to our surface
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
     // create thread only; it's started in surfaceCreated()
        // except if used in the layout editor.
        if (isInEditMode() == false) {
            thread = new BattleThread(this, holder, context, new Handler() {
                public void handleMessage(Message m) {
                }//end handle msg
            });
        }
	}

	public boolean isRunning(){
		return mRun;
	}

	public void setRunning(boolean b) {
		mRun = b;
	}

	public BattleThread getBattleThread(){
		return thread;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
//		setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		thread.setSurfaceSize(width, height);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		setRunning(false);
		while (retry) {
			try {
				thread.join();
				retry = false;

			} catch (InterruptedException e) {
			}
		}
	}
}
