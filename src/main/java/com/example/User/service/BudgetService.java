package com.example.User.service;

import com.example.User.dto.BudgetDTO;
import com.example.User.model.Budget;
import com.example.User.model.User;
import com.example.User.repo.BudgetRepo;
import com.example.User.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepo budgetRepo;


    public void addMonthlyBudget(String userId, BudgetDTO dto, int month, int year) {
        long userIdFromGateway = Long.parseLong(userId);
        Optional<Budget> existing = budgetRepo.findByUserIdAndMonthAndYear(userIdFromGateway, month, year);
        Budget budget = existing.orElse(new Budget());
        budget.setUserId(userIdFromGateway);
        budget.setMonthlyLimit(dto.getMonthlyLimit());
        budget.setMonth(month);
        budget.setYear(year);
        budgetRepo.save(budget);
    }

    public BigDecimal getMonthlyBudget(String userId, int month, int year) {
        long userIdFromGateway = Long.parseLong(userId);
        return budgetRepo.findByUserIdAndMonthAndYear(userIdFromGateway, month, year)
                .map(Budget::getMonthlyLimit) // Extract the limit if present
                .orElse(BigDecimal.ZERO);
    }
}
