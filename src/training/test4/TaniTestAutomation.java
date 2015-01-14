package training.test4;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * @author MustafaBICER
 */
public class TaniTestAutomation {
	public static Scanner selectMenu;
	public static WebDriver driver = new FirefoxDriver();
	//public static Selenium selenium;
	public static void main(String[] args) throws IOException,
			URISyntaxException, InterruptedException {
		long baslangic = System.currentTimeMillis();
		menu();
		long bitis = System.currentTimeMillis();
		long gecenSure = bitis - baslangic;
		System.out.println("Test Ba�ar�yla Tamamland�\n");
		System.out.println("��lem S�resi: " + gecenSure);
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
	public static void yeniHedefListe(String companyName,String targetType,String targetSource) throws InterruptedException,
			IOException {
		try {
			long unixTime = System.currentTimeMillis() / 1000L;
			// Yeni hedef liste olu�tur b�l�m�ne ba�lan�yor
			driver.get("http://testsmsgateway.tani.com.tr:8080/campaign/targetList/new");
			WebElement listName = driver.findElement(By.id("listName"));
			// hedef kitle ismi veriliyor
			listName.sendKeys("Hedef Kitle" + unixTime);
			WebElement selectCompany = driver.findElement(By
					.className("select2-chosen"));
			selectCompany.click();
			selectCompany.sendKeys(companyName);
			selectCompany.sendKeys(Keys.RETURN);// i�yeri se�iliyor
			WebElement typeTarget = driver.findElement(By.id("listType"));
			typeTarget.click();
			typeTarget.sendKeys(targetType);
			typeTarget.sendKeys(Keys.RETURN);// hedef kitle tipi se�iliyor
			WebElement sourceTarget = driver.findElement(By
					.id("targetListSource"));
			sourceTarget.click();
			sourceTarget.sendKeys(targetSource);
			sourceTarget.sendKeys(Keys.RETURN);// hedef kitle kayna�� se�iliyor
			WebElement sourceFile = driver.findElement(By
					.id("targetListFileLocal"));
			sourceFile
					.sendKeys("C:\\Users\\mustafa\\Desktop\\TANI-TARGETLIST\\TANI-TARGETLIST\\testEMAIL-just-me_email_columniack.txt");
			WebElement viewFile = driver.findElement(By
					.id("fileTargetListPreview"));
			viewFile.click();// Dosya �nizleme butonuna t�klan�r
			WebDriverWait wait = new WebDriverWait(driver, 100000);
			// popup`�n a��lmas� beklenir
			WebElement viewPopup = wait.until(ExpectedConditions
					.elementToBeClickable(By.className("modal-header")));
			// popup`daki close butonu bulunur
			WebElement closeButton = viewPopup.findElement(By
					.className("close"));
			try {// butona t�klama i�lemi olumsuz sonu�lan�rsa popup a��lmam��
					// demektir bu y�zden try-catch ile bu i�lemler yap�l�r.
				closeButton.click();
				listName.submit();
				try {
					WebDriverWait wait2 = new WebDriverWait(driver, 100000);
					WebElement okButton = wait2.until(ExpectedConditions
							.elementToBeClickable(By.className("btn-primary")));
					okButton.click();
				} catch (Exception e) {
					System.out.println("Kay�t Tamamlanamad�");
					driver.quit();
				}
			} catch (Exception e) {
				System.out.println("Popup g�sterilemedi");
			}
		} catch (Exception e) {
			System.out.println("Uygulama Sonland�r�ld�.");
			System.out.println(e.getMessage());
			// hata durumunda ekran al�nt�s� al�n�r
			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			// ekran al�nt�s� bu konuma kaydedilir.
			FileUtils.copyFile(scrFile, new File(
					"C:\\Users\\mustafa\\Desktop\\YeniHedefListe.png"));
		} finally {
			driver.quit();
		}
	}

	public static void testKampanyaTan�mlamaEmailTekil(String castType,String friend,String rate,String companyName,String speed) throws InterruptedException,
	IOException {
		try {
			long unixTime = System.currentTimeMillis() / 1000L;
			driver.get("http://testsmsgateway.tani.com.tr:8080/campaign/campaign/email/new");
			WebElement campaignName=driver.findElement(By.id("campaignName"));
			campaignName.sendKeys("Kampanya "+unixTime);
			WebElement sendSelect = driver.findElement(By
					.id("emailCampaignCastType"));
			sendSelect.click();
			sendSelect.sendKeys(castType);
			sendSelect.sendKeys(Keys.RETURN);// g�nderim kurgusu se�ildi
			WebElement shareFriend = driver.findElement(By
					.id("emailCampaignShareThisFlag"));
			shareFriend.click();
			shareFriend.sendKeys(friend);
			shareFriend.sendKeys(Keys.RETURN);// arkada��na g�nder pasif/aktif
			WebElement rateFlag = driver.findElement(By
					.id("emailCampaignRateFlag"));
			rateFlag.click();
			rateFlag.sendKeys(rate);
			rateFlag.sendKeys(Keys.RETURN);// de�erlendirme puan� pasif/aktif
			WebElement mechanismText=driver.findElement(By.id("mechanism"));//mekanizm metin alan� bulundu
			mechanismText.sendKeys("Test");//mekanizm metin alan�na metin giri�i yap�ld�
			WebElement selectCompany = driver.findElement(By
					.className("select2-input"));
			selectCompany.click();
			selectCompany.sendKeys(companyName);
			selectCompany.sendKeys(Keys.RETURN);// i�yeri se�iliyor
		    WebDriverWait wait = new WebDriverWait(driver, 10000);
			WebElement selectTitleDiv = wait.until(ExpectedConditions.elementToBeClickable(By.id("s2id_emailCampaignHeader")));
			WebElement selectTitle=selectTitleDiv.findElement(By.className("select2-chosen"));
			selectTitle.click();
			selectTitle.sendKeys(Keys.RETURN);// ba�l�k se�iliyor
			WebDriverWait wait2 = new WebDriverWait(driver, 10000);
			WebElement subjectText = wait2.until(ExpectedConditions
					.elementToBeClickable(By.name("subjects")));
			WebElement speedPost=driver.findElement(By.id("emailCampaignDirectSendFlag"));
			speedPost.click();
			speedPost.sendKeys(speed);
			speedPost.sendKeys(Keys.RETURN);
			mechanismText.sendKeys(Keys.TAB);
			subjectText.sendKeys("Test Subject");
			 WebDriverWait wait4 = new WebDriverWait(driver, 10000);
			WebElement selectTargetListDiv=wait4.until(ExpectedConditions.elementToBeClickable(By.id("s2id_emailCampaignTestTargetList")));
			WebElement selectTargetList=selectTargetListDiv.findElement(By.className("select2-chosen"));
			selectTargetList.click();
			driver.switchTo().frame("emailCampaignMessage_ifr");
			WebDriverWait wait3 = new WebDriverWait(driver, 10000);
			WebElement messageContent = wait3.until(ExpectedConditions
					.elementToBeClickable(By.className("tinymce")));
			messageContent.sendKeys("Mesaj i�eri�i");		
			Thread.sleep(60000);
			} catch (Exception e) {
			System.out.println("Uygulama Sonland�r�ld�.");
			System.out.println(e.getMessage());
			// hata durumunda ekran al�nt�s� al�n�r
			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			// ekran al�nt�s� bu konuma kaydedilir.
			FileUtils.copyFile(scrFile, new File(
					"C:\\Users\\mustafa\\Desktop\\Kampanya.png"));
		} finally {
		
		}
}
	public static void menu() throws InterruptedException, IOException
	{
		try
		{
		System.out.println("------------L�tfen Test Ad�m�n� Se�iniz--------------");
		System.out.println("(1) Test Hedef kitlesi olu�turmak");
		System.out.println("(2) Test Hedef kitlesi kampanya tan�mlama (tekil g�nderim)");
		selectMenu=new Scanner(System.in);
		int selectedMenu=Integer.parseInt(selectMenu.nextLine());
		switch(selectedMenu)
		{
		case 1:
			connect_Tani();
			yeniHedefListe("EKSTRA PUAN","Ger�ek G�nderim","Dosya Hedef Kitlesi");
			break;
		case 2:
			connect_Tani();
			//testKampanyaTan�mlamaEmailTekil("Tekil G�nderim","Pasif","Aktif","EKSTRA PUAN","Aktif");
			break;
		}
	} catch (Exception e) {
		System.out.println("Uygulama Sonland�r�ld�.");
		System.out.println(e.getMessage());
		// hata durumunda ekran al�nt�s� al�n�r
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		// ekran al�nt�s� bu konuma kaydedilir.
		FileUtils.copyFile(scrFile, new File(
				"C:\\Users\\mustafa\\Desktop\\menu.png"));
	} finally {
		driver.quit();
	}
	}
}
