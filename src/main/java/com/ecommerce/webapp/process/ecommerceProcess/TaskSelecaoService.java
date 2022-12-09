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
public class TaskSelecaoService {

    private final TaskInstanceService taskInstanceService;

    private final EcommerceService ecommerceService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final EcommerceProcessRepository ecommerceProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskSelecaoMapper taskSelecaoMapper;

    private final EcommerceProcessMapper ecommerceProcessMapper;

    public TaskSelecaoService(
        TaskInstanceService taskInstanceService,
        EcommerceService ecommerceService,
        TaskInstanceRepository taskInstanceRepository,
        EcommerceProcessRepository ecommerceProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskSelecaoMapper taskSelecaoMapper,
        EcommerceProcessMapper ecommerceProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.ecommerceService = ecommerceService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.ecommerceProcessRepository = ecommerceProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskSelecaoMapper = taskSelecaoMapper;
        this.ecommerceProcessMapper = ecommerceProcessMapper;
    }

    public TaskSelecaoContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        EcommerceProcessDTO ecommerceProcess = ecommerceProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskSelecaoMapper::toEcommerceProcessDTO)
            .orElseThrow();

        TaskSelecaoContextDTO taskSelecaoContext = new TaskSelecaoContextDTO();
        taskSelecaoContext.setTaskInstance(taskInstanceDTO);
        taskSelecaoContext.setEcommerceProcess(ecommerceProcess);

        return taskSelecaoContext;
    }

    public TaskSelecaoContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskSelecaoContextDTO taskSelecaoContext) {
        EcommerceDTO ecommerceDTO = ecommerceService.findOne(taskSelecaoContext.getEcommerceProcess().getEcommerce().getId()).orElseThrow();
        ecommerceDTO.setUserID(taskSelecaoContext.getEcommerceProcess().getEcommerce().getUserID());
        ecommerceDTO.setProductQuantity(taskSelecaoContext.getEcommerceProcess().getEcommerce().getProductQuantity());
        ecommerceDTO.setProduto(taskSelecaoContext.getEcommerceProcess().getEcommerce().getProduto());
        ecommerceService.save(ecommerceDTO);
    }

    public void complete(TaskSelecaoContextDTO taskSelecaoContext) {
        save(taskSelecaoContext);
        EcommerceProcessDTO ecommerceProcess = ecommerceProcessRepository
            .findByProcessInstanceId(taskSelecaoContext.getEcommerceProcess().getProcessInstance().getId())
            .map(ecommerceProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskSelecaoContext.getTaskInstance(), ecommerceProcess);
    }
}
