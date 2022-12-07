package com.example.lyclebackend.Item.controller;

import com.example.lyclebackend.Item.dto.PostItemDto;
import com.example.lyclebackend.Item.dto.PostItemMemberDto;
import com.example.lyclebackend.Item.dto.PutItemDto;
import com.example.lyclebackend.Item.service.ItemService;
import com.example.lyclebackend.Member.dto.ResultDto;
import com.example.lyclebackend.Member.repository.MemberRepository;
import com.example.lyclebackend.Member.service.AwsS3Service;
import com.example.lyclebackend.Member.util.JwtUtil;
import com.example.lyclebackend.Nft.dto.PostNftItemDto;
import com.example.lyclebackend.Nft.dto.PutNftItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class ItemController {

    private final ItemService itemService;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final AwsS3Service awsS3Service;

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
                                      @RequestPart(value = "postItemDto") @Valid PostItemDto postItemDto,
                                      @RequestPart(value = "file") MultipartFile multipartFile) {
        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));
        String itemImg = awsS3Service.uploadFileV1("itemImg", multipartFile);
        postItemDto.setItemImg(itemImg);
        itemService.postItem(postItemDto, memberId);
        ResultDto result = new ResultDto(true);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{item_id}")
    public ResponseEntity putNftItem(@RequestHeader("Authorization") String accessToken,
                                     @RequestBody @Valid PutItemDto putItemDto,
                                     @PathVariable("item_id") Long itemId) {
        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));
        itemService.putItem(putItemDto, memberId, itemId);
        ResultDto result = new ResultDto(true);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{item_id}")
    public ResponseEntity deleteNftItem(@RequestHeader("Authorization") String accessToken,
                                        @PathVariable("item_id") Long itemId) {
        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));

        ResultDto result = new ResultDto(itemService.deleteItem(memberId, itemId));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/{item_id}/buy")
    public ResponseEntity buyNftItem(@RequestHeader("Authorization") String accessToken,
                                     @PathVariable("item_id") Long itemId,
                                     @RequestBody PostItemMemberDto postItemMemberDto) {
        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));
        ResultDto result = new ResultDto(itemService.buyItem(postItemMemberDto, itemId, memberId));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}