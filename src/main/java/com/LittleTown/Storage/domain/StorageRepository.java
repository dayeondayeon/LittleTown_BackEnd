package com.LittleTown.Storage.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StorageRepository extends JpaRepository<Storage, Long> {
    List<Storage> findByUserIdx(long userIdx);
}
