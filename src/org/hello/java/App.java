package org.hello.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
  public static void main(String[] argv) {
    System.out.println("Hello world");
    readLine();
  }

  private static String readLine() {
    try {
      return new BufferedReader(new InputStreamReader(System.in)).readLine();
    } catch (IOException e) {
      return "";
    }
  }
}
