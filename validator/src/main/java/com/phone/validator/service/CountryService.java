package com.phone.validator.service;

import java.util.List;

import com.phone.validator.dal.entity.Country;

public interface CountryService {
    
    public List<Country> getAllCountries();

    public List<Country> getCountriesByName(String countryName);
}
