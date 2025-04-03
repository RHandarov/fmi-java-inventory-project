package fmi.java.inventory_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dummy")
public class DummyController {
    @GetMapping("/hello-world")
    public String sayHelloWorld() {
        return "Hello World from dummy controller";
    }
}
