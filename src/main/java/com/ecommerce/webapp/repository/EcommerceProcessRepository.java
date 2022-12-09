package com.ecommerce.webapp.repository;

import com.ecommerce.webapp.domain.EcommerceProcess;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EcommerceProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EcommerceProcessRepository extends JpaRepository<EcommerceProcess, Long> {
    Optional<EcommerceProcess> findByProcessInstanceId(Long processInstanceId);
}
