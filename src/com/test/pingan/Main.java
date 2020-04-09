package com.test.pingan;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(5);
		int count = 50 ;
		while (count > 0 ) {
			service.execute(() -> {
				Station.sellSeats(1);
			});
			count --;
		}
		service.shutdown();
	}
	
}
