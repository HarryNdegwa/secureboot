package com.example.demo.auth;

import java.util.Optional;

public interface ApplicationUserDao {

    // Optional<ApplicationUser> selectUserByEmail(String email);
    Optional<ApplicationUser> selectApplicationUserByUsername(String username);

}
