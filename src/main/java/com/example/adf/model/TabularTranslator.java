package com.example.adf.model;

import java.util.List;

public class TabularTranslator {
    private String type = "TabularTranslator";
    private List<AdfMapping> mappings;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<AdfMapping> getMappings() {
        return mappings;
    }

    public void setMappings(List<AdfMapping> mappings) {
        this.mappings = mappings;
    }
}
