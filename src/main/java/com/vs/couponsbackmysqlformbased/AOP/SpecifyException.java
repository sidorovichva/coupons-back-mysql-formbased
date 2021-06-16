package com.vs.couponsbackmysqlformbased.AOP;

import com.vs.couponsbackmysqlformbased.Exceptions.CouponRESTException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SpecifyException {
    CouponRESTException exception() default CouponRESTException.COMPANY_EXISTS;
}
