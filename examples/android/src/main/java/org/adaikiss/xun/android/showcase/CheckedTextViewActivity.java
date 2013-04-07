/**
 * 
 */
package org.adaikiss.xun.android.showcase;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.Toast;

/**
 * @author hlw
 *
 */
public class CheckedTextViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checked_text_view);
		Button btn = (Button)findViewById(R.id.checked_text_view_submit);
		final List<CheckedTextView> views = new ArrayList<CheckedTextView>(3);
		views.add((CheckedTextView)findViewById(R.id.checked_text_view_loin));
		views.add((CheckedTextView)findViewById(R.id.checked_text_view_tiny));
		views.add((CheckedTextView)findViewById(R.id.checked_text_view_storm));
		OnClickListener listener = new OnClickListener(){

			@Override
			public void onClick(View v) {
				CheckedTextView view = (CheckedTextView)v;
				view.toggle();
			}
			
		};
		for(CheckedTextView view : views){
			view.setClickable(true);
			view.setOnClickListener(listener);
		}
		btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				StringBuilder sb = new StringBuilder();
				sb.append("selected [");
				for(CheckedTextView view : views){
					if(view.isChecked()){
						sb.append(view.getText()).append(", ");
					}
				}
				sb.append("]");
				Toast.makeText(CheckedTextViewActivity.this, sb, Toast.LENGTH_LONG).show();
			}
			
		});
	}

}
