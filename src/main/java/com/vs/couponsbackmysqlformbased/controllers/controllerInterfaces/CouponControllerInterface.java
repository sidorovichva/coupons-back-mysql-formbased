package com.vs.couponsbackmysqlformbased.controllers.controllerInterfaces;

import com.vs.couponsbackmysqlformbased.beans.Category;
import com.vs.couponsbackmysqlformbased.beans.Coupon;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;

public interface CouponControllerInterface {
    ResponseEntity<?> addCoupon(User user, Coupon coupon) throws Exception;
    ResponseEntity<?> updateCoupon(User user, Coupon coupon) throws Exception;
    ResponseEntity<?> deleteCoupon(User user, int couponID) throws Exception;
    ResponseEntity<?> getCompanyCoupons(User user) throws Exception;
    ResponseEntity<?> getCompanyCoupons(User user, Category category) throws Exception;
    ResponseEntity<?> getCompanyCoupons(User user, double maxPrice) throws Exception;
    ResponseEntity<?> getOneCouponByCompany(User user, int couponID) throws Exception;
    ResponseEntity<?> getCompanyDetails(User user) throws Exception;
}
