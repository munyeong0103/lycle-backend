package com.example.lyclebackend.Item.dto;

import com.example.lyclebackend.Item.entity.Item;
import com.example.lyclebackend.Item.entity.ItemMember;
import com.example.lyclebackend.Item.entity.ItemStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostItemMemberDto {

    private ItemStatus status;

    private Integer count;

    private Long memberId;

    private Long itemId;

    public ItemMember toEntity(){
        ItemMember build = ItemMember.builder()
                .status(status)
                .count(count)
                .memberId(memberId)
                .itemId(itemId)
                .build();
        return build;
    }

}
