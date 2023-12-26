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
public class PlayDetailResultDTO {

    @XmlElement(name = "db")
    private List<PlayDetailSubDTO> db;

    @Data
    @XmlRootElement(name = "db")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class PlayDetailSubDTO {

        @XmlElement(name = "prfnm")
        private String playName; // 공연명

        @XmlElement(name = "mt10id")
        private String placeId; // 공연시설 ID

        @XmlElement(name = "fcltynm")
        private String placeName; // 공연시설명

        @XmlElement(name = "prfpdfrom")
        private String startDate; // 공연시작일

        @XmlElement(name = "prfpdto")
        private String endDate; // 공연종료일

        @XmlElement(name = "prfcast")
        private String actors; // 출연진

        @XmlElement(name = "prfruntime")
        private String runtime; // 런닝타임

        @XmlElement(name = "prfage")
        private String ageLimit; // 관람연령

        @XmlElement(name = "entrpsnmP")
        private String production; // 제작사

        @XmlElement(name = "pcseguidance")
        private String price; // 티켓가격

        @XmlElement(name = "sty")
        private String story; // 스토리

        @XmlElement(name = "prfstate")
        private String status; // 공연상태

    }

}
