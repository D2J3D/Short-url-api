package ru.gridusov.shorturl.service.impl;

import org.springframework.stereotype.Component;
import ru.gridusov.shorturl.service.UniqueKeyGenerating;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class LinkShortenerMD5 implements UniqueKeyGenerating {

    @Override
    public String generateHash(String input) throws NoSuchAlgorithmException {
        MessageDigest encryptor = MessageDigest.getInstance("MD5");
        byte[] inputDigest = encryptor.digest(input.getBytes());

        BigInteger no = new BigInteger(1, inputDigest);
        String inputHash = no.toString(16);
        while (inputHash.length() < 32) {
            inputHash = "0" + inputHash;
        }
        return inputHash;
    }

}
