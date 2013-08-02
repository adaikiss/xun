package org.adaikiss.xun.catapult;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Bullet extends AbstractBody {

	public Bullet(Bitmap bitmap, float x, float y, float width, float height, float angle) {
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.angle = angle;
	}

	@Override
	public void draw(Canvas canvas, Paint paint, float position_X) {
		canvas.save();
		canvas.rotate(this.angle, x - position_X + width / 2, y + height / 2);
		canvas.drawBitmap(this.bitmap, this.x - position_X, this.y, paint);
		canvas.restore();
	}

}
