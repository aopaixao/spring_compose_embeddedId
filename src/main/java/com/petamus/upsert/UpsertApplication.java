package com.petamus.upsert;

import com.petamus.upsert.service.PriceIngestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@SpringBootApplication
public class UpsertApplication  implements CommandLineRunner{

	@Autowired
	private PriceIngestService priceIngestService;

	public static void main(String[] args) {
		SpringApplication.run(UpsertApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//priceIngestService.ingestRandomPrice1s();
		//priceIngestService.ingestRepeatedPrices();
		//priceIngestService.ingestRepeatedPrices2();
		priceIngestService.ingestRepeatedPrices3();
	}

	@PreDestroy
	public void onExit() {
		// log.info("Persisted {} records in {} sec", count.get(),
		// (System.currentTimeMillis()-startMillis)/1000);
	}
}
