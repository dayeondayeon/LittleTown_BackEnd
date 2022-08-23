package com.LittleTown.Storage.controller;

import com.LittleTown.ResponseDto;
import com.LittleTown.Status;
import com.LittleTown.Storage.dto.GetItemRequestDto;
import com.LittleTown.Storage.dto.PutItemRequestDto;
import com.LittleTown.Storage.service.ClosetServiceImpl;
import com.LittleTown.Storage.service.HouseStorageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/storage", consumes = MediaType.APPLICATION_JSON_VALUE)
public class StorageController {
    private final ClosetServiceImpl closetService;
    private final HouseStorageServiceImpl houseStorageService;

    // 옷장
    @PostMapping("/closet")
    public ResponseEntity putCloth(@RequestBody PutItemRequestDto putRequestDto) {
        try {
            ResponseDto responseDto = closetService.putInto(putRequestDto);
            return ResponseEntity.ok().body(ResponseDto.res(Status.OK, responseDto.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.res(Status.BAD_REQUEST, e.getMessage()));
        }
    }

    @PostMapping("/closet/take")
    public ResponseEntity takeCloth(@RequestBody GetItemRequestDto getItemRequestDto) {
        try {
            ResponseDto responseDto = closetService.getFrom(getItemRequestDto);
            return ResponseEntity.ok().body(ResponseDto.res(Status.OK, responseDto.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.res(Status.BAD_REQUEST, e.getMessage()));
        }
    }

    // 창고
    @PostMapping("/take")
    public ResponseEntity takeCropSeed(@RequestBody GetItemRequestDto getItemRequestDto) {
        try {
            ResponseDto responseDto = houseStorageService.getFrom(getItemRequestDto);
            return ResponseEntity.ok().body(ResponseDto.res(Status.OK, responseDto.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.res(Status.BAD_REQUEST, e.getMessage()));
        }
    }

}
