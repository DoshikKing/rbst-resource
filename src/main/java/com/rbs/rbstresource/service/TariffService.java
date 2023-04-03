package com.rbs.rbstresource.service;

import com.rbs.rbstresource.service.ORMRepository.TariffRepository;
import com.rbs.rbstresource.component.Tariff;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Slf4j
@Component
@Transactional
public class TariffService {

    TariffRepository tariffRepo;

    @Autowired
    public TariffService(TariffRepository tariffRepo){
        this.tariffRepo = tariffRepo;
    }

    public Tariff findByTariffName(String name) {
        log.info("Found tariff by tariff name {}", name);
        return tariffRepo.findByTariffName(name);
    }

    public Tariff findById(Long id) {
        log.info("Found tariff by tariff id {}", id);
        return tariffRepo.getReferenceById(id);
    }

    public Tariff findByTariffPercentage(float percentage) {
        log.info("Found tariff by tariff percentage {}", percentage);
        return tariffRepo.findByTariffPercentage(percentage);
    }
}
