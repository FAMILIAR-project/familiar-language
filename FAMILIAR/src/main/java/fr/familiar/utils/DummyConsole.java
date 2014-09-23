/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
 * http://familiar-project.github.com/
 *
 * FAMILIAR is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar.utils;

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

