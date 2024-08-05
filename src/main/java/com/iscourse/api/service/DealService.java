package com.iscourse.api.service;

import com.iscourse.api.controller.dto.deal.AddDealDto;
import com.iscourse.api.domain.RelatedType;
import com.iscourse.api.domain.UploadFile;
import com.iscourse.api.domain.deal.Deal;
import com.iscourse.api.domain.deal.SalesDetail;
import com.iscourse.api.domain.member.Member;
import com.iscourse.api.exception.UnavailableEntityException;
import com.iscourse.api.repository.UploadFileRepository;
import com.iscourse.api.repository.deal.DealRepository;
import com.iscourse.api.repository.deal.SalesDetailRepository;
import com.iscourse.api.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DealService {

    private final MemberRepository memberRepository;
    private final DealRepository dealRepository;
    private final SalesDetailRepository salesDetailRepository;
    private final UploadFileService uploadFileService;
    private final UploadFileRepository uploadFileRepository;

    @Transactional
    public void purchase(Long memberId, Long dealId) {
        Deal deal = dealRepository.findById(dealId).orElseThrow(IllegalArgumentException::new);
        if (!deal.getEnabled()) {
            throw new UnavailableEntityException("구매할 수 없는 상품입니다.");
        }
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
        salesDetailRepository.save(new SalesDetail(deal, member));
    }

    @Transactional
    public void add(List<MultipartFile> files, AddDealDto addDealDto) throws IOException {
        Deal deal = new Deal(
                addDealDto.getStation(),
                addDealDto.getName(),
                addDealDto.getProduct(),
                addDealDto.getBeforePrice(),
                addDealDto.getPrice(),
                (int)((double) addDealDto.getPrice() / addDealDto.getBeforePrice() * 100),
                addDealDto.getOpening(),
                addDealDto.getClosing(),
                addDealDto.getAddress1(),
                addDealDto.getAddress2(),
                addDealDto.getContact(),
                addDealDto.getMapx(),
                addDealDto.getMapy(),
                addDealDto.isParking()
        );
        dealRepository.save(deal);
        for (MultipartFile file : files) {
            uploadFileService.save(file, RelatedType.DEAL, deal.getId());
        }
    }

    @Transactional
    public void update(Long id, List<MultipartFile> files, AddDealDto addDealDto) throws IOException {
        Deal deal = dealRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        deal.update(
                addDealDto.getStation(),
                addDealDto.getName(),
                addDealDto.getProduct(),
                addDealDto.getBeforePrice(),
                addDealDto.getPrice(),
                (int)((double) addDealDto.getPrice() / addDealDto.getBeforePrice() * 100),
                addDealDto.getOpening(),
                addDealDto.getClosing(),
                addDealDto.getAddress1(),
                addDealDto.getAddress2(),
                addDealDto.getContact(),
                addDealDto.getMapx(),
                addDealDto.getMapy(),
                addDealDto.isParking()
        );
        uploadFileRepository.findByRelatedId(deal.getId()).forEach(UploadFile::delete);
        for (MultipartFile file : files) {
            uploadFileService.save(file, RelatedType.DEAL, deal.getId());
        }
    }
}
