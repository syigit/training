package training.test3;
import java.awt.Desktop;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * @author MustafaBICER
 * @since 08.07.2014
 */
public class WebLinkStroge{
	/**
	 * 
	 * @param args metodumuz string degerli argumanlari icerir
	 * @throws IOException hata ayiklar
	 * @throws URISyntaxException 
	 */
	public static Scanner scanPage;
	public static void main(String [] args) throws IOException, URISyntaxException
	{ 
		System.out.println("Taranacak Sayfa Adresini 'xxxx://abc.xyz/' Seklinde Giriniz");
	    scanPage=new Scanner(System.in);
		String page=scanPage.nextLine();
		System.out.println("Taranacak Sayfa: "+page);		
		/**
		 * @param doc varsayilan site dokumanimizdir.
		 * @param links sitedeki doc.select ile sectigimiz satirlari tutar.
		 */
		
		 Document doc = Jsoup.connect(page).post();
		 Elements links = doc.select("a");
		 FileWriter outFile = new FileWriter(new File("fetch_status.txt"));
         PrintWriter fileOut = new PrintWriter(outFile);
         //Desktop d=Desktop.getDesktop();
         String a;
         Document doc2;
		 for (Element link:links)
		 {
			// System.out.println("http://demo.segmentify.com/"+link.attr("href").replaceAll("http://demo.segmentify.com/", "").replaceAll("//", "")); 
		 doc2=Jsoup.connect(page+link.attr("href").replaceAll(page, "").replaceAll("//", "")).post();
			
			 Elements links2 = doc2.select("a");
			 for (Element link2:links2)
			 {
				
			a=page+link2.attr("href").replaceAll(page, "").replaceAll("//", "");
				 if ( a.contains(".html"))
				 { 
					 fileOut.println(a);
					 System.out.println(a);
					 
					// d.browse(new URI(a));	 
				 }
			 }
			 
			 }
         fileOut.close();
	}
}
