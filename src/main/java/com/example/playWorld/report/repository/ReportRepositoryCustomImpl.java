package com.example.playWorld.report.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReportRepositoryCustomImpl implements ReportRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

}
