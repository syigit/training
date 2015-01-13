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
	 * connect_Tani() Tani üzerinde account açmayý saðlýyor
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
	 * yeniHedefListe() yeni hedef liste oluþturmamýz için gereken test
	 * adýmlarýný yapan methodumuz
	 */
	public static void yeniHedefListe() throws InterruptedException,
			IOException {
		try {
			long unixTime = System.currentTimeMillis() / 1000L;
			driver.get("http://testsmsgateway.tani.com.tr:8080/campaign/targetList/new");// Yeni
																							// hedef
																							// liste
																							// oluþtur
																							// bölümüne
																							// baðlanýyor
			WebElement listName = driver.findElement(By.id("listName"));
			listName.sendKeys("Hedef Kitle" + unixTime);// hedef kitle ismi
														// veriliyor
			WebElement selectCompany = driver.findElement(By
					.className("select2-chosen"));
			selectCompany.click();
			selectCompany.sendKeys(Keys.RETURN);// iþyeri seçiliyor
			WebElement typeTarget = driver.findElement(By.id("listType"));
			typeTarget.click();
			typeTarget.sendKeys(Keys.ARROW_DOWN);
			typeTarget.sendKeys(Keys.RETURN);// hedef kitle tipi seçiliyor
			WebElement sourceTarget = driver.findElement(By
					.id("targetListSource"));
			sourceTarget.click();
			sourceTarget.sendKeys(Keys.ARROW_DOWN);
			sourceTarget.sendKeys(Keys.RETURN);// hedef kitle kaynaðý seçiliyor
			WebElement sourceFile = driver.findElement(By
					.id("targetListFileLocal"));
			sourceFile
					.sendKeys("C:\\Users\\mustafa\\Desktop\\TANI-TARGETLIST\\TANI-TARGETLIST\\testEMAIL-just-me_email_columniack.txt");
			WebElement viewFile = driver.findElement(By
					.id("fileTargetListPreview"));
			viewFile.click();// Dosya önizleme butonuna týklanýr
			Thread.sleep(5000);// popup`ýn açýlmasý beklenir
			WebElement viewPopup = driver.findElement(By
					.className("modal-header"));
			WebElement closeButton = driver.findElement(By.className("close"));// popup`daki
																				// close
																				// butonu
																				// bulunur
			try {// butona týklama iþlemi olumsuz sonuçlanýrsa popup açýlmamýþ
					// demektir bu yüzden try-catch ile bu iþlemler yapýlýr.
				closeButton.click();
				listName.submit();
				Thread.sleep(5000);
				try {
					WebElement okButton = driver.findElement(By
							.className("btn-primary"));
					okButton.click();

				} catch (Exception e) {
					System.out.println("Kayýt Tamamlanamadý");
					driver.quit();
				}

			}

			catch (Exception e) {
				System.out.println("Popup gösterilemedi");
			}
		} catch (Exception e) {
			System.out.println("Uygulama Sonlandýrýldý.");
			File scrFile = ((TakesScreenshot) driver)//hata durumunda ekran alýntýsý alýnýr
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(
					"C:\\Users\\mustafa\\Desktop\\YeniHedefListe.png"));//ekran alýntýsý bu konuma kaydedilir.
		} finally {
			driver.quit();
		}
	}
}
