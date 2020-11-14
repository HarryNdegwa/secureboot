package com.example.demo.auth;

import java.util.List;
import java.util.Optional;

import com.example.demo.security.UserRoles;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

    private PasswordEncoder passwordEncoder;

    @Autowired
    FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().stream().filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser("mike", passwordEncoder.encode("mike"), UserRoles.ADMIN.getGrantedAuthorities(),
                        true, true, true, true),
                new ApplicationUser("tom", passwordEncoder.encode("tom"),
                        UserRoles.ADMINTRAINEE.getGrantedAuthorities(), true, true, true, true),
                new ApplicationUser("anna", passwordEncoder.encode("anna"), UserRoles.ADMIN.getGrantedAuthorities(),
                        true, true, true, true));
        return applicationUsers;
    }

}
