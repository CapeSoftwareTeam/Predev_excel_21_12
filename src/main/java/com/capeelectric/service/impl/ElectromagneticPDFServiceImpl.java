package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.ElectromagneticCompatabilityException;
import com.capeelectric.model.ElectromagneticCompatability;
import com.capeelectric.model.ExternalCompatibility;
import com.capeelectric.repository.ElectromagneticCompatabilityRepository;
import com.capeelectric.service.ElectromagneticPDFService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class ElectromagneticPDFServiceImpl implements ElectromagneticPDFService {

	@Autowired
	private ElectromagneticCompatabilityRepository electromagneticCompatabilityRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(ElectromagneticPDFServiceImpl.class);

	@Override
	public void printElectromagneticData(String userName, Integer emcId) throws ElectromagneticCompatabilityException {
		if (userName != null && !userName.isEmpty() && emcId != null && emcId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);

			try {

				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("ElectromagneticData.pdf"));
				document.open();
				List<ElectromagneticCompatability> electromageticC1 = electromagneticCompatabilityRepository
						.findByUserNameAndEmcId(userName, emcId);
				ElectromagneticCompatability electromageticC = electromageticC1.get(0);

				List<ExternalCompatibility> externalC = electromageticC.getExternalCompatibility();
				ExternalCompatibility externalCo = externalC.get(0);

				Font font9 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
				
				Font font12B = new Font(BaseFont.createFont(), 12, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font10B = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				
                float[] pointColumnHeadLabel = { 100F };
				
				PdfPTable ElectroMagneticDataTable = new PdfPTable(pointColumnHeadLabel);
				ElectroMagneticDataTable.setWidthPercentage(100); // Width 100%
				ElectroMagneticDataTable.setSpacingBefore(5f); // Space before table
				ElectroMagneticDataTable.setSpacingAfter(5f); // Space after table

				PdfPCell ElectroMagneticDataCell = new PdfPCell(new Paragraph("Electro Magnetic Compatibility (EMC) Data", font12B));
				ElectroMagneticDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				ElectroMagneticDataCell.setBackgroundColor(new GrayColor(0.93f));
				ElectroMagneticDataCell.setFixedHeight(20f);
				ElectroMagneticDataTable.addCell(ElectroMagneticDataCell);
				document.add(ElectroMagneticDataTable); 
				
//				PdfPTable table10 = new PdfPTable(1);
//				table10.setWidthPercentage(100); // Width 100%
//				table10.setSpacingBefore(10f); // Space before table
//				table10.getDefaultCell().setBorder(0); 
//				
//				PdfPCell cellSy = new PdfPCell(new Paragraph(30, "System Earth Reference Summary", font9));
//				cellSy.setBorder(PdfPCell.NO_BORDER);
//				cellSy.setBackgroundColor(BaseColor.LIGHT_GRAY);
//				table10.addCell(cellSy);
//				document.add(table10);
				
				PdfPTable SystemEarthRefTable = new PdfPTable(pointColumnHeadLabel);
				SystemEarthRefTable.setWidthPercentage(100); // Width 100%
				SystemEarthRefTable.setSpacingBefore(5f); // Space before table
				SystemEarthRefTable.setSpacingAfter(5f); // Space after table

				PdfPCell SystemEarthRefCell = new PdfPCell(new Paragraph("System Earth Reference Summary", font10B));
				SystemEarthRefCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				SystemEarthRefCell.setBackgroundColor(new GrayColor(0.93f));
				SystemEarthRefCell.setFixedHeight(17f);
				SystemEarthRefTable.addCell(SystemEarthRefCell);
				document.add(SystemEarthRefTable);

				float[] pointColumnWidths1 = { 90F, 90F };

				PdfPTable table = new PdfPTable(pointColumnWidths1); // 3 columns.
				table.setWidthPercentage(100); // Width 100%
				table.setSpacingBefore(5f); // Space before table
//				table7.setSpacingAfter(10f); // Space after table
				table.getDefaultCell().setBorder(0);

				PdfPCell cell = new PdfPCell(new Paragraph("System earth referance a single point referance:", font9));
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setGrayFill(0.92f);
				table.addCell(cell);
				PdfPCell cell1 = new PdfPCell(new Paragraph(electromageticC.getSeSinglePoint(), font9));
				cell1.setGrayFill(0.92f);
				cell1.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell1);

				PdfPCell cell2 = new PdfPCell(new Paragraph("System earth referance a meshed arrangement:", font9));
				cell2.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell2);
				PdfPCell cell3 = new PdfPCell(new Paragraph(electromageticC.getSeMeshedArrangment(), font9));
				cell3.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell3);

				PdfPCell cell126 = new PdfPCell(new Paragraph("Descriptions:", font9));
				cell126.setBorder(PdfPCell.NO_BORDER);
				cell126.setGrayFill(0.92f);
				table.addCell(cell126);
				PdfPCell cell127 = new PdfPCell(new Paragraph(electromageticC.getSeDescription(), font9));
				cell127.setGrayFill(0.92f);
				cell127.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell127);

				PdfPCell cell120 = new PdfPCell(new Paragraph("Provide A Schematic Of Equipotential Bonding:", font9));
				cell120.setBorder(PdfPCell.NO_BORDER);
				// cell120.setGrayFill(0.92f);
				table.addCell(cell120);
				PdfPCell cell121 = new PdfPCell(new Paragraph(electromageticC.getEquiptentialBonding(), font9));
				// cell121.setGrayFill(0.92f);
				cell121.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell121);
				document.add(table);

//				PdfPTable table19 = new PdfPTable(1);
//				table19.setWidthPercentage(100); // Width 100%
//				table19.setSpacingBefore(10f); // Space before table
//				table19.getDefaultCell().setBorder(0);
//				
//				PdfPCell cellSr = new PdfPCell(new Paragraph(30, "System / Room Shielding", font9));
//				cellSr.setBorder(PdfPCell.NO_BORDER);
//				cellSr.setBackgroundColor(BaseColor.LIGHT_GRAY);
//				table19.addCell(cellSr);
//				document.add(table19);
				
				PdfPTable SystemRoomShieldTable = new PdfPTable(pointColumnHeadLabel);
				SystemRoomShieldTable.setWidthPercentage(100); // Width 100%
				SystemRoomShieldTable.setSpacingBefore(5f); // Space before table
//				SystemRoomShieldTable.setSpacingAfter(5f); // Space after table

				PdfPCell SystemRoomShieldCell = new PdfPCell(new Paragraph("System / Room Shielding", font10B));
				SystemRoomShieldCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				SystemRoomShieldCell.setBackgroundColor(new GrayColor(0.93f));
				SystemRoomShieldCell.setFixedHeight(17f);
				SystemRoomShieldTable.addCell(SystemRoomShieldCell);
				document.add(SystemRoomShieldTable);

				PdfPTable table20 = new PdfPTable(pointColumnWidths1);
				table20.setWidthPercentage(100); // Width 100%
				table20.setSpacingBefore(10f); // Space before table
				table20.getDefaultCell().setBorder(0);
				document.add(table20);

				PdfPCell cell112 = new PdfPCell(new Paragraph("RFI - resistant cabinets used:", font9));
				cell112.setBorder(PdfPCell.NO_BORDER);
				cell112.setGrayFill(0.92f);
				table20.addCell(cell112);
				PdfPCell cell113 = new PdfPCell(new Paragraph(electromageticC.getResistanceCabinet(), font9));
				cell113.setGrayFill(0.92f);
				cell113.setBorder(PdfPCell.NO_BORDER);
				table20.addCell(cell113);

				PdfPCell cell114 = new PdfPCell(new Paragraph("Descriptions:", font9));
				cell114.setBorder(PdfPCell.NO_BORDER);
				// cell114.setGrayFill(0.92f);
				table20.addCell(cell114);
				PdfPCell cell115 = new PdfPCell(new Paragraph(electromageticC.getResistanceCabinetDesc(), font9));
				// cell115.setGrayFill(0.92f);
				cell115.setBorder(PdfPCell.NO_BORDER);
				table20.addCell(cell115);

				PdfPCell cell110 = new PdfPCell(new Paragraph("Computer room shield:", font9));
				cell110.setBorder(PdfPCell.NO_BORDER);
				cell110.setGrayFill(0.92f);
				table20.addCell(cell110);
				PdfPCell cell111 = new PdfPCell(new Paragraph(electromageticC.getRoomShield(), font9));
				cell111.setGrayFill(0.92f);
				cell111.setBorder(PdfPCell.NO_BORDER);
				table20.addCell(cell111);

				PdfPCell cell116 = new PdfPCell(new Paragraph("Descriptions:", font9));
				cell116.setBorder(PdfPCell.NO_BORDER);
				// cell116.setGrayFill(0.92f);
				table20.addCell(cell116);
				PdfPCell cell117 = new PdfPCell(new Paragraph(electromageticC.getRoomShieldDesc(), font9));
				// cell117.setGrayFill(0.92f);
				cell117.setBorder(PdfPCell.NO_BORDER);
				table20.addCell(cell117);

				PdfPCell cell118 = new PdfPCell(new Paragraph("Describe other shielding provisions:", font9));
				cell118.setBorder(PdfPCell.NO_BORDER);
				cell118.setGrayFill(0.92f);
				table20.addCell(cell118);
				PdfPCell cell119 = new PdfPCell(new Paragraph(electromageticC.getShieldingOtherDesc(), font9));
				cell119.setGrayFill(0.92f);
				cell119.setBorder(PdfPCell.NO_BORDER);
				table20.addCell(cell119);
				document.add(table20);

//				PdfPTable table6 = new PdfPTable(1);
//				table6.setWidthPercentage(100); // Width 100%
//				table6.setSpacingBefore(10f); // Space before table
//				table6.getDefaultCell().setBorder(0);
//				
//				PdfPCell cellSi = new PdfPCell(new Paragraph(30, "Site RF Source", font9));
//				cellSi.setBorder(PdfPCell.NO_BORDER);
//				cellSi.setBackgroundColor(BaseColor.LIGHT_GRAY);
//				table6.addCell(cellSi);
//				document.add(table6);
				
				PdfPTable SiteRFSourceTable = new PdfPTable(pointColumnHeadLabel);
				SiteRFSourceTable.setWidthPercentage(100); // Width 100%
				SiteRFSourceTable.setSpacingBefore(5f); // Space before table
//				SiteRFSourceTable.setSpacingAfter(5f); // Space after table

				PdfPCell SiteRFSourceCell = new PdfPCell(new Paragraph("Site RF Source", font10B));
				SiteRFSourceCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				SiteRFSourceCell.setBackgroundColor(new GrayColor(0.93f));
				SiteRFSourceCell.setFixedHeight(17f);
				SiteRFSourceTable.addCell(SiteRFSourceCell);
				document.add(SiteRFSourceTable);


				PdfPTable table7 = new PdfPTable(pointColumnWidths1); // 3 columns.
				table7.setWidthPercentage(100); // Width 100%
				table7.setSpacingBefore(10f); // Space before table
//				table7.setSpacingAfter(10f); // Space after table
				table7.getDefaultCell().setBorder(0);

				PdfPCell cell24 = new PdfPCell(new Paragraph(
						"Any equipment in the building or near computer room using high-frequency energy (e.g., welding machine, ultrasonic cleaners, high-energy medical equipment, relay banks, microwave equipment, sodium or mercury arc lamps, equipment engines using spark plugs, other)?:",
						font9));
				cell24.setBorder(PdfPCell.NO_BORDER);
				cell24.setGrayFill(0.92f);
				table7.addCell(cell24);
				PdfPCell cell25 = new PdfPCell(new Paragraph(electromageticC.getEquipmentHighFrequency(), font9));
				cell25.setGrayFill(0.92f);
				cell25.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell25);
				
				PdfPCell cellDesc = new PdfPCell(
						new Paragraph("Describe: ", font9));
				cellDesc.setBorder(PdfPCell.NO_BORDER);
				// cell132.setGrayFill(0.92f);
				table7.addCell(cellDesc);
				PdfPCell cellDescValue = new PdfPCell(new Paragraph(electromageticC.getEquipmentHighFrequencyDesc(), font9));
				// cell133.setGrayFill(0.92f);
				cellDescValue.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cellDescValue);

				PdfPCell cell132 = new PdfPCell(
						new Paragraph("Approximate distance from electronic system in meters:", font9));
				cell132.setBorder(PdfPCell.NO_BORDER);
				// cell132.setGrayFill(0.92f);
				table7.addCell(cell132);
				PdfPCell cell133 = new PdfPCell(new Paragraph(electromageticC.getApproximateDistance(), font9));
				// cell133.setGrayFill(0.92f);
				cell133.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell133);

				PdfPCell cell134 = new PdfPCell(new Paragraph(
						"Equipment maintained and operated properly (access doors/panels closed?):", font9));
				cell134.setBorder(PdfPCell.NO_BORDER);
				cell134.setGrayFill(0.92f);
				table7.addCell(cell134);
				PdfPCell cell135 = new PdfPCell(new Paragraph(electromageticC.getEquipmentMaintence(), font9));
				cell135.setGrayFill(0.92f);
				cell135.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell135);

				PdfPCell cell136 = new PdfPCell(new Paragraph("Describe:", font9));
				cell136.setBorder(PdfPCell.NO_BORDER);
				// cell136.setGrayFill(0.92f);
				table7.addCell(cell136);
				PdfPCell cell137 = new PdfPCell(new Paragraph(electromageticC.getEquipmentMaintenceDesc(), font9));
				// cell137.setGrayFill(0.92f);
				cell137.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell137);

				PdfPCell cell1361 = new PdfPCell(new Paragraph("Operational frequency:", font9));
				cell1361.setBorder(PdfPCell.NO_BORDER);
				cell1361.setGrayFill(0.92f);
				table7.addCell(cell1361);
				PdfPCell cell1371 = new PdfPCell(new Paragraph(electromageticC.getOperationFrequency(), font9));
				cell1371.setGrayFill(0.92f);
				cell1371.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell1371);

				PdfPCell cell1362 = new PdfPCell(
						new Paragraph("Radiated power or power consumption in RF section:", font9));
				cell1362.setBorder(PdfPCell.NO_BORDER);
				// cell1362.setGrayFill(0.92f);
				table7.addCell(cell1362);
				PdfPCell cell1373 = new PdfPCell(new Paragraph(electromageticC.getRadiatedPower(), font9));
				// cell1373.setGrayFill(0.92f);
				cell1373.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell1373);
				document.add(table7);

//				PdfPTable table13 = new PdfPTable(1);
//				table13.setWidthPercentage(100); // Width 100%
//				table13.setSpacingBefore(10f); // Space before table
//				table13.getDefaultCell().setBorder(0);
//				
//				PdfPCell cellEx = new PdfPCell(new Paragraph(30, "Extrenal RFI Sources", font9));
//				cellEx.setBorder(PdfPCell.NO_BORDER);
//				cellEx.setBackgroundColor(BaseColor.LIGHT_GRAY);
//				table13.addCell(cellEx);
//				document.add(table13);
				
				PdfPTable ExtrenaRFISourcesTable = new PdfPTable(pointColumnHeadLabel);
				ExtrenaRFISourcesTable.setWidthPercentage(100); // Width 100%
				ExtrenaRFISourcesTable.setSpacingBefore(5f); // Space before table
//				ExtrenaRFISourcesTable.setSpacingAfter(5f); // Space after table

				PdfPCell ExtrenaRFISourcesCell = new PdfPCell(new Paragraph("External RFI Sources", font10B));
				ExtrenaRFISourcesCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				ExtrenaRFISourcesCell.setBackgroundColor(new GrayColor(0.93f));
				ExtrenaRFISourcesCell.setFixedHeight(17f);
				ExtrenaRFISourcesTable.addCell(ExtrenaRFISourcesCell);
				document.add(ExtrenaRFISourcesTable);


				PdfPTable table14 = new PdfPTable(pointColumnWidths1); // 3 columns.
				table14.setWidthPercentage(100); // Width 100%
				table14.setSpacingBefore(10f); // Space before table
//				table14.setSpacingAfter(10f); // Space after table
				table14.getDefaultCell().setBorder(0);

				PdfPCell cell70 = new PdfPCell(new Paragraph("Communications / navigation:", font9));
				cell70.setBorder(PdfPCell.NO_BORDER);
				cell70.setGrayFill(0.92f);
				table14.addCell(cell70);
				PdfPCell cell71 = new PdfPCell(new Paragraph(externalCo.getCommunication(), font9));
				cell71.setGrayFill(0.92f);
				cell71.setBorder(PdfPCell.NO_BORDER);
				table14.addCell(cell71);

				PdfPCell cell28 = new PdfPCell(new Paragraph(
						"Any broadcast or communication antennas visible on or near the customers site?:", font9));
				cell28.setBorder(PdfPCell.NO_BORDER);
				// cell28.setGrayFill(0.92f);
				table14.addCell(cell28);
				PdfPCell cell29 = new PdfPCell(new Paragraph(externalCo.getVisibilityOfAntennas(), font9));
				// cell29.setGrayFill(0.92f);
				cell29.setBorder(PdfPCell.NO_BORDER);
				table14.addCell(cell29);

				PdfPCell cell30 = new PdfPCell(new Paragraph(
						"Type (s) of transmission (radar, radio/tv broadcse, amateur, microwave, other, if known):",
						font9));
				cell30.setBorder(PdfPCell.NO_BORDER);
				cell30.setGrayFill(0.92f);
				table14.addCell(cell30);
				PdfPCell cell31 = new PdfPCell(new Paragraph(externalCo.getTypeOfTransmission(), font9));
				cell31.setGrayFill(0.92f);
				cell31.setBorder(PdfPCell.NO_BORDER);
				table14.addCell(cell31);

				PdfPCell cell32 = new PdfPCell(new Paragraph("Describe:", font9));
				cell32.setBorder(PdfPCell.NO_BORDER);
				// cell32.setGrayFill(0.92f);
				table14.addCell(cell32);
				PdfPCell cell33 = new PdfPCell(new Paragraph(externalCo.getTransmissionDesc(), font9));
				// cell33.setGrayFill(0.92f);
				cell33.setBorder(PdfPCell.NO_BORDER);
				table14.addCell(cell33);

				PdfPCell cell34 = new PdfPCell(
						new Paragraph("Distance from antenna to electronic system in meters:", font9));
				cell34.setBorder(PdfPCell.NO_BORDER);
				cell34.setGrayFill(0.92f);
				table14.addCell(cell34);
				PdfPCell cell35 = new PdfPCell(new Paragraph(externalCo.getAntennaDistance(), font9));
				cell35.setGrayFill(0.92f);
				cell35.setBorder(PdfPCell.NO_BORDER);
				table14.addCell(cell35);

				PdfPCell cell36 = new PdfPCell(new Paragraph(
						"Number of walls in building between electronic system and antenna (theoretical line of sight):",
						font9));
				cell36.setBorder(PdfPCell.NO_BORDER);
				// cell36.setGrayFill(0.92f);
				table14.addCell(cell36);
				PdfPCell cell37 = new PdfPCell(new Paragraph(externalCo.getNoOfWalls(), font9));
				// cell37.setGrayFill(0.92f);
				cell37.setBorder(PdfPCell.NO_BORDER);
				table14.addCell(cell37);

				PdfPCell cell38 = new PdfPCell(new Paragraph("Describe:", font9));
				cell38.setBorder(PdfPCell.NO_BORDER);
				cell38.setGrayFill(0.92f);
				table14.addCell(cell38);
				PdfPCell cell39 = new PdfPCell(new Paragraph(externalCo.getLosDesc(), font9));
				cell39.setGrayFill(0.92f);
				cell39.setBorder(PdfPCell.NO_BORDER);
				table14.addCell(cell39);

				PdfPCell cell40 = new PdfPCell(new Paragraph(
						"Distance from electronic system to outside wall near to source in meters :", font9));
				cell40.setBorder(PdfPCell.NO_BORDER);
				// cell40.setGrayFill(0.92f);
				table14.addCell(cell40);
				PdfPCell cell41 = new PdfPCell(new Paragraph(externalCo.getElectronicSystemDistance(), font9));
				// cell41.setGrayFill(0.92f);
				cell41.setBorder(PdfPCell.NO_BORDER);
				table14.addCell(cell41);

				PdfPCell cell42 = new PdfPCell(new Paragraph("Transmitter power (if known) in watts (ERP):", font9));
				cell42.setBorder(PdfPCell.NO_BORDER);
				cell42.setGrayFill(0.92f);
				table14.addCell(cell42);
				PdfPCell cell43 = new PdfPCell(new Paragraph(externalCo.getTransmitterPower(), font9));
				cell43.setGrayFill(0.92f);
				cell43.setBorder(PdfPCell.NO_BORDER);
				table14.addCell(cell43);

				PdfPCell cell44 = new PdfPCell(new Paragraph("Frequency (if known):", font9));
				cell44.setBorder(PdfPCell.NO_BORDER);
				// cell44.setGrayFill(0.92f);
				table14.addCell(cell44);
				PdfPCell cell45 = new PdfPCell(new Paragraph(externalCo.getFrequency(), font9));
				// cell45.setGrayFill(0.92f);
				cell45.setBorder(PdfPCell.NO_BORDER);
				table14.addCell(cell45);

				PdfPCell cell46 = new PdfPCell(new Paragraph("Orientation of electronic system to antenna:", font9));
				cell46.setBorder(PdfPCell.NO_BORDER);
				cell46.setGrayFill(0.92f);
				table14.addCell(cell46);
				PdfPCell cell47 = new PdfPCell(new Paragraph(externalCo.getOrientationAntinna(), font9));
				cell47.setGrayFill(0.92f);
				cell47.setBorder(PdfPCell.NO_BORDER);
				table14.addCell(cell47);
				document.add(table14);

//				PdfPCell cellIe = new PdfPCell(new Paragraph(30, "Incidental EMI Source", font9));
//				cellIe.setBorder(PdfPCell.NO_BORDER);
//				cellIe.setBackgroundColor(BaseColor.LIGHT_GRAY);
//
//				PdfPTable table15 = new PdfPTable(1);
//				table15.setWidthPercentage(100); // Width 100%
//				table15.setSpacingBefore(10f); // Space before table
//				table15.getDefaultCell().setBorder(0);
//				table15.addCell(cellIe);
//				document.add(table15);
				
				PdfPTable IncidentalEMISourceTable = new PdfPTable(pointColumnHeadLabel);
				IncidentalEMISourceTable.setWidthPercentage(100); // Width 100%
				IncidentalEMISourceTable.setSpacingBefore(5f); // Space before table
//				IncidentalEMISourceTable.setSpacingAfter(5f); // Space after table

				PdfPCell IncidentalEMISourceCell = new PdfPCell(new Paragraph("Incidental EMI Source", font10B));
				IncidentalEMISourceCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				IncidentalEMISourceCell.setBackgroundColor(new GrayColor(0.93f));
				IncidentalEMISourceCell.setFixedHeight(17f);
				IncidentalEMISourceTable.addCell(IncidentalEMISourceCell);
				document.add(IncidentalEMISourceTable);


				PdfPTable table18 = new PdfPTable(pointColumnWidths1); // 3 columns.
				table18.setWidthPercentage(100); // Width 100%
				table18.setSpacingBefore(10f); // Space before table
//				table14.setSpacingAfter(10f); // Space after table
				table18.getDefaultCell().setBorder(0);

				PdfPCell cell701 = new PdfPCell(new Paragraph(
						"CB, walkie talkies, wireless intercom, or clock synchronisation syste used on site:", font9));
				cell701.setBorder(PdfPCell.NO_BORDER);
				// cell701.setGrayFill(0.92f);
				table18.addCell(cell701);
				PdfPCell cell712 = new PdfPCell(new Paragraph(externalCo.getSystemSite(), font9));
				// cell712.setGrayFill(0.92f);
				cell712.setBorder(PdfPCell.NO_BORDER);
				table18.addCell(cell712);

				PdfPCell cell72 = new PdfPCell(new Paragraph("Describe:", font9));
				cell72.setBorder(PdfPCell.NO_BORDER);
				cell72.setGrayFill(0.92f);
				table18.addCell(cell72);
				PdfPCell cell73 = new PdfPCell(new Paragraph(externalCo.getSystemSiteDesc(), font9));
				cell73.setGrayFill(0.92f);
				cell73.setBorder(PdfPCell.NO_BORDER);
				table18.addCell(cell73);

				PdfPCell cell74 = new PdfPCell(new Paragraph("Dimmer switches or large SCR cotrolled loads:", font9));
				cell74.setBorder(PdfPCell.NO_BORDER);
				// cell74.setGrayFill(0.92f);
				table18.addCell(cell74);
				PdfPCell cell75 = new PdfPCell(new Paragraph(externalCo.getControlledLoads(), font9));
				// cell75.setGrayFill(0.92f);
				cell75.setBorder(PdfPCell.NO_BORDER);
				table18.addCell(cell75);

				PdfPCell cell7011 = new PdfPCell(new Paragraph("Describe:", font9));
				cell7011.setBorder(PdfPCell.NO_BORDER);
				cell7011.setGrayFill(0.92f);
				table18.addCell(cell7011);
				PdfPCell cell721 = new PdfPCell(new Paragraph(externalCo.getControlledLoadsDesc(), font9));
				cell721.setGrayFill(0.92f);
				cell721.setBorder(PdfPCell.NO_BORDER);
				table18.addCell(cell721);

				PdfPCell cell723 = new PdfPCell(new Paragraph("Electric railway / traction near by:", font9));
				cell723.setBorder(PdfPCell.NO_BORDER);
				// cell723.setGrayFill(0.92f);
				table18.addCell(cell723);
				PdfPCell cell732 = new PdfPCell(new Paragraph(externalCo.getElectricRailway(), font9));
				// cell732.setGrayFill(0.92f);
				cell732.setBorder(PdfPCell.NO_BORDER);
				table18.addCell(cell732);

				PdfPCell cell743 = new PdfPCell(new Paragraph("Describe:", font9));
				cell743.setBorder(PdfPCell.NO_BORDER);
				cell743.setGrayFill(0.92f);
				table18.addCell(cell743);
				PdfPCell cell752 = new PdfPCell(new Paragraph(externalCo.getElectricRailwayDesc(), font9));
				cell752.setGrayFill(0.92f);
				cell752.setBorder(PdfPCell.NO_BORDER);
				table18.addCell(cell752);

				PdfPCell cell729 = new PdfPCell(new Paragraph("High voltage transmission lines near by:", font9));
				cell729.setBorder(PdfPCell.NO_BORDER);
				// cell729.setGrayFill(0.92f);
				table18.addCell(cell729);
				PdfPCell cell733 = new PdfPCell(new Paragraph(externalCo.getHvTransmission(), font9));
				// cell733.setGrayFill(0.92f);
				cell733.setBorder(PdfPCell.NO_BORDER);
				table18.addCell(cell733);

				PdfPCell cell744 = new PdfPCell(new Paragraph("Describe:", font9));
				cell744.setBorder(PdfPCell.NO_BORDER);
				cell744.setGrayFill(0.92f);
				table18.addCell(cell744);
				PdfPCell cell756 = new PdfPCell(new Paragraph(externalCo.getHvTransmissionDesc(), font9));
				cell756.setGrayFill(0.92f);
				cell756.setBorder(PdfPCell.NO_BORDER);
				table18.addCell(cell756);

				PdfPCell cell727 = new PdfPCell(
						new Paragraph("High power a.c magets or high current feeders nearby:", font9));
				cell727.setBorder(PdfPCell.NO_BORDER);
				// cell727.setGrayFill(0.92f);
				table18.addCell(cell727);
				PdfPCell cell736 = new PdfPCell(new Paragraph(externalCo.getHpAcMangets(), font9));
				// cell736.setGrayFill(0.92f);
				cell736.setBorder(PdfPCell.NO_BORDER);
				table18.addCell(cell736);

				PdfPCell cell741 = new PdfPCell(new Paragraph("Describe:", font9));
				cell741.setBorder(PdfPCell.NO_BORDER);
				cell741.setGrayFill(0.92f);
				table18.addCell(cell741);
				PdfPCell cell758 = new PdfPCell(new Paragraph(externalCo.getHpAcMangetsDesc(), font9));
				cell758.setGrayFill(0.92f);
				cell758.setBorder(PdfPCell.NO_BORDER);
				table18.addCell(cell758);

				PdfPCell cell725 = new PdfPCell(new Paragraph(
						"Approximate distance between electronics system and cable connected to metal parts of the lightning protection system: (in meters):",
						font9));
				cell725.setBorder(PdfPCell.NO_BORDER);
				// cell725.setGrayFill(0.92f);
				table18.addCell(cell725);
				PdfPCell cell730 = new PdfPCell(new Paragraph(externalCo.getApproximateDistance(), font9));
				// cell730.setGrayFill(0.92f);
				cell730.setBorder(PdfPCell.NO_BORDER);
				table18.addCell(cell730);
				document.add(table18);

//				PdfPTable table23 = new PdfPTable(1);
//				table23.setWidthPercentage(100); // Width 100%
//				table23.setSpacingBefore(10f); // Space before table
//				table23.getDefaultCell().setBorder(0);
//				
//				PdfPCell cellSrs = new PdfPCell(new Paragraph(30, "SITE RFI SURVEY", font9));
//				cellSrs.setBorder(PdfPCell.NO_BORDER);
//				cellSrs.setBackgroundColor(BaseColor.LIGHT_GRAY);
//				table23.addCell(cellSrs);
//				document.add(table23);
				
				PdfPTable SiteRFISurveyTable = new PdfPTable(pointColumnHeadLabel);
				SiteRFISurveyTable.setWidthPercentage(100); // Width 100%
				SiteRFISurveyTable.setSpacingBefore(5f); // Space before table
//				SiteRFISurveyTable.setSpacingAfter(5f); // Space after table

				PdfPCell SiteRFISurveyCell = new PdfPCell(new Paragraph("SITE RFI SURVEY", font10B));
				SiteRFISurveyCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				SiteRFISurveyCell.setBackgroundColor(new GrayColor(0.93f));
				SiteRFISurveyCell.setFixedHeight(17f);
				SiteRFISurveyTable.addCell(SiteRFISurveyCell);
				document.add(SiteRFISurveyTable);

				PdfPTable table21 = new PdfPTable(pointColumnWidths1); // 3 columns.
				table21.setWidthPercentage(100); // Width 100%
				table21.setSpacingBefore(5f); // Space before table
//				table21.setSpacingAfter(10f); // Space after table
				table21.getDefaultCell().setBorder(0);

				PdfPCell cell7018 = new PdfPCell(new Paragraph("RFI survey been condcuted on this site:", font9));
				cell7018.setBorder(PdfPCell.NO_BORDER);
				// cell7018.setGrayFill(0.92f);
				table21.addCell(cell7018);
				PdfPCell cell7129 = new PdfPCell(new Paragraph(externalCo.getRfiSurvey(), font9));
				// cell7129.setGrayFill(0.92f);
				cell7129.setBorder(PdfPCell.NO_BORDER);
				table21.addCell(cell7129);

				PdfPCell cell728 = new PdfPCell(new Paragraph("RFI survey needed:", font9));
				cell728.setBorder(PdfPCell.NO_BORDER);
				cell728.setGrayFill(0.92f);
				table21.addCell(cell728);
				PdfPCell cell731 = new PdfPCell(new Paragraph(externalCo.getNewRfiSurvey(), font9));
				cell731.setGrayFill(0.92f);
				cell731.setBorder(PdfPCell.NO_BORDER);
				table21.addCell(cell731);

				PdfPCell cell745 = new PdfPCell(new Paragraph("Describe:", font9));
				cell745.setBorder(PdfPCell.NO_BORDER);
				// cell745.setGrayFill(0.92f);
				table21.addCell(cell745);
				PdfPCell cell755 = new PdfPCell(new Paragraph(externalCo.getNewRfiSurveyDesc(), font9));
				// cell755.setGrayFill(0.92f);
				cell755.setBorder(PdfPCell.NO_BORDER);
				table21.addCell(cell755);
				document.add(table21);

				document.close();
				writer.close();

			} catch (

			Exception e) {
				logger.error("Error while generating PDF in Electro Magnetic Compatability section"+e.getMessage());
				throw new ElectromagneticCompatabilityException("Error while generating PDF in Electro Magnetic Compatability section"+e.getMessage());
			}

		} else

		{
			throw new ElectromagneticCompatabilityException("Invalid Inputs");
		}
	}
}
