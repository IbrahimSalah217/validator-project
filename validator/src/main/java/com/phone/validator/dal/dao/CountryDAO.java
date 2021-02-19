package com.phone.validator.dal.dao;

import java.util.List;

import com.phone.validator.dal.entity.Country;

public interface CountryDAO extends GenericDAO<Country>{

    List<Country> findByName(String countryName);
    
}