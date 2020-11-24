package com.org.id.facturationservice.interfaces;

import com.org.id.facturationservice.entities.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerServiceClient {
    @GetMapping("/customers/{id}?projection=fullCustomer")
    CustomerDTO findCustomerById(@PathVariable("id") Long id);
}
