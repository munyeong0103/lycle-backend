package com.example.lyclebackend.Nft.entity;

import com.example.lyclebackend.Member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="nft_item_like")
@Entity
public class NftItemLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nft_item_like_id")
    private Long nftItemLikeId;

    @ManyToOne
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "FK_member_nftitemlike"), insertable = false , updatable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "nft_item_id", foreignKey = @ForeignKey(name = "FK_nftitem_nftitemlike"), insertable = false , updatable = false)
    private NftItem nftItem;
}