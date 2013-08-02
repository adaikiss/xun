package org.adaikiss.xun.catapult;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.jbox2d.collision.AABB;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.MouseJoint;
import org.jbox2d.dynamics.joints.MouseJointDef;
import org.jbox2d.dynamics.joints.RevoluteJoint;
import org.jbox2d.dynamics.joints.RevoluteJointDef;
import org.jbox2d.dynamics.joints.WeldJoint;
import org.jbox2d.dynamics.joints.WeldJointDef;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

/**
 * <pre>
 * Copyright:	Copyright (c)2009  
 * Company:		杭州龙驹信息科技
 * Author:		HuLingwei
 * Create at:	2013-8-1 下午2:23:23  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------
 * 
 * </pre>
 */

public class MainView extends SurfaceView implements Callback, Runnable {

	public static final double DEGTORAD = Math.PI / 180;
	private Resources res;
	private SurfaceHolder sfh;
	private Thread th;
	private Canvas canvas;
	private Paint paint;

	private Bitmap background_top;
	private Bitmap background_bottom;
	private Bitmap squirrel_1;
	private Bitmap squirrel_2;
	private Bitmap catapult_base_1;
	private Bitmap catapult_base_2;

	private int screenW;
	private int screenH;
	private float gameWidth;

	private boolean thread_flag;

	/**
	 * 当按下触摸屏时，触摸点在当前游戏场景中的x轴位置
	 */
	private float position_X;
	/**
	 * 移动触摸屏时，移动的偏移量
	 */
	private float move_X;

	/**
	 * 地面的高度
	 */
	private static float FLOOR_HEIGHT = 82f;

	// 添加一个世界需要的变量--->>>

	/**
	 * 屏幕到现实世界的比例
	 * 30px：1m;为了能够真实的模拟现实世界，在jbox2d中，所有的单位都是以米为标准的。所以需要设定一个屏幕到现实世界的比例
	 * 。在屏幕中30像素代表现实世界中的1米。
	 */
	private final float RATE = 30;
	/**
	 * 物理世界
	 */
	private World world;
	/**
	 * 重力向量对象
	 */
	private Vec2 gravity;
	/**
	 * 物理世界模拟的的频率
	 */
	private float timeStep = 1f / 60f;
	/**
	 * 迭代值，迭代越大模拟越精确，但性能越低
	 */
	private int iterations = 10;

	/**
	 * 地面
	 */
	private Body floorBody;

	private Body catapultArmBody;
	private Bitmap catapultArmBitmap;

	/**
	 * 旋转关节
	 */
	private RevoluteJointDef rjd;

	/**
	 * 触摸回调
	 */
	private final TouchCallback callback = new TouchCallback();

	// 创建鼠标关节
	private MouseJoint mj;// 首先创建鼠标关节对象
	private Vec2 touchPoint; // 手指触摸屏幕点的位置
	private final AABB queryAABB = new AABB(); // 物理模拟世界的范围
	private Body curBody; // 手指触摸的对象
	private boolean withMouse = false; // 判断是否创建了鼠标关节

	// 用于存放子弹对象的集合
	private Set<Body> bullets = new HashSet<Body>();
	private final int BulletCount = 4; // 子弹数量
	private Bitmap bulletBitmap; // 子弹图片

	private WeldJoint wj;// 焊接关节

	/**
	 * 推动发射器标示
	 */
	private boolean catapult_flag = false;

	/**
	 * 子弹是否处于移动状态
	 */
	private boolean moveing_flag = false;

	/**
	 * 创建存放目标的集合
	 */
	private Set<Body> targets = new HashSet<Body>();

	private Bitmap target1Bitmap;
	private Bitmap target2Bitmap;
	private Bitmap target3Bitmap;
	private Bitmap target4Bitmap;
	private Bitmap target5Bitmap;

	/**
	 * @param context
	 */
	public MainView(Context context) {
		super(context);
		res = this.getResources();
		sfh = this.getHolder();
		sfh.addCallback(this);
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.RED);
		this.setKeepScreenOn(true);// 保持屏幕常亮

		// 背景图片
		background_top = BitmapFactory.decodeResource(res, R.drawable.bg);
		background_bottom = BitmapFactory.decodeResource(res, R.drawable.fg);
		// 两个松鼠图片
		squirrel_1 = BitmapFactory.decodeResource(res, R.drawable.squirrel_1);
		squirrel_2 = BitmapFactory.decodeResource(res, R.drawable.squirrel_2);
		// 发射器底座图片
		catapult_base_1 = BitmapFactory.decodeResource(res, R.drawable.catapult_base_1);
		catapult_base_2 = BitmapFactory.decodeResource(res, R.drawable.catapult_base_2);

		// 子弹
		bulletBitmap = BitmapFactory.decodeResource(res, R.drawable.acorn);

		//目标
		target1Bitmap = BitmapFactory.decodeResource(res, R.drawable.brick_1);
		target2Bitmap = BitmapFactory.decodeResource(res, R.drawable.brick_2);
		target3Bitmap = BitmapFactory.decodeResource(res, R.drawable.brick_3);
		target4Bitmap = BitmapFactory.decodeResource(res, R.drawable.head_cat);
		target5Bitmap = BitmapFactory.decodeResource(res, R.drawable.head_dog);

		// 游戏画布宽度
		gameWidth = background_top.getWidth();

		FLOOR_HEIGHT = background_bottom.getHeight() * 4 / 5;
		setClickable(true);

		// --添加一个物理世界--->>
		// 模拟显示世界的重力 横向重力为0，纵向重力为10
		gravity = new Vec2(0f, 10f);
		// 为游戏世界添加重力，并且使世界处于睡眠状态，当物体进行改变时，在唤醒世界。这样可以很好的节省系统资源。
		world = new World(gravity);

		catapultArmBitmap = BitmapFactory.decodeResource(res, R.drawable.catapult_arm);
	}

	@Override
	public void run() {
		while (thread_flag) {
			logic();
			draw();
		}
	}

	private void draw() {
		try {
			canvas = sfh.lockCanvas(); // 得到一个canvas实例
			if (canvas != null) {
				canvas.drawColor(Color.WHITE);// 刷屏
				canvas.drawBitmap(background_top, 0 - move_X, 0, paint);

				canvas.drawBitmap(catapult_base_2, 260 - move_X, screenH - FLOOR_HEIGHT - catapult_base_2.getHeight(),
						paint);
				// 发射器
				((CatapultArm) catapultArmBody.m_userData).draw(canvas, paint, move_X);

				canvas.drawBitmap(catapult_base_1, 265 - move_X, screenH - FLOOR_HEIGHT - catapult_base_1.getHeight(),
						paint);

				canvas.drawBitmap(squirrel_1, 50 - move_X, screenH - FLOOR_HEIGHT - squirrel_1.getHeight(), paint);
				canvas.drawBitmap(squirrel_2, 350 - move_X, screenH - FLOOR_HEIGHT - squirrel_2.getHeight(), paint);

				for(Body target : targets){
					((Target)target.m_userData).draw(canvas, paint, move_X);
				}

				// 子弹
				for (Body body : bullets) {
					((Bullet) body.m_userData).draw(canvas, paint, move_X);
				}

				canvas.drawBitmap(background_bottom, 0 - move_X, screenH - background_bottom.getHeight(), paint);

			}
		} catch (Exception ex) {
		} finally {
			if (canvas != null)
				sfh.unlockCanvasAndPost(canvas); // 将画好的画布提交
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		screenH = this.getHeight();
		screenW = this.getWidth();
		thread_flag = true;

		floorBody = createFloorBody(0, screenH - FLOOR_HEIGHT, gameWidth, FLOOR_HEIGHT);
		catapultArmBody = createCatapultBody(catapultArmBitmap, 290,
				screenH - FLOOR_HEIGHT - catapultArmBitmap.getHeight() - catapult_base_2.getHeight() / 2,
				catapultArmBitmap.getWidth(), catapultArmBitmap.getHeight());
		targets.add(createTargetBody(target1Bitmap, 400, screenH - FLOOR_HEIGHT - target1Bitmap.getHeight()));
		targets.add(createTargetBody(target1Bitmap, 700, screenH - FLOOR_HEIGHT - target1Bitmap.getHeight()));
		targets.add(createTargetBody(target2Bitmap, 500, screenH - FLOOR_HEIGHT - target1Bitmap.getHeight()));
		targets.add(createTargetBody(target2Bitmap, 500, screenH - FLOOR_HEIGHT - target1Bitmap.getHeight() * 2));
		targets.add(createTargetBody(target2Bitmap, 500, screenH - FLOOR_HEIGHT - target1Bitmap.getHeight() * 3));
		targets.add(createTargetBody(target2Bitmap, 500, screenH - FLOOR_HEIGHT - target1Bitmap.getHeight() * 4));
		createRevoluteJoint();
		createBulletBody(3);
		wj = createWeldJoint();

		th = new Thread(this); // 创建线程
		th.start(); // 开启线程
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

	// 然后分别在onTouchEvent方法中，实现触屏 按下，抬起，移动事件。
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		touchPoint = new Vec2((event.getX() + move_X) / RATE, event.getY() / RATE);
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			position_X = move_X + event.getX();

			if (mj != null) { // 如果已经创建了鼠标关节，就直接返回
				return true;
			}

			// 得到当前模拟世界中的最小范围
			queryAABB.lowerBound.set(touchPoint.x - .001f, touchPoint.y - .001f);
			// 得到当前模拟世界中的最大范围
			queryAABB.upperBound.set(touchPoint.x + .001f, touchPoint.y + .001f);
			callback.point.set(touchPoint);
			callback.fixture = null;
			world.queryAABB(callback, queryAABB);

			if (callback.fixture != null) {
				curBody = callback.fixture.getBody();
				if (curBody.m_userData instanceof CatapultArm) {
					withMouse = true;
					MouseJointDef def = new MouseJointDef();
					def.bodyA = floorBody;
					def.bodyB = curBody;
					def.target.set(touchPoint);
					def.maxForce = 3000; // 鼠标关节的马力
					mj = (MouseJoint) world.createJoint(def);
					curBody.setAwake(true);
				}
			}
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			if (withMouse) {
				withMouse = false;
				// 同时删除鼠标关节
				if (mj != null) {
					world.destroyJoint(mj);
					mj = null;
				}
				moveing_flag = true;
			}
		} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			if (mj != null) {
				mj.setTarget(touchPoint); // 设置鼠标关节的变化位置
			}
			if (!withMouse) {
				move_X = position_X - event.getX();
				move_X = move_X < 0 ? 0 : (move_X > gameWidth - screenW ? gameWidth - screenW : move_X);
			}
		}
		return super.onTouchEvent(event);
	}

	public Body createFloorBody(float x, float y, float width, float height) {

		PolygonShape ps = new PolygonShape();// 创建形状
		ps.setAsBox(width / 2 / RATE, height / 2 / RATE);// 形状为一个矩形

		FixtureDef fd = new FixtureDef();// 创建夹具
		fd.shape = ps;// 为夹具绑定形状
		fd.density = 0;// 设置密度,当密度为0时，这个物体为静止状态。
		fd.friction = 0.8f;// 设置摩擦力
		fd.restitution = 0.3f;// 设置恢复力

		BodyDef bd = new BodyDef();// 创建刚体
		bd.position.set((x + width / 2) / RATE, (y + height / 2) / RATE);// 创建刚体范围
		bd.type = BodyType.STATIC;// 设置刚体类型，设置为静态刚体

		Body body = world.createBody(bd);// 创建物体并制定刚体
		body.createFixture(fd);// 为物体制定夹具

		return body;
	}

	public Body createCatapultBody(Bitmap bitmap, float x, float y, float width, float height) {
		PolygonShape ps = new PolygonShape();
		ps.setAsBox(width / 2 / RATE, (height / 2 - 40) / RATE);

		FixtureDef fd = new FixtureDef();
		fd.shape = ps;
		fd.density = 1;
		fd.friction = 0.8f;
		fd.restitution = 0.3f;

		BodyDef bd = new BodyDef();
		bd.position.set((x + width / 2) / RATE, (y + height / 2) / RATE);
		bd.type = BodyType.DYNAMIC;

		Body body = world.createBody(bd);
		// put extra
		body.m_userData = new CatapultArm(bitmap, x, y, width, height, 0);
		body.createFixture(fd);

		return body;
	}

	/**
	 * 改变物体参数
	 */
	public void logic() {
		// 唤醒世界
		world.step(timeStep, iterations, 6);
		// 物体位置参数
		Vec2 positionBuller;
		// 世界中的所有物体的集合
		Body body = world.getBodyList();

		// 遍历所有的物体
		for (int i = 0; i < world.getBodyCount(); i++) {
			// 获取当前物体的位置
			positionBuller = body.getPosition();
			// 判断当前物体是否为发射器
			if ((body.m_userData) instanceof CatapultArm) {
				// 获取发射器对象
				CatapultArm catapultArm = (CatapultArm) body.m_userData;
				// 设置发射器的x轴
				catapultArm.setX(positionBuller.x * RATE - catapultArm.width / 2);
				// 设置发射器的y轴
				catapultArm.setY(positionBuller.y * RATE - catapultArm.height / 2);
				// 设置发射器的角度
				catapultArm.setAngle((float) (body.getAngle() * 180 / Math.PI));
			}
			if ((body.m_userData) instanceof Bullet) {
				Bullet bullet = (Bullet) body.m_userData;
				bullet.setX(positionBuller.x * RATE - bullet.width / 2);
				bullet.setY(positionBuller.y * RATE - bullet.height / 2);

				if (moveing_flag)// 判断子弹是否处于运动状态
				{
					// 获取到移动的偏移量。(ScreenW/2.0f用于使子弹处于屏幕中间)
					float ww = this.bullets.iterator().next().getPosition().x * RATE - screenW / 2.0f;
					// 防止镜头移出游戏场景
					move_X = (ww < 5) ? 0 : (ww > 580) ? 586 : ww;
				}
			}
			if((body.m_userData) instanceof Target){
				Target target = (Target) body.m_userData;
				target.setX(positionBuller.x * RATE - target.width / 2);
				target.setY(positionBuller.y * RATE - target.height / 2);
			}
			// 遍历下一个对象
			body = body.m_next;
		}

		if (catapultArmBody.getAngle() < -(float) (30f * Math.PI / 180)) {
			catapult_flag = true;
		}

		if (wj != null && catapult_flag) {
			if (catapultArmBody.getAngle() >= -(float) (10f * Math.PI / 180)) {
				world.destroyJoint(wj);// 销毁焊接关节
				wj = null;
			}
		}

		Iterator<Body> it = bullets.iterator();
		if (bullets.iterator().hasNext())// 首先判断时候还有为发射的子弹
		{
			Body next = it.next();
			// 只要发射的子弹静止或者超出了场景范围就执行
			if (!next.isAwake() || next.getPosition().x * RATE > background_bottom.getWidth()) {
				moveing_flag = false;
			}
		}

		// 子弹续膛
		if (wj == null && !bullets.isEmpty() && !moveing_flag) {
			it = bullets.iterator();
			Body b = it.next();
			// 销毁已经发出去的子弹
			world.destroyBody(b);
			bullets.remove(b);
			catapult_flag = false;
			wj = createWeldJoint();// 创建新的焊接关节
		}
	}

	public RevoluteJoint createRevoluteJoint() {
		// 创建一个旋转关节的数据实例
		rjd = new RevoluteJointDef();
		// 限制角度
		rjd.enableLimit = true;
		// 逆时针为增加
		rjd.lowerAngle = (float) (10f * DEGTORAD);
		rjd.upperAngle = (float) (70f * DEGTORAD);
		// 初始化旋转关节数据
		rjd.initialize(
				catapultArmBody,
				floorBody,
				new Vec2(catapultArmBody.getWorldCenter().x, catapultArmBody.getWorldCenter().y
						+ catapultArmBitmap.getHeight() * 1 / 2 / RATE));

		rjd.maxMotorTorque = 2000;// 马达的预期最大扭矩
		rjd.motorSpeed = -400;// 马达最终扭矩
		rjd.enableMotor = true;// 启动马达

		// 利用world创建一个旋转关节
		RevoluteJoint rj = (RevoluteJoint) world.createJoint(rjd);
		return rj;
	}

	public Body createTargetBody(Bitmap bitmap, float x, float y) {
		float width = bitmap.getWidth();
		float height = bitmap.getHeight();
		PolygonShape ps = new PolygonShape();
		ps.setAsBox(width / 2 / RATE, (height / 2 - 40) / RATE);


		BodyDef bd = new BodyDef();
		bd.position.set((x + width / 2) / RATE, (y + height / 2) / RATE);
		bd.type = BodyType.DYNAMIC;

		Body body = world.createBody(bd);
		// put extra
		body.m_userData = new Target(bitmap, x, y, width, height, 0);

		return body;
	}

	/**
	 * 创建子弹
	 * 
	 * @param count
	 */
	public void createBulletBody(int count) {
		float pos = 62.0f; // 子弹距离场景左端的位置
		if (count > 0) {
			float delta = 50f; // 子弹之间的间隔

			for (int i = 0; i < count; i++, pos += delta) {
				PolygonShape pd = new PolygonShape();

				// 创建一个多边形的物体
				Vec2[] vec2s = new Vec2[6];
				vec2s[0] = new Vec2(-0.6f, 0.66f);
				vec2s[1] = new Vec2(-0.8f, -0.2f);
				vec2s[2] = new Vec2(-0.28f, -0.88f);
				vec2s[3] = new Vec2(0.4f, -0.6f);
				vec2s[4] = new Vec2(0.88f, -0.1f);
				vec2s[5] = new Vec2(0.4f, 0.82f);
				pd.set(vec2s, 6);

				FixtureDef fd = new FixtureDef();
				fd.shape = pd;
				fd.density = 1;
				fd.friction = 0.8f;
				fd.restitution = 0.3f;

				BodyDef db = new BodyDef();
				db.position.set(pos / RATE, (screenH - FLOOR_HEIGHT - 50f) / RATE);
				db.type = BodyType.DYNAMIC;
				db.bullet = true; // 表示这是个高速运转的物体，需要精细的模拟

				Body body = world.createBody(db);
				body.m_userData = new Bullet(bulletBitmap, pos, FLOOR_HEIGHT + 15.0f, 30, 30, 0);
				body.createFixture(fd);

				bullets.add(body);
			}
		}
	}

	// 创建焊接关节
	public WeldJoint createWeldJoint() {
		Body body;
		Iterator<Body> aIterator = bullets.iterator(); // 使用迭代器取出set集合中的数据
		while (aIterator.hasNext()) { // 判断时候还有数据
			body = aIterator.next(); // 获取下一个数据，第一次是获取第一个数据
			body.setTransform(
					new Vec2((360f / RATE), (screenH - FLOOR_HEIGHT - bulletBitmap.getHeight() - 287) / RATE), 0);
			WeldJointDef wd = new WeldJointDef();
			wd.initialize(body, catapultArmBody, catapultArmBody.getWorldCenter());
			return (WeldJoint) world.createJoint(wd);
		}
		return null;
	}
}
