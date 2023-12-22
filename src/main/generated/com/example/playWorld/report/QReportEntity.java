package com.example.playWorld.report;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReportEntity is a Querydsl query type for ReportEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReportEntity extends EntityPathBase<ReportEntity> {

    private static final long serialVersionUID = -1416595600L;

    public static final QReportEntity reportEntity = new QReportEntity("reportEntity");

    public final com.example.playWorld.common.QCommonTimeEntity _super = new com.example.playWorld.common.QCommonTimeEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createTime = _super.createTime;

    public final NumberPath<Long> reportId = createNumber("reportId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public final StringPath userId = createString("userId");

    public final StringPath userNickname = createString("userNickname");

    public QReportEntity(String variable) {
        super(ReportEntity.class, forVariable(variable));
    }

    public QReportEntity(Path<? extends ReportEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReportEntity(PathMetadata metadata) {
        super(ReportEntity.class, metadata);
    }

}

