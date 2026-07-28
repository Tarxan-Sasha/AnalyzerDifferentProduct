package sasha.analizator.products.services;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//Этот класс запускает один раз метод run АВТОМАТИЧЕСКИ при запуске приложения.
@Component
@RequiredArgsConstructor
public class StarterRunParsing implements CommandLineRunner {

    private final ParseDataService parseAndSaveDataService;

    @Override
    public void run(String... args) throws Exception {
        parseAndSaveDataService.parseData();
    }

}
