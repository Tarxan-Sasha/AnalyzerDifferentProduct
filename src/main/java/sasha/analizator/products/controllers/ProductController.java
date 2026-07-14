package sasha.analizator.products.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sasha.analizator.products.entites.Product;
import sasha.analizator.products.services.ProductService;

@Slf4j
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Product> getById(@PathVariable int id){
        log.info("1");
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }
}
