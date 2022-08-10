package com.LittleTown.Storage.controller;

import com.LittleTown.Message;
import com.LittleTown.ResponseDto;
import com.LittleTown.Status;
import com.LittleTown.Storage.domain.ItemRepository;
import com.LittleTown.Storage.domain.Items;
import com.LittleTown.Storage.dto.PutRequestDto;
import com.LittleTown.Storage.service.ClosetServiceImpl;
import com.LittleTown.typeInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/storage", consumes = MediaType.APPLICATION_JSON_VALUE)
public class StorageController {
    private final ClosetServiceImpl closetService;

    @PostMapping("/closet")
    public ResponseEntity putCloth(@RequestBody PutRequestDto putRequestDto) {
        try {
            ResponseDto responseDto = closetService.putInto(putRequestDto);
            return ResponseEntity.ok().body(ResponseDto.res(Status.OK, responseDto.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.res(Status.BAD_REQUEST, e.getMessage()));
        }
    }
}
