package ru.gridusov.shorturl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.gridusov.shorturl.service.UrlService;

@RestController
public class UrlController {
    private UrlService urlService;

    @Autowired
    public void setUrlService(UrlService urlService) {
        this.urlService = urlService;
    }
}
