package com.example.playWorld.report.controller;

import com.example.playWorld.report.service.ReportService;
import com.example.playWorld.report.dto.ReportDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/saveReport")
    public ResponseEntity saveReport(ReportDTO reportDTO){

        ReportDTO savedReport = reportService.saveReport(reportDTO);

        return new ResponseEntity(savedReport, HttpStatus.OK);
    }

    @PostMapping("/searchReport")
    public ResponseEntity searchReport(String keyword){

        List<ReportDTO> reportDTOS = reportService.searchReportList(keyword);

        return new ResponseEntity(reportDTOS, HttpStatus.OK);
    }

}
