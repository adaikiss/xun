package org.adaikiss.xun.catapult;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * <pre>
 * Copyright:	Copyright (c)2009  
 * Company:		杭州龙驹信息科技
 * Author:		HuLingwei
 * Create at:	2013-8-1 下午3:30:02  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------
 * 
 * </pre>
 */

public class CatapultArm extends AbstractBody {

	public CatapultArm(Bitmap bitmap, float x, float y, float width, float height, float angle) {
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.angle = angle;
	}

	@Override
	public void draw(Canvas canvas, Paint paint, float position_X) {
		/*
		 * canvas.save();和canvas.restore();是两个相互匹配出现的，作用是用来保存画布的状态和取出保存的状态的。这里稍微解释一下
		 * ， 当我们对画布进行旋转，缩放，平移等操作的时候其实我们是想对特定的元素进行操作，比如图片，一个矩形等，
		 * 但是当你用canvas的方法来进行这些操作的时候
		 * ，其实是对整个画布进行了操作，那么之后在画布上的元素都会受到影响，所以我们在操作之前调用canvas
		 * .save()来保存画布当前的状态，当操作之后取出之前保存过的状态，这样就不会对其他的元素进行影响*
		 */
		canvas.save();
		canvas.rotate(this.angle, x - position_X + width / 2, y + height / 2);
		canvas.drawBitmap(this.bitmap, this.x - position_X, this.y, paint);
		canvas.restore();
	}

}
