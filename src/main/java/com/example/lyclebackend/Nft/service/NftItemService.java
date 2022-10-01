package com.example.lyclebackend.Nft.service;

import com.example.lyclebackend.Member.entity.Member;
import com.example.lyclebackend.Member.repository.MemberRepository;
import com.example.lyclebackend.Nft.dto.*;
import com.example.lyclebackend.Nft.entity.NftItem;
import com.example.lyclebackend.Nft.entity.NftItemStatus;
import com.example.lyclebackend.Nft.repository.NftItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class NftItemService {

    private final NftItemRepository nftItemRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public NftItemListDto findList(String keyword, String sort, Pageable pageable) {
        NftItemListDto nftItemListDto = new NftItemListDto();
        nftItemListDto.setPageCnt((int) pageable.getOffset());
        nftItemListDto.setLimit(pageable.getPageSize());

        nftItemListDto.setItemList(nftItemRepository.findListBy(keyword, sort, pageable));

        return nftItemListDto;
    }

    @Transactional
    public FindNftItemDto findNftItem(Long nftItemId, Long memberId) {
        FindNftItemDto findNftItemDto = nftItemRepository.findNftItemBy(nftItemId, memberId);
        if (findNftItemDto.getIsDelete() == Boolean.TRUE){
            FindNftItemDto findNftItemDto1 = new FindNftItemDto();
            return findNftItemDto1;
        }
        findNftItemDto.setIsLike(nftItemRepository.findNftItemLikeBy(nftItemId, memberId));
        NftItem nftItem = nftItemRepository.findByNftItemId(nftItemId);
        nftItem.updateViweCnt();
        return findNftItemDto;
    }

    @Transactional
    public FindPostInfoDto findPostInfo(Long memberId) {
        FindPostInfoDto findPostInfoDto = new FindPostInfoDto();
        Member member = memberRepository.findByMemberId(memberId);
        findPostInfoDto.setNickname(member.getNickname());
        findPostInfoDto.setProfileImg(member.getProfileImg());

        List<NftImgDto> nftImgDtoList = new ArrayList<>();

        NftImgDto nftImgDto1 = new NftImgDto();
        nftImgDto1.setNftImg("img1:src1");
        nftImgDto1.setNftId(1L);

        NftImgDto nftImgDto2 = new NftImgDto();
        nftImgDto1.setNftImg("img2:src2");
        nftImgDto1.setNftId(2L);

        nftImgDtoList.add(nftImgDto1);
        nftImgDtoList.add(nftImgDto2);

        findPostInfoDto.setNftImgDtoList(nftImgDtoList);
        return findPostInfoDto;
    }

    @Transactional
    public boolean postNftItem(PostNftItemDto postNftItemDto, Long memberId) {
        postNftItemDto.setViewCnt(0);
        postNftItemDto.setSellerId(memberId);
        postNftItemDto.setIsDelete(Boolean.FALSE);
        postNftItemDto.setStatus(NftItemStatus.sale);
        NftItem nftItem = postNftItemDto.toEntity();
        nftItemRepository.save(nftItem);
        return true;
    }

    @Transactional
    public boolean putNftItem(PutNftItemDto putNftItemDto, Long memberId, Long nftItemId) {
        NftItem nftItem = nftItemRepository.findByNftItemId(nftItemId);
        if (nftItem.getSellerId() == memberId) {
            nftItem.update(putNftItemDto);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public boolean deleteNftItem(Long memberId, Long nftItemId) {
        NftItem nftItem = nftItemRepository.findByNftItemId(nftItemId);
        if (nftItem.getSellerId() == memberId) {
            nftItem.delete();
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public BuyNftItemDto buyNftItem(Long nftItemId, Long memberId) {
        NftItem nftItem = nftItemRepository.findByNftItemId(nftItemId);
        BuyNftItemDto buyNftItemDto = new BuyNftItemDto();
        if(nftItem.getStatus() == NftItemStatus.sale) {
            buyNftItemDto.setNftItemImg(nftItem.getNftItemImg());
            buyNftItemDto.setSellerWalletAddress(nftItem.getSeller().getWalletAddress());
            buyNftItemDto.setBuyerWalletAddress(memberRepository.findByMemberId(memberId).getWalletAddress());
            nftItem.updateStatus(NftItemStatus.paying);
        }
        return buyNftItemDto;
    }

    @Transactional
    public boolean buyNftItemFix(Long nftItemId, Long memberId) {
        NftItem nftItem = nftItemRepository.findByNftItemId(nftItemId);
        nftItem.updatePaying(NftItemStatus.soldOut, memberId);
        return true;
    }
}
