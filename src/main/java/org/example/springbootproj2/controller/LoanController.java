package org.example.springbootproj2.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootproj2.config.LoanProperties;
import org.example.springbootproj2.dto.UserDTO;
import org.example.springbootproj2.service.LoanService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;
    private final LoanProperties loanProperties;

    @GetMapping
    public ResponseEntity<Integer> getLoan(@RequestParam("userId") Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDTO[]> response = restTemplate.getForEntity(loanProperties.getLink(), UserDTO[].class);
        List<UserDTO> userList = List.of(response.getBody());

        Optional<UserDTO> user = userList.stream()
                .filter(u -> Objects.equals(u.getId(), userId))
                .findFirst();
        if (user.isPresent()) {
            return ResponseEntity.ok(loanService.loanApproval(user.get().getIncome(), userId));
        } else {
            return ResponseEntity.ok(loanService.loanApproval(0, userId));
        }

    }
}
