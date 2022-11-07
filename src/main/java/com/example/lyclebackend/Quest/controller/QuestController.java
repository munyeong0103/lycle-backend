package com.example.lyclebackend.Quest.controller;

import com.example.lyclebackend.Member.dto.ResultDto;
import com.example.lyclebackend.Member.repository.MemberRepository;
import com.example.lyclebackend.Member.util.JwtUtil;
import com.example.lyclebackend.Nft.dto.PostNftItemDto;
import com.example.lyclebackend.Nft.dto.PutNftItemDto;
import com.example.lyclebackend.Quest.dto.DeleteQuestListDto;
import com.example.lyclebackend.Quest.dto.PostQuestListDto;
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
        //log.info(String.valueOf(memberId));
        return ResponseEntity.status(HttpStatus.OK).body(questService.findList(category, memberId));
    }

    @PutMapping("") //category, level 둘 다 일치해야 수정할 수 있게 변경(quest_id 코드 삭제)
    public ResponseEntity putQuest(@RequestHeader("Authorization") String accessToken,
                                   @RequestBody PutQuestListDto putQuestListDto,
                                   @RequestParam(name = "quest_id", required = false, defaultValue = "") Long questId) {
        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));

        return ResponseEntity.status(HttpStatus.OK).body(questService.putQuestList(putQuestListDto, questId, memberId));
    }


    @DeleteMapping("") //category, level 둘 다 일치해야 수정할 수 있게 변경(quest_id 코드 삭제)
    public ResponseEntity deleteQuest(@RequestHeader("Authorization") String accessToken,
                                      @RequestBody DeleteQuestListDto deleteQuestListDto,
                                      @RequestParam("quest_id") Long questId) {
        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));

        return ResponseEntity.status(HttpStatus.OK).body(questService.deleteQuest(questId, memberId));
    }

    @PostMapping("")
    public ResponseEntity postQuest(@RequestHeader("Authorization") String accessToken,
                                    @RequestBody PostQuestListDto postQuestListDto) {

        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));

        return ResponseEntity.status(HttpStatus.OK).body(questService.postQuestList(postQuestListDto, memberId));
    }

}
