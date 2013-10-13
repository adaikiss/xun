/**
 * 下午01:53:33
 */
package org.adaikiss.kay.trys;

/**
 * hlw
 *
 */
public class InterfaceViolateTry {
	interface A{
		public String hi();
	}

	interface B{
		public Integer hi();
	}

//	class C implements A, B{
//		@Override
//		public String hi() {
//			return null;
//		}
//	}
	interface D<T>{
		public void hi(T t);
	}

	interface E<T>{
		public void hi(T t);
	}
	interface F<V, K> extends D<V>, E<K>{
		
	}
//	class F implements D<Long>, E<Integer>{
//		@Override
//		public void hi(Long p){
//		}
//	}
	class G implements  D<String>, E<String>{
		@Override
		public void hi(String p){
			
		}
	}
	class P implements D<String>{
		public void hi(String t){
		}
	}
//	class H extends P implements D<Integer>{
//		@Override
//		public void hi(Integer i){
//		}
//	}
	interface Z{
//		private void hello();
		void hi();
	}
}
