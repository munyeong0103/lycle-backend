package com.example.lyclebackend.Member.dto;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Data
public class PutMyPageDto {


    private String nickname;

    private String profileImg;

    private String password;

    private String salt;

}
