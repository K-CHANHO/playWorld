package com.example.playWorld.report.repository;

import com.example.playWorld.report.dto.ReportDTO;
import com.example.playWorld.report.entity.QReportEntity;
import com.example.playWorld.report.entity.ReportEntity;
import com.example.playWorld.user.QUserEntity;
import com.example.playWorld.user.UserEntity;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class ReportRepositoryCustomImpl implements ReportRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    public List<ReportEntity> searchReportByTitleOrContent(String keyword) {
        QReportEntity report = QReportEntity.reportEntity;

        List<ReportEntity> searchList = jpaQueryFactory
                .selectFrom(report)
                .where(report.title.contains(keyword)
                        .or(report.content.contains(keyword))
                )
                .fetch();

        return searchList;
    }

    @Override
    public Page<ReportEntity> findPageBy(Pageable pageable) {
        QReportEntity report = QReportEntity.reportEntity;

        List<ReportEntity> content = jpaQueryFactory
                .selectFrom(report)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(report.reportId.desc())
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(report.count())
                .from(report);

        Page<ReportEntity> result = PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);

        return result;
    }

    @Override
    public Slice<ReportEntity> findSliceBy(Pageable pageable) {
        QReportEntity report = QReportEntity.reportEntity;

        List<ReportEntity> content = jpaQueryFactory
                .selectFrom(report)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .orderBy(report.reportId.desc())
                .fetch();

        boolean hasNext = false;
        if(content.size() > pageable.getPageSize()){
            content.remove(pageable.getPageSize());
            hasNext = true;
        }

        Slice<ReportEntity> result = new SliceImpl<>(content, pageable, hasNext);

        return result;
    }
}
