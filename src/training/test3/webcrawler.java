package training.test3;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class webcrawler {
	public static void main(String args[]) throws IOException
	{
		 Document doc = Jsoup.connect("http://safkoy.com").get();
		 Elements links = doc.select("a[href]");
		 System.out.println(links);   
		 FileWriter outFile = new FileWriter(new File("Inputs/linkler.txt"));
         PrintWriter out = new PrintWriter(outFile);
         out.println(links);
         out.close();
	}
	
}