package training.test3;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HtmlUnit {
	public static void main(String [] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException
	{
	WebClient tarayici = new WebClient();
	tarayici.waitForBackgroundJavaScriptStartingBefore(10000);
	HtmlPage page = tarayici.getPage("http://google.com.tr");
	String javaScriptCode = "showPage('3')";
	ScriptResult result = page.executeJavaScript(javaScriptCode);
	result.getJavaScriptResult();
	System.out.println("result: "+ result);
	}
}