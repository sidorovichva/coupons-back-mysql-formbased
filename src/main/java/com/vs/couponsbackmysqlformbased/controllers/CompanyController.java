package com.vs.couponsbackmysqlformbased.controllers;

import com.vs.couponsbackmysqlformbased.Exceptions.CouponRESTException;
import com.vs.couponsbackmysqlformbased.beans.Company;
import com.vs.couponsbackmysqlformbased.controllers.controllerInterfaces.CompanyControllerInterface;
import com.vs.couponsbackmysqlformbased.services.AdminFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("companies")
@RequiredArgsConstructor
public class CompanyController implements CompanyControllerInterface {

    private final AdminFacade adminFacade;

    @Override
    @PostMapping()
    public ResponseEntity<?> addCompany(@RequestBody Company company) throws Exception{
        adminFacade.addCompany(company);
        return new ResponseEntity<>(CouponRESTException.COMPANY_ADD.getSuccess(), HttpStatus.OK);
    }

    @Override
    @PutMapping
    public ResponseEntity<?> updateCompany(@RequestBody Company company) throws Exception{
        adminFacade.updateCompany(company);
        return new ResponseEntity<>(CouponRESTException.COMPANY_UPDATE.getSuccess(), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable int id) throws Exception{
        adminFacade.deleteCompany(id);
        return new ResponseEntity<>(CouponRESTException.COMPANY_DELETE.getSuccess(), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<?> getAllCompanies() throws Exception {
        return ResponseEntity.ok().body(adminFacade.getAllCompanies());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneCompany(@PathVariable int id) throws Exception {
        return ResponseEntity.ok().body(adminFacade.getOneCompany(id));
    }


}
