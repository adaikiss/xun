package org.adaikiss.xun.catapult;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * <pre>
 * Copyright:	Copyright (c)2009  
 * Company:		杭州龙驹信息科技
 * Author:		HuLingwei
 * Create at:	2013-8-1 下午3:29:15  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------
 * 
 * </pre>
 */

public abstract class AbstractBody {
	protected float x = 0; // x轴
	protected float y = 0; // y轴
	protected float width = 0; // 宽度
	protected float height = 0; // 高度
	protected float angle = 0; // 角度
	protected Bitmap bitmap = null; // 显示图片

	public abstract void draw(Canvas canvas, Paint paint, float w); // 画图

	/**
	 * 设置x轴
	 * @param x
	 */
	public void setX(float x){
		this.x = x;
	}

	/**
	 * 设置y轴
	 * @param y
	 */
	public void setY(float y){
		this.y = y;
	}

	/**
	 * 设置角度
	 * @param angle
	 */
	public void setAngle(float angle){
		this.angle = angle;
	}

	/**
	 * 获取x轴
	 * @return
	 */
	public float getX(){
		return x;
	}

	/**
	 * 获取y轴
	 * @return
	 */
	public float getY(){
		return y;
	}

	/**
	 * 获取宽度
	 * @return
	 */
	public float getWidth(){
		return width;
	}

	/**
	 * 获取高度
	 * @return
	 */
	public float getHeight(){
		return height;
	}
}
