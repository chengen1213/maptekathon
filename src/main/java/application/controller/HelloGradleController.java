package controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@RestController
public class HelloGradleController {

    @RequestMapping(value = "/greeting",method = RequestMethod.GET)
    public String helloGradle() {
        return "Hello Gradle!";
    }

}