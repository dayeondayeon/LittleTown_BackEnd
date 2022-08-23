package com.LittleTown.Storage.service;

import com.LittleTown.ResponseDto;
import com.LittleTown.Storage.dto.GetItemRequestDto;
import com.LittleTown.Storage.dto.PutItemRequestDto;

public interface StorageService {
    public ResponseDto putInto(PutItemRequestDto putItemRequestDto) throws Exception;
    public ResponseDto getFrom(GetItemRequestDto getItemRequestDto) throws Exception;
}
