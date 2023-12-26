package com.capeelectric.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.capeelectric.exception.PowerEarthingDataException;
import com.capeelectric.model.DistrubutionPannel;
import com.capeelectric.model.ElectronicSystem;
import com.capeelectric.model.PowerEarthingData;
import com.capeelectric.model.ResponseFile;
import com.capeelectric.repository.FileDBRepository;
import com.capeelectric.service.PowerEarthingDataPDFService;
import com.capeelectric.util.AWSS3Configuration;
import com.capeelectric.util.Constants;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PowerEarthingDataPDFServiceImpl implements PowerEarthingDataPDFService {
	private static final Logger logger = LoggerFactory.getLogger(PowerEarthingDataPDFServiceImpl.class);

//	@Autowired
//	private PowerEarthingDataRepository powerEarthingDataRepository;

	@Autowired
	private FileDBRepository fileDBRepository;

	
	private String s3EmcFileUploadBucketName = "emc-cloudfront-fileupload-url";

	@Autowired
	AWSS3Configuration AWSS3configuration;
	

//	@Override
//	public void printPowerEarthingData(String userName, Integer emcId) throws PowerEarthingDataException {

	// }

	@Override
	public void printPowerEarthingData(String userName, Integer emcId, Optional<PowerEarthingData> powerEarthingDataRep)
			throws PowerEarthingDataException {

		if (userName != null && !userName.isEmpty() && emcId != null && emcId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);

			try {

				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("PowerandEarthingData.pdf"));
//				List<PowerEarthingData> powerEarthigData1 = powerEarthingDataRepository.findByUserNameAndEmcId(userName,
//						emcId);
				// PowerEarthingData powerEarthigData = powerEarthigData1.get(0);
				PowerEarthingData powerEarthigData = powerEarthingDataRep.get();

				Optional<ResponseFile> file = fileDBRepository.findByEmcId(emcId);
				ResponseFile file1 = file.get();

				List<ElectronicSystem> electroicSys1 = powerEarthigData.getElectronicSystem();

				List<DistrubutionPannel> distrubution1 = powerEarthigData.getDistrubutionPannel();
				DistrubutionPannel distrubution = distrubution1.get(0);
				ElectronicSystem electroicSys = electroicSys1.get(0);
				document.open();

				Font font9 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

				Font font12B = new Font(BaseFont.createFont(), 12, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font10B = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);

				float[] pointColumnHeadLabel = { 100F };

				document.newPage();

				PdfPTable FacilityDataTable = new PdfPTable(pointColumnHeadLabel);
				FacilityDataTable.setWidthPercentage(100); // Width 100%
				FacilityDataTable.setSpacingBefore(5f); // Space before table
				FacilityDataTable.setSpacingAfter(5f); // Space after table

				PdfPCell facilityCell = new PdfPCell(new Paragraph("Power And Earthing Data", font12B));
				facilityCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				facilityCell.setBackgroundColor(new GrayColor(0.93f));
				facilityCell.setFixedHeight(20f);
				FacilityDataTable.addCell(facilityCell);
				document.add(FacilityDataTable);

//				Font font2 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);

//				PdfPTable table10 = new PdfPTable(1);
//				table10.setWidthPercentage(100); // Width 100%
//				table10.setSpacingBefore(10f); // Space before table
//				table10.getDefaultCell().setBorder(0);
//
//				PdfPCell cellBls = new PdfPCell(new Paragraph(30, "Service Entrance", font9));
//				cellBls.setBorder(PdfPCell.NO_BORDER);
//				cellBls.setBackgroundColor(BaseColor.LIGHT_GRAY);
//				table10.addCell(cellBls);
//				document.add(table10);

				PdfPTable ServiceEntranceTable = new PdfPTable(pointColumnHeadLabel);
				ServiceEntranceTable.setWidthPercentage(100); // Width 100%
				ServiceEntranceTable.setSpacingBefore(5f); // Space before table
//				ServiceEntranceTable.setSpacingAfter(5f); // Space after table

				PdfPCell ServiceEntranceCell = new PdfPCell(new Paragraph("Service Entrance", font10B));
				ServiceEntranceCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				ServiceEntranceCell.setBackgroundColor(new GrayColor(0.93f));
				ServiceEntranceCell.setFixedHeight(17f);
				ServiceEntranceTable.addCell(ServiceEntranceCell);
				document.add(ServiceEntranceTable);

				float[] pointColumnWidths1 = { 90F, 90F };

				PdfPTable table = new PdfPTable(pointColumnWidths1); // 3 columns.
				table.setWidthPercentage(100); // Width 100%
				table.setSpacingBefore(10f); // Space before table
//				table7.setSpacingAfter(10f); // Space after table
				table.getDefaultCell().setBorder(0);

				PdfPCell cell = new PdfPCell(new Paragraph("Electrical utility service supplier:", font9));
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setGrayFill(0.92f);
				table.addCell(cell);
				PdfPCell cell1 = new PdfPCell(new Paragraph(powerEarthigData.getPowerElectricalUtility(), font9));
				cell1.setGrayFill(0.92f);
				cell1.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell1);

				PdfPCell cell2 = new PdfPCell(new Paragraph("Back up source:", font9));
				cell2.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell2);
				PdfPCell cell3 = new PdfPCell(new Paragraph(powerEarthigData.getPowerBackupSource(), font9));
				cell3.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell3);

				PdfPCell cell4 = new PdfPCell(new Paragraph("Distance to HV/LV substation:", font9));
				cell4.setBorder(PdfPCell.NO_BORDER);
				cell4.setGrayFill(0.92f);
				table.addCell(cell4);
				PdfPCell cell5 = new PdfPCell(new Paragraph(powerEarthigData.getPowerDistanceHvLv(), font9));
				cell5.setGrayFill(0.92f);
				cell5.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell5);

				PdfPCell cell6 = new PdfPCell(new Paragraph("Type of incoming cables from HV/LV substation:", font9));
				cell6.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell6);
				PdfPCell cell7 = new PdfPCell(new Paragraph(powerEarthigData.getPowerCableHvLv(), font9));
				cell7.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell7);

				PdfPCell cell8 = new PdfPCell(new Paragraph("Description of Supply:", font9));
				cell8.setBorder(PdfPCell.NO_BORDER);
				cell8.setGrayFill(0.92f);
				table.addCell(cell8);
				PdfPCell cell9 = new PdfPCell(new Paragraph(powerEarthigData.getPowerDiscSupply(), font9));
				cell9.setGrayFill(0.92f);
				cell9.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell9);

				PdfPCell cell10 = new PdfPCell(new Paragraph("Transformer characteristics:", font9));
				cell10.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell10);
				PdfPCell cell11 = new PdfPCell(new Paragraph(powerEarthigData.getPowerTransformationKVA(), font9));
				cell11.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell11);
				document.add(table);

				PdfPTable InputTable = new PdfPTable(pointColumnHeadLabel);
				InputTable.setWidthPercentage(100); // Width 100%
				InputTable.setSpacingBefore(5f); // Space before table

				PdfPCell InputCell = new PdfPCell(new Paragraph("Input", font10B));
				InputCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				InputCell.setBackgroundColor(new GrayColor(0.93f));
				InputCell.setFixedHeight(17f);
				InputTable.addCell(InputCell);
				document.add(InputTable);

				PdfPTable table5 = new PdfPTable(pointColumnWidths1); // 3 columns.
				table5.setWidthPercentage(100); // Width 100%
				table5.setSpacingBefore(5f); // Space before table
				table5.getDefaultCell().setBorder(0);

				PdfPCell cell14 = new PdfPCell(new Paragraph("Volts:", font9));
				cell14.setBorder(PdfPCell.NO_BORDER);
				table5.addCell(cell14);
				PdfPCell cell15 = new PdfPCell(new Paragraph(powerEarthigData.getPowerInputVolts(), font9));
				cell15.setBorder(PdfPCell.NO_BORDER);
				table5.addCell(cell15);

				PdfPCell cell16 = new PdfPCell(new Paragraph("No. of Phases:", font9));
				cell16.setBorder(PdfPCell.NO_BORDER);
				cell16.setGrayFill(0.92f);
				table5.addCell(cell16);
				PdfPCell cell17 = new PdfPCell(new Paragraph(powerEarthigData.getPowerInputPhase(), font9));
				cell17.setGrayFill(0.92f);
				cell17.setBorder(PdfPCell.NO_BORDER);
				table5.addCell(cell17);

				PdfPCell cell18 = new PdfPCell(new Paragraph("No. of wires:", font9));
				cell18.setBorder(PdfPCell.NO_BORDER);
				table5.addCell(cell18);
				PdfPCell cell19 = new PdfPCell(new Paragraph(powerEarthigData.getPowerInputWires(), font9));
				cell19.setBorder(PdfPCell.NO_BORDER);
				table5.addCell(cell19);

				PdfPCell cell20 = new PdfPCell(new Paragraph("Feed (Delta/WYE):", font9));
				cell20.setBorder(PdfPCell.NO_BORDER);
				cell20.setGrayFill(0.92f);
				table5.addCell(cell20);
				PdfPCell cell21 = new PdfPCell(new Paragraph(powerEarthigData.getPowerInputFeed(), font9));
				cell21.setGrayFill(0.92f);
				cell21.setBorder(PdfPCell.NO_BORDER);
				table5.addCell(cell21);
				
				PdfPCell cellOther = new PdfPCell(new Paragraph("Describe Other", font9));
				cellOther.setBorder(PdfPCell.NO_BORDER);
				cellOther.setGrayFill(0.92f);
				table5.addCell(cellOther);
				PdfPCell cellOtherDesc = new PdfPCell(new Paragraph(powerEarthigData.getPowerInputDesc(), font9));
				cellOtherDesc.setGrayFill(0.92f);
				cellOtherDesc.setBorder(PdfPCell.NO_BORDER);
				table5.addCell(cellOtherDesc);

				document.add(table5);

				PdfPTable OutPutTable = new PdfPTable(pointColumnHeadLabel);
				OutPutTable.setWidthPercentage(100); // Width 100%
				OutPutTable.setSpacingBefore(10f); // Space before table

				PdfPCell OutPutCell = new PdfPCell(new Paragraph("Output to MDB", font10B));
				OutPutCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				OutPutCell.setBackgroundColor(new GrayColor(0.93f));
				OutPutCell.setFixedHeight(17f);
				OutPutTable.addCell(OutPutCell);
				document.add(OutPutTable);

				PdfPTable table7 = new PdfPTable(pointColumnWidths1); // 3 columns.
				table7.setWidthPercentage(100); // Width 100%
				table7.setSpacingBefore(10f); // Space before table
				table7.getDefaultCell().setBorder(0);

				PdfPCell cell24 = new PdfPCell(new Paragraph("Volts:", font9));
				cell24.setBorder(PdfPCell.NO_BORDER);
				cell24.setGrayFill(0.92f);
				table7.addCell(cell24);
				PdfPCell cell25 = new PdfPCell(new Paragraph(powerEarthigData.getPowerOutputVolts(), font9));
				cell25.setGrayFill(0.92f);
				cell25.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell25);

				PdfPCell cell22 = new PdfPCell(new Paragraph("No. of Phases:", font9));
				cell22.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell22);
				PdfPCell cell23 = new PdfPCell(new Paragraph(powerEarthigData.getPowerOutputPhase(), font9));
				cell23.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell23);

				PdfPCell cell26 = new PdfPCell(new Paragraph("No. of Wires:", font9));
				cell26.setBorder(PdfPCell.NO_BORDER);
				cell26.setGrayFill(0.92f);
				table7.addCell(cell26);
				PdfPCell cell27 = new PdfPCell(new Paragraph(powerEarthigData.getPowerOutputWires(), font9));
				cell27.setBorder(PdfPCell.NO_BORDER);
				cell27.setGrayFill(0.92f);
				table7.addCell(cell27);

				PdfPCell cell28 = new PdfPCell(new Paragraph("Feed (Delta/WYE):", font9));
				cell28.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell28);
				PdfPCell cell29 = new PdfPCell(new Paragraph(powerEarthigData.getPowerOutputFeed(), font9));
				cell29.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell29);

				PdfPCell cell30 = new PdfPCell(new Paragraph("Incoming (Amps):", font9));
				cell30.setBorder(PdfPCell.NO_BORDER);
				cell30.setGrayFill(0.92f);
				table7.addCell(cell30);
				PdfPCell cell31 = new PdfPCell(new Paragraph(powerEarthigData.getPowerIncomingAmps(), font9));
				cell31.setGrayFill(0.92f);
				cell31.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell31);

				PdfPCell cell32 = new PdfPCell(new Paragraph("Neutral:", font9));
				cell32.setBorder(PdfPCell.NO_BORDER);
				// cell32.setGrayFill(0.92f);
				table7.addCell(cell32);
				PdfPCell cell33 = new PdfPCell(new Paragraph(powerEarthigData.getPowerNeutral(), font9));
				// cell33.setGrayFill(0.92f);
				cell33.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell33);

				PdfPCell cell34 = new PdfPCell(new Paragraph("System earthing:", font9));
				cell34.setBorder(PdfPCell.NO_BORDER);
				cell34.setGrayFill(0.92f);
				table7.addCell(cell34);
				PdfPCell cell35 = new PdfPCell(new Paragraph(powerEarthigData.getPsEarthing(), font9));
				cell35.setGrayFill(0.92f);
				cell35.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell35);

				PdfPCell cell36 = new PdfPCell(new Paragraph("Building Service Entrance:", font9));
				cell36.setBorder(PdfPCell.NO_BORDER);
				// cell36.setGrayFill(0.92f);
				table7.addCell(cell36);
				PdfPCell cell37 = new PdfPCell(new Paragraph(powerEarthigData.getDedicatedTransfermation(), font9));
				// cell37.setGrayFill(0.92f);
				cell37.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell37);

				PdfPCell cell38 = new PdfPCell(new Paragraph(
						"If no, which are the other buildings / loads connected to the transformer:", font9));
				cell38.setBorder(PdfPCell.NO_BORDER);
				cell38.setGrayFill(0.92f);
				table7.addCell(cell38);
				PdfPCell cell39 = new PdfPCell(
						new Paragraph(powerEarthigData.getDedicatedTransfermationOtherBuilding(), font9));
				cell39.setGrayFill(0.92f);
				cell39.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell39);

				PdfPCell cell40 = new PdfPCell(
						new Paragraph("Type and routing of incoming cable from transformer:", font9));
				cell40.setBorder(PdfPCell.NO_BORDER);
				// cell40.setGrayFill(0.92f);
				table7.addCell(cell40);
				PdfPCell cell41 = new PdfPCell(new Paragraph(powerEarthigData.getTypeOFIncoming(), font9));
				// cell41.setGrayFill(0.92f);
				cell41.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell41);

				PdfPCell cell42 = new PdfPCell(
						new Paragraph("Description of service entrance earth electrode:", font9));
				cell42.setBorder(PdfPCell.NO_BORDER);
				cell42.setGrayFill(0.92f);
				table7.addCell(cell42);
				PdfPCell cell43 = new PdfPCell(new Paragraph(powerEarthigData.getDescOfService(), font9));
				cell43.setGrayFill(0.92f);
				cell43.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell43);

				PdfPCell cell44 = new PdfPCell(
						new Paragraph("Describe about testing of service entrance earth electrode:", font9));
				cell44.setBorder(PdfPCell.NO_BORDER);
				// cell44.setGrayFill(0.92f);
				table7.addCell(cell44);
				PdfPCell cell45 = new PdfPCell(new Paragraph(powerEarthigData.getDescOfTestingService(), font9));
				// cell45.setGrayFill(0.92f);
				cell45.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell45);
				
				PdfPCell cell46 = new PdfPCell(
						new Paragraph("Describe about equipotential bonding and SPD's service entrance", font9));
				cell46.setBorder(PdfPCell.NO_BORDER);
				// cell44.setGrayFill(0.92f);
				table7.addCell(cell46);
				PdfPCell cell47 = new PdfPCell(new Paragraph(powerEarthigData.getDescOfEquipotentilaBonding(), font9));
				// cell45.setGrayFill(0.92f);
				cell47.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell47);

				document.add(table7);

				PdfPTable table13 = new PdfPTable(1);
				table13.setWidthPercentage(100); // Width 100%
				table13.setSpacingBefore(10f); // Space before table
				table13.getDefaultCell().setBorder(0);

				PdfPCell cellAttachedFile = new PdfPCell(
						new Paragraph(30, "Attach SLD of power supply system:", font9));
				cellAttachedFile.setBorder(PdfPCell.NO_BORDER);
				cellAttachedFile.setBackgroundColor(BaseColor.LIGHT_GRAY);
				table13.addCell(cellAttachedFile);

				// byte[] blob = file1.getData().getBinaryStream().readAllBytes();
				Blob blob = file1.getData();
				byte[] bytes = blob.getBytes(1l, (int) blob.length());
				FileOutputStream fileout = new FileOutputStream(file1.getFileName());
				fileout.write(bytes);

				try {
					// Create a S3 client with in-program credential
//					BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKeyId, accessKeySecret);
//					AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_SOUTH_1)
//							.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
					AmazonS3 s3Client=AWSS3configuration.getAmazonS3Client();
					// Uploading the PDF File in AWS S3 Bucket with folderName + fileNameInS3
					if (file1.getFileName().length() > 0) {
						PutObjectRequest request = new PutObjectRequest(s3EmcFileUploadBucketName,
								"EMC_PowerAndEarthingUploadedFile Name_"+file1.getFileId().toString().concat(file1.getFileName()),
								new File(file1.getFileName()));
						s3Client.putObject(request);
						logger.info("Uploading PowerEarthingfile done in AWS s3");

						java.util.Date expiration = new java.util.Date();
						long expTimeMillis = expiration.getTime();
						expTimeMillis += 1000 * 67 * 9000;
						expiration.setTime(expTimeMillis);

						PdfPCell cell7322 = new PdfPCell(
								new Paragraph("Copy and Paste these url to Browser and download/view the uploaded file in EMC:", font9));
						// cell73.setGrayFill(0.92f);
						cell7322.setBorder(PdfPCell.NO_BORDER);
						table13.addCell(cell7322);

						PdfPCell cell732 = new PdfPCell(new Paragraph(Constants.EMC_FILE_UPLOAD_DOMAIN
								+ "/"+"EMC_PowerAndEarthingUploadedFile Name_"+file1.getFileId().toString().concat(file1.getFileName()),FontFactory.getFont(FontFactory.HELVETICA, 4, Font.UNDERLINE, BaseColor.BLUE)));
						cell732.setGrayFill(0.92f);
						// cell732.setBorder(PdfPCell.NO_BORDER);
						cell732.setFixedHeight(8f);
						table13.addCell(cell732);

//						PdfPCell fileNote = new PdfPCell(
//								new Paragraph("Note :These link will expair  with in 7 days", font9));
//						// cell73.setGrayFill(0.92f);
//						fileNote.setBorder(PdfPCell.NO_BORDER);
//						table13.addCell(fileNote);

						document.add(table13);
//					
					} else {
						logger.error("There is no file available");
						throw new Exception("There is no file  available");
					}

				} catch (AmazonS3Exception e) {
					logger.error("Error while fetching data from AWS"+ e.getErrorMessage());
					throw new PowerEarthingDataException("Error while fetching data from AWS"+ e.getErrorMessage());
				}

				PdfPTable BuildingDistributionTable = new PdfPTable(pointColumnHeadLabel);
				BuildingDistributionTable.setWidthPercentage(100); // Width 100%
				BuildingDistributionTable.setSpacingBefore(10f); // Space before table
//				BuildingDistributionTable.setSpacingAfter(5f); // Space after table

				PdfPCell BuildingDistriCell = new PdfPCell(new Paragraph("Building Distribution", font10B));
				BuildingDistriCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				BuildingDistriCell.setBackgroundColor(new GrayColor(0.93f));
				BuildingDistriCell.setFixedHeight(17f);
				BuildingDistributionTable.addCell(BuildingDistriCell);
				document.add(BuildingDistributionTable);

				PdfPTable table14 = new PdfPTable(pointColumnWidths1); // 3 columns.
				table14.setWidthPercentage(100); // Width 100%
				table14.setSpacingBefore(10f); // Space before table
//				table14.setSpacingAfter(10f); // Space after table
				table14.getDefaultCell().setBorder(0);

				PdfPCell cell70 = new PdfPCell(new Paragraph("SLD:", font9));
				cell70.setBorder(PdfPCell.NO_BORDER);
				cell70.setGrayFill(0.92f);
				table14.addCell(cell70);
				PdfPCell cell71 = new PdfPCell(new Paragraph(electroicSys.getbDSld(), font9));
				cell71.setGrayFill(0.92f);
				cell71.setBorder(PdfPCell.NO_BORDER);
				table14.addCell(cell71);
				document.add(table14);

				PdfPCell cellNote = new PdfPCell(new Paragraph(25,
						"Note: any heavy cycling loads or variable load controllers fed from the same distribution feeder path that supplies the computer system.",
						font9));
				cellNote.setBorder(PdfPCell.NO_BORDER);
				// cellNote.setBackgroundColor(BaseColor.LIGHT_GRAY);

				PdfPTable table29 = new PdfPTable(1);
				table29.setWidthPercentage(100); // Width 100%
				table29.setSpacingBefore(5f); // Space before table
				table29.getDefaultCell().setBorder(0);
				table29.addCell(cellNote);
				document.add(table29);

				PdfPCell cellNote1 = new PdfPCell(new Paragraph(25,
						"Note:how protective earthing distribution is accomplished (conduit only, flexible conduit and bond wire, other).",
						font9));
				cellNote1.setBorder(PdfPCell.NO_BORDER);
				// cellNote1.setBackgroundColor(BaseColor.LIGHT_GRAY);

				PdfPTable table31 = new PdfPTable(1);
				table31.setWidthPercentage(100); // Width 100%
				// table31.setSpacingBefore(10f); // Space before table
				table31.getDefaultCell().setBorder(0);
				table31.addCell(cellNote1);
				document.add(table31);

				PdfPTable table18 = new PdfPTable(pointColumnWidths1); // 3 columns.
				table18.setWidthPercentage(100); // Width 100%
				table18.setSpacingBefore(5f); // Space before table
//				table18.setSpacingAfter(10f); // Space after table
				table18.getDefaultCell().setBorder(0);

				PdfPCell cell72 = new PdfPCell(new Paragraph("Record data:", font9));
				cell72.setBorder(PdfPCell.NO_BORDER);
				// cell72.setGrayFill(0.92f);
				table18.addCell(cell72);
				PdfPCell cell73 = new PdfPCell(new Paragraph(electroicSys.getbDRecordData(), font9));
				// cell73.setGrayFill(0.92f);
				cell73.setBorder(PdfPCell.NO_BORDER);
				table18.addCell(cell73);

				PdfPCell cell74 = new PdfPCell(new Paragraph("Earthing:", font9));
				cell74.setBorder(PdfPCell.NO_BORDER);
				cell74.setGrayFill(0.92f);
				table18.addCell(cell74);
				PdfPCell cell75 = new PdfPCell(new Paragraph(electroicSys.getbDEarthing(), font9));
				cell75.setGrayFill(0.92f);
				cell75.setBorder(PdfPCell.NO_BORDER);
				table18.addCell(cell75);

				document.add(table18);
				document.newPage();

//				PdfPTable table30 = new PdfPTable(1);
//				table30.setWidthPercentage(100); // Width 100%
//				table30.setSpacingBefore(10f); // Space before table
//				table30.getDefaultCell().setBorder(0);
//				
//				PdfPCell cellEs = new PdfPCell(new Paragraph(30, "Electronics System Room Power Distribution", font9));
//				cellEs.setBorder(PdfPCell.NO_BORDER);
//				cellEs.setBackgroundColor(BaseColor.LIGHT_GRAY);
//				table30.addCell(cellEs);
//				document.add(table30);

				PdfPTable ElectronicsSystemTable = new PdfPTable(pointColumnHeadLabel);
				ElectronicsSystemTable.setWidthPercentage(100); // Width 100%
				ElectronicsSystemTable.setSpacingBefore(10f); // Space before table
//				ElectronicsSystemTable.setSpacingAfter(5f); // Space after table

				PdfPCell ElectronicsSystemCell = new PdfPCell(
						new Paragraph("Electronics System Room Power Distribution", font10B));
				ElectronicsSystemCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				ElectronicsSystemCell.setBackgroundColor(new GrayColor(0.93f));
				ElectronicsSystemCell.setFixedHeight(17f);
				ElectronicsSystemTable.addCell(ElectronicsSystemCell);
				document.add(ElectronicsSystemTable);

				PdfPTable table17 = new PdfPTable(pointColumnWidths1);
				table17.setWidthPercentage(100); // Width 100%
				table17.setSpacingBefore(10f); // Space before table
				table17.getDefaultCell().setBorder(0);

				PdfPCell cell850 = new PdfPCell(new Paragraph("Description:", font9));
				cell850.setBorder(PdfPCell.NO_BORDER);
				cell850.setGrayFill(0.92f);
				table17.addCell(cell850);
				PdfPCell cell86 = new PdfPCell(new Paragraph(electroicSys.getElectronicDesc(), font9));
				cell86.setGrayFill(0.92f);
				cell86.setBorder(PdfPCell.NO_BORDER);
				table17.addCell(cell86);
				document.add(table17);

				PdfPCell cellEsp = new PdfPCell(
						new Paragraph(30, "2.Electronic system power distribution panel", font9));
				cellEsp.setBorder(PdfPCell.NO_BORDER);
				cellEsp.setBackgroundColor(BaseColor.LIGHT_GRAY);

				PdfPTable table32 = new PdfPTable(1);
				table32.setWidthPercentage(100); // Width 100%
				table32.setSpacingBefore(10f); // Space before table
				table32.getDefaultCell().setBorder(0);
				table32.addCell(cellEsp);
				document.add(table32);

				float[] pointColumnWidths30 = { 30F, 145F, 55F, 55F };

				PdfPTable table22 = new PdfPTable(pointColumnWidths30);
				table22.setWidthPercentage(100); // Width 100%
				table22.setSpacingBefore(10f); // Space before table
				table22.setWidthPercentage(100);

				PdfPCell cell301 = new PdfPCell(new Paragraph("a.", font9));
				cell301.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell301.setGrayFill(0.92f);
				table22.addCell(cell301);

				PdfPCell cell302 = new PdfPCell(new Paragraph("Panel ID", font9));
				cell302.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell302.setFixedHeight(20f);
				cell302.setGrayFill(0.92f);
				table22.addCell(cell302);

				PdfPCell cell303 = new PdfPCell(new Paragraph(electroicSys.getPanelId().toString(), font9));
				cell303.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell303.setFixedHeight(20f);
				cell303.setColspan(2);
				table22.addCell(cell303);

				PdfPCell cell304 = new PdfPCell(new Paragraph("b.", font9));
				cell304.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell304.setGrayFill(0.92f);
				table22.addCell(cell304);

				PdfPCell cell305 = new PdfPCell(new Paragraph("Name plate data (manufacturer)", font9));
				cell305.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell305.setFixedHeight(20f);
				cell305.setGrayFill(0.92f);
				table22.addCell(cell305);

				PdfPCell cell306 = new PdfPCell(new Paragraph(electroicSys.getNamePlateData(), font9));
				cell306.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell306.setFixedHeight(20f);
				cell306.setColspan(2);
				table22.addCell(cell306);

				document.add(table22);

				float[] pointColumnWidths3 = { 38F, 150F, 35F, 35F, 35F, 35F, 35F };

				PdfPTable table23 = new PdfPTable(pointColumnWidths3);
				table23.setWidthPercentage(100); // Width 100%
				// table23.setSpacingBefore(10f); // Space before table
				table23.setWidthPercentage(100);

				PdfPCell cell3041 = new PdfPCell(new Paragraph("c.", font9));
				cell3041.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3041.setGrayFill(0.92f);
				cell3041.setRowspan(2);
				table23.addCell(cell3041);

				PdfPCell cell309 = new PdfPCell(new Paragraph("Main circuit breaker", font9));
				cell309.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell309.setFixedHeight(20f);
				cell309.setColspan(2);
				cell309.setGrayFill(0.92f);
				table23.addCell(cell309);

				PdfPCell cell310 = new PdfPCell(new Paragraph(electroicSys.getMainCircuteBraker(), font9));
				cell310.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell310.setFixedHeight(20f);
				cell310.setColspan(2);
				table23.addCell(cell310);

				PdfPCell cell313 = new PdfPCell(new Paragraph("Rating (A)", font9));
				cell313.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell313.setFixedHeight(20f);
				cell313.setGrayFill(0.92f);
				table23.addCell(cell313);

				PdfPCell cell3133 = new PdfPCell(new Paragraph(electroicSys.getMainCircuteBrakerRating(), font9));
				cell3133.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3133.setFixedHeight(20f);
				table23.addCell(cell3133);

				PdfPCell cell312 = new PdfPCell(new Paragraph("Emergency trip?", font9));
				cell312.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell312.setFixedHeight(20f);
				cell312.setColspan(2);
				cell312.setGrayFill(0.92f);
				table23.addCell(cell312);

				PdfPCell cell314 = new PdfPCell(new Paragraph("Remote", font9));
				cell314.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell314.setFixedHeight(20f);
				cell314.setGrayFill(0.92f);
				// cell314.setColspan(2);
				table23.addCell(cell314);

				PdfPCell cell3141 = new PdfPCell(new Paragraph(electroicSys.getEmergencyTripRemote(), font9));
				cell3141.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3141.setFixedHeight(20f);
				table23.addCell(cell3141);

				PdfPCell cell3121 = new PdfPCell(new Paragraph("local", font9));
				cell3121.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3121.setFixedHeight(20f);
				cell3121.setGrayFill(0.92f);
				table23.addCell(cell3121);

				PdfPCell cell3122 = new PdfPCell(new Paragraph(electroicSys.getEmergencyTripLocal(), font9));
				cell3122.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3122.setFixedHeight(20f);
				// cell3122.setGrayFill(0.92f);
				table23.addCell(cell3122);
				document.add(table23);

				PdfPTable table24 = new PdfPTable(pointColumnWidths30);
				table24.setWidthPercentage(100); // Width 100%
				// table24.setSpacingBefore(10f); // Space before table
				table24.setWidthPercentage(100);

				PdfPCell cell201 = new PdfPCell(new Paragraph("d.", font9));
				cell201.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell201.setGrayFill(0.92f);
				cell201.setRowspan(2);
				table24.addCell(cell201);

				PdfPCell cell202 = new PdfPCell(new Paragraph("Other trips", font9));
				cell202.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell202.setFixedHeight(20f);
				cell202.setGrayFill(0.92f);
				table24.addCell(cell202);

				PdfPCell cell203 = new PdfPCell(new Paragraph(electroicSys.getOtherTrip(), font9));
				cell203.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell203.setFixedHeight(20f);
				cell203.setColspan(2);
				table24.addCell(cell203);

				PdfPCell cell204 = new PdfPCell(new Paragraph("Differential protection", font9));
				cell204.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell204.setFixedHeight(20f);
				cell204.setGrayFill(0.92f);
				table24.addCell(cell204);

				PdfPCell cell205 = new PdfPCell(new Paragraph(electroicSys.getDifferentalProtection(), font9));
				cell205.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell205.setFixedHeight(20f);
				cell205.setColspan(2);
				table24.addCell(cell205);

				PdfPCell cell2012 = new PdfPCell(new Paragraph("e.", font9));
				cell2012.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2012.setGrayFill(0.92f);
				// cell2012.setRowspan(2);
				table24.addCell(cell2012);

				PdfPCell cell206 = new PdfPCell(new Paragraph("Panel bonded to building steel", font9));
				cell206.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell206.setFixedHeight(20f);
				cell206.setGrayFill(0.92f);
				table24.addCell(cell206);

				PdfPCell cell207 = new PdfPCell(new Paragraph(electroicSys.getBouodingStell(), font9));
				cell207.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell207.setFixedHeight(20f);
				cell207.setColspan(2);
				table24.addCell(cell207);

				PdfPCell cell333 = new PdfPCell(new Paragraph("f.", font9));
				cell333.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell333.setGrayFill(0.92f);
				cell333.setRowspan(4);
				table24.addCell(cell333);

				PdfPCell cell331 = new PdfPCell(new Paragraph("Panel feed", font9));
				cell331.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell331.setFixedHeight(20f);
				cell331.setGrayFill(0.92f);
				table24.addCell(cell331);

				PdfPCell cell332 = new PdfPCell(new Paragraph(electroicSys.getPanelFeed(), font9));
				cell332.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell332.setFixedHeight(20f);
				cell332.setColspan(2);
				table24.addCell(cell332);

				PdfPCell cell334 = new PdfPCell(new Paragraph("Phase wires", font9));
				cell334.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell334.setFixedHeight(20f);
				cell334.setGrayFill(0.92f);
				table24.addCell(cell334);

				PdfPCell cell335 = new PdfPCell(new Paragraph(electroicSys.getPhaseWires(), font9));
				cell335.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell335.setFixedHeight(20f);
				cell335.setColspan(2);
				table24.addCell(cell335);

				PdfPCell cell336 = new PdfPCell(new Paragraph("Neutral wire", font9));
				cell336.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell336.setFixedHeight(20f);
				cell336.setGrayFill(0.92f);
				table24.addCell(cell336);

				PdfPCell cell337 = new PdfPCell(new Paragraph(electroicSys.getNeutralWire(), font9));
				cell337.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell337.setFixedHeight(20f);
				cell337.setColspan(2);
				table24.addCell(cell337);

				PdfPCell cell338 = new PdfPCell(new Paragraph("PE wire size", font9));
				cell338.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell338.setFixedHeight(20f);
				cell338.setGrayFill(0.92f);
				table24.addCell(cell338);

				PdfPCell cell339 = new PdfPCell(new Paragraph(electroicSys.getPeWireSize(), font9));
				cell339.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell339.setFixedHeight(20f);
				cell339.setColspan(2);
				table24.addCell(cell339);

				PdfPCell cell321 = new PdfPCell(new Paragraph("g.", font9));
				cell321.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell321.setGrayFill(0.92f);
				table24.addCell(cell321);

				PdfPCell cell323 = new PdfPCell(new Paragraph("Panel connectors", font9));
				cell323.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell323.setFixedHeight(20f);
				cell323.setGrayFill(0.92f);
				table24.addCell(cell323);

				PdfPCell cell3123 = new PdfPCell(new Paragraph(electroicSys.getPannelConnectors(), font9));
				cell3123.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3123.setFixedHeight(20f);
				cell3123.setColspan(2);
				table24.addCell(cell3123);

				document.add(table24);

				float[] pointColumnWidths6 = { 30F, 145F, 55F, 55F };

				PdfPTable table27 = new PdfPTable(pointColumnWidths6);
				table27.setWidthPercentage(100); // Width 100%
				// table27.setSpacingBefore(10f); // Space before table
				table27.setWidthPercentage(100);

				PdfPCell cell3213 = new PdfPCell(new Paragraph("h.", font9));
				cell3213.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3213.setGrayFill(0.92f);
				cell3213.setRowspan(3);
				table27.addCell(cell3213);

				PdfPCell cell3234 = new PdfPCell(new Paragraph("Neutral and earth busbars", font9));
				cell3234.setHorizontalAlignment(Element.ALIGN_CENTER);
				// cell3234.setFixedHeight(20f);
				cell3234.setRowspan(3);
				cell3234.setGrayFill(0.92f);
				table27.addCell(cell3234);

				PdfPCell cell312313 = new PdfPCell(new Paragraph("Neutral bus", font9));
				cell312313.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell312313.setFixedHeight(20f);
				cell312313.setGrayFill(0.92f);
				table27.addCell(cell312313);

				PdfPCell cell31231 = new PdfPCell(new Paragraph(electroicSys.getNeutralBus(), font9));
				cell31231.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell31231.setFixedHeight(20f);
				cell31231.setColspan(2);
				table27.addCell(cell31231);

				PdfPCell cell31203 = new PdfPCell(new Paragraph("Earth bus", font9));
				cell31203.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell31203.setFixedHeight(20f);
				cell31203.setGrayFill(0.92f);
				table27.addCell(cell31203);

				PdfPCell cell311 = new PdfPCell(new Paragraph(electroicSys.getEarthBus(), font9));
				cell311.setHorizontalAlignment(Element.ALIGN_CENTER);
				// cell311.setFixedHeight(20f);
				table27.addCell(cell311);

				PdfPCell cell312033 = new PdfPCell(new Paragraph("List of non electronic loads on earth bus", font9));
				cell312033.setHorizontalAlignment(Element.ALIGN_CENTER);
				// cell312033.setFixedHeight(20f);
				cell312033.setGrayFill(0.92f);
				table27.addCell(cell312033);

				PdfPCell cell31144 = new PdfPCell(new Paragraph(electroicSys.getListOfNonElectronicLoad(), font9));
				cell31144.setHorizontalAlignment(Element.ALIGN_CENTER);
				// cell31144.setFixedHeight(20f);
				table27.addCell(cell31144);

				PdfPCell cell3322 = new PdfPCell(new Paragraph("i.", font9));
				cell3322.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3322.setGrayFill(0.92f);
				cell3322.setRowspan(2);
				table27.addCell(cell3322);

				PdfPCell cell32343 = new PdfPCell(new Paragraph("Uses", font9));
				cell32343.setHorizontalAlignment(Element.ALIGN_CENTER);
				// cell32343.setFixedHeight(20f);
				cell32343.setRowspan(2);
				cell32343.setGrayFill(0.92f);
				table27.addCell(cell32343);

				PdfPCell cell3123131 = new PdfPCell(new Paragraph("Dediated to electronic system?", font9));
				cell3123131.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3123131.setFixedHeight(20f);
				cell3123131.setGrayFill(0.92f);
				table27.addCell(cell3123131);

				PdfPCell cell12 = new PdfPCell(new Paragraph(electroicSys.getDedicatedElectronicSystem(), font9));
				cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell12.setFixedHeight(20f);
				// cell12.setColspan(2);
				table27.addCell(cell12);

				PdfPCell cell31201 = new PdfPCell(
						new Paragraph("List all non computer loads, including any convenience receptacles", font9));
				cell31201.setHorizontalAlignment(Element.ALIGN_CENTER);
				// cell31201.setFixedHeight(20f);
				cell31201.setGrayFill(0.92f);
				table27.addCell(cell31201);

				PdfPCell cell3116 = new PdfPCell(new Paragraph(electroicSys.getNonComputerLoads(), font9));
				cell3116.setHorizontalAlignment(Element.ALIGN_CENTER);
				// cell3116.setFixedHeight(20f);
				table27.addCell(cell3116);

				document.add(table27);
				document.newPage();

//				PdfPTable table16 = new PdfPTable(1);
//				table16.setWidthPercentage(100); // Width 100%
//				table16.setSpacingBefore(10f); // Space before table
//				table16.getDefaultCell().setBorder(0);
//				
//				PdfPCell cellW = new PdfPCell(new Paragraph(30, "Distribution panel At Load (Final Circuits)", font9));
//				cellW.setBorder(PdfPCell.NO_BORDER);
//				cellW.setBackgroundColor(BaseColor.LIGHT_GRAY);
//				
//				table16.addCell(cellW);
//				document.add(table16);

				PdfPTable DistributionPanelTable = new PdfPTable(pointColumnHeadLabel);
				DistributionPanelTable.setWidthPercentage(100); // Width 100%
				DistributionPanelTable.setSpacingBefore(10f); // Space before table
//				DistributionPanelTable.setSpacingAfter(5f); // Space after table

				PdfPCell DistributionPanelCell = new PdfPCell(
						new Paragraph("Distribution panel At Load (Final Circuits)", font10B));
				DistributionPanelCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				DistributionPanelCell.setBackgroundColor(new GrayColor(0.93f));
				DistributionPanelCell.setFixedHeight(17f);
				DistributionPanelTable.addCell(DistributionPanelCell);
				document.add(DistributionPanelTable);

				PdfPTable table15 = new PdfPTable(pointColumnWidths1);
				table15.setWidthPercentage(100); // Width 100%
				table15.setSpacingBefore(10f); // Space before table
				table15.getDefaultCell().setBorder(0);
				document.add(table15);

				PdfPCell cell85 = new PdfPCell(new Paragraph("Wire size compatilble to circuit braker:", font9));
				cell85.setBorder(PdfPCell.NO_BORDER);
				cell85.setGrayFill(0.92f);
				table15.addCell(cell85);
				PdfPCell cell861 = new PdfPCell(new Paragraph(distrubution.getCbWireSize(), font9));
				cell861.setGrayFill(0.92f);
				cell861.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell861);

				PdfPCell cell84 = new PdfPCell(new Paragraph("Description:", font9));
				cell84.setBorder(PdfPCell.NO_BORDER);
				// cell84.setGrayFill(0.92f);
				table15.addCell(cell84);
				PdfPCell cell87 = new PdfPCell(new Paragraph(distrubution.getCbDesc(), font9));
				// cell87.setGrayFill(0.92f);
				cell87.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell87);

				PdfPCell cell88 = new PdfPCell(new Paragraph("Matches receptable:", font9));
				cell88.setBorder(PdfPCell.NO_BORDER);
				cell88.setGrayFill(0.92f);
				table15.addCell(cell88);
				PdfPCell cell89 = new PdfPCell(new Paragraph(distrubution.getMatchesReceptable(), font9));
				cell89.setGrayFill(0.92f);
				cell89.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell89);

				PdfPCell cell90 = new PdfPCell(new Paragraph("Description:", font9));
				cell90.setBorder(PdfPCell.NO_BORDER);
				// cell90.setGrayFill(0.92f);
				table15.addCell(cell90);
				PdfPCell cell91 = new PdfPCell(new Paragraph(distrubution.getMatchesReceptableDesc(), font9));
				// cell91.setGrayFill(0.92f);
				cell91.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell91);

				PdfPCell cell92 = new PdfPCell(
						new Paragraph("Individual insulated PE wire run in each electronics load circuit:", font9));
				cell92.setBorder(PdfPCell.NO_BORDER);
				cell92.setGrayFill(0.92f);
				table15.addCell(cell92);
				PdfPCell cell93 = new PdfPCell(new Paragraph(distrubution.getIndivdialPwire(), font9));
				cell93.setGrayFill(0.92f);
				cell93.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell93);

				PdfPCell cell94 = new PdfPCell(new Paragraph("Description:", font9));
				cell94.setBorder(PdfPCell.NO_BORDER);
				// cell94.setGrayFill(0.92f);
				table15.addCell(cell94);
				PdfPCell cell95 = new PdfPCell(new Paragraph(distrubution.getIndivdialPwireDesc(), font9));
				// cell95.setGrayFill(0.92f);
				cell95.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell95);

				PdfPCell cell96 = new PdfPCell(new Paragraph(
						"Individual insulated neutral wire run in each computer load circuit (if necessary??:", font9));
				cell96.setBorder(PdfPCell.NO_BORDER);
				cell96.setGrayFill(0.92f);
				table15.addCell(cell96);
				PdfPCell cell97 = new PdfPCell(new Paragraph(distrubution.getIndivdialNeutralwire(), font9));
				cell97.setGrayFill(0.92f);
				cell97.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell97);

				PdfPCell cell98 = new PdfPCell(new Paragraph("Description:", font9));
				cell98.setBorder(PdfPCell.NO_BORDER);
				// cell98.setGrayFill(0.92f);
				table15.addCell(cell98);
				PdfPCell cell99 = new PdfPCell(new Paragraph(distrubution.getIndivdialNeutralwireDesc(), font9));
				// cell99.setGrayFill(0.92f);
				cell99.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell99);

				PdfPCell cell100 = new PdfPCell(
						new Paragraph("All computer load circuits correctly identified:", font9));
				cell100.setBorder(PdfPCell.NO_BORDER);
				cell100.setGrayFill(0.92f);
				table15.addCell(cell100);
				PdfPCell cell101 = new PdfPCell(new Paragraph(distrubution.getComputerLoadCircute(), font9));
				cell101.setGrayFill(0.92f);
				cell101.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell101);

				PdfPCell cell102 = new PdfPCell(new Paragraph("Description:", font9));
				cell102.setBorder(PdfPCell.NO_BORDER);
				// cell102.setGrayFill(0.92f);
				table15.addCell(cell102);
				PdfPCell cell103 = new PdfPCell(new Paragraph(distrubution.getComputerLoadCircuteDes(), font9));
				// cell103.setGrayFill(0.92f);
				cell103.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell103);

				PdfPCell cell104 = new PdfPCell(new Paragraph(
						" All computer load circuits (30/Aphase) terminated in isolated earth receptacles:", font9));
				cell104.setBorder(PdfPCell.NO_BORDER);
				cell104.setGrayFill(0.92f);
				table15.addCell(cell104);
				PdfPCell cell105 = new PdfPCell(new Paragraph(distrubution.getComputerLoadReceptable(), font9));
				cell105.setGrayFill(0.92f);
				cell105.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell105);

				PdfPCell cell106 = new PdfPCell(new Paragraph("Description:", font9));
				cell106.setBorder(PdfPCell.NO_BORDER);
				// cell106.setGrayFill(0.92f);
				table15.addCell(cell106);
				PdfPCell cell107 = new PdfPCell(new Paragraph(distrubution.getComputerLoadReceptableDesc(), font9));
				// cell107.setGrayFill(0.92f);
				cell107.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell107);

				PdfPCell cell108 = new PdfPCell(new Paragraph(
						"System branch circuits run in (metallic conduits / non metallic conduits):", font9));
				cell108.setBorder(PdfPCell.NO_BORDER);
				cell108.setGrayFill(0.92f);
				table15.addCell(cell108);
				PdfPCell cell109 = new PdfPCell(new Paragraph(distrubution.getBranchCircuteRun(), font9));
				cell109.setGrayFill(0.92f);
				cell109.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell109);

				PdfPCell cell112 = new PdfPCell(new Paragraph("Description:", font9));
				cell112.setBorder(PdfPCell.NO_BORDER);
				// cell112.setGrayFill(0.92f);
				table15.addCell(cell112);
				PdfPCell cell113 = new PdfPCell(new Paragraph(distrubution.getBranchCircuteRunDesc(), font9));
				// cell113.setGrayFill(0.92f);
				cell113.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell113);

				PdfPCell cell114 = new PdfPCell(new Paragraph(
						"Frequently cyclid loads (disks/tapes etc) power circuit run seperately or in same physical closure:",
						font9));
				cell114.setBorder(PdfPCell.NO_BORDER);
				cell114.setGrayFill(0.92f);
				table15.addCell(cell114);
				PdfPCell cell115 = new PdfPCell(new Paragraph(distrubution.getFrequencyCyclidLoads(), font9));
				cell115.setGrayFill(0.92f);
				cell115.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell115);

				PdfPCell cell110 = new PdfPCell(new Paragraph("Description::", font9));
				cell110.setBorder(PdfPCell.NO_BORDER);
				// cell110.setGrayFill(0.92f);
				table15.addCell(cell110);
				PdfPCell cell111 = new PdfPCell(new Paragraph(distrubution.getFrequencyCyclidLoadsDesc(), font9));
				// cell111.setGrayFill(0.92f);
				cell111.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell111);

				PdfPCell cell116 = new PdfPCell(new Paragraph(
						"Any phase conductor, neutral conductor or PE condcutors daisy chained??:", font9));
				cell116.setBorder(PdfPCell.NO_BORDER);
				cell116.setGrayFill(0.92f);
				table15.addCell(cell116);
				PdfPCell cell117 = new PdfPCell(new Paragraph(distrubution.getConductors(), font9));
				cell117.setGrayFill(0.92f);
				cell117.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell117);

				PdfPCell cell118 = new PdfPCell(new Paragraph("Description:", font9));
				cell118.setBorder(PdfPCell.NO_BORDER);
				// cell118.setGrayFill(0.92f);
				table15.addCell(cell118);
				PdfPCell cell119 = new PdfPCell(new Paragraph(distrubution.getConductorsDesc(), font9));
				// cell119.setGrayFill(0.92f);
				cell119.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell119);
				document.add(table15);

				document.close();
				writer.close();

			} catch (

			Exception e) {
				logger.error("Error while generating PDF in Power Earthing Data section"+e.getMessage());
				throw new PowerEarthingDataException("Error while generating PDF in Power Earthing Data section"+e.getMessage());
			}

		} else

		{
			logger.error("Error while generating PDF in Power Earthing Data section");
			throw new PowerEarthingDataException("Error while generating PDF in Power Earthing Data section");
		}
	}
}
