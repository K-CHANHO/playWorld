package com.example.playWorld.report;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    public ResponseEntity getAllReport(){

        List<ReportDTO> allReports = reportService.getAllReports();

        return new ResponseEntity(allReports, HttpStatus.OK);
    }
}
