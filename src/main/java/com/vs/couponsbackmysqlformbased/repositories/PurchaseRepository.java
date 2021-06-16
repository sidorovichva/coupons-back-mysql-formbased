package com.vs.couponsbackmysqlformbased.repositories;

import com.vs.couponsbackmysqlformbased.beans.Coupon;
import com.vs.couponsbackmysqlformbased.beans.Customer;
import com.vs.couponsbackmysqlformbased.beans.Purchase;
import com.vs.couponsbackmysqlformbased.beans.PurchaseID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, PurchaseID> {

    boolean existsByCouponAndCustomer(Coupon coupon, Customer customer);

    List<Purchase> getPurchasesByCustomer (Customer customer);
}
