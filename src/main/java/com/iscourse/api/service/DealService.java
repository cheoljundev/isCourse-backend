package com.iscourse.api.service;

import com.iscourse.api.domain.deal.Deal;
import com.iscourse.api.domain.deal.SalesDetail;
import com.iscourse.api.domain.member.Member;
import com.iscourse.api.exception.UnavailableEntityException;
import com.iscourse.api.repository.deal.DealRepository;
import com.iscourse.api.repository.deal.SalesDetailRepository;
import com.iscourse.api.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DealService {

    private final MemberRepository memberRepository;
    private final DealRepository dealRepository;
    private final SalesDetailRepository salesDetailRepository;

    public void purchase(Long memberId, Long dealId) {
        Deal deal = dealRepository.findById(dealId).orElseThrow(IllegalArgumentException::new);
        if (!deal.getEnabled()) {
            throw new UnavailableEntityException("구매할 수 없는 상품입니다.");
        }
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
        salesDetailRepository.save(new SalesDetail(deal, member));
    }
}
