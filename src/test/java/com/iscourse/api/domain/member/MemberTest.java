package com.iscourse.api.domain.member;

import com.iscourse.api.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Commit
    public void save() {
        // given
        Member member = new Member("member", "1234", GenderType.MAN, "nick");
        member.addRole(MemberRole.create(MemberRoleType.ROLE_USER));
        //when
        memberRepository.save(member);

        //then
    }
}