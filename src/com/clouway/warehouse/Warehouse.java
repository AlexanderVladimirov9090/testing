package com.clouway.warehouse;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clouway on 29.06.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
class Warehouse {
   private List<ContainerProduct> catalogue = new ArrayList<>();
  private Map<String, Integer> storage = new LinkedHashMap<>();

  public int sellProduct(String name, int quantityWant) throws ZeroOrNegativeQuantityException {
    if (quantityWant <= 0) {
      throw new ZeroOrNegativeQuantityException();
    }
    for (ContainerProduct each : catalogue) {
      if (each.product.name().equals(name)) {
        return calculateRemainingValue(quantityWant, each);
      }
    }
    return 0;
  }

  public Product addProduct(String name, Double price, Integer quantity, Integer maxQuantity) throws QuantityLimitExceed, NegativeNumberException {
    checkForNull(name, price, maxQuantity);
    checkForNegativity(price, quantity, maxQuantity);
    productNotInStorage(name, maxQuantity);
    checkLimitExceeded(name, quantity);

    for (ContainerProduct each : catalogue) {
      if (each.product.name().equals(name)) {
        each.updateQuantity(new Quantity(quantity + each.quantity.getQuantity()));
      }
    }
    ContainerProduct container = new ContainerProduct(new Product(name), new Price(price), new Quantity(quantity));
    catalogue.add(container);

    return container.product;
  }

  public Integer addQuantity(String name, Integer quantity) throws QuantityLimitExceed, NegativeNumberException {
    checkForNull(quantity);

    for (ContainerProduct each : catalogue) {
      if (each.product.name().equals(name)) {
        checkLimitExceeded(name, each);
        each.updateQuantity(new Quantity(each.quantity.getQuantity() + quantity));

        return each.quantity.getQuantity();
      }
    }

    return 0;
  }

  private void checkForNull(Integer quantity) {
    if (quantity == null) {
      throw new NullPointerException();
    }
  }

  private void checkLimitExceeded(String name, ContainerProduct each) throws QuantityLimitExceed {
    if (each.quantity.getQuantity() == storage.get(name)) {
      throw new QuantityLimitExceed();
    }
  }


  public List<ContainerProduct> getCatalogueByPrice() {
    List<ContainerProduct> unsortedCatalogue = catalogue;
    Collections.sort(unsortedCatalogue, (product1, product2) -> Double.compare(product1.getPrice(), product2.getPrice()));
    return unsortedCatalogue;
  }

  private void checkLimitExceeded(String name, Integer quantity) throws QuantityLimitExceed {
    if (storage.get(name) < quantity) {
      throw new QuantityLimitExceed();
    }
  }

  private void productNotInStorage(String name, Integer maxQuantity) {
    if (!storage.containsKey(name)) {
      updateStorage(name, maxQuantity);
    }
  }

  private void checkForNegativity(Double price, Integer quantity, Integer maxQuantity) throws NegativeNumberException {
    if (price < 0 || quantity < 0 || maxQuantity < 0) {
      throw new NegativeNumberException();
    }
  }

  private void checkForNull(String name, Double price, Integer maxQuantity) {
    if (name.isEmpty() || price.isNaN() || maxQuantity == 0) {
      throw new NullPointerException();
    }
  }

  private int calculateRemainingValue(int quantityWant, ContainerProduct each) {
    if (each.quantity.getQuantity() < quantityWant) {
      return each.quantity.getQuantity();
    }
    each.updateQuantity(new Quantity(each.quantity.getQuantity() - quantityWant));
    return each.quantity.getQuantity();
  }

  private void updateStorage(String name, Integer maxQuantity) {
    if (!(name.isEmpty() || maxQuantity <= 0)) {
      storage.put(name, maxQuantity);
    }
  }


  public class ContainerProduct {
    private final Product product;
    private final Price price;
    private Quantity quantity;

    public ContainerProduct(Product product, Price price, Quantity quantity) {
      this.product = product;
      this.price = price;
      this.quantity = quantity;
    }

    private void updateQuantity(Quantity quantity) {
      this.quantity = quantity;
    }

    public String getNameOfProduct(){
      return product.name();
    }
    public double getPrice() {
      return price.getPrice();
    }

  }
}
