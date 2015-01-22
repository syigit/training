package training.test4;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.title.Title;
import org.jfree.data.general.DefaultPieDataset;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Header;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Table {
	public static String [][]dizi=new String[50][50];
	static int k=0,k_fail,k_pass;;
	static String screeenshotDirectory = "C:\\Users\\mustafa\\Desktop\\";
	
	
	public static void main(String [] args) throws MalformedURLException, DocumentException, IOException
	{
		createPdf();
	}
	public static PdfPTable createFirstTable(String [][] testCases) {
    	// a table with three columns
		
        PdfPTable table = new PdfPTable(3);
        // the cell object
        PdfPCell cell;
        // we add a cell with colspan 3
        cell = new PdfPCell(new Phrase("Test Case Name"));
        cell.setColspan(2);
        table.addCell(cell);
        table.addCell("Fail/Pass");
        // now we add a cell with rowspan 2
        cell = new PdfPCell(new Phrase(testCases[0][0].toString()));
        cell.setColspan(2);
        table.addCell(cell);
   
       for(int i=0;i<k;i++)
        {
    	   table.addCell(testCases[i][i+1].toString());
        }
 
    // we add the four remaining cells with addCell()
       return table;
        }
        
 public static  void createPdf() throws DocumentException, MalformedURLException, IOException
 {
	  Document document = new Document();
	  PdfWriter writer;
      writer=PdfWriter.getInstance(document, new FileOutputStream("C://Users/mustafa/Desktop/deneme.pdf"));
      document.open();     
   
		//Phrase subject=new Phrase(dateFormat.format(date)+"\n", new Font(Font.FontFamily.SYMBOL, 8));
      dizi[k][k]="Email Camping";
      dizi[k][k+1]="Fail";    
      k++;
      document.add(createFirstTable(dizi));
      document.addAuthor("Mustafa BÝÇER");
        String imageUrl = "C://Users/mustafa/Desktop/infoowl.jpg";
        Image image2 = Image.getInstance(imageUrl);
        image2.setAlignment(1);
        document.add(image2);
	     	  Paragraph content = new Paragraph(
		     	        "Test Platform: Selenium Web Driver\nTest Browser: Mozilla Firefox 30.0\nElapsed Time: ",new Font(Font.FontFamily.TIMES_ROMAN, 10));
		     	document.add(content);
		     	Paragraph missing = new Paragraph(
		     	        "Requirement:\n-Eclipse IDE\n-Jdk 6\n-Selenium Web Driver\n-Mozilla Firefox maximum 30.0 version\n-iText Plugin\n-Log4j Plugin\n",new Font(Font.FontFamily.TIMES_ROMAN, 10));
		     	document.add(missing);
		     	 DefaultPieDataset dataSet = new DefaultPieDataset();
		         dataSet.setValue("Pass", k);
		         dataSet.setValue("Fail", k);
		         JFreeChart chart = ChartFactory.createPieChart(
		                 "Result Charts", dataSet, true, true, false);	   
		         PiePlot plot = (PiePlot) chart.getPlot();
		         plot.setSectionPaint("Pass", Color.green);
		         plot.setSectionPaint("Fail", Color.red);
		         plot.setBackgroundPaint(Color.white);
		         plot.setLabelBackgroundPaint(Color.white);
		         plot.setCircular(true);
		         plot.setNoDataMessage("Not Found");
		         plot.setLabelFont(new java.awt.Font("Arial",  java.awt.Font.BOLD, 10));
		         TextTitle title = chart.getTitle(); 
		         title.setBorder(0, 0, 2, 0);   
		         title.setBackgroundPaint(Color.white);
		         title.setPaint(Color.DARK_GRAY);
		         title.setExpandToFitSpace(false);
		         java.awt.Image originalImage = chart.createBufferedImage(175, 150);			         
		         Image image1 = Image.getInstance(originalImage,null);
		         image1.setAlignment(1);
		         document.add(image1);
		    	 Paragraph creator=new Paragraph("Tested By MustafaBICER", new Font(Font.FontFamily.TIMES_ROMAN, 8));
		    	 creator.setAlignment(Element.ALIGN_RIGHT);
		    	   DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		   		Date date = new Date();
		   	 Paragraph subject=new Paragraph(dateFormat.format(date)+"\n", new Font(Font.FontFamily.SYMBOL, 8));
		   		subject.setAlignment(Element.ALIGN_RIGHT);
		 		document.add(creator);
		 		document.add(subject);
	    document.close();
	  }
}
