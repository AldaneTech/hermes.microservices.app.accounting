package es.aldane.hermes.cloud.accounting.service;

import es.aldane.hermes.cloud.accounting.mapper.StatusMapper;
import es.aldane.hermes.cloud.accounting.repository.db.StatusDbRepository;
import es.aldane.hermes.cloud.accounting_api_server_java.model.Status;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    private final StatusDbRepository statusDbRepository;
    private final StatusMapper statusMapper;

    public StatusServiceImpl(StatusDbRepository statusDbRepository, StatusMapper statusMapper) {
        this.statusDbRepository = statusDbRepository;
        this.statusMapper = statusMapper;
    }

    @Override
    public List<Status> getStatus(List<String> statesIds) {
        try {
            var statesList = statusDbRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
            return statusMapper.statusDbListToStatusList(statesList);
        } catch (Exception e) {
            System.out.println("Error obtaining status");
            return new ArrayList<>();
        }
    }

    @Override
    public Status getStatusById(Long statusId) {
        try {
            var status = statusDbRepository.findById(statusId).orElse(null);
            return statusMapper.statusDbToStatus(status);
        } catch (Exception e) {
            System.out.println("Error obtaining status with id: " + statusId);
            return null;
        }
    }

    @Override
    public Status createStatus(Status status) {
        try {
            var statusSaved = statusDbRepository.save(statusMapper.statusToStatusDb(status));
            return statusMapper.statusDbToStatus(statusSaved);
        } catch (Exception e) {
            System.out.println("Error creating status");
            return null;
        }
    }

    @Override
    public boolean deleteStatus(Long id) {
        try {
            statusDbRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting status with id: " + id);
            return false;
        }
    }

    @Override
    public Status updateStatus(Status status) {
        try {
            var statusDb = statusDbRepository.findById(status.getId()).orElse(null);
            statusDb.setName(status.getName());
            return statusMapper.statusDbToStatus(statusDbRepository.save(statusDb));
        } catch (Exception e) {
            System.out.println("Error updating status with id: " + status.getId());
            return null;
        }
    }
}
