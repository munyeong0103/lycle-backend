package com.example.lyclebackend.Nft.entity;

import com.example.lyclebackend.Item.entity.ItemMember;
import com.example.lyclebackend.Member.entity.Member;
import com.example.lyclebackend.Nft.dto.PutNftItemDto;
import com.example.lyclebackend.converter.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="nft_item")
@Entity
@AllArgsConstructor
@Builder
public class NftItem extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nft_item_id")
    private Long nftItemId;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @Column(name="price")
    private Integer price;

    @Column(name="nft_item_img")
    private String nftItemImg;

    @Column(name="view_cnt")
    private Integer viewCnt;

    @Column(name="nft_id")
    private Long nftId;

    @ManyToOne
    @JoinColumn(name = "seller_id", foreignKey = @ForeignKey(name = "FK_member_nftitem_seller"), insertable = false , updatable = false)
    private Member seller;

    @Column(name = "seller_id")
    private Long sellerId;

    @ManyToOne
    @JoinColumn(name = "buyer_id", foreignKey = @ForeignKey(name = "FK_member_nftitem_buyer"), insertable = false , updatable = false)
    private Member buyer;

    @Column(name = "buyer_id")
    private Long buyerId;

    @OneToMany(mappedBy = "nftItem", fetch = FetchType.LAZY)
    private List<NftItemLike> nftItemLikeList = new ArrayList<>();

    public void update(PutNftItemDto putNftItemDto) {
        this.title = putNftItemDto.getTitle();
        this.content = putNftItemDto.getContent();
        this.price = putNftItemDto.getPrice();
    }
}