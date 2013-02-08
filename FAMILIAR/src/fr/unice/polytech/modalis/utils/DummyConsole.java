package fr.unice.polytech.modalis.utils;

/*
 * Output:
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DummyConsole {

  public static void main(String args[]) {
    try {

      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(isr);

      while (true) {

        System.out.print("Radius? ");

        String str = br.readLine();
        double radius;
        try {
          radius = Double.valueOf(str).doubleValue();
        } catch (NumberFormatException nfe) {
          System.out.println("Incorrect format!");
          continue;
        }

        if (radius <= 0) {
          System.out.println("Radius must be positive!");
          continue;
        }

        double area = Math.PI * radius * radius;
        System.out.println("Area is " + area);
        //return;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

