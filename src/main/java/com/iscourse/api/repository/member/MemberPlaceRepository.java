package com.iscourse.api.repository.member;

import com.iscourse.api.domain.course.Place;
import com.iscourse.api.domain.member.Member;
import com.iscourse.api.domain.member.MemberPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberPlaceRepository extends JpaRepository<MemberPlace, Long> {
    Optional<MemberPlace> findByMemberAndPlace(Member member, Place place);
}
