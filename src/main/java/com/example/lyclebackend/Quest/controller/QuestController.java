package com.example.lyclebackend.Quest.controller;

import com.example.lyclebackend.Member.dto.ResultDto;
import com.example.lyclebackend.Member.repository.MemberRepository;
import com.example.lyclebackend.Member.util.JwtUtil;
import com.example.lyclebackend.Nft.dto.PutNftItemDto;
import com.example.lyclebackend.Quest.dto.DeleteQuestListDto;
import com.example.lyclebackend.Quest.dto.PutQuestListDto;
import com.example.lyclebackend.Quest.service.QuestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quest")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class QuestController {
    private final QuestService questService;

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;


    @GetMapping("")
    public ResponseEntity findList(@RequestHeader("Authorization") String accessToken,
                                   @RequestParam(name = "category", required = false, defaultValue = "") String category) {
        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));
        log.info(String.valueOf(memberId));
        return ResponseEntity.status(HttpStatus.OK).body(questService.findList(category));
    }

    @PutMapping("")
    public ResponseEntity putQuest(@RequestHeader("Authorization") String accessToken,
                                   @RequestBody PutQuestListDto putQuestListDto,
                                   @RequestParam(name = "quest_id", required = false, defaultValue = "") Long questId) {
        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));
        log.info(String.valueOf(memberId));

        return ResponseEntity.status(HttpStatus.OK).body(questService.putQuestList(putQuestListDto, questId));
    }


    @DeleteMapping("")
    public ResponseEntity deleteQuest(@RequestHeader("Authorization") String accessToken,
                                      @RequestBody DeleteQuestListDto deleteQuestListDto,
                                      @RequestParam("quest_id") Long questId) {
        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));
        log.info(String.valueOf(memberId));

        return ResponseEntity.status(HttpStatus.OK).body(questService.deleteQuest(questId));
    }


    // 1. 바뀐 테이블 값 수정하기
    // 2. memberId, authorization 추가하기

}
