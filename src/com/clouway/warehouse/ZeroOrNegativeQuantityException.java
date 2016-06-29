package com.clouway.warehouse;

/**
 * Created by clouway on 29.06.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
class ZeroOrNegativeQuantityException extends Exception {
  public ZeroOrNegativeQuantityException() {
    super("Can`t sell zero or negative quantity from product");
  }
}
