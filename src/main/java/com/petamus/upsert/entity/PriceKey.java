package com.petamus.upsert.entity;

import lombok.Getter;
import lombok.Setter;

import lombok.EqualsAndHashCode;;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PriceKey implements Serializable {
    private static final long serialVersionUID = 24232431L;

    @Getter
    @Setter
    @Column(name = "upc")
    private String upc;

    @Getter
    @Setter
    @Column(name = "store_id")
    private String storeId;

}
