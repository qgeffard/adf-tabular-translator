package com.example.adf.service;

import com.example.adf.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TranslatorService {

    public TabularTranslator generateTranslator(TranslatorRequest request) {
        List<AdfMapping> mappings = new ArrayList<>();

        if (request.getFields() != null) {
            for (FieldSpec field : request.getFields()) {
                // Map source to sink 1:1
                // Tokenization flag is present in FieldSpec but ignored for mapping logic
                
                String adfType = mapTypeToAdf(field.getType());
                
                MappingColumn source = new MappingColumn(field.getName(), adfType);
                MappingColumn sink = new MappingColumn(field.getName(), adfType);
                
                mappings.add(new AdfMapping(source, sink));
            }
        }

        TabularTranslator translator = new TabularTranslator();
        translator.setMappings(mappings);
        return translator;
    }

    private String mapTypeToAdf(String inputType) {
        if (inputType == null) return "String";
        
        switch (inputType.toLowerCase()) {
            case "int":
            case "integer":
                return "Int32";
            case "long":
                return "Int64";
            case "decimal":
            case "double":
            case "float":
                return "Decimal"; // or Double, depending on ADF sink
            case "boolean":
                return "Boolean";
            case "date":
            case "datetime":
                return "DateTime";
            default:
                return "String";
        }
    }
}
