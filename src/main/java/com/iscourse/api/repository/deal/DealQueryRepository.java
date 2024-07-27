package com.iscourse.api.repository.deal;

import com.iscourse.api.domain.RelatedType;
import com.iscourse.api.domain.deal.dto.DealDto;
import com.iscourse.api.domain.deal.dto.DealListDto;
import com.iscourse.api.domain.deal.dto.QDealDto;
import com.iscourse.api.domain.deal.dto.QDealListDto;
import com.iscourse.api.domain.dto.QUploadFileDto;
import com.iscourse.api.domain.dto.UploadFileDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.iscourse.api.domain.QUploadFile.uploadFile;
import static com.iscourse.api.domain.deal.QDeal.deal;

@Repository
@RequiredArgsConstructor
public class DealQueryRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public List<DealListDto> findList() {
        List<DealListDto> deals = queryFactory
                .select(new QDealListDto(deal))
                .from(deal)
                .where(deal.enabled.eq(true))
                .fetch();

        deals.forEach(dealDto -> {
            UploadFileDto image = queryFactory
                    .select(new QUploadFileDto(uploadFile))
                    .from(uploadFile)
                    .where(uploadFile.relatedType.eq(RelatedType.DEAL)
                            .and(uploadFile.relatedId.eq(dealDto.getId())))
                    .fetchFirst(); // Fetch the first image only

            dealDto.setImage(image);

        });
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
                    .select(new QUploadFileDto(uploadFile))
                    .from(uploadFile)
                    .where(uploadFile.relatedType.eq(RelatedType.DEAL).and(uploadFile.relatedId.eq(id)))
                    .fetch();

            dealDto.setImages(images);
        }

        // TODO: 추후 쿼리 최적화 가능한지 확인

        return dealDto;
    }
}
