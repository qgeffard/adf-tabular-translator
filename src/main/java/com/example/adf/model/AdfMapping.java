package com.example.adf.model;

public class AdfMapping {
    private MappingColumn source;
    private MappingColumn sink;

    public AdfMapping() {
    }

    public AdfMapping(MappingColumn source, MappingColumn sink) {
        this.source = source;
        this.sink = sink;
    }

    public MappingColumn getSource() {
        return source;
    }

    public void setSource(MappingColumn source) {
        this.source = source;
    }

    public MappingColumn getSink() {
        return sink;
    }

    public void setSink(MappingColumn sink) {
        this.sink = sink;
    }
}
