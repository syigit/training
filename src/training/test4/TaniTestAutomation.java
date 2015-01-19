package training.test4;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
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
	private static org.apache.log4j.Logger log = Logger.getLogger(TaniTestAutomation.class);
	public static Scanner selectMenu;
	public static WebDriver driver = new FirefoxDriver();
	static String screeenshotDirectory="C:\\Users\\mustafa\\Desktop\\";
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
	 * @return 
	 * @throws Exception 
	 */
	public static WebElement searchElement(WebDriver driver,String cssText) throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, 30000);
		WebElement element;
		try
		{
			element = wait.until(ExpectedConditions
					.elementToBeClickable(By.cssSelector(cssText)));
			return element;
		}
		catch(Exception e)
		{
		log.error("searchElement-- object not found");
		throw new Exception("Bulunamadi");
		}
		
	}
	public static void yeniHedefListe(String companyName,String targetType,String targetSource) throws InterruptedException,
			IOException {
		try {
			log.info("newTargetList Creating");
			long unixTime = System.currentTimeMillis() / 1000L;
			// Yeni hedef liste oluştur bölümüne bağlanıyor
	     	driver.get("http://testsmsgateway.tani.com.tr:8080/campaign/targetList/new");
			WebElement listName=searchElement(driver,"#listName");
	     	// hedef kitle ismi veriliyor
		    listName.sendKeys("Hedef Kitle" + unixTime);
			WebElement selectCompany;
			selectCompany=searchElement(driver,"#s2id_branch>a>.select2-chosen");
			selectCompany.click();
			selectCompany.sendKeys(companyName);
			selectCompany.sendKeys(Keys.RETURN);// işyeri seçiliyor
			WebElement typeTarget=searchElement(driver,"#listType");
			typeTarget.click();
			typeTarget.sendKeys(targetType);
			typeTarget.sendKeys(Keys.RETURN);// hedef kitle tipi seçiliyor
			WebElement sourceTarget=searchElement(driver,"#targetListSource");
			sourceTarget.click();
			sourceTarget.sendKeys(targetSource);
			sourceTarget.sendKeys(Keys.RETURN);// hedef kitle kaynağı seçiliyor
			//-------dosya hedef kitlesi seçildiyse------------
			if(targetSource=="Dosya Hedef Kitlesi")
			{
		    WebElement sourceFile=driver.findElement(By.id("targetListFileLocal"));
			sourceFile.sendKeys("C:\\Users\\mustafa\\Desktop\\TANI-TARGETLIST\\TANI-TARGETLIST\\testEMAIL-just-me_email_columniack.txt");//hedef kitle dosyası yükleniyor
			WebElement viewFile=searchElement(driver,"#fileTargetListPreview");
			viewFile.click();// Dosya önizleme butonuna tıklanır
			// popup`ın açılması beklenir
			searchElement(driver,".modal-header");
			// popup`daki close butonu bulunur
			WebElement closeButton=searchElement(driver,".close");
			try {// butona tıklama işlemi olumsuz sonuçlanırsa popup açılmamış
					// demektir bu yüzden try-catch ile bu işlemler yapılır.
				closeButton.click();
				listName.submit();
				try {
					WebElement okButton=searchElement(driver,".btn.btn-primary");
					log.info("newTargetList Created");
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
			WebElement queryName=searchElement(driver,"#s2id_autogen2>a>.select2-chosen");
			//WebElement queryName=driver.findElement(By.cssSelector("#s2id_autogen2>a>.select2-chosen"));
			queryName.click();
			queryName.sendKeys("APP_TEST_DB");
			queryName.sendKeys(Keys.ENTER);
			WebElement queryText=searchElement(driver,"textarea.form-control.queryTargetList");
		    queryText.sendKeys("SELECT DWH_PARO_KOD,AD, SOYAD, EMAIL, To_Number(To_Char(DOGUM_TARIHI,'DDMM')) KRITER_DOGUM, To_Number(To_Char(SYSDATE+0,'DDMM')) KRITER_SYSDATE FROM SD_TUKETICI_VPI Where To_Number(To_Char(DOGUM_TARIHI,'DDMM')) <= To_Number(To_Char(SYSDATE+0,'DDMM')) and dwh_paro_kod>1000");
		    WebElement viewSql=searchElement(driver,"#queryTargetListPreview");
			viewSql.click();// sorgu önizleme butonuna tıklanır
			// popup`ın açılması beklenir
			WebElement viewPopup=searchElement(driver,".modal-header");
			// popup`daki close butonu bulunur
			WebElement closeButton=searchElement(driver,".close");
			try {// butona tıklama işlemi olumsuz sonuçlanırsa popup açılmamış
					// demektir bu yüzden try-catch ile bu işlemler yapılır.
				closeButton.click();
				listName.submit();
				try {
					WebDriverWait wait2 = new WebDriverWait(driver, 100000);
					WebElement okButton = wait2.until(ExpectedConditions
							.elementToBeClickable(By.className("btn-primary")));
					okButton.click();
					log.info("newTargetList Created");
				} catch (Exception e) {
					System.out.println("Kayıt Tamamlanamadı");
					log.error("Registry Error");
					driver.quit();
				}
			} catch (Exception e) {
				System.out.println("Popup gösterilemedi");
			}
			}
		} catch (Exception e) {
			System.out.println("Uygulama Sonlandırıldı.");
			log.error("Application Stopped");
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
	public static void KampanyaGonderimiEmail(String castType,String friend,String rate,String companyName,String speed) throws InterruptedException,
	IOException {
		try {
			log.info("SendCampaignEmail Started");
			 String alert1Text = null,alert2Text=null;
			long unixTime = System.currentTimeMillis() / 1000L;
			driver.get("http://testsmsgateway.tani.com.tr:8080/campaign/campaign/email/new");
			WebElement campaignName=searchElement(driver,"#campaignName");
			campaignName.sendKeys("Kampanya "+unixTime);
			WebElement sendSelect=searchElement(driver,"#emailCampaignCastType");
			sendSelect.click();
			sendSelect.sendKeys(castType);
			sendSelect.sendKeys(Keys.RETURN);// gönderim kurgusu seçildi✓
			//-----------------------------------------------------------------------
			if(castType=="Tekil Gönderim")
			{
			WebElement shareFriend=searchElement(driver,"#emailCampaignShareThisFlag");
			shareFriend.click();
			shareFriend.sendKeys(friend);
			shareFriend.sendKeys(Keys.RETURN);// arkadaşına gönder pasif/aktif✓
			}
			//-----------------------------------------------------------------------
			if(castType=="Tekil Gönderim")
			{
			WebElement rateFlag=searchElement(driver, "#emailCampaignRateFlag");
			rateFlag.click();
			rateFlag.sendKeys(rate);
			rateFlag.sendKeys(Keys.RETURN);// değerlendirme puanı pasif/aktif✓
			}
			//-----------------------------------------------------------------------
			WebElement mechanismText=searchElement(driver,"#mechanism");
			mechanismText.sendKeys("Test");//mekanizm metin alanına metin girişi yapıldı✓
			//-----------------------------------------------------------------------
			WebElement selectCompany=searchElement(driver,"#s2id_autogen1");
			selectCompany.click();
			selectCompany.sendKeys(companyName);
			selectCompany.sendKeys(Keys.RETURN);// işyeri seçiliyor✓
			//-----------------------------------------------------------------------
			WebElement subjectText=searchElement(driver,"#emailCampaignSubjects>div>div>.form-control");
			subjectText.sendKeys("Test Subject");//Subject girişi yapılıyor ✓
			//-------------------------------------------------------------------------
			if(castType=="Tekil Gönderim")
			{
			WebElement speedPost=searchElement(driver,"#emailCampaignDirectSendFlag");
			speedPost.click();
			speedPost.sendKeys(speed);
			speedPost.sendKeys(Keys.RETURN);//Hızlı gönder aktif/pasif seçiliyor✓
			}
			//-------------------------------------------------------------------------
			//targetlistseçimi
			WebElement targetSelect=searchElement(driver,"#s2id_emailCampaignTestTargetList>a>.select2-chosen");
			targetSelect.click();
			targetSelect.sendKeys(Keys.RETURN); //target list seçiliyor ✓
			//-------------------------------------------------------------------------
			driver.switchTo().frame("emailCampaignMessage_ifr");
			WebElement messageContent=searchElement(driver,"#tinymce");			
			messageContent.sendKeys("Mesaj içeriği"); //mesaj içeriği giriliyor✓
			//-------------------------------------------------------------------------
			driver.switchTo().defaultContent();
			WebElement titleSelect=searchElement(driver,"#s2id_emailCampaignHeader>a>.select2-chosen");
			titleSelect.click();
			titleSelect.sendKeys(Keys.RETURN); //başlık seçiliyor ✓
			//----------------------------------------------------------------------
			//Kampanya Kaydet Kontrolü//
			WebElement saveButton=searchElement(driver,".btn.btn-lg.blue.campaignSave");
			saveButton.click();
			boolean saveSuccess = true;
			try {
				boolean searchError=searchElement(driver,".preserve-newline") == null;
				while(searchError)
				{
				WebElement alert1=searchElement(driver,".preserve-newline.margin-bottom-5.alert.alert-success");
				 alert1Text=alert1.getText();
				   saveSuccess = true;
				   searchError=searchElement(driver,".preserve-newline")==null;
				}
				} catch (NoSuchElementException e) {
					saveSuccess = false;
				}
			if (saveSuccess==true)
			{
				 WebElement sendTest=driver.findElement(By.cssSelector(".btn.btn-lg.green.campaignSend"));
				 sendTest.click();
				WebElement alert2=searchElement(driver,".preserve-newline.margin-bottom-5.alert.alert-success");
				 alert2Text=alert2.getText();
				 if(alert1Text!=alert2Text)
				 {
					 System.out.println("Test Gönderimi Başarılı");
					 System.out.println("Gerçek Gönderim Yapılıyor...");
					 WebElement acceptSend=searchElement(driver,".campaignTestApprove.uniform");
					 acceptSend.click();
					 WebElement realTargetSelect=searchElement(driver,"#s2id_emailCampaignRealTargetList>a>.select2-chosen");
					realTargetSelect.click();
					 realTargetSelect.sendKeys(Keys.RETURN);
					 WebElement realSendButton=searchElement(driver,".btn.btn-lg.green.campaignSend");
					 realSendButton.click();
					String url="http://testsmsgateway.tani.com.tr:8080/campaign/campaign/search?message=Email%20Kampanyas%C4%B1%20ger%C3%A7ek%20g%C3%B6nderim%20yap%C4%B1ld%C4%B1...";
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
			log.error("Application Stopped");
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
	public static void KampanyaGonderimiSms(String castType,String companyName,String operatorName) throws InterruptedException,
	IOException {
		try {
			log.info("SendCampaignSms Started");
			 String alert1Text = null,alert2Text=null;
			long unixTime = System.currentTimeMillis() / 1000L;
			driver.get("http://testsmsgateway.tani.com.tr:8080/campaign/campaign/sms/new");
			WebElement campaignName=searchElement(driver,"#campaignName");
			campaignName.sendKeys("Kampanya "+unixTime);
			WebElement sendSelect=searchElement(driver,"#castType");
			sendSelect.click();
			sendSelect.sendKeys(castType);
			sendSelect.sendKeys(Keys.RETURN);// gönderim kurgusu seçildi✓
			//--------------Operatör Seçimi-----------------------
			WebElement operatorSelect=searchElement(driver,"#operator");
			operatorSelect.click();
			operatorSelect.sendKeys(operatorName);
			operatorSelect.sendKeys(Keys.RETURN);// gönderim kurgusu seçildi✓
			//----------------------------------------------------
			WebElement mechanismText=searchElement(driver,"#mechanism");
			mechanismText.sendKeys("Test");//mekanizm metin alanına metin girişi yapıldı✓
			WebElement selectCompany=searchElement(driver,"#s2id_autogen1");
			selectCompany.click();
			selectCompany.sendKeys(companyName);
			selectCompany.sendKeys(Keys.RETURN);// işyeri seçiliyor✓
			//-----------------------------------------------------------------------
			WebElement targetSelect=searchElement(driver,"#s2id_emailCampaignTestTargetList>a>.select2-chosen");
			targetSelect.click();
			targetSelect.sendKeys(Keys.RETURN); //target list seçiliyor ✓
			//-------------------------------------------------------------------------
			driver.switchTo().frame("emailCampaignMessage_ifr");
			WebElement messageContent=searchElement(driver,"#tinymce");
			messageContent.sendKeys("Mesaj içeriği"); //mesaj içeriği giriliyor✓
			//-------------------------------------------------------------------------
			driver.switchTo().defaultContent();
			WebElement titleSelect=searchElement(driver,"#s2id_emailCampaignHeader>a>.select2-chosen");
			titleSelect.click();
			titleSelect.sendKeys(Keys.RETURN); //başlık seçiliyor ✓
			//Kampanya Kaydet Kontrolü//
			WebElement saveButton=searchElement(driver,".btn.btn-lg.blue.campaignSave");
			saveButton.click();
			boolean saveSuccess = true;
			try {
				boolean searchError=searchElement(driver,".preserve-newline")==null;
				while(searchError)
				{
				WebElement alert1=searchElement(driver,".preserve-newline.margin-bottom-5.alert.alert-success");
				alert1Text=alert1.getText();
			    saveSuccess = true;
			    searchError=searchElement(driver,".preserve-newline")==null;
				}
				} catch (NoSuchElementException e) {
					saveSuccess = false;
				}
			if (saveSuccess==true)
			{
				 WebElement sendTest=searchElement(driver,".btn.btn-lg.green.campaignSend");
				 sendTest.click();
				 WebElement alert2=searchElement(driver,".preserve-newline.margin-bottom-5.alert.alert-success");
				 alert2Text=alert2.getText();
				 if(alert1Text!=alert2Text)
				 {
					 System.out.println("Test Gönderimi Başarılı");
					 System.out.println("Gerçek Gönderim Yapılıyor...");
					 WebElement acceptSend=searchElement(driver,".campaignTestApprove.uniform");
					 acceptSend.click();
					WebElement realTargetSelect=searchElement(driver,"#s2id_emailCampaignRealTargetList>a>.select2-chosen");
					 realTargetSelect.click();
					 realTargetSelect.sendKeys(Keys.RETURN);
					 WebElement realSendButton=searchElement(driver,".btn.btn-lg.green.campaignSend");
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
			KampanyaGonderimiEmail("Tekil Gönderim","Pasif","Aktif","EKSTRA PUAN","Aktif");
			break;
		case 4:
			connect_Tani();
			KampanyaGonderimiEmail("Çoğul Gönderim","Pasif","Aktif","EKSTRA PUAN","Aktif");
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
			KampanyaGonderimiSms("Çoğul Gönderim","EKSTRA PUAN","Vodafone");
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