package com.capeelectric.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capeelectric.service.TestExcelDataService;

@RestController
public class TestExcelDataController {
	
	@Autowired
	 private TestExcelDataService testExcelDataService;

	// add question From Excel
	@PostMapping("/addQuestionsFromExcel")
	public ResponseEntity<?> addQuestionsFromExcel(@RequestParam("file") MultipartFile file)
			throws IOException {
		testExcelDataService.saveCustomersToDatabase(file);
		return new ResponseEntity<>("Customers data uploaded and saved to database successfully", HttpStatus.OK);
	}
}
