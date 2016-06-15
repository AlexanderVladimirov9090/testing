package com.clouway.warehouse;

import org.junit.Test;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


/**
 * Created by clouway on 28.06.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
public class SellProductTest {

  @Test
  public void happyPath() throws QuantityLimitExceed, ZeroOrNegativeQuantityException, NegativeNumberException {
    Warehouse warehouse = new Warehouse();
    warehouse.addProduct("Apple", 2.0, 3, 50);
    assertThat(warehouse.sellProduct("Apple", 1), is(equalTo(2)));
  }

  @Test(expected = QuantityLimitExceed.class)
  public void addProductTwice() throws QuantityLimitExceed, ZeroOrNegativeQuantityException, NegativeNumberException {
    Warehouse warehouse = new Warehouse();
    warehouse.addProduct("Food", 3.3, 3, 3);
    warehouse.addProduct("Food", 3.3, 4, 4);

  }

  @Test(expected = NullPointerException.class)
  public void addProductWithNoName() throws QuantityLimitExceed, ZeroOrNegativeQuantityException, NegativeNumberException {
    Warehouse warehouse = new Warehouse();
    warehouse.addProduct("", 3.4, 2, 13);
    assertThat(warehouse.sellProduct("", 2), is(equalTo(0)));
  }


  @Test(expected = NullPointerException.class)
  public void addProductWithNullPrice() throws QuantityLimitExceed, NegativeNumberException {
    Warehouse warehouse = new Warehouse();
    warehouse.addProduct("Good", null, 2, 3);
  }

  @Test(expected = NullPointerException.class)
  public void addProductWithNullQuantity() throws QuantityLimitExceed, NegativeNumberException {
    Warehouse warehouse = new Warehouse();
    warehouse.addProduct("ER", 4.4, null, 13);

  }

  @Test(expected = NullPointerException.class)
  public void addProductWithNullMaxQuantity() throws QuantityLimitExceed, NegativeNumberException {
    Warehouse warehouse = new Warehouse();
    warehouse.addProduct("SleepingPills", 3.4, 3, null);
  }

  @Test(expected = NullPointerException.class)
  public void addQuantityWithNullQuantity() throws QuantityLimitExceed, NegativeNumberException {
    Warehouse warehouse = new Warehouse();
    warehouse.addProduct("Hello Kitty Doll", 3.4, 3, 3);
    warehouse.addQuantity("Hello Kitty Doll", null);
  }

  @Test(expected = NegativeNumberException.class)
  public void addProductWithNegativePrice() throws QuantityLimitExceed, NegativeNumberException {
    Warehouse warehouse = new Warehouse();
    warehouse.addProduct("Name", -1.0, 3, 3);
  }

  @Test(expected = NegativeNumberException.class)
  public void addProductWithNegativeQuantity() throws QuantityLimitExceed, NegativeNumberException {
    Warehouse warehouse = new Warehouse();
    warehouse.addProduct("Mouse", 3.0, -3, 1);
  }

  @Test(expected = NegativeNumberException.class)
  public void addProductWithNegativeMaxQuantity() throws QuantityLimitExceed, NegativeNumberException {
    Warehouse warehouse = new Warehouse();
    warehouse.addProduct("Zipper", 3.0, 3, -3);
  }

  @Test(expected = QuantityLimitExceed.class)
  public void quantityLimitExceed() throws QuantityLimitExceed, NegativeNumberException {
    Warehouse warehouse = new Warehouse();
    warehouse.addProduct("Paprika", 22.3, 51, 3);
  }

  @Test(expected = QuantityLimitExceed.class)
  public void addProductThenAddMoreQuantityLimitExceed() throws QuantityLimitExceed, NegativeNumberException {
    Warehouse warehouse = new Warehouse();
    warehouse.addProduct("Salami", 33.4, 9, 9);
    warehouse.addQuantity("Salami", 1);
  }

  @Test
  public void sellProductTwice() throws QuantityLimitExceed, ZeroOrNegativeQuantityException, NegativeNumberException {
    Warehouse warehouse = new Warehouse();
    warehouse.addProduct("Apple", 3.3, 3, 3);
    warehouse.sellProduct("Apple", 1);
    assertThat(warehouse.sellProduct("Apple", 1), is(equalTo(1)));
  }

  @Test
  public void sellProductWithZeroPrice() throws QuantityLimitExceed, ZeroOrNegativeQuantityException, NegativeNumberException {
    Warehouse warehouse = new Warehouse();
    warehouse.addProduct("CatFood", 0.0, 3, 3);
    assertThat(warehouse.sellProduct("CatFood", 1), is(equalTo(2)));
  }

  @Test
  public void sellProductWhitZeroQuantity() throws QuantityLimitExceed, ZeroOrNegativeQuantityException, NegativeNumberException {
    Warehouse warehouse = new Warehouse();
    warehouse.addProduct("Shoes", 3.0, 0, 3);
    assertThat(warehouse.sellProduct("Shoes", 3), is(equalTo(0)));
  }

  @Test(expected = NullPointerException.class)
  public void sellProductWithZeroMaxQuantity() throws QuantityLimitExceed, ZeroOrNegativeQuantityException, NegativeNumberException {
    Warehouse warehouse = new Warehouse();
    warehouse.addProduct("Cheese", 3.3, 1, 0);
    warehouse.sellProduct("Cheese", 1);
  }

  @Test(expected = ZeroOrNegativeQuantityException.class)
  public void sellProductWithZeroQuantityWant() throws QuantityLimitExceed, ZeroOrNegativeQuantityException, NegativeNumberException {
    Warehouse warehouse = new Warehouse();
    warehouse.addProduct("Goo", 4.4, 9, 9);
    assertThat(warehouse.sellProduct("Goo", 0), is(equalTo(0)));
  }

  @Test(expected = ZeroOrNegativeQuantityException.class)
  public void sellProductWithNegativeQuantityWant() throws QuantityLimitExceed, ZeroOrNegativeQuantityException, NegativeNumberException {
    Warehouse warehouse = new Warehouse();
    warehouse.addProduct("OkProduct", 3.3, 8, 8);
    warehouse.sellProduct("OkProduct", -1);
  }


  @Test
  public void sellProductWithQuantityLessThanWant() throws QuantityLimitExceed, ZeroOrNegativeQuantityException, NegativeNumberException {
    Warehouse warehouse = new Warehouse();
    warehouse.addProduct("Door", 33.3, 3, 4);
    assertThat(warehouse.sellProduct("Door", 4), is(equalTo(3)));
  }

  @Test
  public void addSellAddQuantitySellProduct() throws QuantityLimitExceed, NegativeNumberException, ZeroOrNegativeQuantityException {
    Warehouse warehouse = new Warehouse();
    warehouse.addProduct("GoodProduct", 3.3, 4, 99);
    warehouse.sellProduct("GoodProduct", 2);
    warehouse.addQuantity("GoodProduct", 3);
    assertThat(warehouse.sellProduct("GoodProduct", 2), is(equalTo(3)));
  }


  @Test
  public void getCatalogueByPrice() throws QuantityLimitExceed, NegativeNumberException {
    Warehouse warehouse = new Warehouse();
    warehouse.addProduct("SeaFood", 3.3, 1, 4);
    warehouse.addProduct("WhatIsThisProduct", 3.4, 1, 4);
    warehouse.addProduct("Doner", 4.5, 2, 4);
    assertThat(warehouse.getCatalogueByPrice().get(0).product.name(), is(equalTo("SeaFood")));
    assertThat(warehouse.getCatalogueByPrice().get(1).product.name(), is(equalTo("WhatIsThisProduct")));
    assertThat(warehouse.getCatalogueByPrice().get(2).product.name(), is(equalTo("Doner")));
  }

  @Test
  public void getCatalogueEmpty() {
    Warehouse warehouse = new Warehouse();
    assertThat(warehouse.getCatalogueByPrice(), is(empty()));
  }

  @Test
  public void getCatalogueByPriceWithSamePrice() throws QuantityLimitExceed, NegativeNumberException {
    Warehouse warehouse = new Warehouse();
    warehouse.addProduct("Food", 4.4, 3, 3);
    warehouse.addProduct("NotFood", 4.4, 3, 3);
    warehouse.addProduct("ForgottenFood", 3.3, 3, 3);
    assertThat(warehouse.getCatalogueByPrice().get(0).product.name(), is(equalTo("ForgottenFood")));
    assertThat(warehouse.getCatalogueByPrice().get(1).product.name(), is(equalTo("Food")));
    assertThat(warehouse.getCatalogueByPrice().get(2).product.name(), is(equalTo("NotFood")));
  }


  private class Warehouse {
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


    private class ContainerProduct {
      private final Product product;
      private final Price price;
      private Quantity quantity;

      public ContainerProduct(Product product, Price price, Quantity quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
      }

      public void updateQuantity(Quantity quantity) {
        this.quantity = quantity;
      }

      public double getPrice() {
        return price.getPrice();
      }

    }
  }

  private class Product {
    private final String name;

    public Product(String name) {
      this.name = name;
    }


    public String name() {
      return name;
    }
  }

  private class Price {
    private final Double value;

    public Price(Double value) {
      this.value = value;
    }

    public Double getPrice() {
      return value;

    }
  }

  private class Quantity {
    private final int quantity;

    public Quantity(int quantity) {
      this.quantity = quantity;
    }

    public int getQuantity() {
      return quantity;
    }
  }

  private class QuantityLimitExceed extends Exception {
    public QuantityLimitExceed() {
      super("Quantity exceed");
    }
  }

  private class ZeroOrNegativeQuantityException extends Exception {
    public ZeroOrNegativeQuantityException() {
      super("Can`t sell zero or negative quantity from product");
    }
  }

  private class NegativeNumberException extends Exception {
    public NegativeNumberException() {
      super("Can`t be negative number.");
    }
  }
}

