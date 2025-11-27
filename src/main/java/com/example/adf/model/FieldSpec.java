package com.example.adf.model;

public class FieldSpec {
    private String name;
    private String type;
    private Integer precision;
    private Integer scale;
    private boolean optional;
    private boolean tokenizationNeeded;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public boolean isTokenizationNeeded() {
        return tokenizationNeeded;
    }

    public void setTokenizationNeeded(boolean tokenizationNeeded) {
        this.tokenizationNeeded = tokenizationNeeded;
    }
}
