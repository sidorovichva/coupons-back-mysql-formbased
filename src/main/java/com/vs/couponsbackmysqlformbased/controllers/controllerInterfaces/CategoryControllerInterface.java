package com.vs.couponsbackmysqlformbased.controllers.controllerInterfaces;

import com.vs.couponsbackmysqlformbased.beans.Category;
import org.springframework.http.ResponseEntity;

public interface CategoryControllerInterface {
    ResponseEntity<?> addCategory(Category category) throws Exception;
    ResponseEntity<?> getAllCategories() throws Exception;
}
