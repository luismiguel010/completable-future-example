package com.ceiba.completablefuture.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class Async {

    @Autowired
    CallApi callAPI;

    public void asyncThreeThreads() throws IOException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            String response = "";
            try {
                response = callAPI.callAPI10SecondsForResponse();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            String response = "";
            try {
                response = callAPI.callAPI10SecondsForResponse();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        });
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            String response = "";
            try {
                response = callAPI.callAPI10SecondsForResponse();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        });
        Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));
    }
}
