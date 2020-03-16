package com.example.application.service;

import java.util.List;

public interface CustomService<T> {

    List<T> findAll();

    T findUserById(Long id);

    T findUserByUsername(String username);

    void deleteUserById(Long id);

    void addUser(T user);

    void updateUserById(Long id, T user);

}