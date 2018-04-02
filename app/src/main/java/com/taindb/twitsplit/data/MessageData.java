package com.taindb.twitsplit.data;

/**
 * Copyright (C) 2017, Taindb.
 *
 * @author taindb
 * @since 4/2/18
 */

public class MessageData {
    private String mMessage = " ";

    private String mTimestamp = " ";

    public MessageData(String message, String timestamp) {
        mMessage = message;
        mTimestamp = timestamp;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(String timestamp) {
        mTimestamp = timestamp;
    }

    public static MessageData createMessage(String message, String time) {
        return new MessageData(message, time);
    }
}
