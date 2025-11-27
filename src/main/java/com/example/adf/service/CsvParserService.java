package com.example.adf.service;

import com.example.adf.model.FieldSpec;
import com.opencsv.CSVReaderHeaderAware;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CsvParserService {

    public List<FieldSpec> parseCsv(MultipartFile file) throws Exception {
        List<FieldSpec> fields = new ArrayList<>();

        try (CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new InputStreamReader(file.getInputStream()))) {
            Map<String, String> values;
            while ((values = reader.readMap()) != null) {
                FieldSpec field = new FieldSpec();

                // Case insensitive lookup helper
                field.setName(getValue(values, "Name"));
                field.setType(getValue(values, "Type"));

                String precision = getValue(values, "Precision");
                if (precision != null && !precision.isEmpty()) {
                    field.setPrecision(Integer.parseInt(precision));
                }

                String scale = getValue(values, "Scale");
                if (scale != null && !scale.isEmpty()) {
                    field.setScale(Integer.parseInt(scale));
                }

                String optional = getValue(values, "Optional");
                field.setOptional(Boolean.parseBoolean(optional));

                String tokenization = getValue(values, "Tokenization");
                field.setTokenizationNeeded(Boolean.parseBoolean(tokenization));

                fields.add(field);
            }
        }

        return fields;
    }

    private String getValue(Map<String, String> map, String key) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(key)) {
                return entry.getValue();
            }
        }
        return null;
    }
}
