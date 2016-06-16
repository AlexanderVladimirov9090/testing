package com.clouway.arraytask;

import org.junit.Before;
import org.junit.Test;

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
  private int[] arrMoreThanOne = {1, 2, 3, 4, 5, 6, 7};

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
  public void printArray() throws Exception {
    array.printArray(emptyArray);
  }

  @Test
  public void getMinOneElementArray() {
    int result = array.getMinElement(arrOneElement);
    assertEquals(0, result, 0);
  }

  @Test
  public void getMaxOneElementArray() {
    int result = array.getMinElement(arrOneElement);
    assertEquals(0, result, 0);
  }

  @Test
  public void printArrayOneElementArray(){
    int[] sameArray=array.printArray(arrOneElement);
    assertArrayEquals(arrOneElement,sameArray);
  }

  @Test
  public void getMinMoreThanOneElementArray(){
    int result = array.getMinElement(arrMoreThanOne);
    assertEquals(1,result,0);
  }

  @Test
  public void getMaxMoreThanOneElementArray(){
    int result = array.getMaxElement(arrMoreThanOne);
    assertEquals(7, result,0);
  }
}