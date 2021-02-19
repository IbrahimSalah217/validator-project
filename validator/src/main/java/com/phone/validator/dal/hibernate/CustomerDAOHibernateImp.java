package com.phone.validator.dal.hibernate;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.phone.validator.dal.dao.CustomerDAO;
import com.phone.validator.dal.entity.Customer;

@Repository
public class CustomerDAOHibernateImp extends HibernateDAO<Customer> implements CustomerDAO {

    @Autowired
    public CustomerDAOHibernateImp(EntityManager entityManager) {
        super(entityManager);
    }
}
