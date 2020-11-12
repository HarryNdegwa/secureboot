package com.example.demo.security;

import java.util.Set;

import com.google.common.collect.Sets;

public enum UserRoles {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(UserPermissions.STUDENT_WRITE, UserPermissions.STUDENT_READ, UserPermissions.COURSE_READ,
            UserPermissions.COURSE_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(UserPermissions.STUDENT_READ, UserPermissions.COURSE_READ));

    private final Set<UserPermissions> permissions;

    UserRoles(Set<UserPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermissions> getPermissions() {
        return permissions;
    }
}
