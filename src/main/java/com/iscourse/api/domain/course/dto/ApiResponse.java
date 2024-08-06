package com.iscourse.api.domain.course.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ApiResponse {

    @JsonProperty("response")
    private Response response;

    @Getter
    public static class Response {
        @JsonProperty("header")
        private Header header;

        @JsonProperty("body")
        private Body body;

    }

    public static class Header {
        @JsonProperty("resultCode")
        private String resultCode;

        @JsonProperty("resultMsg")
        private String resultMsg;

        // Getters and Setters
    }

    @Getter
    public static class Body {
        @JsonProperty("items")
        private Items items;

        @JsonProperty("numOfRows")
        private int numOfRows;

        @JsonProperty("pageNo")
        private int pageNo;

        @JsonProperty("totalCount")
        private int totalCount;

    }

    @Getter
    public static class Items {
        @JsonProperty("item")
        private List<Item> itemList;

    }

    @Getter
    public static class Item {
        @JsonProperty("addr1")
        private String address1;

        @JsonProperty("addr2")
        private String address2;

        @JsonProperty("areacode")
        private String stateCode;

        @JsonProperty("sigungucode")
        private String cityCode;

        @JsonProperty("cat1")
        private String largeCategoryCode;

        @JsonProperty("cat2")
        private String middleCategoryCode;

        @JsonProperty("cat3")
        private String tagCode;

        @JsonProperty("contenttypeid")
        private String placeTypeCode;

        @JsonProperty("firstimage")
        private String image;

        @JsonProperty("mapx")
        private String mapx;

        @JsonProperty("mapy")
        private String mapy;

        @JsonProperty("tel")
        private String tel;

        @JsonProperty("title")
        private String name;

        @JsonProperty("zipcode")
        private String zipcode;

    }
}
