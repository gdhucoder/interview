package org.gdh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HuGuodong on 2020/7/19.
 */
@RestController
public class BookController {

  @Autowired
  Book book;

  @GetMapping("/book")
  public String book() {
    System.out.println(book);
    return book.toString();
  }
}
