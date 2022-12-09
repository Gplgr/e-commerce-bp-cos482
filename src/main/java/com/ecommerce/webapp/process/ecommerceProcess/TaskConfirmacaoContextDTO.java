package com.ecommerce.webapp.process.ecommerceProcess;

import com.ecommerce.webapp.service.dto.EcommerceProcessDTO;
import org.akip.service.dto.TaskInstanceDTO;

public class TaskConfirmacaoContextDTO {

    private EcommerceProcessDTO ecommerceProcess;
    private TaskInstanceDTO taskInstance;

    public EcommerceProcessDTO getEcommerceProcess() {
        return ecommerceProcess;
    }

    public void setEcommerceProcess(EcommerceProcessDTO ecommerceProcess) {
        this.ecommerceProcess = ecommerceProcess;
    }

    public TaskInstanceDTO getTaskInstance() {
        return taskInstance;
    }

    public void setTaskInstance(TaskInstanceDTO taskInstance) {
        this.taskInstance = taskInstance;
    }
}
