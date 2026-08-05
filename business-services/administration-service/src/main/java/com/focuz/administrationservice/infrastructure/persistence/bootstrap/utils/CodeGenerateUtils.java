package com.focuz.administrationservice.infrastructure.persistence.bootstrap.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class CodeGenerateUtils {
    public static String generateCodeWithPrefix(String prefix) {
        int random = new Random().nextInt(10_000);
        return "%s-%s-%04d".formatted(prefix, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")),  random);
    }
}
