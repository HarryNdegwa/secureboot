package com.example.demo.auth;

import java.util.Optional;

public interface ApplicationUserDao {

    Optional<ApplicationUserDetails> selectApplicationUserByUsername(String username);

}
