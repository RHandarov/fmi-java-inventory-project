package fmi.java.inventory_project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dummy")
public class DummyController {
    @GetMapping("/hello-world")
    public ResponseEntity<String> sayHelloWorld() {
        return new ResponseEntity<String>("Hello World from dummy controller",
                HttpStatus.ACCEPTED);
    }

    @GetMapping("/hello/{name}")
    public String sayHelloToPerson(@PathVariable("name") String name) {
        return "Hello, " + name;
    }
}
