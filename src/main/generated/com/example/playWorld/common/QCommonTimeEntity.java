package com.example.playWorld.common;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommonTimeEntity is a Querydsl query type for CommonTimeEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QCommonTimeEntity extends EntityPathBase<CommonTimeEntity> {

    private static final long serialVersionUID = -1995241923L;

    public static final QCommonTimeEntity commonTimeEntity = new QCommonTimeEntity("commonTimeEntity");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> updateTime = createDateTime("updateTime", java.time.LocalDateTime.class);

    public QCommonTimeEntity(String variable) {
        super(CommonTimeEntity.class, forVariable(variable));
    }

    public QCommonTimeEntity(Path<? extends CommonTimeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommonTimeEntity(PathMetadata metadata) {
        super(CommonTimeEntity.class, metadata);
    }

}

