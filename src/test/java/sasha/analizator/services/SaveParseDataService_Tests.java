package sasha.analizator.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sasha.analizator.products.entites.Product;
import sasha.analizator.products.repositories.ProductRepository;
import sasha.analizator.products.services.SaveParseData;

import java.util.Optional;

import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class SaveParseDataService_Tests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private SaveParseData saveParseData;

    @Test
    public void saveDataFromParse_completeSaveNewProduct(){

        Product newProduct = new Product();
        newProduct.setName("TestName");
        newProduct.setPrice("100грн");
        newProduct.setLink("TestLink");

        when(productRepository.findByNameAndLink(newProduct.getName(), newProduct.getLink())).thenReturn(Optional.empty());

        saveParseData.saveDataFromParse(newProduct);

        verify(productRepository, times(1)).findByNameAndLink(newProduct.getName(), newProduct.getLink());
        verify(productRepository, times(1)).save(newProduct);

    }
    @Test
    public void saveDataFromParse_completeUpdatePriseForExistProduct(){
        Product newProduct = new Product();
        newProduct.setName("TestName");
        newProduct.setPrice("100грн");
        newProduct.setLink("TestLink");

        Product oldProduct = new Product();
        oldProduct.setPrice("10грн");
        Optional<Product> optionalOldProduct = Optional.of(oldProduct);


        when(productRepository.findByNameAndLink(newProduct.getName(), newProduct.getLink())).thenReturn(Optional.of(oldProduct));;

        saveParseData.saveDataFromParse(newProduct);

        Assertions.assertTrue(optionalOldProduct.isPresent());
        Assertions.assertEquals("100грн", optionalOldProduct.get().getPrice());

        verify(productRepository, times(1)).findByNameAndLink(newProduct.getName(), newProduct.getLink());
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    public void saveDataFromParse_completeWhenProductExistAndHaveLastPrice(){
        Product newProduct = new Product();
        newProduct.setName("TestName");
        newProduct.setPrice("100грн");
        newProduct.setLink("TestLink");

        Product oldProduct = new Product();
        oldProduct.setPrice("100грн");
        Optional<Product> optionalOldProduct = Optional.of(oldProduct);

        when(productRepository.findByNameAndLink(newProduct.getName(), newProduct.getLink())).thenReturn(Optional.of(oldProduct));;

        saveParseData.saveDataFromParse(newProduct);

        Assertions.assertEquals(oldProduct.getPrice(), optionalOldProduct.get().getPrice());

        verify(productRepository, times(1)).findByNameAndLink(newProduct.getName(), newProduct.getLink());
        verify(productRepository, never()).save(any(Product.class));

    }

}
