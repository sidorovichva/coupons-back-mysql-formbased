package com.vs.couponsbackmysqlformbased.controllers;

import com.vs.couponsbackmysqlformbased.beans.Category;
import com.vs.couponsbackmysqlformbased.beans.Customer;
import com.vs.couponsbackmysqlformbased.controllers.controllerInterfaces.PurchaseControllerInterface;
import com.vs.couponsbackmysqlformbased.repositories.CustomerRepository;
import com.vs.couponsbackmysqlformbased.services.CustomerFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("purchases")
@RequiredArgsConstructor
public class PurchaseController implements PurchaseControllerInterface {

    private final CustomerFacade customerFacade;
    private final CustomerRepository customerRepository;

    @Override
    @GetMapping()
    public ResponseEntity<?> getCustomerCoupons(@AuthenticationPrincipal User user) throws Exception {
        Customer customer = customerFacade.getCustomerRepository().findCustomerByEmail(user.getUsername());
        return ResponseEntity.ok().body(customerFacade.getCustomerCoupons(customer));
    }

    @Override
    @GetMapping("/{category}")
    public ResponseEntity<?> getCustomerCoupons(@AuthenticationPrincipal User user, @PathVariable Category category) throws Exception {
        Customer customer = customerRepository.findCustomerByEmail(user.getUsername());
        return ResponseEntity.ok().body(customerFacade.getCustomerCoupons(customer, category));
    }

    @Override
    @GetMapping("/{maxPrice}")
    public ResponseEntity<?> getCustomerCoupons(@AuthenticationPrincipal User user, @PathVariable double maxPrice) throws Exception {
        Customer customer = customerRepository.findCustomerByEmail(user.getUsername());
        return ResponseEntity.ok().body(customerFacade.getCustomerCoupons(customer, maxPrice));
    }

    @Override
    @GetMapping("/getone/{id}")
    public ResponseEntity<?> getOneCouponByCustomer(@AuthenticationPrincipal User user, @PathVariable int id) throws Exception {
        Customer customer = customerRepository.findCustomerByEmail(user.getUsername());
        return ResponseEntity.ok().body(customerFacade.getCustomerCoupons(customer, id));
    }

    @Override
    @GetMapping("/details")
    public ResponseEntity<?> getCustomerDetails(@AuthenticationPrincipal User user) throws Exception {
        Customer customer = customerRepository.findCustomerByEmail(user.getUsername());
        return ResponseEntity.ok().body(customerFacade.getCustomerDetails(customer));
    }
}
