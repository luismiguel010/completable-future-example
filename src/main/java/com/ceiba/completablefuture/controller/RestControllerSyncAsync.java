package com.ceiba.completablefuture.controller;

import com.ceiba.completablefuture.util.Async;
import com.ceiba.completablefuture.util.Sync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.IOException;

@RestController
@RequestMapping("/test")
public class RestControllerSyncAsync {

    @Autowired
    Async async;

    @Autowired
    Sync sync;

    @GetMapping("/async")
    public long testAsync() throws IOException {
        long initTime = System.currentTimeMillis();
        async.asyncThreeThreads();
        return System.currentTimeMillis() - initTime;
    }

    @GetMapping("/sync")
    public long testSync() throws IOException {
        long initTime = System.currentTimeMillis();
        sync.syncThreeCall();
        return System.currentTimeMillis() - initTime;
    }

}
