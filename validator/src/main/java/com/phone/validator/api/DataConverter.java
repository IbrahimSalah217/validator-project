package com.phone.validator.api;

import java.util.List;
import java.util.stream.Collectors;

import com.phone.validator.api.dto.Customer;
import com.phone.validator.dal.entity.Country;

public class DataConverter {
    
    private List<Country> countries;
    
    public DataConverter(List<Country> countries) {
        this.countries = countries;
    }

    public Customer mapToDTO(com.phone.validator.dal.entity.Customer customer) {
        boolean validPhone = false;
        String customerCountry = null;
        String countryCode = null;
        String phone = customer.getPhone();
        
        for (Country country : countries) {
            if (phone.matches(country.getRegex())) {
                customerCountry = country.getName();
                countryCode = country.getCode();
                validPhone = true;
                break;
                
            } else if (phone.startsWith("(" + country.getCode() + ")")) {
                customerCountry = country.getName();
                countryCode = country.getCode();
                break;
            }
        }
        
        return new Customer(customer.getId(), customer.getName(), phone, customerCountry, countryCode, validPhone);
    }
    
    public List<Customer> getFilteredCustomers(List<com.phone.validator.dal.entity.Customer> customers, String state) {
        return customers.stream().map(this::mapToDTO)
                .filter(customer -> (customer.getCountryName() != null) && (state.equals("valid") ? customer.isValid() : (state.equals("not valid") ? !customer.isValid() : false)))
                .collect(Collectors.toList());
    }
}
