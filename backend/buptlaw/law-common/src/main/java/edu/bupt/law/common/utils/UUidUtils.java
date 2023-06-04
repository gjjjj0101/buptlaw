package edu.bupt.law.common.utils;

import java.util.UUID;

public class UUidUtils {
    public static String generateUuid(){
        return UUID.randomUUID().toString();
    }
}
