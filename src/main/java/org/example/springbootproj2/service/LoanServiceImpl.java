package org.example.springbootproj2.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootproj2.config.LoanProperties;
import org.example.springbootproj2.dto.UserDTO;
import org.example.springbootproj2.model.User;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final UserService userService;
    private final LoanProperties loanProperties;


    @Override
    public int loanApproval(Long id) {
        int priceCar = 0;
        int income = 0;

        User user = userService.getUserById(id);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDTO[]> response = restTemplate.getForEntity(loanProperties.getLink(), UserDTO[].class);
        List<UserDTO> userList = List.of(response.getBody());

        Optional<UserDTO> userUrl = userList.stream()
                .filter(u -> Objects.equals(u.getId(), id))
                .findFirst();

        if (userUrl.isPresent()) {
            income = userUrl.get().getIncome();
        } else {
            income = user.getIncome();
        }


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
