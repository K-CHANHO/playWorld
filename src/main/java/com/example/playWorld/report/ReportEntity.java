package com.example.playWorld.report;

import com.example.playWorld.common.CommonTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PW_report")
public class ReportEntity extends CommonTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @Column
    private String userId;

    @Column
    private String userNickname;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String content;

}
