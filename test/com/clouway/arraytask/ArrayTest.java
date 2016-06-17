package com.clouway.arraytask;

import org.junit.Before;
import org.junit.Test;

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
    int[] thisEmptyArray = array.printArray(emptyArray);
    for (int each : thisEmptyArray) {
      assertThat(0, is(equalTo(each)));
    }
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
    int[] sameArray = array.printArray(arrOneElement);

    assertThat(arrOneElement, is(equalTo(sameArray)));
  }

  @Test
  public void getMinMoreThanOneElementArray() {

    assertThat(1, is(equalTo(array.getMinElement(new int[]{1, 2, 3, 4, 5, 6, 7}))));
  }

  @Test
  public void getMaxMoreThanOneElementArray() {

    assertThat(7, is(equalTo(array.getMinElement(new int[]{1, 2, 3, 4, 5, 6, 7}))));

  }

  @Test
  public void printArrayMoreThanOneElementArray() {
    int[] sameArray = array.printArray(new int[]{1, 2, 3, 4, 5, 6, 7});
    int[] thisArray = {1, 2, 3, 4, 5, 6, 7};

    assertThat(sameArray, is(equalTo(thisArray)));
  }
}