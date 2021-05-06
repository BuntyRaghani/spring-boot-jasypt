package com.example.springbootjasypt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringBootJasyptController {

    @Value("${some.encrypted.property}")
    private String someProperty;

    @GetMapping("/getDecryptedValue")
    public String sendDecryptedValue() {
        return "Decrypted value: " + someProperty;
    }
}
