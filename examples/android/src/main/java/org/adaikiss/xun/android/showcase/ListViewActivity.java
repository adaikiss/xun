/**
 * 
 */
package org.adaikiss.xun.android.showcase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

/**
 * @author hlw
 * 
 */
public class ListViewActivity extends ListActivity {

	private List<Map<String, Object>> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view);
		data = getData();
		final SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.list_view_item, new String[] { "img", "title",
						"detail" }, new int[] { R.id.list_view_item_img,
						R.id.list_view_item_title, R.id.list_view_item_detail });
		setListAdapter(adapter);
		Button btn = (Button)findViewById(R.id.list_view_btn);
		btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				ListView lv = getListView();
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < lv.getChildCount();i++){
					View item = lv.getChildAt(i);
					CheckBox checkBox = (CheckBox)item.findViewById(R.id.list_view_item_checkbox);
					if(checkBox.isChecked()){
						sb.append(((Map<String, Object>)adapter.getItem(i)).get("title")).append(" ");
					}
				}
				Toast.makeText(ListViewActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
			}
			
		});
	}

	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		Map<String, Object> item1 = new HashMap<String, Object>();
		item1.put("img", R.drawable.loin);
		item1.put("title", "Loin");
		item1.put("detail", "Loin is ...");
		data.add(item1);
		Map<String, Object> item2 = new HashMap<String, Object>();
		item2.put("img", R.drawable.tiny);
		item2.put("title", "Tiny");
		item2.put("detail", "Tiny is ...");
		data.add(item2);
		Map<String, Object> item3 = new HashMap<String, Object>();
		item3.put("img", R.drawable.storm);
		item3.put("title", "Storm");
		item3.put("detail", "Storm is ...");
		data.add(item3);
		return data;
	}
}
