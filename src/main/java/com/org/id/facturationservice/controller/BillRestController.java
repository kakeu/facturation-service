package com.org.id.facturationservice.controller;

import com.org.id.facturationservice.entities.Bill;
import com.org.id.facturationservice.interfaces.CustomerServiceClient;
import com.org.id.facturationservice.interfaces.InventoryServiceClient;
import com.org.id.facturationservice.repositories.BillRepository;
import com.org.id.facturationservice.repositories.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;

    private CustomerServiceClient customerServiceClient;
    private InventoryServiceClient inventoryServiceClient;

    @GetMapping("/bills/full/{id}")
    Bill getBill (@PathVariable(name ="id") Long id){
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerServiceClient.findCustomerById(bill.getCustomerID()));
        bill.setProductItems(productItemRepository.findByBillId(id));
        bill.getProductItems().forEach(pi ->{
            pi.setProduct(inventoryServiceClient.findProductById(pi.getProductId()));
        } );
        return bill;
    }
}
