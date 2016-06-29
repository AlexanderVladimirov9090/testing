package com.clouway.warehouse;

/**
 * Created by clouway on 29.06.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
class Product {
  private final String name;

  public Product(String name) {
    this.name = name;
  }

  /**
   * Returns name of the product.
   * @return name of product.
   */
  public String name() {
    return name;
  }
}
