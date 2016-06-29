package com.clouway.warehouse;

/**
 * Created by clouway on 29.06.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
class Quantity {
  private final int quantity;

  public Quantity(int quantity) {
    this.quantity = quantity;
  }

  /**
   * Returns value of the quantity.
   *
   * @return value of quantity.
   */
  public int getQuantity() {
    return quantity;
  }
}
