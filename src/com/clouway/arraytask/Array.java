package com.clouway.arraytask;

/**
 * Created by clouway on 16.06.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
public class Array {

  /**
   * Finds minimum element in array.
   * @param array passed array.
   * @return minimum element.
   */
  public int getMinElement(int[] array){
    int minElement = array[0];
    for (int i = 1; i < array.length; i++) {
      if (array[i] < minElement) {
        minElement = array[i];
      }
    }
    return minElement;
  }

  /**
   * Finds maximum element int array.
   * @param array passed array.
   * @return maximum element.
   */
  public int getMaxElement(int[] array)throws NullPointerException{
    int maxElement = array[0];
    if(!(array.equals(null))) {
      for (int i = 1; i < array.length; i++) {
        if (array[i] > maxElement) {
          maxElement = array[i];
        }
      }
      return maxElement;
    }
    else {
      throw new NullPointerException();
    }
  }

  /**
   * Returns array.
   * @param array passed array.
   * @return array.
   */
  public int[] printArray(int[] array) {
    return array;
  }
}
