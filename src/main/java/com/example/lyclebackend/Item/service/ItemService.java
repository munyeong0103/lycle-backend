package com.example.lyclebackend.Item.service;

import com.example.lyclebackend.Item.dto.*;
import com.example.lyclebackend.Item.entity.Item;
import com.example.lyclebackend.Item.entity.ItemMember;
import com.example.lyclebackend.Item.entity.ItemStatus;
import com.example.lyclebackend.Item.repository.ItemMemberRepository;
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
    private final ItemMemberRepository itemMemberRepository;

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
        if (findItemDto == null){
            return findItemDto;
        }
        Item item = itemRepository.findByItemId(itemId);
        item.updateViweCnt();
        return findItemDto;
    }

    @Transactional
    public boolean postItem(PostItemDto postItemDto, Long memberId) {
        postItemDto.setViewCnt(0);
        postItemDto.setIsDelete(Boolean.FALSE);
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
            item.delete();
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public boolean buyItem(PostItemMemberDto postItemMemberDto, Long itemId, Long memberId) {
        postItemMemberDto.setItemId(itemId);
        postItemMemberDto.setMemberId(memberId);
        postItemMemberDto.setStatus(ItemStatus.shipping);
        ItemMember itemMember = postItemMemberDto.toEntity();
        itemMemberRepository.save(itemMember);
        return true;
    }

}
