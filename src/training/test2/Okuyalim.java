package training.test2;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Okuyalim {

    public static void main(String[] args) throws IOException {
        String [] dizi=new String[10];
        File dosya = new File("Inputs/Sayilar.txt");
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(dosya));
        String satir = (reader.readLine());
        int i=0;
        while (satir != null) {
              	dizi[i]=satir;
              	i++;
            satir = reader.readLine();
        }
        
           for(int j=0;j<i;j++)
           	System.out.println(dizi[j]);
        reader.close();
    }
}
