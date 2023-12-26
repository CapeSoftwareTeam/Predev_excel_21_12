package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.ClientDetailsException;
import com.capeelectric.model.ClientDetails;
import com.capeelectric.service.ClientDetailsPDFService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
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
public class ClientDetailsPDFServiceImpl implements ClientDetailsPDFService {

//	@Autowired
//	private ClientDetailsRepository clientDetailsRepository;

//	@Override
//	public void printClientDetails(String userName, Integer emcId) throws ClientDetailsException {
		
	private static final Logger logger = LoggerFactory.getLogger(ClientDetailsPDFServiceImpl.class);
	@Override
	public void printClientDetails(String userName, Integer emcId, List<ClientDetails> clientDetailsRepo) throws ClientDetailsException {
		if (userName != null && !userName.isEmpty() && emcId != null && emcId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);

			try {

				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("ClientDetails.pdf"));

				ClientDetails clientDetailsRepo1 = clientDetailsRepo.get(0);

				Font font9 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

				document.open();

				document.open();

				Font font12B = new Font(BaseFont.createFont(), 12, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font10N = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

				float[] pointColumnWidths0 = { 100F };

				PdfPTable titlebox = new PdfPTable(pointColumnWidths0);
				titlebox.setWidthPercentage(100); // Width 100%
				titlebox.setSpacingBefore(10f); // Space before table
				titlebox.getDefaultCell().setBorder(15);

				PdfPCell cell20 = new PdfPCell(
						new Paragraph("\r\n" + "EMC assessment of an installation (IEC 61000-5-1)", font12B));
				cell20.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell20.setFixedHeight(40);
				titlebox.addCell(cell20);
				document.add(titlebox);

				PdfPTable projectHeader = new PdfPTable(pointColumnWidths0);
				projectHeader.setSpacingBefore(15f); // Space before table
				//				projectHeader.setSpacingAfter(7f); // Space after table
				projectHeader.setWidthPercentage(100);
				projectHeader.getDefaultCell().setBorder(0);

				PdfPCell title = new PdfPCell(
						new Paragraph("Project", new Font(BaseFont.createFont(), 11, Font.NORMAL)));
				title.setBackgroundColor(new GrayColor(0.82f));
				title.setHorizontalAlignment(Element.ALIGN_LEFT);
				title.setBorder(PdfPCell.NO_BORDER);
				projectHeader.addCell(title);

				document.add(projectHeader);

				PdfPTable titlebox1 = new PdfPTable(pointColumnWidths0);
				titlebox1.setWidthPercentage(100); // Width 100%
				titlebox1.setSpacingBefore(10f); // Space before table
				titlebox1.getDefaultCell().setBorder(15);

				PdfPCell cell25 = new PdfPCell(new Paragraph("", font10N));
				cell25.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell25.setFixedHeight(75);
				titlebox1.addCell(cell25);

				document.add(titlebox1);

				float[] pointColumnWidths11 = { 30F, 30F };
				PdfPTable table11 = new PdfPTable(pointColumnWidths11);

				table11.setWidthPercentage(60); // Width 100%
				table11.setSpacingBefore(25f); // Space before table
				table11.setSpacingAfter(15f); // Space after table
				table11.getDefaultCell().setBorder(15);

				PdfPCell certificate12 = new PdfPCell(
						new Paragraph("", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
				table11.addCell(
						new Phrase("Starting date of inspection", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
				certificate12.setHorizontalAlignment(Element.ALIGN_LEFT);
				certificate12.setBorder(15);
				certificate12.setFixedHeight(25f);
				table11.addCell(certificate12);

				PdfPCell certificate13 = new PdfPCell(
						new Paragraph("", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
				table11.addCell(
						new Phrase("Ending date of inspection", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
				certificate13.setHorizontalAlignment(Element.ALIGN_LEFT);
				certificate13.setFixedHeight(25f);
				certificate13.setBorder(15);
				table11.addCell(certificate13);
				document.add(table11);

				float[] pointColumnWidths2 = { 90F, 90F, 90F };

				PdfPTable table2 = new PdfPTable(pointColumnWidths2);
				table2.setWidthPercentage(100); // Width 100%
				table2.setSpacingBefore(10f); // Space before table
				table2.getDefaultCell().setBorder(15);

				PdfPCell cell1 = new PdfPCell(new Paragraph("Inspected by", font10N));
				cell1.setGrayFill(0.92f);
				cell1.setFixedHeight(25f);
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table2.addCell(cell1);

				PdfPCell cell3 = new PdfPCell(new Paragraph("Inspected by", font10N));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setGrayFill(0.92f);
				cell3.setFixedHeight(25f);
				table2.addCell(cell3);

				PdfPCell cell4 = new PdfPCell(new Paragraph("Witnessed by", font10N));
				cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell4.setGrayFill(0.92f);
				cell4.setFixedHeight(25f);
				table2.addCell(cell4);

				PdfPCell cell5 = new PdfPCell(new Paragraph("", font10N));
				cell5.setFixedHeight(25f);
				cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
				table2.addCell(cell5);

				PdfPCell cell6 = new PdfPCell(new Paragraph("", font10N));
				cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell6.setFixedHeight(25f);
				table2.addCell(cell6);

				PdfPCell cell19 = new PdfPCell(new Paragraph("", font10N));
				cell19.setFixedHeight(25f);
				cell19.setHorizontalAlignment(Element.ALIGN_CENTER);
				table2.addCell(cell19);

				PdfPCell cell55 = new PdfPCell(new Paragraph("", font10N));
				cell55.setFixedHeight(25f);
				cell55.setHorizontalAlignment(Element.ALIGN_CENTER);
				table2.addCell(cell55);

				PdfPCell cell66 = new PdfPCell(new Paragraph("", font10N));
				cell66.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell66.setFixedHeight(25f);
				table2.addCell(cell66);

				PdfPCell cell77 = new PdfPCell(new Paragraph("", font10N));
				cell77.setFixedHeight(25f);
				cell77.setHorizontalAlignment(Element.ALIGN_CENTER);
				table2.addCell(cell77);
				document.add(table2);

				float[] pointColumnWidths3 = { 100F };

				PdfPTable table3 = new PdfPTable(pointColumnWidths3);
				table3.setWidthPercentage(33); // Width 100%
				table3.setSpacingBefore(20f); // Space before table
				table3.getDefaultCell().setBorder(15);

				PdfPCell cell8 = new PdfPCell(new Paragraph("Reviewed by", font10N));
				cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell8.setGrayFill(0.92f);
				cell8.setFixedHeight(25f);
				table3.addCell(cell8);

				PdfPCell cell9 = new PdfPCell(new Paragraph("", font10N));
				cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell9.setFixedHeight(25f);
				table3.addCell(cell9);

				PdfPCell cell7 = new PdfPCell(new Paragraph("", font10N));
				cell7.setFixedHeight(25f);
				cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
				table3.addCell(cell7);

				document.add(table3);

				//				float[] pointColumnWidths3 = {100F };
				PdfPTable comment = new PdfPTable(pointColumnWidths3);
				comment.setSpacingBefore(15f); // Space before table
				//				comment.setSpacingAfter(7f); // Space after table
				comment.setWidthPercentage(100);
				comment.getDefaultCell().setBorder(0);

				PdfPCell comment1 = new PdfPCell(
						new Paragraph("Comments", new Font(BaseFont.createFont(), 11, Font.NORMAL)));
				comment1.setBackgroundColor(new GrayColor(0.82f));
				comment1.setHorizontalAlignment(Element.ALIGN_LEFT);
				comment1.setBorder(PdfPCell.NO_BORDER);
				comment.addCell(comment1);
				document.add(comment);

				PdfPTable table41 = new PdfPTable(pointColumnWidths3);
				table41.setWidthPercentage(100); // Width 100%
				table41.setSpacingBefore(10f); // Space before table
				table41.getDefaultCell().setBorder(15);

				PdfPCell cell21 = new PdfPCell(new Paragraph("", font10N));
				cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell21.setFixedHeight(200);
				table41.addCell(cell21);

				document.add(table41);

				document.newPage();

				//				PdfPTable table10 = new PdfPTable(1);
				//				table10.setWidthPercentage(100); // Width 100%
				//				table10.setSpacingBefore(10f); // Space before table
				//				table10.getDefaultCell().setBorder(0);
				//
				//				PdfPCell cell45 = new PdfPCell(new Paragraph(30, "Client Details", font9));
				//				cell45.setBorder(PdfPCell.NO_BORDER);
				//				cell45.setBackgroundColor(BaseColor.LIGHT_GRAY);
				//				table10.addCell(cell45);
				//				document.add(table10);

				Font font10B = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);

				PdfPTable ClientDetailsTable = new PdfPTable(1);
				ClientDetailsTable.setWidthPercentage(100); // Width 100%
				ClientDetailsTable.setSpacingBefore(5f); // Space before table
				//				ClientDetailsTable.setSpacingAfter(5f); // Space after table

				PdfPCell cleintDetailsCell = new PdfPCell(new Paragraph("Client Details", font10B));
				cleintDetailsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cleintDetailsCell.setBackgroundColor(new GrayColor(0.90f));
				cleintDetailsCell.setFixedHeight(17f);
				ClientDetailsTable.addCell(cleintDetailsCell);
				document.add(ClientDetailsTable);

				float[] pointColumnWidths1 = { 90F, 90F };

				PdfPTable table = new PdfPTable(pointColumnWidths1); // 3 columns.
				table.setWidthPercentage(100); // Width 100%
				table.setSpacingBefore(10f); // Space before table
				//				table7.setSpacingAfter(10f); // Space after table
				table.getDefaultCell().setBorder(0);

				PdfPCell cell = new PdfPCell(new Paragraph("Client Name:", font9));
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setGrayFill(0.92f);
				table.addCell(cell);
				PdfPCell cell11 = new PdfPCell(new Paragraph(clientDetailsRepo1.getClientName(), font9));
				cell11.setGrayFill(0.92f);
				cell11.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell11);

				PdfPCell cell2 = new PdfPCell(new Paragraph("Contact Number:", font9));
				cell2.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell2);
				PdfPCell cell31 = new PdfPCell(new Paragraph(clientDetailsRepo1.getContactNumber(), font9));
				cell31.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell31);

				PdfPCell cell41 = new PdfPCell(new Paragraph("Contact Person:", font9));
				cell41.setBorder(PdfPCell.NO_BORDER);
				cell41.setGrayFill(0.92f);
				table.addCell(cell41);
				PdfPCell cell51 = new PdfPCell(new Paragraph(clientDetailsRepo1.getContactPerson(), font9));
				cell51.setGrayFill(0.92f);
				cell51.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell51);

				PdfPCell cell61 = new PdfPCell(new Paragraph("Landmark:", font9));
				cell61.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell61);
				PdfPCell cell71 = new PdfPCell(new Paragraph(clientDetailsRepo1.getLandMark(), font9));
				cell71.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell71);

				PdfPCell cell81 = new PdfPCell(new Paragraph("Location:", font9));
				cell81.setBorder(PdfPCell.NO_BORDER);
				cell81.setGrayFill(0.92f);
				table.addCell(cell81);
				PdfPCell cell91 = new PdfPCell(new Paragraph(clientDetailsRepo1.getClientLocation(), font9));
				cell91.setGrayFill(0.92f);
				cell91.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell91);

				PdfPCell cell10 = new PdfPCell(new Paragraph("Address:", font9));
				cell10.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell10);
				PdfPCell cell111 = new PdfPCell(new Paragraph(clientDetailsRepo1.getClientAddress(), font9));
				cell111.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell111);

				PdfPCell cell12 = new PdfPCell(new Paragraph("Email:", font9));
				cell12.setBorder(PdfPCell.NO_BORDER);
				cell12.setGrayFill(0.92f);
				table.addCell(cell12);
				PdfPCell cell13 = new PdfPCell(new Paragraph(clientDetailsRepo1.getEmail(), font9));
				cell13.setGrayFill(0.92f);
				cell13.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell13);

				//				PdfPCell cell14 = new PdfPCell(new Paragraph("Address:", font9));
				//				cell14.setBorder(PdfPCell.NO_BORDER);
				//				table.addCell(cell14);
				//				PdfPCell cell15 = new PdfPCell(new Paragraph(clientDetailsRepo1.getClientAddress(), font9));
				//				cell15.setBorder(PdfPCell.NO_BORDER);
				//				table.addCell(cell15);

				PdfPCell cell16 = new PdfPCell(new Paragraph("Country:", font9));
				cell16.setBorder(PdfPCell.NO_BORDER);
				//cell16.setGrayFill(0.92f);
				table.addCell(cell16);
				PdfPCell cell17 = new PdfPCell(new Paragraph(clientDetailsRepo1.getCountry(), font9));
				//cell17.setGrayFill(0.92f);
				cell17.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell17);

				PdfPCell cell18 = new PdfPCell(new Paragraph("State:", font9));
				cell18.setBorder(PdfPCell.NO_BORDER);
				cell18.setGrayFill(0.92f);
				table.addCell(cell18);
				PdfPCell cell191 = new PdfPCell(new Paragraph(clientDetailsRepo1.getState(), font9));
				cell191.setBorder(PdfPCell.NO_BORDER);
				cell191.setGrayFill(0.92f);
				table.addCell(cell191);

				document.add(table);

				document.close();
				writer.close();

			} catch (Exception e) {
				logger.error("Error while generating PDF in client section"+e.getMessage());
				throw new ClientDetailsException("Error while generating PDF in client section"+e.getMessage());
			}

		} else

		{
			logger.error("Error while generating PDF in client section");
			throw new ClientDetailsException("Error while generating PDF in client section");
		}

	}
}
