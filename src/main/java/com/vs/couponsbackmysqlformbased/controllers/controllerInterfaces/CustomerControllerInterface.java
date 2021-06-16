package com.vs.couponsbackmysqlformbased.controllers.controllerInterfaces;

import com.vs.couponsbackmysqlformbased.beans.Customer;
import org.springframework.http.ResponseEntity;

public interface CustomerControllerInterface {
    ResponseEntity<?> addCustomer(Customer customer) throws Exception;
    ResponseEntity<?> updateCustomer(Customer customer) throws Exception;
    ResponseEntity<?> deleteCustomer(int id) throws Exception;
    ResponseEntity<?> getAllCustomers() throws Exception;
    ResponseEntity<?> getOneCustomer(int id) throws Exception;
}
