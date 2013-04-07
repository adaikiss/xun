package org.adaikiss.xun.android.showcase;

import org.adaikiss.xun.android.showcase.form.FormActivity;
import org.adaikiss.xun.android.showcase.layout.AbsoluteLayoutActivity;
import org.adaikiss.xun.android.showcase.layout.FrameLayoutActivity;
import org.adaikiss.xun.android.showcase.layout.LinearLayoutActivity;
import org.adaikiss.xun.android.showcase.layout.RelativeLayoutActivity;
import org.adaikiss.xun.android.showcase.layout.TableLayoutActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HelloAndroidActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
//		ImageView HelloWorldImageView = new ImageView(this);
		/**Set the ImageView to helloworld.png */
//		HelloWorldImageView.setImageResource(R.drawable.helloworld);
		/**Set the ContentView to the ImageView */
//		setContentView(HelloWorldImageView);
		OnClickListener btnClickListener = new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Button btn = (Button)arg0;
				switch(btn.getId()){
				case R.id.linearLayoutBtn:
					forward(LinearLayoutActivity.class);
					break;
				case R.id.frameLayoutBtn:
					forward(FrameLayoutActivity.class);
					break;
				case R.id.tableLayoutBtn:
					forward(TableLayoutActivity.class);
					break;
				case R.id.absoluteLayoutBtn:
					forward(AbsoluteLayoutActivity.class);
					break;
				case R.id.relativeLayoutBtn:
					forward(RelativeLayoutActivity.class);
					break;
				case R.id.formBtn:
					forward(FormActivity.class);
					break;
				case R.id.checkedTextViewBtn:
					forward(CheckedTextViewActivity.class);
					break;
				case R.id.listViewBtn:
					forward(ListViewActivity.class);
					break;
				}
				//Toast.makeText(HelloAndroidActivity.this, "点击了按钮" + btn.getId() + btn.getImeActionId(), Toast.LENGTH_LONG).show();
			}
			
		};
		Button linearLayoutBtn = (Button)findViewById(R.id.linearLayoutBtn);
		Button frameLayoutBtn = (Button)findViewById(R.id.frameLayoutBtn);
		Button tableLayoutBtn = (Button)findViewById(R.id.tableLayoutBtn);
		Button absoluteLayoutBtn = (Button)findViewById(R.id.absoluteLayoutBtn);
		Button relativeLayoutBtn = (Button)findViewById(R.id.relativeLayoutBtn);
		Button formBtn = (Button)findViewById(R.id.formBtn);
		Button checkedTextViewBtn = (Button)findViewById(R.id.checkedTextViewBtn);
		Button listViewBtn = (Button)findViewById(R.id.listViewBtn);
		
		linearLayoutBtn.setOnClickListener(btnClickListener);
		frameLayoutBtn.setOnClickListener(btnClickListener);
		tableLayoutBtn.setOnClickListener(btnClickListener);
		absoluteLayoutBtn.setOnClickListener(btnClickListener);
		relativeLayoutBtn.setOnClickListener(btnClickListener);
		formBtn.setOnClickListener(btnClickListener);
		checkedTextViewBtn.setOnClickListener(btnClickListener);
		listViewBtn.setOnClickListener(btnClickListener);
	}

	private void forward(Class<?> activity) {
		Intent intent = new Intent();
		intent.setClass(HelloAndroidActivity.this, activity);
		startActivity(intent);
	}
}
