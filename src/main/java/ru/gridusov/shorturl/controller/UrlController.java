package ru.gridusov.shorturl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gridusov.shorturl.mappers.Mapper;
import ru.gridusov.shorturl.model.dto.UrlDto;
import ru.gridusov.shorturl.model.entity.Url;
import ru.gridusov.shorturl.service.UrlService;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@RestController
@RequestMapping("/")
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
    public String getFullUrl(@PathVariable String shortUrlKey){
        Url url = urlService.findByShortUrlKey(shortUrlKey); //TODO make checks for expiration
        return this.serviceDomain.concat(url.getShortUrlKey());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createShortUrl(@RequestBody UrlDto urlDto) throws NoSuchAlgorithmException {
        Url urlEntity = urlMapper.mapFrom(urlDto);
        Url shortenedUrl = urlService.createShortUrl(urlEntity);
        return urlMapper.mapTo(shortenedUrl).getShortUrlKey();
    }

}
