package com.sdn.prj.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class PromocodeGenerator {
    public String generatePromoCode() {
        String letters = RandomStringUtils.randomAlphabetic(3);
        String numbers = RandomStringUtils.randomNumeric(3);
        return letters + numbers;
    }
}