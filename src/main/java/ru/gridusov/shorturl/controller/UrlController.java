package ru.gridusov.shorturl.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.Getter;
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
    @Getter
    private final UrlService urlService;
    private final Mapper<Url, UrlDto> urlMapper;

    @Autowired
    public UrlController(UrlService urlService, Mapper<Url, UrlDto> urlMapper){
        this.urlMapper = urlMapper;
        this.urlService = urlService;
    }

    @GetMapping("/{shortUrlKey}")
    public void getFullUrlAndRedirect(HttpServletResponse httpServletResponse, @PathVariable String shortUrlKey){
        Optional<Url> requestedUrl = urlService.findByShortUrlKey(shortUrlKey);
        if (requestedUrl.isPresent()){
            String fullUrl = requestedUrl.get().getFullUrl();
            httpServletResponse.setHeader("Location", fullUrl);
            httpServletResponse.setStatus(302);
        }
    }

    @GetMapping("/full/{shortUrlKey}")
    public ResponseEntity<UrlDto> getFullUrl(@PathVariable String shortUrlKey){
        Optional<Url> requestedUrl = urlService.findByShortUrlKey(shortUrlKey);
        return requestedUrl.map(urlEntity -> {
            UrlDto urlDto = urlMapper.mapTo(urlEntity);
            return new ResponseEntity<>(urlDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/full/view/{shortUrlKey}")
    public ResponseEntity<String> getFullUrlString(@PathVariable String shortUrlKey){
        Optional<Url> requestedUrl = urlService.findByShortUrlKey(shortUrlKey);
        return requestedUrl.map(urlEntity -> {
            UrlDto urlDto = urlMapper.mapTo(urlEntity);
            return new ResponseEntity<>(urlDto.getFullUrl(), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/shorten")
    public ResponseEntity<UrlDto> createShortUrl(@Valid @RequestBody UrlDto urlDto) throws NoSuchAlgorithmException {
        Url urlEntity = urlMapper.mapFrom(urlDto);
        Url shortenedUrl = urlService.createShortUrl(urlEntity);
        return new ResponseEntity<>(urlMapper.mapTo(shortenedUrl), HttpStatus.CREATED);
    }
}
