package sasha.analizator.products.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sasha.analizator.products.entites.Product;
import sasha.analizator.products.repositories.ProductRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product getProductById(int id){
        log.info("2");
        return productRepository.findById(id).orElseThrow();
    }
}
