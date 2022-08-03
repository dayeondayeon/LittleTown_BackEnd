package com.LittleTown.Email.domain;

import com.LittleTown.Email.dto.EmailListFormat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Long> {
    List<Email> findByWriter(long userIdx);
}
