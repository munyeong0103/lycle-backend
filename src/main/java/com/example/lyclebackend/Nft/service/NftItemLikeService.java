package com.example.lyclebackend.Nft.service;


import com.example.lyclebackend.Nft.dto.NftItemLikeDto;
import com.example.lyclebackend.Nft.entity.NftItemLike;
import com.example.lyclebackend.Nft.repository.NftItemLikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class NftItemLikeService {

    private final NftItemLikeRepository nftItemLikeRepository;

    @Transactional
    public void likeNftItem(Long nftItemId, Long memberId) {
        NftItemLikeDto nftItemLikeDto = new NftItemLikeDto();
        nftItemLikeDto.setNftItemId(nftItemId);
        nftItemLikeDto.setMemberId(memberId);

        NftItemLike nftItemLike = nftItemLikeDto.toEntity();
        nftItemLikeRepository.save(nftItemLike);
    }

    @Transactional
    public void dislikeNftItem(Long nftItemLikeId) {
        nftItemLikeRepository.deleteByNftItemLikeId(nftItemLikeId);
    }

    @Transactional
    public boolean postNftItemLike(Long nftItemId, Long memberId) {
        Long nftItemLikeId = nftItemLikeRepository.findByNftItemAndMember(nftItemId, memberId);
        if(nftItemLikeId == null) {
            likeNftItem(nftItemId, memberId);
        } else {
            dislikeNftItem(nftItemLikeId);
        }
        return true;
    }


}
