package ru.gridusov.shorturl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gridusov.shorturl.dto.UrlDto;
import ru.gridusov.shorturl.entity.Url;
import ru.gridusov.shorturl.service.UrlService;

@RestController
@RequestMapping("/")
public class UrlController {
    private final UrlService urlService;
    @Autowired
    public UrlController(UrlService urlService){
        this.urlService = urlService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Url createShortUrl(@RequestBody UrlDto urlDto){
        return null;
    }

}
