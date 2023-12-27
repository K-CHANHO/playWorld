package com.example.playWorld.report.service;

import com.example.playWorld.report.dto.ReportDTO;
import com.example.playWorld.report.entity.ReportEntity;
import com.example.playWorld.report.repository.ReportRepository;
import com.example.playWorld.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
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

    public List<ReportDTO> getAllReport(){
        List<ReportDTO> reportDTOS = new ArrayList<>();

        List<ReportEntity> entities = reportRepository.findAll(Sort.by(Sort.Direction.DESC, "reportId"));
        entities.stream().forEach(entity -> reportDTOS.add(ReportDTO.toDTO(entity)));

        return reportDTOS;
    }

    /**
     * Page<Entity>를 Page<DTO>로 변환
     */
    public Page<ReportDTO> getAllReportAsPage(int page){

        // 페이징 조건
        Sort sort = Sort.by("reportId").descending();
        Pageable pageable = PageRequest.of(page, 10, sort);

        Page<ReportEntity> report = reportRepository.findPageBy(pageable);
        Page<ReportDTO> reportDTOS = report.map(ReportDTO::toDTO);

        return reportDTOS;
    }

    public Slice<ReportDTO> getAllReportAsSlice(int page){

        // 페이징 조건
        Sort sort = Sort.by("reportId").descending();
        Pageable pageable = PageRequest.of(page, 10, sort);

        Slice<ReportEntity> report = reportRepository.findSliceBy(pageable);
        Slice<ReportDTO> reportDTOS = report.map(ReportDTO::toDTO);

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
