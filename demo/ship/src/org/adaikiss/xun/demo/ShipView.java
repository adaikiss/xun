/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.adaikiss.xun.demo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ShipView extends SurfaceView implements SurfaceHolder.Callback {

	private boolean mRun;

	private ShipThread thread;

	public ShipView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setFocusable(true);
		// register our interest in hearing about changes to our surface
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
     // create thread only; it's started in surfaceCreated()
        // except if used in the layout editor.
        if (isInEditMode() == false) {
            thread = new ShipThread(holder, context, new Handler() {
                public void handleMessage(Message m) {
                }//end handle msg
            });
        }
	}

	public boolean isRunning(){
		return mRun;
	}

	public ShipThread getShipThread(){
		return thread;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		thread.setSurfaceSize(width, height);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		thread.setRunning(false);
		while (retry) {
			try {
				thread.join();
				retry = false;

			} catch (InterruptedException e) {
			}
		}
	}

	class ShipThread extends Thread {
		private SurfaceHolder mSurfaceHolder;
		private Bitmap mBackgroundImageFar;
		private int mBGFarMoveY = 0;
		private int mCanvasHeight = 1;
        private int mCanvasWidth = 1;
        private int speed = 2;

        private int mShipIndex = 0;
        private Bitmap[] mShipFlying = new Bitmap[4];
        private int mShipXMin = 10;
        private int mShipXMax = 40;
        private int mShipX;
        private int mShipY;

		Resources mRes;

		public ShipThread(SurfaceHolder surfaceHolder, Context context, Handler handler) {
			mSurfaceHolder = surfaceHolder;
			mRes = context.getResources();
			mBackgroundImageFar = BitmapFactory.decodeResource(mRes, R.drawable.beehive);
			mShipFlying[0] = BitmapFactory.decodeResource(mRes, R.drawable.ship);
			mShipFlying[0] = Bitmap.createScaledBitmap(mShipFlying[0], 64, 64, false);
            mShipFlying[1] = BitmapFactory.decodeResource(mRes, R.drawable.ship);
            mShipFlying[1] = Bitmap.createScaledBitmap(mShipFlying[1], 64, 64, false);
            mShipFlying[2] = BitmapFactory.decodeResource(mRes, R.drawable.ship);
            mShipFlying[2] = Bitmap.createScaledBitmap(mShipFlying[2], 64, 64, false);
            mShipFlying[3] = BitmapFactory.decodeResource(mRes, R.drawable.ship);
            mShipFlying[3] = Bitmap.createScaledBitmap(mShipFlying[3], 64, 64, false);
		}

		public void moveLeft(){
			mShipX = Math.max(mShipX - 2, mShipXMin);
		}
	
		public void moveRight(){
			mShipX = Math.min(mShipX + 2, mShipXMax);
		}
		public void speedUp(){
			
		}
		public void speedDown(){
			
		}
		
		public void setRunning(boolean b) {
			mRun = b;
		}

		public void setSurfaceSize(int width, int height) {
			// synchronized to make sure these all change atomically
			synchronized (mSurfaceHolder) {
				mCanvasWidth = width;
				mCanvasHeight = height;
				// don't forget to resize the background image
//				mBackgroundImageFar = Bitmap.createScaledBitmap(mBackgroundImageFar, width, height, true);
				mShipXMax = width - mShipFlying[0].getWidth() - 10;
				mShipY = height - mShipFlying[0].getHeight();
				mShipX = (width - mShipFlying[0].getWidth()) / 2;
			}
		}

		public void run() {
			while (mRun) {
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
			doDrawRunning(c);
		}

		public void doDrawRunning(Canvas canvas) {
//			mBGFarMoveY = mBGFarMoveY - 1;
			mBGFarMoveY = mBGFarMoveY + speed;
//			int newFarY = mBackgroundImageFar.getHeight() - (-mBGFarMoveY);
			int newFarY = -mBackgroundImageFar.getHeight() - (-mBGFarMoveY);
			// if we have scrolled all the way, reset to start
			if (newFarY >= 0) {
//				mBGFarMoveY = 0;
				mBGFarMoveY = mCanvasHeight - mBackgroundImageFar.getHeight();
				// only need one draw
				canvas.drawBitmap(mBackgroundImageFar, 0, mBGFarMoveY, null);

			} else {
				// need to draw original and wrap
				canvas.drawBitmap(mBackgroundImageFar, 0, mBGFarMoveY, null);
				canvas.drawBitmap(mBackgroundImageFar, 0, newFarY, null);
			}
			//draw ship
			mShipIndex++;

            if (mShipIndex == 4)
                mShipIndex = 0;

            // draw the space ship in the same lane as the next asteroid
            canvas.drawBitmap(mShipFlying[mShipIndex], mShipX, mShipY, null);
		}
	}
}
