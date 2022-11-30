package com.example.lyclebackend.Member.service;


import com.example.lyclebackend.Item.dto.ItemListDto;
import com.example.lyclebackend.Member.dto.FindMyPageDto;
import com.example.lyclebackend.Member.dto.PutMyPageDto;
import com.example.lyclebackend.Member.dto.SignUpDto;
import com.example.lyclebackend.Member.entity.Member;
import com.example.lyclebackend.Member.repository.MemberRepository;
import com.example.lyclebackend.Member.util.SaltUtil;
import com.example.lyclebackend.Nft.dto.NftItemListDto;
import com.example.lyclebackend.error.ErrorCode.PutMyPageErrorCode;
import com.example.lyclebackend.error.ErrorCode.SignUpErrorCode;
import com.example.lyclebackend.error.Exception.RestApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.regex.Pattern;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final SaltUtil saltUtil;
    private final MyUserDetailsService userDetailsService;

    @Transactional
    public FindMyPageDto findMyPage(Long myPageMemberId, Long memberId) {
        FindMyPageDto findMyPageDto = memberRepository.findMemberBy(myPageMemberId, memberId);
        return findMyPageDto;
    }

    @Transactional
    public Boolean checkPassword(SignUpDto signUpDto, Long memberId, Long myPageMemberId) {
        if (memberId == myPageMemberId) {
            Member member = memberRepository.findByMemberId(memberId);
            String salt = member.getSalt();
            String encodePassword = saltUtil.encodePassword(salt, signUpDto.getPassword());
            if (member.getPassword().equals(encodePassword)) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } else {
            return Boolean.FALSE;
        }



    }

    @Transactional
    public boolean putMyPage(PutMyPageDto putMyPageDto, Long memberId, Long myPageMemberId) {
        Member member = memberRepository.findByMemberId(myPageMemberId);
        if (myPageMemberId == memberId) {
            if (putMyPageDto.getPassword() != null && putMyPageDto.getPassword().length() != 0){

                String pattern = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}";
                boolean result = Pattern.matches(pattern, putMyPageDto.getPassword());
                if(!result) {
                    throw new RestApiException(PutMyPageErrorCode.FAIL_PUT_PAGE_PASSWORD);
                }

                String salt = saltUtil.genSalt();
                putMyPageDto.setSalt(salt);
                putMyPageDto.setPassword(saltUtil.encodePassword(salt, putMyPageDto.getPassword()));
            } else {
                putMyPageDto.setSalt(member.getSalt());
                putMyPageDto.setPassword(member.getPassword());
            }

            if (putMyPageDto.getNickname() == null) {
                putMyPageDto.setNickname(member.getNickname());
            } else {
                String pattern = "^[ㄱ-ㅎ가-힣a-z0-9-_]{4,10}$";
                boolean result = Pattern.matches(pattern, putMyPageDto.getNickname());
                if(member.getNickname() != putMyPageDto.getNickname()){
                    if(!result) {
                        throw new RestApiException(PutMyPageErrorCode.FAIL_PUT_PAGE_NICKNAME);
                    }
                }


                if(memberRepository.existsByNickname(putMyPageDto.getNickname())){
                    throw new RestApiException(SignUpErrorCode.FAIL_SIGNUP_NICKNAME);
                }
            }

            if (putMyPageDto.getProfileImg() == null) {
                putMyPageDto.setProfileImg(member.getProfileImg());
            }

            member.update(putMyPageDto);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public NftItemListDto findList(String keyword, String sort, Pageable pageable, Long myPageMemberId,Long memberId) {
        NftItemListDto nftItemListDto = new NftItemListDto();
        if (myPageMemberId != memberId) {
            return nftItemListDto;
        } else {
            nftItemListDto.setPageCnt((int) pageable.getOffset());
            nftItemListDto.setLimit(pageable.getPageSize());
            nftItemListDto.setItemList(memberRepository.findListBy(keyword, sort, pageable, memberId));
            return nftItemListDto;
        }
    }

    @Transactional
    public ItemListDto findBuyList(String keyword, String sort, Pageable pageable, Long myPageMemberId, Long memberId) {
        ItemListDto itemListDto = new ItemListDto();
        if (myPageMemberId != memberId) {
            return itemListDto;
        } else {
            itemListDto.setPageCnt((int) pageable.getOffset());
            itemListDto.setLimit(pageable.getPageSize());
            itemListDto.setItemList(memberRepository.findBuyListBy(keyword, sort, pageable, memberId));
            return itemListDto;
        }
    }

}
