package training.test1;
/**
 * @author SerdarYIGIT
 * @author MustafaBICER
 * @since 24.06.2014
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import org.apache.log4j.*;
/**
 * 
 *@param log nesnesi Log islemleri icin Logger class`indan turetilir.
 *@param scanNumber nesnesi okuma islemleri icin Scanner Class`indan turetilir.
 */
public class DiziSiralama {
	private static org.apache.log4j.Logger log = Logger.getLogger(DiziSiralama.class);
	private static Scanner scanNumber;
	/**
	 * 
	 * @param args  metodumuz string degerli argumanlari icerir
	 * @throws IOException hata yakalar.
	 */
	public static void main(String[] args) throws IOException
{			
	scanNumber = new Scanner(System.in);
	System.out.println("(1)Rastgele Dizi Oluþturmak-(2) Dosyadan Dizi Çekmek");
	int menuSelect=Integer.parseInt(scanNumber.nextLine());
	if (menuSelect==1)
	{
	System.out.print("Diziniz Kaç Elemanlý Olsun?");
	int arrayElement;
	arrayElement=Integer.parseInt(scanNumber.nextLine());
	System.out.println(arrayElement+" Elemanlý Diziniz Hazýrlanýyor...");
	log.info("Diziniz Hazirlaniyor");
	int [] dizi=new int[arrayElement];
	for (int i=0;i<dizi.length;i++)
	{
		dizi[i]=(int)(Math.random()*100);
	}
	System.out.println(arrayElement+" Elemanlý Diziniz Yazdýrýlýyor...");
	log.info("Diziniz Yazdýrýlýyor");
	for (int j=0;j<dizi.length;j++)
	{
		System.out.println((j+1)+" nci elemanýnýz= "+dizi[j]);
		
	}		
	System.out.println("Diziniz Küçükten Büyüðe Sýralanýyor");
	log.info("Diziniz Kucukten Buyuge Siralaniyor.");
	arraySort(dizi, arrayElement);
	for (int i = 0; i < arrayElement; i++)
	{	
	System.out.println((i+1)+"nci elemanýnýz= " + dizi[i]);
	}
	}
	else if (menuSelect==2)
	{	
		int [] dizi=new int[10];
        File dosya = new File("Inputs/Sayilar.txt");        	
        log.info("Inputs/Sayilar.txt dosyasindan degerler cekiliyor.");
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(dosya));
        if (reader.ready()==false)
        {
        	log.error("Okuma Gerceklestirilemedi");
        }
        String satir = (reader.readLine());
        int i=0;
        while (satir != null) {
          	dizi[i]=Integer.parseInt(satir);
          	i++;
        satir = reader.readLine();
    }    arraySort(dizi,i);
    	for(int j=0;j<i;j++)
    		System.out.println(dizi[j]);
    reader.close();
}
	else
		
		{
		System.out.println("Lütfen 1 ya da 2 Giriniz");
		log.error("Hatali Giris Yaptiniz");
		}
	 	
}    
	/**
	 * 
	 * @param a dizi degiskenini tutar.
	 * @param size dizi boyutunu tutar.
	 */
static void arraySort(int a[], int size) {
for (int i = 1; i < size; i++) {
int x = a[i];
int j;
for (j = i-1; j >=0; --j) {
if (a[j] <= x)
break;
a[j+1] = a[j];
}
a[j+1] = x;
}}}