package ru.gridusov.shorturl.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gridusov.shorturl.model.dto.UrlDto;
import ru.gridusov.shorturl.model.entity.Url;
import ru.gridusov.shorturl.mappers.Mapper;

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
        return modelMapper.map(urlDto, Url.class);
    }
}
