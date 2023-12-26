package com.example.playWorld.report.repository;

import com.example.playWorld.report.dto.ReportDTO;
import com.example.playWorld.report.entity.QReportEntity;
import com.example.playWorld.report.entity.ReportEntity;
import com.example.playWorld.user.QUserEntity;
import com.example.playWorld.user.UserEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class ReportRepositoryCustomImpl implements ReportRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;


    // TODO : 추후에 지우기
    public void test(){
        QReportEntity report = QReportEntity.reportEntity;
        QUserEntity user = QUserEntity.userEntity;

        List<UserEntity> fetch1 = jpaQueryFactory.selectFrom(user).fetch();

        log.info(fetch1.toString());
    }

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
}
