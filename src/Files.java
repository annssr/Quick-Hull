package alg;

import java.io.File;
import java.util.Scanner;

class Files {
  public static Scanner scan;
  
  public static void openRFile(String s) {
    try {
      scan = new Scanner(new File(s));
    } catch (Exception e) {
      System.out.println("Scanner Error: " + e.getMessage());
    } 
  }
  
  public static double[] readFileString(String s) {
    int count = 0;
    for (int i = 0; scan.hasNext(); i++) {
      count++;
      scan.next();
    } 
    double[] res = new double[count];
    closeRFile();
    openRFile(s);
    for (int j = 0; scan.hasNext(); j++) {
      String z = String.format("%s", new Object[] { Double.valueOf(scan.nextDouble()) });
      res[j] = Double.parseDouble(z);
    } 
    return res;
  }
  
  public static void closeRFile() {
    scan.close();
  }
}
