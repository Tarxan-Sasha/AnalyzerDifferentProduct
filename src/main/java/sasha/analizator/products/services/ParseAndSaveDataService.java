package sasha.analizator.products.services;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ParseAndSaveDataService {

        public void parseAndSaveData() {
            try {
                Document document = Jsoup.connect("https://domigr.com.ua/ua/c-modelirovanie/c-paint/game-color-fire-dragons-8-colors-set-18-ml-72196.php")
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/149.0.0.0 Safari/537.36")
                        .get();

                Elements elements = document.select("div.new-price");

                log.info(elements.text());

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
}
