package com.iscourse.api.domain.course;

import com.iscourse.api.domain.BaseEntity;
import jakarta.persistence.*;

@Entity
public class Place extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "place_id")
    private Long id;

    @Embedded
    private PlaceInfo placeInfo;

    private String image;

}
