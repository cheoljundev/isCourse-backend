package com.iscourse.api.domain.course;

import com.iscourse.api.domain.BaseEntity;
import jakarta.persistence.*;

@Entity
public class Place extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "place_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private PlaceType placeType;
    @Enumerated(EnumType.STRING)
    private largeCategory largeCategory;
    @Enumerated(EnumType.STRING)
    private middleCategory middleCategory;
    @Enumerated(EnumType.STRING)
    private smallCategory smallCategory;
    @Enumerated(EnumType.STRING)
    private State state;
    @Enumerated(EnumType.STRING)
    private City city;

    private String image;

}
