/**
 * 
 */
package org.adaikiss.kay.trys.tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * @author hlw
 *
 */
public class Utils {

	private static DataSource dataSource;

	public static List<List<String>> readExcel(String path, int sheetIndex) throws Exception{
		FileInputStream input = new FileInputStream(path);
		POIFSFileSystem fs = new POIFSFileSystem(input);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(sheetIndex);
		Iterator<Row> rows = (Iterator<Row>)sheet.rowIterator();
		List<List<String>> list = new ArrayList<List<String>>();
		while (rows.hasNext()) {
			Row row = rows.next();
			if(row.getRowNum() == 0){
				continue;
			}
			Iterator<Cell> cells = (Iterator<Cell>)row.cellIterator();
			List<String> cs = new ArrayList<String>();
			while(cells.hasNext()){
				Cell cell = cells.next();
				String value = null;
				switch(cell.getCellType()){
				case Cell.CELL_TYPE_BOOLEAN :
					value = String.valueOf(cell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_NUMERIC :
					value = String.valueOf(cell.getNumericCellValue());
					break;
				case Cell.CELL_TYPE_FORMULA :
					value = cell.getCellFormula();
					break;
				default : value = cell.getStringCellValue();
				}
				cs.add(value);
			}
			list.add(cs);
		}
		input.close();
		return list;
	}

	public static void initDataSource() throws Exception{
		initDataSource(false);
	}

	public static void initDataSource(boolean sy) throws Exception{
		String sConnStr;
		String username;
		String password;
		if(sy){
			sConnStr = "jdbc:oracle:thin:@192.168.9.37:1521:ydpt2";
			username = "ytxt";
			password = "tian$YI";
		}else{
			sConnStr = "jdbc:oracle:thin:@192.168.10.49:1521:jf";
			username = "ytxt";
			password = "ytxt123";
		}
		OracleDataSource ds = new OracleDataSource();
		ds.setURL(sConnStr);
		ds.setUser(username);
		ds.setPassword(password);
		dataSource = ds;
	}

	public static void downloadCEB(String bookId) throws Exception{
		String sql = "select f.file_content, t.name, t.author_name from t_content_file f, t_content_metadata t where t.metadata_id = " + bookId + " and f.file_id = substr(t.ceb_file, 1, instr(t.ceb_file, '.') -1)";
		initDataSource();
		Connection conn = dataSource.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		boolean flag = false;
		while(rs.next()){
			if(flag){
				System.out.println(bookId + "重复___________________________________________");
				break;
			}
			flag = true;
			byte[] bytes = rs.getBytes(1);
			String name = rs.getString(2);
			String author = rs.getString(3);
			saveCEB(bytes, bookId, name, author);
		}
		if(!flag){
			System.out.println(bookId + "没有数据_____________________________________________");
		}
		rs.close();
		stmt.close();
		conn.close();
	}

	public static void saveCEB(byte[] bytes, String bookId, String name, String author) throws Exception{
		String path = "E:\\yt\\ceb\\";
		String fileName = bookId + "_" + name.replace("/", "|").replace("\\", "|") + "_" + author.replace("/", "|").replace("\\", "|") + ".ceb";
		FileOutputStream out = new FileOutputStream(path + fileName);
		out.write(bytes);
		out.flush();
		out.close();
	}

	
	public static void updateISBN(List<String> sqls) throws Exception{
		Connection conn = dataSource.getConnection();
		Statement stmt = conn.createStatement();
		for(String sql : sqls){			
			try {
				int result = stmt.executeUpdate(sql);
				if(1 != result){
					System.out.println(sql);
				}
			} catch (Exception e) {
				System.out.println(sql);
				e.printStackTrace();
			}
		}
		stmt.close();
		conn.close();
	}

	public static String generateSql(String sqlTemplate, Object...values){
		char[] cs = sqlTemplate.toCharArray();
		StringBuilder sb = new StringBuilder();
		int index = 0;
		for(char c : cs){
			if(c == '?'){
				sb.append(values[index++]);
			}else{
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static void updateISBN(String excel, String sqlFile) throws Exception{
		DecimalFormat format = new DecimalFormat("#####0");
		List<List<String>> list = readExcel(excel, 0);
		List<String> sqls = new ArrayList<String>();
		FileWriter out = new FileWriter(sqlFile);
		for(List<String> l : list){
			if(l.size() <= 6){
				continue;
			}
			if(!"2.0".equals(l.get(6))){
				continue;
			}
			String id = l.get(0);
			String name = l.get(1);
			String isbn = l.get(5);
			try{
				id = format.format(new BigDecimal(id));
			}catch(Exception e){
			}
			try{
				isbn = format.format(new BigDecimal(isbn));
			}catch(Exception e){	
			}
			String sql = new StringBuilder("update tmp_content_metadata set xml_file = updateXML(xml_file, '//book/isbn/text()', '")
				.append(isbn)
				.append("') where metadata_id = ").append(id).append(";\n").toString();
			//System.out.println(sql);
			//sqls.add(sql);
			out.write(sql);
		}
		out.flush();
		out.close();
		
		
		//updateISBN(sqls);
	}

	public static void backupCopyright(String sqlFile) throws Exception{
		FileWriter writer = new FileWriter(sqlFile);
		String sql = "select t.metadata_id, n.control_id, n.xml_file.extract('//copyright/copyrightbegin/text()').getStringVal(), n.xml_file.extract('//copyright/copyrightend/text()').getStringVal() from t_content_metadata t left join t_content_news n on t.xml_file.extract('//book/rightid/text()').getNumberVal() = n.control_id";
		Connection conn = dataSource.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		while(rs.next()){
			long metadataId = rs.getLong(1);
			long copyrightId = rs.getLong(2);
			String begin = rs.getString(3);
			String end = rs.getString(4);
			try {
				format.parse(begin);
				format.parse(end);
			} catch (Exception e) {
				begin = begin + "--------";
				end = end + "--------";
			}
			String insert = "insert into t_content_copyright (metadata_id, copyright_id, copyright_begin, copyright_end) values(" + metadataId + ", " + copyrightId + ", to_date('" + begin + "', 'YYYY-MM-DD'), to_date('" + end + "', 'YYYY-MM-DD'));\n";
			System.out.println(insert);
			writer.write(insert);
		}
		conn.close();
		writer.flush();
		writer.close();
	}

	public static void readCopyright(String fileName, String newFile) throws Exception{
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		FileWriter out = new FileWriter(newFile);
		String line = null;
		while ((line = reader.readLine()) != null) {
//			String expr = "insert into t_content_copyright \\(metadata_id, copyright_id, copyright_begin, copyright_end\\) values\\((\\d+), (\\d+), to_date\\('(\\d{4}-\\d{2}-\\d{2})', 'YYYY-MM-DD'\\), to_date\\('(\\d{4}-\\d{2}-\\d{2})', 'YYYY-MM-DD'\\));";
//			if(!line.matches(expr)){
//				System.out.println(line);
//				if(line.indexOf("0, to_date('null--------'") != -1){
//					continue;
//				}
//				if(line.indexOf("to_date('2011-11-1', 'YYYY-MM-DD')") != -1){
//					line = line.replace("2011-11-1", "2011-11-01");
//				}
//				if(line.indexOf(" 00:00:00'") != -1){
//					line = line.replace(" 00:00:00'", "'");
//				}
//				if(line.indexOf("null--------") != -1){
//					line = line.replace("to_date('null--------', 'YYYY-MM-DD'),", "to_date('2011-01-01', 'YYYY-MM-DD'),");
//					line = line.replace("to_date('null--------', 'YYYY-MM-DD');", "to_date('2011-01-01', 'YYYY-MM-DD');");
//				}
//			}
//			line = line.replace("'<copyrightmark>3</copyrightmark>'", "XMLTYPE('<copyrightmark>3</copyrightmark>')");
//			line = line.replace("'<copyrightmark>2</copyrightmark>'", "XMLTYPE('<copyrightmark>2</copyrightmark>')");
//			line = line.replace("'<copyrightrange>1,2,3,4,5,6,7,8</copyrightrange>'", "XMLTYPE('<copyrightrange>1,2,3,4,5,6,7,8</copyrightrange>')");
			if(line.indexOf("0000-00-00") == -1){				
				continue;
			}
			out.write(line);
			out.write("\n");
		}
		reader.close();
		out.flush();
		out.close();
	}

	public static void updateCopyright(String path) throws Exception{
		FileWriter writer_copyright = new FileWriter(path + "copyright.sql");
		FileWriter writer_t_mark = new FileWriter(path + "t_mark.sql");
		FileWriter writer_t_range = new FileWriter(path + "t_range.sql");
		FileWriter writer_tmp_mark = new FileWriter(path + "tmp_mark.sql");
		FileWriter writer_tmp_range = new FileWriter(path + "tmp_range.sql");
		String sql = "select t.metadata_id, n.control_id, n.xml_file.extract('//copyright/copyrightbegin/text()').getStringVal(), n.xml_file.extract('//copyright/copyrightend/text()').getStringVal(), t.xml_file.getClobval(), t.current_state from t_content_metadata t left join t_content_news n on t.xml_file.extract('//book/rightid/text()').getNumberVal() = n.control_id";
		Connection conn = dataSource.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		long time = today.getTimeInMillis();
		while(rs.next()){
			long metadataId = rs.getLong(1);
			long copyrightId = rs.getLong(2);
			String begin = rs.getString(3);
			String end = rs.getString(4);
			String xml = rs.getString(5);
			String currentState = rs.getString(6);
			begin = format(begin);
			end = format(end);
			String insert = "insert into t_content_copyright (metadata_id, copyright_id, copyright_begin, copyright_end) values(" + metadataId + ", " + copyrightId + ", to_date('" + begin + "', 'YYYY-MM-DD'), to_date('" + end + "', 'YYYY-MM-DD'));\n";
			String update_t_mark;
			String update_tmp_mark;
			if("P".equals(currentState) && time > format.parse(end).getTime()){
				update_t_mark = "update t_content_metadata t set t.xml_file=appendChildXML(t.xml_file, '//book', XMLType('<copyrightmark>3</copyrightmark>')), t.copyright_mark='3' where t.metadata_id=" + metadataId + ";\n";
				update_tmp_mark = "update tmp_content_metadata t set t.xml_file=appendChildXML(t.xml_file, '//book', XMLType('<copyrightmark>3</copyrightmark>')), t.copyright_mark='3' where t.metadata_id=" + metadataId + ";\n";
			}else{
				update_t_mark = "update t_content_metadata t set t.xml_file=appendChildXML(t.xml_file, '//book', XMLType('<copyrightmark>2</copyrightmark>')), t.copyright_mark='2' where t.metadata_id=" + metadataId + ";\n";
				update_tmp_mark = "update tmp_content_metadata t set t.xml_file=appendChildXML(t.xml_file, '//book', XMLType('<copyrightmark>2</copyrightmark>')), t.copyright_mark='2' where t.metadata_id=" + metadataId + ";\n";
			}
			System.out.println(metadataId);
			writer_copyright.write(insert);
			writer_t_mark.write(update_t_mark);
			writer_tmp_mark.write(update_tmp_mark);			
			String update_t_range = "update t_content_metadata t set t.xml_file=appendChildXML(t.xml_file, '//book', XMLType('<copyrightrange>1,2,3,4,5,6,7,8</copyrightrange>')), t.copyright_range='1,2,3,4,5,6,7,8' where t.metadata_id=" + metadataId + ";\n";
			String update_tmp_range = "update tmp_content_metadata t set t.xml_file=appendChildXML(t.xml_file, '//book', XMLType('<copyrightrange>1,2,3,4,5,6,7,8</copyrightrange>')), t.copyright_range='1,2,3,4,5,6,7,8' where t.metadata_id=" + metadataId + ";\n";
			writer_t_range.write(update_t_range);
			writer_tmp_range.write(update_tmp_range);
		}
		conn.close();
		writer_copyright.flush();
		writer_copyright.close();
		writer_t_mark.flush();
		writer_t_mark.close();
		writer_t_range.flush();
		writer_t_range.close();
		writer_tmp_mark.flush();
		writer_tmp_mark.close();
		writer_tmp_range.flush();
		writer_tmp_range.close();
	}

	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-d");
	public static SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	private static String format(String time){
		String date = time;
		try {
			format.parse(date);
		} catch (Exception e) {
			try {
				format2.parse(date);
				date = date.substring(0, 8) + "0" + date.substring(8);
			} catch (Exception e1) {
				try {
					format3.parse(date);
					date = date.substring(0, 10);
				} catch (Exception e2) {
					date = "0000-00-00";
				}
			}
		}
		return date;
	}

	public static void cutFile(String path, String fileName) throws Exception{
		String filename = fileName.substring(0, fileName.lastIndexOf("."));
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		BufferedReader reader = new BufferedReader(new FileReader(path + fileName));
		String line;
		int size = 50000;
		int index = 1;
		int num = 1;
		FileWriter out = new FileWriter(path + filename + "_" + num + suffix);
		while((line = reader.readLine()) != null){
			if(index > size){
				try {
					out.flush();
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				num ++;
				out = new FileWriter(path + filename + "_" + num + suffix);
				index = 1;
			}
			out.write(line);
			out.write("\n");
			index ++;
		}
		try {
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
//		List<List<String>> list1 = readExcel("E:\\yt\\定制三种机型选择书单2011.10.25.xls", 1);
//		for(List<String> o : list1){
//			downloadCEB(o.get(0));
//		}
		//initDataSource(true);
		//updateISBN("E:\\yt\\isbn.xls", "e:\\yt\\isbn2.sql");
		//updateCopyright("E:\\yt\\copyright_\\");
		//readCopyright("E:\\yt\\copyright\\t_mark.sql", "E:\\yt\\copyright\\t_mark1.sql");
		//readCopyright("E:\\yt\\copyright\\tmp_mark.sql", "E:\\yt\\copyright\\tmp_mark1.sql");
		//readCopyright("E:\\yt\\copyright\\t_range.sql", "E:\\yt\\copyright\\t_range1.sql");
		//readCopyright("E:\\yt\\copyright\\tmp_range.sql", "E:\\yt\\copyright\\tmp_range1.sql");
		//readCopyright("E:\\yt\\copyright\\copyright.sql", "E:\\yt\\copyright\\copyright1.sql");
		cutFile("E:\\yt\\copyright_\\", "copyright.sql");
		cutFile("E:\\yt\\copyright_\\", "t_mark.sql");
		cutFile("E:\\yt\\copyright_\\", "t_range.sql");
		cutFile("E:\\yt\\copyright_\\", "tmp_mark.sql");
		cutFile("E:\\yt\\copyright_\\", "tmp_range.sql");
	}

}
