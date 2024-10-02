package es.aldane.hermes.cloud.accounting.service;

import es.aldane.hermes.cloud.accounting_api_server_java.model.Status;

import java.util.List;

public interface StatusService {

    List<Status> getStatus(List<String> statesIds);

    Status getStatusById(Long statusId);

    Status createStatus(Status status);

    boolean deleteStatus(Long id);

    Status updateStatus(Status status);

}
