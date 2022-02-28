package com.lazy.common.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataDrivenUtil {

	public static Object[][] getDateByExcel(String FileName, String sheetName) throws Exception {
		String filePath = FilePath.getDataDirectory();
		File file = new File(filePath + FileName);
		FileInputStream inputStream = new FileInputStream(file);

		// xls文件
		@SuppressWarnings("resource")
		Workbook Workbook = new HSSFWorkbook(inputStream);

		Sheet Sheet = Workbook.getSheet(sheetName);
		int rowCount = Sheet.getLastRowNum() - Sheet.getFirstRowNum();
		List<Object[]> records = new ArrayList<Object[]>();
		for (int i = 1; i < rowCount + 1; i++) {
			Row row = Sheet.getRow(i);
			String fields[] = new String[row.getLastCellNum()];
			for (int j = 0; j < row.getLastCellNum(); j++) {
				fields[j] = row.getCell(j).getStringCellValue();
			}
			records.add(fields);
		}
		Object[][] results = new Object[records.size()][];
		for (int i = 0; i < records.size(); i++) {
			results[i] = records.get(i);
		}
		return results;
	}
	
	public static Object[][] getDateByDatabase(String db, String table) throws Exception {
		String url=AutoCommonUtil.readConfig("database.properties", "dburl");
		Connection connection=	DbUtils.getConnection(url,db);
		List<Object[]> records=new ArrayList<Object[]>();
		String sql="select * from "+table;
		Statement statement=connection.createStatement();
		ResultSet rs=statement.executeQuery(sql);
		ResultSetMetaData rSetMetaData=rs.getMetaData();
		
		int cols=rSetMetaData.getColumnCount();
		
		while(rs.next()) {
			String fields[] =new String[cols];
			int col=0;
			
			for (int colIdx = 0; colIdx <cols; colIdx++) {
				fields[col]=rs.getString(colIdx+1);
				col++;
			}
			records.add(fields);
		}
		DbUtils.closeAll(connection, statement, rs);
		
		Object [] [] results=new Object[records.size()][];
		for (int i = 0; i < records.size(); i++) {
			results[i]=records.get(i);
		}
		
		
		return results;
		
	}

}
