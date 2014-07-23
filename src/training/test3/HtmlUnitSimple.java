package training.test3;

import java.io.IOException;

import java.net.URL;
/*import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import com.gargoylesoftware.htmlunit.BrowserVersion;*/
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
/**
 * @author MustafaBICER
 */
public class HtmlUnitSimple {
public static void main(String args []) throws FailingHttpStatusCodeException, IOException, IOException
{
final WebClient browser = new WebClient();
WebRequest request = new WebRequest(new URL("http://localhost/solar"));
browser.getOptions().setThrowExceptionOnScriptError(false);
browser.setJavaScriptTimeout(10000);
browser.getOptions().setJavaScriptEnabled(true);
browser.setAjaxController(new NicelyResynchronizingAjaxController());
browser.getOptions().setTimeout(10000);
HtmlPage page = browser.getPage(request);
System.out.println(page.asXml());
      String script="var list = [];\n" +
                "\n" +
                "\n" +
                "var size = Object.keys(g_ActiveInventory.rgInventory).size();\n" +
                "\n" +
                "\n" +
                "\n" +
                "var counter = 0;\n" +
                "\n" +
                "while (counter < size) {\n" +
                " list.push(g_ActiveInventory.rgInventory[Object.keys(g_ActiveInventory.rgInventory)[counter]].market_name);\n" +
                " counter +=1;\n" +
                "}";
        Object result = page.executeJavaScript(script).getJavaScriptResult();
        System.out.println(result);
//final WebClient webClient = new WebClient(BrowserVersion.CHROME);
//final HtmlPage page = webClient.getPage("http://safkoy.com");
//Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());
browser.closeAllWindows();
}
}
