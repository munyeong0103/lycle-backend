package com.example.lyclebackend.Quest.repository;

import com.example.lyclebackend.Quest.dto.FindQuestListDto;

import java.util.List;

public interface CustomQuestRepository {
    List<FindQuestListDto> findQuestList(String category);

    FindQuestListDto findQuestListBy(Long questId, Long memberId);
    // questId로 찾을 수 있게 설정했는데 맞는지 / quest만 수정하는거니까 memberId 필요없는거 맞는지
    // memberId 지정해서 관리자만 변경할 수 있도록 제한, Authorization도 포함

}
