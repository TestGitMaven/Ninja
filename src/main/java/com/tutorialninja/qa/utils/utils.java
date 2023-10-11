package com.tutorialninja.qa.utils;

import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class utils {
	public static final int IMPLICITY_WAIT_TIME = 10;
	public static final int PAGE_LOAD_TIME = 5;

	public static String generateEmailWithTimeStamp() {
		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "venu" + timestamp + "@gmail.com";

	}

	public static void getTestDataFromExcelSheet(String sheetName) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.getSheet(sheetName);
		

	}

}
