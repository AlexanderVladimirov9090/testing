package com.clouway.warehouse;

/**
 * Created by clouway on 28.06.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
class Product {
  private final String description;

  public Product(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
