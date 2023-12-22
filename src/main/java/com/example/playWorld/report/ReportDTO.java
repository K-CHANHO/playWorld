package com.example.playWorld.report;

import com.example.playWorld.common.CommonTimeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@DynamicUpdate
public class ReportDTO extends CommonTimeDTO {

    private long reportId;
    private String userId;
    private String userNickname;
    private String content;

    public static ReportEntity toEntity(ReportDTO dto){
        ReportEntity entity = ReportEntity.builder()
                .reportId(dto.getReportId())
                .userId(dto.getUserId())
                .userNickname(dto.getUserNickname())
                .content(dto.getContent())
                .build();

        return entity;
    }

    public static ReportDTO toDTO(ReportEntity entity) {
        ReportDTO dto = ReportDTO.builder()
                .reportId(entity.getReportId())
                .userId(entity.getUserId())
                .userNickname(entity.getUserNickname())
                .content(entity.getContent())
                .createTime(entity.getCreateTime())
                .updateTime(entity.getUpdateTime())
                .build();

        return dto;
    }

}
