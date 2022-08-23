package com.LittleTown.Storage.service;

import com.LittleTown.Message;
import com.LittleTown.ResponseDto;
import com.LittleTown.Status;
import com.LittleTown.Storage.domain.ItemRepository;
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
        return null;
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
