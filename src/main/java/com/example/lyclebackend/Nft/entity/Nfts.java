package com.example.lyclebackend.Nft.entity;

import lombok.*;
import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="nfts")
@Entity
@AllArgsConstructor
@Builder
public class Nfts{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nfts_id")
    private Long nftsId;

    @Column(name="name")
    private String name;

    @Column(name="dna")
    private String dna;

    @Column(name="json_url", columnDefinition = "TEXT")
    private String jsonUrl;

}