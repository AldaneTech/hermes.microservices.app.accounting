package es.aldane.hermes.cloud.accounting.mapper;

import es.aldane.hermes.cloud.accounting.repository.db.entity.StatusDb;
import es.aldane.hermes.cloud.accounting_api_server_java.model.Status;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface StatusMapper {
    Status statusDbToStatus(StatusDb statusDb);

    List<Status> statusDbListToStatusList(List<StatusDb> statusDb);

    StatusDb statusToStatusDb(Status status);
}
