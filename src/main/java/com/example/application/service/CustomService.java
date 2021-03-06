package com.example.application.service;

import java.util.List;

public interface CustomService<T> {

    List<T> findAll();

    T findUserById(Long id);

    T findUserByEmail(String email);

    void deleteUserById(Long id);

}
