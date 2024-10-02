package es.aldane.hermes.cloud.accounting.service;

import es.aldane.hermes.cloud.accounting_api_server_java.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRoles(List<String> rolesIds);

    Role getRoleById(Long roleId);

    Role createRole(Role role);

    boolean deleteRole(Long id);

    Role updateRole(Role role);
}
