package com.LittleTown.Email.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long sender;

    @Column(nullable = false)
    private long receiver;

    @Column(nullable = false)
    private String contents;

    @Builder
    public Email (long sender, long receiver, String contents) {
        this.sender = sender;
        this.receiver = receiver;
        this.contents = contents;
    }
}
