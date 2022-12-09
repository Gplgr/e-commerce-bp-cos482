package com.ecommerce.webapp.service;

import com.ecommerce.webapp.domain.Ecommerce;
import com.ecommerce.webapp.repository.EcommerceRepository;
import com.ecommerce.webapp.service.dto.EcommerceDTO;
import com.ecommerce.webapp.service.mapper.EcommerceMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Ecommerce}.
 */
@Service
@Transactional
public class EcommerceService {

    private final Logger log = LoggerFactory.getLogger(EcommerceService.class);

    private final EcommerceRepository ecommerceRepository;

    private final EcommerceMapper ecommerceMapper;

    public EcommerceService(EcommerceRepository ecommerceRepository, EcommerceMapper ecommerceMapper) {
        this.ecommerceRepository = ecommerceRepository;
        this.ecommerceMapper = ecommerceMapper;
    }

    /**
     * Save a ecommerce.
     *
     * @param ecommerceDTO the entity to save.
     * @return the persisted entity.
     */
    public EcommerceDTO save(EcommerceDTO ecommerceDTO) {
        log.debug("Request to save Ecommerce : {}", ecommerceDTO);
        Ecommerce ecommerce = ecommerceMapper.toEntity(ecommerceDTO);
        ecommerce = ecommerceRepository.save(ecommerce);
        return ecommerceMapper.toDto(ecommerce);
    }

    /**
     * Partially update a ecommerce.
     *
     * @param ecommerceDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EcommerceDTO> partialUpdate(EcommerceDTO ecommerceDTO) {
        log.debug("Request to partially update Ecommerce : {}", ecommerceDTO);

        return ecommerceRepository
            .findById(ecommerceDTO.getId())
            .map(
                existingEcommerce -> {
                    ecommerceMapper.partialUpdate(existingEcommerce, ecommerceDTO);
                    return existingEcommerce;
                }
            )
            .map(ecommerceRepository::save)
            .map(ecommerceMapper::toDto);
    }

    /**
     * Get all the ecommerces.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EcommerceDTO> findAll() {
        log.debug("Request to get all Ecommerces");
        return ecommerceRepository.findAll().stream().map(ecommerceMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ecommerce by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EcommerceDTO> findOne(Long id) {
        log.debug("Request to get Ecommerce : {}", id);
        return ecommerceRepository.findById(id).map(ecommerceMapper::toDto);
    }

    /**
     * Delete the ecommerce by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Ecommerce : {}", id);
        ecommerceRepository.deleteById(id);
    }
}
