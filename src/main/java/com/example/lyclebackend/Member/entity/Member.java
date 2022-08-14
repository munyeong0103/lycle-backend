package com.example.lyclebackend.Member.entity;

import com.example.lyclebackend.Item.entity.ItemMember;
import com.example.lyclebackend.Nft.entity.Nft;
import com.example.lyclebackend.Nft.entity.NftItemLike;
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

    @OneToMany(mappedBy = "member")
    private List<ItemMember> itemMemberList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<NftItemLike> nftItemLikeList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Nft> nftList = new ArrayList<>();
}
