package com.phone.validator.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phone.validator.api.dto.Customer;
import com.phone.validator.dal.entity.Country;
import com.phone.validator.service.CountryService;
import com.phone.validator.service.CustomerService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CustomerRestControl {
    
    private CustomerService customerService;
    private CountryService countryService;

    @Autowired
    public CustomerRestControl(CustomerService customerService, CountryService countryService) {
        this.customerService = customerService;
        this.countryService = countryService;
    }
    
    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        List<Country> countries = countryService.getAllCountries();
        DataConverter dataConverter = new DataConverter(countries);
        return customerService.getAllCustomers().stream().map(dataConverter::mapToDTO).collect(Collectors.toList());
    }
    
    @GetMapping("/countries")
    public List<Country> getAllCountries(){
        return countryService.getAllCountries();
    }
    
    @GetMapping("/customers/{countryName}/{state}")
    public List<Customer> getFilteredCustomers(@PathVariable String countryName, @PathVariable String state){
        
        if (countryName.equals("all") && state.equals("all")) {
            DataConverter dataConverter = new DataConverter(countryService.getAllCountries());
            return customerService.getAllCustomers().stream().map(dataConverter::mapToDTO).collect(Collectors.toList());
            
        } else if (countryName.equals("all")) {
            DataConverter dataConverter = new DataConverter(countryService.getAllCountries());
            return dataConverter.getFilteredCustomers(customerService.getAllCustomers(), state);
            
        } else if (state.equals("all")) {
            DataConverter dataConverter = new DataConverter(countryService.getCountriesByName(countryName));
            return customerService.getAllCustomers().stream().map(dataConverter::mapToDTO)
                    .filter(customer -> customer.getCountryName() != null).collect(Collectors.toList());
            
        } else {
            DataConverter dataConverter = new DataConverter(countryService.getCountriesByName(countryName));
            return dataConverter.getFilteredCustomers(customerService.getAllCustomers(), state);
        }
    }
}
