package com.clouway.warehouse;

/**
 * Created by clouway on 29.06.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
class QuantityLimitExceed extends Exception {
  public QuantityLimitExceed() {
    super("Quantity exceed");
  }
}
