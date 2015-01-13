package training.test4;

import java.io.IOException;
import java.net.URISyntaxException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.thoughtworks.selenium.Selenium;
/**
 * @author MustafaBICER
 * @since 26.12.2014
 */
public class WebAutomatedTest {
	public static WebDriver driver =new FirefoxDriver();
	public static Selenium selenium;
	public static void main(String [] args)throws IOException, URISyntaxException, InterruptedException
	{
		connect_N11();
		String [] productList=new String[50];
		productList[0]="http://www.n11.com/lenovo-z510-i5-4200m-6gb-ram-1tb8gb-sshd-2gb-gt740-156-win81-P16135969?rf=one-cikanlar";
		productList[1]="http://www.n11.com/gonen-celik-9-lt-zenith-matik-duduklu-tencere-P24622798?rf=one-cikanlar";
		productList[2]="http://www.n11.com/pierre-cardin-16-jant-cougar-bisiklet-cocuk-bisikleti-P17721118";
		for(int i=0;i<productList.length;i++)
		{
			if(productList[i]!=null)
			{
			productView(productList[i]);
			Thread.sleep(5000);
			}
			else 
			{
				driver.quit();
				break; 
				}
		}
	}
	public static void productView(String product)
	{
		driver.get(product);
	}
	public static void connect_N11()
	{
		driver.get("https://www.n11.com/giris-yap");
		WebElement userName = driver.findElement(By.name("email"));
		userName.sendKeys("mstfbiccer@gmail.com");
		WebElement userPass = driver.findElement(By.name("password"));
		userPass.sendKeys("199414mb");
		WebElement loginbutton = driver.findElement(By.name("returnUrl"));
		loginbutton.submit();
	}
}
