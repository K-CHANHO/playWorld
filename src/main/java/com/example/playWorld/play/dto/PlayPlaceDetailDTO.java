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
public class PlayPlaceDetailDTO {

    @XmlElement(name = "db")
    private List<PlaceDetailSubDTO> db;

    @Data
    @XmlRootElement(name = "db")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class PlaceDetailSubDTO {

        @XmlElement(name = "fcltynm")
        private String placeName; // 공연시설명

        @XmlElement(name = "seatscale")
        private String seatScale; // 객석 수

        @XmlElement(name = "relateurl")
        private String placeURL; // 홈페이지

        @XmlElement(name = "adres")
        private String address; // 주소

        @XmlElement(name = "parkinglot")
        private boolean parking; // 주차가능여부

        @XmlElement(name = "la")
        private String latitude; // 위도

        @XmlElement(name = "lo")
        private String longitude; // 경도

    }
}
