package com.iscourse.api.repository.deal;

import com.iscourse.api.domain.RelatedType;
import com.iscourse.api.domain.deal.dto.DealDto;
import com.iscourse.api.domain.deal.dto.DealListDto;
import com.iscourse.api.domain.deal.dto.QDealDto;
import com.iscourse.api.domain.deal.dto.QDealListDto;
import com.iscourse.api.domain.dto.QUploadFileDto;
import com.iscourse.api.domain.dto.UploadFileDto;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
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
                    .where(uploadFile.relatedType.eq(RelatedType.DEAL).and(uploadFile.relatedId.eq(id)))
                    .fetch();

            dealDto.setImages(images);
        }
        return dealDto;

    }
}
