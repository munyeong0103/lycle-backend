package com.example.lyclebackend.Member.repository;

import com.example.lyclebackend.Member.dto.FindMyPageDto;
import com.example.lyclebackend.Nft.dto.NftItemListInDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomMemberRepository {
    FindMyPageDto findMemberBy(Long myPageMemberId, Long memberId);

    List<NftItemListInDto> findListBy(String keyword, String sort, Pageable pageable, Long memberId);
}
