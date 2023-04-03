package com.rbs.rbstresource.service;

import com.rbs.rbstresource.component.Status;
import com.rbs.rbstresource.service.ORMRepository.StatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Component
@Transactional
public class StatusService {
    StatusRepository statusRepo;

    @Autowired
    public StatusService(StatusRepository statusRepo){
        this.statusRepo = statusRepo;
    }

    public Status findByStatusName(String name) {
        log.info("Found status by name {}", name);
        return statusRepo.findByStatusName(name);
    }

    public Status findById(Long id) {
        log.info("Found status by id {}", id);
        return statusRepo.getReferenceById(id);
    }
}
