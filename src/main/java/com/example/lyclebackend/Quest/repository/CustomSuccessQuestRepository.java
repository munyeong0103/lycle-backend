package com.example.lyclebackend.Quest.repository;

import com.example.lyclebackend.Quest.dto.FindQuestListDto;
import com.example.lyclebackend.Quest.dto.FindSuccessQuestListDto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public interface CustomSuccessQuestRepository {
    List<FindSuccessQuestListDto> findSuccessQuestList(String category);

    FindSuccessQuestListDto findSuccessQuestListBy(Long successQuestId, Long memberId);
}
