package com.example.lyclebackend.Member.service;

import com.example.lyclebackend.Item.dto.PutItemDto;
import com.example.lyclebackend.Item.entity.Item;
import com.example.lyclebackend.Member.dto.FindMyPageDto;
import com.example.lyclebackend.Member.dto.PutMyPageDto;
import com.example.lyclebackend.Member.entity.Member;
import com.example.lyclebackend.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public FindMyPageDto findMyPage(Long myPageMemberId, Long memberId) {
        FindMyPageDto findMyPageDto = memberRepository.findMemberBy(myPageMemberId, memberId);

        return findMyPageDto;
    }

    @Transactional
    public boolean putMyPage(PutMyPageDto putMyPageDto, Long memberId, Long myPageMemberId) {
        Member member = memberRepository.findByMemberId(myPageMemberId);
        if (myPageMemberId == memberId) {
            member.update(putMyPageDto);
            return true;
        } else {
            return false;
        }
    }

}
