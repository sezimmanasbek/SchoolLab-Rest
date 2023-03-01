package com.cydeo.dto.weather;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class LocationDTO {


    private String name;

    private String country;

    private String region;

    private String lat;

    private String lon;

    private String timezoneId;

    private String localtime;

    private Integer localtimeEpoch;

    private String utcOffset;

}
