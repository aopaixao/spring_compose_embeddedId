package com.petamus.upsert.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "price")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Price implements Serializable {
    private static final long serialVersionUID = 24232431L;

    @EmbeddedId
    private PriceKey priceKey;

    @Column(name = "price")
    private Double price;
}
