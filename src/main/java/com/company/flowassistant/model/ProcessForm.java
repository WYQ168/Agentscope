package com.company.flowassistant.model;

import java.util.HashMap;
import java.util.Map;

public class ProcessForm {
    private String processType;
    private Map<String, Object> fields = new HashMap<>();

    public void put(String key, Object value) {
        fields.put(key, value);
    }

    public String getProcessType() { return processType; }
    public void setProcessType(String processType) { this.processType = processType; }

    public Map<String, Object> getFields() { return fields; }
    public void setFields(Map<String, Object> fields) { this.fields = fields; }
}