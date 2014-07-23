package training.test3;

/*
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CollectingAlertHandler;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;
*/

public class HtmlUnitExample {
	public static void main(String[] args) throws Exception{
		/*
		int deneme = 0;
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		final List<String> collectedAlerts = new ArrayList<String>();
		webClient.setAlertHandler(new CollectingAlertHandler(collectedAlerts));
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.getOptions().setCssEnabled(true);
		webClient.getOptions().setRedirectEnabled(false);
		webClient.getOptions().setAppletEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setPopupBlockerEnabled(true);
		webClient.getOptions().setTimeout(10000);
		webClient.getOptions().setActiveXNative(false);
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(true);
		webClient.getOptions().setThrowExceptionOnScriptError(true);
		webClient.getOptions().setPrintContentOnFailingStatusCode(true);
		WebRequest request = new WebRequest(new URL("http://localhost/solar"), HttpMethod.GET);
		try {
		    HtmlPage page = webClient.getPage(request);
		    webClient.waitForBackgroundJavaScript(10000);
		    //just wait
		   for (int i = 0; i < 20; i++) {
		        synchronized (page) {
		            page.wait(500);
		            deneme=i;
		            
		        }
		        return;
		    }
		} finally {
		    webClient.closeAllWindows();
		    for (final String alert : collectedAlerts) {
		        System.out.println("ALERT: " + alert);
		        System.out.println(deneme);
		    }
		}
		*/
		
	}
	
}
