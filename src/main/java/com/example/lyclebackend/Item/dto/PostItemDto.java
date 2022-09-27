package com.example.lyclebackend.Item.dto;

import com.example.lyclebackend.Item.entity.Item;
import com.example.lyclebackend.Nft.entity.NftItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.swing.text.StyledEditorKit;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostItemDto {

    private String itemImg;

    private String title;

    private Integer price;

    private String content;

    private Integer viewCnt;

    private Boolean isDelete;

    public Item toEntity(){
        Item build = Item.builder()
                .itemImg(itemImg)
                .title(title)
                .price(price)
                .content(content)
                .viewCnt(viewCnt)
                .isDelete(isDelete)
                .build();
        return build;
    }
}