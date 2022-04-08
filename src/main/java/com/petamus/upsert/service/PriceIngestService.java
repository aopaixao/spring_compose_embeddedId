package com.petamus.upsert.service;

import com.petamus.upsert.entity.Price;
import com.petamus.upsert.entity.PriceKey;
import com.petamus.upsert.repository.PriceRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

@Slf4j
@Service
public class PriceIngestService {

    @Autowired
    private PriceRepo priceRepo;

    private AtomicBoolean keepIngesting = new AtomicBoolean(true);

    public void ingestRandomPriceBatches(Supplier<List<Price>> randomPriceBatchSupplier){
        int batchNo = 1;

        while (keepIngesting.get()){
            long starMillis = System.currentTimeMillis();

            List<Price> batch = randomPriceBatchSupplier.get();

            priceRepo.saveAll(batch);

            log.info("Persisted batch {} ( {} ) for price in {} sec", batchNo, batch.size(), (System.currentTimeMillis() - starMillis) / 1000);

            batchNo++;
        }
    }

    public void ingestRandomPrice1s() {
        Random r = new Random(0);

        int batch = 1;
        while (true) {
            List<Price> l = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                l.add(new Price(new PriceKey("abc" + r.nextInt(1000), String.valueOf(r.nextInt(10))), r.nextDouble()));
            }
            long startMillis = System.currentTimeMillis();
            priceRepo.saveAll(l);
            log.info("Persisted batch {} ( 1000 ) for price1 in {} sec", batch,
                    (System.currentTimeMillis() - startMillis) / 1000);
            batch++;
        }
    }
    public void ingestRepeatedPrices() {

        List<Price> l = new ArrayList<>();
        l.add(new Price(new PriceKey("abc0", "0"), 2.9667108 ));
        l.add(new Price(new PriceKey("abc1", "0"), 2.8532581 ));

        long startMillis = System.currentTimeMillis();
        priceRepo.saveAll(l);
        log.info("Persisted for price1 in {} sec",
                (System.currentTimeMillis() - startMillis) / 1000);

    }
    public void ingestRepeatedPrices2() {

        List<Price> l = new ArrayList<>();
        l.add(new Price(new PriceKey("abc0", "0"), 3.9667108 ));
        l.add(new Price(new PriceKey("abc1", "0"), 3.8532581 ));

        long startMillis = System.currentTimeMillis();
        for(Price p : l) {
            priceRepo.save(p);
        }
        log.info("Persisted for price1 in {} sec",
                (System.currentTimeMillis() - startMillis) / 1000);

    }

    public void ingestRepeatedPrices3() {

        List<Price> l = new ArrayList<>();
        Price price = new Price();
        PriceKey priceKey = new PriceKey();
        priceKey.setUpc("abc0");
        priceKey.setStoreId("0");
        price.setPrice(3.9667108);
        price.setPriceKey(priceKey);
        l.add(price);

        priceKey.setUpc("abc1");
        priceKey.setStoreId("0");
        price.setPrice(3.8532581);
        price.setPriceKey(priceKey);
        l.add(price);

        long startMillis = System.currentTimeMillis();
		
        for(Price p : l) {
            priceRepo.save(p);
        }
		
        log.info("Persisted for price1 in {} sec",
                (System.currentTimeMillis() - startMillis) / 1000);

    }
}
