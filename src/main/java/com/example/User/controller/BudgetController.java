package com.example.User.controller;

import com.example.User.dto.BudgetDTO;
import com.example.User.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping("/getMonthlyBudget")
    public BigDecimal getMonthlyBudget(@RequestHeader("X-User-Id")String userId, @RequestParam int month, @RequestParam(required = false) Integer year) {
        System.out.println("i am here!!");
        int targetYear = (year == null) ? LocalDate.now().getYear() : year;
        return budgetService.getMonthlyBudget(userId, month, targetYear);
    }

    @PostMapping("/addBudget")
    public ResponseEntity<String> addMonthlyBudget (@RequestHeader("X-User-Id") String userId,  @RequestBody BudgetDTO dto,@RequestParam int month, @RequestParam(required = false) Integer year) {
        int targetYear = (year == null) ? LocalDate.now().getYear() : year;
        budgetService.addMonthlyBudget(userId,dto,month,targetYear);
        return ResponseEntity.ok("Budget saved successfully");
    }

    @GetMapping("/userIdCheck")
    public String getCreds(@RequestHeader("X-User-Id") String userId) {
        return "user id is accessible!! " + userId;
    }
}
