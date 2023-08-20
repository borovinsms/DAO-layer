package ru.netology.daolayer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.daolayer.repository.Repository;

import java.util.List;

@RestController
@RequestMapping("/")
public class WebController {

    private final Repository repository;

    public WebController(Repository repository) {
        this.repository = repository;
    }

    @GetMapping("/products/fetch-product")
    List<String> getProductName(@RequestParam String name) {
        return repository.getProductName(name);
    }
}
