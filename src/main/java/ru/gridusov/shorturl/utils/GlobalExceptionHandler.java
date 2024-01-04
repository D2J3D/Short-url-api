package ru.gridusov.shorturl.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.gridusov.shorturl.exceptions.AppError;
import ru.gridusov.shorturl.exceptions.NotEnoughSpaceError;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<AppError> catchNotEnoughSpaceError(NotEnoughSpaceError error){
        return new ResponseEntity<>(new AppError(HttpStatus.GATEWAY_TIMEOUT.value(), error.getMessage()), HttpStatus.GATEWAY_TIMEOUT);
    }
}
