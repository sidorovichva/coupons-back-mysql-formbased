package com.vs.couponsbackmysqlformbased.controllers;

import com.vs.couponsbackmysqlformbased.Exceptions.CouponRESTException;
import com.vs.couponsbackmysqlformbased.beans.Category;
import com.vs.couponsbackmysqlformbased.controllers.controllerInterfaces.CategoryControllerInterface;
import com.vs.couponsbackmysqlformbased.services.AdminFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoryController implements CategoryControllerInterface {

    private final AdminFacade adminFacade;

    @Override
    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody Category category) throws Exception {
        adminFacade.addCategory(category);
        return new ResponseEntity<>(CouponRESTException.CATEGORY_ADD.getSuccess(), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<?> getAllCategories() throws Exception {
        return ResponseEntity.ok().body(adminFacade.getAllCategories());
    }

}
