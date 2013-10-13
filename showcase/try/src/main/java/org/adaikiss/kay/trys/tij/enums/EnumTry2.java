/**
 * 下午05:17:28
 */
package org.adaikiss.kay.trys.tij.enums;

/**
 * hlw
 *
 */
public class EnumTry2 {

	enum WEEKDAY{
		Monday{
			@Override
			public String getInfo(){
				return "周一";
			}
			@Override
			public void init(){
				
			}
		},
		Tuesday{
			@Override
			public String getInfo(){
				return "周二";
			}
		},
		Wednesday{
			@Override
			public String getInfo(){
				return "周三";
			}
		},
		Thursday{
			@Override
			public String getInfo(){
				return "周四";
			}
		},
		Friday{
			@Override
			public String getInfo(){
				return "周五";
			}
		},
		Saturday{
			@Override
			public String getInfo(){
				return "周六";
			}
		},
		Sunday{
			@Override
			public String getInfo(){
				return "周日";
			}
		};
		private String info;
		public abstract String getInfo();
		public String info(){
			return info;
		}
		public void init(){
			
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WEEKDAY.Monday.init();
	}

}
