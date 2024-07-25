package com.iscourse.api.domain.course;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class PlaceInfo {
    @ManyToOne
    @JoinColumn(name = "place_type_id")
    private PlaceType placeType;

    @ManyToOne
    @JoinColumn(name="large_category_id")
    private largeCategory largeCategory;

    @ManyToOne
    @JoinColumn(name="middle_category_id")
    private middleCategory middleCategory;

    @ManyToOne
    @JoinColumn(name="small_category_id")
    private smallCategory smallCategory;

    @ManyToOne
    @JoinColumn(name="state_id")
    private State state;

    @ManyToOne
    @JoinColumn(name="city_id")
    private City city;
}
