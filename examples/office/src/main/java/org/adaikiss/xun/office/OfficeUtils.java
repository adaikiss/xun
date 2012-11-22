/**
 * 
 */
package org.adaikiss.xun.office;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

/**
 * @author hlw
 *
 */
public class OfficeUtils {
	public static final String EMPTY = "";

	public static <T>T processCell(Cell cell, FormulaEvaluator eval) {
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
		@SuppressWarnings("unchecked")
		T t = (T)value;
		return t;
	}
}
