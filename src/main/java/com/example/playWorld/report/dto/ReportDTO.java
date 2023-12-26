package com.example.playWorld.report.dto;

import com.example.playWorld.common.CommonTimeDTO;
import com.example.playWorld.report.entity.ReportEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@DynamicUpdate
public class ReportDTO extends CommonTimeDTO {

    private long reportId;
    private String userId;
    private String userNickname;
    private String title;
    private String content;

    public static ReportEntity toEntity(ReportDTO dto){
        ReportEntity entity = ReportEntity.builder()
                .reportId(dto.getReportId())
                .userId(dto.getUserId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();

        return entity;
    }

    public static ReportDTO toDTO(ReportEntity entity) {
        ReportDTO dto = ReportDTO.builder()
                .reportId(entity.getReportId())
                .userId(entity.getUserId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .createTime(entity.getCreateTime())
                .updateTime(entity.getUpdateTime())
                .build();

        return dto;
    }

}
