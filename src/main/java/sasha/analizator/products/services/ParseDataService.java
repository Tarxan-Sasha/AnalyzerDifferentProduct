package sasha.analizator.products.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import sasha.analizator.products.entites.Product;

@Service
@Slf4j
@RequiredArgsConstructor
public class ParseDataService {

    /*
        Сделай тест на этот метод.
        Парсировку затестить
        И в частности находит ли обьект?, а если нет то доабвляет ли?, а если находит обновляет ли?

        Консультация по этому проекту тут: https://gemini.google.com/app/42cc41bd19df9114?hl=ru

     */
        private final SaveParseData saveParseData;

        public void parseData() {

            try {
                String link = "https://domigr.com.ua/ua/c-modelirovanie/c-paint/?filter_categories[0]=426&page=2";

                Document document = Jsoup.connect(link)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/149.0.0.0 Safari/537.36")
                        .get();
                parseDocument(document, link);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void parseDocument(Document document, String link){
            Elements elements = document.select("div.product-anons-body");

            if(!elements.isEmpty()){

                for(Element el : elements){
                    String name = el.selectFirst(".anons-name").text();
                    String price = el.selectFirst(".anons-price").text();
                    String exist = el.selectFirst(".anons-stock").text();

                    Product newProduct = new Product();
                    newProduct.setName(name);
                    newProduct.setPrice(price);
                    newProduct.setExist(exist);
                    newProduct.setLink(link);

                    saveParseData.saveDataFromParse(newProduct);
                }
            }

        }
}




















