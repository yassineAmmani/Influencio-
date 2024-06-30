package com.influencio.server.repository;

import com.influencio.server.model.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Integer> {
    Optional<Enterprise> findByEnterpriseName(String enterpriseName);
}
