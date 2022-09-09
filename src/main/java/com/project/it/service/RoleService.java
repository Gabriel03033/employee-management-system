package com.project.it.service;

import com.project.it.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(Long rolesId);
    Role saveRole(Role role);
    Role updateRoleById(Role role, Long roleId);
    void deleteRoleById(Long roleId);
}
