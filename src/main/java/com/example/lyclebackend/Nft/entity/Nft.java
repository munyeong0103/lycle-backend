package com.example.lyclebackend.Nft.entity;

import com.example.lyclebackend.Member.entity.Member;
import com.example.lyclebackend.converter.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="nft")
@Entity
public class Nft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nft_id")
    private Long nftId;

    @Column(name="nft_img")
    private String nftImg;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="title")
    private String title;

    @Column(name="effect")
    private String effect;

    @Column(name="status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "FK_member_nft"), insertable = false , updatable = false)
    private Member member;

    @OneToOne(mappedBy = "nft")
    private NftItem nftItem;
}
