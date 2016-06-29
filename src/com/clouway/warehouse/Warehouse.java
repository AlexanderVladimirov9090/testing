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

  /**
   * Selling product
   *
   * @param name         name of product that is trying to be sold.
   * @param quantityWant quantity that is trying to be sold.
   * @return remaining value in the warehouse.
   * @throws ZeroOrNegativeQuantityException if given quantity is zero or negative.
   */
  public int sellProduct(String name, int quantityWant) throws ZeroOrNegativeQuantityException {
    checkIfZeroOrNegative(quantityWant);
    for (ContainerProduct each : catalogue) {
      if (each.product.name().equals(name)) {
        return calculateRemainingValue(quantityWant, each);
      }
    }
    return 0;
  }

  /**
   * Adds product in the warehouse.
   *
   * @param name        of the product.
   * @param price       of product
   * @param quantity    of added product.
   * @param maxQuantity the maximum quantity that can be added in the warehouse.
   * @return Added product.
   * @throws QuantityLimitExceed     when quantity is over the max quantity in the warehouse.
   * @throws NegativeNumberException when trying to pass negative number to the method.
   */
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

  /**
   * Adds quantity to the warehouse.
   *
   * @param name     of the product.
   * @param quantity that trying to be added.
   * @return the sum of given quantity.
   * @throws QuantityLimitExceed     when passed quantity (and the sum of passed quantity and quantity in the warehouse) exceeded max quantity.
   * @throws NegativeNumberException when passed quantity is negative number.
   */
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

  /**
   * Sorts product catalogue by price.
   *
   * @return sorted catalogue by price.
   */
  public List<ContainerProduct> getCatalogueByPrice() {
    List<ContainerProduct> sortedCatalogue = catalogue;
    Collections.sort(sortedCatalogue, (product1, product2) -> Double.compare(product1.getPrice(), product2.getPrice()));
    return sortedCatalogue;
  }

  /**
   * Checks if passed quantity is Zero or negative number
   *
   * @param quantityWant this is the extracted quantity from the warehouse when sellProduct is called.
   * @throws ZeroOrNegativeQuantityException when quantity is zero or negative.
   */
  private void checkIfZeroOrNegative(int quantityWant) throws ZeroOrNegativeQuantityException {
    if (quantityWant <= 0) {
      throw new ZeroOrNegativeQuantityException();
    }
  }

  /**
   * Checks for null value of quantity.
   *
   * @param quantity checked quantity.
   */
  private void checkForNull(Integer quantity) {
    if (quantity == null) {
      throw new NullPointerException();
    }
  }


  /**
   * Checks if quantity is equal to maximum quantity in the warehouse.
   *
   * @param name of product.
   * @param each
   * @throws QuantityLimitExceed when try to add more quantity over the maximum quantity.
   */
  private void checkLimitExceeded(String name, ContainerProduct each) throws QuantityLimitExceed {
    if (each.quantity.getQuantity() == storage.get(name)) {
      throw new QuantityLimitExceed();
    }
  }

  /**
   * Checks if given quantity exceeds maximum quantity of warehouse.
   *
   * @param name     of product.
   * @param quantity to be added in warehouse.
   * @throws QuantityLimitExceed when maximum quantity is less than quantity to be added.
   */
  private void checkLimitExceeded(String name, Integer quantity) throws QuantityLimitExceed {
    if (storage.get(name) < quantity) {
      throw new QuantityLimitExceed();
    }
  }

  /**
   * Check if product is not in storage then updates storage with new product.
   *
   * @param name        of product
   * @param maxQuantity of product in storage of warehouse.
   */
  private void productNotInStorage(String name, Integer maxQuantity) {
    if (!storage.containsKey(name)) {
      updateStorage(name, maxQuantity);
    }
  }

  /**
   * Checks for negativity of given parameters.
   *
   * @param price       of product.
   * @param quantity    of product.
   * @param maxQuantity of storage in the warehouse for given product.
   * @throws NegativeNumberException
   */
  private void checkForNegativity(Double price, Integer quantity, Integer maxQuantity) throws NegativeNumberException {
    if (price < 0 || quantity < 0 || maxQuantity < 0) {
      throw new NegativeNumberException();
    }
  }

  /**
   * Checks for null value of given parametres.
   *
   * @param name        of product.
   * @param price       of product.
   * @param maxQuantity of storage in the warehouse for given prodcut.
   */
  private void checkForNull(String name, Double price, Integer maxQuantity) {
    if (name.isEmpty() || price.isNaN() || maxQuantity == 0) {
      throw new NullPointerException();
    }
  }

  /**
   * Calculates remaining value of product in the warehouse.
   *
   * @param quantityWant extracted quantity from warehouse.
   * @param each         product in the warehouse.
   * @return updated quantity.
   */
  private int calculateRemainingValue(int quantityWant, ContainerProduct each) {
    if (each.quantity.getQuantity() < quantityWant) {
      return each.quantity.getQuantity();
    }
    each.updateQuantity(new Quantity(each.quantity.getQuantity() - quantityWant));
    return each.quantity.getQuantity();
  }

  /**
   * Updates storage of the warehouse.
   *
   * @param name        of product.
   * @param maxQuantity of storage in the warehouse for the given product.
   */
  private void updateStorage(String name, Integer maxQuantity) {
    if (!(name.isEmpty() || maxQuantity <= 0)) {
      storage.put(name, maxQuantity);
    }
  }

  /**
   * This class is container for the product.
   */
  public class ContainerProduct {
    private final Product product;
    private final Price price;
    private Quantity quantity;

    public ContainerProduct(Product product, Price price, Quantity quantity) {
      this.product = product;
      this.price = price;
      this.quantity = quantity;
    }

    /**
     * Updates quantity to the product.
     *
     * @param quantity is the new quantity.
     */
    private void updateQuantity(Quantity quantity) {
      this.quantity = quantity;
    }

    /**
     * @return product name.
     */
    public String getNameOfProduct() {
      return product.name();
    }

    /**
     * Getting the product price.
     *
     * @return product price.
     */
    public double getPrice() {
      return price.getPrice();
    }

  }
}
