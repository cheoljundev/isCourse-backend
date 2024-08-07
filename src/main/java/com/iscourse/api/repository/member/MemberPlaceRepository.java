package com.iscourse.api.repository.member;

import com.iscourse.api.domain.member.MemberPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberPlaceRepository extends JpaRepository<MemberPlace, Long> {
}
