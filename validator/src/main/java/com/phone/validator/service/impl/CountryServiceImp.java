package com.phone.validator.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phone.validator.dal.dao.CountryDAO;
import com.phone.validator.dal.entity.Country;
import com.phone.validator.service.CountryService;

@Service
public class CountryServiceImp implements CountryService{
    
    private CountryDAO countryDAO;
    
    @Autowired
    public CountryServiceImp(CountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }
    
    @Override
    @Transactional
    public List<Country> getAllCountries() {
        return countryDAO.findAll();
    }

    @Override
    public List<Country> getCountriesByName(String countryName) {
        
        return countryDAO.findByName(countryName);
    }
}
