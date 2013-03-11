package org.adaikiss.xun.android.showcase;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class HelloAndroidActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ImageView HelloWorldImageView = new ImageView(this);
		/**Set the ImageView to helloworld.png */
		HelloWorldImageView.setImageResource(R.drawable.helloworld);
		/**Set the ContentView to the ImageView */
		setContentView(HelloWorldImageView);
	}

}
