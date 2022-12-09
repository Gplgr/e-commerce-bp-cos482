package com.ecommerce.webapp.web.rest;

import com.ecommerce.webapp.service.EcommerceProcessService;
import com.ecommerce.webapp.service.dto.EcommerceProcessDTO;
import com.ecommerce.webapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.ecommerce.webapp.domain.EcommerceProcess}.
 */
@RestController
@RequestMapping("/api")
public class EcommerceProcessResource {

    private final Logger log = LoggerFactory.getLogger(EcommerceProcessResource.class);

    private static final String ENTITY_NAME = "ecommerceProcess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EcommerceProcessService ecommerceProcessService;

    public EcommerceProcessResource(EcommerceProcessService ecommerceProcessService) {
        this.ecommerceProcessService = ecommerceProcessService;
    }

    /**
     * {@code POST  /ecommerce-processes} : Create a new ecommerceProcess.
     *
     * @param ecommerceProcessDTO the ecommerceProcessDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ecommerceProcessDTO, or with status {@code 400 (Bad Request)} if the ecommerceProcess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ecommerce-processes")
    public ResponseEntity<EcommerceProcessDTO> create(@RequestBody EcommerceProcessDTO ecommerceProcessDTO) throws URISyntaxException {
        log.debug("REST request to save EcommerceProcess : {}", ecommerceProcessDTO);
        if (ecommerceProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new ecommerceProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EcommerceProcessDTO result = ecommerceProcessService.create(ecommerceProcessDTO);
        return ResponseEntity
            .created(new URI("/api/ecommerce-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ecommerce-processes} : get all the ecommerceProcesss.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ecommerceProcesss in body.
     */
    @GetMapping("/ecommerce-processes")
    public List<EcommerceProcessDTO> getAllEcommerceProcesss() {
        log.debug("REST request to get all EcommerceProcesss");
        return ecommerceProcessService.findAll();
    }

    /**
     * {@code GET  /ecommerce-processes/:id} : get the "id" ecommerceProcess.
     *
     * @param processInstanceId the id of the ecommerceProcessDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ecommerceProcessDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ecommerce-processes/{processInstanceId}")
    public ResponseEntity<EcommerceProcessDTO> getByProcessInstanceId(@PathVariable Long processInstanceId) {
        log.debug("REST request to get EcommerceProcess by processInstanceId : {}", processInstanceId);
        Optional<EcommerceProcessDTO> ecommerceProcessDTO = ecommerceProcessService.findByProcessInstanceId(processInstanceId);
        return ResponseUtil.wrapOrNotFound(ecommerceProcessDTO);
    }
}
