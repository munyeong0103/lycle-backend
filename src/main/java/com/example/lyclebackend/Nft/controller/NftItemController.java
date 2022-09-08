package com.example.lyclebackend.Nft.controller;

import com.example.lyclebackend.Member.dto.ResultDto;
import com.example.lyclebackend.Member.repository.MemberRepository;
import com.example.lyclebackend.Member.util.JwtUtil;
import com.example.lyclebackend.Nft.dto.PostNftItemDto;
import com.example.lyclebackend.Nft.dto.PutNftItemDto;
import com.example.lyclebackend.Nft.service.NftItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nftItem")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class NftItemController {

    private final NftItemService nftItemService;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @GetMapping("")
    public ResponseEntity findList(@RequestParam(name = "title", required = false, defaultValue = "") String title,
                                   @RequestParam(name = "sort", required = false, defaultValue = "recent") String sort,
                                   Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(nftItemService.findList(title, sort, pageable));
    }

    @GetMapping("/{nft_item_id}")
    public ResponseEntity findNftItem(@RequestHeader("Authorization") String accessToken,
                                      @PathVariable("nft_item_id") Long nftItemId) {
        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));
        return ResponseEntity.status(HttpStatus.OK).body(nftItemService.findNftItem(nftItemId, memberId));
    }

    @GetMapping("/post")
    public ResponseEntity findPostInfo(@RequestHeader("Authorization") String accessToken) {
        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));
        return ResponseEntity.status(HttpStatus.OK).body(nftItemService.findPostInfo(memberId));
    }


    @PostMapping("")
    public ResponseEntity postNftItem(@RequestHeader("Authorization") String accessToken,
                                      @RequestBody PostNftItemDto postNftItemDto) {
        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));
        nftItemService.postNftItem(postNftItemDto, memberId);
        ResultDto result = new ResultDto(true);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{nft_item_id}")
    public ResponseEntity putNftItem(@RequestHeader("Authorization") String accessToken,
                                     @RequestBody PutNftItemDto putNftItemDto,
                                     @PathVariable("nft_item_id") Long nftItemId) {
        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));
        nftItemService.putNftItem(putNftItemDto, memberId, nftItemId);
        ResultDto result = new ResultDto(true);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{nft_item_id}")
    public ResponseEntity deleteNftItem(@RequestHeader("Authorization") String accessToken,
                                     @PathVariable("nft_item_id") Long nftItemId) {
        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));
        nftItemService.deleteNftItem(memberId, nftItemId);
        ResultDto result = new ResultDto(true);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}
