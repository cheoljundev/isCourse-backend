package com.iscourse.api.repository.deal;

import com.iscourse.api.domain.RelatedType;
import com.iscourse.api.domain.deal.dto.*;
import com.iscourse.api.domain.dto.QUploadFileDto;
import com.iscourse.api.domain.dto.UploadFileDto;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.iscourse.api.domain.QUploadFile.uploadFile;
import static com.iscourse.api.domain.deal.QDeal.deal;

@Repository
@RequiredArgsConstructor
public class DealQueryRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public List<DealListDto> findList() {
        JPQLQuery<String> originalFileNameSubquery = JPAExpressions
                .select(uploadFile.originalFileName)
                .from(uploadFile)
                .where(uploadFile.relatedType.eq(RelatedType.DEAL)
                        .and(uploadFile.relatedId.eq(deal.id)))
                .limit(1);

        JPQLQuery<String> storedFileNameSubquery = JPAExpressions
                .select(uploadFile.storedFileName)
                .from(uploadFile)
                .where(uploadFile.relatedType.eq(RelatedType.DEAL)
                        .and(uploadFile.relatedId.eq(deal.id)))
                .limit(1);

        JPQLQuery<String> fileTypeSubquery = JPAExpressions
                .select(uploadFile.fileType)
                .from(uploadFile)
                .where(uploadFile.relatedType.eq(RelatedType.DEAL)
                        .and(uploadFile.relatedId.eq(deal.id)))
                .limit(1);

        List<DealListDto> deals = queryFactory
                .select(new QDealListDto(
                        deal,
                        new QUploadFileDto(
                                originalFileNameSubquery,
                                storedFileNameSubquery,
                                fileTypeSubquery)
                ))
                .from(deal)
                .where(deal.enabled.eq(true))
                .fetch();

        return deals;
    }

    public DealDto findOne(Long id) {

        DealDto dealDto = queryFactory
                .select(new QDealDto(deal))
                .from(deal)
                .where(deal.id.eq(id))
                .fetchOne();

        if (dealDto != null) {
            List<UploadFileDto> images = queryFactory
                    .select(new QUploadFileDto(
                            uploadFile.originalFileName,
                            uploadFile.storedFileName,
                            uploadFile.fileType
                    ))
                    .from(uploadFile)
                    .where(uploadFile.relatedType.eq(RelatedType.DEAL).and(uploadFile.relatedId.eq(id)), uploadFile.enabled.eq(true))
                    .fetch();

            dealDto.setImages(images);
        }
        return dealDto;

    }

    public Page<DealAdminListDto> findAdminList(String name, Integer minPrice, Integer maxPrice, Pageable pageable) {

        JPQLQuery<DealAdminListDto> query = queryFactory
                .select(new QDealAdminListDto(
                        deal
                ))
                .from(deal)
                .where(deal.name.contains(name)
                        .and(deal.price.goe(minPrice))
                        .and(deal.price.loe(maxPrice))
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<DealAdminListDto> contents = query.fetch();

        int total = contents.size();
        return PageableExecutionUtils.getPage(contents, pageable, () -> total);
    }
}
