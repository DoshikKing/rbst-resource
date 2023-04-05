package com.rbs.rbstresource.service;

import com.rbs.rbstresource.service.ORMRepository.PaySystemRepository;
import com.rbs.rbstresource.component.PaySystem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class PaySystemService {
    private final PaySystemRepository paySystemRepo;

    @Autowired
    public PaySystemService(PaySystemRepository paySystemRepo){
        this.paySystemRepo = paySystemRepo;
    }

    public PaySystem findByType(String type) {
        log.info("Found pay system by type {}", type);
        return paySystemRepo.findByType(type);
    }

    public PaySystem findById(Long id) {
        log.info("Found pay system by id {}", id);
        return paySystemRepo.getReferenceById(id);
    }
}
