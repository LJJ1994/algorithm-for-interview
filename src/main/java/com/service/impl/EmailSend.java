package com.service.impl;

import com.service.SendMessage;

public class EmailSend implements SendMessage {
    @Override
    public String send(String msg) {
        String user = "user send email: ";
        System.out.println(user + msg);
        return "success email";
    }
}
