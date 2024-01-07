package ru.gridusov.shorturl.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import ru.gridusov.shorturl.AbstractTest;
import ru.gridusov.shorturl.model.dto.UrlDto;
import ru.gridusov.shorturl.model.entity.Url;
import ru.gridusov.shorturl.service.UrlService;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Slf4j
public class UrlControllerTest extends AbstractTest {
    @Autowired
    private UrlController urlController;
    @Autowired
    private UrlService urlService;

    @Test
    public void createShortUrlTest() throws NoSuchAlgorithmException {
        UrlDto urlToSave = UrlDto.builder()
                .fullUrl("https://www.youtube.com/watch?v=cehTm_oSrqA")
                .build();
        ResponseEntity<UrlDto> createdUrlDto = urlController.createShortUrl(urlToSave);

        Optional<Url> foundUrl = urlService.findByFullUrl(urlToSave.getFullUrl());

        assert createdUrlDto.getStatusCode().is2xxSuccessful();
        assert foundUrl.isPresent();
        assert foundUrl.get().getFullUrl().equals(urlToSave.getFullUrl());
        assert foundUrl.get().getShortUrlKey() != null;
    }

    @Test
    public void createShortUrlForSameLinkTest() throws NoSuchAlgorithmException {
        UrlDto urlToSave = UrlDto.builder()
                .fullUrl("https://www.youtube.com/watch?v=cehTm_oSrqA")
                .build();
        ResponseEntity<UrlDto> createdUrlDto1 = urlController.createShortUrl(urlToSave);

        ResponseEntity<UrlDto> createdUrlDto2 = urlController.createShortUrl(urlToSave);
        log.info("createdUrlDto1: " + createdUrlDto1.toString());
        log.info("createdUrlDto2: " + createdUrlDto2.toString());

        assert createdUrlDto2.getStatusCode().is2xxSuccessful();
    }

}
