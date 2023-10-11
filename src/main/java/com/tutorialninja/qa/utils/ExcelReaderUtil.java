package com.tutorialninja.qa.utils;
	import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	public class ExcelReaderUtil {

	    public static List<List<String>> readData(String filePath) {
	        List<List<String>> data = new ArrayList<>();

	        try {
	            FileInputStream excelFile = new FileInputStream(filePath);
	            Workbook workbook = new XSSFWorkbook(excelFile);
	            Sheet sheet = workbook.getSheetAt(0);

	            for (Row row : sheet) {
	                List<String> rowData = new ArrayList<>();
	                for (Cell cell : row) {
	                    rowData.add(cell.toString());
	                }
	                data.add(rowData);
	            }

	            workbook.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return data;
	    }
	}



