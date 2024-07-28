package com.iscourse.api.domain.course;

import com.iscourse.api.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class City {
    @Id @GeneratedValue
    @Column(name = "city_id")
    private Long id;
    private String code;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private State parent;

    public City(String code, String name, State parent) {
        this.code = code;
        this.name = name;
        this.parent = parent;
    }
}
