package com.org.id.facturationservice;

import com.org.id.facturationservice.entities.Bill;
import com.org.id.facturationservice.entities.CustomerDTO;
import com.org.id.facturationservice.entities.ProductItem;
import com.org.id.facturationservice.interfaces.CustomerServiceClient;
import com.org.id.facturationservice.interfaces.InventoryServiceClient;
import com.org.id.facturationservice.repositories.BillRepository;
import com.org.id.facturationservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class FacturationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacturationServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository,
                            InventoryServiceClient inventoryServiceClient, CustomerServiceClient customerServiceClient){

        return args -> {
            Bill bill = new Bill();
            bill.setBillingDate(new Date());
            CustomerDTO customerDTO = customerServiceClient.findCustomerById(1L);
            bill.setCustomerID(customerDTO.getId());
            inventoryServiceClient.findAll().getContent().forEach(p ->{
                productItemRepository.save(new ProductItem(null,null,p.getId(),p.getPrice(),(int)(1+Math.random()*1000),bill));
            });
        };
    }

}
