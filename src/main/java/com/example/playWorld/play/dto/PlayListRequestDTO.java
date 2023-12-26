package com.example.playWorld.play.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayListRequestDTO {

    public String stdate = LocalDate.now().toString(); // 공연시작일(필수)
    public String eddate = LocalDate.now().plusMonths(1).toString(); // 공연종료일(필수)
    public String cpage = "1"; // 현재페이지 (필수)
    public String rows = "10"; // 페이지당 목록 갯수 (필수)

    public String shprfnm; // 공연명
    public String shprfnmfct; // 공연시설명
    public String signgucode; // 지역(시도)코드
    public String signgucodesub; // 지역(구군)코드
    public String shcate = "AAAA"; // 장르코드 / 연극
    public String prfstate = "02"; // 공연상태코드 / 공연중

}
