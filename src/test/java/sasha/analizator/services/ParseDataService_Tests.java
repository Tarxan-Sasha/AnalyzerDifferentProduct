package sasha.analizator.services;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sasha.analizator.products.entites.Product;
import sasha.analizator.products.services.ParseDataService;
import sasha.analizator.products.services.SaveParseData;

import java.io.InputStream;

import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class ParseDataService_Tests {

    @Mock
    private SaveParseData saveParseData;

    @InjectMocks
    private ParseDataService parseDataService;
    //Изменить имя на нормальное
    // В помке мб изменить <version>4.1.0</version> на <version>3.2.5</version>
    //И я закоментриолвал все что связано с FlyWay не помогло
    @Test
    public void parseDocument_rightWork_ElementsIsNotEmpty() throws Exception{

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("HtmlPageForParseTestExist16Elements.html");

        Assertions.assertNotNull(inputStream, "Файл не найден");

        Document document = Jsoup.parse(inputStream, "UTF-8", "");

        parseDataService.parseDocument(document,"testLink");

        verify(saveParseData, times(16)).saveDataFromParse(any(Product.class));

    }
    @Test
    public void parseDocument_ElementsIsEmpty() throws Exception{
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("HtmlPageForParseTestExist0Elements.html");
        Assertions.assertNotNull(inputStream, "Файл не найден");

        Document document = Jsoup.parse(inputStream, "UTF-8", "");

        parseDataService.parseDocument(document,"testLink");

        verify(saveParseData, never()).saveDataFromParse(any(Product.class));


    }

}
