package training.test2;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class okuyalim {

    public static void main(String[] args) throws IOException {
        // String [] dizi=new String[10];
        File dosya = new File("C:/Users/MyComputer/Desktop/sayilar.txt");
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(dosya));
        String satir = (reader.readLine());
        //int i=0;
        while (satir != null) {
            //   	dizi[i]=satir; amac�m burada dosyadan �ekti�im de�erlerden dizi olu�turmak ama malesef hata veriyor.
            //   	i++;
            satir = reader.readLine();
            System.out.println(satir);
        }
        //    for(int j=0;j<dizi.length;j++)
        //   	System.out.println(dizi[j]);
        reader.close();
    }
}
