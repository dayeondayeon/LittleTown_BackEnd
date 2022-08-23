package com.LittleTown.Storage.domain;

import com.LittleTown.User.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long userIdx;

    @Column(nullable = false)
    private long itemIdx;

    @Column(nullable = false)
    private long count;

    @Column(nullable = false)
    private int storageType;

    @Builder
    public Storage (long userIdx, long itemIdx, long count, int storageType) {
        this.userIdx = userIdx;
        this.itemIdx = itemIdx;
        this.count = count;
        this.storageType = storageType;
    }

}
