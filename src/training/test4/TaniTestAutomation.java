package training.test4;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
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
	static String screeenshotDirectory="C:\\Users\\mustafa\\Desktop\\";
	//public static Selenium selenium;
	public static void main(String[] args) throws IOException,
			URISyntaxException, InterruptedException {
		long baslangic = System.currentTimeMillis();
		menu();
		long bitis = System.currentTimeMillis();
		long gecenSure = bitis - baslangic;
		System.out.println("Test Başarıyla Tamamlandı\n");
		System.out.println("İşlem Süresi: " + gecenSure);
	}
	/**
	 * connect_Tani() Tani üzerinde account açmayı sağlıyor
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
	 * yeniHedefListe() yeni hedef liste oluşturmamız için gereken test
	 * adımlarını yapan methodumuz
	 */
	public static void yeniHedefListe(String companyName,String targetType,String targetSource) throws InterruptedException,
			IOException {
		try {
			long unixTime = System.currentTimeMillis() / 1000L;
			// Yeni hedef liste oluştur bölümüne bağlanıyor
			driver.get("http://testsmsgateway.tani.com.tr:8080/campaign/targetList/new");
			WebElement listName = driver.findElement(By.id("listName"));
			// hedef kitle ismi veriliyor
			listName.sendKeys("Hedef Kitle" + unixTime);
			WebElement selectCompany = driver.findElement(By
					.className("select2-chosen"));
			selectCompany.click();
			selectCompany.sendKeys(companyName);
			selectCompany.sendKeys(Keys.RETURN);// işyeri seçiliyor
			WebElement typeTarget = driver.findElement(By.id("listType"));
			typeTarget.click();
			typeTarget.sendKeys(targetType);
			typeTarget.sendKeys(Keys.RETURN);// hedef kitle tipi seçiliyor
			WebElement sourceTarget = driver.findElement(By
					.id("targetListSource"));
			sourceTarget.click();
			sourceTarget.sendKeys(targetSource);
			sourceTarget.sendKeys(Keys.RETURN);// hedef kitle kaynağı seçiliyor
			//-------dosya hedef kitlesi seçildiyse------------
			if(targetSource=="Dosya Hedef Kitlesi")
			{
			WebElement sourceFile = driver.findElement(By
					.id("targetListFileLocal"));
			sourceFile
					.sendKeys("C:\\Users\\mustafa\\Desktop\\TANI-TARGETLIST\\TANI-TARGETLIST\\testEMAIL-just-me_email_columniack.txt");//hedef kitle dosyası yükleniyor
		
			WebElement viewFile = driver.findElement(By
					.id("fileTargetListPreview"));
			viewFile.click();// Dosya önizleme butonuna tıklanır
			WebDriverWait wait = new WebDriverWait(driver, 100000);
			// popup`ın açılması beklenir
			WebElement viewPopup = wait.until(ExpectedConditions
					.elementToBeClickable(By.className("modal-header")));
			// popup`daki close butonu bulunur
			WebElement closeButton = viewPopup.findElement(By
					.className("close"));
			try {// butona tıklama işlemi olumsuz sonuçlanırsa popup açılmamış
					// demektir bu yüzden try-catch ile bu işlemler yapılır.
				closeButton.click();
				listName.submit();
				try {
					WebDriverWait wait2 = new WebDriverWait(driver, 100000);
					WebElement okButton = wait2.until(ExpectedConditions
							.elementToBeClickable(By.className("btn-primary")));
					okButton.click();
				} catch (Exception e) {
					System.out.println("Kayıt Tamamlanamadı");
					driver.quit();
				}
			} catch (Exception e) {
				System.out.println("Popup gösterilemedi");
			}
		}
			//-------Sorgu Hedef Kitlesi Seçildiyse---------------
			else
			{
			WebElement queryName=driver.findElement(By.cssSelector("#s2id_autogen2>a>.select2-chosen"));
			queryName.click();
			queryName.sendKeys("APP_TEST_DB");
			queryName.sendKeys(Keys.ENTER);
			WebElement queryText=driver.findElement(By.name("sqlQuery"));
		    queryText.sendKeys("SELECT DWH_PARO_KOD,AD, SOYAD, EMAIL, To_Number(To_Char(DOGUM_TARIHI,'DDMM')) KRITER_DOGUM, To_Number(To_Char(SYSDATE+0,'DDMM')) KRITER_SYSDATE FROM SD_TUKETICI_VPI Where To_Number(To_Char(DOGUM_TARIHI,'DDMM')) <= To_Number(To_Char(SYSDATE+0,'DDMM')) and dwh_paro_kod>1000");
		    WebElement viewSql = driver.findElement(By
					.cssSelector("#queryTargetListPreview"));
			viewSql.click();// sorgu önizleme butonuna tıklanır
		    WebDriverWait wait = new WebDriverWait(driver, 100000);
			// popup`ın açılması beklenir
			WebElement viewPopup = wait.until(ExpectedConditions
					.elementToBeClickable(By.className("modal-header")));
			// popup`daki close butonu bulunur
			WebElement closeButton = viewPopup.findElement(By
					.className("close"));
			try {// butona tıklama işlemi olumsuz sonuçlanırsa popup açılmamış
					// demektir bu yüzden try-catch ile bu işlemler yapılır.
				closeButton.click();
				listName.submit();
				try {
					WebDriverWait wait2 = new WebDriverWait(driver, 100000);
					WebElement okButton = wait2.until(ExpectedConditions
							.elementToBeClickable(By.className("btn-primary")));
					okButton.click();
				} catch (Exception e) {
					System.out.println("Kayıt Tamamlanamadı");
					driver.quit();
				}
			} catch (Exception e) {
				System.out.println("Popup gösterilemedi");
			}
			}
		} catch (Exception e) {
			System.out.println("Uygulama Sonlandırıldı.");
			System.out.println(e.getMessage());
			// hata durumunda ekran alıntısı alınır
			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			// ekran alıntısı bu konuma kaydedilir.
			FileUtils.copyFile(scrFile, new File(screeenshotDirectory+"YeniHedefListe.png"));
		} finally {
			driver.quit();
		}
	}

	public static void testKampanyaGonderimiEmail(String castType,String friend,String rate,String companyName,String speed) throws InterruptedException,
	IOException {
		try {
			 String alert1Text = null,alert2Text=null;
			long unixTime = System.currentTimeMillis() / 1000L;
			driver.get("http://testsmsgateway.tani.com.tr:8080/campaign/campaign/email/new");
			WebElement campaignName=driver.findElement(By.id("campaignName"));
			campaignName.sendKeys("Kampanya "+unixTime);
			WebElement sendSelect = driver.findElement(By
					.id("emailCampaignCastType"));
			sendSelect.click();
			sendSelect.sendKeys(castType);
			sendSelect.sendKeys(Keys.RETURN);// gönderim kurgusu seçildi✓
			//-----------------------------------------------------------------------
			if(castType=="Tekil Gönderim")
			{
			WebElement shareFriend = driver.findElement(By
					.id("emailCampaignShareThisFlag"));
			shareFriend.click();
			shareFriend.sendKeys(friend);
			shareFriend.sendKeys(Keys.RETURN);// arkadaşına gönder pasif/aktif✓
			}
			//-----------------------------------------------------------------------
			if(castType=="Tekil Gönderim")
			{
			WebElement rateFlag = driver.findElement(By
					.id("emailCampaignRateFlag"));
			rateFlag.click();
			rateFlag.sendKeys(rate);
			rateFlag.sendKeys(Keys.RETURN);// değerlendirme puanı pasif/aktif✓
			}
			//-----------------------------------------------------------------------
			WebElement mechanismText=driver.findElement(By.id("mechanism"));//mekanizm metin alanı bulundu
			mechanismText.sendKeys("Test");//mekanizm metin alanına metin girişi yapıldı✓
			//-----------------------------------------------------------------------
			WebElement selectCompany = driver.findElement(By
					.className("select2-input"));
			selectCompany.click();
			selectCompany.sendKeys(companyName);
			selectCompany.sendKeys(Keys.RETURN);// işyeri seçiliyor✓
			//-----------------------------------------------------------------------
			WebDriverWait wait2 = new WebDriverWait(driver, 10000);
			WebElement subjectText = wait2.until(ExpectedConditions
					.elementToBeClickable(By.name("subjects")));
			subjectText.sendKeys("Test Subject");//Subject girişi yapılıyor ✓
			//-------------------------------------------------------------------------
			if(castType=="Tekil Gönderim")
			{
			WebElement speedPost=driver.findElement(By.id("emailCampaignDirectSendFlag"));
			speedPost.click();
			speedPost.sendKeys(speed);
			speedPost.sendKeys(Keys.RETURN);//Hızlı gönder aktif/pasif seçiliyor✓
			}
			//-------------------------------------------------------------------------
			//targetlistseçimi
			WebDriverWait waitSelect = new WebDriverWait(driver, 10000);
			WebElement targetSelect = waitSelect.until(ExpectedConditions
					.elementToBeClickable(By.cssSelector("#s2id_emailCampaignTestTargetList>a>.select2-chosen")));
			targetSelect.click();
			targetSelect.sendKeys(Keys.RETURN); //target list seçiliyor ✓
			//-------------------------------------------------------------------------
			driver.switchTo().frame("emailCampaignMessage_ifr");
			WebDriverWait wait3 = new WebDriverWait(driver, 10000);
			WebElement messageContent = wait3.until(ExpectedConditions
					.elementToBeClickable(By.id("tinymce")));
			messageContent.sendKeys("Mesaj içeriği"); //mesaj içeriği giriliyor✓
			//-------------------------------------------------------------------------
			driver.switchTo().defaultContent();
			WebElement titleSelect=driver.findElement(By.cssSelector("#s2id_emailCampaignHeader>a>.select2-chosen"));
			titleSelect.click();
			titleSelect.sendKeys(Keys.RETURN); //başlık seçiliyor ✓
			//----------------------------------------------------------------------
			//Kampanya Kaydet Kontrolü//
			WebElement saveButton=driver.findElement(By.cssSelector(".btn.btn-lg.blue.campaignSave"));
			saveButton.click();
			boolean saveSuccess = true;
			try {
				boolean searchError=driver.findElement(By.cssSelector(".preserve-newline")) == null;
				while(searchError)
				{
				 WebDriverWait wait=new WebDriverWait(driver,100);
				 WebElement alert1 = wait.until(ExpectedConditions
							.elementToBeClickable(By.cssSelector(".preserve-newline.margin-bottom-5.alert.alert-success")));
				 alert1Text=alert1.getText();
				   saveSuccess = true;
				   searchError=driver.findElement(By.cssSelector(".preserve-newline")) == null;
				}
				} catch (NoSuchElementException e) {
					saveSuccess = false;
				}
			if (saveSuccess==true)
			{
				 WebElement sendTest=driver.findElement(By.cssSelector(".btn.btn-lg.green.campaignSend"));
				 sendTest.click();
				 WebDriverWait wait=new WebDriverWait(driver,100);
				 WebElement alert2 = wait.until(ExpectedConditions
							.elementToBeClickable(By.cssSelector(".preserve-newline.margin-bottom-5.alert.alert-success")));
				 alert2Text=alert2.getText();
				 if(alert1Text!=alert2Text)
				 {
					 System.out.println("Test Gönderimi Başarılı");
					 System.out.println("Gerçek Gönderim Yapılıyor...");
					 WebElement acceptSend=driver.findElement(By.cssSelector(".campaignTestApprove.uniform"));
					 acceptSend.click();
				  WebDriverWait waitRealTarget=new WebDriverWait(driver,10);
					WebElement realTargetSelect=waitRealTarget.until(ExpectedConditions
								.elementToBeClickable(By.cssSelector("#s2id_emailCampaignRealTargetList>a>.select2-chosen")));
					 realTargetSelect.click();
					 realTargetSelect.sendKeys(Keys.RETURN);
					 WebElement realSendButton=driver.findElement(By.cssSelector(".btn.btn-lg.green.campaignSend"));
					 realSendButton.click();
					//String url="http://testsmsgateway.tani.com.tr:8080/campaign/campaign/search?message=Email%20Kampanyas%C4%B1%20ger%C3%A7ek%20g%C3%B6nderim%20yap%C4%B1ld%C4%B1...";
					String url="sacma";
					 int count = 0;
					while(count<60)
					{
					if(url.contains(driver.getCurrentUrl().toString()))
					{
						 System.out.println("Gerçek Gönderim Başarıyla Tamamlandı");
						 break;
					}
					else
					{
						Thread.sleep(1000);
						count++;
					}
					if(count>=60) System.out.println("Gerçek Gönderim Yapilamadi");
					}
				 }
				 else
					 System.out.println("Gönderim Başarısız");
			}
			else
				System.out.println("Kayıt Sırasında Hata Oluştu.");
			Thread.sleep(60000);
			} catch (Exception e) {
			System.out.println("Uygulama Sonlandırıldı.");
			System.out.println(e.getMessage());
			// hata durumunda ekran alıntısı alınır
			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			// ekran alıntısı bu konuma kaydedilir.
			FileUtils.copyFile(scrFile, new File(
					screeenshotDirectory+"Kampanya.png"));
		} finally {
			driver.quit();
		}
}
	public static void testKampanyaGonderimiSms(String castType,String companyName,String operatorName) throws InterruptedException,
	IOException {
		try {
			 String alert1Text = null,alert2Text=null;
			long unixTime = System.currentTimeMillis() / 1000L;
			driver.get("http://testsmsgateway.tani.com.tr:8080/campaign/campaign/sms/new");
			WebElement campaignName=driver.findElement(By.cssSelector("#campaignName"));
			campaignName.sendKeys("Kampanya "+unixTime);
			WebElement sendSelect = driver.findElement(By
					.cssSelector("#castType"));
			sendSelect.click();
			sendSelect.sendKeys(castType);
			sendSelect.sendKeys(Keys.RETURN);// gönderim kurgusu seçildi✓
			//--------------Operatör Seçimi-----------------------
			WebElement operatorSelect = driver.findElement(By
					.cssSelector("#operator"));
			operatorSelect.click();
			operatorSelect.sendKeys(operatorName);
			operatorSelect.sendKeys(Keys.RETURN);// gönderim kurgusu seçildi✓
			//----------------------------------------------------
			//-----------------------------------------------------------------------
			WebElement mechanismText=driver.findElement(By.id("mechanism"));//mekanizm metin alanı bulundu
			mechanismText.sendKeys("Test");//mekanizm metin alanına metin girişi yapıldı✓
			//-----------------------------------------------------------------------
			WebElement selectCompany = driver.findElement(By
					.className("select2-input"));
			selectCompany.click();
			selectCompany.sendKeys(companyName);
			selectCompany.sendKeys(Keys.RETURN);// işyeri seçiliyor✓
			//-----------------------------------------------------------------------
			//targetlistseçimi
			WebDriverWait waitSelect = new WebDriverWait(driver, 10000);
			WebElement targetSelect = waitSelect.until(ExpectedConditions
					.elementToBeClickable(By.cssSelector("#s2id_emailCampaignTestTargetList>a>.select2-chosen")));
			targetSelect.click();
			targetSelect.sendKeys(Keys.RETURN); //target list seçiliyor ✓
			//-------------------------------------------------------------------------
			driver.switchTo().frame("emailCampaignMessage_ifr");
			WebDriverWait wait3 = new WebDriverWait(driver, 10000);
			WebElement messageContent = wait3.until(ExpectedConditions
					.elementToBeClickable(By.id("tinymce")));
			messageContent.sendKeys("Mesaj içeriği"); //mesaj içeriği giriliyor✓
			//-------------------------------------------------------------------------
			driver.switchTo().defaultContent();
			WebElement titleSelect=driver.findElement(By.cssSelector("#s2id_emailCampaignHeader>a>.select2-chosen"));
			titleSelect.click();
			titleSelect.sendKeys(Keys.RETURN); //başlık seçiliyor ✓
			//----------------------------------------------------------------------
			//Kampanya Kaydet Kontrolü//
			WebElement saveButton=driver.findElement(By.cssSelector(".btn.btn-lg.blue.campaignSave"));
			saveButton.click();
			boolean saveSuccess = true;
			try {
				boolean searchError=driver.findElement(By.cssSelector(".preserve-newline")) == null;
				while(searchError)
				{
				 WebDriverWait wait=new WebDriverWait(driver,100);
				 WebElement alert1 = wait.until(ExpectedConditions
							.elementToBeClickable(By.cssSelector(".preserve-newline.margin-bottom-5.alert.alert-success")));
				 alert1Text=alert1.getText();
				   saveSuccess = true;
				   searchError=driver.findElement(By.cssSelector(".preserve-newline")) == null;
				}
				} catch (NoSuchElementException e) {
					saveSuccess = false;
				}
			if (saveSuccess==true)
			{
				 WebElement sendTest=driver.findElement(By.cssSelector(".btn.btn-lg.green.campaignSend"));
				 sendTest.click();
				 WebDriverWait wait=new WebDriverWait(driver,100);
				 WebElement alert2 = wait.until(ExpectedConditions
							.elementToBeClickable(By.cssSelector(".preserve-newline.margin-bottom-5.alert.alert-success")));
				 alert2Text=alert2.getText();
				 if(alert1Text!=alert2Text)
				 {
					 System.out.println("Test Gönderimi Başarılı");
					 System.out.println("Gerçek Gönderim Yapılıyor...");
					 WebElement acceptSend=driver.findElement(By.cssSelector(".campaignTestApprove.uniform"));
					 acceptSend.click();
				  WebDriverWait waitRealTarget=new WebDriverWait(driver,10);
					WebElement realTargetSelect=waitRealTarget.until(ExpectedConditions
								.elementToBeClickable(By.cssSelector("#s2id_emailCampaignRealTargetList>a>.select2-chosen")));
					 realTargetSelect.click();
					 realTargetSelect.sendKeys(Keys.RETURN);
					 WebElement realSendButton=driver.findElement(By.cssSelector(".btn.btn-lg.green.campaignSend"));
					 realSendButton.click();
					String url="http://testsmsgateway.tani.com.tr:8080/campaign/campaign/search?message=SMS%20Kampanyas%C4%B1%20ger%C3%A7ek%20g%C3%B6nderim%20yap%C4%B1ld%C4%B1...";
					 int count = 0;
					while(count<60)
					{
					if(url.contains(driver.getCurrentUrl().toString()))
					{
						 System.out.println("Gerçek Gönderim Başarıyla Tamamlandı");
						 break;
					}
					else
					{
						Thread.sleep(1000);
						count++;
					}
					if(count>=60) System.out.println("Gerçek Gönderim Yapilamadi");
					}
				 }
				 else
					 System.out.println("Gönderim Başarısız");
			}
			else
				System.out.println("Kayıt Sırasında Hata Oluştu.");
			Thread.sleep(60000);
			} catch (Exception e) {
			System.out.println("Uygulama Sonlandırıldı.");
			System.out.println(e.getMessage());
			// hata durumunda ekran alıntısı alınır
			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			// ekran alıntısı bu konuma kaydedilir.
			FileUtils.copyFile(scrFile, new File(
					screeenshotDirectory+"Kampanya.png"));
		} finally {
			driver.quit();
		}
}
	public static void menu() throws InterruptedException, IOException
	{
		try
		{
		System.out.println("------------Lütfen Test Adımını Seçiniz--------------");
		System.out.println("(1) Test Hedef kitlesi oluşturmak/Dosya Hedef Kitlesi");
		System.out.println("(2) Gerçek Hedef kitlesi oluşturmak/Dosya Hedef Kitlesi");
		System.out.println("(3) Kampanya Tanımlama Email Test Gönderim ve Gerçek Gönderim (Tekli Gönderim)");
		System.out.println("(4) Kampanya Tanımlama Test Gönderim ve Gerçek Gönderim (Çoklu Gönderim)");
		System.out.println("(5) Test Hedef kitlesi oluşturmak/Sorgu Hedef Kitlesi");
		System.out.println("(6) Gerçek Hedef kitlesi oluşturmak/Sorgu Hedef Kitlesi");
		selectMenu=new Scanner(System.in);
		int selectedMenu=Integer.parseInt(selectMenu.nextLine());
		switch(selectedMenu)
		{
		case 1:
			connect_Tani();
			yeniHedefListe("EKSTRA PUAN","Test Gönderim","Dosya Hedef Kitlesi");
			break;
		case 2:
			connect_Tani();
			yeniHedefListe("EKSTRA PUAN","Gerçek Gönderim","Dosya Hedef Kitlesi");
			break;
		case 3:
			connect_Tani();
			testKampanyaGonderimiEmail("Tekil Gönderim","Pasif","Aktif","EKSTRA PUAN","Aktif");
			break;
		case 4:
			connect_Tani();
			testKampanyaGonderimiEmail("Çoğul Gönderim","Pasif","Aktif","EKSTRA PUAN","Aktif");
			break;
		case 5:
			connect_Tani();
			yeniHedefListe("EKSTRA PUAN","Test Gönderim","Sorgu Hedef Kitlesi");
			break;
		case 6:
			connect_Tani();
			yeniHedefListe("EKSTRA PUAN","Gerçek Gönderim","Sorgu Hedef Kitlesi");
			break;
		case 7:
			connect_Tani();
			testKampanyaGonderimiSms("Çoğul Gönderim","EKSTRA PUAN","Vodafone");
			break;
		}
	} catch (Exception e) {
		System.out.println("Uygulama Sonlandırıldı.");
		System.out.println(e.getMessage());
		// hata durumunda ekran alıntısı alınır
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		// ekran alıntısı bu konuma kaydedilir.
		FileUtils.copyFile(scrFile, new File(
				screeenshotDirectory+"menu.png"));
	} finally {
		driver.quit();
	}
	}
}
