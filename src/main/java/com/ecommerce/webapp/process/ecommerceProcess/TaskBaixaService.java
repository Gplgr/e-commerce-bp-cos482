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
public class TaskBaixaService {

    private final TaskInstanceService taskInstanceService;

    private final EcommerceService ecommerceService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final EcommerceProcessRepository ecommerceProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskBaixaMapper taskBaixaMapper;

    private final EcommerceProcessMapper ecommerceProcessMapper;

    public TaskBaixaService(
        TaskInstanceService taskInstanceService,
        EcommerceService ecommerceService,
        TaskInstanceRepository taskInstanceRepository,
        EcommerceProcessRepository ecommerceProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskBaixaMapper taskBaixaMapper,
        EcommerceProcessMapper ecommerceProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.ecommerceService = ecommerceService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.ecommerceProcessRepository = ecommerceProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskBaixaMapper = taskBaixaMapper;
        this.ecommerceProcessMapper = ecommerceProcessMapper;
    }

    public TaskBaixaContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        EcommerceProcessDTO ecommerceProcess = ecommerceProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskBaixaMapper::toEcommerceProcessDTO)
            .orElseThrow();

        TaskBaixaContextDTO taskBaixaContext = new TaskBaixaContextDTO();
        taskBaixaContext.setTaskInstance(taskInstanceDTO);
        taskBaixaContext.setEcommerceProcess(ecommerceProcess);

        return taskBaixaContext;
    }

    public TaskBaixaContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskBaixaContextDTO taskBaixaContext) {
        EcommerceDTO ecommerceDTO = ecommerceService.findOne(taskBaixaContext.getEcommerceProcess().getEcommerce().getId()).orElseThrow();
        ecommerceDTO.setUserID(taskBaixaContext.getEcommerceProcess().getEcommerce().getUserID());
        ecommerceDTO.setProductQuantity(taskBaixaContext.getEcommerceProcess().getEcommerce().getProductQuantity());
        ecommerceDTO.setProductsAvailable(taskBaixaContext.getEcommerceProcess().getEcommerce().getProductsAvailable());
        ecommerceService.save(ecommerceDTO);
    }

    public void complete(TaskBaixaContextDTO taskBaixaContext) {
        save(taskBaixaContext);
        EcommerceProcessDTO ecommerceProcess = ecommerceProcessRepository
            .findByProcessInstanceId(taskBaixaContext.getEcommerceProcess().getProcessInstance().getId())
            .map(ecommerceProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskBaixaContext.getTaskInstance(), ecommerceProcess);
    }
}
