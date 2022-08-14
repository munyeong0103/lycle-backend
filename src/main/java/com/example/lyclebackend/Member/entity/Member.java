package com.example.lyclebackend.Member.entity;

import com.example.lyclebackend.Item.entity.ItemMember;
import com.example.lyclebackend.Nft.entity.Nft;
import com.example.lyclebackend.Nft.entity.NftItemLike;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="member")
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long memberId;

    @Column(name="email")
    private String email;

    @Column(name="profile_img")
    private String profileImg;

    @Column(name="wallet_address")
    private String walletAddress;

    @OneToMany(mappedBy = "member")
    private List<ItemMember> itemMemberList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<NftItemLike> nftItemLikeList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Nft> nftList = new ArrayList<>();
}
