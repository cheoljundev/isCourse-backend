package com.iscourse.api.domain.member;

import lombok.Getter;

@Getter
public enum MemberRoleType{
    ROLE_USER(3), ROLE_MANAGER(2), ROLE_ADMIN(1);

    private final int priority;

    MemberRoleType(int priority) {
        this.priority = priority;
    }
}
