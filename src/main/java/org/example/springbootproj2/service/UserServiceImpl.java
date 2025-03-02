package org.example.springbootproj2.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootproj2.model.User;
import org.example.springbootproj2.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public User getUserById(long id) {
        return userRepository.getUserById(id);
    }

}
