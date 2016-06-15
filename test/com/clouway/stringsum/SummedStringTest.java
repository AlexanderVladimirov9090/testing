package com.clouway.stringsum;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by clouway on 15.06.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
public class SummedStringTest {
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
    calculator.add("\n");
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
    assertThat(calculator.add("1"), is(equalTo(1.0)));
  }


  @Test
  public void addTwoNumbersReturnSum() {
    assertThat(calculator.add("1 2"), is(equalTo(3.0)));
  }

  @Test
  public void addFrontTextNumbersReturnSumOfNumbers() {
    assertThat(calculator.add("a 1 3 asd 4"), is(equalTo(8.0)));
  }

  @Test
  public void addNumbersMidTextReturnSumOfNumbers() {
    assertThat(calculator.add("1 2 asd 2"), is(equalTo(5.0)));
  }

  @Test
  public void addNumbersEndTextReturnSumOfNumbers() {
   assertThat(calculator.add("1 2 3 asd"),is(equalTo(6.0)));

  }

  @Test
  public void addTextNewLinesNumbersReturnSumOfNumbers() {
    assertThat(calculator.add("aaa\n34\nbbb4ccc\n11"),is(equalTo(49.0)));
  }


}