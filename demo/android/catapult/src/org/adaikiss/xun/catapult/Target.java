package org.adaikiss.xun.catapult;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Target extends AbstractBody {

	public Target(Bitmap bitmap, float x, float y, float width, float height, float angle) {
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.angle = angle;
	}

	@Override
	public void draw(Canvas canvas, Paint paint, float w) {
		// TODO Auto-generated method stub

	}

}
