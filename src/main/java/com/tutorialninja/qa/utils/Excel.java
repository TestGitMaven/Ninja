package com.tutorialninja.qa.utils;
import java.util.List;

public class Excel {

	    public static void main(String[] args) {
	        String filePath = "your_excel_file.xlsx"; // Replace with your file path
	        List<List<String>> data = ExcelReaderUtil.readData(filePath);

	        // Print the data
	        for (List<String> row : data) {
	            for (String cellValue : row) {
	                System.out.print(cellValue + "\t");
	            }
	            System.out.println();
	        }
	    }
	}
