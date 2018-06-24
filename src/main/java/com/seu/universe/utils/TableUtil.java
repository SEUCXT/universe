package com.seu.universe.utils;

public class TableUtil {

    public static String getTableName(String email) {
        StringBuilder tableName = new StringBuilder();
        String tmp = email.substring(0, email.lastIndexOf('@'));
        tableName.append("t_").append(tmp).append("_message");
        return tableName.toString();
    }
}
