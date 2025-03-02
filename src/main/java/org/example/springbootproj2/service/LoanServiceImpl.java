package org.example.springbootproj2.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootproj2.config.LoanProperties;
import org.example.springbootproj2.model.User;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final UserService userService;
    private final LoanProperties loanProperties;


    @Override
    public int loanApproval(int income, long id) {
        User user = userService.getUserById(id);
        System.out.println(user);
        int priceCar = 0;

        if (user.getCar() != null) {
            priceCar = user.getCar().getPrice();
        }


        if (approvalVerification(income, priceCar)) {
            return getLoanAmount(income, priceCar);
        } else {
            return 0;
        }
    }

    private int getLoanAmount(int income, int priceCar) {

        int loanByCar = priceCar * loanProperties.getLoanByCarPercent() / 100;

        int loanByIncome = income * loanProperties.getLoanByIncomePeriod();

        return Math.max(loanByCar, loanByIncome);
    }

    private boolean approvalVerification(int income, int priceCar) {
        return income > loanProperties.getMinIncome() || priceCar > loanProperties.getMinPriceCar();
    }

}
