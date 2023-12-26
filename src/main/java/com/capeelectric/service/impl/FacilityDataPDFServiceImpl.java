package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.FacilityDataException;
import com.capeelectric.model.FacilityData;
import com.capeelectric.model.FloorCovering;
import com.capeelectric.service.FacilityDataPDFService;
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
public class FacilityDataPDFServiceImpl implements FacilityDataPDFService {

	private static final Logger logger = LoggerFactory.getLogger(FacilityDataPDFServiceImpl.class);	
//	@Override
//	public void printFacilityDataDetails(String userName, Integer emcId) throws FacilityDataException {

	@Override
	public void printFacilityDataDetails(String userName, Integer emcId,Optional<FacilityData> facilityDataRep) throws FacilityDataException {
		if (userName != null && !userName.isEmpty() && emcId != null && emcId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);

			try {

				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("FacilityData.pdf"));

//				List<FacilityData> facilityDataRepo1 = facilityDataRepository.findByUserNameAndEmcId(userName, emcId);
//				FacilityData facilityDataRepo = facilityDataRepo1.get(0);
				FacilityData facilityDataRepo = facilityDataRep.get();

				List<FloorCovering> FloorCovering = facilityDataRepo.getFloorCovering();
				FloorCovering FloorCovering1 = FloorCovering.get(0);

				Font font12B = new Font(BaseFont.createFont(), 12, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font10B = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
			
				Font font9 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

				document.open();
				
				float[] pointColumnHeadLabel = { 100F };
				
				PdfPTable FacilityDataTable = new PdfPTable(pointColumnHeadLabel);
				FacilityDataTable.setWidthPercentage(100); // Width 100%
				FacilityDataTable.setSpacingBefore(5f); // Space before table
				FacilityDataTable.setSpacingAfter(5f); // Space after table

				PdfPCell facilityCell = new PdfPCell(new Paragraph("Facility Data", font12B));
				facilityCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				facilityCell.setBackgroundColor(new GrayColor(0.93f));
				facilityCell.setFixedHeight(20f);
				FacilityDataTable.addCell(facilityCell);
				
				document.add(FacilityDataTable);

//				PdfPCell cellBls = new PdfPCell(new Paragraph(30, "Building Location / Setting", font9));
//				cellBls.setBorder(PdfPCell.NO_BORDER);
//				cellBls.setBackgroundColor(BaseColor.LIGHT_GRAY);
				
				PdfPTable BuildingLocationTable = new PdfPTable(pointColumnHeadLabel);
				BuildingLocationTable.setWidthPercentage(100); // Width 100%
				BuildingLocationTable.setSpacingBefore(5f); // Space before table
				BuildingLocationTable.setSpacingAfter(5f); // Space after table

				PdfPCell buildinLocCell = new PdfPCell(new Paragraph("Building Location / Setting", font10B));
				buildinLocCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				buildinLocCell.setBackgroundColor(new GrayColor(0.93f));
				buildinLocCell.setFixedHeight(17f);
				BuildingLocationTable.addCell(buildinLocCell);
	
				document.add(BuildingLocationTable);

				PdfPTable table10 = new PdfPTable(1);
				table10.setWidthPercentage(100); // Width 100%
				table10.setSpacingBefore(10f); // Space before table
				table10.getDefaultCell().setBorder(0);
//				table10.addCell(cellBls);
				document.add(table10);

				float[] pointColumnWidths1 = { 90F, 90F };

				PdfPTable table = new PdfPTable(pointColumnWidths1); // 3 columns.
				table.setWidthPercentage(100); // Width 100%
				table.setSpacingBefore(5f); // Space before table
//				table7.setSpacingAfter(10f); // Space after table
				table.getDefaultCell().setBorder(0);

				PdfPCell cell = new PdfPCell(new Paragraph("Type:", font9));
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setGrayFill(0.92f);
				table.addCell(cell);
				PdfPCell cell1 = new PdfPCell(new Paragraph(facilityDataRepo.getBlType(), font9));
				cell1.setGrayFill(0.92f);
				cell1.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell1);

				PdfPCell cell2 = new PdfPCell(new Paragraph("Describe others:", font9));
				cell2.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell2);
				PdfPCell cell3 = new PdfPCell(new Paragraph(facilityDataRepo.getBlOtherDescription(), font9));
				cell3.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell3);
				document.add(table);
				
//				PdfPTable table2 = new PdfPTable(1);
//				table2.setWidthPercentage(100); // Width 100%
//				table2.setSpacingBefore(10f); // Space before table
//				table2.getDefaultCell().setBorder(0);
//
//				PdfPCell cellBc = new PdfPCell(new Paragraph(30, "Building Construction", font9));
//				cellBc.setBorder(PdfPCell.NO_BORDER);
//				cellBc.setBackgroundColor(BaseColor.LIGHT_GRAY);
//				table2.addCell(cellBc);
//				document.add(table2);
				
				PdfPTable BuildingConstructionTable = new PdfPTable(pointColumnHeadLabel);
				BuildingConstructionTable.setWidthPercentage(100); // Width 100%
				BuildingConstructionTable.setSpacingBefore(10f); // Space before table
				BuildingConstructionTable.setSpacingAfter(5f); // Space after table

				PdfPCell buildinConCell = new PdfPCell(new Paragraph("Building Construction", font10B));
				buildinConCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				buildinConCell.setBackgroundColor(new GrayColor(0.93f));
				buildinConCell.setFixedHeight(17f);
				BuildingConstructionTable.addCell(buildinConCell);
	
				document.add(BuildingConstructionTable);
				
				
				
				
				

				PdfPTable table3 = new PdfPTable(pointColumnWidths1); // 3 columns.
				table3.setWidthPercentage(100); // Width 100%
				table3.setSpacingBefore(5f); // Space before table
//				table3.setSpacingAfter(10f); // Space after table
				table3.getDefaultCell().setBorder(0);

				PdfPCell cell4 = new PdfPCell(new Paragraph("Type:", font9));
				cell4.setBorder(PdfPCell.NO_BORDER);
				cell4.setGrayFill(0.92f);
				table3.addCell(cell4);
				PdfPCell cell5 = new PdfPCell(new Paragraph(facilityDataRepo.getBcType(), font9));
				cell5.setGrayFill(0.92f);
				cell5.setBorder(PdfPCell.NO_BORDER);
				table3.addCell(cell5);

				PdfPCell cell6 = new PdfPCell(new Paragraph("No. of floors:", font9));
				cell6.setBorder(PdfPCell.NO_BORDER);
				table3.addCell(cell6);
				PdfPCell cell7 = new PdfPCell(new Paragraph(facilityDataRepo.getBcNoOfFloors(), font9));
				cell7.setBorder(PdfPCell.NO_BORDER);
				table3.addCell(cell7);

				PdfPCell cell8 = new PdfPCell(new Paragraph("Computer room floor location:", font9));
				cell8.setBorder(PdfPCell.NO_BORDER);
				cell8.setGrayFill(0.92f);
				table3.addCell(cell8);
				PdfPCell cell9 = new PdfPCell(new Paragraph(facilityDataRepo.getBcRoomFloorLocation(), font9));
				cell9.setGrayFill(0.92f);
				cell9.setBorder(PdfPCell.NO_BORDER);
				table3.addCell(cell9);

				PdfPCell cell10 = new PdfPCell(new Paragraph("Building primary use:", font9));
				cell10.setBorder(PdfPCell.NO_BORDER);
				table3.addCell(cell10);
				PdfPCell cell11 = new PdfPCell(new Paragraph(facilityDataRepo.getBcBuildingPrimaryUse(), font9));
				cell11.setBorder(PdfPCell.NO_BORDER);
				table3.addCell(cell11);

				PdfPCell cell12 = new PdfPCell(new Paragraph("Other uses / processes:", font9));
				cell12.setBorder(PdfPCell.NO_BORDER);
				cell12.setGrayFill(0.92f);
				table3.addCell(cell12);
				PdfPCell cell13 = new PdfPCell(new Paragraph(facilityDataRepo.getBcOtherUses(), font9));
				cell13.setGrayFill(0.92f);
				cell13.setBorder(PdfPCell.NO_BORDER);
				table3.addCell(cell13);
				document.add(table3);

//				PdfPTable table4 = new PdfPTable(1);
//				table4.setWidthPercentage(100); // Width 100%
//				table4.setSpacingBefore(10f); // Space before table
//				table4.getDefaultCell().setBorder(0);
//				
//				PdfPCell cellBl = new PdfPCell(new Paragraph(30, "Room Location", font9));
//				cellBl.setBorder(PdfPCell.NO_BORDER);
//				cellBl.setBackgroundColor(BaseColor.LIGHT_GRAY);
//				table4.addCell(cellBl);
//				document.add(table4);
				
				PdfPTable RoomLocationTable = new PdfPTable(pointColumnHeadLabel);
				RoomLocationTable.setWidthPercentage(100); // Width 100%
				RoomLocationTable.setSpacingBefore(10f); // Space before table
//				RoomLocationTable.setSpacingAfter(5f); // Space after table

				PdfPCell roomLocationCell = new PdfPCell(new Paragraph("Room Location", font10B));
				roomLocationCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				roomLocationCell.setBackgroundColor(new GrayColor(0.93f));
				roomLocationCell.setFixedHeight(17f);
				RoomLocationTable.addCell(roomLocationCell);
	
				document.add(RoomLocationTable);

				PdfPTable table5 = new PdfPTable(pointColumnWidths1); // 3 columns.
				table5.setWidthPercentage(100); // Width 100%
				table5.setSpacingBefore(5f); // Space before table
//				table3.setSpacingAfter(10f); // Space after table
				table5.getDefaultCell().setBorder(0);

				PdfPCell cell14 = new PdfPCell(new Paragraph("Interior room:", font9));
				cell14.setBorder(PdfPCell.NO_BORDER);
				table5.addCell(cell14);
				PdfPCell cell15 = new PdfPCell(new Paragraph(facilityDataRepo.getRlInteriorRoom(), font9));
				cell15.setBorder(PdfPCell.NO_BORDER);
				table5.addCell(cell15);

				PdfPCell cell16 = new PdfPCell(new Paragraph("Exterior room:", font9));
				cell16.setBorder(PdfPCell.NO_BORDER);
				cell16.setGrayFill(0.92f);
				table5.addCell(cell16);
				PdfPCell cell17 = new PdfPCell(new Paragraph(facilityDataRepo.getRlExteriorRoom(), font9));
				cell17.setGrayFill(0.92f);
				cell17.setBorder(PdfPCell.NO_BORDER);
				table5.addCell(cell17);

				PdfPCell cell18 = new PdfPCell(new Paragraph("Solid exterior walls:", font9));
				cell18.setBorder(PdfPCell.NO_BORDER);
				table5.addCell(cell18);
				PdfPCell cell19 = new PdfPCell(new Paragraph(facilityDataRepo.getRlSolidExterior(), font9));
				cell19.setBorder(PdfPCell.NO_BORDER);
				table5.addCell(cell19);

				PdfPCell cell20 = new PdfPCell(new Paragraph("Windowed exterior walls:", font9));
				cell20.setBorder(PdfPCell.NO_BORDER);
				cell20.setGrayFill(0.92f);
				table5.addCell(cell20);
				PdfPCell cell21 = new PdfPCell(new Paragraph(facilityDataRepo.getRlWindowedExterior(), font9));
				cell21.setGrayFill(0.92f);
				cell21.setBorder(PdfPCell.NO_BORDER);
				table5.addCell(cell21);

				PdfPCell cell22 = new PdfPCell(new Paragraph("Windows face (north, south, east, or west):", font9));
				cell22.setBorder(PdfPCell.NO_BORDER);
				table5.addCell(cell22);
				PdfPCell cell23 = new PdfPCell(new Paragraph(facilityDataRepo.getRlWindsFace(), font9));
				cell23.setBorder(PdfPCell.NO_BORDER);
				table5.addCell(cell23);

				document.add(table5);
				
//				PdfPTable table6 = new PdfPTable(1);
//				table6.setWidthPercentage(100); // Width 100%
//				table6.setSpacingBefore(10f); // Space before table
//				table6.getDefaultCell().setBorder(0);
//
//				PdfPCell cellRu = new PdfPCell(new Paragraph(30, "Room Use", font9));
//				cellRu.setBorder(PdfPCell.NO_BORDER);
//				cellRu.setBackgroundColor(BaseColor.LIGHT_GRAY);
//				table6.addCell(cellRu);
//				document.add(table6);
				
				PdfPTable RoomUseTable = new PdfPTable(pointColumnHeadLabel);
				RoomUseTable.setWidthPercentage(100); // Width 100%
				RoomUseTable.setSpacingBefore(10f); // Space before table
//				RoomUseTable.setSpacingAfter(5f); // Space after table

				PdfPCell roomUseCell = new PdfPCell(new Paragraph("Room Use", font10B));
				roomUseCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				roomUseCell.setBackgroundColor(new GrayColor(0.93f));
				roomUseCell.setFixedHeight(17f);
				RoomUseTable.addCell(roomUseCell);
				document.add(RoomUseTable);

				PdfPTable table7 = new PdfPTable(pointColumnWidths1); // 3 columns.
				table7.setWidthPercentage(100); // Width 100%
				table7.setSpacingBefore(10f); // Space before table
//				table7.setSpacingAfter(10f); // Space after table
				table7.getDefaultCell().setBorder(0);

				PdfPCell cell24 = new PdfPCell(new Paragraph("Dedicated room for system:", font9));
				cell24.setBorder(PdfPCell.NO_BORDER);
				cell24.setGrayFill(0.92f);
				table7.addCell(cell24);
				PdfPCell cell25 = new PdfPCell(new Paragraph(facilityDataRepo.getRuDedicated(), font9));
				cell25.setGrayFill(0.92f);
				cell25.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell25);

				PdfPCell cell26 = new PdfPCell(new Paragraph("Describe other equipment in room:", font9));
				cell26.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell26);
				PdfPCell cell27 = new PdfPCell(new Paragraph(facilityDataRepo.getRuOtherDesc(), font9));
				cell27.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell27);
				document.add(table7);

//				PdfPTable table8 = new PdfPTable(1);
//				table8.setWidthPercentage(100); // Width 100%
//				table8.setSpacingBefore(10f); // Space before table
//				table8.getDefaultCell().setBorder(0);
//				
//				PdfPCell cellRd = new PdfPCell(new Paragraph(30, "Room Dimentions", font9));
//				cellRd.setBorder(PdfPCell.NO_BORDER);
//				cellRd.setBackgroundColor(BaseColor.LIGHT_GRAY);
//				table8.addCell(cellRd);
//				document.add(table8);
				
				PdfPTable RoomDimentionsTable = new PdfPTable(pointColumnHeadLabel);
				RoomDimentionsTable.setWidthPercentage(100); // Width 100%
				RoomDimentionsTable.setSpacingBefore(10f); // Space before table
//				RoomDimentionsTable.setSpacingAfter(5f); // Space after table

				PdfPCell roomDimentionsCell = new PdfPCell(new Paragraph("Room Dimentions", font10B));
				roomDimentionsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				roomDimentionsCell.setBackgroundColor(new GrayColor(0.93f));
				roomDimentionsCell.setFixedHeight(17f);
				RoomDimentionsTable.addCell(roomDimentionsCell);
				document.add(RoomDimentionsTable);


				PdfPTable table9 = new PdfPTable(pointColumnWidths1); // 3 columns.
				table9.setWidthPercentage(100); // Width 100%
				table9.setSpacingBefore(10f); // Space before table
//				table9.setSpacingAfter(10f); // Space after table
				table9.getDefaultCell().setBorder(0);

				PdfPCell cell28 = new PdfPCell(new Paragraph("Height (true floor to true ceiling):", font9));
				cell28.setBorder(PdfPCell.NO_BORDER);
				cell28.setGrayFill(0.92f);
				table9.addCell(cell28);
				PdfPCell cell29 = new PdfPCell(new Paragraph(facilityDataRepo.getRmHeightTrueFloor(), font9));
				cell29.setGrayFill(0.92f);
				cell29.setBorder(PdfPCell.NO_BORDER);
				table9.addCell(cell29);

				PdfPCell cell30 = new PdfPCell(new Paragraph("Height (false floor to drop ceiling):", font9));
				cell30.setBorder(PdfPCell.NO_BORDER);
				// cell30.setGrayFill(0.92f);
				table9.addCell(cell30);
				PdfPCell cell31 = new PdfPCell(new Paragraph(facilityDataRepo.getRmHeightFalseFloor(), font9));
				// cell31.setGrayFill(0.92f);
				cell31.setBorder(PdfPCell.NO_BORDER);
				table9.addCell(cell31);

				PdfPCell cell32 = new PdfPCell(new Paragraph("Width:", font9));
				cell32.setBorder(PdfPCell.NO_BORDER);
				cell32.setGrayFill(0.92f);
				table9.addCell(cell32);
				PdfPCell cell33 = new PdfPCell(new Paragraph(facilityDataRepo.getRmWidth(), font9));
				cell33.setGrayFill(0.92f);
				cell33.setBorder(PdfPCell.NO_BORDER);
				table9.addCell(cell33);

				PdfPCell cell34 = new PdfPCell(new Paragraph("Length:", font9));
				cell34.setBorder(PdfPCell.NO_BORDER);
				// cell34.setGrayFill(0.92f);
				table9.addCell(cell34);
				PdfPCell cell35 = new PdfPCell(new Paragraph(facilityDataRepo.getRmLength(), font9));
				// cell35.setGrayFill(0.92f);
				cell35.setBorder(PdfPCell.NO_BORDER);
				table9.addCell(cell35);

				PdfPCell cell36 = new PdfPCell(new Paragraph("Maximum allowable floor loading (Kg/m2):", font9));
				cell36.setBorder(PdfPCell.NO_BORDER);
				cell36.setGrayFill(0.92f);
				table9.addCell(cell36);
				PdfPCell cell37 = new PdfPCell(new Paragraph(facilityDataRepo.getRmMaxFloor(), font9));
				cell37.setGrayFill(0.92f);
				cell37.setBorder(PdfPCell.NO_BORDER);
				table9.addCell(cell37);
				document.add(table9);

//				PdfPTable table11 = new PdfPTable(1);
//				table11.setWidthPercentage(100); // Width 100%
//				table11.setSpacingBefore(10f); // Space before table
//				table11.getDefaultCell().setBorder(0);
//				
//				PdfPCell cellFt = new PdfPCell(new Paragraph(30, "Floor Type", font9));
//				cellFt.setBorder(PdfPCell.NO_BORDER);
//				cellFt.setBackgroundColor(BaseColor.LIGHT_GRAY);
//				table11.addCell(cellFt);
//				document.add(table11);
				
				PdfPTable floorTypeTable = new PdfPTable(pointColumnHeadLabel);
				floorTypeTable.setWidthPercentage(100); // Width 100%
				floorTypeTable.setSpacingBefore(10f); // Space before table
//				floorTypeTable.setSpacingAfter(5f); // Space after table

				PdfPCell floorTypeCell = new PdfPCell(new Paragraph("Floor Type", font10B));
				floorTypeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				floorTypeCell.setBackgroundColor(new GrayColor(0.93f));
				floorTypeCell.setFixedHeight(17f);
				floorTypeTable.addCell(floorTypeCell);
				document.add(floorTypeTable);

				PdfPTable table12 = new PdfPTable(pointColumnWidths1); // 3 columns.
				table12.setWidthPercentage(100); // Width 100%
				table12.setSpacingBefore(10f); // Space before table
//				table12.setSpacingAfter(10f); // Space after table
				table12.getDefaultCell().setBorder(0);

				PdfPCell cell38 = new PdfPCell(new Paragraph("Raised floor:", font9));
				cell38.setBorder(PdfPCell.NO_BORDER);
				cell38.setGrayFill(0.92f);
				table12.addCell(cell38);
				PdfPCell cell39 = new PdfPCell(new Paragraph(facilityDataRepo.getFtRaisedFloor(), font9));
				cell39.setGrayFill(0.92f);
				cell39.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell39);

				PdfPCell cell40 = new PdfPCell(new Paragraph("Used as air supply plenum:", font9));
				cell40.setBorder(PdfPCell.NO_BORDER);
				// cell40.setGrayFill(0.92f);
				table12.addCell(cell40);
				PdfPCell cell41 = new PdfPCell(new Paragraph(facilityDataRepo.getFtAirSupply(), font9));
				// cell41.setGrayFill(0.92f);
				cell41.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell41);

				PdfPCell cell42 = new PdfPCell(new Paragraph("Height:", font9));
				cell42.setBorder(PdfPCell.NO_BORDER);
				cell42.setGrayFill(0.92f);
				table12.addCell(cell42);
				PdfPCell cell43 = new PdfPCell(new Paragraph(facilityDataRepo.getFtHeight(), font9));
				cell43.setGrayFill(0.92f);
				cell43.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell43);

				PdfPCell cell44 = new PdfPCell(new Paragraph("Air flow obstructions:", font9));
				cell44.setBorder(PdfPCell.NO_BORDER);
				// cell44.setGrayFill(0.92f);
				table12.addCell(cell44);
				PdfPCell cell45 = new PdfPCell(new Paragraph(facilityDataRepo.getFtAirFlowObservation(), font9));
				// cell45.setGrayFill(0.92f);
				cell45.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell45);

				PdfPCell cell46 = new PdfPCell(new Paragraph("Description:", font9));
				cell46.setBorder(PdfPCell.NO_BORDER);
				cell46.setGrayFill(0.92f);
				table12.addCell(cell46);
				PdfPCell cell47 = new PdfPCell(new Paragraph(facilityDataRepo.getFtDescription(), font9));
				cell47.setGrayFill(0.92f);
				cell47.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell47);

				PdfPCell cell48 = new PdfPCell(new Paragraph("Air grill dampers:", font9));
				cell48.setBorder(PdfPCell.NO_BORDER);
				// cell48.setGrayFill(0.92f);
				table12.addCell(cell48);
				PdfPCell cell49 = new PdfPCell(new Paragraph(facilityDataRepo.getFtAirGrillDampers(), font9));
				// cell49.setGrayFill(0.92f);
				cell49.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell49);

				PdfPCell cell50 = new PdfPCell(new Paragraph("Cable hole edge guards:", font9));
				cell50.setBorder(PdfPCell.NO_BORDER);
				cell50.setGrayFill(0.92f);
				table12.addCell(cell50);
				PdfPCell cell51 = new PdfPCell(new Paragraph(facilityDataRepo.getFtCableHole(), font9));
				cell51.setGrayFill(0.92f);
				cell51.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell51);

				PdfPCell cell52 = new PdfPCell(new Paragraph("Pedestals:", font9));
				cell52.setBorder(PdfPCell.NO_BORDER);
				// cell52.setGrayFill(0.92f);
				table12.addCell(cell52);
				PdfPCell cell53 = new PdfPCell(new Paragraph(facilityDataRepo.getFtPedestals(), font9));
				// cell53.setGrayFill(0.92f);
				cell53.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell53);

				PdfPCell cell54 = new PdfPCell(new Paragraph("Grids:", font9));
				cell54.setBorder(PdfPCell.NO_BORDER);
				cell54.setGrayFill(0.92f);
				table12.addCell(cell54);
				PdfPCell cell55 = new PdfPCell(new Paragraph(facilityDataRepo.getFtGrids(), font9));
				cell55.setGrayFill(0.92f);
				cell55.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell55);

				PdfPCell cell56 = new PdfPCell(new Paragraph("Bolted:", font9));
				cell56.setBorder(PdfPCell.NO_BORDER);
				// cell56.setGrayFill(0.92f);
				table12.addCell(cell56);
				PdfPCell cell57 = new PdfPCell(new Paragraph(facilityDataRepo.getFtBolted(), font9));
				// cell57.setGrayFill(0.92f);
				cell57.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell57);

				PdfPCell cell58 = new PdfPCell(new Paragraph("Welded:", font9));
				cell58.setBorder(PdfPCell.NO_BORDER);
				cell58.setGrayFill(0.92f);
				table12.addCell(cell58);
				PdfPCell cell59 = new PdfPCell(new Paragraph(facilityDataRepo.getFtWelded(), font9));
				cell59.setGrayFill(0.92f);
				cell59.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell59);

				PdfPCell cell60 = new PdfPCell(new Paragraph("Description of earthing:", font9));
				cell60.setBorder(PdfPCell.NO_BORDER);
				// cell60.setGrayFill(0.92f);
				table12.addCell(cell60);
				PdfPCell cell61 = new PdfPCell(new Paragraph(facilityDataRepo.getFtEarthingDesc(), font9));
				// cell61.setGrayFill(0.92f);
				cell61.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell61);

				PdfPCell cell62 = new PdfPCell(new Paragraph("True floor material:", font9));
				cell62.setBorder(PdfPCell.NO_BORDER);
				cell62.setGrayFill(0.92f);
				table12.addCell(cell62);
				PdfPCell cell63 = new PdfPCell(new Paragraph(facilityDataRepo.getFtTrueFloorMaterial(), font9));
				cell63.setGrayFill(0.92f);
				cell63.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell63);

				PdfPCell cell64 = new PdfPCell(new Paragraph("Describe:", font9));
				cell64.setBorder(PdfPCell.NO_BORDER);
				// cell64.setGrayFill(0.92f);
				table12.addCell(cell64);
				PdfPCell cell65 = new PdfPCell(new Paragraph(facilityDataRepo.getFtDescribe(), font9));
				// cell65.setGrayFill(0.92f);
				cell65.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell65);

				PdfPCell cell66 = new PdfPCell(new Paragraph("Cleanliness:", font9));
				cell66.setBorder(PdfPCell.NO_BORDER);
				cell66.setGrayFill(0.92f);
				table12.addCell(cell66);
				PdfPCell cell67 = new PdfPCell(new Paragraph(facilityDataRepo.getFtCleanliness(), font9));
				cell67.setGrayFill(0.92f);
				cell67.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell67);

				PdfPCell cell68 = new PdfPCell(new Paragraph("Other descriptions:", font9));
				cell68.setBorder(PdfPCell.NO_BORDER);
				// cell68.setGrayFill(0.92f);
				table12.addCell(cell68);
				PdfPCell cell69 = new PdfPCell(new Paragraph(facilityDataRepo.getFtOtherDescription(), font9));
				// cell69.setGrayFill(0.92f);
				cell69.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell69);

				document.add(table12);
				
//				PdfPTable table13 = new PdfPTable(1);
//				table13.setWidthPercentage(100); // Width 100%
//				table13.setSpacingBefore(10f); // Space before table
//				table13.getDefaultCell().setBorder(0);
//				
//				PdfPCell cellFc = new PdfPCell(new Paragraph(30, "Floor Covering", font9));
//				cellFc.setBorder(PdfPCell.NO_BORDER);
//				cellFc.setBackgroundColor(BaseColor.LIGHT_GRAY);
//				table13.addCell(cellFc);
//				document.add(table13);
			
				PdfPTable floorCoveringTable = new PdfPTable(pointColumnHeadLabel);
				floorCoveringTable.setWidthPercentage(100); // Width 100%
				floorCoveringTable.setSpacingBefore(10f); // Space before table
//				floorCoveringTable.setSpacingAfter(5f); // Space after table

				PdfPCell floorCoveingCell = new PdfPCell(new Paragraph("Floor Covering", font10B));
				floorCoveingCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				floorCoveingCell.setBackgroundColor(new GrayColor(0.93f));
				floorCoveingCell.setFixedHeight(17f);
				floorCoveringTable.addCell(floorCoveingCell);
				document.add(floorCoveringTable);
				

				PdfPTable table14 = new PdfPTable(pointColumnWidths1); // 3 columns.
				table14.setWidthPercentage(100); // Width 100%
				table14.setSpacingBefore(10f); // Space before table
//				table14.setSpacingAfter(10f); // Space after table
				table14.getDefaultCell().setBorder(0);

				PdfPCell cell70 = new PdfPCell(new Paragraph("Type:", font9));
				cell70.setBorder(PdfPCell.NO_BORDER);
				cell70.setGrayFill(0.92f);
				table14.addCell(cell70);
				PdfPCell cell71 = new PdfPCell(new Paragraph(FloorCovering1.getFcType(), font9));
				cell71.setGrayFill(0.92f);
				cell71.setBorder(PdfPCell.NO_BORDER);
				table14.addCell(cell71);

				PdfPCell cell72 = new PdfPCell(new Paragraph("Manufacturer:", font9));
				cell72.setBorder(PdfPCell.NO_BORDER);
				// cell72.setGrayFill(0.92f);
				table14.addCell(cell72);
				PdfPCell cell73 = new PdfPCell(new Paragraph(FloorCovering1.getFcManufactor(), font9));
				// cell73.setGrayFill(0.92f);
				cell73.setBorder(PdfPCell.NO_BORDER);
				table14.addCell(cell73);

				PdfPCell cell74 = new PdfPCell(new Paragraph("Static treatment type and description:", font9));
				cell74.setBorder(PdfPCell.NO_BORDER);
				cell74.setGrayFill(0.92f);
				table14.addCell(cell74);
				PdfPCell cell75 = new PdfPCell(new Paragraph(FloorCovering1.getFcDescription(), font9));
				cell75.setGrayFill(0.92f);
				cell75.setBorder(PdfPCell.NO_BORDER);
				table14.addCell(cell75);

				PdfPCell cell76 = new PdfPCell(new Paragraph("Woven:", font9));
				cell76.setBorder(PdfPCell.NO_BORDER);
				// cell76.setGrayFill(0.92f);
				table14.addCell(cell76);
				PdfPCell cell77 = new PdfPCell(new Paragraph(FloorCovering1.getFcWoven(), font9));
				// cell77.setGrayFill(0.92f);
				cell77.setBorder(PdfPCell.NO_BORDER);
				table14.addCell(cell77);

				PdfPCell cell78 = new PdfPCell(new Paragraph("Chemical:", font9));
				cell78.setBorder(PdfPCell.NO_BORDER);
				cell78.setGrayFill(0.92f);
				table14.addCell(cell78);
				PdfPCell cell79 = new PdfPCell(new Paragraph(FloorCovering1.getFcChemical(), font9));
				cell79.setGrayFill(0.92f);
				cell79.setBorder(PdfPCell.NO_BORDER);
				table14.addCell(cell79);

				PdfPCell cell80 = new PdfPCell(new Paragraph("None:", font9));
				cell80.setBorder(PdfPCell.NO_BORDER);
				// cell80.setGrayFill(0.92f);
				table14.addCell(cell80);
				PdfPCell cell81 = new PdfPCell(new Paragraph(FloorCovering1.getFcNone(), font9));
				// cell81.setGrayFill(0.92f);
				cell81.setBorder(PdfPCell.NO_BORDER);
				table14.addCell(cell81);

				PdfPCell cell82 = new PdfPCell(new Paragraph("Others - Describe:", font9));
				cell82.setBorder(PdfPCell.NO_BORDER);
				cell82.setGrayFill(0.92f);
				table14.addCell(cell82);
				PdfPCell cell83 = new PdfPCell(new Paragraph(FloorCovering1.getFcOtherDecription(), font9));
				cell83.setGrayFill(0.92f);
				cell83.setBorder(PdfPCell.NO_BORDER);
				table14.addCell(cell83);
				document.add(table14);
				
//				PdfPTable table16 = new PdfPTable(1);
//				table16.setWidthPercentage(100); // Width 100%
//				table16.setSpacingBefore(10f); // Space before table
//				table16.getDefaultCell().setBorder(0);
//
//				PdfPCell cellW = new PdfPCell(new Paragraph(30, "Walls", font9));
//				cellW.setBorder(PdfPCell.NO_BORDER);
//				cellW.setBackgroundColor(BaseColor.LIGHT_GRAY);
//				table16.addCell(cellW);
//				document.add(table16);
				
				PdfPTable WalssTable = new PdfPTable(pointColumnHeadLabel);
				WalssTable.setWidthPercentage(100); // Width 100%
				WalssTable.setSpacingBefore(10f); // Space before table
//				WalssTable.setSpacingAfter(5f); // Space after table

				PdfPCell wallsCell = new PdfPCell(new Paragraph("Walls", font10B));
				wallsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				wallsCell.setBackgroundColor(new GrayColor(0.93f));
				wallsCell.setFixedHeight(17f);
				WalssTable.addCell(wallsCell);
				document.add(WalssTable);

				PdfPTable table15 = new PdfPTable(pointColumnWidths1);
				table15.setWidthPercentage(100); // Width 100%
				table15.setSpacingBefore(10f); // Space before table
				table15.getDefaultCell().setBorder(0);
				document.add(table15);

				PdfPCell cell85 = new PdfPCell(new Paragraph("Type of construction:", font9));
				cell85.setBorder(PdfPCell.NO_BORDER);
				cell85.setGrayFill(0.92f);
				table15.addCell(cell85);
				PdfPCell cell86 = new PdfPCell(new Paragraph(FloorCovering1.getWallType(), font9));
				cell86.setGrayFill(0.92f);
				cell86.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell86);

				PdfPCell cell84 = new PdfPCell(new Paragraph("Material:", font9));
				cell84.setBorder(PdfPCell.NO_BORDER);
				// cell84.setGrayFill(0.92f);
				table15.addCell(cell84);
				PdfPCell cell87 = new PdfPCell(new Paragraph(FloorCovering1.getWallMaterial(), font9));
				// cell87.setGrayFill(0.92f);
				cell87.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell87);

				PdfPCell cell88 = new PdfPCell(new Paragraph("Covering type:", font9));
				cell88.setBorder(PdfPCell.NO_BORDER);
				cell88.setGrayFill(0.92f);
				table15.addCell(cell88);
				PdfPCell cell89 = new PdfPCell(new Paragraph(FloorCovering1.getWallCovering_Type(), font9));
				cell89.setGrayFill(0.92f);
				cell89.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell89);

				PdfPCell cell90 = new PdfPCell(new Paragraph("Humidity and dust control:", font9));
				cell90.setBorder(PdfPCell.NO_BORDER);
				// cell90.setGrayFill(0.92f);
				table15.addCell(cell90);
				PdfPCell cell91 = new PdfPCell(new Paragraph(FloorCovering1.getWallHumidity(), font9));
				// cell91.setGrayFill(0.92f);
				cell91.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell91);

				PdfPCell cell92 = new PdfPCell(new Paragraph("True floor to true sealing:", font9));
				cell92.setBorder(PdfPCell.NO_BORDER);
				cell92.setGrayFill(0.92f);
				table15.addCell(cell92);
				PdfPCell cell93 = new PdfPCell(new Paragraph(FloorCovering1.getWallSealing(), font9));
				cell93.setGrayFill(0.92f);
				cell93.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell93);

				PdfPCell cell94 = new PdfPCell(new Paragraph("Additional description:", font9));
				cell94.setBorder(PdfPCell.NO_BORDER);
				// cell94.setGrayFill(0.92f);
				table15.addCell(cell94);
				PdfPCell cell95 = new PdfPCell(new Paragraph(FloorCovering1.getWallDesc(), font9));
				// cell95.setGrayFill(0.92f);
				cell95.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell95);
				document.add(table15);

//				PdfPTable table17 = new PdfPTable(1);
//				table17.setWidthPercentage(100); // Width 100%
//				table17.setSpacingBefore(10f); // Space before table
//				table17.getDefaultCell().setBorder(0);
//				
//				PdfPCell cellCc = new PdfPCell(new Paragraph(30, "Ceiling Construction", font9));
//				cellCc.setBorder(PdfPCell.NO_BORDER);
//				cellCc.setBackgroundColor(BaseColor.LIGHT_GRAY);
//				table17.addCell(cellCc);
//				document.add(table17);
				
				PdfPTable CeilingConstructionTable = new PdfPTable(pointColumnHeadLabel);
				CeilingConstructionTable.setWidthPercentage(100); // Width 100%
				CeilingConstructionTable.setSpacingBefore(10f); // Space before table
//				CeilingConstructionTable.setSpacingAfter(5f); // Space after table

				PdfPCell CeilingConstructionTablel = new PdfPCell(new Paragraph("Ceiling Construction", font10B));
				CeilingConstructionTablel.setHorizontalAlignment(Element.ALIGN_LEFT);
				CeilingConstructionTablel.setBackgroundColor(new GrayColor(0.93f));
				CeilingConstructionTablel.setFixedHeight(17f);
				CeilingConstructionTable.addCell(CeilingConstructionTablel);
				document.add(CeilingConstructionTable);

				PdfPTable table18 = new PdfPTable(pointColumnWidths1);
				table18.setWidthPercentage(100); // Width 100%
				table18.setSpacingBefore(10f); // Space before table
				table18.getDefaultCell().setBorder(0);

				PdfPCell cell96 = new PdfPCell(new Paragraph("Description of False (drop) ceiling:", font9));
				cell96.setBorder(PdfPCell.NO_BORDER);
				cell96.setGrayFill(0.92f);
				table18.addCell(cell96);
				PdfPCell cell97 = new PdfPCell(new Paragraph(FloorCovering1.getCcFalseDesc(), font9));
				cell97.setGrayFill(0.92f);
				cell97.setBorder(PdfPCell.NO_BORDER);
				table18.addCell(cell97);

				PdfPCell cell98 = new PdfPCell(new Paragraph("Humidity and dust control:", font9));
				cell98.setBorder(PdfPCell.NO_BORDER);
				// cell98.setGrayFill(0.92f);
				table18.addCell(cell98);
				PdfPCell cell99 = new PdfPCell(new Paragraph(FloorCovering1.getCcFalseHumidity(), font9));
				// cell99.setGrayFill(0.92f);
				cell99.setBorder(PdfPCell.NO_BORDER);
				table18.addCell(cell99);

				PdfPCell cell100 = new PdfPCell(new Paragraph("Height and use of void space:", font9));
				cell100.setBorder(PdfPCell.NO_BORDER);
				cell100.setGrayFill(0.92f);
				table18.addCell(cell100);
				PdfPCell cell101 = new PdfPCell(new Paragraph(FloorCovering1.getCcFalseHeight(), font9));
				cell101.setGrayFill(0.92f);
				cell101.setBorder(PdfPCell.NO_BORDER);
				table18.addCell(cell101);

				PdfPCell cell102 = new PdfPCell(new Paragraph("Utilization of void:", font9));
				cell102.setBorder(PdfPCell.NO_BORDER);
				// cell102.setGrayFill(0.92f);
				table18.addCell(cell102);
				PdfPCell cell103 = new PdfPCell(new Paragraph(FloorCovering1.getCcUtilisation(), font9));
				// cell103.setGrayFill(0.92f);
				cell103.setBorder(PdfPCell.NO_BORDER);
				table18.addCell(cell103);

				PdfPCell cell104 = new PdfPCell(new Paragraph("Description of true ceiling:", font9));
				cell104.setBorder(PdfPCell.NO_BORDER);
				cell104.setGrayFill(0.92f);
				table18.addCell(cell104);
				PdfPCell cell105 = new PdfPCell(new Paragraph(FloorCovering1.getCcTrueDesc(), font9));
				cell105.setGrayFill(0.92f);
				cell105.setBorder(PdfPCell.NO_BORDER);
				table18.addCell(cell105);

				PdfPCell cell106 = new PdfPCell(new Paragraph("Humidity and dust control:", font9));
				cell106.setBorder(PdfPCell.NO_BORDER);
				// cell106.setGrayFill(0.92f);
				table18.addCell(cell106);
				PdfPCell cell107 = new PdfPCell(new Paragraph(FloorCovering1.getCcTrueHumidity(), font9));
				// cell107.setGrayFill(0.92f);
				cell107.setBorder(PdfPCell.NO_BORDER);
				table18.addCell(cell107);

				PdfPCell cell108 = new PdfPCell(
						new Paragraph("Description of surface treatment and condition/clealiness:", font9));
				cell108.setBorder(PdfPCell.NO_BORDER);
				cell108.setGrayFill(0.92f);
				table18.addCell(cell108);
				PdfPCell cell109 = new PdfPCell(new Paragraph(FloorCovering1.getCcSurfaceDesc(), font9));
				cell109.setGrayFill(0.92f);
				cell109.setBorder(PdfPCell.NO_BORDER);
				table18.addCell(cell109);
				document.add(table18);

//				PdfPCell cellWi = new PdfPCell(new Paragraph(30, "Windows", font9));
//				cellWi.setBorder(PdfPCell.NO_BORDER);
//				cellWi.setBackgroundColor(BaseColor.LIGHT_GRAY);
//
//				PdfPTable table19 = new PdfPTable(1);
//				table19.setWidthPercentage(100); // Width 100%
//				table19.setSpacingBefore(10f); // Space before table
//				table19.getDefaultCell().setBorder(0);
//				table19.addCell(cellWi);
//				document.add(table19);
				
				PdfPTable WindowsTable = new PdfPTable(pointColumnHeadLabel);
				WindowsTable.setWidthPercentage(100); // Width 100%
				WindowsTable.setSpacingBefore(10f); // Space before table
//				WindowsTable.setSpacingAfter(5f); // Space after table

				PdfPCell WindowsCell = new PdfPCell(new Paragraph("Windows", font10B));
				WindowsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				WindowsCell.setBackgroundColor(new GrayColor(0.93f));
				WindowsCell.setFixedHeight(17f);
				WindowsTable.addCell(WindowsCell);
				document.add(WindowsTable);


				PdfPTable table20 = new PdfPTable(pointColumnWidths1);
				table20.setWidthPercentage(100); // Width 100%
				table20.setSpacingBefore(10f); // Space before table
				table20.getDefaultCell().setBorder(0);
				document.add(table20);

				PdfPCell cell112 = new PdfPCell(new Paragraph("External:", font9));
				cell112.setBorder(PdfPCell.NO_BORDER);
				cell112.setGrayFill(0.92f);
				table20.addCell(cell112);
				PdfPCell cell113 = new PdfPCell(new Paragraph(FloorCovering1.getWindowsExternal(), font9));
				cell113.setGrayFill(0.92f);
				cell113.setBorder(PdfPCell.NO_BORDER);
				table20.addCell(cell113);

				PdfPCell cell114 = new PdfPCell(new Paragraph("Description of construction:", font9));
				cell114.setBorder(PdfPCell.NO_BORDER);
				// cell114.setGrayFill(0.92f);
				table20.addCell(cell114);
				PdfPCell cell115 = new PdfPCell(new Paragraph(FloorCovering1.getWindowsDescription(), font9));
				// cell115.setGrayFill(0.92f);
				cell115.setBorder(PdfPCell.NO_BORDER);
				table20.addCell(cell115);

				PdfPCell cell110 = new PdfPCell(new Paragraph("Window covering:", font9));
				cell110.setBorder(PdfPCell.NO_BORDER);
				cell110.setGrayFill(0.92f);
				table20.addCell(cell110);
				PdfPCell cell111 = new PdfPCell(new Paragraph(FloorCovering1.getWindowsCovering(), font9));
				cell111.setGrayFill(0.92f);
				cell111.setBorder(PdfPCell.NO_BORDER);
				table20.addCell(cell111);

				PdfPCell cell116 = new PdfPCell(new Paragraph("Describe others:", font9));
				cell116.setBorder(PdfPCell.NO_BORDER);
				// cell116.setGrayFill(0.92f);
				table20.addCell(cell116);
				PdfPCell cell117 = new PdfPCell(new Paragraph(FloorCovering1.getWindowsOtherDesc(), font9));
				// cell117.setGrayFill(0.92f);
				cell117.setBorder(PdfPCell.NO_BORDER);
				table20.addCell(cell117);

				PdfPCell cell118 = new PdfPCell(new Paragraph("Description of Internal windows:", font9));
				cell118.setBorder(PdfPCell.NO_BORDER);
				cell118.setGrayFill(0.92f);
				table20.addCell(cell118);
				PdfPCell cell119 = new PdfPCell(new Paragraph(FloorCovering1.getWindowsInternalDesc(), font9));
				cell119.setGrayFill(0.92f);
				cell119.setBorder(PdfPCell.NO_BORDER);
				table20.addCell(cell119);
				document.add(table20);

//				PdfPTable table21 = new PdfPTable(1);
//				table21.setWidthPercentage(100); // Width 100%
//				table21.setSpacingBefore(10f); // Space before table
//				table21.getDefaultCell().setBorder(0);
//				
//				PdfPCell cellD = new PdfPCell(new Paragraph(30, "Doors", font9));
//				cellD.setBorder(PdfPCell.NO_BORDER);
//				cellD.setBackgroundColor(BaseColor.LIGHT_GRAY);
//				table21.addCell(cellD);
//				document.add(table21);
				
				PdfPTable DoorsTable = new PdfPTable(pointColumnHeadLabel);
				DoorsTable.setWidthPercentage(100); // Width 100%
				DoorsTable.setSpacingBefore(10f); // Space before table
//				DoorsTable.setSpacingAfter(5f); // Space after table

				PdfPCell DoorsCell = new PdfPCell(new Paragraph("Doors", font10B));
				DoorsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				DoorsCell.setBackgroundColor(new GrayColor(0.93f));
				DoorsCell.setFixedHeight(17f);
				DoorsTable.addCell(DoorsCell);
				document.add(DoorsTable);

				PdfPTable table22 = new PdfPTable(pointColumnWidths1);
				table22.setWidthPercentage(100); // Width 100%
				table22.setSpacingBefore(10f); // Space before table
				table22.getDefaultCell().setBorder(0);

				PdfPCell cell126 = new PdfPCell(new Paragraph("Material:", font9));
				cell126.setBorder(PdfPCell.NO_BORDER);
				cell126.setGrayFill(0.92f);
				table22.addCell(cell126);
				PdfPCell cell127 = new PdfPCell(new Paragraph(FloorCovering1.getDoorsMaterial(), font9));
				cell127.setGrayFill(0.92f);
				cell127.setBorder(PdfPCell.NO_BORDER);
				table22.addCell(cell127);

				PdfPCell cell120 = new PdfPCell(new Paragraph("Number:", font9));
				cell120.setBorder(PdfPCell.NO_BORDER);
				// cell120.setGrayFill(0.92f);
				table22.addCell(cell120);
				PdfPCell cell121 = new PdfPCell(new Paragraph(FloorCovering1.getDoorsNumber(), font9));
				// cell121.setGrayFill(0.92f);
				cell121.setBorder(PdfPCell.NO_BORDER);
				table22.addCell(cell121);

				PdfPCell cell128 = new PdfPCell(new Paragraph("Width:", font9));
				cell128.setBorder(PdfPCell.NO_BORDER);
				cell128.setGrayFill(0.92f);
				table22.addCell(cell128);
				PdfPCell cell129 = new PdfPCell(new Paragraph(FloorCovering1.getDoorsWidth(), font9));
				cell129.setGrayFill(0.92f);
				cell129.setBorder(PdfPCell.NO_BORDER);
				table22.addCell(cell129);

				PdfPCell cell130 = new PdfPCell(new Paragraph("Height:", font9));
				cell130.setBorder(PdfPCell.NO_BORDER);
				// cell130.setGrayFill(0.92f);
				table22.addCell(cell130);
				PdfPCell cell131 = new PdfPCell(new Paragraph(FloorCovering1.getDoorsHeight(), font9));
				// cell131.setGrayFill(0.92f);
				cell131.setBorder(PdfPCell.NO_BORDER);
				table22.addCell(cell131);

				PdfPCell cell132 = new PdfPCell(new Paragraph("Closure mechanism:", font9));
				cell132.setBorder(PdfPCell.NO_BORDER);
				cell132.setGrayFill(0.92f);
				table22.addCell(cell132);
				PdfPCell cell133 = new PdfPCell(new Paragraph(FloorCovering1.getDoorsCloserMechanish(), font9));
				cell133.setGrayFill(0.92f);
				cell133.setBorder(PdfPCell.NO_BORDER);
				table22.addCell(cell133);

				PdfPCell cell134 = new PdfPCell(new Paragraph("Quality of air sealing:", font9));
				cell134.setBorder(PdfPCell.NO_BORDER);
				// cell134.setGrayFill(0.92f);
				table22.addCell(cell134);
				PdfPCell cell135 = new PdfPCell(new Paragraph(FloorCovering1.getDoorsQualitySealing(), font9));
				// cell135.setGrayFill(0.92f);
				cell135.setBorder(PdfPCell.NO_BORDER);
				table22.addCell(cell135);

				PdfPCell cell136 = new PdfPCell(new Paragraph("Description of service area provided:", font9));
				cell136.setBorder(PdfPCell.NO_BORDER);
				cell136.setGrayFill(0.92f);
				table22.addCell(cell136);
				PdfPCell cell137 = new PdfPCell(new Paragraph(FloorCovering1.getDoorsDesc(), font9));
				cell137.setGrayFill(0.92f);
				cell137.setBorder(PdfPCell.NO_BORDER);
				table22.addCell(cell137);
				document.add(table22);
				
				
				PdfPCell cellNote = new PdfPCell(new Paragraph(25, "Note: Attach room layout as close to scale as possible (or attach site plan drawing, if current). Show all major equipment: windows, doors, furniture, and so forth. If possible, make three copies of this drawing for use in subsequent survey sections.", font9));
				cellNote.setBorder(PdfPCell.NO_BORDER);
				//cellNote.setBackgroundColor(BaseColor.LIGHT_GRAY);

				PdfPTable table29 = new PdfPTable(1);
				table29.setWidthPercentage(100); // Width 100%
				table29.setSpacingBefore(10f); // Space before table
				table29.getDefaultCell().setBorder(0);
				table29.addCell(cellNote);
				document.add(table29);

				document.close();
				writer.close();

			} catch (

			Exception e) {
				logger.error("Error while generating PDF in Facility Data section"+e.getMessage());
				throw new FacilityDataException("Error while generating PDF in Facility Data section"+e.getMessage());
			}

		} else

		{
			logger.error("Error while generating PDF in Facility Data section");
			throw new FacilityDataException("Error while generating PDF in Facility Data section");
		}
	}
}
