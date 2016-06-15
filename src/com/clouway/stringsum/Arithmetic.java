package com.clouway.stringsum;

/**
 * Created by clouway on 15.06.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
public class Arithmetic {

  /**
   * Sums up given string escaping non Number characters and throws IllegalArgumentException if number not found in string.
   *
   * @param sequence Given string
   * @return double result.
   */
  public double add(String sequence) {
    double result = 0;
    sequence = sequence.replaceAll("[a-zA-z]", "");
    if (!(sequence.isEmpty())) {
      String[] numberContainer = sequence.split("\\D+");
      if (numberContainer.length == 0) {
        throw new IllegalArgumentException();
      }
      for (String each : numberContainer) {
        if (!each.isEmpty()) {
          result += Double.parseDouble(each);
        }
      }
      return result;
    } else {
      throw new NullPointerException();
    }
  }
}
