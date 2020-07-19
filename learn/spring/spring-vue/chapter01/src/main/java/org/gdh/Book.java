package org.gdh;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by HuGuodong on 2020/7/19.
 */
@Component
@ConfigurationProperties(prefix="book")
public class Book {
  private String name;
  private String author;
  private double price;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Book{" + "name='" + name + '\'' + ", author='" + author + '\'' + ", price=" + price
        + '}';
  }
}
