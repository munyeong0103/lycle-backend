package com.example.lyclebackend.Nft.entity;

import com.example.lyclebackend.Member.entity.Member;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="nft_item_like")
@Entity
@AllArgsConstructor
@Builder
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

    @Column(name = "nft_item_id")
    private Long nftItemId;

    @Column(name = "member_id")
    private Long memberId;
}