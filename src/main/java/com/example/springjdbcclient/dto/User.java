package com.example.springjdbcclient.dto;

public record User(Long id, String username, String email, String password) {
    public User withPassword(String newPassword) {
        return new User(id, username, email, newPassword);
    }
}
