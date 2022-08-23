package com.LittleTown.Storage.service;

import com.LittleTown.Message;
import com.LittleTown.ResponseDto;
import com.LittleTown.Status;
import com.LittleTown.Storage.domain.ItemRepository;
import com.LittleTown.Storage.domain.Items;
import com.LittleTown.Storage.domain.Storage;
import com.LittleTown.Storage.domain.StorageRepository;
import com.LittleTown.Storage.dto.GetItemRequestDto;
import com.LittleTown.Storage.dto.PutItemRequestDto;
import com.LittleTown.User.domain.User;
import com.LittleTown.User.domain.UserRepository;
import com.LittleTown.typeInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClosetServiceImpl implements StorageService{
    private final StorageRepository storageRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResponseDto putInto(PutItemRequestDto putItemRequestDto) throws Exception {
        try {
            Optional<Items> item = itemRepository.findById(putItemRequestDto.getItemIdx());
            Optional<User> user = userRepository.findById(putItemRequestDto.getUserIdx());
            
            if (item.isEmpty()) {
                throw new Exception(Message.INVALID_ITEM);
            }
            if (user.isEmpty()) {
                throw new Exception(Message.INVALID_USER);
            }
            if (putItemRequestDto.getItemType() != typeInfo.CLOTHES || putItemRequestDto.getStorageType() != typeInfo.CLOSET) {
                throw new Exception(Message.INVALID_REQUEST);
            }

            storageRepository.save(putItemRequestDto.toEntity());
            return new ResponseDto(Status.OK, Message.ITEM_SAVE_SUCCESS);

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public ResponseDto getFrom(GetItemRequestDto getItemRequestDto) throws Exception{
        try {
            Optional<User> user = userRepository.findById(getItemRequestDto.getUserIdx());
            if (user.isEmpty()) {
                throw new Exception(Message.INVALID_USER);
            }

            List<Storage> storage = storageRepository.findByUserIdx(getItemRequestDto.getUserIdx());

            for (Storage s : storage) {
                if (s.getStorageType() == typeInfo.CLOSET && s.getItemIdx() == getItemRequestDto.getItemIdx() && s.getCount() > 0) {
                    // 해당 아이템이 존재해야 꺼내기 가능.
                    storageRepository.delete(s);
                    return new ResponseDto(Status.OK, Message.ITEM_GET_SUCCESS);
                }
            }
            throw new Exception(Message.ITEM_GET_FAIL);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
