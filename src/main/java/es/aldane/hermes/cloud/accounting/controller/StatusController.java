package es.aldane.hermes.cloud.accounting.controller;

import es.aldane.hermes.cloud.accounting.service.StatusService;
import es.aldane.hermes.cloud.accounting_api_server_java.api.StatusApi;
import es.aldane.hermes.cloud.accounting_api_server_java.model.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StatusController implements StatusApi {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @Override
    public ResponseEntity<Status> createStatus(@Valid Status status) {
        return ResponseEntity.ok(statusService.createStatus(status));
    }

    @Override
    public ResponseEntity<Void> deleteStatus(Long statusId) {
        var status = statusService.deleteStatus(statusId);
        return status ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Status> getStatusById(Long statusId) {
        var status = statusService.getStatusById(statusId);
        return status != null ? ResponseEntity.ok(status) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<Status>> getStatus() {
        var states = statusService.getStatus(new ArrayList<>());
        return ResponseEntity.ok(states);
    }

    @Override
    public ResponseEntity<Status> updateStatus(@Valid Status status) {
        return statusService.updateStatus(status) != null ? ResponseEntity.ok(status) : ResponseEntity.badRequest().build();
    }
}
