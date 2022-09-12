package com.example.lyclebackend.Item.controller;

import com.example.lyclebackend.Item.dto.PostItemDto;
import com.example.lyclebackend.Item.dto.PostItemMemberDto;
import com.example.lyclebackend.Item.dto.PutItemDto;
import com.example.lyclebackend.Item.service.ItemService;
import com.example.lyclebackend.Member.dto.ResultDto;
import com.example.lyclebackend.Member.repository.MemberRepository;
import com.example.lyclebackend.Member.util.JwtUtil;
import com.example.lyclebackend.Nft.dto.PostNftItemDto;
import com.example.lyclebackend.Nft.dto.PutNftItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class ItemController {

    private final ItemService itemService;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @GetMapping("/test")
    public String test() {

        return "이메일 인증이 완료되었습니다";
    }

    @GetMapping("")
    public ResponseEntity findList(@RequestParam(name = "title", required = false, defaultValue = "") String title,
                                   @RequestParam(name = "sort", required = false, defaultValue = "recent") String sort,
                                   Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.findList(title, sort, pageable));
    }

    @GetMapping("/{item_id}")
    public ResponseEntity findNftItem(@RequestHeader("Authorization") String accessToken,
                                      @PathVariable("item_id") Long itemId) {
        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));
        return ResponseEntity.status(HttpStatus.OK).body(itemService.findItem(itemId, memberId));
    }

    @PostMapping("")
    public ResponseEntity postNftItem(@RequestHeader("Authorization") String accessToken,
                                      @RequestBody PostItemDto postItemDto) {
        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));
        itemService.postItem(postItemDto, memberId);
        ResultDto result = new ResultDto(true);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{item_id}")
    public ResponseEntity putNftItem(@RequestHeader("Authorization") String accessToken,
                                     @RequestBody PutItemDto putItemDto,
                                     @PathVariable("item_id") Long itemId) {
        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));
        itemService.putItem(putItemDto, memberId, itemId);
        ResultDto result = new ResultDto(true);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{item_id}")
    public ResponseEntity deleteNftItem(@RequestHeader("Authorization") String accessToken,
                                        @PathVariable("item_id") Long itemId) {
        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken));
        itemService.deleteItem(memberId, itemId);
        ResultDto result = new ResultDto(true);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/{item_id}/buy")
    public ResponseEntity buyNftItem(@RequestHeader("Authorization") String accessToken,
                                     @PathVariable("item_id") Long itemId,
                                     @RequestBody PostItemMemberDto postItemMemberDto) {
        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));
        itemService.buyItem(postItemMemberDto, itemId, memberId);
        ResultDto result = new ResultDto(true);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}