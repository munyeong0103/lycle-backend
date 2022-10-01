package com.example.lyclebackend.Quest.service;

import com.example.lyclebackend.Member.repository.MemberRepository;
import com.example.lyclebackend.Nft.dto.NftItemListDto;
import com.example.lyclebackend.Nft.dto.PutNftItemDto;
import com.example.lyclebackend.Nft.entity.NftItem;
import com.example.lyclebackend.Quest.dto.FindQuestListDto;
import com.example.lyclebackend.Quest.dto.FindSuccessQuestListDto;
import com.example.lyclebackend.Quest.dto.PutQuestListDto;
import com.example.lyclebackend.Quest.entity.Quest;
import com.example.lyclebackend.Quest.repository.QuestRepository;
import com.example.lyclebackend.Quest.repository.SuccessQuestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class SuccessQuestService {
    private final SuccessQuestRepository successQuestRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public List<FindSuccessQuestListDto> findList(String category, Long memberId) {
        List<FindSuccessQuestListDto> findSuccessQuestListDtoList = successQuestRepository.findSuccessQuestList(category);

        memberRepository.findByMemberId(memberId);

        return findSuccessQuestListDtoList;
    }

    public List<FindSuccessQuestListDto> findRangeList(String category, Long start, Long end, Long memberId) {


            List<FindSuccessQuestListDto> findSuccessQuestListDtoList = successQuestRepository.findSuccessQuestList(category);

        memberRepository.findByMemberId(memberId);

        return findSuccessQuestListDtoList;
    }
}

