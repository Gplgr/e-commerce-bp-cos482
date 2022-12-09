package com.ecommerce.webapp.repository;

import com.ecommerce.webapp.domain.Ecommerce;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Ecommerce entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EcommerceRepository extends JpaRepository<Ecommerce, Long> {}
