package com.vs.couponsbackmysqlformbased.controllers.controllerInterfaces;

import com.vs.couponsbackmysqlformbased.beans.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;

public interface PurchaseControllerInterface {
    ResponseEntity<?> getCustomerCoupons(User user) throws Exception;
    ResponseEntity<?> getCustomerCoupons(User user, Category category) throws Exception;
    ResponseEntity<?> getCustomerCoupons(User user, double maxPrice) throws Exception;
    ResponseEntity<?> getOneCouponByCustomer(User user, int id) throws Exception;
    ResponseEntity<?> getCustomerDetails(User user) throws Exception;
}
