package com.phone.validator.dal.hibernate;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.phone.validator.dal.dao.CountryDAO;
import com.phone.validator.dal.entity.Country;

@Repository
public class CountryDAOHibernateImp extends HibernateDAO<Country> implements CountryDAO {

    @Autowired
    public CountryDAOHibernateImp(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Country> findByName(String countryName) {
        return createQueryWithListResult((builder, root, query) ->
                    query.select(root).where(builder.equal(root.get("name"), countryName)));
    }
}