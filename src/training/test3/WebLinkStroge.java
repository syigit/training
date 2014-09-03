package training.test3;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.Scanner;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
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
	public static Selenium selenium,selenium2,selenium3,selenium4;
	public static Scanner scanPage;
	public static void main(String [] args) throws IOException, URISyntaxException, InterruptedException
	{ int i =0;
	
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
     	
          WebDriver driver =new FirefoxDriver();
        WebDriver driver2=new FirefoxDriver();
		String baseUrl = page;
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
		selenium2=new WebDriverBackedSelenium(driver2, baseUrl);
         String a;
         Document doc2;
         Elements links2;
		 for (Element link:links)
		 {
			
			// Thread.sleep(3000);
			 try
			 {
		 doc2=Jsoup.connect(page+link.attr("href").replaceAll(page, "").replaceAll("//", "")).post();
			 links2 = doc2.select("a");
			 }
			 catch(Exception e)
			 {continue;}
			 for (Element link2:links2)
			 {
				 i++;
				 
				 // Thread.sleep(3000);
				try
				{
					 a=page+link2.attr("href").replaceAll(page, "");
					 fileOut.println(a);
					 System.out.println(a);
					 if (i%2==0){selenium.open(a);}
				     selenium2.open(a);
				}
				catch(Exception e){continue;}
			 } }
         fileOut.close();
	}
}