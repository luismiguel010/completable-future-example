package com.ceiba.completablefuture;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class CompletablefutureApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void completableFutureTest1() throws InterruptedException, ExecutionException {
		Future<String> completableFuture = CompletableFuture.completedFuture("Hello");
		String result = completableFuture.get();
		Assert.assertEquals("Hello", result);
	}

	@Test
	public void completableFutureTest2() throws InterruptedException, ExecutionException{
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
		Assert.assertEquals("Hello", future.get());
	}

	@Test
	public void completableFutureParallelTest() throws InterruptedException, ExecutionException{
		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Ceiba");
		CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "Software");

		CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);
		combinedFuture.get();

		Assert.assertTrue(future1.isDone());
		Assert.assertTrue(future2.isDone());
		Assert.assertTrue(future3.isDone());
	}

	@Test
	public void completableFutureParallelWithReturnTest(){
		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Ceiba");
		CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "Software");

		String combined = Stream.of(future1, future2, future3)
				.map(CompletableFuture::join)
				.collect(Collectors.joining(" "));
		Assert.assertEquals("Hello Ceiba Software", combined);
	}

	public void completableFutureParallelWithApiRestTest() throws IOException {
		long timeInit = System.currentTimeMillis();
		URL url = new URL("http://moer1.mocklab.io/json/1");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");

		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
			String response = "";
			try {
				BufferedReader in = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return response;
		});
		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
			String response = "";
			try {
				BufferedReader in = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return response;
		});
		CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
			String response = "";
			try {
				BufferedReader in = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return response;
		});
		CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);
		long timeResponse = System.currentTimeMillis() - timeInit;
		System.out.println(timeResponse);
	}



}
