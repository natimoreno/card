package com.bank.card.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.ColumnTransformer;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @ColumnTransformer(write = "UPPER(?)")
    private String name;

    @Column(nullable = false)
    @ColumnTransformer(write = "UPPER(?)")
    private String segment;

    @Column(nullable = false)
    @ColumnTransformer(write = "UPPER(?)")
    private String status;
}
