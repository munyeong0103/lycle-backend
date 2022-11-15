package com.example.lyclebackend.Member.controller;


import com.example.lyclebackend.Item.dto.PutItemDto;
import com.example.lyclebackend.Member.dto.PutMyPageDto;
import com.example.lyclebackend.Member.dto.ResultDto;
import com.example.lyclebackend.Member.dto.SignUpDto;
import com.example.lyclebackend.Member.repository.MemberRepository;
import com.example.lyclebackend.Member.service.AwsS3Service;
import com.example.lyclebackend.Member.service.MemberService;
import com.example.lyclebackend.Member.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/myPage")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class MyPageController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final JwtUtil jwtUtil;
    private final AwsS3Service awsS3Service;

    @GetMapping("")
    public ResponseEntity findNftItemup(@RequestHeader("Authorization") String accessToken,
                                      @PathVariable("member_id") Long myPageMemberId) {
        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));
        return ResponseEntity.status(HttpStatus.OK).body(memberService.findMyPage(myPageMemberId, memberId));
    }

    @GetMapping("/{member_id}")
    public ResponseEntity findNftItem(@RequestHeader("Authorization") String accessToken,
                                      @PathVariable("member_id") Long myPageMemberId) {
        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));
        return ResponseEntity.status(HttpStatus.OK).body(memberService.findMyPage(myPageMemberId, memberId));
    }

    @PostMapping("/check/{member_id}")
    public ResponseEntity findPassword(@RequestHeader("Authorization") String accessToken,
                                       @RequestBody SignUpDto signUpDto,
                                       @PathVariable("member_id") Long myPageMemberId) {
        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));
        ResultDto result = new ResultDto();
        result.setResult(memberService.checkPassword(signUpDto, memberId, myPageMemberId));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{member_id}")
    public ResponseEntity putNftItem(@RequestHeader("Authorization") String accessToken,
                                     @RequestPart(value = "putMyPageDto") PutMyPageDto putMyPageDto,
                                     @PathVariable("member_id") Long myPageMemberId,
                                     @RequestPart(value = "file") MultipartFile multipartFile) {
        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));
        String profileImg = awsS3Service.uploadFileV1("profileImg", multipartFile);
        putMyPageDto.setProfileImg(profileImg);
        memberService.putMyPage(putMyPageDto, memberId, myPageMemberId);
        ResultDto result = new ResultDto(false);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/like/{member_id}")
    public ResponseEntity findList(@PathVariable("member_id") Long myPageMemberId,
                                   @RequestHeader("Authorization") String accessToken,
                                   @RequestParam(name = "title", required = false, defaultValue = "") String title,
                                   @RequestParam(name = "sort", required = false, defaultValue = "recent") String sort,
                                   Pageable pageable) {
        Long memberId = memberRepository.findMemberIdByAccountName(jwtUtil.extractUsername(accessToken.substring(7)));
        return ResponseEntity.status(HttpStatus.OK).body(memberService.findList(title, sort, pageable, myPageMemberId, memberId));
    }

}
