package com.clouway.arraytask;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
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
  public void getMinEmptyArray() {
    array.getMinElement(emptyArray);
  }

  @Test(expected = ArrayIndexOutOfBoundsException.class)
  public void getMaxElement() throws Exception {
    array.getMaxElement(emptyArray);
  }

  @Test
  public void printArrayEmptyArray() {
    PrintStream originalOutput = System.out;
    OutputStream outStream = new ByteArrayOutputStream();
    PrintStream prStream = new PrintStream(outStream);
    System.setOut(prStream);
    array.printArray(emptyArray);

    assertThat("",is(equalTo(outStream.toString())));
  System.setOut(originalOutput);
  }

  @Test
  public void getMinOneElementArray() {

    assertThat(0, is(equalTo(array.getMinElement(arrOneElement))));
  }

  @Test
  public void getMaxOneElementArray() {

    assertThat(0, is(equalTo(array.getMaxElement(arrOneElement))));
  }

  @Test
  public void printArrayOneElementArray() {
    PrintStream originalOutput = System.out;
    OutputStream outStream = new ByteArrayOutputStream();
    PrintStream prStream = new PrintStream(outStream);
    System.setOut(prStream);
    array.printArray(arrOneElement);

    assertThat("0\n",is(equalTo(outStream.toString())));
    System.setOut(originalOutput);
  }

  @Test
  public void getMinMoreThanOneElementArray() {

    assertThat(1, is(equalTo(array.getMinElement(new int[]{1, 2, 3, 4, 5, 6, 7}))));
  }

  @Test
  public void getMaxMoreThanOneElementArray() {

    assertThat(7, is(equalTo(array.getMaxElement(new int[]{1, 2, 3, 4, 5, 6, 7}))));

  }

  @Test
  public void printArrayMoreThanOneElementArray() {
    PrintStream originalOutput = System.out;
    OutputStream outStream = new ByteArrayOutputStream();
    PrintStream prStream = new PrintStream(outStream);
    System.setOut(prStream);
    array.printArray(new int[]{1, 2, 3, 4, 5, 6, 7});

    assertThat("1\n2\n3\n4\n5\n6\n7\n",is(equalTo(outStream.toString())));
    System.setOut(originalOutput);
  }
}