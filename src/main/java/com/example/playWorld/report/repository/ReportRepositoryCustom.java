package com.example.playWorld.report.repository;

import com.example.playWorld.report.entity.ReportEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ReportRepositoryCustom {

    Page<ReportEntity> findPageBy(Pageable pageable);

    Slice<ReportEntity> findSliceBy(Pageable pageable);

    List<ReportEntity> searchReportByTitleOrContent(String keyword);
}
