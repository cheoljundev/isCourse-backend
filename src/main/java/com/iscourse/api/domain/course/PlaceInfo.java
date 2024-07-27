package com.iscourse.api.domain.course;

import com.iscourse.api.domain.Tag;
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
    private LargeCategory largeCategory;

    @ManyToOne
    @JoinColumn(name="middle_category_id")
    private MiddleCategory middleCategory;

    @ManyToOne
    @JoinColumn(name="tag_id")
    private Tag tag;

    @ManyToOne
    @JoinColumn(name="state_id")
    private State state;

    @ManyToOne
    @JoinColumn(name="city_id")
    private City city;
}
