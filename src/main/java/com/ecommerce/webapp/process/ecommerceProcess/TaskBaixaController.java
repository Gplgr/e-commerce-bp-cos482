package com.ecommerce.webapp.process.ecommerceProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ecommerce-process/task-baixa")
public class TaskBaixaController {

    private final Logger log = LoggerFactory.getLogger(TaskBaixaController.class);

    private final TaskBaixaService taskBaixaService;

    public TaskBaixaController(TaskBaixaService taskBaixaService) {
        this.taskBaixaService = taskBaixaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskBaixaContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskBaixaContextDTO taskBaixaContext = taskBaixaService.loadContext(id);
        return ResponseEntity.ok(taskBaixaContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskBaixaContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskBaixaContextDTO taskBaixaContext = taskBaixaService.claim(id);
        return ResponseEntity.ok(taskBaixaContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskBaixaContextDTO taskBaixaContext) {
        log.debug("REST request to complete EcommerceProcess.TaskBaixa {}", taskBaixaContext.getTaskInstance().getId());
        taskBaixaService.complete(taskBaixaContext);
        return ResponseEntity.noContent().build();
    }
}
