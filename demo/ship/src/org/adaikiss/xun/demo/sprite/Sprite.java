package org.adaikiss.xun.demo.sprite;

import android.graphics.Canvas;

/**  
 * <pre>
 * Copyright:	Copyright (c)2009  
 * Company:		杭州龙驹信息科技
 * Author:		HuLingwei
 * Create at:	2013-7-30 上午9:12:13  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */

public abstract class Sprite {
	public abstract void tick(Canvas canvas);

	public void resize(int width, int height){
		
	}

	public abstract int[] getRange();

	public boolean isCollided(Sprite sprite){
		int[] own = getRange();
		int[] s = sprite.getRange();
		return s[0] >= own[0] && s[1] >= own[1] && s[0] + s[2] <= own[0] + own[2] && (s[1] + s[3]) <= own[1] + own[3];
	}
}
