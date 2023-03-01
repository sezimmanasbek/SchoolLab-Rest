package com.cydeo.dto.weather;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class WeatherDTOClient {

    @JsonProperty("request")
    private RequestDTO request;
    @JsonProperty("location")
    private LocationDTO location;
    @JsonProperty("current")
    private CurrentDTO current;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


}
