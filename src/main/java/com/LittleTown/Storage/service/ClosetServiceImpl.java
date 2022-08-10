package com.LittleTown.Storage.service;

import com.LittleTown.Message;
import com.LittleTown.ResponseDto;
import com.LittleTown.Status;
import com.LittleTown.Storage.domain.ItemRepository;
import com.LittleTown.Storage.domain.Items;
import com.LittleTown.Storage.domain.Storage;
import com.LittleTown.Storage.domain.StorageRepository;
import com.LittleTown.Storage.dto.PutRequestDto;
import com.LittleTown.User.domain.User;
import com.LittleTown.User.domain.UserRepository;
import com.LittleTown.typeInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClosetServiceImpl implements StorageService{
    private final StorageRepository storageRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResponseDto putInto(PutRequestDto putRequestDto) throws Exception {
        try {
            Optional<Items> item = itemRepository.findById(putRequestDto.getItemIdx());
            Optional<User> user = userRepository.findById(putRequestDto.getUserIdx());

            if (item.isEmpty()) {
                throw new Exception(Message.INVALID_ITEM);
            }
            if (user.isEmpty()) {
                throw new Exception(Message.INVALID_USER);
            }
            if (putRequestDto.getItemType() != typeInfo.CLOTHES || putRequestDto.getStorageType() != typeInfo.CLOSET) {
                throw new Exception(Message.INVALID_REQUEST);
            }
            storageRepository.save(putRequestDto.toEntity());
            return new ResponseDto(Status.OK, Message.ITEM_SAVE_SUCCESS);

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public ResponseDto getFrom() throws Exception{
        return null;
    }
}
