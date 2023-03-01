package com.cydeo.client;

import com.cydeo.dto.country.CountryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "https://restcountries.com/v3.1",name ="FLAG-CLIENT")
public interface FlagClient {

    @GetMapping("/name/{name}")
    List<CountryDTO> getFlag(@PathVariable("name") String name);
}
