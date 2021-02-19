package com.phone.validator.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phone.validator.dal.dao.CustomerDAO;
import com.phone.validator.dal.entity.Customer;
import com.phone.validator.service.CustomerService;


@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO;
    
    @Autowired
    public CustomerServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }
    
    @Override
    @Transactional
    public List<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }
}
