package com.example.lyclebackend.Nft.controller;

import com.example.lyclebackend.Member.dto.ResultDto;
import com.example.lyclebackend.Member.repository.MemberRepository;
import com.example.lyclebackend.Member.util.JwtUtil;
import com.example.lyclebackend.Nft.dto.PostNftItemDto;
import com.example.lyclebackend.Nft.service.NftItemLikeService;
import com.example.lyclebackend.Nft.service.NftItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nftItem")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class NftItemLikeController {

    private final NftItemLikeService nftItemLikeService;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @PostMapping("/{nft_item_id}/like")
    public ResponseEntity likeNftItem(@RequestHeader("Authorization") String accessToken,
                                      @PathVariable("nft_item_id") Long nftItemId) {

        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));
        nftItemLikeService.postNftItemLike(nftItemId, memberId);
        ResultDto result = new ResultDto(true);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
