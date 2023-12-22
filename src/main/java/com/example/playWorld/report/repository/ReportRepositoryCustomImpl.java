package com.example.playWorld.report.repository;

import com.example.playWorld.report.QReportEntity;
import com.example.playWorld.report.ReportEntity;
import com.example.playWorld.user.QUserEntity;
import com.example.playWorld.user.UserEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class ReportRepositoryCustomImpl implements ReportRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    public void test(){
        QReportEntity report = QReportEntity.reportEntity;
        QUserEntity user = QUserEntity.userEntity;

        List<UserEntity> fetch1 = jpaQueryFactory.selectFrom(user).fetch();

        log.info(fetch1.toString());
    }
}
