package com.rbs.rbstresource.service.repository;

import com.rbs.rbstresource.component.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TariffRepository extends JpaRepository<Tariff, Long> {
    Tariff findByTariffName(String name);
    Tariff findByTariffPercentage(float percentage);
}
