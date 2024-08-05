package com.iscourse.api.domain.course;

import com.iscourse.api.domain.BaseEntity;
import com.iscourse.api.domain.member.MemberRoleType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "course_id")
    private Long id;

    private String name;

    @Column(name = "hours")
    private Integer hour;

    @Column(name = "minutes")
    private Integer minute;

    @Lob
    private String introduce;

    @Enumerated(EnumType.STRING)
    private MemberRoleType courseType;

    private int likes;

    private Double mapx;
    private Double mapy;

    public void setAxis(Double mapx, Double mapy) {
        if (this.mapx == null || this.mapy == null) {
            this.mapx = mapx;
            this.mapy = mapy;
        }
    }

    public Course(String name, Integer hour, Integer minute, String introduce, MemberRoleType courseType) {
        this.name = name;
        this.hour = hour;
        this.minute = minute;
        this.introduce = introduce;
        this.courseType = courseType;
        this.likes = 0;
    }

    public void subtractLike() {
        this.likes--;
    }

    public void addLike() {
        this.likes++;
    }

    public void update(String name, Integer hour, Integer minute, String introduce) {
        this.name = name;
        this.hour = hour;
        this.minute = minute;
        this.introduce = introduce;
        this.mapx = null;
        this.mapy = null;
    }

    public void delete() {
        this.enabled = false;
    }

}
