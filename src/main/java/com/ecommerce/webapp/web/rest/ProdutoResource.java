package com.ecommerce.webapp.web.rest;

import com.ecommerce.webapp.repository.ProdutoRepository;
import com.ecommerce.webapp.service.ProdutoService;
import com.ecommerce.webapp.service.dto.ProdutoDTO;
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
 * REST controller for managing {@link com.ecommerce.webapp.domain.Produto}.
 */
@RestController
@RequestMapping("/api")
public class ProdutoResource {

    private final Logger log = LoggerFactory.getLogger(ProdutoResource.class);

    private static final String ENTITY_NAME = "produto";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProdutoService produtoService;

    private final ProdutoRepository produtoRepository;

    public ProdutoResource(ProdutoService produtoService, ProdutoRepository produtoRepository) {
        this.produtoService = produtoService;
        this.produtoRepository = produtoRepository;
    }

    /**
     * {@code POST  /produtos} : Create a new produto.
     *
     * @param produtoDTO the produtoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new produtoDTO, or with status {@code 400 (Bad Request)} if the produto has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/produtos")
    public ResponseEntity<ProdutoDTO> createProduto(@RequestBody ProdutoDTO produtoDTO) throws URISyntaxException {
        log.debug("REST request to save Produto : {}", produtoDTO);
        if (produtoDTO.getId() != null) {
            throw new BadRequestAlertException("A new produto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProdutoDTO result = produtoService.save(produtoDTO);
        return ResponseEntity
            .created(new URI("/api/produtos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /produtos/:id} : Updates an existing produto.
     *
     * @param id the id of the produtoDTO to save.
     * @param produtoDTO the produtoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated produtoDTO,
     * or with status {@code 400 (Bad Request)} if the produtoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the produtoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/produtos/{id}")
    public ResponseEntity<ProdutoDTO> updateProduto(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProdutoDTO produtoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Produto : {}, {}", id, produtoDTO);
        if (produtoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, produtoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!produtoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProdutoDTO result = produtoService.save(produtoDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, produtoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /produtos/:id} : Partial updates given fields of an existing produto, field will ignore if it is null
     *
     * @param id the id of the produtoDTO to save.
     * @param produtoDTO the produtoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated produtoDTO,
     * or with status {@code 400 (Bad Request)} if the produtoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the produtoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the produtoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/produtos/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<ProdutoDTO> partialUpdateProduto(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProdutoDTO produtoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Produto partially : {}, {}", id, produtoDTO);
        if (produtoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, produtoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!produtoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProdutoDTO> result = produtoService.partialUpdate(produtoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, produtoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /produtos} : get all the produtos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of produtos in body.
     */
    @GetMapping("/produtos")
    public List<ProdutoDTO> getAllProdutos() {
        log.debug("REST request to get all Produtos");
        return produtoService.findAll();
    }

    /**
     * {@code GET  /produtos/:id} : get the "id" produto.
     *
     * @param id the id of the produtoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the produtoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/produtos/{id}")
    public ResponseEntity<ProdutoDTO> getProduto(@PathVariable Long id) {
        log.debug("REST request to get Produto : {}", id);
        Optional<ProdutoDTO> produtoDTO = produtoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(produtoDTO);
    }

    /**
     * {@code DELETE  /produtos/:id} : delete the "id" produto.
     *
     * @param id the id of the produtoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        log.debug("REST request to delete Produto : {}", id);
        produtoService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
