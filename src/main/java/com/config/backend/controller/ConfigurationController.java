package com.config.backend.controller;

import com.config.backend.model.Configuration;
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
                                          @RequestBody RemarkRequest body) {
        boolean updated = service.updateRemark(id, body.getRemark());
        if (updated) {
            return ResponseEntity.ok(new ResponseMessage("success"));
        }
        return ResponseEntity.status(404)
                .body(new ResponseMessage("Configuration not found"));
    }


    public static class RemarkRequest {
        private String remark;
        public String getRemark() { return remark; }
        public void setRemark(String remark) { this.remark = remark; }
    }

    public static class ResponseMessage {
        private String message;
        public ResponseMessage(String message) { this.message = message; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
}
