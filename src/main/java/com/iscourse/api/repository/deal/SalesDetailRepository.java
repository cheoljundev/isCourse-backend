package com.iscourse.api.repository.deal;

import com.iscourse.api.domain.deal.SalesDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesDetailRepository extends JpaRepository<SalesDetail, Long> {
}
