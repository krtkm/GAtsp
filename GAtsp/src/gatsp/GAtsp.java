/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatsp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tika
 */
public class GAtsp {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) throws FileNotFoundException {
        int banyakKota=0;
        long startTime = System.currentTimeMillis();
        long limitedTime = startTime + 60*1000;
        
//        int isi=0;
        // TODO code application logic here
        
        // Create and add our cities
//        City city = new City(24, 82);
//        TourManager.addCity(city);
//        City city2 = new City(24, 87);
//        TourManager.addCity(city2);
//        City city3 = new City(200, 84);
//        TourManager.addCity(city3);
//        City city4 = new City(138, 135);
//        TourManager.addCity(city4);
//        City city5 = new City(150, 174);
//        TourManager.addCity(city5);

//        Thread t = new Thread(myRunnable);
        String namaFile="65834_hiddeninstance1";
        File file = new File("E:\\College\\Master Degree\\Semester3\\OKH\\datasets\\datasets\\"+namaFile+".csv"); // TODO: read about File Names
//        while (System.currentTimeMillis() < limitedTime) {
            try {
                Scanner inputScanner = new Scanner(file);
                int isi=inputScanner.nextInt();
                City numberOfCity[]=new City[isi];
                banyakKota=isi;
                int  ind=0;
                while (inputScanner.hasNext()){
                    String dataLocation[]= inputScanner.next().split(",");
                    numberOfCity[ind]=new City(Integer.parseInt(dataLocation[0]),Integer.parseInt(dataLocation[1]));
                    TourManager.addCity(numberOfCity[ind]);
                    ind++;
                }
            inputScanner.close();
        } catch (Exception e) {
        // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Initialize population
        Population pop = new Population(100, true);
        System.out.println("Initial distance: " + pop.getFittest().getDistance());

        // Evolve population for 100 generations
        pop = GA.evolvePopulation(pop);
        for (int i = 0; i < 50; i++) {
            pop = GA.evolvePopulation(pop);
        }
        System.out.println("Finished");
        System.out.println("Final distance: " + pop.getFittest().getDistance());
        System.out.println("Solution:");
//        System.out.println(pop.getFittest());

        
        long currentTime=(System.currentTimeMillis() - startTime);
        int firstCity=0;
        try {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm").format(Calendar.getInstance().getTime());
            PrintWriter writerSolution = new PrintWriter("solutionGA_"+namaFile+"_"+timeStamp+".csv", "UTF-8");
            for (int i=0; i< banyakKota; i++) {
//                System.out.println(pop.getFittest());
//              System.out.println((dataLocation[0])+","+(dataLocation[1]));
                
//                if ((currentTime / 1000d)<6010){
                int  ind=0;
                Scanner inputScanner = new Scanner(file);
                int isi=inputScanner.nextInt();
                City numberOfCity[]=new City[isi];
                while (inputScanner.hasNext()){
                    String dataLocation[]= inputScanner.next().split(",");
                    numberOfCity[ind]=new City(Integer.parseInt(dataLocation[0]),Integer.parseInt(dataLocation[1]));
                    String nyamain=(dataLocation[0])+", "+(dataLocation[1]);
                    if (nyamain.equalsIgnoreCase(pop.getFittest().getCity(i).toString())) {
                        int urutanKota = ind+1;
                        firstCity = urutanKota;
                            System.out.print (" "+urutanKota+" > ");
                            writerSolution.println(urutanKota+", "+dataLocation[0]+", "+dataLocation[1]);

                    }
                    ind++;
                }
                inputScanner.close();
//                System.out.println("kota di file "+nyamain
//            }
            }
            writerSolution.close();
//            System.out.print(" "+firstCity);
            System.out.println("");
            System.out.println("");
            System.out.println("File Saved to solutionGA_"+namaFile+"_"+timeStamp+".csv");
//            }
        } catch (Exception e) {
        // TODO Auto-generated catch block
            e.printStackTrace();
        }
//    }
//        System.exit(0);

            // Print final results
        
        long endTime   = System.currentTimeMillis();
        NumberFormat formatter = new DecimalFormat("#0.000");
        System.out.println("");
//        System.out.println((endTime - startTime) / 1000d);
        System.out.println("Execution time is " + formatter.format((endTime - startTime) / 1000d) + " seconds");
//    System.out.println("total waktu yang dibutuhkan "+totalTime);

    }
    
}
