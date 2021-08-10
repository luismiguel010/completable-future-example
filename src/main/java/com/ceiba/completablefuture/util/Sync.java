package com.ceiba.completablefuture.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Sync {

    @Autowired
    CallApi callAPI;

    public void syncThreeCall() throws IOException {
        callAPI.callAPI10SecondsForResponse();
        callAPI.callAPI10SecondsForResponse();
        callAPI.callAPI10SecondsForResponse();
    }
}
