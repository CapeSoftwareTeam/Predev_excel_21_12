package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.InspectionException;
import com.capeelectric.exception.PdfException;
import com.capeelectric.model.Circuit;
import com.capeelectric.model.ConsumerUnit;
import com.capeelectric.model.DbParentArray;
import com.capeelectric.model.InspectionInnerObservations;
import com.capeelectric.model.InspectionOuterObservation;
import com.capeelectric.model.IpaoInspection;
import com.capeelectric.model.IsolationCurrent;
import com.capeelectric.model.MainsFirstArray;
import com.capeelectric.model.MainsSecondArray;
import com.capeelectric.model.ObsFormArrayA;
import com.capeelectric.model.ObsFormArrayB;
import com.capeelectric.model.ObsFormArrayC;
import com.capeelectric.model.PeriodicInspection;
import com.capeelectric.model.PeriodicInspectionComment;
import com.capeelectric.model.SubDbParent;
import com.capeelectric.model.SubDistribution;
import com.capeelectric.model.SubDistributionOne;
import com.capeelectric.repository.InspectionRepository;
import com.capeelectric.service.InspectionServicePDF;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class InspectionServiceImplPDF implements InspectionServicePDF {

	private static final Logger logger = LoggerFactory.getLogger(InspectionServiceImplPDF.class);

//	@Autowired
//	private InspectionRepository inspectionRepository;
	
	@Override
	public List<PeriodicInspection> printInspectionDetails(String userName, Integer siteId,Optional<PeriodicInspection> periodicInspection) throws InspectionException, PdfException {

		logger.debug("called printInspectionDetails function userName: {},siteId : {}", userName,siteId);
		
		if (userName != null && !userName.isEmpty() && siteId != null) {

			Document document = new Document(PageSize.A4, 36, 36, 50, 36);
			document.setMargins(68, 68, 62, 68);

			try {
				PdfWriter writer = PdfWriter.getInstance(document,
						new FileOutputStream("PrintInspectionDetailsData.pdf"));

//				Optional<PeriodicInspection> inspectionDetails = inspectionRepository.findBySiteId(siteId);
				PeriodicInspection inspection1 = periodicInspection.get();

//				List<IpaoInspection> ipo = inspection1.getIpaoInspection();
//				IpaoInspection ipoInspection1 = ipo.get(0);
//
//				List<ConsumerUnit> consumer1 = ipoInspection1.getConsumerUnit();
////				ConsumerUnit consumerUnit = consumer1.get(0);
//
//				List<Circuit> circuitDetails = ipoInspection1.getCircuit();
//				Circuit circuit1 = circuitDetails.get(0);
//
//				List<IsolationCurrent> isolationCurrentDetails = ipoInspection1.getIsolationCurrent();
//				IsolationCurrent isolationCurrent1 = isolationCurrentDetails.get(0);

				List<PeriodicInspectionComment> reportComments = inspection1.getPeriodicInspectorComment();
				PeriodicInspectionComment comments = reportComments.get(0);

				document.open();

				Font font11B = new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Paragraph paragraph1 = new Paragraph("TIC of LV electrical installation", font11B);
				paragraph1.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph1);

				float[] pointColumnWidths1 = { 100F };
				PdfPTable part3 = new PdfPTable(pointColumnWidths1);

				part3.setWidthPercentage(100); // Width 100%
				part3.setSpacingBefore(5f); // Space before table
				part3.setSpacingAfter(5f); // Space after table
				part3.setWidthPercentage(100);
				part3.getDefaultCell().setBorder(0);

				PdfPCell basic = new PdfPCell(new Paragraph("Part - 3: Inspection",
						new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
				basic.setBackgroundColor(new GrayColor(0.82f));
				basic.setHorizontalAlignment(Element.ALIGN_LEFT);
				basic.setBorder(PdfPCell.NO_BORDER);
				part3.addCell(basic);
				document.add(part3);

				Font noteFont = new Font(BaseFont.createFont(), 8, Font.NORMAL | Font.NORMAL, BaseColor.BLACK);
				Paragraph paragraph4 = new Paragraph(
						"Note: For periodic inspection, a visual inspection should be made to find out the external condition of all electrical equipment which is not concealed. Further detailed inspection, including partial dismantling of equipment (as required), should be carried out as agreed with the customer.",
						noteFont);
				document.add(paragraph4);
				int consumerRemarksIndex;
				for (IpaoInspection ipoInspection : inspection1.getIpaoInspection()) {
					
					consumerRemarksIndex = 0;
					
					if (ipoInspection.getInspectionFlag()!=null&&!ipoInspection.getInspectionFlag().equalsIgnoreCase("R")) {

//						inspectionIteration(document, arr, consumerUnit, circuit, isolationCurrent);
						Font font10B = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
						float[] observationspointColumnWidths = { 200F };
						float[] pointColumnWidths = { 120F, 80F };
						PdfPTable table100 = new PdfPTable(pointColumnWidths);

						table100.setWidthPercentage(100); // Width 100%
						table100.setSpacingBefore(10f); // Space before table
						table100.setSpacingAfter(5f); // Space after table
						table100.setWidthPercentage(100);
						table100.getDefaultCell().setBorder(0);

						PdfPCell cell80 = new PdfPCell(new Paragraph("Location number:",
								new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
						cell80.setBackgroundColor(new BaseColor(203, 183, 162));
						cell80.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell80.setBorder(PdfPCell.NO_BORDER);
						table100.addCell(cell80);
						PdfPCell cell81 = new PdfPCell(new Paragraph(ipoInspection.getLocationNumber().toString(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
						cell81.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell81.setBackgroundColor(new BaseColor(203, 183, 162));
						cell81.setBorder(PdfPCell.NO_BORDER);
						table100.addCell(cell81);
						document.add(table100);

						PdfPTable table1001 = new PdfPTable(pointColumnWidths);

						table1001.setWidthPercentage(100); // Width 100%
						table1001.setSpacingBefore(5f); // Space before table
						table1001.setSpacingAfter(5f); // Space after table
						table1001.setWidthPercentage(100);
						table1001.getDefaultCell().setBorder(0);
						PdfPCell cell36 = new PdfPCell(new Paragraph("Location name:",
								new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
						cell36.setBackgroundColor(new BaseColor(203, 183, 162));
						cell36.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell36.setBorder(PdfPCell.NO_BORDER);
						table1001.addCell(cell36);
						PdfPCell cell811 = new PdfPCell(new Paragraph(ipoInspection.getLocationName(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
						cell811.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell811.setBackgroundColor(new BaseColor(203, 183, 162));
						cell811.setBorder(PdfPCell.NO_BORDER);
						table1001.addCell(cell811);
						
						document.add(table1001);
						
//						float[] pointColumnWidths = { 120F, 80F };
						PdfPTable tableElectricShock = new PdfPTable(pointColumnWidths);

						tableElectricShock.setWidthPercentage(100); // Width 100%
						tableElectricShock.setSpacingBefore(10f); // Space before table
						tableElectricShock.setSpacingAfter(5f); // Space after table
						tableElectricShock.setWidthPercentage(100);
						tableElectricShock.getDefaultCell().setBorder(0);

						PdfPCell cellElectricShock = new PdfPCell(new Paragraph("Protection against electric shock :",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cellElectricShock.setBackgroundColor(new GrayColor(0.93f));
						cellElectricShock.setHorizontalAlignment(Element.ALIGN_LEFT);
						cellElectricShock.setBorder(PdfPCell.NO_BORDER);
						tableElectricShock.addCell(cellElectricShock);
						PdfPCell cellElectricShock1 = new PdfPCell(new Paragraph(ipoInspection.getElectricShock(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cellElectricShock1.setHorizontalAlignment(Element.ALIGN_LEFT);
						cellElectricShock1.setBackgroundColor(new GrayColor(0.93f));
						cellElectricShock1.setBorder(PdfPCell.NO_BORDER);
						tableElectricShock.addCell(cellElectricShock1);
						
						document.add(tableElectricShock);
						
						float[] pointColumnWidths4 = { 100F };
						PdfPTable electricShockAcc = new PdfPTable(pointColumnWidths4);

						electricShockAcc.setWidthPercentage(100); // Width 100%
						electricShockAcc.setSpacingBefore(5f); // Space before table
//						part31.setSpacingAfter(5f); // Space after table
						electricShockAcc.setWidthPercentage(100);
						electricShockAcc.getDefaultCell().setBorder(0);

						PdfPCell electricSection = new PdfPCell(new Paragraph(ipoInspection.getElectricShock(),
								new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
						electricSection.setBackgroundColor(new GrayColor(0.82f));
						electricSection.setHorizontalAlignment(Element.ALIGN_LEFT);
						electricSection.setBorder(PdfPCell.NO_BORDER);
						electricShockAcc.addCell(electricSection);
						document.add(electricShockAcc);
						
						//Basic Protection:
						//Faultprotection:
						if (ipoInspection.getElectricShock()
								.equalsIgnoreCase("Protective measures with basic and fault protection")) {

//							PdfPTable faltProtection = new PdfPTable(pointColumnWidths4);
//
//							faltProtection.setWidthPercentage(100); // Width 100%
//							faltProtection.setSpacingBefore(5f); // Space before table
////							part31.setSpacingAfter(5f); // Space after table
//							faltProtection.setWidthPercentage(100);
//							faltProtection.getDefaultCell().setBorder(0);
//
//							PdfPCell electricSectionfaltProtection = new PdfPCell(new Paragraph(
//									"Basic Protection  :" + ipoInspection.getBasicProtection() + "                     "
//											+ "Faultprotection  :" + ipoInspection.getFaultProtection(),
//									new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
//							electricSectionfaltProtection.setBackgroundColor(new GrayColor(0.82f));
//							electricSectionfaltProtection.setHorizontalAlignment(Element.ALIGN_LEFT);
//							electricSectionfaltProtection.setBorder(PdfPCell.NO_BORDER);
//							faltProtection.addCell(electricSectionfaltProtection);
//							document.add(faltProtection);

							PdfPTable table1 = new PdfPTable(pointColumnWidths);

							table1.setWidthPercentage(100); // Width 100%
							table1.setSpacingBefore(5f); // Space before table
							table1.setSpacingAfter(5f); // Space after table
							table1.setWidthPercentage(100);
							table1.getDefaultCell().setBorder(0);

							PdfPCell cell = new PdfPCell(new Paragraph(ipoInspection.getBasicProtection(),
									new Font(BaseFont.createFont(), 10, Font.NORMAL)));
							table1.addCell(new Phrase("Basic Protection  :",
									new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//						    cell.setFixedHeight(30f);
							cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell.setBorder(PdfPCell.NO_BORDER);
							table1.addCell(cell);

							PdfPCell cell37 = new PdfPCell(new Paragraph("Faultprotection  :",
									new Font(BaseFont.createFont(), 10, Font.NORMAL)));
							cell37.setBackgroundColor(new GrayColor(0.93f));
							cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell37.setBorder(PdfPCell.NO_BORDER);
							table1.addCell(cell37);
							PdfPCell cell1 = new PdfPCell(new Paragraph(ipoInspection.getFaultProtection(),
									new Font(BaseFont.createFont(), 10, Font.NORMAL)));
							cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell1.setBackgroundColor(new GrayColor(0.93f));
							cell1.setBorder(PdfPCell.NO_BORDER);
							table1.addCell(cell1);

//							PdfPCell cell378 = new PdfPCell(new Paragraph("Faultprotection  :",
//									new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//							cell378.setBackgroundColor(new GrayColor(0.93f));
//							cell378.setHorizontalAlignment(Element.ALIGN_LEFT);
//							cell378.setBorder(PdfPCell.NO_BORDER);
//							table1.addCell(cell378);
//							PdfPCell cell11 = new PdfPCell(new Paragraph(ipoInspection.getFaultProtection(),
//									new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//							cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
//							cell11.setBackgroundColor(new GrayColor(0.93f));
//							cell11.setBorder(PdfPCell.NO_BORDER);
//							table1.addCell(cell11);

							document.add(table1);

						}
						
						//Limited value:
						//Protective measure:
						//Additional measure:
						else if (ipoInspection.getElectricShock()
								.equalsIgnoreCase("Protective measures (Limitation of Voltage or current)")) {

							PdfPTable table1 = new PdfPTable(pointColumnWidths);

							table1.setWidthPercentage(100); // Width 100%
							table1.setSpacingBefore(5f); // Space before table
							table1.setSpacingAfter(5f); // Space after table
							table1.setWidthPercentage(100);
							table1.getDefaultCell().setBorder(0);

							PdfPCell cell = new PdfPCell(new Paragraph(ipoInspection.getLimitedValue(),
									new Font(BaseFont.createFont(), 10, Font.NORMAL)));
							table1.addCell(
									new Phrase("Limited value  :", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//						    cell.setFixedHeight(30f);
							cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell.setBorder(PdfPCell.NO_BORDER);
							table1.addCell(cell);

							PdfPCell cell37 = new PdfPCell(new Paragraph("Protective measure  :",
									new Font(BaseFont.createFont(), 10, Font.NORMAL)));
							cell37.setBackgroundColor(new GrayColor(0.93f));
							cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell37.setBorder(PdfPCell.NO_BORDER);
							table1.addCell(cell37);
							PdfPCell cell1 = new PdfPCell(new Paragraph(ipoInspection.getProtectiveMeasure(),
									new Font(BaseFont.createFont(), 10, Font.NORMAL)));
							cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell1.setBackgroundColor(new GrayColor(0.93f));
							cell1.setBorder(PdfPCell.NO_BORDER);
							table1.addCell(cell1);

							PdfPCell cell378 = new PdfPCell(new Paragraph("Additional measure  :",
									new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//							cell378.setBackgroundColor(new GrayColor(0.93f));
							cell378.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell378.setBorder(PdfPCell.NO_BORDER);
							table1.addCell(cell378);
							PdfPCell cell11 = new PdfPCell(new Paragraph(ipoInspection.getAdditionalMeasure(),
									new Font(BaseFont.createFont(), 10, Font.NORMAL)));
							cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
//							cell11.setBackgroundColor(new GrayColor(0.93f));
							cell11.setBorder(PdfPCell.NO_BORDER);
							table1.addCell(cell11);
							document.add(table1);

						}
						
						//Additonal protection:
						//Faultprotection:
						else {

							PdfPTable table1 = new PdfPTable(pointColumnWidths);
							table1.setWidthPercentage(100); // Width 100%
							table1.setSpacingBefore(5f); // Space before table
							table1.setSpacingAfter(5f); // Space after table
							table1.setWidthPercentage(100);
							table1.getDefaultCell().setBorder(0);

							PdfPCell cell = new PdfPCell(new Paragraph(ipoInspection.getAdditionalProtection(),
									new Font(BaseFont.createFont(), 10, Font.NORMAL)));
							table1.addCell(new Phrase("Additonal protection  :",
									new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//						    cell.setFixedHeight(30f);
							cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell.setBorder(PdfPCell.NO_BORDER);
							table1.addCell(cell);

							PdfPCell cell37 = new PdfPCell(new Paragraph("Faultprotection  :",
									new Font(BaseFont.createFont(), 10, Font.NORMAL)));
							cell37.setBackgroundColor(new GrayColor(0.93f));
							cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell37.setBorder(PdfPCell.NO_BORDER);
							table1.addCell(cell37);
							PdfPCell cell1 = new PdfPCell(new Paragraph(ipoInspection.getFaultProtectionDesc(),
									new Font(BaseFont.createFont(), 10, Font.NORMAL)));
							cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell1.setBackgroundColor(new GrayColor(0.93f));
							cell1.setBorder(PdfPCell.NO_BORDER);
							table1.addCell(cell1);
							document.add(table1);
						}
						

						PdfPTable section1 = new PdfPTable(pointColumnWidths4);

						section1.setWidthPercentage(100); // Width 100%
						section1.setSpacingBefore(5f); // Space before table
//						section1.setSpacingAfter(5f); // Space after table
						section1.setWidthPercentage(100);
						section1.getDefaultCell().setBorder(0);

						PdfPCell mainsIncoming = new PdfPCell(new Paragraph("Section - 1: Mains Incoming",
								new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
						mainsIncoming.setBackgroundColor(new GrayColor(0.82f));
						mainsIncoming.setHorizontalAlignment(Element.ALIGN_LEFT);
						mainsIncoming.setBorder(PdfPCell.NO_BORDER);
						section1.addCell(mainsIncoming);
						document.add(section1);
						

							float[] pointColumnWidths140 = { 80F, 100F };
							PdfPTable table120 = new PdfPTable(pointColumnWidths140);

							table120.setWidthPercentage(100); // Width 100%
							table120.setSpacingBefore(10f); // Space before table
//						table120.setSpacingAfter(5f); // Space after table
							table120.setWidthPercentage(100);
							table120.getDefaultCell().setBorder(0);

							

							PdfPCell cell142 = new PdfPCell(new Paragraph("Distribution board name :",
									new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						    cell142.setBackgroundColor(new GrayColor(0.93f));
							cell142.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell142.setBorder(PdfPCell.NO_BORDER);
							table120.addCell(cell142);
							PdfPCell cell143 = new PdfPCell(new Paragraph(ipoInspection.getMainsDistribution(),
									new Font(BaseFont.createFont(), 10, Font.NORMAL)));
							cell143.setHorizontalAlignment(Element.ALIGN_LEFT);
						    cell143.setBackgroundColor(new GrayColor(0.93f));
							cell143.setBorder(PdfPCell.NO_BORDER);
							table120.addCell(cell143);

							PdfPCell cell144 = new PdfPCell(
									new Paragraph("Location :", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//							cell144.setBackgroundColor(new GrayColor(0.93f));
							cell144.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell144.setBorder(PdfPCell.NO_BORDER);
							table120.addCell(cell144);
							PdfPCell cell145 = new PdfPCell(new Paragraph(ipoInspection.getMainsLocation(),
									new Font(BaseFont.createFont(), 10, Font.NORMAL)));
							cell145.setHorizontalAlignment(Element.ALIGN_LEFT);
//							cell145.setBackgroundColor(new GrayColor(0.93f));
							cell145.setBorder(PdfPCell.NO_BORDER);
							table120.addCell(cell145);
							
							PdfPCell cell140 = new PdfPCell(new Paragraph("Source Details :",
									new Font(BaseFont.createFont(), 10, Font.NORMAL)));
							cell140.setBackgroundColor(new GrayColor(0.93f));
							cell140.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell140.setBorder(PdfPCell.NO_BORDER);
							table120.addCell(cell140);
							PdfPCell cell141 = new PdfPCell(
									new Paragraph(ipoInspection.getMainsSourceDetails(),
											new Font(BaseFont.createFont(), 10, Font.NORMAL)));
							cell141.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell141.setBackgroundColor(new GrayColor(0.93f));
							cell141.setBorder(PdfPCell.NO_BORDER);
							table120.addCell(cell141);

							document.add(table120);
							
							
							PdfPTable section1a = new PdfPTable(pointColumnWidths4);

							section1a.setWidthPercentage(100); // Width 100%
							section1a.setSpacingBefore(5f); // Space before table
//							section1a.setSpacingAfter(5f); // Space after table
							section1a.setWidthPercentage(100);
							section1a.getDefaultCell().setBorder(0);

							PdfPCell incoming = new PdfPCell(new Paragraph("1.1: Incoming Equipment",
									new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
							incoming.setBackgroundColor(new GrayColor(0.82f));
							incoming.setHorizontalAlignment(Element.ALIGN_LEFT);
							incoming.setBorder(PdfPCell.NO_BORDER);
							section1a.addCell(incoming);
							document.add(section1a);


						PdfPTable table1 = new PdfPTable(pointColumnWidths);

						table1.setWidthPercentage(100); // Width 100%
						table1.setSpacingBefore(5f); // Space before table
						table1.setSpacingAfter(5f); // Space after table
						table1.setWidthPercentage(100);
						table1.getDefaultCell().setBorder(0);

						PdfPCell cell = new PdfPCell(new Paragraph(ipoInspection.getServiceCable(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						table1.addCell(
								new Phrase("Cable/Busbar trunking:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//					    cell.setFixedHeight(30f);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell.setBorder(PdfPCell.NO_BORDER);
						table1.addCell(cell);

						PdfPCell cell37 = new PdfPCell(new Paragraph("Incoming Protective Device:",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell37.setBackgroundColor(new GrayColor(0.93f));
						cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell37.setBorder(PdfPCell.NO_BORDER);
						table1.addCell(cell37);
						PdfPCell cell1 = new PdfPCell(new Paragraph(ipoInspection.getServiceFuse(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell1.setBackgroundColor(new GrayColor(0.93f));
						cell1.setBorder(PdfPCell.NO_BORDER);
						table1.addCell(cell1);

						PdfPCell cell2 = new PdfPCell(new Paragraph(ipoInspection.getMeterDistributor(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						table1.addCell(new Phrase("Energy Meters for Mains Incoming:",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//					cell2.setFixedHeight(30f);
						cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell2.setBorder(PdfPCell.NO_BORDER);
						table1.addCell(cell2);

						PdfPCell cell38 = new PdfPCell(new Paragraph("Energy Meters for Outgoings:",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell38.setBackgroundColor(new GrayColor(0.93f));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell38.setBorder(PdfPCell.NO_BORDER);
						table1.addCell(cell38);
						PdfPCell cell3 = new PdfPCell(new Paragraph(ipoInspection.getMeterConsumer(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell3.setBackgroundColor(new GrayColor(0.93f));
						cell3.setBorder(PdfPCell.NO_BORDER);
						table1.addCell(cell3);

						PdfPCell cell4 = new PdfPCell(new Paragraph(ipoInspection.getMeterEqu(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						table1.addCell(new Phrase("Over Voltage Protection (overvoltage category maintained):",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//					    cell4.setFixedHeight(30f);
						cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell4.setBorder(PdfPCell.NO_BORDER);
						table1.addCell(cell4);
						
						PdfPCell Tov = new PdfPCell(new Paragraph("TOV Control measures on LV side due to HV fault :",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						Tov.setBackgroundColor(new GrayColor(0.93f));
						Tov.setHorizontalAlignment(Element.ALIGN_LEFT);
						Tov.setBorder(PdfPCell.NO_BORDER);
						table1.addCell(Tov);
						PdfPCell Tov1 = new PdfPCell(new Paragraph(ipoInspection.getTovMeasuresLVHV(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						Tov1.setHorizontalAlignment(Element.ALIGN_LEFT);
						Tov1.setBackgroundColor(new GrayColor(0.93f));
						Tov1.setBorder(PdfPCell.NO_BORDER);
						table1.addCell(Tov1);
						
						PdfPCell Iso = new PdfPCell(new Paragraph("Isolator (means to isolate the incoming supply system) :",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//						Iso.setBackgroundColor(new GrayColor(0.93f));
						Iso.setHorizontalAlignment(Element.ALIGN_LEFT);
						Iso.setBorder(PdfPCell.NO_BORDER);
						table1.addCell(Iso);
						PdfPCell Iso2 = new PdfPCell(new Paragraph(ipoInspection.getIsolator(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						Iso2.setHorizontalAlignment(Element.ALIGN_LEFT);
//						Iso2.setBackgroundColor(new GrayColor(0.93f));
						Iso2.setBorder(PdfPCell.NO_BORDER);
						table1.addCell(Iso2);

						document.add(table1);

						Font noteFont1 = new Font(BaseFont.createFont(), 8, Font.NORMAL | Font.NORMAL, BaseColor.BLACK);
						Paragraph paragraph8 = new Paragraph(
								"Note: Where inadequacies in distributor’s equipment are encountered, it is recommended that the user informs this to the appropriate authority.",
								noteFont1);
						document.add(paragraph8);

						float[] pointColumnWidths5 = { 100F };
						PdfPTable section1b = new PdfPTable(pointColumnWidths5);

						section1b.setWidthPercentage(100); // Width 100%
						section1b.setSpacingBefore(10f); // Space before table
						section1b.setSpacingAfter(5f); // Space after table
						section1b.setWidthPercentage(100);
						section1b.getDefaultCell().setBorder(0);

						PdfPCell arrangements = new PdfPCell(new Paragraph(
								"1.2: Arrangements for parallel or switched alternative sources of supply",
								new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
						arrangements.setBackgroundColor(new GrayColor(0.82f));
						arrangements.setHorizontalAlignment(Element.ALIGN_LEFT);
						arrangements.setBorder(PdfPCell.NO_BORDER);
						section1b.addCell(arrangements);
						document.add(section1b);

						float[] pointColumnWidths11 = { 200F, 50F };
						PdfPTable table2 = new PdfPTable(pointColumnWidths11);

						table2.setWidthPercentage(100); // Width 100%
//					table2.setSpacingBefore(5f); // Space before table
						table2.setSpacingAfter(5f); // Space after table
						table2.setWidthPercentage(100);
						table2.getDefaultCell().setBorder(0);

						PdfPCell cell6 = new PdfPCell(new Paragraph(ipoInspection.getEarthingArrangement(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						table2.addCell(
								new Phrase("Dedicated earthing arrangement independent to that of public supply:",
										new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell6.setFixedHeight(15f);
						cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell6.setBorder(PdfPCell.NO_BORDER);
						table2.addCell(cell6);

						PdfPCell cell40 = new PdfPCell(new Paragraph(
								"Presence of adequate arrangements where generator to operate in parallel with the public supply system:",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell40.setBackgroundColor(new GrayColor(0.93f));
						cell40.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell40.setBorder(PdfPCell.NO_BORDER);
						table2.addCell(cell40);
						PdfPCell cell7 = new PdfPCell(new Paragraph(ipoInspection.getAdequateArrangement(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell7.setFixedHeight(15f);
						cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell7.setBackgroundColor(new GrayColor(0.93f));
						cell7.setBorder(PdfPCell.NO_BORDER);
						table2.addCell(cell7);

						PdfPCell cell8 = new PdfPCell(new Paragraph(ipoInspection.getConnectionGenerator(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						table2.addCell(new Phrase(
								"Correct connections of generator in parallel. (note: Special attention to circulating currents):",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell8.setFixedHeight(15f);
						cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell8.setBorder(PdfPCell.NO_BORDER);
						table2.addCell(cell8);

						PdfPCell cell41 = new PdfPCell(
								new Paragraph("Compatibility of characteristics of means of generation:",
										new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell41.setBackgroundColor(new GrayColor(0.93f));
						cell41.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell41.setBorder(PdfPCell.NO_BORDER);
						table2.addCell(cell41);
						PdfPCell cell9 = new PdfPCell(new Paragraph(ipoInspection.getCompatibilityCharacteristics(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell9.setFixedHeight(15f);
						cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell9.setBackgroundColor(new GrayColor(0.93f));
						cell9.setBorder(PdfPCell.NO_BORDER);
						table2.addCell(cell9);

						PdfPCell cell10 = new PdfPCell(new Paragraph(ipoInspection.getAutomaticDisconnectGenerator(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						table2.addCell(new Phrase(
								"Means to provide automatic disconnection of generator in the event of loss of public supply system or voltage or frequency deviation beyond declared values:",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell10.setFixedHeight(15f);
						cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell10.setBorder(PdfPCell.NO_BORDER);
						table2.addCell(cell10);

						PdfPCell cell42 = new PdfPCell(new Paragraph(
								"Means to prevent connection of generator in the event of loss of public supply system or voltage or frequency deviation beyond declared values:",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell42.setBackgroundColor(new GrayColor(0.93f));
						cell42.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell42.setBorder(PdfPCell.NO_BORDER);
						table2.addCell(cell42);
						PdfPCell cell11 = new PdfPCell(new Paragraph(ipoInspection.getPreventConnectGenerator(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell11.setFixedHeight(15f);
						cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell11.setBackgroundColor(new GrayColor(0.93f));
						cell11.setBorder(PdfPCell.NO_BORDER);
						table2.addCell(cell11);

						PdfPCell cell12 = new PdfPCell(new Paragraph(ipoInspection.getIsolateGenerator(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						table2.addCell(new Phrase("Means to isolate generator from the public supply system :",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell12.setFixedHeight(15f);
						cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell12.setBorder(PdfPCell.NO_BORDER);
						table2.addCell(cell12);
						document.add(table2);

						document.newPage();
						PdfPTable section1c = new PdfPTable(pointColumnWidths5);

						section1c.setWidthPercentage(100); // Width 100%
						section1c.setSpacingBefore(5f); // Space before table
						section1c.setSpacingAfter(5f); // Space after table
						section1c.setWidthPercentage(100);
						section1c.getDefaultCell().setBorder(0);

						PdfPCell Automatic = new PdfPCell(
								new Paragraph("1.3: Automatic disconnection of supply",
										new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
						Automatic.setBackgroundColor(new GrayColor(0.82f));
						Automatic.setHorizontalAlignment(Element.ALIGN_LEFT);
						Automatic.setBorder(PdfPCell.NO_BORDER);
						section1c.addCell(Automatic);
						document.add(section1c);

						float[] pointColumnWidths3 = { 200F, 40F };
						PdfPTable table3 = new PdfPTable(pointColumnWidths3);

						table3.setWidthPercentage(100); // Width 100%
//					table3.setSpacingBefore(10f); // Space before table
						table3.setSpacingAfter(5f); // Space after table
						table3.setWidthPercentage(100);
						table3.getDefaultCell().setBorder(0);

						PdfPCell cell14 = new PdfPCell(new Paragraph(ipoInspection.getMainEarting(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						table3.addCell(new Phrase("Main earthing / bonding arrangements:",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//					cell14.setFixedHeight(25f);
						cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell14.setBorder(PdfPCell.NO_BORDER);
						table3.addCell(cell14);

						PdfPCell cell43 = new PdfPCell(new Paragraph(
								"Presence and adequacy of energy suppliers earthing arrangement or installation earth electrode arrangement:",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell43.setBackgroundColor(new GrayColor(0.93f));
						cell43.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell43.setBorder(PdfPCell.NO_BORDER);
						table3.addCell(cell43);
						PdfPCell cell15 = new PdfPCell(new Paragraph(ipoInspection.getEarthElectordeArrangement(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell15.setBackgroundColor(new GrayColor(0.93f));
						cell15.setBorder(PdfPCell.NO_BORDER);
						table3.addCell(cell15);

						PdfPCell cell16 = new PdfPCell(new Paragraph(ipoInspection.getEarthConductorConnection(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						table3.addCell(new Phrase("Presence and adequacy of earthing conductor and connections:",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//					cell16.setFixedHeight(30f);
						cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell16.setBorder(PdfPCell.NO_BORDER);
						table3.addCell(cell16);

						PdfPCell cell44 = new PdfPCell(new Paragraph("Accessibility of earthing conductor connections:",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell44.setBackgroundColor(new GrayColor(0.93f));
						cell44.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell44.setBorder(PdfPCell.NO_BORDER);
						table3.addCell(cell44);
						PdfPCell cell17 = new PdfPCell(new Paragraph(ipoInspection.getAccessibility(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell17.setBackgroundColor(new GrayColor(0.93f));
						cell17.setBorder(PdfPCell.NO_BORDER);
						table3.addCell(cell17);

						PdfPCell cell18 = new PdfPCell(new Paragraph(ipoInspection.getAainProtectBonding(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						table3.addCell(new Phrase(
								"Presence and adequacy of main protective bonding conductors and connections (colour, sizes, termination, and provision of independent earthing):",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//					cell18.setFixedHeight(45f);
						cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell18.setBorder(PdfPCell.NO_BORDER);
						table3.addCell(cell18);

						PdfPCell cell45 = new PdfPCell(
								new Paragraph("Accessibility of all protective bonding connections:",
										new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell45.setBackgroundColor(new GrayColor(0.93f));
						cell45.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell45.setBorder(PdfPCell.NO_BORDER);
						table3.addCell(cell45);
						PdfPCell cell19 = new PdfPCell(new Paragraph(ipoInspection.getAllProtectBonding(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell19.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell19.setBackgroundColor(new GrayColor(0.93f));
						cell19.setBorder(PdfPCell.NO_BORDER);
						table3.addCell(cell19);

						PdfPCell cell20 = new PdfPCell(new Paragraph(ipoInspection.getAllAppropriateLocation(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						table3.addCell(new Phrase(
								"Presence and adequacy of electrical earthing/bonding labels at all appropriate locations:",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//					cell20.setFixedHeight(35f);
						cell20.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell20.setBorder(PdfPCell.NO_BORDER);
						table3.addCell(cell20);

						PdfPCell cell46 = new PdfPCell(new Paragraph("Accessibility of FELV requirements satisfied:",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell46.setBackgroundColor(new GrayColor(0.93f));
						cell46.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell46.setBorder(PdfPCell.NO_BORDER);
						table3.addCell(cell46);
						PdfPCell cell21 = new PdfPCell(new Paragraph(ipoInspection.getFelvRequirement(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell21.setBackgroundColor(new GrayColor(0.93f));
						cell21.setBorder(PdfPCell.NO_BORDER);
						table3.addCell(cell21);
						document.add(table3);

						PdfPTable section1d = new PdfPTable(pointColumnWidths5);
						section1d.setWidthPercentage(100); // Width 100%
						section1d.setSpacingBefore(5f); // Space before table
						section1d.setSpacingAfter(3f); // Space after table
						section1d.setWidthPercentage(100);
						section1d.getDefaultCell().setBorder(0);

						PdfPCell protection = new PdfPCell(new Paragraph("1.4: Other methods of protection ",
								new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
						protection.setBackgroundColor(new GrayColor(0.82f));
						protection.setHorizontalAlignment(Element.ALIGN_LEFT);
						protection.setBorder(PdfPCell.NO_BORDER);
						section1d.addCell(protection);
						document.add(section1d);

						Paragraph paragraph13 = new Paragraph(
								"Applicable to locations where automatic disconnection of supply is not employed. If any of the methods listed below are employed details should be provided on separate page",
								noteFont1);
						document.add(paragraph13);

						Paragraph gap2 = new Paragraph(6, " ", font10B);
						document.add(gap2);

						PdfPTable Basic = new PdfPTable(pointColumnWidths5);
						Basic.setWidthPercentage(100); // Width 100%
						Basic.setSpacingBefore(5f); // Space before table
						Basic.setSpacingAfter(3f); // Space after table
						Basic.setWidthPercentage(100);
						Basic.getDefaultCell().setBorder(0);

						PdfPCell fault = new PdfPCell(new Paragraph("1.4.1: Basic and fault protection.",
								new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
						fault.setBackgroundColor(new GrayColor(0.82f));
						fault.setHorizontalAlignment(Element.ALIGN_LEFT);
						fault.setBorder(PdfPCell.NO_BORDER);
						Basic.addCell(fault);
						document.add(Basic);

						PdfPTable table4 = new PdfPTable(pointColumnWidths3);

						table4.setWidthPercentage(100); // Width 100%
						table4.setSpacingBefore(5f); // Space before table
						table4.setSpacingAfter(5f); // Space after table
						table4.setWidthPercentage(100);
						table4.getDefaultCell().setBorder(0);

						PdfPCell cell22 = new PdfPCell(new Paragraph(ipoInspection.getSelvSystem(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						table4.addCell(new Phrase("SELV system, including the source and associated circuits:",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell22.setFixedHeight(10f);
						cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell22.setBorder(PdfPCell.NO_BORDER);
						table4.addCell(cell22);

						PdfPCell cell47 = new PdfPCell(
								new Paragraph("PELV system, including the source and associated circuits:",
										new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell47.setBackgroundColor(new GrayColor(0.93f));
						cell47.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell47.setBorder(PdfPCell.NO_BORDER);
						table4.addCell(cell47);
						PdfPCell cell23 = new PdfPCell(new Paragraph(ipoInspection.getPelvSystem(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell23.setFixedHeight(10f);
						cell23.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell23.setBackgroundColor(new GrayColor(0.93f));
						cell23.setBorder(PdfPCell.NO_BORDER);
						table4.addCell(cell23);

						PdfPCell cell24 = new PdfPCell(new Paragraph(ipoInspection.getDoubleInsulation(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						table4.addCell(new Phrase(
								"Double insulation (Class II or equivalent equipment and associated circuits):",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell24.setFixedHeight(10f);
						cell24.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell24.setBorder(PdfPCell.NO_BORDER);
						table4.addCell(cell24);

						PdfPCell cell48 = new PdfPCell(new Paragraph("Reinforced insulation:",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell48.setBackgroundColor(new GrayColor(0.93f));
						cell48.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell48.setBorder(PdfPCell.NO_BORDER);
						table4.addCell(cell48);
						PdfPCell cell25 = new PdfPCell(new Paragraph(ipoInspection.getReinforcedInsulation(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell25.setFixedHeight(10f);
						cell25.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell25.setBackgroundColor(new GrayColor(0.93f));
						cell25.setBorder(PdfPCell.NO_BORDER);
						table4.addCell(cell25);

						PdfPCell cell26 = new PdfPCell(new Paragraph(ipoInspection.getBasicElectricalSepartion(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						table4.addCell(
								new Phrase("Electrical separation for one item of equipment (shaver supply unit):",
										new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell26.setFixedHeight(10f);
						cell26.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell26.setBorder(PdfPCell.NO_BORDER);
						table4.addCell(cell26);

						document.add(table4);

						PdfPTable BasicPro = new PdfPTable(pointColumnWidths5);
						BasicPro.setWidthPercentage(100); // Width 100%
						BasicPro.setSpacingBefore(5f); // Space before table
						BasicPro.setSpacingAfter(3f); // Space after table
						BasicPro.setWidthPercentage(100);
						BasicPro.getDefaultCell().setBorder(0);

						PdfPCell liveParts = new PdfPCell(
								new Paragraph("1.4.2: Basic protection (prevention of contact with live parts).",
										new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
						liveParts.setBackgroundColor(new GrayColor(0.82f));
						liveParts.setHorizontalAlignment(Element.ALIGN_LEFT);
						liveParts.setBorder(PdfPCell.NO_BORDER);
						BasicPro.addCell(liveParts);
						document.add(BasicPro);

						PdfPTable table5 = new PdfPTable(pointColumnWidths3);

						table5.setWidthPercentage(100); // Width 100%
//						table5.setSpacingBefore(5f); // Space before table
						table5.setSpacingAfter(5f); // Space after table
						table5.setWidthPercentage(100);
						table5.getDefaultCell().setBorder(0);

						PdfPCell cell27 = new PdfPCell(new Paragraph(ipoInspection.getInsulationLiveParts(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						table5.addCell(new Phrase(
								"Insulation of live parts (conductors completely covered with durable insulating material):",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//					cell27.setFixedHeight(35f);
						cell27.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell27.setBorder(PdfPCell.NO_BORDER);
						table5.addCell(cell27);

						PdfPCell cell50 = new PdfPCell(new Paragraph("Barriers or enclosures (correct IP rating):",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell50.setBackgroundColor(new GrayColor(0.93f));
						cell50.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell50.setBorder(PdfPCell.NO_BORDER);
						table5.addCell(cell50);
						PdfPCell cell29 = new PdfPCell(new Paragraph(ipoInspection.getBarriersEnclosers(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell29.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell29.setBackgroundColor(new GrayColor(0.93f));
						cell29.setBorder(PdfPCell.NO_BORDER);
						table5.addCell(cell29);

						PdfPCell cell30 = new PdfPCell(new Paragraph(ipoInspection.getObstacles(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						table5.addCell(new Phrase("Obstacles:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//					cell30.setFixedHeight(25f);
						cell30.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell30.setBorder(PdfPCell.NO_BORDER);
						table5.addCell(cell30);

						PdfPCell cell51 = new PdfPCell(new Paragraph("Placing out of reach:",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell51.setBackgroundColor(new GrayColor(0.93f));
						cell51.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell51.setBorder(PdfPCell.NO_BORDER);
						table5.addCell(cell51);
						PdfPCell cell31 = new PdfPCell(new Paragraph(ipoInspection.getPlacingOutReach(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell31.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell31.setBackgroundColor(new GrayColor(0.93f));
						cell31.setBorder(PdfPCell.NO_BORDER);
						table5.addCell(cell31);
						document.add(table5);

						PdfPTable Fault = new PdfPTable(pointColumnWidths5);
						Fault.setWidthPercentage(100); // Width 100%
						Fault.setSpacingBefore(5f); // Space before table
						Fault.setSpacingAfter(3f); // Space after table
						Fault.setWidthPercentage(100);
						Fault.getDefaultCell().setBorder(0);

						PdfPCell protection1 = new PdfPCell(new Paragraph("1.4.3: Fault protection.",
								new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
						protection1.setBackgroundColor(new GrayColor(0.82f));
						protection1.setHorizontalAlignment(Element.ALIGN_LEFT);
						protection1.setBorder(PdfPCell.NO_BORDER);
						Fault.addCell(protection1);
						document.add(Fault);

						PdfPTable table6 = new PdfPTable(pointColumnWidths3);

						table6.setWidthPercentage(100); // Width 100%
//						table6.setSpacingBefore(5f); // Space before table
//					    table6.setSpacingAfter(10f); // Space after table
						table6.setWidthPercentage(100);
						table6.getDefaultCell().setBorder(0);

						PdfPCell cell32 = new PdfPCell(new Paragraph(ipoInspection.getFaultNonConductLocation(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						table6.addCell(new Phrase("Non-Conducting location – earth free local equipotential bonding:",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//					    cell32.setFixedHeight(35f);
						cell32.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell32.setBorder(PdfPCell.NO_BORDER);
						table6.addCell(cell32);

						PdfPCell cell52 = new PdfPCell(new Paragraph("Electrical separation:",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell52.setBackgroundColor(new GrayColor(0.93f));
						cell52.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell52.setBorder(PdfPCell.NO_BORDER);
						table6.addCell(cell52);
						PdfPCell cell33 = new PdfPCell(new Paragraph(ipoInspection.getFaultElectricalSepartion(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell33.setBackgroundColor(new GrayColor(0.93f));
						cell33.setBorder(PdfPCell.NO_BORDER);
						table6.addCell(cell33);
						document.add(table6);

						PdfPTable Additional = new PdfPTable(pointColumnWidths5);
						Additional.setWidthPercentage(100); // Width 100%
						Additional.setSpacingBefore(10f); // Space before table
						Additional.setSpacingAfter(5f); // Space after table
						Additional.setWidthPercentage(100);
						Additional.getDefaultCell().setBorder(0);

						PdfPCell protection2 = new PdfPCell(new Paragraph("1.4.4: Additional protection.",
								new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
						protection2.setBackgroundColor(new GrayColor(0.82f));
						protection2.setHorizontalAlignment(Element.ALIGN_LEFT);
						protection2.setBorder(PdfPCell.NO_BORDER);
						Additional.addCell(protection2);
						document.add(Additional);

						PdfPTable table7 = new PdfPTable(pointColumnWidths3);

						table7.setWidthPercentage(100); // Width 100%
//						table7.setSpacingBefore(5f); // Space before table
//						table7.setSpacingAfter(5f); // Space after table
						table7.setWidthPercentage(100);
						table7.getDefaultCell().setBorder(0);

						PdfPCell cell34 = new PdfPCell(new Paragraph(ipoInspection.getOperatingCurrent(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						table7.addCell(new Phrase("RCD(s) not exceeding 30 mA operating current:",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//					cell34.setFixedHeight(30f);
						cell34.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell34.setBorder(PdfPCell.NO_BORDER);
						table7.addCell(cell34);

						PdfPCell cell53 = new PdfPCell(new Paragraph("Supplementary bonding:",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell53.setBackgroundColor(new GrayColor(0.93f));
						cell53.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell53.setBorder(PdfPCell.NO_BORDER);
						table7.addCell(cell53);
						PdfPCell cell35 = new PdfPCell(new Paragraph(ipoInspection.getSupplementaryBonding(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell35.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell35.setBackgroundColor(new GrayColor(0.93f));
						cell35.setBorder(PdfPCell.NO_BORDER);
						table7.addCell(cell35);
						document.add(table7);

//						Font font10N = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.NORMAL, BaseColor.BLACK);
//						Paragraph paragraph18 = new Paragraph(
//								"SPECIFIC INSPECTION EXAMPLES to be included in the report as appropriate to the installation",
//								font10N);
//						document.add(paragraph18);

						PdfPTable specificInsp = new PdfPTable(pointColumnWidths5);
						specificInsp.setWidthPercentage(100); // Width 100%
						specificInsp.setSpacingBefore(10f); // Space before table
//						specificInsp.setSpacingAfter(3f); // Space after table
						specificInsp.getDefaultCell().setBorder(0);

						PdfPCell inspection = new PdfPCell(new Paragraph(
								"SPECIFIC INSPECTION EXAMPLES to be included in the report as appropriate to the installation",
								new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
						inspection.setBackgroundColor(new GrayColor(0.82f));
						inspection.setHorizontalAlignment(Element.ALIGN_LEFT);
						inspection.setBorder(PdfPCell.NO_BORDER);
						specificInsp.addCell(inspection);
						document.add(specificInsp);

						PdfPTable SpecificInspection = new PdfPTable(pointColumnWidths5);
						SpecificInspection.setWidthPercentage(100); // Width 100%
						SpecificInspection.setSpacingBefore(5f); // Space before table
						SpecificInspection.setSpacingAfter(3f); // Space after table
						SpecificInspection.getDefaultCell().setBorder(10);
						
						float[] pointColumnWidths51 = { 100F };

						
						PdfPTable section1e = new PdfPTable(pointColumnWidths51);
						section1e.setWidthPercentage(100); // Width 100%
						section1e.setSpacingBefore(5f); // Space before table
						section1e.setSpacingAfter(5f); // Space after table
						section1e.getDefaultCell().setBorder(0);

						for(MainsFirstArray mainsFirstArray:ipoInspection.getMainsFirstArray()) {
							
							PdfPCell distributionEqui = new PdfPCell(new Paragraph("1.5: Distribution equipment's",
									new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
							distributionEqui.setBackgroundColor(new GrayColor(0.82f));
							distributionEqui.setHorizontalAlignment(Element.ALIGN_LEFT);
							distributionEqui.setBorder(PdfPCell.NO_BORDER);
							section1e.addCell(distributionEqui);
							document.add(section1e);
							
							float[] pointColumnWidths2 = { 30F, 255F, 65F };


							PdfPTable table80 = new PdfPTable(pointColumnWidths2); // 3 columns.
							table80.setWidthPercentage(100); // Width 100%
							table80.setSpacingBefore(5f); // Space before table
							table80.setSpacingAfter(5f); // Space after table
							table80.setWidthPercentage(100);

							addRow(table80, "1",
									"Selection of protective devices and bases correct type and rating (no signs of unacceptable thermal damage, arcing or overheating)",
									mainsFirstArray.getDeEquipA());
							addRow(table80, "2", "Presence of main switches linked where required",
									mainsFirstArray.getDeEquipB());
							addRow(table80, "3", "Operation of main switches (functional checks)",
									mainsFirstArray.getDeEquipC());
							addRow(table80, "4",
									"Manual operation of circuit breakers and RCD’s to prove functionally",
									mainsFirstArray.getDeEquipD());
							addRow(table80, "5",
									"Confirmation that integral test button / switch causes RCD’s to trip when operated (functional check)",
									mainsFirstArray.getDeEquipE());
							addRow(table80, "6", "RCD’s provided for fault protection, where specified",
									mainsFirstArray.getDeEquipF());
							addRow(table80, "7", "RCD’s provided for additional protection, where specified",
									mainsFirstArray.getDeEquipG());
							addRow(table80, "8",
									"Confirmation of over voltage protection (SPD’s) provided where specified",
									mainsFirstArray.getDeEquipH());
							addRow(table80, "9", "Confirmation of indication that SPD is functional",
									mainsFirstArray.getDeEquipI());
//							addRow(table80, "21", "Single pole protective devices in line conductor only",
//									mainsFirstArray.getDeEquip());

							document.add(table80);

							PdfPTable section1f = new PdfPTable(pointColumnWidths51);
							section1f.setWidthPercentage(100); // Width 100%
							section1f.setSpacingBefore(5f); // Space before table
							section1f.setSpacingAfter(5f); // Space after table
							section1f.getDefaultCell().setBorder(0);

							PdfPCell warningNote = new PdfPCell(new Paragraph("1.6: Warning notices",
									new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
							warningNote.setBackgroundColor(new GrayColor(0.82f));
							warningNote.setHorizontalAlignment(Element.ALIGN_LEFT);
							warningNote.setBorder(PdfPCell.NO_BORDER);
							section1f.addCell(warningNote);
							document.add(section1f);

							PdfPTable table81 = new PdfPTable(pointColumnWidths2); // 3 columns.
							table81.setWidthPercentage(100); // Width 100%
							table81.setSpacingBefore(5f); // Space before table
							table81.setSpacingAfter(5f); // Space after table
							table81.setWidthPercentage(100);

							addRow(table81, "1", "Presence of RCD quarterly test notice at or near origin",
									mainsFirstArray.getWarningNoticeA());
							addRow(table81, "2",
									"Presence of diagrams charts or schedules at or near each distribution board where required",
									mainsFirstArray.getWarningNoticeB());
							addRow(table81, "3",
									"presence of nonstandard (mixed) cable colour warning notice near appropriate distribution board, as required",
									mainsFirstArray.getWarningNoticeC());
							addRow(table81, "4", "Presence of  cable tag on both ends",
									mainsFirstArray.getWarningNoticeD());
							addRow(table81, "5",
									"Presence of alternative supply - warning notice at or near the origin",
									mainsFirstArray.getWarningNoticeE());
							addRow(table81, "6",
									"Presence of alternative supply - warning notice at or near the meter position, if remote from origin",
									mainsFirstArray.getWarningNoticeF());
							addRow(table81, "7",
									"Presence of alternative supply - warning notice at or near the distribution board to which alternative sources are connected",
									mainsFirstArray.getWarningNoticeG());
							addRow(table81, "8",
									"Presence of alternative supply - warning notice at or near all points of isolation of ALL sources of supply",
									mainsFirstArray.getWarningNoticeH());
							addRow(table81, "9", "Presence of next inspection recommendation label",
									mainsFirstArray.getWarningNoticeI());
							addRow(table81, "10", "Presence of source labelling at incoming feeders",
									mainsFirstArray.getWarningNoticeJ());
							addRow(table81, "11",
									"Presence of source labelling at outgoing feeders",
									mainsFirstArray.getWarningNoticeK());
							addRow(table81, "12",
									"Presence of mimic diagram:",
									mainsFirstArray.getWarningNoticeL());
							addRow(table81, "13", "Colour of mimic diagram done as per voltage level",
									mainsFirstArray.getWarningNoticeM());
							
							document.add(table81);
							
							PdfPTable section1g = new PdfPTable(pointColumnWidths5);
							section1g.setWidthPercentage(100); // Width 100%
							section1g.setSpacingBefore(5f); // Space before table
							section1g.setSpacingAfter(5f); // Space after table
							section1g.getDefaultCell().setBorder(0);

							PdfPCell insulation = new PdfPCell(
									new Paragraph("1.7: Circuit conductors and its insulation",
											new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
							insulation.setBackgroundColor(new GrayColor(0.82f));
							insulation.setHorizontalAlignment(Element.ALIGN_LEFT);
							insulation.setBorder(PdfPCell.NO_BORDER);
							section1g.addCell(insulation);
							document.add(section1g);

							PdfPTable table9 = new PdfPTable(pointColumnWidths2);
							table9.setWidthPercentage(100); // Width 100%
							table9.setSpacingBefore(5f); // Space before table
//						table9.setSpacingAfter(5f); // Space after table
							table9.getDefaultCell().setBorder(0);

							addRow(table9, "1",
									"Identification of conductors including main earthing / bonding arrangements",
									mainsFirstArray.getCircuitConductorsA());
							addRow(table9, "2",
									"Examination of insulation of live parts not damaged during erection",
									mainsFirstArray.getCircuitConductorsB());
							addRow(table9, "3",
									"Adequacy of conductors for current-carrying capacity with respect to type and nature of the installation",
									mainsFirstArray.getCircuitConductorsC());
							addRow(table9, "4",
									"Presence, adequacy, and correct termination of protective conductors",
									mainsFirstArray.getCircuitConductorsD());
							addRow(table9, "5",
									"Provision of fire barriers, sealing arrangements so as to minimize the spread of fire",
									mainsFirstArray.getCircuitConductorsE());
							addRow(table9, "6", "Segregation/separation of Band I (ELV) and Band II (LV) circuits",
									mainsFirstArray.getCircuitConductorsF());
							addRow(table9, "7", "Segregation/separation of electrical and non-electrical services",
									mainsFirstArray.getCircuitConductorsG());
							addRow(table9, "8", "No basic insulation of a conductor visible outside enclosure",
									mainsFirstArray.getCircuitConductorsH());
							addRow(table9, "9", "Connections of live conductors adequately enclosed",
									mainsFirstArray.getCircuitConductorsI());
							addRow(table9, "10",
									"Adequately connected at the point of entry to enclosure (glands, bushes etc.)",
									mainsFirstArray.getCircuitConductorsJ());
							addRow(table9, "11",
									"Adequacy of connections, including protective conductors, within accessories and fixed and stationary equipment",
									mainsFirstArray.getCircuitConductorsK());

							document.add(table9);

							PdfPTable section1h = new PdfPTable(pointColumnWidths5);
							section1h.setWidthPercentage(100); // Width 100%
							section1h.setSpacingBefore(10f); // Space before table
							section1h.setSpacingAfter(5f); // Space after table
							section1h.getDefaultCell().setBorder(0);

							PdfPCell equipment = new PdfPCell(new Paragraph("1.8: Circuit equipements",
									new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
							equipment.setBackgroundColor(new GrayColor(0.82f));
							equipment.setHorizontalAlignment(Element.ALIGN_LEFT);
							equipment.setBorder(PdfPCell.NO_BORDER);
							section1h.addCell(equipment);
							document.add(section1h);

							PdfPTable table90 = new PdfPTable(pointColumnWidths2);
							table90.setWidthPercentage(100); // Width 100%
							table90.setSpacingBefore(5f); // Space before table
							table90.setSpacingAfter(5f); // Space after table
							table90.getDefaultCell().setBorder(0);

							addRow1(table90, "1",
									"Adequacy of protective devices, type and fault current rating for fault protection",
									mainsFirstArray.getCircuitEquipmentA());
							addRow1(table90, "2",
									"Co-ordination between conductors and overload protective devices",
									mainsFirstArray.getCircuitEquipmentB());
							addRow1(table90, "3",
									"Additional protection by RCD’s having residual operating current (I∆n) not exceeding 30mA for circuits used to supply mobile equipment not exceeding 32A rating for use outdoors in all cases",
									mainsFirstArray.getCircuitEquipmentC());
							addRow1(table90, "4",
									"Additional protection by RCD’s having residual operating current (I∆n) not exceeding 30mA for all socket outlets of rating 20A or less provided for use by ordinary persons unless exempt",
									mainsFirstArray.getCircuitEquipmentD());
							addRow1(table90, "5",
									"Additional protection by RCD’s having residual operating current (I∆n) not exceeding 30mA for cables concealed in walls at a depth of less than 50mm",
									mainsFirstArray.getCircuitEquipmentE());
							addRow1(table90, "6",
									"Additional protection by RCD’s having residual operating current (I∆n) not exceeding 30mA for Cables concealed in walls / sections containing metal sections regardless of depth",
									mainsFirstArray.getCircuitEquipmentF());
							addRow1(table90, "7", "Condition of circuit accessories",
									mainsFirstArray.getCircuitEquipmentG());
							addRow1(table90, "8", "Suitability of circuit accessories for external influences",
									mainsFirstArray.getCircuitEquipmentH());
							addRow1(table90, "9",
									"Condition of accessories including socket-outlets, switches and joint boxes (Circuit accessories not damaged, securely fixed, correctly connected, suitable for external influences)",
									mainsFirstArray.getCircuitEquipmentI());
							addRow1(table90, "10",
									"Single-pole devices for switching or protection in line conductors only",
									mainsFirstArray.getCircuitEquipmentJ());
							addRow1(table90, "11",
									"Presence, operation, and correct location of appropriate devices for isolation and switching",
									mainsFirstArray.getCircuitEquipmentK());

							document.add(table90);	
						}
						
						for(MainsSecondArray mainsSecondArray:ipoInspection.getMainsSecondArray()) {
							
							PdfPTable section1h = new PdfPTable(pointColumnWidths5);
							section1h.setWidthPercentage(100); // Width 100%
							section1h.setSpacingBefore(5f); // Space before table
//						section1h.setSpacingAfter(5f); // Space after table
							section1h.getDefaultCell().setBorder(0);

							PdfPCell circuitCables = new PdfPCell(new Paragraph("1.9: Circuit cables",
									new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
							circuitCables.setBackgroundColor(new GrayColor(0.82f));
							circuitCables.setHorizontalAlignment(Element.ALIGN_LEFT);
							circuitCables.setBorder(PdfPCell.NO_BORDER);
							section1h.addCell(circuitCables);
							document.add(section1h);
							
							float[] pointColumnWidths2 = { 30F, 255F, 65F };

							PdfPTable table91 = new PdfPTable(pointColumnWidths2);
							table91.setWidthPercentage(100); // Width 100%
							table91.setSpacingBefore(10f); // Space before table
							table91.setSpacingAfter(5f); // Space after table
							table91.getDefaultCell().setBorder(0);

							addRow1(table91, "1",
									"Cable installation methods (including support) suitable for the location(s) and external influences (Cables correctly supported throughout)",
									mainsSecondArray.getCablesContolsA());
							addRow1(table91, "2",
									"Examination of cables for signs of mechanical damage during installation",
									mainsSecondArray.getCablesContolsB());
							addRow1(table91, "3",
									"Non-Sheathed cables protected by enclosure in conduit, ducting or trucking",
									mainsSecondArray.getCablesContolsC());
							addRow1(table91, "4", "Suitability of containment systems including flexible conduit",
									mainsSecondArray.getCablesContolsD());
							addRow1(table91, "5", "Correct temperature rating of cable insulation",
									mainsSecondArray.getCablesContolsE());
							addRow1(table91, "6", "Cables correctly terminated in enclosures",
									mainsSecondArray.getCablesContolsF());
							addRow1(table91, "7",
									"Wiring systems and cable installation methods/ practices with regard to the type and nature of installation and external influences",
									mainsSecondArray.getCablesContolsG());
							addRow1(table91, "8",
									"Cables concealed under floors above ceilings, in wall adequately protected against damage by contact with fixings",
									mainsSecondArray.getCablesContolsH());
							addRow1(table91, "9",
									"Cables and conductors correctly terminated, enclosed and with no undue mechanical strain",
									mainsSecondArray.getCablesContolsI());
							addRow1(table91, "10",
									"Unused cable entry holes or gland holes seized or sealed properly",
									mainsSecondArray.getCablesContolsJ());

							document.add(table91);
							
							PdfPTable section1i = new PdfPTable(pointColumnWidths5);
							section1i.setWidthPercentage(100); // Width 100%
							section1i.setSpacingBefore(5f); // Space before table
							section1i.setSpacingAfter(5f); // Space after table
							section1i.getDefaultCell().setBorder(0);

							PdfPCell isolator = new PdfPCell(new Paragraph("1.10: Isolator",
									new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
							isolator.setBackgroundColor(new GrayColor(0.82f));
							isolator.setHorizontalAlignment(Element.ALIGN_LEFT);
							isolator.setBorder(PdfPCell.NO_BORDER);
							section1i.addCell(isolator);
							document.add(section1i);

							PdfPTable table10 = new PdfPTable(pointColumnWidths2);
							table10.setWidthPercentage(100); // Width 100%
							table10.setSpacingBefore(5f); // Space before table
							table10.setSpacingAfter(5f); // Space after table
							table10.getDefaultCell().setBorder(0);

							addRow1(table10, "1", "Isolators - Presence of appropriate devices ",
									mainsSecondArray.getIsolatorsControlsA());
							addRow1(table10, "2", "Isolators – Condition of appropriate devices ",
									mainsSecondArray.getIsolatorsControlsB());
							addRow1(table10, "3",
									"Isolators - Location of appropriate devices (state if local or remote from equipment in question)",
									mainsSecondArray.getIsolatorsControlsC());
							addRow1(table10, "4", "Isolators - Capable of being secured in OFF position",
									mainsSecondArray.getIsolatorsControlsD());
							addRow1(table10, "5", "Isolators - Correct operation verified (functional checks)",
									mainsSecondArray.getIsolatorsControlsE());
							addRow1(table10, "6",
									"Isolators - The installation, circuit or Section thereof that will be isolated is clearly identified by location and/or durable marking",
									mainsSecondArray.getIsolatorsControlsF());
							addRow1(table10, "7",
									"Isolators - Warning label posted in the situation where live Sections cannot be isolated by the operation of single device",
									mainsSecondArray.getIsolatorsControlsG());

							document.add(table10);

							PdfPTable section1j = new PdfPTable(pointColumnWidths5);
							section1j.setWidthPercentage(100); // Width 100%
							section1j.setSpacingBefore(5f); // Space before table
							section1j.setSpacingAfter(5f); // Space after table
							section1j.getDefaultCell().setBorder(0);

							PdfPCell switching = new PdfPCell(new Paragraph("1.11: Switching",
									new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
							switching.setBackgroundColor(new GrayColor(0.82f));
							switching.setHorizontalAlignment(Element.ALIGN_LEFT);
							switching.setBorder(PdfPCell.NO_BORDER);
							section1j.addCell(switching);
							document.add(section1j);

							PdfPTable table111 = new PdfPTable(pointColumnWidths2);
							table111.setWidthPercentage(100); // Width 100%
							table111.setSpacingBefore(5f); // Space before table
							table111.setSpacingAfter(5f); // Space after table
							table111.getDefaultCell().setBorder(0);

							addRow1(table111, "1",
									"Switching off for mechanical maintenance - Presence of appropriate devices",
									mainsSecondArray.getSwitchingDevicesA());
							addRow1(table111, "2",
									"Switching off for mechanical maintenance - Condition of appropriate devices",
									mainsSecondArray.getSwitchingDevicesB());
							addRow1(table111, "3",
									"Switching off for mechanical maintenance - Acceptable location (state if local or remote from equipment in question)",
									mainsSecondArray.getSwitchingDevicesC());
							addRow1(table111, "4",
									"Switching off for mechanical maintenance - Capable of being secured in OFF position",
									mainsSecondArray.getSwitchingDevicesD());
							addRow1(table111, "5",
									"Switching off for mechanical maintenance - Correct operation verified (functional check)",
									mainsSecondArray.getSwitchingDevicesE());
							addRow1(table111, "6",
									"Switching off for mechanical maintenance - The circuit or section there of that will be disconnected clearly identified by location and / or durable marking",
									mainsSecondArray.getSwitchingDevicesF());
							addRow1(table111, "7",
									"Switching off for mechanical maintenance - Warning label posted in situations where live parts cannot be isolated by the operation of a single device",
									mainsSecondArray.getSwitchingDevicesG());
							addRow1(table111, "8", "Emergency switching / stopping - Presence of appropriate devices",
									mainsSecondArray.getSwitchingDevicesH());
							addRow1(table111, "9", "Emergency switching / stopping – Condition of appropriate devices",
									mainsSecondArray.getSwitchingDevicesI());
							addRow1(table111, "10",
									"Emergency switching / stopping - Location of appropriate devices (readily accessible for operation where danger might occur)",
									mainsSecondArray.getSwitchingDevicesJ());
							addRow1(table111, "11",
									"Emergency switching / stopping - Correct operation verified (functional check)",
									mainsSecondArray.getSwitchingDevicesK());
							addRow1(table111, "12",
									"Emergency switching / stopping - The installation circuit or Section there of that will be disconnected clearly identified by location and / or durable marking",
									mainsSecondArray.getSwitchingDevicesL());
//							addRow2(table111, "20", "Functional switching - Presence of appropriate devices",
//									mainsSecondArray.getSwitchingDevicesM());
//							addRow2(table111, "21", "Functional switching - Location of appropriate devices",
//									mainsSecondArray.getSwitchingDevices());
//							addRow2(table111, "22",
//									"Functional switching - Correct operation verified (functional check)",
//									mainsSecondArray.getSwitchingDevicesA());
							document.add(table111);

						}
						Integer mainsObservationCount=1;
						for(ObsFormArrayA obsFormArrayA:ipoInspection.getObsFormArrayA()) {
							if(obsFormArrayA.getObsAFlag()!=null&&!obsFormArrayA.getObsAFlag().equalsIgnoreCase("R")&&obsFormArrayA.getMainsObservation()!=null&&!obsFormArrayA.getMainsObservation().isEmpty()) {
								PdfPTable mainsObservation = new PdfPTable(observationspointColumnWidths);
								mainsObservation.setWidthPercentage(100); // Width 100%
								mainsObservation.setSpacingBefore(5f); // Space before table
								mainsObservation.setWidthPercentage(100);
								mainsObservation.getDefaultCell().setBorder(0);
								
								PdfPCell cell154 = new PdfPCell(new Paragraph("Remarks/Observation -"+mainsObservationCount +":"+"    "+obsFormArrayA.getMainsObservation(),
										new Font(BaseFont.createFont(), 10, Font.NORMAL)));
								cell154.setBackgroundColor(new GrayColor(0.93f));
								cell154.setHorizontalAlignment(Element.ALIGN_LEFT);
								cell154.setBorder(PdfPCell.NO_BORDER);
								mainsObservation.addCell(cell154);
//								PdfPCell cell155 = new PdfPCell(
//										new Paragraph(obsFormArrayA.getMainsObservation(),
//												new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//								cell155.setHorizontalAlignment(Element.ALIGN_LEFT);
//								cell155.setBackgroundColor(new GrayColor(0.93f));
//								cell155.setBorder(PdfPCell.NO_BORDER);
//								mainsObservation.addCell(cell155);
								
								document.add(mainsObservation);
								++mainsObservationCount;
							}
						}
						
						

//						if (ipoInspection.getSpecificInspectionRe() != null) {
//							PdfPCell cell502 = new PdfPCell(
//									new Paragraph("", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
////							cell502.setBackgroundColor(new GrayColor(0.93f));
//							cell502.setHorizontalAlignment(Element.ALIGN_LEFT);
//							cell502.setBorder(PdfPCell.NO_BORDER);
//							SpecificInspection.addCell(cell502);
//							PdfPCell cell5020 = new PdfPCell(new Paragraph(ipoInspection.getSpecificInspectionRe(),
//									new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//							cell5020.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
////							cell5020.setBackgroundColor(new GrayColor(0.93f));
//							cell5020.setBorder(PdfPCell.NO_BORDER);
//							SpecificInspection.addCell(cell5020);
//							document.add(SpecificInspection);
//						} else {
//
//							PdfPCell noData = new PdfPCell(
//									new Paragraph("Specific Inspection Examples Data Not Available",
//											new Font(BaseFont.createFont(), 10, Font.NORMAL)));
////							noData.setBackgroundColor(new GrayColor(0.82f));
//							noData.setHorizontalAlignment(Element.ALIGN_LEFT);
//							noData.setBorder(PdfPCell.NO_BORDER);
//							SpecificInspection.addCell(noData);
//							document.add(SpecificInspection);
//
//						}
						document.newPage();
						
						for(DbParentArray dbParentArray:ipoInspection.getDbParentArray()) {
							

							if (dbParentArray.getDbParentFlag()!=null&&!dbParentArray.getDbParentFlag().equalsIgnoreCase("R")) {

								float[] pointColumnWidths53 = { 100F };
								PdfPTable section2 = new PdfPTable(pointColumnWidths53);
								section2.setWidthPercentage(100); // Width 100%
								section2.setSpacingBefore(10f); // Space before table
//								section2.setSpacingAfter(3f); // Space after table
								section2.setWidthPercentage(100);
								section2.getDefaultCell().setBorder(0);

								PdfPCell distribution = new PdfPCell(new Paragraph(
										"Section - 2: Distribution And SubDistribution Boards",
										new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
								distribution.setBackgroundColor(new GrayColor(0.82f));
								distribution.setHorizontalAlignment(Element.ALIGN_LEFT);
								distribution.setBorder(PdfPCell.NO_BORDER);
								section2.addCell(distribution);
								document.add(section2);

								float[] pointColumnWidths141 = { 80F, 100F };
								float[] pointColumnWidths6 = { 30F, 255F, 65F };
								
								PdfPTable table121 = new PdfPTable(pointColumnWidths141);

								table121.setWidthPercentage(100); // Width 100%
								table121.setSpacingBefore(10f); // Space before table
//							table120.setSpacingAfter(5f); // Space after table
								table121.setWidthPercentage(100);
								table121.getDefaultCell().setBorder(0);

								

								PdfPCell cell148 = new PdfPCell(new Paragraph("Distribution board name :",
										new Font(BaseFont.createFont(), 10, Font.NORMAL)));
								cell148.setBackgroundColor(new GrayColor(0.93f));
								cell148.setHorizontalAlignment(Element.ALIGN_LEFT);
								cell148.setBorder(PdfPCell.NO_BORDER);
								table121.addCell(cell148);
								PdfPCell cell149 = new PdfPCell(new Paragraph(dbParentArray.getDistributionBoard(),
										new Font(BaseFont.createFont(), 10, Font.NORMAL)));
								cell149.setHorizontalAlignment(Element.ALIGN_LEFT);
								cell149.setBackgroundColor(new GrayColor(0.93f));
								cell149.setBorder(PdfPCell.NO_BORDER);
								table121.addCell(cell149);

								PdfPCell cell150 = new PdfPCell(
										new Paragraph("Location :", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//								cell144.setBackgroundColor(new GrayColor(0.93f));
								cell150.setHorizontalAlignment(Element.ALIGN_LEFT);
								cell150.setBorder(PdfPCell.NO_BORDER);
								table121.addCell(cell150);
								PdfPCell cell151 = new PdfPCell(new Paragraph(dbParentArray.getDistributionLocation(),
										new Font(BaseFont.createFont(), 10, Font.NORMAL)));
								cell151.setHorizontalAlignment(Element.ALIGN_LEFT);
//								cell145.setBackgroundColor(new GrayColor(0.93f));
								cell151.setBorder(PdfPCell.NO_BORDER);
								table121.addCell(cell151);
								
								PdfPCell cell152 = new PdfPCell(new Paragraph("Source Details :",
										new Font(BaseFont.createFont(), 10, Font.NORMAL)));
								cell152.setBackgroundColor(new GrayColor(0.93f));
								cell152.setHorizontalAlignment(Element.ALIGN_LEFT);
								cell152.setBorder(PdfPCell.NO_BORDER);
								table121.addCell(cell152);
								PdfPCell cell153 = new PdfPCell(
										new Paragraph(dbParentArray.getDistributionSourceDetails(),
												new Font(BaseFont.createFont(), 10, Font.NORMAL)));
								cell153.setHorizontalAlignment(Element.ALIGN_LEFT);
								cell153.setBackgroundColor(new GrayColor(0.93f));
								cell153.setBorder(PdfPCell.NO_BORDER);
								table121.addCell(cell153);

								document.add(table121);

						for (ConsumerUnit consumerUnit :dbParentArray.getConsumerUnit()) {
							
							 PdfPTable section2a = new PdfPTable(pointColumnWidths53);
							 section2a.setWidthPercentage(100); // Width 100%
							 section2a.setSpacingBefore(8f); // Space before table
							 section2a.setSpacingAfter(5f); // Space after table
							 section2a.getDefaultCell().setBorder(0);

							PdfPCell otherMethod = new PdfPCell(
									new Paragraph("2.1: Other Methods of Protection",
											new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
							otherMethod.setBackgroundColor(new GrayColor(0.82f));
							otherMethod.setHorizontalAlignment(Element.ALIGN_LEFT);
							otherMethod.setBorder(PdfPCell.NO_BORDER);
							section2a.addCell(otherMethod);
							document.add(section2a);
							

								PdfPTable section2a1 = new PdfPTable(pointColumnWidths53);
								section2a1.setWidthPercentage(100); // Width 100%
								section2a1.setSpacingBefore(8f); // Space before table
								section2a1.setSpacingAfter(5f); // Space after table
								section2a1.getDefaultCell().setBorder(0);

								PdfPCell consumerunit = new PdfPCell(
										new Paragraph("2.1.1: Consumer unit(s) / distribution board's",
												new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
								consumerunit.setBackgroundColor(new GrayColor(0.82f));
								consumerunit.setHorizontalAlignment(Element.ALIGN_LEFT);
								consumerunit.setBorder(PdfPCell.NO_BORDER);
								section2a1.addCell(consumerunit);
								document.add(section2a1);

								

								PdfPTable table8 = new PdfPTable(pointColumnWidths6); // 3 columns.
								table8.setWidthPercentage(100); // Width 100%
								table8.setSpacingBefore(5f); // Space before table
								table8.setSpacingAfter(5f); // Space after table
								table8.setWidthPercentage(100);

								addRow(table8, "1",
										"Adequacy of access and working space for items of electrical equipment including switchgear",
										consumerUnit.getAccessWorking());
								addRow(table8, "2", "Security of fixing ", consumerUnit.getSecurityFixing());
								addRow(table8, "3", "Insulation of live parts not damaged during erection",
										consumerUnit.getLivePartsDamage());
								addRow(table8, "4", "Adequacy / security of barriers",
										consumerUnit.getSecurityBarriers());
								addRow(table8, "5", "Suitability of enclosure(s) for IP and fire ratings",
										consumerUnit.getSuitabilityEnclosure());
								addRow(table8, "6", "Enclosure not damaged during installation",
										consumerUnit.getEnclosureDamaged());
								addRow(table8, "7", "Presence and effectiveness of obstacles",
										consumerUnit.getPresenceObstacles());
								addRow(table8, "8", "Placing out of reach", consumerUnit.getPlacingOutOfConsumer());
								addRow(table8, "9", "Protection against mechanical damage where cables enter equipment",
										consumerUnit.getMechanicalDamage());
								addRow(table8, "10",
										"Protection against electromagnetic/heating effects where cables enter ferromagnetic enclosures",
										consumerUnit.getElectromagnetic());
								addRow(table8, "11",
										"Confirmation that all conductor connections including connections to busbars are correctly located in terminals and are tight and secure",
										consumerUnit.getAllConductorCon());

								document.add(table8);

								PdfPTable section2a2 = new PdfPTable(pointColumnWidths53);
								section2a2.setWidthPercentage(100); // Width 100%
								section2a2.setSpacingBefore(5f); // Space before table
								section2a2.setSpacingAfter(5f); // Space after table
								section2a2.getDefaultCell().setBorder(0);

								PdfPCell distributionEqui = new PdfPCell(new Paragraph("2.1.2: Distribution equipment's",
										new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
								distributionEqui.setBackgroundColor(new GrayColor(0.82f));
								distributionEqui.setHorizontalAlignment(Element.ALIGN_LEFT);
								distributionEqui.setBorder(PdfPCell.NO_BORDER);
								section2a2.addCell(distributionEqui);
								document.add(section2a2);

								PdfPTable table80 = new PdfPTable(pointColumnWidths6); // 3 columns.
								table80.setWidthPercentage(100); // Width 100%
								table80.setSpacingBefore(5f); // Space before table
								table80.setSpacingAfter(5f); // Space after table
								table80.setWidthPercentage(100);

								addRow(table80, "1",
										"Selection of protective devices and bases correct type and rating (no signs of unacceptable thermal damage, arcing or overheating)",
										consumerUnit.getBasesCorrectType());
								addRow(table80, "2", "Presence of main switches linked where required",
										consumerUnit.getPresenceMainSwitches());
								addRow(table80, "3", "Operation of main switches (functional checks)",
										consumerUnit.getOperationMainSwitches());
								addRow(table80, "4",
										"Manual operation of circuit breakers and RCD’s to prove functionally",
										consumerUnit.getManualCircuitBreakers());
								addRow(table80, "5",
										"Confirmation that integral test button / switch causes RCD’s to trip when operated (functional check)",
										consumerUnit.getSwitchCausesRcd());
								addRow(table80, "6", "RCD’s provided for fault protection, where specified",
										consumerUnit.getRcdFaultProtection());
								addRow(table80, "7", "RCD’s provided for additional protection, where specified",
										consumerUnit.getRcdAdditionalProtection());
								addRow(table80, "8",
										"Confirmation of over voltage protection (SPD’s) provided where specified",
										consumerUnit.getOverVoltageProtection());
								addRow(table80, "9", "Confirmation of indication that SPD is functional",
										consumerUnit.getIndicationOfSpd());
//								addRow(table80, "21", "Single pole protective devices in line conductor only",
//										consumerUnit.getSinglePole());

								document.add(table80);
//								document.newPage();

								PdfPTable section2a3 = new PdfPTable(pointColumnWidths53);
								section2a3.setWidthPercentage(100); // Width 100%
								section2a3.setSpacingBefore(5f); // Space before table
								section2a3.setSpacingAfter(5f); // Space after table
								section2a3.getDefaultCell().setBorder(0);

								PdfPCell warningNote = new PdfPCell(new Paragraph("2.1.3: Warning notices",
										new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
								warningNote.setBackgroundColor(new GrayColor(0.82f));
								warningNote.setHorizontalAlignment(Element.ALIGN_LEFT);
								warningNote.setBorder(PdfPCell.NO_BORDER);
								section2a3.addCell(warningNote);
								document.add(section2a3);

								PdfPTable table81 = new PdfPTable(pointColumnWidths6); // 3 columns.
								table81.setWidthPercentage(100); // Width 100%
								table81.setSpacingBefore(5f); // Space before table
								table81.setSpacingAfter(5f); // Space after table
								table81.setWidthPercentage(100);

								addRow(table81, "1", "Presence of RCD quarterly test notice at or near origin",
										consumerUnit.getRcdQuarterlyTest());
								addRow(table81, "2",
										"Presence of diagrams charts or schedules at or near each distribution board where required",
										consumerUnit.getDiagramsCharts());
								addRow(table81, "3",
										"presence of nonstandard (mixed) cable colour warning notice near appropriate distribution board, as required",
										consumerUnit.getNonstandardCableColour());
								addRow(table81, "4",
										"Presence of alternative supply - warning notice at or near the origin",
										consumerUnit.getAlSupplyOfOrign());
								addRow(table81, "5",
										"Presence of alternative supply - warning notice at or near the meter position, if remote from origin",
										consumerUnit.getAlSupplyOfMeter());
								addRow(table81, "6",
										"Presence of alternative supply - warning notice at or near the distribution board to which alternative sources are connected",
										consumerUnit.getAlSupplyDistribution());
								addRow(table81, "7",
										"Presence of alternative supply - warning notice at or near all points of isolation of ALL sources of supply",
										consumerUnit.getAllPointsIsolation());
								addRow(table81, "8", "Presence of next inspection recommendation label",
										consumerUnit.getNextInspection());
								addRow(table81, "9",
										"Presence of source labelling at incoming feeders",
										consumerUnit.getWarningNoticesA());
								addRow(table81, "10",
										"Presence of destination labelling at outgoing feeders",
										consumerUnit.getWarningNoticesB());
								addRow(table81, "11",
										"Presence of mimic diagram",
										consumerUnit.getWarningNoticesC());
								addRow(table81, "12",
										"Colour of mimic diagram done as per voltage level",
										consumerUnit.getWarningNoticesD());
								addRow(table81, "13", "Presence of cable tag on both ends",
										consumerUnit.getWarningNoticesE());
//								

								document.add(table81);
							}
							 
						for (Circuit circuit : dbParentArray.getCircuit()) {


							PdfPTable section2b = new PdfPTable(pointColumnWidths53);
							section2b.setWidthPercentage(100); // Width 100%
							section2b.setSpacingBefore(10f); // Space before table
//							section2b.setSpacingAfter(3f); // Space after table
							section2b.setWidthPercentage(100);
							section2b.getDefaultCell().setBorder(0);

							PdfPCell circuit11 = new PdfPCell(new Paragraph("2.2: Circuits",
									new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
							circuit11.setHorizontalAlignment(Element.ALIGN_LEFT);
							circuit11.setBackgroundColor(new GrayColor(0.82f));
							circuit11.setBorder(PdfPCell.NO_BORDER);
							section2b.addCell(circuit11);
							document.add(section2b);

								PdfPTable section2b1 = new PdfPTable(pointColumnWidths53);
								section2b1.setWidthPercentage(100); // Width 100%
								section2b1.setSpacingBefore(5f); // Space before table
								section2b1.setSpacingAfter(5f); // Space after table
								section2b1.getDefaultCell().setBorder(0);

								PdfPCell insulation = new PdfPCell(
										new Paragraph("2.2.1: Circuit conductors and its insulation",
												new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
								insulation.setBackgroundColor(new GrayColor(0.82f));
								insulation.setHorizontalAlignment(Element.ALIGN_LEFT);
								insulation.setBorder(PdfPCell.NO_BORDER);
								section2b1.addCell(insulation);
								document.add(section2b1);

								float[] pointColumnWidths2 = { 30F, 255F, 65F };

								PdfPTable table9 = new PdfPTable(pointColumnWidths2);
								table9.setWidthPercentage(100); // Width 100%
								table9.setSpacingBefore(5f); // Space before table
//							table9.setSpacingAfter(5f); // Space after table
								table9.getDefaultCell().setBorder(0);

								addRow1(table9, "1",
										"Identification of conductors including main earthing / bonding arrangements",
										circuit.getIdentificationConductors());
								addRow1(table9, "2",
										"Examination of insulation of live parts not damaged during erection",
										circuit.getExaminationInsulation());
								addRow1(table9, "3",
										"Adequacy of conductors for current-carrying capacity with respect to type and nature of the installation",
										circuit.getCurrentCarryCapacity());
								addRow1(table9, "4",
										"Presence, adequacy, and correct termination of protective conductors",
										circuit.getPresenceProtectConductors());
								addRow1(table9, "5",
										"Provision of fire barriers, sealing arrangements so as to minimize the spread of fire",
										circuit.getProvisionFireBarriers());
								addRow1(table9, "6", "Segregation/separation of Band I (ELV) and Band II (LV) circuits",
										circuit.getSeparationBand());
								addRow1(table9, "7", "Segregation/separation of electrical and non-electrical services",
										circuit.getSeparationElectrical());
								addRow1(table9, "8", "No basic insulation of a conductor visible outside enclosure",
										circuit.getConductorVisibleOutside());
								addRow1(table9, "9", "Connections of live conductors adequately enclosed",
										circuit.getConnLiveConductors());
								addRow1(table9, "10",
										"Adequately connected at the point of entry to enclosure (glands, bushes etc.)",
										circuit.getAdequatelyConnectedEnclosure());
								addRow1(table9, "11",
										"Adequacy of connections, including protective conductors, within accessories and fixed and stationary equipment",
										circuit.getAdequacyConnections());

								document.add(table9);

								PdfPTable section2b2 = new PdfPTable(pointColumnWidths53);
								section2b2.setWidthPercentage(100); // Width 100%
								section2b2.setSpacingBefore(10f); // Space before table
								section2b2.setSpacingAfter(5f); // Space after table
								section2b2.getDefaultCell().setBorder(0);

								PdfPCell equipment = new PdfPCell(new Paragraph("2.2.2: Circuit equipements",
										new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
								equipment.setBackgroundColor(new GrayColor(0.82f));
								equipment.setHorizontalAlignment(Element.ALIGN_LEFT);
								equipment.setBorder(PdfPCell.NO_BORDER);
								section2b2.addCell(equipment);
								document.add(section2b2);

								PdfPTable table90 = new PdfPTable(pointColumnWidths2);
								table90.setWidthPercentage(100); // Width 100%
								table90.setSpacingBefore(5f); // Space before table
								table90.setSpacingAfter(5f); // Space after table
								table90.getDefaultCell().setBorder(0);

								addRow1(table90, "1",
										"Adequacy of protective devices, type and fault current rating for fault protection",
										circuit.getAdequacyProtectDevices());
								addRow1(table90, "2",
										"Co-ordination between conductors and overload protective devices",
										circuit.getCoOrdination());
								addRow1(table90, "3",
										"Additional protection by RCD’s having residual operating current (I∆n) not exceeding 30mA for circuits used to supply mobile equipment not exceeding 32A rating for use outdoors in all cases",
										circuit.getOperatingCurrentCircuits());
								addRow1(table90, "4",
										"Additional protection by RCD’s having residual operating current (I∆n) not exceeding 30mA for all socket outlets of rating 20A or less provided for use by ordinary persons unless exempt",
										circuit.getOperatingCurrentSocket());
								addRow1(table90, "5",
										"Additional protection by RCD’s having residual operating current (I∆n) not exceeding 30mA for cables concealed in walls at a depth of less than 50mm",
										circuit.getCablesConcDepth());
								addRow1(table90, "6",
										"Additional protection by RCD’s having residual operating current (I∆n) not exceeding 30mA for Cables concealed in walls / sections containing metal sections regardless of depth",
										circuit.getSectionsRegardlessDepth());
								addRow1(table90, "7", "Condition of circuit accessories",
										circuit.getConditionCircuitAccessories());
								addRow1(table90, "8", "Suitability of circuit accessories for external influences",
										circuit.getSuitabilityCircuitAccessories());
								addRow1(table90, "9",
										"Condition of accessories including socket-outlets, switches and joint boxes (Circuit accessories not damaged, securely fixed, correctly connected, suitable for external influences)",
										circuit.getConditionAccessories());
//								addRow1(table90, "21",
//										"Single-pole devices for switching or protection in line conductors only",
//										circuit.getSinglePoleDevices());
								addRow1(table90, "10",
										"Presence, operation, and correct location of appropriate devices for isolation and switching",
										circuit.getIsolationSwitching());

								document.add(table90);

								PdfPTable section2b3 = new PdfPTable(pointColumnWidths53);
								section2b3.setWidthPercentage(100); // Width 100%
								section2b3.setSpacingBefore(5f); // Space before table
//							section2b3.setSpacingAfter(5f); // Space after table
								section2b3.getDefaultCell().setBorder(0);

								PdfPCell circuitCables = new PdfPCell(new Paragraph("2.2.3: Circuit cables",
										new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
								circuitCables.setBackgroundColor(new GrayColor(0.82f));
								circuitCables.setHorizontalAlignment(Element.ALIGN_LEFT);
								circuitCables.setBorder(PdfPCell.NO_BORDER);
								section2b3.addCell(circuitCables);
								document.add(section2b3);

								PdfPTable table91 = new PdfPTable(pointColumnWidths2);
								table91.setWidthPercentage(100); // Width 100%
								table91.setSpacingBefore(10f); // Space before table
								table91.setSpacingAfter(5f); // Space after table
								table91.getDefaultCell().setBorder(0);

								addRow1(table91, "1",
										"Cable installation methods (including support) suitable for the location(s) and external influences (Cables correctly supported throughout)",
										circuit.getCableInstallation());
								addRow1(table91, "2",
										"Examination of cables for signs of mechanical damage during installation",
										circuit.getExaminationCables());
								addRow1(table91, "3",
										"Non-Sheathed cables protected by enclosure in conduit, ducting or trucking",
										circuit.getNonSheathedCables());
								addRow1(table91, "4", "Suitability of containment systems including flexible conduit",
										circuit.getContainmentSystems());
								addRow1(table91, "5", "Correct temperature rating of cable insulation",
										circuit.getTemperatureRating());
								addRow1(table91, "6", "Cables correctly terminated in enclosures",
										circuit.getCablesTerminated());
								addRow1(table91, "7",
										"Wiring systems and cable installation methods/ practices with regard to the type and nature of installation and external influences",
										circuit.getWiringSystems());
								addRow1(table91, "8",
										"Cables concealed under floors above ceilings, in wall adequately protected against damage by contact with fixings",
										circuit.getCablesConcealUnderFloors());
								addRow1(table91, "9",
										"Cables and conductors correctly terminated, enclosed and with no undue mechanical strain",
										circuit.getConductorCorrectTerminated());
								addRow1(table91, "10",
										"Unused cable entry holes or gland holes seized or sealed properly",
										circuit.getCablesA());

								document.add(table91);
								
								PdfPTable section2b4 = new PdfPTable(pointColumnWidths5);
								section2b4.setWidthPercentage(100); // Width 100%
								section2b4.setSpacingBefore(5f); // Space before table
//							    section2b4.setSpacingAfter(5f); // Space after table
								section2b4.getDefaultCell().setBorder(0);

								PdfPCell permanentlyConnect = new PdfPCell(new Paragraph("2.2.4: Permanently connected loads",
										new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
								permanentlyConnect.setBackgroundColor(new GrayColor(0.82f));
								permanentlyConnect.setHorizontalAlignment(Element.ALIGN_LEFT);
								permanentlyConnect.setBorder(PdfPCell.NO_BORDER);
								section2b4.addCell(permanentlyConnect);
								document.add(section2b4);
								
//								float[] pointColumnWidths2 = { 30F, 255F, 65F };

								PdfPTable table92 = new PdfPTable(pointColumnWidths2);
								table92.setWidthPercentage(100); // Width 100%
								table92.setSpacingBefore(10f); // Space before table
								table92.setSpacingAfter(5f); // Space after table
								table92.getDefaultCell().setBorder(0);
								
								addRow1(table92, "1",
										"Suitability of the equipment in terms of IP and fire ratings",
										circuit.getPermanentlyConnectedA());
								addRow1(table92, "2",
										"Enclosure not damaged / deteriorated during installation so as to impair safety",
										circuit.getPermanentlyConnectedB());
								addRow1(table92, "3",
										"Suitability for the environment and external influences",
										circuit.getPermanentlyConnectedC());
								addRow1(table92, "4", "Security of fixing",
										circuit.getPermanentlyConnectedD());
								addRow1(table92, "5", "Unused cable entry holes, seized or sealed so as to restrict the spread of fire",
										circuit.getPermanentlyConnectedE());
								addRow1(table92, "6", "Provision (condition) of under voltage protection, where specified",
										circuit.getPermanentlyConnectedF());
								addRow1(table92, "7",
										"Provision (condition) of overload protection, where specified",
										circuit.getPermanentlyConnectedG());
								addRow1(table92, "8",
										"No signs of overheating to surrounding building fabric (applicable for periodic inspection)",
										circuit.getPermanentlyConnectedH());
								addRow1(table92, "9",
										"No signs of overheating to conductors / terminations (applicable for periodic inspection)",
										circuit.getPermanentlyConnectedI());
								addRow1(table92, "10",
										"Presence and operation of appropriate devices near load for isolation and switching incase of emergency",
										circuit.getPermanentlyConnectedJ());
								
								document.add(table92);
								
							}
			
						   Integer DbObservationCount=1;
							for (ObsFormArrayB obsFormArrayB : dbParentArray.getObsFormArrayB()) {
								if (obsFormArrayB.getObsBFlag() != null
										&& !obsFormArrayB.getObsBFlag().equalsIgnoreCase("R")&&obsFormArrayB.getDistributionObservation()!=null&&!obsFormArrayB.getDistributionObservation().isEmpty()) {
									PdfPTable dbParentObservation = new PdfPTable(observationspointColumnWidths);
									dbParentObservation.setWidthPercentage(100); // Width 100%
									dbParentObservation.setSpacingBefore(5f); // Space before table
									dbParentObservation.setWidthPercentage(100);
									dbParentObservation.getDefaultCell().setBorder(0);

									PdfPCell cell154 = new PdfPCell(new Paragraph("Remarks/Observation -"+DbObservationCount +":"+"    "+obsFormArrayB.getDistributionObservation(),
											new Font(BaseFont.createFont(), 10, Font.NORMAL)));
									cell154.setBackgroundColor(new GrayColor(0.93f));
									cell154.setHorizontalAlignment(Element.ALIGN_LEFT);
									cell154.setBorder(PdfPCell.NO_BORDER);
									dbParentObservation.addCell(cell154);
//									PdfPCell cell155 = new PdfPCell(
//											new Paragraph(obsFormArrayB.getDistributionObservation(),
//													new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//									cell155.setHorizontalAlignment(Element.ALIGN_LEFT);
//									cell155.setBackgroundColor(new GrayColor(0.93f));
//									cell155.setBorder(PdfPCell.NO_BORDER);
//									dbParentObservation.addCell(cell155);

									document.add(dbParentObservation);
									++DbObservationCount;
								}
							}
						}
						
						}
						document.newPage();
						
                     for(SubDbParent subDbParent:ipoInspection.getSubDbParent()) {
                    	 
                 		float[] pointColumnWidths141 = { 80F, 100F };
                 		  
						PdfPTable section3 = new PdfPTable(pointColumnWidths5);
						section3.setWidthPercentage(100); // Width 100%
						section3.setSpacingBefore(5f); // Space before table
						section3.setSpacingAfter(5f); // Space after table
						section3.setWidthPercentage(100);
						section3.getDefaultCell().setBorder(0);

						PdfPCell subdistribution = new PdfPCell(new Paragraph("Section - 3: Final Distribution Boards ",
								new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
						subdistribution.setHorizontalAlignment(Element.ALIGN_LEFT);
						subdistribution.setBackgroundColor(new GrayColor(0.82f));
						subdistribution.setBorder(PdfPCell.NO_BORDER);
						section3.addCell(subdistribution);
						document.add(section3);
						
							
						
						PdfPTable table122 = new PdfPTable(pointColumnWidths141);

						table122.setWidthPercentage(100); // Width 100%
						table122.setSpacingBefore(10f); // Space before table
						table122.setSpacingAfter(5f); // Space after table
						table122.getDefaultCell().setBorder(0);
						
					
						PdfPCell cell156 = new PdfPCell(new Paragraph("Distribution board name :",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell156.setBackgroundColor(new GrayColor(0.93f));
						cell156.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell156.setBorder(PdfPCell.NO_BORDER);
						table122.addCell(cell156);
						PdfPCell cell157 = new PdfPCell(new Paragraph(subDbParent.getSubDistributionBoard(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell157.setBackgroundColor(new GrayColor(0.93f));
						cell157.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell157.setBorder(PdfPCell.NO_BORDER);
						table122.addCell(cell157);
					
						PdfPCell cell158 = new PdfPCell(new Paragraph("Location :",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//						cell144.setBackgroundColor(new GrayColor(0.93f));
						cell158.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell158.setBorder(PdfPCell.NO_BORDER);
						table122.addCell(cell158);
						PdfPCell cell159 = new PdfPCell(new Paragraph(subDbParent.getSubDistributionLocation(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//						cell145.setBackgroundColor(new GrayColor(0.93f));
						cell159.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell159.setBorder(PdfPCell.NO_BORDER);
						table122.addCell(cell159);
						
						PdfPCell cell160 = new PdfPCell(new Paragraph("Source Details :",
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell160.setBackgroundColor(new GrayColor(0.93f));
						cell160.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell160.setBorder(PdfPCell.NO_BORDER);
						table122.addCell(cell160);
						PdfPCell cell161= new PdfPCell(new Paragraph(subDbParent.getSubDistributionSource(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell161.setBackgroundColor(new GrayColor(0.93f));
						cell161.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell161.setBorder(PdfPCell.NO_BORDER);
						table122.addCell(cell161);
                        document.add(table122);
						
						for (SubDistribution subDistribution : subDbParent.getSubDistribution()) {

//							document.newPage();
                             
							PdfPTable section3a = new PdfPTable(pointColumnWidths5);
							section3a.setWidthPercentage(100); // Width 100%
							section3a.setSpacingBefore(5f); // Space before table
							section3a.setSpacingAfter(5f); // Space after table
							section3a.getDefaultCell().setBorder(0);

							PdfPCell othermethod = new PdfPCell(new Paragraph("3.1: Other Method of Protection",
									new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
							othermethod.setBackgroundColor(new GrayColor(0.82f));
							othermethod.setHorizontalAlignment(Element.ALIGN_LEFT);
							othermethod.setBorder(PdfPCell.NO_BORDER);
							section3a.addCell(othermethod);
							document.add(section3a);

							float[] pointColumnWidths2 = { 30F, 255F, 65F };

							PdfPTable table10 = new PdfPTable(pointColumnWidths2);
							table10.setWidthPercentage(100); // Width 100%
							table10.setSpacingBefore(5f); // Space before table
							table10.setSpacingAfter(5f); // Space after table
							table10.getDefaultCell().setBorder(0);

							addRow2(table10, "1", "Adequacy of access and working space for items of electrical equipment including switchgear",
									subDistribution.getSubDistributionA());
							addRow2(table10, "2", "Security of fixing: ",
									subDistribution.getSubDistributionB());
							addRow2(table10, "3",
									"Insulation of live parts not damaged during erection (conductors completely covered with durable insulating material)",
									subDistribution.getSubDistributionC());
							addRow2(table10, "4", "Adequacy / security of barriers",
									subDistribution.getSubDistributionD());
							addRow2(table10, "5", "Suitability of enclosure(s) for IP and fire ratings",
									subDistribution.getSubDistributionE());
							addRow2(table10, "6",
									"Enclosure not damaged during installation",
									subDistribution.getSubDistributionF());
							addRow2(table10, "7",
									"Presence and effectiveness of obstacles",
									subDistribution.getSubDistributionG());
							addRow2(table10, "8", "Placing out of reach",
									subDistribution.getSubDistributionH());
							addRow2(table10, "9", "Protection against mechanical damage where cables enter equipment",
									subDistribution.getSubDistributionI());
							addRow2(table10, "10",
									"Protection against electromagnetic/heating effects where cables enter ferromagnetic enclosures",
									subDistribution.getSubDistributionJ());
							addRow2(table10, "11",
									"Confirmation that all conductor connections including connections to busbars are correctly located in terminals and are tight and secure",
									subDistribution.getSubDistributionK());

							document.add(table10);

							PdfPTable section3b = new PdfPTable(pointColumnWidths5);
							section3b.setWidthPercentage(100); // Width 100%
							section3b.setSpacingBefore(5f); // Space before table
							section3b.setSpacingAfter(5f); // Space after table
							section3b.getDefaultCell().setBorder(0);

							PdfPCell distributionEquipment = new PdfPCell(new Paragraph("3.2: Distribution Equipments",
									new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
							distributionEquipment.setBackgroundColor(new GrayColor(0.82f));
							distributionEquipment.setHorizontalAlignment(Element.ALIGN_LEFT);
							distributionEquipment.setBorder(PdfPCell.NO_BORDER);
							section3b.addCell(distributionEquipment);
							document.add(section3b);

							PdfPTable table111 = new PdfPTable(pointColumnWidths2);
							table111.setWidthPercentage(100); // Width 100%
							table111.setSpacingBefore(5f); // Space before table
							table111.setSpacingAfter(5f); // Space after table
							table111.getDefaultCell().setBorder(0);

							addRow2(table111, "1",
									"Selection of protective devices and bases correct type and rating (no signs of unacceptable thermal damage, arcing or overheating)",
									subDistribution.getDistributionEquipA());
							addRow2(table111, "2", "Presence of main switches linked where required",
									subDistribution.getDistributionEquipB());
							addRow2(table111, "3", "Operation of main switches (functional checks)",
									subDistribution.getDistributionEquipC());
							addRow2(table111, "4",
									"Manual operation of circuit breakers and RCD’s to prove functionally",
									subDistribution.getDistributionEquipD());
							addRow2(table111, "5",
									"Confirmation that integral test button / switch causes RCD’s to trip when operated (functional check)",
									subDistribution.getDistributionEquipE());
							addRow2(table111, "6", "RCD’s provided for fault protection, where specified",
									subDistribution.getDistributionEquipF());
							addRow2(table111, "7", "RCD’s provided for additional protection, where specified",
									subDistribution.getDistributionEquipG());
							addRow2(table111, "8",
									"Confirmation of over voltage protection (SPD’s) provided where specified",
									subDistribution.getDistributionEquipH());
							addRow2(table111, "9", "Confirmation of indication that SPD is functional",
									subDistribution.getDistributionEquipI());
							addRow2(table111, "10", "Single pole protective devices in line conductor only",
									subDistribution.getDistributionEquipJ());
							
							document.add(table111);

							

							PdfPTable section3c = new PdfPTable(pointColumnWidths5);
							section3c.setWidthPercentage(100); // Width 100%
							section3c.setSpacingBefore(5f); // Space before table
							section3c.setSpacingAfter(5f); // Space after table
							section3c.setWidthPercentage(100);
							section3c.getDefaultCell().setBorder(0);

							PdfPCell warningNotice = new PdfPCell(
									new Paragraph("3.3: Warning Notices",
											new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
							warningNotice.setHorizontalAlignment(Element.ALIGN_LEFT);
							warningNotice.setBackgroundColor(new GrayColor(0.82f));
							warningNotice.setBorder(PdfPCell.NO_BORDER);
							section3c.addCell(warningNotice);
							document.add(section3c);

							PdfPTable table11 = new PdfPTable(pointColumnWidths2);

							table11.setWidthPercentage(100); // Width 100%
							table11.setSpacingBefore(5f); // Space before table
							table11.setSpacingAfter(5f); // Space after table
							table11.setWidthPercentage(100);
							table11.getDefaultCell().setBorder(0);

							addRow2(table11, "1", "Presence of RCD quarterly test notice at or near origin",
									subDistribution.getWarningNoticeA());
							addRow2(table11, "2",
									"Presence of diagrams charts or schedules at or near each distribution board where required",
									subDistribution.getWarningNoticeB());
							addRow2(table11, "3",
									"presence of nonstandard (mixed) cable colour warning notice near appropriate distribution board, as required",
									subDistribution.getWarningNoticeC());
							addRow2(table11, "4", "Presence of next inspection recommendation label",
									subDistribution.getWarningNoticeD());
							addRow2(table11, "5", "Presence of source labelling at incoming feeders",
									subDistribution.getWarningNoticeE());
							addRow2(table11, "6",
									"Presence of source labelling at outgoing feeders",
									subDistribution.getWarningNoticeF());
							addRow2(table11, "7",
									"Presence of mimic diagram:",
									subDistribution.getWarningNoticeG());
							addRow2(table11, "8", "Colour of mimic diagram done as per voltage level",
									subDistribution.getWarningNoticeH());
							addRow2(table11, "9", "Presence of  cable tag on both ends",
									subDistribution.getWarningNoticeI());
							document.add(table11);
					

						}
						
						for(SubDistributionOne subDistributionOne:subDbParent.getSubDistributionOne()) {
							float[] pointColumnWidths2 = { 30F, 255F, 65F };

							PdfPTable section3d = new PdfPTable(pointColumnWidths5);
							section3d.setWidthPercentage(100); // Width 100%
							section3d.setSpacingBefore(5f); // Space before table
							section3d.setSpacingAfter(5f); // Space after table
							section3d.getDefaultCell().setBorder(0);

							PdfPCell insulation = new PdfPCell(
									new Paragraph("3.4: Circuit conductors and its insulation",
											new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
							insulation.setBackgroundColor(new GrayColor(0.82f));
							insulation.setHorizontalAlignment(Element.ALIGN_LEFT);
							insulation.setBorder(PdfPCell.NO_BORDER);
							section3d.addCell(insulation);
							document.add(section3d);

							PdfPTable table9 = new PdfPTable(pointColumnWidths2);
							table9.setWidthPercentage(100); // Width 100%
							table9.setSpacingBefore(5f); // Space before table
//						table9.setSpacingAfter(5f); // Space after table
							table9.getDefaultCell().setBorder(0);

							addRow1(table9, "1",
									"Identification of conductors including main earthing / bonding arrangements",
									subDistributionOne.getCircuitConductorsA());
							addRow1(table9, "2",
									"Examination of insulation of live parts not damaged during erection",
									subDistributionOne.getCircuitConductorsB());
							addRow1(table9, "3",
									"Adequacy of conductors for current-carrying capacity with respect to type and nature of the installation",
									subDistributionOne.getCircuitConductorsC());
							addRow1(table9, "4",
									"Presence, adequacy, and correct termination of protective conductors",
									subDistributionOne.getCircuitConductorsD());
							addRow1(table9, "5",
									"Provision of fire barriers, sealing arrangements so as to minimize the spread of fire",
									subDistributionOne.getCircuitConductorsE());
							addRow1(table9, "6", "Segregation/separation of Band I (ELV) and Band II (LV) circuits",
									subDistributionOne.getCircuitConductorsF());
							addRow1(table9, "7", "Segregation/separation of electrical and non-electrical services",
									subDistributionOne.getCircuitConductorsG());
							addRow1(table9, "8", "No basic insulation of a conductor visible outside enclosure",
									subDistributionOne.getCircuitConductorsH());
							addRow1(table9, "9", "Connections of live conductors adequately enclosed",
									subDistributionOne.getCircuitConductorsI());
							addRow1(table9, "10",
									"Adequately connected at the point of entry to enclosure (glands, bushes etc.)",
									subDistributionOne.getCircuitConductorsJ());
							addRow1(table9, "11",
									"Adequacy of connections, including protective conductors, within accessories and fixed and stationary equipment",
									subDistributionOne.getCircuitConductorsK());

							document.add(table9);

							PdfPTable section3e = new PdfPTable(pointColumnWidths5);
							section3e.setWidthPercentage(100); // Width 100%
							section3e.setSpacingBefore(10f); // Space before table
							section3e.setSpacingAfter(5f); // Space after table
							section3e.getDefaultCell().setBorder(0);

							PdfPCell equipments = new PdfPCell(new Paragraph("3.5: Circuit equipements",
									new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
							equipments.setBackgroundColor(new GrayColor(0.82f));
							equipments.setHorizontalAlignment(Element.ALIGN_LEFT);
							equipments.setBorder(PdfPCell.NO_BORDER);
							section3e.addCell(equipments);
							document.add(section3e);

							PdfPTable table90 = new PdfPTable(pointColumnWidths2);
							table90.setWidthPercentage(100); // Width 100%
							table90.setSpacingBefore(5f); // Space before table
							table90.setSpacingAfter(5f); // Space after table
							table90.getDefaultCell().setBorder(0);

							addRow1(table90, "1",
									"Adequacy of protective devices, type and fault current rating for fault protection",
									subDistributionOne.getCircuitEquipmentsA());
							addRow1(table90, "2",
									"Co-ordination between conductors and overload protective devices",
									subDistributionOne.getCircuitEquipmentsB());
							addRow1(table90, "3",
									"Additional protection by RCD’s having residual operating current (I∆n) not exceeding 30mA for circuits used to supply mobile equipment not exceeding 32A rating for use outdoors in all cases",
									subDistributionOne.getCircuitEquipmentsC());
							addRow1(table90, "4",
									"Additional protection by RCD’s having residual operating current (I∆n) not exceeding 30mA for all socket outlets of rating 20A or less provided for use by ordinary persons unless exempt",
									subDistributionOne.getCircuitEquipmentsD());
							addRow1(table90, "5", "Condition of circuit accessories",
									subDistributionOne.getCircuitEquipmentsE());
							addRow1(table90, "6", "Suitability of circuit accessories for external influences",
									subDistributionOne.getCircuitEquipmentsF());
							addRow1(table90, "7",
									"Condition of accessories including socket-outlets, switches and joint boxes (Circuit accessories not damaged, securely fixed, correctly connected, suitable for external influences)",
									subDistributionOne.getCircuitEquipmentsG());
							addRow1(table90, "8",
									"Single-pole devices for switching or protection in line conductors only",
									subDistributionOne.getCircuitEquipmentsH());
							addRow1(table90, "9",
									"Presence, operation, and correct location of appropriate devices for isolation and switching",
									subDistributionOne.getCircuitEquipmentsI());

							document.add(table90);	
							
							PdfPTable section3f = new PdfPTable(pointColumnWidths5);
							section3f.setWidthPercentage(100); // Width 100%
							section3f.setSpacingBefore(5f); // Space before table
//						    Section6c.setSpacingAfter(5f); // Space after table
							section3f.getDefaultCell().setBorder(0);

							PdfPCell circuitCable = new PdfPCell(new Paragraph("3.6: Circuit cables",
									new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
							circuitCable.setBackgroundColor(new GrayColor(0.82f));
							circuitCable.setHorizontalAlignment(Element.ALIGN_LEFT);
							circuitCable.setBorder(PdfPCell.NO_BORDER);
							section3f.addCell(circuitCable);
							document.add(section3f);
							
//							float[] pointColumnWidths2 = { 30F, 255F, 65F };

							PdfPTable table91 = new PdfPTable(pointColumnWidths2);
							table91.setWidthPercentage(100); // Width 100%
							table91.setSpacingBefore(10f); // Space before table
							table91.setSpacingAfter(5f); // Space after table
							table91.getDefaultCell().setBorder(0);

							addRow1(table91, "1",
									"Cable installation methods (including support) suitable for the location(s) and external influences (Cables correctly supported throughout)",
									subDistributionOne.getSubCablesA());
							addRow1(table91, "2",
									"Examination of cables for signs of mechanical damage during installation",
									subDistributionOne.getSubCablesB());
							addRow1(table91, "3",
									"Non-Sheathed cables protected by enclosure in conduit, ducting or trucking",
									subDistributionOne.getSubCablesC());
							addRow1(table91, "4", "Suitability of containment systems including flexible conduit",
									subDistributionOne.getSubCablesD());
							addRow1(table91, "5", "Correct temperature rating of cable insulation",
									subDistributionOne.getSubCablesE());
							addRow1(table91, "6", "Cables correctly terminated in enclosures",
									subDistributionOne.getSubCablesF());
							addRow1(table91, "7",
									"Wiring systems and cable installation methods/ practices with regard to the type and nature of installation and external influences",
									subDistributionOne.getSubCablesG());
							addRow1(table91, "8",
									"Cables concealed under floors above ceilings, in wall adequately protected against damage by contact with fixings",
									subDistributionOne.getSubCablesH());
							addRow1(table91, "9",
									"Cables and conductors correctly terminated, enclosed and with no undue mechanical strain",
									subDistributionOne.getSubCablesI());
							addRow1(table91, "10",
									"Unused cable entry holes or gland holes seized or sealed properly",
									subDistributionOne.getSubCablesJ());

							document.add(table91);
							
							PdfPTable section3g = new PdfPTable(pointColumnWidths5);
							section3g.setWidthPercentage(100); // Width 100%
							section3g.setSpacingBefore(5f); // Space before table
//						    section3g.setSpacingAfter(5f); // Space after table
							section3g.getDefaultCell().setBorder(0);

							PdfPCell permanently = new PdfPCell(new Paragraph("3.7: Permanently connected loads",
									new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
							permanently.setBackgroundColor(new GrayColor(0.82f));
							permanently.setHorizontalAlignment(Element.ALIGN_LEFT);
							permanently.setBorder(PdfPCell.NO_BORDER);
							section3g.addCell(permanently);
							document.add(section3g);
							
//							float[] pointColumnWidths2 = { 30F, 255F, 65F };

							PdfPTable table92 = new PdfPTable(pointColumnWidths2);
							table92.setWidthPercentage(100); // Width 100%
							table92.setSpacingBefore(10f); // Space before table
							table92.setSpacingAfter(5f); // Space after table
							table92.getDefaultCell().setBorder(0);
							
							addRow1(table92, "1",
									"Suitability of the equipment in terms of IP and fire ratings",
									subDistributionOne.getPermanentlyConnectedA());
							addRow1(table92, "2",
									"Enclosure not damaged / deteriorated during installation so as to impair safety",
									subDistributionOne.getPermanentlyConnectedB());
							addRow1(table92, "3",
									"Suitability for the environment and external influences",
									subDistributionOne.getPermanentlyConnectedC());
							addRow1(table92, "4", "Security of fixing",
									subDistributionOne.getPermanentlyConnectedD());
							addRow1(table92, "5", "Unused cable entry holes, seized or sealed so as to restrict the spread of fire",
									subDistributionOne.getPermanentlyConnectedE());
							addRow1(table92, "6", "Provision (condition) of under voltage protection, where specified",
									subDistributionOne.getPermanentlyConnectedF());
							addRow1(table92, "7",
									"Provision (condition) of overload protection, where specified",
									subDistributionOne.getPermanentlyConnectedG());
							addRow1(table92, "8",
									"No signs of overheating to surrounding building fabric (applicable for periodic inspection)",
									subDistributionOne.getPermanentlyConnectedH());
							addRow1(table92, "9",
									"No signs of overheating to conductors / terminations (applicable for periodic inspection)",
									subDistributionOne.getPermanentlyConnectedI());
							addRow1(table92, "10",
									"Presence and operation of appropriate devices near load for isolation and switching incase of emergency",
									subDistributionOne.getPermanentlyConnectedJ());
							addRow1(table92, "11",
									"Recessed luminaires (downlighters) - Correct type of lamps fitted",
									subDistributionOne.getPermanentlyConnectedK());
							addRow1(table92, "12",
									"Recessed luminaires (downlighters) - Installed to minimise build-up of heat by use of “fire rated” fittings, insulation displacement box or similar",
									subDistributionOne.getPermanentlyConnectedL());
							addRow1(table92, "13",
									"Load current distributed evenly",
									subDistributionOne.getPermanentlyConnectedM());
							addRow1(table92, "14",
									"Cable entry holes in ceilings above luminaries, seized or sealed so as to restrict the spread of fire",
									subDistributionOne.getPermanentlyConnectedN());
							
							document.add(table92);
							

						}
						Integer subDbObservationCount=1;
						for (ObsFormArrayC obsFormArrayC : subDbParent.getObsFormArrayC()) {
							if (obsFormArrayC.getObsCFlag() != null
									&& !obsFormArrayC.getObsCFlag().equalsIgnoreCase("R")&&obsFormArrayC.getSubDistributionObs()!=null&&!obsFormArrayC.getSubDistributionObs().isEmpty()) {
								PdfPTable subdbObservation = new PdfPTable(observationspointColumnWidths);
								subdbObservation.setWidthPercentage(100); // Width 100%
								subdbObservation.setSpacingBefore(5f); // Space before table
								subdbObservation.setWidthPercentage(100);
								subdbObservation.getDefaultCell().setBorder(0);

								PdfPCell cell154 = new PdfPCell(new Paragraph("Remarks/Observation -"+subDbObservationCount +":"+"    "+obsFormArrayC.getSubDistributionObs(),
										new Font(BaseFont.createFont(), 10, Font.NORMAL)));
								cell154.setBackgroundColor(new GrayColor(0.93f));
								cell154.setHorizontalAlignment(Element.ALIGN_LEFT);
								cell154.setBorder(PdfPCell.NO_BORDER);
								subdbObservation.addCell(cell154);
//								PdfPCell cell155 = new PdfPCell(new Paragraph(obsFormArrayC.getSubDistributionObs(),
//										new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//								cell155.setHorizontalAlignment(Element.ALIGN_LEFT);
//								cell155.setBackgroundColor(new GrayColor(0.93f));
//								cell155.setBorder(PdfPCell.NO_BORDER);
//								subdbObservation.addCell(cell155);

								document.add(subdbObservation);
								++subDbObservationCount;
							}
						}
                     }
						
					}

					if (comments.getViewerUserName() != null && comments.getInspectorUserName() != null) {

						document.newPage();

						PdfPTable table199 = new PdfPTable(1);
						table199.setWidthPercentage(100); // Width 100%
						table199.setSpacingBefore(10f); // Space before table
						table199.setWidthPercentage(100);
						table199.getDefaultCell().setBorder(0);
						Font font = new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD);
						PdfPCell cell65 = new PdfPCell(
								new Paragraph(15, "Section - 9: Viewer And Inspector Comment:", font));
						cell65.setBorder(PdfPCell.NO_BORDER);
						cell65.setBackgroundColor(BaseColor.LIGHT_GRAY);
						table199.addCell(cell65);
						document.add(table199);
						Font font91 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
						float[] pointColumnWidths4 = { 90F, 90F, 90F, 90F };

						PdfPTable table44 = new PdfPTable(pointColumnWidths4);
						table44.setWidthPercentage(100); // Width 100%
						table44.setSpacingBefore(10f); // Space before table
						table44.setWidthPercentage(100);

						PdfPCell cell55 = new PdfPCell(new Paragraph(comments.getViewerUserName(), font91));
						cell55.setHorizontalAlignment(Element.ALIGN_CENTER);
						PdfPCell cell371 = new PdfPCell(new Paragraph("ViewerUserName:", font91));
						cell371.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell371.setFixedHeight(25f);
						cell371.setGrayFill(0.92f);
						table44.addCell(cell371);
						table44.addCell(cell55);
						PdfPCell cell381 = new PdfPCell(new Paragraph(comments.getInspectorUserName(), font91));
						cell381.setHorizontalAlignment(Element.ALIGN_CENTER);
						PdfPCell cell3711 = new PdfPCell(new Paragraph("InspectorUserName:", font91));
						cell3711.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell3711.setFixedHeight(25f);
						cell3711.setGrayFill(0.92f);
						table44.addCell(cell3711);
						table44.addCell(cell381);

						PdfPCell cell561 = new PdfPCell(new Paragraph("ViewerComment Date:", font91));
						cell561.setGrayFill(0.92f);
						cell561.setHorizontalAlignment(Element.ALIGN_CENTER);

						PdfPCell cell5611 = new PdfPCell(new Paragraph("ViewerComment:", font91));
						cell5611.setGrayFill(0.92f);
						cell5611.setHorizontalAlignment(Element.ALIGN_CENTER);
						table44.addCell(cell5611);
						table44.addCell(cell561);

						PdfPCell cell401 = new PdfPCell(new Paragraph("InspectorComment Date:", font91));
						cell401.setGrayFill(0.92f);
						cell401.setHorizontalAlignment(Element.ALIGN_CENTER);
						PdfPCell cell391 = new PdfPCell(new Paragraph("InspectorComment:", font91));
						cell391.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell391.setGrayFill(0.92f);
						table44.addCell(cell391);
						table44.addCell(cell401);

						tableData(table44, reportComments);

						document.add(table44);
					}

				}
				document.close();
				writer.close();

			} catch (Exception e) {
				logger.debug("Inspection PDF creation Failed for SiteId : {}", siteId);
				throw new PdfException("Inspection PDF creation Failed"); 
			}

		} else {
			throw new InspectionException("Invalid Inputs");
		}
		return null;
	}

	private List<InspectionInnerObservations> getObservationData(IpaoInspection ipoInspection) {
		List<InspectionInnerObservations> inspectionInnerObservation = new ArrayList<InspectionInnerObservations>();
		List<InspectionInnerObservations> inspectionInnerObservations = ipoInspection.getInspectionOuterObervation().get(0).getInspectionInnerObservations();
			 for(InspectionInnerObservations inspectionObservation : inspectionInnerObservations) {
				 inspectionInnerObservation.add(inspectionObservation);
			 }

		return inspectionInnerObservation;

	}

	private void tableData(PdfPTable table44, List<PeriodicInspectionComment> reportComments)
			throws DocumentException, IOException {

		Collections.sort(reportComments, new Comparator<PeriodicInspectionComment>() {
			public int compare(PeriodicInspectionComment periodic1, PeriodicInspectionComment periodic2) {

				if (periodic1.getViewerDate() != null && periodic2.getViewerDate() != null) {

					return periodic1.getViewerDate().compareTo(periodic2.getViewerDate());

				} else {
					return 0;
				}
			}
		});

		Collections.sort(reportComments, new Comparator<PeriodicInspectionComment>() {
			public int compare(PeriodicInspectionComment periodic1, PeriodicInspectionComment periodic2) {

				if (periodic1.getInspectorDate() != null && periodic2.getInspectorDate() != null) {

					return periodic1.getInspectorDate().compareTo(periodic2.getInspectorDate());

				} else {
					return 0;
				}
			}
		});

		for (PeriodicInspectionComment arr : reportComments) {
			Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
			PdfPCell cell = new PdfPCell();

			if (arr.getViewerComment() != null) {
				cell.setPhrase(new Phrase(arr.getViewerComment(), font));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table44.addCell(cell);
			} else {
				cell.setPhrase(new Phrase("No viewer comment available", font));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table44.addCell(cell);
			}

			if (arr.getViewerDate() != null) {
				cell.setPhrase(new Phrase(arr.getViewerDate().toString(), font));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table44.addCell(cell);
			} else {
				cell.setPhrase(new Phrase("No viewer date available", font));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table44.addCell(cell);
			}

			if (arr.getInspectorComment() != null) {
				cell.setPhrase(new Phrase(arr.getInspectorComment(), font));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table44.addCell(cell);
			} else {
				cell.setPhrase(new Phrase("No viewer inspector available", font));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table44.addCell(cell);
			}

			if (arr.getInspectorDate() != null) {
				cell.setPhrase(new Phrase(arr.getInspectorDate().toString(), font));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table44.addCell(cell);
			} else {
				cell.setPhrase(new Phrase("No inspector date available", font));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table44.addCell(cell);
			}
		}
	}

	private void addRow(PdfPTable table8, String string, String string2, String string3)
			throws DocumentException, IOException {
		PdfPCell nameCell = new PdfPCell(new Paragraph(string, new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		PdfPCell valueCell1 = new PdfPCell(new Paragraph(string2, new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		PdfPCell valueCell2 = new PdfPCell(new Paragraph(string3, new Font(BaseFont.createFont(), 10, Font.NORMAL)));

		nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		valueCell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);

		table8.addCell(nameCell);
		table8.addCell(valueCell1);
		table8.addCell(valueCell2);
	}

	private void addRow1(PdfPTable table9, String string, String string2, String string3)
			throws DocumentException, IOException {
		PdfPCell nameCell1 = new PdfPCell(new Paragraph(string, new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		PdfPCell valueCell3 = new PdfPCell(new Paragraph(string2, new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		PdfPCell valueCell4 = new PdfPCell(new Paragraph(string3, new Font(BaseFont.createFont(), 10, Font.NORMAL)));

		nameCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell3.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		valueCell4.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);

		table9.addCell(nameCell1);
		table9.addCell(valueCell3);
		table9.addCell(valueCell4);
	}

	private void addRow2(PdfPTable table10, String string, String string2, String string3)
			throws DocumentException, IOException {
		PdfPCell nameCell2 = new PdfPCell(new Paragraph(string, new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		PdfPCell valueCell5 = new PdfPCell(new Paragraph(string2, new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		PdfPCell valueCell6 = new PdfPCell(new Paragraph(string3, new Font(BaseFont.createFont(), 10, Font.NORMAL)));

		nameCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell5.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		valueCell6.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);

		table10.addCell(nameCell2);
		table10.addCell(valueCell5);
		table10.addCell(valueCell6);
	}

	private void addRow3(PdfPTable table11, String string, String string2, String string3)
			throws DocumentException, IOException {
		PdfPCell nameCell3 = new PdfPCell(new Paragraph(string, new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		PdfPCell valueCell7 = new PdfPCell(new Paragraph(string2, new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		PdfPCell valueCell8 = new PdfPCell(new Paragraph(string3, new Font(BaseFont.createFont(), 10, Font.NORMAL)));

		nameCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell7.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		valueCell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);

		table11.addCell(nameCell3);
		table11.addCell(valueCell7);
		table11.addCell(valueCell8);
	}

}