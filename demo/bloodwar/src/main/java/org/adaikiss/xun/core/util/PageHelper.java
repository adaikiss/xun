/**
 * 
 */
package org.adaikiss.xun.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author hlw
 *
 */
public class PageHelper {

	public static final String PAGE_PREFIX = "page_";

	/**
	 * @param params
	 * @return
	 */
	public static PageRequest buildFromMap(Map<String, TemplateModel> params) throws TemplateException{
		int page = 0;
		int size = 20;
		List<Order> orders = new ArrayList<Order>(5);
		for(Map.Entry<String, TemplateModel> entry : params.entrySet()){
			String key = (String)entry.getKey();
			if(!key.startsWith(PAGE_PREFIX)){
				continue;
			}
			String name = key.substring(5);
			if(name.equals("page")){
				page = FreemarkerUtil.parseInteger(key, params);
				continue;
			}
			if(name.equals("size")){
				size = FreemarkerUtil.parseInteger(key, params);
				continue;
			}
			if(name.startsWith("sort_")){
				String n = name.substring(5);
				String dir = FreemarkerUtil.parseString(key, params, "ASC");
				orders.add(new Order(Direction.fromString(dir.toUpperCase()), n));
				continue;
			}
		}
		PageRequest pageRequest = new PageRequest(page, size, orders.size() > 0?new Sort(orders) : null);
		return pageRequest;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
