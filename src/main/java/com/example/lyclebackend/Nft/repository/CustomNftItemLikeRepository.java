package com.example.lyclebackend.Nft.repository;

public interface CustomNftItemLikeRepository {
    Long findByNftItemAndMember(Long nftItemId, Long memberId);
}
