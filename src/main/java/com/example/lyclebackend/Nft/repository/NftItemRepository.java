package com.example.lyclebackend.Nft.repository;

import com.example.lyclebackend.Nft.entity.NftItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NftItemRepository extends JpaRepository<NftItem, Long>, CustomNftItemRepository {

    NftItem findByNftItemId(Long nftItemId);

    void deleteByNftItemId(Long nftItemId);

}
