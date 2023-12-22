package com.example.playWorld.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class CommonTimeDTO {

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
