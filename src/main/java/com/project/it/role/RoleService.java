package com.project.it.role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    Role getRoleById(Long rolesId);

    Role saveRole(Role role);

    Role updateRoleById(Role role, Long roleId);

    void deleteRoleById(Long roleId);
}
