package training.test3;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
/**
 * @author MustafaBICER
 * @since 08.07.2014
 */
public class WebCrawler {
	/**
	 * 
	 * @param args metodumuz string degerli argumanlari icerir
	 * @throws IOException hata ayýklar
	 */
	public static void main(String args[]) throws IOException
	{
		/**
		 * @param doc varsayýlan site dokumanýmýzdýr.
		 * @oaram links sitedeki doc.select ile seçtiðimiz satýrlarý tutar.
		 */
		 Document doc = Jsoup.connect("http://safkoy.com").get();
		 Elements links = doc.select("a[href]");
		 System.out.println(links);   
		 FileWriter outFile = new FileWriter(new File("Inputs/linkler.txt"));
         PrintWriter fileOut = new PrintWriter(outFile);
         fileOut.println(links);
         fileOut.close();
	}
	
}