package com.service.impl;

import com.service.SendMessage;

public class SmsSend implements SendMessage {
    @Override
    public String send(String msg) {
        String to = "user send sms code: ";
        System.out.println(to + msg);
        return "success sms.";
    }
}
