package com.phone.validator.dal.dao;

import java.util.List;

import com.phone.validator.dal.entity.Entity;

public interface GenericDAO <T extends Entity>{

    public List<T> findAll();
}
