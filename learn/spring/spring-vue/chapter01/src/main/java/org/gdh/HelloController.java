package org.gdh;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HuGuodong on 2020/7/19.
 */
@RestController
public class HelloController {

  @GetMapping("/hello")
  public String hello() {
    return "hello 小北鼻";
  }
}
