package com.example.playWorld.report;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/allReport")
    public ResponseEntity getAllReport(){

        List<ReportDTO> allReports = reportService.getAllReports();

        return new ResponseEntity(allReports, HttpStatus.OK);
    }

    @GetMapping("/test")
    // TODO : 추후에 지우기
    public void test(){
        reportService.test();
    }


}
