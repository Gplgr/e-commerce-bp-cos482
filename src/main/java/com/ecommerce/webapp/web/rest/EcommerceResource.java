package com.ecommerce.webapp.web.rest;

import com.ecommerce.webapp.repository.EcommerceRepository;
import com.ecommerce.webapp.service.EcommerceService;
import com.ecommerce.webapp.service.dto.EcommerceDTO;
import com.ecommerce.webapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.ecommerce.webapp.domain.Ecommerce}.
 */
@RestController
@RequestMapping("/api")
public class EcommerceResource {

    private final Logger log = LoggerFactory.getLogger(EcommerceResource.class);

    private final EcommerceService ecommerceService;

    private final EcommerceRepository ecommerceRepository;

    public EcommerceResource(EcommerceService ecommerceService, EcommerceRepository ecommerceRepository) {
        this.ecommerceService = ecommerceService;
        this.ecommerceRepository = ecommerceRepository;
    }

    /**
     * {@code GET  /ecommerces} : get all the ecommerces.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecommerces in body.
     */
    @GetMapping("/ecommerces")
    public List<EcommerceDTO> getAllEcommerces() {
        log.debug("REST request to get all Ecommerces");
        return ecommerceService.findAll();
    }

    /**
     * {@code GET  /ecommerces/:id} : get the "id" ecommerce.
     *
     * @param id the id of the ecommerceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecommerceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ecommerces/{id}")
    public ResponseEntity<EcommerceDTO> getEcommerce(@PathVariable Long id) {
        log.debug("REST request to get Ecommerce : {}", id);
        Optional<EcommerceDTO> ecommerceDTO = ecommerceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ecommerceDTO);
    }
}
