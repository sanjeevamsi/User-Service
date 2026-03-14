package com.example.User.repo;

import com.example.User.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BudgetRepo extends JpaRepository<Budget, Long> {

    Optional<Budget> findByUserIdAndMonthAndYear(long userId, int month, int year);

}
