/**
 * 
 */
package org.adaikiss.xun.office;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author hlw
 * 
 */
public class ReadWriteExcel {

	public static final String EMPTY = "";

	public static void read(String path) throws IOException {
		List<String> list = new LinkedList<String>();
		InputStream myxls = new FileInputStream(path);
		HSSFWorkbook book = new HSSFWorkbook(myxls);
		FormulaEvaluator eval = book.getCreationHelper().createFormulaEvaluator();
		HSSFSheet sheet = book.getSheetAt(0);
		boolean isFirst = true;
		for (Row row : sheet) {
			//跳过第一行表头
			if(isFirst){
				isFirst = false;
				continue;
			}
			int index = 0;
			for (Cell cell : row) {
				if(++index != 5){
					continue;
				}
				String value = processCell(cell, eval);
				list.add(value);
				System.out.print(value + "; ");
				break;
			}
			System.out.println();
		}
		myxls.close();
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(list));
	}

	public static void write(String path) throws IOException{
		FileOutputStream fileOut = new FileOutputStream(path);
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet worksheet = workbook.createSheet("POI Worksheet");

		// index from 0,0... cell A1 is cell(0,0)
		HSSFRow row1 = worksheet.createRow((short) 0);

		HSSFCell cellA1 = row1.createCell(0);
		cellA1.setCellValue("Hello");
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.GOLD.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellA1.setCellStyle(cellStyle);

		HSSFCell cellB1 = row1.createCell(1);
		cellB1.setCellValue("Goodbye");
		cellStyle = workbook.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellB1.setCellStyle(cellStyle);

		HSSFCell cellC1 = row1.createCell(2);
		cellC1.setCellValue(true);

		HSSFCell cellD1 = row1.createCell(3);
		cellD1.setCellValue(new Date());
		cellStyle = workbook.createCellStyle();
		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
		cellD1.setCellStyle(cellStyle);

		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

	@SuppressWarnings("unchecked")
	private static <T>T processCell(Cell cell, FormulaEvaluator eval) {
		Object value = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			value = EMPTY;
			break;
		case Cell.CELL_TYPE_STRING:
			value = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				value = cell.getDateCellValue();
			} else {
				value = cell.getNumericCellValue();
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_FORMULA:
			// System.out.print(cell.getCellFormula());
			CellValue cellValue = eval.evaluate(cell);
			switch (cellValue.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				double v = cellValue.getNumberValue();
				if (DateUtil.isCellDateFormatted(cell)) {
					value = DateUtil.getJavaDate(v, true);
				} else {
					value = v;
				}
				break;
			}
			break;
		default:
			value = null;
		}
		return (T)value;
	}

	private static String processCellString(Cell cell, FormulaEvaluator eval) {
		Object value = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			value = EMPTY;
			break;
		case Cell.CELL_TYPE_STRING:
			value = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				value = cell.getDateCellValue();
			} else {
				value = cell.getNumericCellValue();
				value = new DecimalFormat("#0").format(value);
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_FORMULA:
			// System.out.print(cell.getCellFormula());
			CellValue cellValue = eval.evaluate(cell);
			switch (cellValue.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				double v = cellValue.getNumberValue();
				if (DateUtil.isCellDateFormatted(cell)) {
					value = DateUtil.getJavaDate(v, true);
				} else {
					value = v;
					value = new DecimalFormat("#0").format(value);
				}
				break;
			}
			break;
		default:
			value = null;
		}
		return String.valueOf(value);
	}

	public static void write(String path, String newPath, double[] lng, double[] lat) throws Exception{
		InputStream myxls = new FileInputStream(path);
		HSSFWorkbook book = new HSSFWorkbook(myxls);
		FormulaEvaluator eval = book.getCreationHelper().createFormulaEvaluator();
		HSSFSheet sheet = book.getSheetAt(0);

		FileOutputStream fileOut = new FileOutputStream(newPath);
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet worksheet = workbook.createSheet("1");

		boolean isFirst = true;
		int rowNum = 0;
		for (Row row : sheet) {
			if(row.getRowNum() >= 213){
				break;
			}
			HSSFRow row1 = worksheet.createRow(rowNum);
			//跳过第一行表头
			if(isFirst){
				isFirst = false;
				for (Cell cell : row) {
					String value = processCell(cell, eval);
					HSSFCell cellA1 = row1.createCell(cell.getColumnIndex());
					cellA1.setCellValue(value);
				}
				HSSFCell cellLng = row1.createCell(9);
				cellLng.setCellValue("经度");
				HSSFCell cellLat = row1.createCell(10);
				cellLat.setCellValue("纬度");
			}else{
				for (Cell cell : row) {
					int columnIndex = cell.getColumnIndex();
					HSSFCell cellA1 = row1.createCell(columnIndex);
					if(columnIndex == 0){
						cellA1.setCellValue((Double)processCell(cell, eval));
					}else if( columnIndex == 1 || columnIndex == 4 || columnIndex == 8 || columnIndex == 5){
						cellA1.setCellValue((String)processCell(cell, eval));
					} else if(columnIndex == 6 || columnIndex == 7 || columnIndex == 2 || columnIndex == 3){
						cellA1.setCellValue(processCellString(cell, eval));
					}
				}
				HSSFCell cellLng = row1.createCell(9);
				if(lng[rowNum-1] != 0){
					cellLng.setCellValue(lng[rowNum-1]);
				}
				HSSFCell cellLat = row1.createCell(10);
				if(lat[rowNum-1] != 0){
					cellLat.setCellValue(lat[rowNum-1]);
				}
			}
			rowNum++;
		}
		myxls.close();
		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		double[] lng = new double[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,27.837442,27.837442,39.950581,31.615587,0,31.63145,0,0,0,31.634327,39.978298,31.616456,31.624913,0,0,31.637144,0,0,0,0,0,0,33.726032,33.683389,39.828946,33.253184,33.252731,33.257177,0,0,0,0,0,30.718577,0,0,0,0,0,0,0,26.994247,26.465593,0,0,0,0,0,0,0,0,26.465593,0,0,0,0,0,0,0,0,0,0,0};
		double[] lat = new double[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,112.963291,112.963291,116.114814,120.491008,0,120.351012,0,0,0,120.271505,116.149692,120.336603,120.293253,0,0,120.360805,0,0,0,0,0,0,117.159076,116.986182,116.290692,116.574499,116.577807,116.550966,0,0,0,0,0,120.721091,0,0,0,0,0,0,0,112.629714,111.589176,0,0,0,0,0,0,0,0,111.589176,0,0,0,0,0,0,0,0,0,0,0};
		System.out.println(lng.length);
		if(lat.length != lng.length){System.out.println("lat和lng长度不等！");return;}
		write("c:/T_USR_SST-20121011.xls", "c:/new2.xls", lng, lat);
//		read("c:/T_USR_SST-20121011.xls");
		//write("c:/new.xls");
	}

}
