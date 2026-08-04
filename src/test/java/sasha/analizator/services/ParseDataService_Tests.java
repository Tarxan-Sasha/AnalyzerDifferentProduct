package sasha.analizator.services;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sasha.analizator.products.entites.Product;
import sasha.analizator.products.services.ParseDataService;
import sasha.analizator.products.services.SaveParseData;

import java.io.File;
import java.io.InputStream;

import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class ParseDataService_Tests {

    ParseDataService parseDataServicel;

    @Mock
    SaveParseData saveParseData;

    //Изменить имя на нормальное
    // В помке мб изменить <version>4.1.0</version> на <version>3.2.5</version>
    //И я закоментриолвал все что связано с FlyWay не помогло
    @Test
    public void parseDocument() throws Exception{

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("HtmlPageForParseTest.html");

        Assertions.assertNotNull(inputStream, "Файл не найден");

        Document document = Jsoup.parse(inputStream, "UTF-8", "");

        parseDataServicel.parseDocument(document,"testLink");

        verify(saveParseData, times(16)).saveDataFromParse(any(Product.class));

    }

}
