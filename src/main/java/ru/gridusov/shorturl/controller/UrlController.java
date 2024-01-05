package ru.gridusov.shorturl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gridusov.shorturl.mappers.Mapper;
import ru.gridusov.shorturl.model.dto.UrlDto;
import ru.gridusov.shorturl.model.entity.Url;
import ru.gridusov.shorturl.service.UrlService;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/short-api")
public class UrlController {
    private final UrlService urlService;
    private final Mapper<Url, UrlDto> urlMapper;
    private final String serviceDomain = "http://localhost:8080/";

    @Autowired
    public UrlController(UrlService urlService, Mapper<Url, UrlDto> urlMapper){
        this.urlMapper = urlMapper;
        this.urlService = urlService;
    }

    @GetMapping("/{shortUrlKey}")
    public ResponseEntity<UrlDto> getFullUrl(@PathVariable String shortUrlKey){
        Optional<Url> requestedUrl = urlService.findByShortUrlKey(shortUrlKey);
        return requestedUrl.map(urlEntity -> {
            UrlDto urlDto = urlMapper.mapTo(urlEntity);
            return new ResponseEntity<>(urlDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/shorten")
    public ResponseEntity<UrlDto> createShortUrl(@RequestBody UrlDto urlDto) throws NoSuchAlgorithmException {
        log.info("Got a POSTMapping request for url: " + urlDto.getFullUrl());
        Url urlEntity = urlMapper.mapFrom(urlDto);
        log.info("Mapped DTO to entity");
        Url shortenedUrl = urlService.createShortUrl(urlEntity);
        log.info("Created shortened url: " + shortenedUrl.getShortUrlKey());
        return new ResponseEntity<>(urlMapper.mapTo(shortenedUrl), HttpStatus.CREATED);
    }

}
