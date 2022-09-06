package com.example.lyclebackend.Member.repository;

import com.example.lyclebackend.Member.dto.FindMyPageDto;

public interface CustomMemberRepository {
    FindMyPageDto findMemberBy(Long myPageMemberId, Long memberId);
}
