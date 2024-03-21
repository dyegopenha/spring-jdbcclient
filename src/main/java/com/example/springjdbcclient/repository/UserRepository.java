package com.example.springjdbcclient.repository;

import com.example.springjdbcclient.dto.User;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepository {
    private final JdbcClient jdbcClient;

    public UserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<User> findAll() {
        return jdbcClient.sql("SELECT * FROM users")
                .query(User.class)
                .list();
    }

    public User findById(Long id) {
        return jdbcClient.sql("SELECT * FROM users WHERE id = :id")
                .param("id", id)
                .query(User.class)
                .single();
    }

    public User save(User user) {
        jdbcClient.sql("""
				INSERT INTO users (id, username, email, password)
				VALUES (:id, :username, :email, :password)
					""")
                .param("id", user.id())
                .param("username", user.username())
                .param("email", user.email())
                .param("password", user.password())
                .update();
        return user;
    }

    public User update(User user) {
        jdbcClient.sql("""
				UPDATE users
				SET username = :username, email = :email, password = :password
				WHERE id = :id
					""")
                .param("id", user.id())
                .param("username", user.username())
                .param("email", user.email())
                .param("password", user.password())
                .update();
        return user;
    }

    public void deleteById(Long id) {
        jdbcClient.sql("DELETE FROM users WHERE id = :id")
                .param("id", id)
                .update();
    }
}

