package com.library.management.databaseService.checkout.repository;

import com.library.management.databaseService.checkout.entity.Checkout;
import com.library.management.databaseService.checkout.model.CheckOutRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckOutRepository extends JpaRepository<Checkout,Integer> {

    List<Checkout> findByUserId(Integer userId);
    Checkout findByUserIdAndBookId(Integer userId,String BookId);
}
