package com.vs.couponsbackmysqlformbased.controllers;

import com.vs.couponsbackmysqlformbased.Exceptions.CouponRESTException;
import com.vs.couponsbackmysqlformbased.beans.Category;
import com.vs.couponsbackmysqlformbased.beans.Company;
import com.vs.couponsbackmysqlformbased.beans.Coupon;
import com.vs.couponsbackmysqlformbased.controllers.controllerInterfaces.CouponControllerInterface;
import com.vs.couponsbackmysqlformbased.services.CompanyFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("coupons")
@RequiredArgsConstructor
public class CouponController implements CouponControllerInterface {

    private final CompanyFacade companyFacade;

    @Override
    @PostMapping
    public ResponseEntity<?> addCoupon(@AuthenticationPrincipal User user, @RequestBody Coupon coupon) throws Exception {
        Company company = companyFacade.getCompanyRepository().findCompanyByEmail(user.getUsername());
        companyFacade.addCoupon(company, coupon);
        return new ResponseEntity<>(CouponRESTException.COUPON_ADD.getSuccess(), HttpStatus.OK);
    }

    @Override
    @PutMapping
    public ResponseEntity<?> updateCoupon(@AuthenticationPrincipal User user, @RequestBody Coupon coupon) throws Exception {
        Company company = companyFacade.getCompanyRepository().findCompanyByEmail(user.getUsername());
        companyFacade.updateCoupon(company, coupon);
        return new ResponseEntity<>(CouponRESTException.COUPON_UPDATE.getSuccess(), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCoupon(@AuthenticationPrincipal User user, @PathVariable int id) throws Exception {
        Company company = companyFacade.getCompanyRepository().findCompanyByEmail(user.getUsername());
        companyFacade.deleteCoupon(company, id);
        return new ResponseEntity<>(CouponRESTException.COUPON_DELETE.getSuccess(), HttpStatus.OK);
    }

    @Override
    @GetMapping()
    public ResponseEntity<?> getCompanyCoupons(@AuthenticationPrincipal User user) throws Exception {
        Company company = companyFacade.getCompanyRepository().findCompanyByEmail(user.getUsername());
        return ResponseEntity.ok().body(companyFacade.getCompanyCoupons(company));
    }

    @Override
    @GetMapping("/{category}")
    public ResponseEntity<?> getCompanyCoupons(@AuthenticationPrincipal User user, @PathVariable Category category) throws Exception {
        Company company = companyFacade.getCompanyRepository().findCompanyByEmail(user.getUsername());
        return ResponseEntity.ok().body(companyFacade.getCompanyCoupons(company, category));
    }

    @Override
    @GetMapping("/{maxPrice}")
    public ResponseEntity<?> getCompanyCoupons(@AuthenticationPrincipal User user, @PathVariable double maxPrice) throws Exception {
        Company company = companyFacade.getCompanyRepository().findCompanyByEmail(user.getUsername());
        return ResponseEntity.ok().body(companyFacade.getCompanyCoupons(company, maxPrice));
    }

    @Override
    @GetMapping("/getone/{id}")
    public ResponseEntity<?> getOneCouponByCompany(@AuthenticationPrincipal User user, @PathVariable int id) throws Exception {
        Company company = companyFacade.getCompanyRepository().findCompanyByEmail(user.getUsername());
        return ResponseEntity.ok().body(companyFacade.getOneCouponByCompany(company, id));
    }

    @Override
    @GetMapping("/details")
    public ResponseEntity<?> getCompanyDetails(@AuthenticationPrincipal User user) throws Exception {
        Company company = companyFacade.getCompanyRepository().findCompanyByEmail(user.getUsername());
        return ResponseEntity.ok().body(companyFacade.getCompanyDetails(company));
    }
}
