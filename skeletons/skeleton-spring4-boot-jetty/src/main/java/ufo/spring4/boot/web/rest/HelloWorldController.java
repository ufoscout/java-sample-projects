package ufo.spring4.boot.web.rest;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping("/hello")
    String characters() {
        return "Hello World is " + new Date();
    }

}
