package com.example.lyclebackend.Nft.entity;

import com.example.lyclebackend.Item.entity.ItemMember;
import com.example.lyclebackend.Member.entity.Member;
import com.example.lyclebackend.converter.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="nft_item")
@Entity
public class NftItem extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nft_itme_id")
    private Long nftItemId;

    @Column(name="seller_id")
    private Long sellerId;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @Column(name="price")
    private Long price;

    @Column(name="nft_item_img")
    private String nftItemImg;

    @Column(name="view")
    private Long view;

    @OneToOne
    @JoinColumn(name = "nft_id", foreignKey = @ForeignKey(name = "FK_nft_nftinfo"), insertable = false , updatable = false)
    private Nft nft;

    @OneToMany(mappedBy = "nftItem")
    private List<NftItemLike> nftItemLikeList = new ArrayList<>();
}