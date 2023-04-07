package com.rbs.rbstresource.service;

import com.rbs.rbstresource.service.repository.TariffRepository;
import com.rbs.rbstresource.component.Tariff;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Slf4j
@Transactional
@Deprecated
public class TariffService {
    private final TariffRepository tariffRepo;

    @Autowired
    public TariffService(TariffRepository tariffRepo){
        this.tariffRepo = tariffRepo;
    }

    public Tariff findTariffByTariffName(String name) {
        log.info("Found tariff by tariff name {}", name);
        return tariffRepo.findByTariffName(name);
    }

    public Tariff findTariffById(Long id) {
        log.info("Found tariff by tariff id {}", id);
        return tariffRepo.getReferenceById(id);
    }

    public Tariff findTariffByTariffPercentage(float percentage) {
        log.info("Found tariff by tariff percentage {}", percentage);
        return tariffRepo.findByTariffPercentage(percentage);
    }
}
