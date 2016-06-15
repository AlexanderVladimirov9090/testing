package com.clouway.stringsum;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


/**
 * Created by clouway on 15.06.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
public class ArithmeticTest {
  private Arithmetic calculator;

  @Before
  public void initialize() {
    calculator = new Arithmetic();
  }

  @Test(expected = NullPointerException.class)
  public void testAddEmptyStringThrowsNullPointerException() {
    calculator.add("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNewLineThrowsNullPointerException() {
    double result = calculator.add("\n");
  }

  @Test(expected = NullPointerException.class)
  public void testAddOnlyTextThrowsNullPointerException() {
    calculator.add("asdfsadf");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddFrontNewLineTextOnlyReturnZero() {
    calculator.add("\n asdf");
  }

  @Test
  public void testAddOneNumberReturnItself() {
    double result = calculator.add("1");
    assertEquals(1, result, 0);
  }

  @Test
  public void testAddTwoNumbersReturnSum() {
    double result = calculator.add("1 2");
    assertEquals(3, result, 0);
  }

  @Test
  public void testAddFrontTextNumbersReturnSumOfNumbers() {
    double result = calculator.add("a 1 3 asd 4");
    assertEquals(8, result, 0);
  }

  @Test
  public void testAddNumbersMidTextReturnSumOfNumbers() {
    double result = calculator.add("1 2 asd 2");
    assertEquals(5, result, 0);
  }

  @Test
  public void testAddNumbersEndTextReturnSumOfNumbers() {
    double result = calculator.add("1 2 3 asd");
    assertEquals(6, result, 0);
  }

  @Test
  public void testAddTextNewLinesNumbersReturnSumOfNumbers() {
    double result = calculator.add("import java.util.regex.Pattern;\n" +
            "import java.util.regex.Matcher;\n" +
            "\n" +
            "public class MatcherDemo {\n" +
            "\n" +
            "    private static final String REGEX =\n" +
            "        \"\\\\bdog\\\\b\";\n" +
            "    private static final String INPUT =\n" +
            "        \"dog dog dog doggie dogg\";\n" +
            "\n" +
            "    public static void main(String[] args) {\n" +
            "       Pattern p = Pattern.compile(REGEX);\n" +
            "       //  get a matcher object\n" +
            "       Matcher m = p.matcher(INPUT);\n" +
            "       int count = 0;\n" +
            "       while(m.find()) {\n" +
            "           count++;\n" +
            "           System.out.println(\"Match number \"\n" +
            "                              + count);\n" +
            "           System.out.println(\"start(): \"\n" +
            "                              + m.start());\n" +
            "           System.out.println(\"end(): \"\n" +
            "                              + m.end());\n" +
            "      }\n" +
            "   }\n" +
            "}\n" +
            "\n" +
            "OUTPUT:\n" +
            "\n" +
            "Match number 1\n" +
            "start(): 0\n" +
            "end(): 3\n" +
            "Match number 2\n" +
            "start(): 4\n" +
            "end(): 7\n" +
            "Match number 3\n" +
            "start(): 8\n" +
            "end(): 11\n");
    assertEquals(39, result, 0);
  }
}