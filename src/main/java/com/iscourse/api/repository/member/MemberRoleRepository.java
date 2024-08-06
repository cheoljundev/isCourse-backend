package com.iscourse.api.repository.member;

import com.iscourse.api.domain.member.Member;
import com.iscourse.api.domain.member.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRoleRepository extends JpaRepository<MemberRole, Long>{
    List<MemberRole> findAllByMember(Member member);
}
