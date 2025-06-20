package com.config.backend.service;

import com.config.backend.model.Configuration;
import com.config.backend.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConfigurationService {

    @Autowired
    private ConfigurationRepository repository;

    public Optional<Configuration> getConfigurationById(String id) {
        return repository.findById(id);
    }

    public boolean updateRemark(String id, String remark) {
        Optional<Configuration> opt = repository.findById(id);
        if (opt.isPresent()) {
            Configuration cfg = opt.get();
            cfg.setRemark(remark);
            repository.save(cfg);
            return true;
        }
        return false;
    }
    public Configuration createConfiguration(Configuration config) {
        return repository.save(config);
    }

    public boolean deleteConfiguration(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<Configuration> getAllConfigurations() {
        return repository.findAll();
    }
}
