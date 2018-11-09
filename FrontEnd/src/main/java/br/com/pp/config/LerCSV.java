package br.com.pp.config;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LerCSV {

  public static void main(String[] args) {

	 LerCSV obj = new LerCSV();
    obj.run();

  }

  public void run() {

    String arquivoCSV = "C:\\\\aplicativos\\\\picpay\\\\users.csv";
    BufferedReader br = null;
    String linha = "";
    String csvDivisor = ",";
    try {

        br = new BufferedReader(new FileReader(arquivoCSV));
        while ((linha = br.readLine()) != null) {

            String[] pais = linha.split(csvDivisor);

            System.out.println(pais[0]);

        }

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
  }

}