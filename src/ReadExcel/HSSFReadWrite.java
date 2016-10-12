/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package ReadExcel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static Print.Print.println;

/**
 * File for HSSF testing/examples
 *
 * THIS IS NOT THE MAIN HSSF FILE!! This is a utility for testing functionality.
 * It does contain sample API usage that may be educational to regular API
 * users.
 */
public final class HSSFReadWrite {

	/**
	 * creates an {@link HSSFWorkbook} the specified OS filename.
	 */
	private static HSSFWorkbook readxlsFile(String filename) throws IOException {
	    FileInputStream fis = new FileInputStream(filename);
	    try {
	        return new HSSFWorkbook(fis);
	    } finally {
	        fis.close();
	    }
	}
	private static XSSFWorkbook readxlsxFile(String filename) throws IOException {
		FileInputStream fis = new FileInputStream(filename);
		try {
			return new XSSFWorkbook(fis);
		} finally {
			fis.close();
		}
	}
	/**
	 * given a filename this outputs a sample sheet with just a set of
	 * rows/cells.
	 */
	private static void testCreateSampleSheet(String outputFilename) throws IOException {
		int rownum;
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet s = wb.createSheet();
		HSSFCellStyle cs = wb.createCellStyle();
		HSSFCellStyle cs2 = wb.createCellStyle();
		HSSFCellStyle cs3 = wb.createCellStyle();
		HSSFFont f = wb.createFont();
		HSSFFont f2 = wb.createFont();

		f.setFontHeightInPoints((short) 12);
		f.setColor((short) 0xA);
		f.setBold(true);
		f2.setFontHeightInPoints((short) 10);
		f2.setColor((short) 0xf);
		f2.setBold(true);
		cs.setFont(f);
		cs.setDataFormat(HSSFDataFormat.getBuiltinFormat("($#,##0_);[Red]($#,##0)"));
		cs2.setBorderBottom(BorderStyle.THIN);
		cs2.setFillPattern((short) 1); // fill w fg
		cs2.setFillForegroundColor((short) 0xA);
		cs2.setFont(f2);
		wb.setSheetName(0, "HSSF Test");
		for (rownum = 0; rownum < 300; rownum++) {
			HSSFRow r = s.createRow(rownum);
			if ((rownum % 2) == 0) {
				r.setHeight((short) 0x249);
			}
			for (int cellnum = 0; cellnum < 50; cellnum += 2) {
				HSSFCell c = r.createCell(cellnum);
				c.setCellValue(rownum * 10000 + cellnum
						+ (((double) rownum / 1000) + ((double) cellnum / 10000)));
				if ((rownum % 2) == 0) {
					c.setCellStyle(cs);
				}
				c = r.createCell(cellnum + 1);
				c.setCellValue(new HSSFRichTextString("TEST"));
				// 50 characters divided by 1/20th of a point
				s.setColumnWidth(cellnum + 1, (int) (50 * 8 / 0.05));
				if ((rownum % 2) == 0) {
					c.setCellStyle(cs2);
				}
			}
		}
		// draw a thick black border on the row at the bottom using BLANKS
		rownum++;
		rownum++;
		HSSFRow r = s.createRow(rownum);
		cs3.setBorderBottom(BorderStyle.THICK);
		for (int cellnum = 0; cellnum < 50; cellnum++) {
			HSSFCell c = r.createCell(cellnum);
			c.setCellStyle(cs3);
		}
		s.addMergedRegion(new CellRangeAddress(0, 3, 0, 3));
		s.addMergedRegion(new CellRangeAddress(100, 110, 100, 110));

		// end draw thick black border
		// create a sheet, set its title then delete it
		wb.createSheet();
		wb.setSheetName(1, "DeletedSheet");
		wb.removeSheetAt(1);
		// end deleted sheet
		FileOutputStream out = new FileOutputStream(outputFilename);
		try {
		    wb.write(out);
		} finally {
		    out.close();
		}
		wb.close();
	}

	private static void startReadXlsFile(String fileName){
		try {
			HSSFWorkbook wb = HSSFReadWrite.readxlsFile(fileName);
			System.out.println("Data dump:\n");
			for (int k = 0; k < wb.getNumberOfSheets(); k++) {
				HSSFSheet sheet = wb.getSheetAt(k);
				int rows = sheet.getPhysicalNumberOfRows();
				System.out.println("Sheet " + k + " \"" + wb.getSheetName(k) + "\" has " + rows
						+ " row(s).");
				for (int r = 0; r < rows; r++) {
					HSSFRow row = sheet.getRow(r);
					if (row == null) {
						continue;
					}
					int cells = row.getPhysicalNumberOfCells();
					System.out.println("\nROW " + row.getRowNum() + " has " + cells
							+ " cell(s).");
					for (int c = 0; c < cells; c++) {
						HSSFCell cell = row.getCell(c);
						String value = null;
						switch (cell.getCellTypeEnum()) {
							case FORMULA:
								value = "FORMULA value=" + cell.getCellFormula();break;
							case NUMERIC:
								value = "NUMERIC value=" + cell.getNumericCellValue();break;
							case STRING:
								value = "STRING value=" + cell.getStringCellValue();break;
							default:
						}
						System.out.println("CELL col=" + cell.getColumnIndex() + " VALUE="
								+ value);
					}
				}
			}
			wb.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void startReadXlsxFile(String fileName){
		try {
			XSSFWorkbook wb = HSSFReadWrite.readxlsxFile(fileName);
			System.out.println("Data dump:\n");
			for (int k = 0; k < wb.getNumberOfSheets(); k++) {
				XSSFSheet sheet = wb.getSheetAt(k);
				int rows = sheet.getPhysicalNumberOfRows();
				System.out.println("Sheet " + k + " \"" + wb.getSheetName(k) + "\" has " + rows
						+ " row(s).");
				for (int r = 0; r < rows; r++) {
					XSSFRow row = sheet.getRow(r);
					if (row == null) {
						continue;
					}
					int cells = row.getPhysicalNumberOfCells();
					System.out.println("\nROW " + row.getRowNum() + " has " + cells
							+ " cell(s).");
					for (int c = 0; c < cells; c++) {
						XSSFCell cell = row.getCell(c);
						String value = null;
						switch (cell.getCellTypeEnum()) {
							case FORMULA:
								value = "FORMULA value=" + cell.getCellFormula();break;
							case NUMERIC:
								value = "NUMERIC value=" + cell.getNumericCellValue();break;
							case STRING:
								value = "STRING value=" + cell.getStringCellValue();break;
							default:
						}
						System.out.println("CELL col=" + cell.getColumnIndex() + " VALUE="
								+ value);
					}
				}
			}
			wb.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 是xls文件返回0；
	 * 是xlsx文件返回1；
	 * 两者都不是返回-1；
	 * @param fileName
	 * @return
     */
	private static int whichFile(String fileName){
		int length = fileName.length();
		int index =fileName.lastIndexOf(".");
		if (index!=-1){
			if (length-index==4)
				return 0;
			if (length-index ==5)
				return 1;
		}
		return -1;
	}
	public void read(String fileName){
		if (whichFile(fileName)==0){
			startReadXlsFile(fileName);
		}
		else if (whichFile(fileName)==1){
			startReadXlsxFile(fileName);
		}
		else
			try {
				throw new FileNotFoundException("文件输入错误，请检查文件类型");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}
	public static void main(String[] args) {
		String fileName = "src/ReadExcel/ano.xls";	//only can read xls file
		startReadXlsFile(fileName);
		String name ="src/ReadExcel/ABC产品_13条.xlsx";	//only can read xlsx file
		startReadXlsxFile(name);
		String str=".ano.xlsx";
		if (whichFile(str)==0){
			println("是xls");
		}
		else if (whichFile(str)==1){
			println("xlsx");
		}
		else
			println("all not");


	}
}
