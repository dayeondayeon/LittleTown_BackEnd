package com.LittleTown.Storage.service;

import com.LittleTown.ResponseDto;
import com.LittleTown.Storage.dto.PutRequestDto;

public interface StorageService {
    public ResponseDto putInto(PutRequestDto putRequestDto) throws Exception;
    public ResponseDto getFrom() throws Exception;
}
