package ru.gridusov.shorturl.model.entity;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gridusov.shorturl.AbstractTest;
import ru.gridusov.shorturl.repository.UrlRepository;

import java.sql.Timestamp;

@Slf4j
public class UrlTest extends AbstractTest {
    @Autowired
    private UrlRepository urlRepository;

    @Test
    public void urlCreationTest(){
        Url url = Url.builder()
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .expirationDate(new Timestamp(System.currentTimeMillis()))
                .fullUrl("https://url")
                .build();
        Url savedUrl = urlRepository.save(url);
        log.info("Url entity: " + url.toString());
        log.info("Saved url: " + savedUrl.toString());

    }
}
