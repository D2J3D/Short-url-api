package ru.gridusov.shorturl.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gridusov.shorturl.model.entity.Url;
import ru.gridusov.shorturl.service.UrlService;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

@Slf4j
public class UrlControllerTest extends AbstractTest{
    @Autowired
    private UrlService urlService;
    @Autowired
    private final LinkShortenerMD5 generator = new LinkShortenerMD5();


    @Test
    public void createShortUrlTest() throws NoSuchAlgorithmException {
        Url url = Url.builder()
                .id(1L)
                .fullUrl("https://habr.com/ru/articles/")
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .expirationDate(new Timestamp(System.currentTimeMillis()))
                .key(null)
                .build();
        Url urlWithKey = urlService.createShortUrl(url);
        assert urlWithKey.getKey() != null;
        assert urlWithKey.getKey().length() == 7;
    }


    @Test
    public void findByKeyCacheEnabledTest() throws NoSuchAlgorithmException {
        Url firstUrl = Url.builder()
                .id(2L)
                .fullUrl("https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-cache/3.2.0")
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .expirationDate(new Timestamp(System.currentTimeMillis()))
                .key(null)
                .build();
        firstUrl = urlService.createShortUrl(firstUrl);

        Url secondUrl = Url.builder()
                .id(3L)
                .fullUrl("https://ya.ru/search/")
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .expirationDate(new Timestamp(System.currentTimeMillis()))
                .key(null)
                .build();
        secondUrl = urlService.createShortUrl(secondUrl);

        getAndPrint(firstUrl.getKey());
        getAndPrint(secondUrl.getKey());
        getAndPrint(firstUrl.getKey());
        getAndPrint(secondUrl.getKey());
    }

    private void getAndPrint(String key){
        log.info("user found: " + urlService.findByKey(key));
    }

}
