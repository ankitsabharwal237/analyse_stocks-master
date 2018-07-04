package com.stockapp.calculation;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import au.com.bytecode.opencsv.CSVWriter;

public class ExcelReportService {
	
	public static void generateReport(List<String[]> data, String[] header, String fileName) throws IOException{
		String csv = fileName + ".csv";
		CSVWriter writer = new CSVWriter(new FileWriter(csv));
		 data.add(0, header);
		writer.writeAll(data);
		 
		writer.close();
	}

}
