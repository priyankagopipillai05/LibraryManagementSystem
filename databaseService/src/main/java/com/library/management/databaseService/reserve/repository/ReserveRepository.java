package com.library.management.databaseService.reserve.repository;

import com.library.management.databaseService.reserve.entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve,String> {
    List<Reserve> findByBookId(String bookId);
}
