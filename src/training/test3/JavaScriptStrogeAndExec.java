package training.test3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
/**
 * @author MustafaBICER
 * @since 21.07.2014
 */
public class JavaScriptStrogeAndExec {	
//	private static Scanner scanNumber;
	public static Scanner scanPage;
	public static void main(String [] args) throws IOException
	{
		System.out.println("Taranacak Sayfa Adresini 'xxxx://abc.xyz' Seklinde Giriniz");
		scanPage=new Scanner(System.in);
		Stroge(scanPage.nextLine()+"/");
		
	}
	/**
	 * 
	 * @param website methodumuzun web site degerini yakalamasini ve return etmesini saglar.
	 * @throws IOException
	 */
	public static void Stroge(String website) throws IOException
	{	
		  String adress=website;
		 Document doc;
			doc = Jsoup.connect(adress).get();
			 Elements links = doc.select("script");
			 FileWriter outFile = new FileWriter(new File("Inputs/scriptler.txt"));
	         PrintWriter fileOut = new PrintWriter(outFile);
			 for (Element link:links)
			 {
				 if (link.attr("src")!="")
				 {
			     System.out.println(link.attr("src"));
				 fileOut.println(link.attr("src"));
				 } 
			 }
			  fileOut.close();
		ExecuteJavaScript(adress);		
	}
	
/**
 * 
 * @param website methodumuzun web site degerini yakalamasini ve return etmesini saglar.
 * @throws IOException
 */
public static void ExecuteJavaScript(String website) throws IOException
{
 //scanNumber = new Scanner(System.in);
	String [] dizi=new String[1000];
    File dosya = new File("Inputs/scriptler.txt");
    BufferedReader reader = null;
    reader = new BufferedReader(new FileReader(dosya));
    String satir = (reader.readLine());
    int i=0;
    WebClient browser=new WebClient();
    browser.getOptions().setThrowExceptionOnScriptError(false);
    HtmlPage page=browser.getPage(website); 
    browser.setAjaxController(new NicelyResynchronizingAjaxController());
    while (satir != null) {
          	dizi[i]=satir;
          	i++;
        satir = reader.readLine();
    }
       for(int j=0;j<i;j++)
       {
       //	System.out.println(j+"nci script "+dizi[j]);
    	   page.executeJavaScript(dizi[j]);    	   
       }
   //	page.executeJavaScript("http://www.safkoy.com/jslib/idea.js");
    /*   System.out.println("Hangi Script Çalýþtýrýlsýn?");
       int a=Integer.parseInt(scanNumber.nextLine());
       System.out.println(page.executeJavaScript(dizi[a]));
       */
    reader.close();	
}
}