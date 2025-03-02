package org.example.springbootproj2.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootproj2.DTO.UserDTO;
import org.example.springbootproj2.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/loan")
@RequiredArgsConstructor
public class UserController {

    @Value("${loan.link}")
    private String link;

    @GetMapping
    public int getLoan(@RequestParam("id") Long id){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDTO[]> response = restTemplate.getForEntity(link, UserDTO[].class);
        List<UserDTO> userList = List.of(response.getBody());
        Optional<UserDTO> user = userList.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();


    }
}
