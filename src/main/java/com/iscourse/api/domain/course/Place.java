package com.iscourse.api.domain.course;

import com.iscourse.api.domain.BaseEntity;
import com.iscourse.api.domain.Tag;
import jakarta.persistence.*;

@Entity
public class Place extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "place_id")
    private Long id;
    
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

    private String address1;
    private String address2;
    private String mapx;
    private String mapy;
    private String tel;
    private String image;

}
