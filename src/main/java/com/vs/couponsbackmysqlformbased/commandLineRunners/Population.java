package com.vs.couponsbackmysqlformbased.commandLineRunners;

import com.vs.couponsbackmysqlformbased.beans.*;
import com.vs.couponsbackmysqlformbased.services.AdminFacade;
import com.vs.couponsbackmysqlformbased.services.CompanyFacade;
import com.vs.couponsbackmysqlformbased.services.CustomerFacade;
import com.vs.couponsbackmysqlformbased.utils.Entries;
import com.vs.couponsbackmysqlformbased.utils.MyArrayDouble;
import com.vs.couponsbackmysqlformbased.utils.MyArrayInt;
import com.vs.couponsbackmysqlformbased.utils.MyArrayString;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Date;

//@Component
@Order(1)
@RequiredArgsConstructor
public class Population implements CommandLineRunner {

    private final AdminFacade adminFacade;
    private final CompanyFacade companyFacade;
    private final CustomerFacade customerFacade;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        int companies = 2;
        int customers = 2;
        int categories = 3;
        int coupons = 10;
        int purchases = 10;

        for (int i = 0; i < companies; i++) {
            String name = Entries.randomString(2).toString();
            String pass = Entries.randomString(1).toString();
            if (i == 0) {
                name = "company";
                pass = "com";
            }
            try {
                Company company = Company.builder()
                        .name(MyArrayString.randomEntry(MyArrayString.myCompanyNames))
                        .email(name)
                        .password(passwordEncoder.encode(pass))
                        .build();
                adminFacade.addCompany(company);
            } catch (Exception e) {
                continue;
            }
        }

        for (int i = 0; i < customers; i++) {
            String name = Entries.randomString(3).toString();
            String pass = Entries.randomString(1).toString();
            if (i == 0) {
                name = "customer";
                pass = "cus";
            }
            try {
                Customer customer = Customer.builder()
                        .firstName(MyArrayString.randomEntry(MyArrayString.myNames))
                        .lastName(MyArrayString.randomEntry(MyArrayString.myLastNames))
                        .email(name)
                        .password(passwordEncoder.encode(pass))
                        .build();
                adminFacade.addCustomer(customer);
            } catch (Exception e) {
                continue;
            }
        }

        for (int i = 0; i < categories; i++) {
            try {
                Category category = Category.builder()
                        .name(MyArrayString.randomEntry(MyArrayString.myCategoryTitles))
                        .build();
                adminFacade.addCategory(category);
            } catch (Exception e) {
                continue;
            }
        }

        for (int i = 0; i < coupons; i++) {
            try {
                Company company = adminFacade.getOneCompany(MyArrayInt.randomNumber(1, companies));
                Category category = adminFacade.getOneCategory(MyArrayInt.randomNumber(1, categories));
                Long time = System.currentTimeMillis() + Entries.randomNumber(0, 2600000000l);
                Coupon coupon = Coupon.builder()
                        .category(category)
                        .company(company)
                        .title(MyArrayString.randomEntry(MyArrayString.myCouponTitles))
                        .description(Entries.randomString(10).toString())
                        .startDate(new Date(time))
                        .endDate(new Date(time + Entries.randomNumber(0, 2600000000l)))
                        .amount(MyArrayInt.randomNumber(1, 50) * 100)
                        .price(Math.round(MyArrayDouble.randomNumber(20.0, 500.0) * 100.0) / 100.0)
                        .image(Entries.randomString(20).toString())
                        .build();
                companyFacade.addCoupon(company, coupon);
            } catch (Exception e) {
                continue;
            }
        }

        for (int i = 0; i < purchases; i++) {
            try {
                Coupon coupon = customerFacade.getOneCoupon(MyArrayInt.randomNumber(1, coupons));
                Customer customer = adminFacade.getOneCustomer(MyArrayInt.randomNumber(1, customers));
                customerFacade.purchaseCoupon(customer,coupon);
            }
            catch (Exception e) {
                continue;
            }
        }
    }
}
