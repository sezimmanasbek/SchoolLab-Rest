package com.cydeo.dto.weather;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Current {


    private String observationTime;

    private Integer temperature;

    private Integer weatherCode;

    private List<String> weatherIcons;

    private List<String> weatherDescriptions;
    private Integer windSpeed;

    private Integer windDegree;

    private String windDir;

    private Integer pressure;

    private Integer precip;

    private Integer humidity;

    private Integer cloudcover;

    private Integer feelslike;

    private Integer uvIndex;

    private Integer visibility;

    private String isDay;

}
