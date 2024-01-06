package ru.gridusov.shorturl.mappers.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gridusov.shorturl.model.dto.UrlDto;
import ru.gridusov.shorturl.model.entity.Url;
import ru.gridusov.shorturl.mappers.Mapper;

import java.sql.Timestamp;
@Slf4j
@Component
public class UrlMapper implements Mapper<Url, UrlDto> {
    private final ModelMapper modelMapper;

    @Autowired
    public UrlMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public UrlDto mapTo(Url url) {
        return modelMapper.map(url, UrlDto.class);
    }

    @Override
    public Url mapFrom(UrlDto urlDto) {
        log.info("Mapping from dto with address: " + urlDto.getFullUrl() + " has started");
        Url url =  modelMapper.map(urlDto, Url.class);
        url = setCreatedAt(url);
        log.info("createdAt field has been set as: " + url.getCreatedAt().toString());
        url = setExpirationDate(url);
        log.info("expirationDate field has been set as: " + url.getExpirationDate().toString());
        return url;
    }
    private Url setExpirationDate(Url url){
        if (url.getExpirationDate() == null){
            url.setExpirationDate(Timestamp.valueOf(url.getCreatedAt().toLocalDateTime().plusWeeks(1)));
        }
        return url;
    }
    private Url setCreatedAt(Url url){
        if (url.getCreatedAt() == null){
            url.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        }
        return url;
    }
}
