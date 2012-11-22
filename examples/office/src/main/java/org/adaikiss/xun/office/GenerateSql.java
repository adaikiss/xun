/**
 * 
 */
package org.adaikiss.xun.office;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.adaikiss.xun.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

/**
 * @author hlw
 *
 */
public class GenerateSql {

	private static void read(String path, String sqlPath) throws Exception{
		FileWriter writer = new FileWriter(sqlPath);
		InputStream myxls = new FileInputStream(path);
		HSSFWorkbook book = new HSSFWorkbook(myxls);
		FormulaEvaluator eval = book.getCreationHelper().createFormulaEvaluator();
		HSSFSheet sheet = book.getSheetAt(0);
		boolean isFirst = true;
		NumberFormat format = new DecimalFormat("#0");
		String tpl1 = "insert into T_USR_GIS(USRID, DCT_UC, GIS_X, GIS_Y)values(";
		String tpl2 = ", 'UC_SST', ";
		String tpl3 = ", ";
		String tpl4 = ");";
		for (Row row : sheet) {
			//跳过第一行表头
			if(isFirst){
				isFirst = false;
				continue;
			}
			Double USRID = null;
			Double x = null;
			Double y = null;
			for (Cell cell : row) {
				int index = cell.getColumnIndex();
				switch(index){
				case 0 : {
					USRID = OfficeUtils.processCell(cell, eval);
					break;
				}
				case 9 : {
					x = OfficeUtils.processCell(cell, eval);
					break;
				}
				case 10 : {
					y = OfficeUtils.processCell(cell, eval);
					break;
				}
				default : break;
				}
			}
			if(x == 0 || y == 0){
				continue;
			}
			String sql = StringUtils.join(tpl1, format.format(USRID), tpl2, x, tpl3, y, tpl4, "\n");
			System.out.print(sql);
			writer.append(sql);
			//System.out.println(StringUtils.join("USRID : ", format.format(USRID), ", X : ", x, ", Y : ", y));
		}
		writer.flush();
		writer.close();
		myxls.close();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		read("c:/new2.xls", "c:/sst_gis3.sql");
	}

}
