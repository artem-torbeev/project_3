package com.example.application.service;

import com.example.application.model.FormUser;
import com.example.application.model.Role;
import com.example.application.model.User;
import com.example.application.repository.RoleRepository;
import com.example.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements CustomService<User> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
    }


    public void addUser(FormUser user) {
        Long idRole;
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRole().equals("ROLE_ADMIN")) {
            idRole = 2L;
        } else {
            idRole = 1L;
        }
        Role role = roleRepository.findRoleById(idRole)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role"));
        newUser.getRole().add(role);
        userRepository.save(newUser);
    }

    public void updateUserById(Long id, FormUser user) {
        User oldUser = userRepository.findUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        oldUser.setUsername(user.getUsername());
        oldUser.setEmail(user.getEmail());
        userRepository.save(oldUser);
    }

}
