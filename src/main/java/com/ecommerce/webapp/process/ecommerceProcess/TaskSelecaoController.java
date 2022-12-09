package com.ecommerce.webapp.process.ecommerceProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ecommerce-process/task-selecao")
public class TaskSelecaoController {

    private final Logger log = LoggerFactory.getLogger(TaskSelecaoController.class);

    private final TaskSelecaoService taskSelecaoService;

    public TaskSelecaoController(TaskSelecaoService taskSelecaoService) {
        this.taskSelecaoService = taskSelecaoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskSelecaoContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskSelecaoContextDTO taskSelecaoContext = taskSelecaoService.loadContext(id);
        return ResponseEntity.ok(taskSelecaoContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskSelecaoContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskSelecaoContextDTO taskSelecaoContext = taskSelecaoService.claim(id);
        return ResponseEntity.ok(taskSelecaoContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskSelecaoContextDTO taskSelecaoContext) {
        log.debug("REST request to complete EcommerceProcess.TaskSelecao {}", taskSelecaoContext.getTaskInstance().getId());
        taskSelecaoService.complete(taskSelecaoContext);
        return ResponseEntity.noContent().build();
    }
}
