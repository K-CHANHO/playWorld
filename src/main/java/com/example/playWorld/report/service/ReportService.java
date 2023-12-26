package com.example.playWorld.report.service;

import com.example.playWorld.report.dto.ReportDTO;
import com.example.playWorld.report.entity.ReportEntity;
import com.example.playWorld.report.repository.ReportRepository;
import com.example.playWorld.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    public List<ReportDTO> getAllReports(){
        List<ReportDTO> reportDTOS = new ArrayList<>();

        List<ReportEntity> entities = reportRepository.findAll(Sort.by(Sort.Direction.DESC, "reportId"));
        entities.stream().forEach(entity -> reportDTOS.add(ReportDTO.toDTO(entity)));

        return reportDTOS;
    }


    public ReportDTO saveReport(ReportDTO reportDTO) {

        ReportEntity savedReport = reportRepository.save(ReportDTO.toEntity(reportDTO));
        ReportDTO savedReportDTO = ReportDTO.toDTO(savedReport);
        String nickname = userRepository.findById(savedReport.getUserId()).orElseThrow().getNickname();
        savedReportDTO.setUserNickname(nickname);

        return savedReportDTO;
    }

    public List<ReportDTO> searchReportList(String keyword){

        List<ReportDTO> reportDTOS = new ArrayList<>();
        List<ReportEntity> reportEntities = reportRepository.searchReportByTitleOrContent(keyword);
        reportEntities.stream().forEach(entity -> reportDTOS.add(ReportDTO.toDTO(entity)));

        return reportDTOS;

    }
}
