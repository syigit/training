package training.test3;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

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
	 */
	public static void main(String [] args) throws IOException
	{ 
		
		/**
		 * @param doc varsayilan site dokumanimizdir.
		 * @param links sitedeki doc.select ile sectigimiz satirlari tutar.
		 */
		 Document doc = Jsoup.connect("http://safkoy.com").post();
		 Elements links = doc.select("a");
		 FileWriter outFile = new FileWriter(new File("Inputs/linkler.txt"));
         PrintWriter fileOut = new PrintWriter(outFile);
		 for (Element link:links)
		 {
			 System.out.println("http://safkoy.com/"+link.attr("href").replaceAll("http://www.safkoy.com/", "").replaceAll("//", "")); 
		//	 fileOut.println(link.attr("href"));
		 }
         fileOut.close();
	}
	}

