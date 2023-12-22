package com.example.playWorld.report.repository;

import com.example.playWorld.report.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<ReportEntity, Long>, ReportRepositoryCustom {
}
