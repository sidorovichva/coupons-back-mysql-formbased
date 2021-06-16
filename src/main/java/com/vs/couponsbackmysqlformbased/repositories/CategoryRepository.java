package com.vs.couponsbackmysqlformbased.repositories;

import com.vs.couponsbackmysqlformbased.beans.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsByName(String name);
}
