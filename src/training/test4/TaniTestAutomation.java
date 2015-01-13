package training.test4;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.thoughtworks.selenium.Selenium;

public class TaniTestAutomation {
	public static WebDriver driver = new FirefoxDriver();
	public static Selenium selenium;

	public static void main(String[] args) throws IOException,
			URISyntaxException, InterruptedException {
		connect_Tani();
		yeniHedefListe();
	}

	/**
	 * connect_Tani() Tani �zerinde account a�may� sa�l�yor
	 */
	public static void connect_Tani() {
		driver.get("http://testsmsgateway.tani.com.tr:8080/campaign/account/login");
		WebElement userName = driver.findElement(By.name("j_username"));
		userName.sendKeys("demo");
		WebElement userPass = driver.findElement(By.name("j_password"));
		userPass.sendKeys("123456");
		WebElement loginBtn = driver.findElement(By.id("command"));
		loginBtn.submit();

	}

	/**
	 * yeniHedefListe() yeni hedef liste olu�turmam�z i�in gereken test
	 * ad�mlar�n� yapan methodumuz
	 */
	public static void yeniHedefListe() throws InterruptedException,
			IOException {
		try {
			long unixTime = System.currentTimeMillis() / 1000L;
			driver.get("http://testsmsgateway.tani.com.tr:8080/campaign/targetList/new");// Yeni
																							// hedef
																							// liste
																							// olu�tur
																							// b�l�m�ne
																							// ba�lan�yor
			WebElement listName = driver.findElement(By.id("listName"));
			listName.sendKeys("Hedef Kitle" + unixTime);// hedef kitle ismi
														// veriliyor
			WebElement selectCompany = driver.findElement(By
					.className("select2-chosen"));
			selectCompany.click();
			selectCompany.sendKeys(Keys.RETURN);// i�yeri se�iliyor
			WebElement typeTarget = driver.findElement(By.id("listType"));
			typeTarget.click();
			typeTarget.sendKeys(Keys.ARROW_DOWN);
			typeTarget.sendKeys(Keys.RETURN);// hedef kitle tipi se�iliyor
			WebElement sourceTarget = driver.findElement(By
					.id("targetListSource"));
			sourceTarget.click();
			sourceTarget.sendKeys(Keys.ARROW_DOWN);
			sourceTarget.sendKeys(Keys.RETURN);// hedef kitle kayna�� se�iliyor
			WebElement sourceFile = driver.findElement(By
					.id("targetListFileLocal"));
			sourceFile
					.sendKeys("C:\\Users\\mustafa\\Desktop\\TANI-TARGETLIST\\TANI-TARGETLIST\\testEMAIL-just-me_email_columniack.txt");
			WebElement viewFile = driver.findElement(By
					.id("fileTargetListPreview"));
			viewFile.click();// Dosya �nizleme butonuna t�klan�r
			Thread.sleep(5000);// popup`�n a��lmas� beklenir
			WebElement viewPopup = driver.findElement(By
					.className("modal-header"));
			WebElement closeButton = driver.findElement(By.className("close"));// popup`daki
																				// close
																				// butonu
																				// bulunur
			try {// butona t�klama i�lemi olumsuz sonu�lan�rsa popup a��lmam��
					// demektir bu y�zden try-catch ile bu i�lemler yap�l�r.
				closeButton.click();
				listName.submit();
				Thread.sleep(5000);
				try {
					WebElement okButton = driver.findElement(By
							.className("btn-primary"));
					okButton.click();

				} catch (Exception e) {
					System.out.println("Kay�t Tamamlanamad�");
					driver.quit();
				}

			}

			catch (Exception e) {
				System.out.println("Popup g�sterilemedi");
			}
		} catch (Exception e) {
			System.out.println("Uygulama Sonland�r�ld�.");
			File scrFile = ((TakesScreenshot) driver)//hata durumunda ekran al�nt�s� al�n�r
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(
					"C:\\Users\\mustafa\\Desktop\\YeniHedefListe.png"));//ekran al�nt�s� bu konuma kaydedilir.
		} finally {
			driver.quit();
		}
	}
}
