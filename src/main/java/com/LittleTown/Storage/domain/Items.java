package com.LittleTown.Storage.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long buyPrice;

    @Column(nullable = false)
    private long sellPrice;

    @Column(nullable = false)
    private int type;
}
