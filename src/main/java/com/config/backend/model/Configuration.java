package com.config.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "configurations")
public class Configuration {

    @Id
    private String id;

    private List<List<String>> data;   // 2‑D array

    private String remark;

    /* ---------- constructors ---------- */

    public Configuration() { }

    public Configuration(String id,
                         List<List<String>> data,
                         String remark) {
        this.id = id;
        this.data = data;
        this.remark = remark;
    }

    /* ---------- getters & setters ---------- */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<List<String>> getData() {
        return data;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
