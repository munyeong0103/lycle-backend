package com.example.lyclebackend.Member.entity;

import com.example.lyclebackend.Item.dto.PutItemDto;
import com.example.lyclebackend.Item.entity.ItemMember;
import com.example.lyclebackend.Member.dto.PutMyPageDto;
import com.example.lyclebackend.Nft.entity.NftItem;
import com.example.lyclebackend.Nft.entity.NftItemLike;
import com.example.lyclebackend.Quest.entity.CurrentQuest;
import com.example.lyclebackend.Quest.entity.SuccessQuest;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="member")
@Entity
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long memberId;

    @Column(name="account_name")
    private String accountName;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="nickname")
    private String nickname;

    @Column(name="profile_img")
    private String profileImg;

    @Column(name="wallet_address", unique = true)
    private String walletAddress;

    @Column(name="salt")
    private String salt;

    @Column(name="token_cnt")
    private Integer tokenCnt;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<ItemMember> itemMemberList = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<NftItemLike> nftItemLikeList = new ArrayList<>();

    @OneToMany(mappedBy = "sellerId", fetch = FetchType.LAZY)
    private List<NftItem> nftItemSellList = new ArrayList<>();

    @OneToMany(mappedBy = "buyerId", fetch = FetchType.LAZY)
    private List<NftItem> nftItemBuyList = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<SuccessQuest> successQuestList = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<CurrentQuest> currentQuestList = new ArrayList<>();

    public void update(PutMyPageDto putMyPageDto) {
        this.nickname = putMyPageDto.getNickname();
        this.profileImg = putMyPageDto.getProfileImg();
    }
}
