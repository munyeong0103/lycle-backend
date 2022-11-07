package com.example.lyclebackend.Quest.service;

import com.example.lyclebackend.Member.repository.MemberRepository;
import com.example.lyclebackend.Nft.dto.NftItemListDto;
import com.example.lyclebackend.Nft.dto.PutNftItemDto;
import com.example.lyclebackend.Nft.entity.NftItem;
import com.example.lyclebackend.Quest.dto.*;
import com.example.lyclebackend.Quest.entity.Quest;
import com.example.lyclebackend.Quest.entity.SuccessQuest;
import com.example.lyclebackend.Quest.repository.QuestRepository;
import com.example.lyclebackend.Quest.repository.SuccessQuestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
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
/*
    public List<FindSuccessQuestListDto> findRangeList(Long start, Long end, Long memberId) {

        for (Long i=start; i<end; i++) {
            SuccessQuest successQuest = successQuestRepository.findBySuccessQuestId(memberId);
        }
        memberRepository.findByMemberId(memberId);

        return findSuccessQuestListDtoList;
    }
    */
    @Transactional
    public boolean postSuccessQuestList(PostSuccessQuestListDto postSuccessQuestListDto, Long memberId) {

        SuccessQuest successQuest = postSuccessQuestListDto.toEntity();
        //QuestRepository.save(memberId);
        return true;
    }

    @Transactional
    public boolean putSuccessQuestList(PutSuccessQuestListDto putSuccessQuestListDto, Long successQuestId, Long memberId) {

        SuccessQuest successQuest = successQuestRepository.findBySuccessQuestId(successQuestId);

        if (successQuest.getSuccessQuestId() == successQuestId) {
            successQuest.update(putSuccessQuestListDto);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public boolean deleteSuccessQuest(DeleteSuccessQuestListDto deleteSuccessQuestListDto, Long successQuestId, Long memberId) { //category, level

        SuccessQuest successQuest = successQuestRepository.findBySuccessQuestId(successQuestId);

        if (successQuest.getSuccessQuestId() == successQuestId) {
            successQuestRepository.deleteBySuccessQuestId(successQuest.getSuccessQuestId());
            return true;
        } else {
            return false;
        }
    }

    @Transactional //반환
    public boolean deleteExpiredSuccessQuest(LocalDateTime expiredDate, Long successQuestId, Long memberId) { //category, level

        SuccessQuest successQuest = successQuestRepository.findBySuccessQuestId(successQuestId);

        if (successQuest.getSuccessQuestId() == successQuestId) {
            successQuestRepository.deleteBySuccessQuestId(successQuest.getSuccessQuestId());
            return true;
        } else {
            return false;
        }
    }

}

