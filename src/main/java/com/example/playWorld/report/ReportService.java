package com.example.playWorld.report;

import com.example.playWorld.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    public List<ReportDTO> getAllReports(){
        List<ReportDTO> reportDTOS = new ArrayList<>();

        List<ReportEntity> entities = reportRepository.findAll(Sort.by(Sort.Direction.DESC, "reportId"));
        entities.stream().forEach(entity -> reportDTOS.add(ReportDTO.toDTO(entity)));

        return reportDTOS;
    }


    // TODO : 추후에 지우기
    public void test(){
        reportRepository.test();
    }

}
