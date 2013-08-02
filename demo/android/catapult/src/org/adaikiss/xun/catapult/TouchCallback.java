package org.adaikiss.xun.catapult;

import org.jbox2d.callbacks.QueryCallback;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;

public class TouchCallback implements QueryCallback {

	public final Vec2 point;
	public Fixture fixture;

	public TouchCallback() {
		point = new Vec2();
		fixture = null;
	}

	public boolean reportFixture(Fixture argFixture) {
		Body body = argFixture.getBody();
		// 获取动态的物体
		if (body.getType() == BodyType.DYNAMIC) {
			// 当前触摸点是否有物体
			boolean inside = argFixture.testPoint(point);
			if (inside) {
				fixture = argFixture;
				return false;
			}
		}
		return true;
	}

}
