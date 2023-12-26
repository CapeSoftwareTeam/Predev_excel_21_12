package com.capeelectric.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.capeelectric.model.TestExcelData;

public interface TestExcelDataService {
	public void saveCustomersToDatabase(MultipartFile file) throws IOException;
	public TestExcelData getQuestionsFromExcel(InputStream inputStream) throws IOException;
}
