package com.clouway.warehouse;

/**
 * Created by clouway on 29.06.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
class Price {
  private final Double value;

  public Price(Double value) {
    this.value = value;
  }

  /**
   * Returns value of price.
   *
   * @return value of price.
   */
  public Double getPrice() {
    return value;

  }
}
