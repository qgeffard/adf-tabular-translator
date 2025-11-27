package com.example.adf.controller;

import com.example.adf.model.TabularTranslator;
import com.example.adf.model.TranslatorRequest;
import com.example.adf.service.TranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TranslatorController {

    private final TranslatorService translatorService;
    private final com.example.adf.service.CsvParserService csvParserService;

    @Autowired
    public TranslatorController(TranslatorService translatorService,
            com.example.adf.service.CsvParserService csvParserService) {
        this.translatorService = translatorService;
        this.csvParserService = csvParserService;
    }

    @PostMapping("/generate-translator")
    public TabularTranslator generate(@RequestBody TranslatorRequest request) {
        return translatorService.generateTranslator(request);
    }

    @PostMapping("/generate-translator/csv")
    public TabularTranslator generateFromCsv(@RequestParam("file") org.springframework.web.multipart.MultipartFile file)
            throws Exception {
        java.util.List<com.example.adf.model.FieldSpec> fields = csvParserService.parseCsv(file);
        TranslatorRequest request = new TranslatorRequest();
        request.setFields(fields);
        return translatorService.generateTranslator(request);
    }
}
