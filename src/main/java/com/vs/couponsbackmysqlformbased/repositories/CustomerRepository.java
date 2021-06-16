package com.vs.couponsbackmysqlformbased.repositories;

import com.vs.couponsbackmysqlformbased.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, int id);

    Customer findCustomerByEmailAndPassword(String email, String password);

    Customer findCustomerByEmail(String email);
}
