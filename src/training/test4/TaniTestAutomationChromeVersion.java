package training.test4;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
//import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * @author MustafaBICER
 */
public class TaniTestAutomationChromeVersion {
	private static org.apache.log4j.Logger log = Logger
			.getLogger(TaniTestAutomationChromeVersion.class);
	public static Scanner selectMenu;
	public static WebDriver driver;
	static String failedSteps = " ";
	static String screeenshotDirectory = "C:\\Users\\mustafa\\Desktop\\";
	public static void main(String[] args) throws IOException,
			URISyntaxException, InterruptedException {
		try {
			System.setProperty("webdriver.chrome.driver", "C:/Users/mustafa/Desktop/SeleniumWebDrivers/chromedriver_windows.exe");
			driver=new ChromeDriver();
			// driver.manage().window().setPosition(new Point(-2000, 0));
			// Browser`ı ekran dışında tutma
			long baslangic = System.currentTimeMillis();
			connect_Tani();
			yeniHedefListe("EKSTRA PUAN", "Test Gönderim",
					"Dosya Hedef Kitlesi");
			yeniHedefListe("EKSTRA PUAN", "Gerçek Gönderim",
					"Dosya Hedef Kitlesi");
			KampanyaGonderimiEmail("Tekil Gönderim", "Pasif", "Aktif",
					"EKSTRA PUAN", "Aktif");
			KampanyaGonderimiEmail("Çoğul Gönderim", "Pasif", "Aktif",
					"EKSTRA PUAN", "Aktif");
			KampanyaGonderimiSms("Çoğul Gönderim", "EKSTRA PUAN", "Vodafone");
			KampanyaGonderimiSms("Tekil Gönderim", "EKSTRA PUAN", "Vodafone");
			long bitis = System.currentTimeMillis();
			long gecenSure = bitis - baslangic;
			if (failedSteps != " ") {
				log.info("Failed Steps: " + failedSteps);
			}
			log.info("Test Succeed " + "Elapsed Time: " + gecenSure / 1000
					+ " Seconds");
			// yeniHedefListe("EKSTRA PUAN","Gerçek Gönderim","Sorgu Hedef Kitlesi");
			// KampanyaGonderimiSms("Çoğul Gönderim","EKSTRA PUAN","Vodafone");
		} catch (Exception e) {

			System.out.println(e.getMessage());
			screenShot("Main");
			log.error("Application Stoped");
		} finally {
			driver.quit();
		}
	}

	/**
	 * yeniHedefListe() yeni hedef liste oluşturmamız için gereken test
	 * adımlarını yapan methodumuz
	 * 
	 * @return
	 * @throws Exception
	 */
	public static void yeniHedefListe(String companyName, String targetType,
			String targetSource) throws InterruptedException, IOException {
		try {
			if (targetType == "Gerçek Gönderim")
				log.info("newRealTargetList Creating");
			else
				log.info("newTestTargetList Creating");
			long unixTime = System.currentTimeMillis() / 1000L;
			// Yeni hedef liste oluştur bölümüne bağlanıyor
			driver.get("http://testsmsgateway.tani.com.tr:8080/campaign/targetList/new");
			WebElement listName = searchElement(driver, "#listName");
			// hedef kitle ismi veriliyor
			listName.sendKeys("Hedef Kitle" + unixTime);
			WebElement selectCompany;
			selectCompany = searchElement(driver,
					"#s2id_branch>a>.select2-chosen");
			selectCompany.click();
			selectCompany.sendKeys(companyName);
			selectCompany.sendKeys(Keys.RETURN);// işyeri seçiliyor
			WebElement typeTarget = searchElement(driver, "#listType");
			typeTarget.click();
			typeTarget.sendKeys(targetType);
			typeTarget.sendKeys(Keys.RETURN);// hedef kitle tipi seçiliyor
			WebElement sourceTarget = searchElement(driver, "#targetListSource");
			sourceTarget.click();
			sourceTarget.sendKeys(targetSource);
			sourceTarget.sendKeys(Keys.RETURN);// hedef kitle kaynağı seçiliyor
			// -------dosya hedef kitlesi seçildiyse------------
			if (targetSource == "Dosya Hedef Kitlesi") {
				WebElement sourceFile = driver.findElement(By
						.id("targetListFileLocal"));
				sourceFile
						.sendKeys("C:\\Users\\mustafa\\Desktop\\TANI-TARGETLIST\\TANI-TARGETLIST\\testEMAIL-just-me_email_columniack.txt");// hedef
																																			// kitle
																																			// dosyası
																																			// yükleniyor
				WebElement viewFile = searchElement(driver,
						"#fileTargetListPreview");
				viewFile.click();// Dosya önizleme butonuna tıklanır
				// popup`ın açılması beklenir
				searchElement(driver, ".modal-header");
				// popup`daki close butonu bulunur
				WebElement closeButton = searchElement(driver, ".close");
				try {// butona tıklama işlemi olumsuz sonuçlanırsa popup
						// açılmamış
						// demektir bu yüzden try-catch ile bu işlemler yapılır.
					closeButton.click();
					listName.submit();
					try {
						WebElement okButton = searchElement(driver,
								".btn.btn-primary");
						if (targetType == "Gerçek Gönderim")
							log.info("newRealTargetList Created");
						else
							log.info("newTestTargetList Created");
						okButton.click();
					} catch (Exception e) {
						screenShot("newTestTargetList");
						log.error("Registry Error");
					}
				} catch (Exception e) {
					screenShot("newTargetListPopup");
					log.error("Popup Error");
				}
			}
			// -------Sorgu Hedef Kitlesi Seçildiyse---------------
			else {
				WebElement queryName = searchElement(driver,
						"#s2id_autogen2>a>.select2-chosen");
				queryName.click();
				queryName.sendKeys("APP_TEST_DB");
				queryName.sendKeys(Keys.ENTER);
				WebElement queryText = searchElement(driver,
						"textarea.form-control.queryTargetList");
				queryText
						.sendKeys("SELECT DWH_PARO_KOD,AD, SOYAD, EMAIL, To_Number(To_Char(DOGUM_TARIHI,'DDMM')) KRITER_DOGUM, To_Number(To_Char(SYSDATE+0,'DDMM')) KRITER_SYSDATE FROM SD_TUKETICI_VPI Where To_Number(To_Char(DOGUM_TARIHI,'DDMM')) <= To_Number(To_Char(SYSDATE+0,'DDMM')) and dwh_paro_kod>1000");
				WebElement viewSql = searchElement(driver,
						"#queryTargetListPreview");
				viewSql.click();// sorgu önizleme butonuna tıklanır
				// popup`ın açılması beklenir
				searchElement(driver, ".modal-header");
				// popup`daki close butonu bulunur
				WebElement closeButton = searchElement(driver, ".close");
				try {// butona tıklama işlemi olumsuz sonuçlanırsa popup
						// açılmamış
						// demektir bu yüzden try-catch ile bu işlemler yapılır.
					closeButton.click();
					listName.submit();
					try {
						WebDriverWait wait2 = new WebDriverWait(driver, 100000);
						WebElement okButton = wait2.until(ExpectedConditions
								.elementToBeClickable(By
										.className("btn-primary")));
						okButton.click();
						if (targetType == "Gerçek Gönderim")
							log.info("newRealTargetList Created");
						else
							log.info("newTestTargetList Created");
					} catch (Exception e) {
						log.error("Registry Error");
						screenShot("newTargetListRegistry");
					}
				} catch (Exception e) {
					screenShot("newTargetListRegistryPopup");
					log.error("Popup Error");
				}
			}
		} catch (Exception e) {

			failedSteps += "\n New Target List Create";
			System.out.println(e.getMessage());
			screenShot("newTargetList");
			log.error("Application Stopped");
		}
	}

	public static void KampanyaGonderimiEmail(String castType, String friend,
			String rate, String companyName, String speed)
			throws InterruptedException, IOException {
		try {
			log.info("SendCampaignEmail (" + castType + ") Started");
			String alert1Text = null, alert2Text = null;
			long unixTime = System.currentTimeMillis() / 1000L;
			driver.get("http://testsmsgateway.tani.com.tr:8080/campaign/campaign/email/new");
			WebElement campaignName = searchElement(driver, "#campaignName");
			campaignName.sendKeys("Kampanya " + unixTime);
			WebElement sendSelect = searchElement(driver,
					"#emailCampaignCastType");
			sendSelect.click();
			sendSelect.sendKeys(castType);
			sendSelect.sendKeys(Keys.RETURN);// gönderim kurgusu seçildi✓
			// -----------------------------------------------------------------------
			if (castType == "Tekil Gönderim") {
				WebElement shareFriend = searchElement(driver,
						"#emailCampaignShareThisFlag");
				shareFriend.click();
				shareFriend.sendKeys(friend);
				shareFriend.sendKeys(Keys.RETURN);// arkadaşına gönder
													// pasif/aktif✓
			}
			// -----------------------------------------------------------------------
			if (castType == "Tekil Gönderim") {
				WebElement rateFlag = searchElement(driver,
						"#emailCampaignRateFlag");
				rateFlag.click();
				rateFlag.sendKeys(rate);
				rateFlag.sendKeys(Keys.RETURN);// değerlendirme puanı
												// pasif/aktif✓
			}
			// -----------------------------------------------------------------------
			WebElement mechanismText = searchElement(driver, "#mechanism");
			mechanismText.sendKeys("Test");// mekanizm metin alanına metin
											// girişi yapıldı✓
			// -----------------------------------------------------------------------
			WebElement selectCompany = searchElement(driver, "#s2id_autogen1");
			selectCompany.click();
			selectCompany.sendKeys(companyName);
			selectCompany.sendKeys(Keys.RETURN);// işyeri seçiliyor✓
			// -----------------------------------------------------------------------
			WebElement subjectText = searchElement(driver,
					"#emailCampaignSubjects>div>div>.form-control");
			subjectText.sendKeys("Test Subject");// Subject girişi yapılıyor ✓
			// -------------------------------------------------------------------------
			if (castType == "Tekil Gönderim") {
				WebElement speedPost = searchElement(driver,
						"#emailCampaignDirectSendFlag");
				speedPost.click();
				speedPost.sendKeys(speed);
				speedPost.sendKeys(Keys.RETURN);// Hızlı gönder aktif/pasif
												// seçiliyor✓
			}
			// -------------------------------------------------------------------------
			// targetlistseçimi
			WebElement targetSelect = searchElement(driver,
					"#s2id_emailCampaignTestTargetList>a>.select2-chosen");
			targetSelect.click();
			targetSelect.sendKeys(Keys.ARROW_DOWN);
			targetSelect.sendKeys(Keys.ARROW_DOWN);
			targetSelect.sendKeys(Keys.RETURN); // target list seçiliyor ✓
			// -------------------------------------------------------------------------
			driver.switchTo().frame("emailCampaignMessage_ifr");
			WebElement messageContent = searchElement(driver, "#tinymce");
			messageContent.sendKeys("Mesaj içeriği"); // mesaj içeriği
														// giriliyor✓
			// -------------------------------------------------------------------------
			driver.switchTo().defaultContent();
			WebElement titleSelect = searchElement(driver,
					"#s2id_emailCampaignHeader>a>.select2-chosen");
			titleSelect.click();
			titleSelect.sendKeys(Keys.RETURN); // başlık seçiliyor ✓
			// ----------------------------------------------------------------------
			// Kampanya Kaydet Kontrolü//
			WebElement saveButton = searchElement(driver,
					".btn.btn-lg.blue.campaignSave");
			saveButton.click();
			boolean saveSuccess = true;
			try {
				boolean searchError = searchElement(driver, ".preserve-newline") == null;
				while (searchError) {
					WebElement alert1 = searchElement(driver,
							".preserve-newline.margin-bottom-5.alert.alert-success");
					alert1Text = alert1.getText();
					saveSuccess = true;
					searchError = searchElement(driver, ".preserve-newline") == null;
				}
			} catch (NoSuchElementException e) {
				saveSuccess = false;
				screenShot("SaveCampaignEmail");
			}
			if (saveSuccess == true) {
				WebElement sendTest = driver.findElement(By
						.cssSelector(".btn.btn-lg.green.campaignSend"));
				sendTest.click();
				WebElement alert2 = searchElement(driver,
						".preserve-newline.margin-bottom-5.alert.alert-success");
				alert2Text = alert2.getText();
				if (alert1Text != alert2Text) {
					log.info("Test Send Succeed");
					log.info("Real Send Starting...");
					WebElement acceptSend = driver.findElement(By
							.cssSelector("input.campaignTestApprove.uniform"));
					acceptSend.click();
					WebElement realTargetSelect = searchElement(driver,
							"#s2id_emailCampaignRealTargetList>a>.select2-chosen");
					realTargetSelect.click();
					realTargetSelect.sendKeys(Keys.ARROW_DOWN);
					realTargetSelect.sendKeys(Keys.ARROW_DOWN);
					realTargetSelect.sendKeys(Keys.RETURN);
					WebElement realSendButton = searchElement(driver,
							".btn.btn-lg.green.campaignSend");
					realSendButton.click();
					String url = "http://testsmsgateway.tani.com.tr:8080/campaign/campaign/search?message=Email%20Kampanyas%C4%B1%20ger%C3%A7ek%20g%C3%B6nderim%20yap%C4%B1ld%C4%B1...";
					int count = 0;
					while (count < 60) {
						if (url.contains(driver.getCurrentUrl().toString())) {
							log.info("Real Send Succeed");

							break;
						} else {
							Thread.sleep(1000);
							count++;
						}
						if (count >= 60)
							log.error("Real Send Failed");
					}
				} else
					log.error("Send Failed");
			} else
				log.error("Registry Error");
		} catch (Exception e) {
			failedSteps += "\n Campaign Send Email (" + castType + ")";
			System.out.println(e.getMessage());
			screenShot("SendCampaignEmail(" + castType + ")");
			log.error("Application Stopped");
		}
	}

	public static void KampanyaGonderimiSms(String castType,
			String companyName, String operatorName)
			throws InterruptedException, IOException {
		try {
			if (castType == "Tekil Gönderim")
				log.info("SendCampaignSms (Single) Started");
			else
				log.info("SendCampaignSms (Multi) Started");
			String alert1Text = null, alert2Text = null;
			long unixTime = System.currentTimeMillis() / 1000L;
			driver.get("http://testsmsgateway.tani.com.tr:8080/campaign/campaign/sms/new");
			WebElement campaignName = searchElement(driver, "#campaignName");
			campaignName.sendKeys("Kampanya " + unixTime);
			WebElement sendSelect = searchElement(driver, "#castType");
			sendSelect.click();
			sendSelect.sendKeys(castType);
			sendSelect.sendKeys(Keys.RETURN);// gönderim kurgusu seçildi✓
			// --------------Operatör Seçimi-----------------------
			WebElement operatorSelect = searchElement(driver, "#operator");
			operatorSelect.click();
			operatorSelect.sendKeys(operatorName);
			operatorSelect.sendKeys(Keys.RETURN);// operator seçildi✓
			// ----------------------------------------------------
			WebElement mechanismText = searchElement(driver, "#mechanism");
			mechanismText.sendKeys("Test");// mekanizm metin alanına metin
											// girişi yapıldı✓
			WebElement selectCompany = searchElement(driver, "#s2id_autogen1");
			selectCompany.click();
			selectCompany.sendKeys(companyName);
			selectCompany.sendKeys(Keys.RETURN);// işyeri seçiliyor✓
			// -----------------------------------------------------------------------
			driver.switchTo().frame("smsCampaignMessage_ifr");
			WebElement messageContent = searchElement(driver, "#tinymce");
			messageContent.sendKeys("Mesaj icerigi"); // mesaj içeriği
														// giriliyor✓
			driver.switchTo().defaultContent();
			WebElement targetSelect = searchElement(driver,
					"#s2id_smsCampaignTestTargetList>a>.select2-chosen");
			targetSelect.click();
			targetSelect.sendKeys(Keys.ARROW_DOWN);
			targetSelect.sendKeys(Keys.RETURN); // target list seçiliyor ✓
			// -------------------------------------------------------------------------
			WebElement titleSelect = searchElement(driver,
					" #s2id_smsCampaignPrefix>a>.select2-chosen");
			titleSelect.click();
			titleSelect.sendKeys(Keys.RETURN); // başlık seçiliyor ✓
			// Kampanya Kaydet Kontrolü//
			WebElement saveButton = searchElement(driver,
					".btn.btn-lg.blue.campaignSave");
			saveButton.click();
			boolean saveSuccess = true;
			try {
				boolean searchError = searchElement(driver, ".preserve-newline") == null;
				while (searchError) {
					WebElement alert1 = searchElement(driver,
							".preserve-newline.margin-bottom-5.alert.alert-success");
					alert1Text = alert1.getText();
					saveSuccess = true;
					searchError = searchElement(driver, ".preserve-newline") == null;
				}
			} catch (NoSuchElementException e) {
				saveSuccess = false;
				screenShot("SaveCampaignSms(" + castType + ")");
			}
			if (saveSuccess == true) {
				WebElement sendTest = searchElement(driver,
						".btn.btn-lg.green.campaignSend");
				sendTest.click();
				WebElement alert2 = searchElement(driver,
						".preserve-newline.margin-bottom-5.alert.alert-success");
				alert2Text = alert2.getText();
				if (alert1Text != alert2Text) {
					log.info("Test Send Succeed");
					log.info("Real Send Starting...");
					WebElement acceptSend = driver.findElement(By
							.cssSelector(".campaignTestApprove.uniform"));
					acceptSend.click();
					WebElement realTargetSelect = searchElement(driver,
							"#s2id_smsCampaignRealTargetList>a>.select2-chosen");
					realTargetSelect.click();
					realTargetSelect.sendKeys(Keys.ARROW_DOWN);
					realTargetSelect.sendKeys(Keys.RETURN);
					WebElement realSendButton = searchElement(driver,
							".btn.btn-lg.green.campaignSend");
					realSendButton.click();
					String url = "http://testsmsgateway.tani.com.tr:8080/campaign/campaign/search?message=SMS%20Kampanyas%C4%B1%20ger%C3%A7ek%20g%C3%B6nderim%20yap%C4%B1ld%C4%B1...";
					int count = 0;
					while (count < 60) {
						if (url.contains(driver.getCurrentUrl().toString())) {
							log.info("Real Send Succeed");
							break;
						} else {
							Thread.sleep(1000);
							count++;
						}
						if (count >= 60)
							log.error("Real Send Failed");
					}
				} else
				{log.error("Send Failed");
				screenShot("SendCampaignSmsFailed(" + castType + ")");
				}
			} else
			{log.error("Registry Error");
			screenShot("SendCampaignSmsRegistry(" + castType + ")");}
		} catch (Exception e) {

			System.out.println(e.getMessage());
			// hata durumunda ekran alıntısı alınır
			failedSteps += "\n Campaing Send Sms (" + castType + ")";
			screenShot("SendCampaignSms(" + castType + ")");
			log.error("Application Stoped");
		}
	}

	// ----------İslevsel Methodlar-----------
	/**
	 * connect_Tani() Tani üzerinde account açmayı sağlıyor
	 * 
	 * @throws IOException
	 */
	public static void connect_Tani() throws IOException {
		try {
			log.info("Tani Connecting...");
			driver.get("http://testsmsgateway.tani.com.tr:8080/campaign/account/login");
			WebElement userName = driver.findElement(By.name("j_username"));
			userName.sendKeys("demo");
			WebElement userPass = driver.findElement(By.name("j_password"));
			userPass.sendKeys("123456");
			WebElement loginBtn = driver.findElement(By.id("command"));
			loginBtn.submit();
			log.info("Connected.");
		} catch (Exception e) {
			failedSteps += "\n Tani Connect";
			screenShot("connectTani");
			log.error("Not Connecting");
		}
	}

	/**
	 * 
	 * @param driver
	 *            o an kullandigimiz WebDriver`ı alıyor
	 * @param cssText
	 *            Aradigimiz nesnenin cssSelector bilgisini alıyor
	 * @return Web Elementi gönderiyor
	 * @throws Exception
	 */
	public static WebElement searchElement(WebDriver driver, String cssText)
			throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 1000);
		WebElement element;
		try {
			element = wait.until(ExpectedConditions.elementToBeClickable(By
					.cssSelector(cssText)));
			return element;
		} catch (Exception e) {
			log.error("searchElement-- object not found");
			throw new Exception("Bulunamadi");
		}
	}

	public static void screenShot(String methodName) throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_hh_mm");
		Date date = new Date();
		// hata durumunda ekran alıntısı alınır
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		// ekran alıntısı bu konuma kaydedilir.
		FileUtils.copyFile(scrFile,
				new File(screeenshotDirectory +"\\"+dateFormat.format(date)+"\\"+ dateFormat.format(date)
						+ methodName + ".png"));
		log.info("Screen Captured");
		log.info("File: " + screeenshotDirectory + methodName
				+ dateFormat.format(date) + ".png");
	}
}