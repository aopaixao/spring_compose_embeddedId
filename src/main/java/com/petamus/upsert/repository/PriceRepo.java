package com.petamus.upsert.repository;

import com.petamus.upsert.entity.Price;
import com.petamus.upsert.entity.PriceKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepo extends JpaRepository<Price, PriceKey> {
}
