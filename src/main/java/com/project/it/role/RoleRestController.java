package com.project.it.role;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleRestController {

    private final RoleService roleService;

    public RoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{roleId}")
    public Role getRoleById(@PathVariable Long roleId) {
        return roleService.getRoleById(roleId);
    }

    @PostMapping
    public Role saveRole(@RequestBody Role role) {
        return roleService.saveRole(role);
    }

    @PutMapping("/{roleId}")
    public Role updateRoleById(@RequestBody Role role, @PathVariable Long roleId) {
        return roleService.updateRoleById(role, roleId);
    }

    @DeleteMapping("/{roleId}")
    public void deleteRoleById(@PathVariable Long roleId) {
        roleService.deleteRoleById(roleId);
    }

}
