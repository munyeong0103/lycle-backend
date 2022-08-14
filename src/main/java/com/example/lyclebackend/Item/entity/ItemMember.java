package com.example.lyclebackend.Item.entity;

import com.example.lyclebackend.Member.entity.Member;
import com.example.lyclebackend.converter.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="item_member")
@Entity
public class ItemMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_member_id")
    private Long itemMemberId;

    @Column(name="count")
    private Long count;

    @Column(name="status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "FK_member_itemmember"), insertable = false , updatable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "item_id", foreignKey = @ForeignKey(name = "FK_item_itemmember"), insertable = false , updatable = false)
    private Item item;
}