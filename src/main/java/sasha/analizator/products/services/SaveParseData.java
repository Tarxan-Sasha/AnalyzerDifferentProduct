package sasha.analizator.products.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sasha.analizator.products.entites.Product;
import sasha.analizator.products.repositories.ProductRepository;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaveParseData {
    private final ProductRepository productRepository;

    @Transactional
    public void saveDataFromParse(Product newProduct){
        Optional<Product> oldProduct = productRepository.findByNameAndLink(newProduct.getName(), newProduct.getLink());

        if(oldProduct.isPresent()) {
            if (!(oldProduct.get().getPrice().equals(newProduct.getPrice()))) {
                oldProduct.get().setPrice(newProduct.getPrice());
            }
        }else{
            productRepository.save(newProduct);
        }
    }
}
