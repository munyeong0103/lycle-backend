package com.example.lyclebackend.Item.entity;

import com.example.lyclebackend.Item.dto.PutItemDto;
import com.example.lyclebackend.converter.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="item")
@Entity
@AllArgsConstructor
@Builder
public class Item extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_id")
    private Long itemId;

    @Column(name="title")
    private String title;

    @Column(name="price")
    private Integer price;

    @Column(name="content")
    private String content;

    @Column(name="item_img")
    private String itemImg;

    @Column(name="view_cnt")
    private Integer viewCnt;

    @OneToMany(mappedBy = "item")
    private List<ItemMember> itemMemberList = new ArrayList<>();

    public void update(PutItemDto putItemDto) {
        this.title = putItemDto.getTitle();
        this.content = putItemDto.getContent();
        this.price = putItemDto.getPrice();
    }

    public void updateViweCnt() {
        this.viewCnt = this.viewCnt + 1;
    }
}
