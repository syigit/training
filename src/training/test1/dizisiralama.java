package training.test1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class dizisiralama {
	public static void main(String[] args) throws IOException
{
	Scanner scnf=new Scanner(System.in);
	System.out.println("(1)Rastgele Dizi Oluþturmak-(2) Dosyadan Dizi Çekmek");
	int sec=Integer.parseInt(scnf.nextLine());
	if (sec==1)
	{
	System.out.print("Diziniz Kaç Elemanlý Olsun?");
	int eleman;
	eleman=Integer.parseInt(scnf.nextLine());
	System.out.println(eleman+" Elemanlý Diziniz Hazýrlanýyor...");
	int [] dizi=new int[eleman];
	for (int i=0;i<dizi.length;i++)
	{
		dizi[i]=(int)(Math.random()*100);
	}
	System.out.println(eleman+" Elemanlý Diziniz Yazdýrýlýyor...");
	for (int j=0;j<dizi.length;j++)
	{
		System.out.println((j+1)+" nci elemanýnýz= "+dizi[j]);
		
	}		
	System.out.println("Diziniz Küçükten Büyüðe Sýralanýyor");
	sort(dizi, eleman);
	for (int i = 0; i < eleman; i++)
	{	
	System.out.println((i+1)+"nci elemanýnýz= " + dizi[i]);
	}
	}
	else if (sec==2)
	{	
		int [] dizi=new int[10];
        File dosya = new File("Inputs/sayilar.txt");
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(dosya));
        String satir = (reader.readLine());
        int i=0;
        while (satir != null) {
          	dizi[i]=Integer.parseInt(satir); //amacým burada dosyadan çektiðim deðerlerden dizi oluþturmak ama malesef hata veriyor.
          	i++;
        satir = reader.readLine();
       // System.out.println(satir);
    }    sort(dizi,i);
    	for(int j=0;j<i;j++)
    		System.out.println(dizi[j]);
    reader.close();
}
	else
		System.out.println("Lütfen 1 ya da 2 Giriniz");
}

        
static void sort(int a[], int size) {
for (int i = 1; i < size; i++) {
int x = a[i];
int j;
for (j = i-1; j >=0; --j) {
if (a[j] <= x)
break;
a[j+1] = a[j];
}
a[j+1] = x;
}
}
}
