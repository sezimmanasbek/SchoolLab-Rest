package com.cydeo.dto.weather;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class RequestDTO {

    private String type;
    private String query;
    private String language;

    private String unit;



}
