package com.org.id.facturationservice.repositories;

import com.org.id.facturationservice.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill,Long> {

}
