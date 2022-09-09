package com.project.it.service.Impl;

import com.project.it.entity.Role;
import com.project.it.repository.RoleRepository;
import com.project.it.service.RoleService;
import java.util.List;

public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    public RoleServiceImpl(RoleRepository roleRepository) { this.roleRepository = roleRepository;}

    @Override
    public List<Role> getAllRoles() {return roleRepository.findAll();}

    @Override
    public Role getRoleById(Long rolesId) {
        return roleRepository.findById(rolesId).orElseThrow(() -> new RuntimeException("No role found with id: " + rolesId));}

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRoleById(Role role, Long roleId) {
        Role roleToUpdate = getRoleById(roleId);
        roleToUpdate.setRoleId(role.getRoleId());
        roleToUpdate.setName(role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void deleteRoleById(Long roleId) {
        roleRepository.deleteById(roleId);
    }
}
