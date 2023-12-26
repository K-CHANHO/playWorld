package com.example.playWorld.report.repository;

import com.example.playWorld.report.entity.ReportEntity;

import java.util.List;

public interface ReportRepositoryCustom {

    void test();

    List<ReportEntity> searchReportByTitleOrContent(String keyword);
}
