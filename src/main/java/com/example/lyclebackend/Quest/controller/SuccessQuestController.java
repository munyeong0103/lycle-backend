package com.example.lyclebackend.Quest.controller;

import com.example.lyclebackend.Member.repository.MemberRepository;
import com.example.lyclebackend.Member.util.JwtUtil;
import com.example.lyclebackend.Quest.dto.PostQuestListDto;
import com.example.lyclebackend.Quest.dto.PostSuccessQuestListDto;
import com.example.lyclebackend.Quest.service.QuestService;
import com.example.lyclebackend.Quest.service.SuccessQuestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/successQuest")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class SuccessQuestController {
    private final SuccessQuestService successQuestService;

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;


    @GetMapping("/success")
    public ResponseEntity findList(@RequestHeader("Authorization") String accessToken,
                                   @RequestParam(name = "category", required = false, defaultValue = "") String category) {
        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));
        //log.info(String.valueOf(memberId));
        return ResponseEntity.status(HttpStatus.OK).body(successQuestService.findList(category, memberId));
    }

/*
    @GetMapping("/range")
    public ResponseEntity findRangeList(@RequestHeader("Authorization") String accessToken,
                                        @RequestParam(name = "start", required = false, defaultValue = "") Long start,
                                        @RequestParam(name = "end", required = false, defaultValue = "") Long end) {
        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));

        return ResponseEntity.status(HttpStatus.OK).body(successQuestService.findRangeList(start, end, memberId));
    }
*/
    //@GetMapping

    @PostMapping("/success")
    public ResponseEntity postSuccessQuest(@RequestHeader("Authorization") String accessToken,
                                    @RequestBody PostSuccessQuestListDto postSuccessQuestListDto) {

        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));

        return ResponseEntity.status(HttpStatus.OK).body(successQuestService.postSuccessQuestList(postSuccessQuestListDto, memberId));
    }
}
