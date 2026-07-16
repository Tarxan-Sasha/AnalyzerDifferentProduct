package sasha.analizator.products.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import sasha.analizator.products.entites.Product;
import sasha.analizator.products.repositories.ProductRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class ParseAndSaveDataService {

        private final ProductRepository productRepository;

        public void parseAndSaveData() {

            try {
                if(productRepository.count() >= 1){
                    productRepository.deleteAll();
                    productRepository.deleteAll();
                }

                String link = "https://domigr.com.ua/ua/c-modelirovanie/c-paint/?filter_categories[0]=426&page=2";

                Document document = Jsoup.connect(link)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/149.0.0.0 Safari/537.36")
                        .get();
                Elements elements = document.select("div.product-anons-body");

                if(!elements.isEmpty()){

                    for(Element el : elements){
                        String name = el.selectFirst(".anons-name").text();
                        String price = el.selectFirst(".anons-price").text();
                        String exist = el.selectFirst(".anons-stock").text();

                        Product product = new Product();
                        product.setName(name);
                        product.setPrice(price);
                        product.setExist(exist);
                        product.setLink(link);

                        productRepository.save(product);

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
}
