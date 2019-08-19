package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/greeting")
public class HelloGradleController {

    @GetMapping
    public String helloGradle() {
        return "Hello Gradle!";
    }

}