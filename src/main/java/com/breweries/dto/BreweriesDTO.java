package com.breweries.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BreweriesDTO {

    // Getters and setters for all fields
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("brewery_type")
    private String breweryType;

    @JsonProperty("address_1")
    private String address1;

    @JsonProperty("city")
    private String city;

    @JsonProperty("state_province")
    private String stateProvince;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("country")
    private String country;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("website_url")
    private String websiteUrl;

    @JsonProperty("state")
    private String state;

    @JsonProperty("street")
    private String street;

}
