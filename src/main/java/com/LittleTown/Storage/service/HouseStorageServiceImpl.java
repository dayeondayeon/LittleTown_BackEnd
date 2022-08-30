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
public class HouseStorageServiceImpl implements StorageService {
    private final StorageRepository storageRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;


    @Override
    @Transactional
    public ResponseDto putInto(PutItemRequestDto putItemRequestDto) throws Exception {
        try {
            Optional<User> user = userRepository.findById(putItemRequestDto.getUserIdx());
            Optional<Items> item = itemRepository.findById(putItemRequestDto.getItemIdx());
            if (user.isEmpty()) {
                throw new Exception(Message.INVALID_USER);
            } // 유효한 유저인가 확인.

            if (item.isEmpty() || item.get().getType() == typeInfo.CLOTHES) {
                throw new Exception(Message.INVALID_ITEM);
            } // 유효한 아이템인가 확인

            if (putItemRequestDto.getStorageType() != typeInfo.STORAGE) {
                throw new Exception(Message.INVALID_REQUEST);
            }

            List<Storage> storage = storageRepository.findByUserIdx(putItemRequestDto.getUserIdx()); // 유저가 이 아이템을 이미 창고에 넣어둔 경우

            if (storage.isEmpty()) { // 저장된 것이 아예 없을 경우.
                storageRepository.save(putItemRequestDto.toEntity());
                return new ResponseDto(Status.OK, Message.ITEM_SAVE_SUCCESS);
            } else {
                for (Storage s : storage) {
                    if (s.getItemIdx() == putItemRequestDto.getItemIdx()) {
                        // 이미 창고에 해당 아이템이 있으므로 그 개수만 update.
                        s.setId(s.getId());
                        s.setCount(s.getCount() + putItemRequestDto.getCount());
                        storageRepository.save(s);
                        return new ResponseDto(Status.OK, Message.ITEM_SAVE_SUCCESS);
                    }
                }
                // 반복문을 다 돌았는데 없다. (storage 정보는 있지만 요청온 아이템은 처음 창고에 저장하는 경우)

                storageRepository.save(putItemRequestDto.toEntity());
                return new ResponseDto(Status.OK, Message.ITEM_SAVE_SUCCESS);
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ResponseDto getFrom(GetItemRequestDto getItemRequestDto) throws Exception {
        try {
            Optional<User> user = userRepository.findById(getItemRequestDto.getUserIdx());
            if (user.isEmpty()) {
                throw new Exception(Message.INVALID_USER);
            }

            List<Storage> storage = storageRepository.findByUserIdx(getItemRequestDto.getUserIdx());

            for (Storage s : storage) {
                if (s.getStorageType() == typeInfo.CROPS || s.getStorageType() == typeInfo.SEEDLING && s.getItemIdx() == getItemRequestDto.getItemIdx() && s.getCount() > 0) {
                    // 해당 아이템이 존재해야 꺼내기 가능.
                    s.setId(s.getId());
                    s.setCount(s.getCount() - 1); // 카운트 하나 감소시킴. 일단 작물도 하나만 되도록 하긴 했는데 이건 나중에 기획 보고 결정.
                    storageRepository.save(s);
                    return new ResponseDto(Status.OK, Message.ITEM_GET_SUCCESS);
                }
            }
            throw new Exception(Message.ITEM_GET_FAIL);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
