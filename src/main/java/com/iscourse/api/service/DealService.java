package com.iscourse.api.service;

import com.iscourse.api.domain.deal.Deal;
import com.iscourse.api.domain.deal.dto.DealDto;
import com.iscourse.api.repository.deal.DealQueryRepository;
import com.iscourse.api.repository.deal.DealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DealService {
    private final DealRepository dealRepository;
    private final DealQueryRepository dealQueryRepository;

}
