package ru.gridusov.shorturl.service;

import java.security.NoSuchAlgorithmException;

public interface UniqueKeyGenerating {
    String generateHash(String longInput) throws NoSuchAlgorithmException;
}
