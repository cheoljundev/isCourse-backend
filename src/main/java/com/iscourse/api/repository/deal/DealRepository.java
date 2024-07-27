package com.iscourse.api.repository.deal;

import com.iscourse.api.domain.deal.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, Long> {
}
