package org.example.springbootproj2.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootproj2.config.LoanProperties;
import org.example.springbootproj2.model.Car;
import org.example.springbootproj2.model.User;
import org.m004ka.service.IncomeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final UserService userService;
    private final LoanProperties loanProperties;
    private final IncomeService client;


    @Override
    public int loanApproval(Long id) {
        User user = userService.getUserById(id);

        int income = client.getIncome(id);

        int priceCar = Optional.ofNullable(user)
                .map(User::getCar)
                .map(Car::getPrice)
                .orElse(0);

        if (approvalVerification(income, priceCar)) {
            return getLoanAmount(income, priceCar);
        } else {
            return 0;
        }
    }

    private int getLoanAmount(int income, int priceCar) {

        int loanByCar = priceCar * loanProperties.getByCarPercent() / 100;

        int loanByIncome = income * loanProperties.getByIncomePeriod();

        return Math.max(loanByCar, loanByIncome);
    }

    private boolean approvalVerification(int income, int priceCar) {
        return income > loanProperties.getMinIncome() || priceCar > loanProperties.getMinPriceCar();
    }

}
