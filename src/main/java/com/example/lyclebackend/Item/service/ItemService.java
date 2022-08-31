package com.example.lyclebackend.Item.service;

import com.example.lyclebackend.Item.dto.FindItemDto;
import com.example.lyclebackend.Item.dto.ItemListDto;
import com.example.lyclebackend.Item.dto.PostItemDto;
import com.example.lyclebackend.Item.dto.PutItemDto;
import com.example.lyclebackend.Item.entity.Item;
import com.example.lyclebackend.Item.repository.ItemRepository;
import com.example.lyclebackend.Nft.entity.NftItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public ItemListDto findList(String keyword, String sort, Pageable pageable) {
        ItemListDto itemListDto = new ItemListDto();
        itemListDto.setPageCnt((int) pageable.getOffset());
        itemListDto.setLimit(pageable.getPageSize());

        itemListDto.setItemList(itemRepository.findListBy(keyword, sort, pageable));

        return itemListDto;
    }

    @Transactional
    public FindItemDto findItem(Long itemId, Long memberId) {
        FindItemDto findItemDto = itemRepository.findItemBy(itemId, memberId);

        return findItemDto;
    }

    @Transactional
    public boolean postItem(PostItemDto postItemDto, Long memberId) {
        postItemDto.setViewCnt(0);
        Item item = postItemDto.toEntity();
        itemRepository.save(item);
        return true;
    }

    @Transactional
    public boolean putItem(PutItemDto putItemDto, Long memberId, Long itemId) {
        Item item = itemRepository.findByItemId(itemId);
        if (1 == memberId) {
            item.update(putItemDto);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public boolean deleteItem(Long memberId, Long itemId) {
        Item item = itemRepository.findByItemId(itemId);
        if (1 == memberId) {
            itemRepository.deleteByItemId(item.getItemId());
            return true;
        } else {
            return false;
        }
    }

}
