package com.iscourse.api.service;

import com.iscourse.api.domain.deal.Deal;
import com.iscourse.api.domain.deal.dto.DealDto;
import com.iscourse.api.repository.deal.DealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DealService {
    private final DealRepository dealRepository;

    public DealDto findOne(Long id) {
        Deal deal = dealRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return new DealDto(deal);
    }
}
