package com.ecommerce.webapp.service;

import com.ecommerce.webapp.domain.EcommerceProcess;
import com.ecommerce.webapp.repository.EcommerceProcessRepository;
import com.ecommerce.webapp.repository.EcommerceRepository;
import com.ecommerce.webapp.service.dto.EcommerceProcessDTO;
import com.ecommerce.webapp.service.mapper.EcommerceProcessMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.akip.domain.ProcessInstance;
import org.akip.service.ProcessInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EcommerceProcess}.
 */
@Service
@Transactional
public class EcommerceProcessService {

    public static final String BPMN_PROCESS_DEFINITION_ID = "EcommerceProcess";

    private final Logger log = LoggerFactory.getLogger(EcommerceProcessService.class);

    private final ProcessInstanceService processInstanceService;

    private final EcommerceRepository ecommerceRepository;

    private final EcommerceProcessRepository ecommerceProcessRepository;

    private final EcommerceProcessMapper ecommerceProcessMapper;

    public EcommerceProcessService(
        ProcessInstanceService processInstanceService,
        EcommerceRepository ecommerceRepository,
        EcommerceProcessRepository ecommerceProcessRepository,
        EcommerceProcessMapper ecommerceProcessMapper
    ) {
        this.processInstanceService = processInstanceService;
        this.ecommerceRepository = ecommerceRepository;
        this.ecommerceProcessRepository = ecommerceProcessRepository;
        this.ecommerceProcessMapper = ecommerceProcessMapper;
    }

    /**
     * Save a ecommerceProcess.
     *
     * @param ecommerceProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public EcommerceProcessDTO create(EcommerceProcessDTO ecommerceProcessDTO) {
        log.debug("Request to save EcommerceProcess : {}", ecommerceProcessDTO);

        EcommerceProcess ecommerceProcess = ecommerceProcessMapper.toEntity(ecommerceProcessDTO);

        //Saving the domainEntity
        ecommerceRepository.save(ecommerceProcess.getEcommerce());

        //Creating the process instance in the Camunda and setting it in the process entity
        ProcessInstance processInstance = processInstanceService.create(
            BPMN_PROCESS_DEFINITION_ID,
            "Ecommerce#" + ecommerceProcess.getEcommerce().getId(),
            ecommerceProcess
        );
        ecommerceProcess.setProcessInstance(processInstance);

        //Saving the process entity
        ecommerceProcess = ecommerceProcessRepository.save(ecommerceProcess);
        return ecommerceProcessMapper.toDto(ecommerceProcess);
    }

    /**
     * Get all the ecommerceProcesss.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EcommerceProcessDTO> findAll() {
        log.debug("Request to get all EcommerceProcesss");
        return ecommerceProcessRepository
            .findAll()
            .stream()
            .map(ecommerceProcessMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ecommerceProcess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EcommerceProcessDTO> findOne(Long id) {
        log.debug("Request to get EcommerceProcess : {}", id);
        return ecommerceProcessRepository.findById(id).map(ecommerceProcessMapper::toDto);
    }

    /**
     * Get one ecommerceProcess by id.
     *
     * @param processInstanceId the id of the processInstance associated to the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EcommerceProcessDTO> findByProcessInstanceId(Long processInstanceId) {
        log.debug("Request to get EcommerceProcess by  processInstanceId: {}", processInstanceId);
        return ecommerceProcessRepository.findByProcessInstanceId(processInstanceId).map(ecommerceProcessMapper::toDto);
    }
}
