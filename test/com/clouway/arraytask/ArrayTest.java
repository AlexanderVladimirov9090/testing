package com.clouway.arraytask;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by clouway on 16.06.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
public class ArrayTest {
  private Array array;
  private int[] emptyArray = {};
  private int[] arrOneElement = {0};


  @Before
  public void initialize() {
    array = new Array();
  }

  @Test(expected = ArrayIndexOutOfBoundsException.class)
  public void getMinFromEmptyArray() {
    array.getMinElement(emptyArray);
  }

  @Test(expected = ArrayIndexOutOfBoundsException.class)
  public void getMaxElementFromEmptyArray() throws Exception {
    array.getMaxElement(emptyArray);
  }

  @Test
  public void printArrayEmptyArray() {
    PrintStream originalOutput = new PrintStream(System.out);
    OutputStream outStream = new ByteArrayOutputStream();
    PrintStream prStream = new PrintStream(outStream);
    System.setOut(prStream);
    array.printArray(emptyArray);
    prStream.close();
    System.setOut(originalOutput);

    assertThat(outStream.toString(),is(equalTo("")));

  }

  @Test
  public void getMinOneElementArray() {

    assertThat(array.getMinElement(new int[] {0}),is(equalTo(0)));
  }

  @Test
  public void getMaxOneElementArray() {

    assertThat(array.getMaxElement(new int[]{1}),is(equalTo(1)));
  }

  @Test
  public void printArrayOneElementArray() {
    PrintStream originalOutput = new PrintStream(System.out);
    OutputStream outStream = new ByteArrayOutputStream();
    PrintStream prStream = new PrintStream(outStream);
    System.setOut(prStream);
    array.printArray(new int[]{0});
    prStream.close();
    System.setOut(originalOutput);


    assertThat("0\n",is(equalTo(outStream.toString())));
  }

    @Test
    public void getMinMoreThanOneElementArray () {

      assertThat(array.getMinElement(new int[]{1, 2, 3, 4, 5, 6, 7}),is(equalTo(1)));
    }

    @Test
    public void getMaxMoreThanOneElementArray () {

      assertThat(array.getMaxElement(new int[]{1, 2, 3, 4, 5, 6, 7}),is(equalTo(7)));

    }

  @Test
  public void printArrayMoreThanOneElementArray() {
    PrintStream originalOutput = new PrintStream(System.out);
    OutputStream outStream = new ByteArrayOutputStream();
    PrintStream prStream = new PrintStream(outStream);
    System.setOut(prStream);
    array.printArray(new int[]{1, 2, 3, 4, 5, 6, 7});
    prStream.close();
    System.setOut(originalOutput);

    assertThat("1\n2\n3\n4\n5\n6\n7\n",is(equalTo(outStream.toString())));
  }
}