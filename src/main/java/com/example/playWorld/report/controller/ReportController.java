package com.example.playWorld.report.controller;

import com.example.playWorld.report.dto.ReportDTO;
import com.example.playWorld.report.service.ReportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/allReport")
    public ResponseEntity getAllReport(){

        List<ReportDTO> allReports = reportService.getAllReport();

        return new ResponseEntity(allReports, HttpStatus.OK);
    }

    @GetMapping("/allReport/{page}")
    public ResponseEntity getAllReportAsPage(@PathVariable("page") int page) throws JsonProcessingException {

        Page<ReportDTO> allReportAsPage = reportService.getAllReportAsPage(page);
        Slice<ReportDTO> allReportAsSlice = reportService.getAllReportAsSlice(page);

        Map<String, Object> result = new HashMap<>();
        result.put("page", allReportAsPage);
        result.put("slice", allReportAsSlice);

        return new ResponseEntity(result, HttpStatus.OK);
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
