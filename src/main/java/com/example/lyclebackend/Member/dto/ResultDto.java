package com.example.lyclebackend.Member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResultDto {
    private boolean result;

    public ResultDto(boolean result) {
        this.result = result;
    }
}
