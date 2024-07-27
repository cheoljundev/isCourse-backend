package com.iscourse.api.domain.course;

import com.iscourse.api.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.Persistable;

@Entity @Getter
public class City {
    @Id @GeneratedValue
    @Column(name = "city_id")
    private Long id;
    private String code;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private State parent;

}
