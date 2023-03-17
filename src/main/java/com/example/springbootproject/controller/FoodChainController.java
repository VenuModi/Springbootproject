package com.example.springbootproject.controller;


import com.example.springbootproject.repository.FoodChainRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foodchain")
public class FoodChainController {

    private final FoodChainRepository foodChainRepository{

    }
}
