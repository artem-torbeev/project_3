package com.example.application.service;

import com.example.application.model.FormUser;
import com.example.application.model.Role;
import com.example.application.model.User;
import com.example.application.repository.RoleRepository;
import com.example.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements CustomService<User> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

//    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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

    @Override
    public void addUser(User user) {
        Role role = roleRepository.findRoleById(1L)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role"));
        user.getRole().add(role);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPassword(user.getPassword());
        userRepository.save(user);
    }

    @Override
    public void updateUserById(Long id, User newUser) {
        User oldUser = userRepository.findUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        oldUser.setUsername(newUser.getUsername());
        oldUser.setEmail(newUser.getEmail());
//        oldUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        oldUser.setPassword(newUser.getPassword());
        Role role = roleRepository.findRoleById(1L)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role"));
        oldUser.getRole().add(role);

        userRepository.save(oldUser);
    }

    public void saveUser(FormUser formUser) {
        Long id;
        User user;
        if (formUser.getId() == null) {
            user = new User();
        } else {
            user = findUserById(formUser.getId());
        }
        user.setEmail(formUser.getEmail());
        user.setUsername(formUser.getUsername());
//        user.setPassword(passwordEncoder.encode(formUser.getPassword()));
        user.setPassword(formUser.getPassword());
        if (formUser.getRole().equals("ROLE_ADMIN")) {
            id = 2L;
        } else {
            id = 1L;
        }
        Role role = roleRepository.findRoleById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role"));
        user.getRole().add(role);
        userRepository.save(user);
    }
}
