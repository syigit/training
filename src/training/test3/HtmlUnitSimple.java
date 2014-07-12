package training.test3;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HtmlUnitSimple {
public static void main(String args []) throws FailingHttpStatusCodeException, IOException, IOException
{
WebClient browser=new WebClient(); //Creating virtual browser
HtmlPage page=browser.getPage("http://www.simpleweb.org/"); // open web page at browser
System.out.println(page.asText());// print page


//final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
//final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
//Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());

browser.closeAllWindows();

}
}
