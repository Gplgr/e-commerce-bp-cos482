package com.ecommerce.webapp.process.ecommerceProcess;

import com.ecommerce.webapp.repository.EcommerceProcessRepository;
import com.ecommerce.webapp.service.EcommerceService;
import com.ecommerce.webapp.service.dto.EcommerceDTO;
import com.ecommerce.webapp.service.dto.EcommerceProcessDTO;
import com.ecommerce.webapp.service.mapper.EcommerceProcessMapper;
import org.akip.repository.TaskInstanceRepository;
import org.akip.service.TaskInstanceService;
import org.akip.service.dto.TaskInstanceDTO;
import org.akip.service.mapper.TaskInstanceMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskConfirmacaoService {

    private final TaskInstanceService taskInstanceService;

    private final EcommerceService ecommerceService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final EcommerceProcessRepository ecommerceProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskConfirmacaoMapper taskConfirmacaoMapper;

    private final EcommerceProcessMapper ecommerceProcessMapper;

    public TaskConfirmacaoService(
        TaskInstanceService taskInstanceService,
        EcommerceService ecommerceService,
        TaskInstanceRepository taskInstanceRepository,
        EcommerceProcessRepository ecommerceProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskConfirmacaoMapper taskConfirmacaoMapper,
        EcommerceProcessMapper ecommerceProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.ecommerceService = ecommerceService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.ecommerceProcessRepository = ecommerceProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskConfirmacaoMapper = taskConfirmacaoMapper;
        this.ecommerceProcessMapper = ecommerceProcessMapper;
    }

    public TaskConfirmacaoContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        EcommerceProcessDTO ecommerceProcess = ecommerceProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskConfirmacaoMapper::toEcommerceProcessDTO)
            .orElseThrow();

        TaskConfirmacaoContextDTO taskConfirmacaoContext = new TaskConfirmacaoContextDTO();
        taskConfirmacaoContext.setTaskInstance(taskInstanceDTO);
        taskConfirmacaoContext.setEcommerceProcess(ecommerceProcess);

        return taskConfirmacaoContext;
    }

    public TaskConfirmacaoContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskConfirmacaoContextDTO taskConfirmacaoContext) {
        EcommerceDTO ecommerceDTO = ecommerceService
            .findOne(taskConfirmacaoContext.getEcommerceProcess().getEcommerce().getId())
            .orElseThrow();
        ecommerceDTO.setUserID(taskConfirmacaoContext.getEcommerceProcess().getEcommerce().getUserID());
        ecommerceDTO.setProductQuantity(taskConfirmacaoContext.getEcommerceProcess().getEcommerce().getProductQuantity());
        ecommerceDTO.setConfirmation(taskConfirmacaoContext.getEcommerceProcess().getEcommerce().getConfirmation());
        ecommerceService.save(ecommerceDTO);
    }

    public void complete(TaskConfirmacaoContextDTO taskConfirmacaoContext) {
        save(taskConfirmacaoContext);
        EcommerceProcessDTO ecommerceProcess = ecommerceProcessRepository
            .findByProcessInstanceId(taskConfirmacaoContext.getEcommerceProcess().getProcessInstance().getId())
            .map(ecommerceProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskConfirmacaoContext.getTaskInstance(), ecommerceProcess);
    }
}
