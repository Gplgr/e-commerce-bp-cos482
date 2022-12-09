package com.ecommerce.webapp.process.ecommerceProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ecommerce-process/task-confirmacao")
public class TaskConfirmacaoController {

    private final Logger log = LoggerFactory.getLogger(TaskConfirmacaoController.class);

    private final TaskConfirmacaoService taskConfirmacaoService;

    public TaskConfirmacaoController(TaskConfirmacaoService taskConfirmacaoService) {
        this.taskConfirmacaoService = taskConfirmacaoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskConfirmacaoContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskConfirmacaoContextDTO taskConfirmacaoContext = taskConfirmacaoService.loadContext(id);
        return ResponseEntity.ok(taskConfirmacaoContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskConfirmacaoContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskConfirmacaoContextDTO taskConfirmacaoContext = taskConfirmacaoService.claim(id);
        return ResponseEntity.ok(taskConfirmacaoContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskConfirmacaoContextDTO taskConfirmacaoContext) {
        log.debug("REST request to complete EcommerceProcess.TaskConfirmacao {}", taskConfirmacaoContext.getTaskInstance().getId());
        taskConfirmacaoService.complete(taskConfirmacaoContext);
        return ResponseEntity.noContent().build();
    }
}
