package test;

import com.clouway.stringsum.Arithmetic;
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
  public void addEmptyStringThrowsNullPointerException() {
    calculator.add("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNewLineThrowsNullPointerException() {
    double result = calculator.add("\n");
  }

  @Test(expected = NullPointerException.class)
  public void addOnlyTextThrowsNullPointerException() {
    calculator.add("asdfsadf");
  }

  @Test(expected = IllegalArgumentException.class)
  public void addFrontNewLineTextOnlyReturnZero() {
    calculator.add("\n asdf");
  }

  @Test
  public void addOneNumberReturnItself() {
    double result = calculator.add("1");
    assertEquals(1, result, 0);
  }

  @Test
  public void addTwoNumbersReturnSum() {
    double result = calculator.add("1 2");
    assertEquals(3, result, 0);
  }

  @Test
  public void addFrontTextNumbersReturnSumOfNumbers() {
    double result = calculator.add("a 1 3 asd 4");
    assertEquals(8, result, 0);
  }

  @Test
  public void addNumbersMidTextReturnSumOfNumbers() {
    double result = calculator.add("1 2 asd 2");
    assertEquals(5, result, 0);
  }

  @Test
  public void addNumbersEndTextReturnSumOfNumbers() {
    double result = calculator.add("1 2 3 asd");
    assertEquals(6, result, 0);
  }

  @Test
  public void addTextNewLinesNumbersReturnSumOfNumbers() {
    double result = calculator.add("aaa\n34\nbbb4ccc\n11");
    assertEquals(49, result, 0);
  }
}