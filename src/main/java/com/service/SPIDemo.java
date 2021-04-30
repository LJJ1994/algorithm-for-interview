package com.service;

import java.util.ServiceLoader;

public class SPIDemo {
    public static void main(String[] args) {
        ServiceLoader<SendMessage> messages = ServiceLoader.load(SendMessage.class);
        for (SendMessage message : messages) {
            message.send("LJJ, GO TO HELL!");
        }
    }
}
