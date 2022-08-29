package com.example.lyclebackend.Nft.repository;

import com.example.lyclebackend.Nft.entity.NftItemLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NftItemLikeRepository extends JpaRepository<NftItemLike, Long> {
    Boolean findByNftItemAndMember(Long nftItem, Long member);
}
