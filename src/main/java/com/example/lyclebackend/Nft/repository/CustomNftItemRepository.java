package com.example.lyclebackend.Nft.repository;

import com.example.lyclebackend.Nft.dto.FindNftItemDto;
import com.example.lyclebackend.Nft.dto.NftItemListInDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomNftItemRepository {
    List<NftItemListInDto> findListBy(String keyword, String sort, Pageable pageable);

    FindNftItemDto findNftItemBy(Long nftItemId, Long memberId);

    Boolean findNftItemLikeBy(Long nftItemId, Long memberId);
}
