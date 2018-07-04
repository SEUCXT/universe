package com.seu.universe.utils;

public class LikeRedisKeyUtil {

    private static final String SPLIT = ":";
    public static final String MESSAGE_SET = "MESSAGE_SET"; // message列表
    private static String MESSAGE_USER_LIKE_SET = "MESSAGE_USER_LIKE_SET_%s"; //
    private static String MESSAGE_USER_LIKE = "MESSAGE_USER_LIKE_%s_%s";
    private static String MESSAGE_LIKE_COUNTER = "MESSAGE_LIKE_%s_COUNTER";

    public static String getMessageSetKey() {
        return MESSAGE_SET;
    }

    public static String getMessageUserLikeSetKey(String messageId) {
        return String.format(MESSAGE_USER_LIKE_SET, messageId);
    }

    public static String getMessageUserLikeKey(String messageId, String userId) {
        return String.format(MESSAGE_USER_LIKE, messageId, userId);
    }

    public static String getMessageLikeCounterKey(String messageId) {
        return String.format(MESSAGE_LIKE_COUNTER, messageId);
    }

}
