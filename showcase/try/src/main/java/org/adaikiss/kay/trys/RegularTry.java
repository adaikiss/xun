/**
 * 
 */
package org.adaikiss.kay.trys;


/**
 * @author Administrator
 *
 */
public class RegularTry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		System.out.println(Pattern.matches("([1-7],)*([1-7])?", "1,2,3,4,5,6"));
//		System.out.println("1".matches("([1-7],)*([1-7])?"));
//		System.out.println("as/sdf".matches("^[^(.+\\.[a-zA-Z0-9]+)]+$"));
//		System.out.println("长阳人".matches("^((?!((长沙)|(衡阳))).)*$"));
//		System.out.println("aaabb".matches("^(?!aa).*$"));
//		System.out.println("[0].resources".matches("\\[\\d+\\]\\.resources"));
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><smil><head><meta name=\"base\" content=\"/obeps/\" /><layout><root-layout width=\"480\" height=\"640\" background-color=\"RGB(255,255,255)\" /><region id=\"Region_Id0\" left=\"0\" top=\"0\" width=\"480\" height=\"640\" z-index=\"0\" /></layout></head><body><seq><img src=\"http://61.130.247.175:8080/portalapi/imagedownload?isTransfer=0&amp;type=content&amp;key=1000005286772.jpg\" region=\"Region_Id0\" /></seq></body></smil>";
		String expr = ".*(<img [^>]*/?>).*";
		System.out.println(xml.matches(expr));
		System.out.println(xml.replaceAll(expr, "$1"));
		System.out.println("insert into t_content_copyright (metadata_id, copyright_id, copyright_begin, copyright_end) values(123456, 12342123123, to_date('2011-12-12', 'YYYY-MM-DD'), to_date('2011-12-21', 'YYYY-MM-DD');\n".matches("insert into t_content_copyright \\(metadata_id, copyright_id, copyright_begin, copyright_end\\) values\\((\\d+), (\\d+), to_date\\('(\\d{4}-\\d{2}-\\d{2})', 'YYYY-MM-DD'\\), to_date\\('(\\d{4}-\\d{2}-\\d{2})', 'YYYY-MM-DD'\\);\n"));
		System.out.println("bb".matches("^(?!(ab)).*$"));
	}

}
