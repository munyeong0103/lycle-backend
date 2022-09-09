package com.example.lyclebackend.Quest.service;

import com.example.lyclebackend.Nft.dto.NftItemListDto;
import com.example.lyclebackend.Nft.dto.PutNftItemDto;
import com.example.lyclebackend.Nft.entity.NftItem;
import com.example.lyclebackend.Quest.dto.FindQuestListDto;
import com.example.lyclebackend.Quest.dto.PutQuestListDto;
import com.example.lyclebackend.Quest.entity.Quest;
import com.example.lyclebackend.Quest.repository.QuestRepository;
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
public class QuestService {
    private final QuestRepository questRepository;

    @Transactional
    public List<FindQuestListDto> findList(String category) {
        List<FindQuestListDto> findQuestListDtoList = questRepository.findQuestList(category);

        return findQuestListDtoList;
    }

    @Transactional
    public boolean putQuestList(PutQuestListDto putQuestListDto, Long questId) {

        Quest quest = questRepository.findByQuestId(questId);
        if (quest.getQuestId() == questId) {
            quest.update(putQuestListDto);
            return true;
        } else {
            return false;
        }
    }



    @Transactional
    public boolean deleteQuest(Long questId) { //category, level

        Quest quest = questRepository.findByQuestId(questId);

        if (quest.getQuestId() == questId) {
            questRepository.deleteByQuestId(quest.getQuestId());
            return true;
        } else {
            return false;
        }
    }
}
