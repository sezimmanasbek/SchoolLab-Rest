package com.cydeo.service.impl;

import com.cydeo.client.FlagClient;
import com.cydeo.client.WeatherClient;
import com.cydeo.dto.AddressDTO;
import com.cydeo.dto.weather.WeatherDTOClient;
import com.cydeo.entity.Address;
import com.cydeo.util.MapperUtil;
import com.cydeo.repository.AddressRepository;
import com.cydeo.service.AddressService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Value("${access_key}")
    private String access_key;
    private final AddressRepository addressRepository;
    private final MapperUtil mapperUtil;
    private final WeatherClient weatherClient;
    private final FlagClient flagClient;

    public AddressServiceImpl(AddressRepository addressRepository, MapperUtil mapperUtil, WeatherClient weatherClient, FlagClient flagClient) {
        this.addressRepository = addressRepository;
        this.mapperUtil = mapperUtil;
        this.weatherClient = weatherClient;
        this.flagClient = flagClient;
    }

    @Override
    public List<AddressDTO> findAll() {
        return addressRepository.findAll()
                .stream()
                .map(address -> mapperUtil.convert(address, new AddressDTO()))
                .map(address -> {
                    Integer temp = getWeather(address.getState()).getCurrent().getTemperature();
                    address.setCurrentTemperature(temp);
                    return address;
                })
                .collect(Collectors.toList());
    }

    @Override
    public AddressDTO findById(Long id) throws Exception {
        Address foundAddress = addressRepository.findById(id)
                .orElseThrow(() -> new Exception("No Address Found!"));
        AddressDTO address = mapperUtil.convert(foundAddress, new AddressDTO());
        address.setCurrentTemperature(getWeather(address.getCity()).getCurrent().getTemperature());
        address.setFlag(getCountryFlag(address.getCountry()));
        return address;
    }

    private String getCountryFlag(String country) {
       return flagClient.getFlag(country).get(0).getFlags().getPng();
    }

    @Override
    public AddressDTO update(AddressDTO addressDTO) throws Exception {

        addressRepository.findById(addressDTO.getId())
                .orElseThrow(() -> new Exception("No Address Found!"));

        Address addressToSave = mapperUtil.convert(addressDTO, new Address());

        addressRepository.save(addressToSave);

        return mapperUtil.convert(addressToSave, new AddressDTO());

    }

    @Override
    public AddressDTO create(AddressDTO addressDTO) throws Exception {

        Optional<Address> foundAddress = addressRepository.findById(addressDTO.getId());

        if (foundAddress.isPresent()) {
            throw new Exception("Address Already Exists!");
        }

        Address addressToSave = mapperUtil.convert(addressDTO, new Address());

        addressRepository.save(addressToSave);

        return mapperUtil.convert(addressToSave, new AddressDTO());

    }

    private WeatherDTOClient getWeather(String state){

       return weatherClient.getWeatherC(access_key,state);

    }

}