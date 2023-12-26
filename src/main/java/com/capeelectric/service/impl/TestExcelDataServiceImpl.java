package com.capeelectric.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.capeelectric.model.ConsumerUnit;
import com.capeelectric.model.TestExcelData;
import com.capeelectric.model.TestExcelDataChild;
import com.capeelectric.repository.TestExcelDataRepo;
import com.capeelectric.service.TestExcelDataService;

@Service
public class TestExcelDataServiceImpl implements TestExcelDataService {
	
 @Autowired 
 private TestExcelDataRepo testexcelRepo;
	
	public void saveCustomersToDatabase(MultipartFile file) throws IOException  {
		if (isValidExcelFile(file)) {
			try {
				TestExcelData customers = getQuestionsFromExcel(file.getInputStream());
				testexcelRepo.save(customers);
			} catch (IOException e) {
				throw new IllegalArgumentException("The file is not a valid excel file");
			}
		}
	}
	
	public TestExcelData getQuestionsFromExcel(InputStream inputStream) throws IOException {

		XSSFWorkbook workbook = null;
		List<TestExcelDataChild> testExcelDataModel1List = new ArrayList<>(); 
		TestExcelData testExcelDataModel = null;
		try {
			workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int rowIndex = 0;
			  testExcelDataModel = new TestExcelData();   
			for (int j=0; j< sheet.getPhysicalNumberOfRows(); j++) {
		        Row row = sheet.getRow(j);
		        Cell cell = row.getCell(1); //get first cell
		          switch(rowIndex) {
		          case 0:{
		        	  testExcelDataModel.setAddress(cell.getStringCellValue());
		          break;
		          }
		          case 1:{
		        	  testExcelDataModel.setPersonalData(cell.getStringCellValue());
		       	  break;
		          }
		          case 2:{
		        	  testExcelDataModel.setPhoneNumber(cell.getStringCellValue());
		          break;
		          }
		          case 3:{
		        	  testExcelDataModel.setUserName(cell.getStringCellValue());
		          break;  
		          }
		          }
		          rowIndex++;  
		    }	
             int rowIndex1=0 ;  
			for (Row row : sheet) {
				TestExcelDataChild testExcelDataModel1 = new TestExcelDataChild(); 
				if (rowIndex1 <=4) {
					rowIndex1++;
					continue;	
				}
				Iterator<Cell> cellIterator = row.iterator();
				int cellIndex = 0;			
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cellIndex) {
					case 0:{
						testExcelDataModel1.setLocation(cell.getStringCellValue());
						break ;
					}
					case 1:{
						testExcelDataModel1.setExperience(cell.getStringCellValue());
						break ;
					}
					case 2:{
						testExcelDataModel1.setMaritalStatus(cell.getStringCellValue());
						break ;
					}
					case 3:{					
						testExcelDataModel1.setMotherName(cell.getStringCellValue());
						break ;
					}				
				}
				cellIndex++;
				}
				testExcelDataModel1List.add(testExcelDataModel1);	
			}
			
		   testExcelDataModel.setTestExcelDataChild(testExcelDataModel1List);
		}
		catch (IOException e) {
		} finally {
			workbook.close(); 
		}
		return testExcelDataModel;
	}
public static boolean isValidExcelFile(MultipartFile file) {
	return Objects.equals(file.getContentType(),
			"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
}


}
