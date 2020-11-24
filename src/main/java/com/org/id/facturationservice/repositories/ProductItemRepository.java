package com.org.id.facturationservice.repositories;

import com.org.id.facturationservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductItemRepository  extends JpaRepository<ProductItem,Long> {
    List<ProductItem> findByBillId(long billId);
}
