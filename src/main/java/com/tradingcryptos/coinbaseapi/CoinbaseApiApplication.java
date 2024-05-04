package com.tradingcryptos.coinbaseapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoinbaseApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(CoinbaseApiApplication.class, args);

		System.out.println("coin base running ...");

	}

}
