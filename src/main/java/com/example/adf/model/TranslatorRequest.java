package com.example.adf.model;

import java.util.List;

public class TranslatorRequest {
    private List<FieldSpec> fields;

    public List<FieldSpec> getFields() {
        return fields;
    }

    public void setFields(List<FieldSpec> fields) {
        this.fields = fields;
    }
}
