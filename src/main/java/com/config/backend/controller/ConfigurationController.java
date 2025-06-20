package com.config.backend.controller;

import com.config.backend.model.Configuration;
import com.config.backend.model.Remark;
import com.config.backend.model.Response;
import com.config.backend.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/configurations")
@CrossOrigin(origins = "http://localhost:3000")
public class ConfigurationController {

    @Autowired
    private ConfigurationService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getConfiguration(@PathVariable String id) {
        Optional<Configuration> cfg = service.getConfigurationById(id);
        if (cfg.isPresent()) {
            return ResponseEntity.ok(cfg.get().getData());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRemark(@PathVariable String id,
                                          @RequestBody Remark body) {
        boolean updated = service.updateRemark(id, body.getRemark());
        if (updated) {
            return ResponseEntity.ok(new Response("success"));
        }
        return ResponseEntity.status(404)
                .body(new Response("Configuration not found"));
    }

    @PostMapping
    public ResponseEntity<?> createConfiguration(@RequestBody Configuration config) {
        Configuration saved = service.createConfiguration(config);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteConfiguration(@PathVariable String id) {
        boolean deleted = service.deleteConfiguration(id);
        if (deleted) {
            return ResponseEntity.ok(new Response("deleted"));
        }
        return ResponseEntity.status(404)
                .body(new Response("Configuration not found"));
    }
    @GetMapping
    public ResponseEntity<?> getAllConfigurations() {
        return ResponseEntity.ok(service.getAllConfigurations());
    }



}
