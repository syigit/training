package training.test1;
import java.util.Scanner;
public class dizisiralama {
public static void main(String args[])
{
	Scanner scnf=new Scanner(System.in);
	System.out.print("Diziniz Ka� Elemanl� Olsun?");
	int eleman;
	eleman=Integer.parseInt(scnf.nextLine());
	System.out.println(eleman+" Elemanl� Diziniz Haz�rlan�yor...");
	int [] dizi=new int[eleman];
	for (int i=0;i<dizi.length;i++)
	{
		dizi[i]=(int)(Math.random()*100);
	}
	System.out.println(eleman+" Elemanl� Diziniz Yazd�r�l�yor...");
	for (int j=0;j<dizi.length;j++)
	{
		System.out.println((j+1)+" nci eleman�n�z= "+dizi[j]);
		
	}		
	scnf.close();
	System.out.println("Diziniz K���kten B�y��e S�ralan�yor");
	sort(dizi, eleman);
	for (int i = 0; i < eleman; i++)
	{	
	System.out.println((i+1)+"nci eleman�n�z= " + dizi[i]);
	}
	System.exit(0);
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
