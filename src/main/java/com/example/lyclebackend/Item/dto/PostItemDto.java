package com.example.lyclebackend.Item.dto;

import com.example.lyclebackend.Item.entity.Item;
import com.example.lyclebackend.Nft.entity.NftItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.swing.text.StyledEditorKit;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostItemDto {

    @NotBlank(message = "아이템 이미지는 필수 입력사항 입니다.")
    private String itemImg;

    @NotBlank(message = "제목은 필수 입력사항 입니다.")
    private String title;


    private Integer price;

    @NotBlank(message = "내용은 필수 입력사항 입니다.")
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