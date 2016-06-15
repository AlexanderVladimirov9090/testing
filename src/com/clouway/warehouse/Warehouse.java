package com.clouway.warehouse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clouway on 28.06.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
class Warehouse {

  private List<ProductContainer> catalogue = new ArrayList<>();


  public String addProduct(String description, double priceValue, int maxValue, int quantity) {
  ProductContainer productContainer = new ProductContainer(new Product(description), new Price(priceValue),new MaxQuantity(maxValue),new Quantity(quantity));
    if(catalogue.contains(productContainer)) {
      return null;
    }
    for (ProductContainer each : catalogue) {

      if (each.product.getDescription().equals(description)) {
        return null;
      }
    }

    if (description.equals("")|| maxValue == 0 || quantity == 0 || maxValue < quantity) {
      return null;
    }

    catalogue.add(productContainer);
    return productContainer.product.getDescription();
  }

  public double sellProduct(String description, int quantity) {
    double price =0.0;
    if (quantity <= 0) {
      return 0.0;
    }
    for (ProductContainer each : catalogue) {
      if (each.product.getDescription().equals(description)) {

        if (checkProductConsistency(quantity, each)){
          return 0.0;
        }
        int updatedQuantity = each.quantity.value()-quantity;
        each.updateQuantity(updatedQuantity);
        price = quantity*each.price.value();
      }

    }

    return price;
  }

  private boolean checkProductConsistency(int quantity, ProductContainer each) {
    if (each.quantity.value() < quantity) {
      return true;
    }
    if (each.price.value() == 0) {
      return true;
    }
    return false;
  }

  private class ProductContainer {
    private final Product product;
    private final Price price;
    private final MaxQuantity maxQuantity;
    private Quantity quantity;

    public ProductContainer(Product product, Price price, MaxQuantity maxQuantity, Quantity quantity) {
      this.product = product;
      this.price = price;
      this.maxQuantity = maxQuantity;
      this.quantity = quantity;
    }
    public void updateQuantity(int quantity){

      this.quantity =new Quantity(quantity);
    }
  }
}
