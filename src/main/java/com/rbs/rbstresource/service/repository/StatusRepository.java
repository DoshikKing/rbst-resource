package com.rbs.rbstresource.service.repository;

import com.rbs.rbstresource.component.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findByStatusName(String name);
}
