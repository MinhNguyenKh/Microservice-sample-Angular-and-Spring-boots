package com.minhnk.query.service;

import org.springframework.stereotype.Service;

@Service
public class QueryService {

    public String getMessageFromEvenBus(String message) {
        System.out.println(message);
        return message;
    }
}
