package com.iscourse.api.domain.course;

import com.iscourse.api.domain.BaseEntity;
import com.iscourse.api.domain.Tag;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public Place(PlaceType placeType, LargeCategory largeCategory, MiddleCategory middleCategory,
                 Tag tag, State state, City city, String address1,
                 String address2, String mapx, String mapy, String tel, String image) {
        this.placeType = placeType;
        this.largeCategory = largeCategory;
        this.middleCategory = middleCategory;
        this.tag = tag;
        this.state = state;
        this.city = city;
        this.address1 = address1;
        this.address2 = address2;
        this.mapx = mapx;
        this.mapy = mapy;
        this.tel = tel;
        this.image = image;
    }
}
