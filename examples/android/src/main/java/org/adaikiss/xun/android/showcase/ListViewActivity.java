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
import android.widget.SimpleAdapter;

/**
 * @author hlw
 * 
 */
public class ListViewActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.list_view);
		SimpleAdapter adapter = new SimpleAdapter(this, getData(),
				R.layout.list_view_item, new String[] { "img", "title",
						"detail" }, new int[] { R.id.list_view_item_img,
						R.id.list_view_item_title, R.id.list_view_item_detail });
		setListAdapter(adapter);
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
