package com.example.playWorld.play.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@XmlRootElement(name = "dbs")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayListResultDTO {

    @XmlElement(name = "db")
    private List<PlayListSubResultDTO> db;

    @Data
    @XmlRootElement(name = "db")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class PlayListSubResultDTO {

        @XmlElement(name = "mt20id")
        private String mt20id; // 공연 ID

        @XmlElement(name = "prfnm")
        private String prfnm; // 공연명

        @XmlElement(name = "prfpdfrom")
        private String prfpdfrom; // 공연시작일

        @XmlElement(name = "prfpdto")
        private String prfpdto; // 공연종료일

        @XmlElement(name = "fcltynm")
        private String fcltynm; // 공연장명

        @XmlElement(name = "poster")
        private String poster; // 포스터경로

        @XmlElement(name = "genrenm")
        private String genrenm; // 장르

        @XmlElement(name = "openrun")
        private String openrun; // 오픈런

        @XmlElement(name = "prfstate")
        private String prfstate; // 공연상태
    }

}
