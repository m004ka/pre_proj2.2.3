package org.example.springbootproj2.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootproj2.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @GetMapping
    public ResponseEntity<Integer> getLoan(@RequestParam("userId") Long userId) {
        return ResponseEntity.ok(loanService.loanApproval(userId));
    }
}
