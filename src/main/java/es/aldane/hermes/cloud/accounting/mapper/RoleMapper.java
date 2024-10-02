package es.aldane.hermes.cloud.accounting.mapper;

import es.aldane.hermes.cloud.accounting.repository.db.entity.RoleDb;
import es.aldane.hermes.cloud.accounting_api_server_java.model.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    Role roleDbToRole(RoleDb roleDb);

    List<Role> roleDbListToRoleList(List<RoleDb> roleDb);

    RoleDb roleToRoleDb(Role role);
}

