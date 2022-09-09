package com.example.lyclebackend.Item.repository;

import com.example.lyclebackend.Item.dto.FindItemDto;
import com.example.lyclebackend.Item.dto.ItemListInDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomItemRepository {
    List<ItemListInDto> findListBy(String keyword, String sort, Pageable pageable);

    FindItemDto findItemBy(Long itemId, Long memberId);
//
//    Boolean findNftItemLikeBy(Long nftItemId, Long memberId);
}