package com.app;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.junit.jupiter.api.Test;

public class JasyptTest {

    @Test
    public void jasyptTest() {
        String password = "asdasdasdsa!@#Fasfadsasdasdf123";

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor(); // 멀티코어 환경에선 StandardPBEStringEncryptor보다 성능이 좋다고 함.
        encryptor.setPoolSize(4);
        encryptor.setPassword(password);
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES"); // 암호화 알고리즘 설정

        String content = "and0LXRva2VuLXNlY3JldA=="; //암호화할 내용
        String encryptedContent = encryptor.encrypt(content);//암호화
        String decryptedContent = encryptor.decrypt(encryptedContent);

        System.out.println("Enc: " + encryptedContent + ", Dec: " + decryptedContent);

    }
}
