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
  private ;

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
  public void printArrayEmptyArray() throws Exception {
    array.printArray(emptyArray);
  }

  @Test
  public void getMinOneElementArray() {
    int result =
    assertEquals(0, array.getMinElement(arrOneElement), 0);
  }

  @Test
  public void getMaxOneElementArray() {

    assertEquals(0, array.getMinElement(arrOneElement), 0);
  }

  @Test
  public void printArrayOneElementArray(){
    int[] sameArray=array.printArray(arrOneElement);
    assertArrayEquals(arrOneElement,sameArray);
  }

  @Test
  public void getMinMoreThanOneElementArray(){

    assertEquals(1,array.getMinElement(new int[] {1, 2, 3, 4, 5, 6, 7}),0);
  }

  @Test
  public void getMaxMoreThanOneElementArray(){

    assertEquals(7, array.getMaxElement(new int[] {1, 2, 3, 4, 5, 6, 7}),0);
  }
  @Test
  public void printArrayMoreThanOneElementArray(){
    int[] sameArray = array.printArray(new int[] {1, 2, 3, 4, 5, 6, 7});
    assertArrayEquals(sameArray,new int[]{1,2,3,4,5,6,7},0);
  }
}